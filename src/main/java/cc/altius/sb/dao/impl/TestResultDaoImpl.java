/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.sb.dao.TestResultDao;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.DTO.TakerNoOfQuestionAttempted;
import cc.altius.sb.model.DTORowMapper.TakerNoOfQuestionAttemptedRowMapper;
import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionResponse;
import cc.altius.sb.model.rowmapper.QuestionResponseRowMapper;
import cc.altius.sb.model.rowmapper.QuestionRowMapper;
import cc.altius.sb.model.rowmapper.TakerSessionResultRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yogesh
 */
@Repository
public class TestResultDaoImpl implements TestResultDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<TestSession> getTakerSessionResultList(CustomUserDetails curUser, int flag) {

        String sql = "SELECT t.`TAKER_ID`,t.`FIRST_NAME`,t.`LAST_NAME`,t.`EMAIL`,ts.`TEST_STARTED`, "
                + "IF(ts.`LAST_SORT_ORDER`>65,COUNT(q.`QUESTION_ID`),COUNT(qr1.`TEST_SESSION_ID`)) LAST_SORT_ORDER, "
                + "ts.`TEST_ENDED`,ts.`ACTIVE` "
                + "FROM taker t  "
                + "LEFT JOIN test_session ts ON ts.`TAKER_ID`= t.`TAKER_ID` "
                + "LEFT JOIN  (SELECT qr.`TEST_SESSION_ID`,qr.`QUESTION_ID`  "
                + "FROM question_response qr GROUP BY qr.`TEST_SESSION_ID`,qr.`QUESTION_ID`) AS qr1 ON ts.`TEST_SESSION_ID`=qr1.`TEST_SESSION_ID` "
                + "LEFT JOIN question q ON qr1.`QUESTION_ID`=q.`QUESTION_ID` AND q.`DIMENSION_ID`<6  ";
        if (flag == 0) {
            sql += "where  t.FLAG in (0,1,2) ";
        } else if (flag == 1) {
            sql += "where  t.FLAG=1 ";
        } else {
            sql += " where t.FLAG=2 ";
        }
        if (curUser.getRoles().get(0).getRoleId().equals("ROLE_TAKER")) {
            sql += " AND  t.USER_ID='" + curUser.getUserId() + "' ";
        }
        sql += " GROUP BY ts.`TEST_SESSION_ID` ";
        return this.jdbcTemplate.query(sql, new TakerSessionResultRowMapper());
    }

    @Override
    public List<QuestionResponse> getExportRawReport(int takerId) {
        String sql = "SELECT q.`QUESTION_ID`,q.`QUESTION_NAME`, q.`sort_order`, "
                + "qr.`question_option_value`,qr.`question_option_additional_value`, "
                + "qr.`started_question`,qr.`ended_question` "
                + "FROM question_response qr "
                + "LEFT JOIN question q ON q.`QUESTION_ID`=qr.`question_id` "
                + "WHERE qr.`taker_id`= ?";
        return this.jdbcTemplate.query(sql, new QuestionResponseRowMapper(), takerId);
    }

    @Override
    public List<Integer> getWisdomTakerListReport() {

        String sql = " SELECT DISTINCT t.`TAKER_ID` "
                + " FROM taker t   LEFT JOIN test_session ts   ON ts.`TAKER_ID` = t.`TAKER_ID`  "
                + " LEFT JOIN question_response qr  ON ts.`TEST_SESSION_ID` = qr.`TEST_SESSION_ID`  "
                + " WHERE qr.`QUESTION_OPTION_VALUE` IS NOT NULL  "
                + "  AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 5 "
                + "  ORDER BY  t.`TAKER_ID` ";
        return this.jdbcTemplate.queryForList(sql, Integer.class);

    }

    @Override
    public List<Question> getQuestionsSetCompletedList(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional) {
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT  "
                + "q.`ACTIVE`,q.`QUESTION_ID`,q.`QUESTION_NAME`,q.`SORT_ORDER`,q.`IS_REVERSE_KEY`, "
                + "q.`DESCRIPTION`,qd.`DIMENSION_NAME`,qtr.`TRAIT_NAME`,qa.`ASPECT_NAME`, "
                + "qt.`QUESTION_TYPE_ID`,qt.`QUESTION_TYPE_NAME`,qttt.`TEST_TYPE_ID`,qttt.`TEST_TYPE_DESC` "
                + "FROM question_response qr "
                + "LEFT JOIN taker tk ON tk.`TAKER_ID`=qr.`TAKER_ID` "
                + "LEFT JOIN question q  ON  qr.`QUESTION_ID`=q.`QUESTION_ID` "
                + "LEFT JOIN question_type qt ON qt.`QUESTION_TYPE_ID`=q.`QUESTION_TYPE_ID` "
                + "LEFT JOIN question_dimension qd ON qd.`DIMENSION_ID`=q.`DIMENSION_ID` "
                + "LEFT JOIN question_trait qtr ON qtr.`TRAIT_ID`=q.`TRAIT_ID` "
                + "LEFT JOIN question_aspect qa ON qa.`ASPECT_ID`=q.`ASPECT_ID` "
                + "LEFT JOIN test_type  qttt ON qttt.`TEST_TYPE_ID`=q.`TEST_TYPE_ID` "
                + "WHERE 1 ";
        if (curUser.getRoles().get(0).getRoleId().equals("ROLE_TAKER")) {
            sql += " AND tk.USER_ID=:userId ";
            params.put("userId", curUser.getUserId());
        }

        if (dimensionId != -1) {
            sql += " AND q.`DIMENSION_ID`=:dimensionId ";
            params.put("dimensionId", dimensionId);
        }

        if (isMedicalProfessional) {
            sql += " AND  qtr.`TRAIT_ID` NOT IN (57) ";
        } else {
            sql += " AND  qtr.`TRAIT_ID` NOT IN (58) ";
        }
        sql += " GROUP BY q.`QUESTION_ID` ;";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(dataSource);
        return npjt.query(sql, params, new QuestionRowMapper());
    }

    @Override
    public List<Question> getQuestionsSetPendingList(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional) {
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT q.`ACTIVE`,q.`QUESTION_ID`,q.`QUESTION_NAME`,q.`SORT_ORDER`,q.`IS_REVERSE_KEY`, "
                + "q.`DESCRIPTION`,qd.`DIMENSION_NAME`,qtr.`TRAIT_NAME`,qa.`ASPECT_NAME`, "
                + "qt.`QUESTION_TYPE_ID`,qt.`QUESTION_TYPE_NAME`,qttt.`TEST_TYPE_ID`,qttt.`TEST_TYPE_DESC` "
                + "FROM question q "
                + "LEFT JOIN question_response  qr ON qr.`QUESTION_ID`=q.`QUESTION_ID` AND qr.`TAKER_ID`=(SELECT t.`TAKER_ID` FROM taker t WHERE t.`USER_ID`=:userId) "
                + "LEFT JOIN question_type qt ON qt.`QUESTION_TYPE_ID`=q.`QUESTION_TYPE_ID` "
                + "LEFT JOIN question_dimension qd ON qd.`DIMENSION_ID`=q.`DIMENSION_ID` "
                + "LEFT JOIN question_trait qtr ON qtr.`TRAIT_ID`=q.`TRAIT_ID` "
                + "LEFT JOIN question_aspect qa ON qa.`ASPECT_ID`=q.`ASPECT_ID` "
                + "LEFT JOIN test_type  qttt ON qttt.`TEST_TYPE_ID`=q.`TEST_TYPE_ID` "
                + "WHERE qr.`QUESTION_ID` IS NULL AND q.`QUESTION_ID`!=648 ";
        params.put("userId", curUser.getUserId());
        if (dimensionId != -1) {
            sql += " AND q.`DIMENSION_ID`=:dimensionId ";
            params.put("dimensionId", dimensionId);
        }
        if (isMedicalProfessional) {
            sql += " AND  qtr.`TRAIT_ID` NOT IN (57) ";
        } else {
            sql += " AND  qtr.`TRAIT_ID` NOT IN (58) ";
        }
        sql += " GROUP BY q.`QUESTION_ID`;";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(dataSource);
        return npjt.query(sql, params, new QuestionRowMapper());
    }

    @Override
    public List<Map<String, Object>> getQuestionsSetCompletedListCount(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional) {
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT  "
                + "`TEST_TYPE_DESC`,COUNT(*) AS `TOTAL_QUESTION` FROM (SELECT qttt.`TEST_TYPE_DESC` "
                + "FROM question_response qr "
                + "LEFT JOIN taker tk ON tk.`TAKER_ID`=qr.`TAKER_ID` "
                + "LEFT JOIN question q  ON  qr.`QUESTION_ID`=q.`QUESTION_ID` "
                + "LEFT JOIN question_type qt ON qt.`QUESTION_TYPE_ID`=q.`QUESTION_TYPE_ID` "
                + "LEFT JOIN question_dimension qd ON qd.`DIMENSION_ID`=q.`DIMENSION_ID` "
                + "LEFT JOIN question_trait qtr ON qtr.`TRAIT_ID`=q.`TRAIT_ID` "
                + "LEFT JOIN question_aspect qa ON qa.`ASPECT_ID`=q.`ASPECT_ID` "
                + "LEFT JOIN test_type  qttt ON qttt.`TEST_TYPE_ID`=q.`TEST_TYPE_ID` "
                + "WHERE 1 ";
        if (curUser.getRoles().get(0).getRoleId().equals("ROLE_TAKER")) {
            sql += " AND tk.USER_ID=:userId ";
            params.put("userId", curUser.getUserId());
        }

        if (dimensionId != -1) {
            sql += " AND q.`DIMENSION_ID`=:dimensionId ";
            params.put("dimensionId", dimensionId);
        }

        if (isMedicalProfessional) {
            sql += " AND  qtr.`TRAIT_ID` NOT IN (57) ";
        } else {
            sql += " AND  qtr.`TRAIT_ID` NOT IN (58) ";
        }
        sql += " GROUP BY qr.`QUESTION_ID`,qttt.`TEST_TYPE_ID`) s1 GROUP BY `TEST_TYPE_DESC`;";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(dataSource);
        return npjt.queryForList(sql, params);
    }

    @Override
    public List<Map<String, Object>> getQuestionsSetPendingListCount(CustomUserDetails curUser, int dimensionId, boolean isMedicalProfessional) {
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT qttt.`TEST_TYPE_DESC`,COUNT(*) AS `TOTAL_QUESTION` "
                + "FROM question q "
                + "LEFT JOIN question_response  qr ON qr.`QUESTION_ID`=q.`QUESTION_ID` AND qr.`TAKER_ID`=(SELECT t.`TAKER_ID` FROM taker t WHERE t.`USER_ID`=:userId) "
                + "LEFT JOIN question_type qt ON qt.`QUESTION_TYPE_ID`=q.`QUESTION_TYPE_ID` "
                + "LEFT JOIN question_dimension qd ON qd.`DIMENSION_ID`=q.`DIMENSION_ID` "
                + "LEFT JOIN question_trait qtr ON qtr.`TRAIT_ID`=q.`TRAIT_ID` "
                + "LEFT JOIN question_aspect qa ON qa.`ASPECT_ID`=q.`ASPECT_ID` "
                + "LEFT JOIN test_type  qttt ON qttt.`TEST_TYPE_ID`=q.`TEST_TYPE_ID` "
                + "WHERE qr.`QUESTION_ID` IS NULL AND q.`QUESTION_ID`!=648 ";
        params.put("userId", curUser.getUserId());
        if (dimensionId != -1) {
            sql += " AND q.`DIMENSION_ID`=:dimensionId ";
            params.put("dimensionId", dimensionId);
        }
        if (isMedicalProfessional) {
            sql += " AND  qtr.`TRAIT_ID` NOT IN (57) ";
        } else {
            sql += " AND  qtr.`TRAIT_ID` NOT IN (58) ";
        }
        sql += " GROUP BY qttt.`TEST_TYPE_ID`;";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(dataSource);
        return npjt.queryForList(sql, params);
    }

    @Override
    public List<TakerNoOfQuestionAttempted> getNoOfQuestionAttemptedByTaker(int takerId) {
//
//        String sql = "SELECT t.`TAKER_ID`,t.`FIRST_NAME`,t.`LAST_NAME`,COUNT(qr.`QUESTION_RESPONSE_ID`) AS No_of_question_attempted "
//                + "FROM taker t "
//                + "LEFT JOIN question_response qr ON t.`TAKER_ID`=qr.`TAKER_ID` "
//                + "GROUP BY qr.`TAKER_ID`";
        String sql = "SELECT t.`TAKER_ID`,t.`FIRST_NAME`,t.`LAST_NAME`,t.ACTIVE,  "
                + "COUNT(q.`QUESTION_ID`)  AS No_of_question_attempted,ts.`TEST_STARTED`,ts.`TEST_ENDED`,t.`REFERRED_BY`,t.EMAIL,t.MOBILE_NO,t.ZIP_CODE "
                + "FROM taker t  "
                + "LEFT JOIN test_session ts ON ts.`TAKER_ID`= t.`TAKER_ID`  "
                + "LEFT JOIN  (SELECT qr.`TEST_SESSION_ID`,qr.`QUESTION_ID`   "
                + "FROM question_response qr GROUP BY qr.`TEST_SESSION_ID`,qr.`QUESTION_ID`) AS qr1 ON ts.`TEST_SESSION_ID`=qr1.`TEST_SESSION_ID`  "
                + "LEFT JOIN question q ON qr1.`QUESTION_ID`=q.`QUESTION_ID`   ";
        if (takerId != 0) {
            sql = sql + " WHERE t.`TAKER_ID`='" + takerId + "' ";
        }
        sql = sql + "GROUP BY ts.`TEST_SESSION_ID` ";
        return this.jdbcTemplate.query(sql, new TakerNoOfQuestionAttemptedRowMapper());
    }

    @Override
    public int updateNoOfQuestionAttemptedByTaker(int active, int takerId) {
        String sql = "UPDATE `taker` SET ACTIVE=? WHERE TAKER_ID=? ";
        int i = this.jdbcTemplate.update(sql, active, takerId);
        return i;
    }
}
