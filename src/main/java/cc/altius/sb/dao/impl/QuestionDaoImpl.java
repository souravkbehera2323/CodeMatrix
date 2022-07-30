/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.maven.AllAltiusUtil;
import cc.altius.sb.dao.QuestionDao;
import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionAspect;
import cc.altius.sb.model.QuestionDimension;
import cc.altius.sb.model.QuestionOption;
import cc.altius.sb.model.QuestionTrait;
import cc.altius.sb.model.QuestionType;
import cc.altius.sb.model.QuestionTestType;
import cc.altius.sb.model.rowmapper.AspectRowMapper;
import cc.altius.sb.model.rowmapper.DimensionRowMapper;
import cc.altius.sb.model.rowmapper.QuestionResultSetExtractor;
import cc.altius.sb.model.rowmapper.QuestionRowMapper;
import cc.altius.sb.model.rowmapper.QuestionTypeRowMapper;
import cc.altius.sb.model.rowmapper.TestRowMapper;
import cc.altius.sb.model.rowmapper.TraitRowMapper;
//import cc.altius.utils.DateUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yogesh
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {

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
    @Transactional
    public int addQuestion(Question question) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        SimpleJdbcInsert questionInsert = new SimpleJdbcInsert(this.dataSource).withTableName("question").usingGeneratedKeyColumns("QUESTION_ID");
        Map<String, Object> params = new HashMap<>();
        params.put("QUESTION_NAME", question.getQuestionName());
        params.put("QUESTION_TYPE_ID", question.getQuestionType().getQuestionTypeId());
        params.put("TEST_TYPE_ID", question.getQuestionTestType().getQuestionTestTypeId());
        params.put("DESCRIPTION", question.getDescription());
        params.put("DIMENSION_ID", question.getQuestionDimension().getDimensionId());
        params.put("TRAIT_ID", question.getQuestionTrait().getTraitId());
        params.put("ASPECT_ID", question.getQuestionAspect().getAspectId());
        params.put("SORT_ORDER", question.getSortOrder());
        params.put("IS_REVERSE_KEY", question.isIsReverseKey());
        params.put("USES_MODIFIER_ON_QUESTION", question.getUsesModifierOnQuestion());
        params.put("MODIFIER_VALUE", question.getModifierValue());
        params.put("ACTIVE", question.isActive());
        Date dt = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST);
        params.put("CREATED_BY", curUser);
        params.put("CREATED_DATE", dt);
        params.put("LAST_MODIFIED_BY", curUser);
        params.put("LAST_MODIFIED_DATE", dt);
        params.put("DESCIPTION_TOGGLE", question.isDescToggle());

        int questionId = questionInsert.executeAndReturnKey(params).intValue();
        params.clear();

        String sql = "DELETE FROM question_option_mapping WHERE question_option_mapping.`QUESTION_ID`=?";
        this.jdbcTemplate.update(sql, questionId);

        SimpleJdbcInsert questionMappingInsert = new SimpleJdbcInsert(this.dataSource).withTableName("`question_option_mapping`").usingGeneratedKeyColumns("QUESTION_QUESTION_OPTION_ID");
        Map<String, Object> para = new HashMap<>();
        para.put("QUESTION_ID", questionId);
        List<QuestionOption> questionMap = question.getQuestionOptions();
        for (QuestionOption a : questionMap) {
            para.put("QUESTION_OPTION_ID", a.getQuestionOptionId());
            int qInsert = questionMappingInsert.executeAndReturnKey(para).intValue();
        }
        return questionId;
    }

    @Override
    public List<QuestionType> getQuestionTypeList() {
        String sql = "SELECT qt.`QUESTION_TYPE_ID`,qt.`QUESTION_TYPE_NAME` FROM question_type qt;";
        return this.jdbcTemplate.query(sql, new QuestionTypeRowMapper());
    }

    @Override
    public List<QuestionTestType> getTestList() {
        String sql = "SELECT t.`TEST_TYPE_ID`,t.`TEST_TYPE_DESC` FROM test_type t;";
        return this.jdbcTemplate.query(sql, new TestRowMapper());
    }

    @Override
    public List<Question> getQuestionList() {
        String sql = "SELECT q.`ACTIVE`,q.`QUESTION_ID`,q.`QUESTION_NAME`,q.`SORT_ORDER`,q.`IS_REVERSE_KEY`, "
                + "q.`DESCRIPTION`,qd.`DIMENSION_NAME`,qtr.`TRAIT_NAME`,qa.`ASPECT_NAME`, "
                + "qt.`QUESTION_TYPE_ID`,qt.`QUESTION_TYPE_NAME`,qttt.`TEST_TYPE_ID`,qttt.`TEST_TYPE_DESC` "
                + "FROM question q "
                + "LEFT JOIN question_type qt ON qt.`QUESTION_TYPE_ID`=q.`QUESTION_TYPE_ID` "
                + "LEFT JOIN question_dimension qd ON qd.`DIMENSION_ID`=q.`DIMENSION_ID` "
                + "LEFT JOIN question_trait qtr ON qtr.`TRAIT_ID`=q.`TRAIT_ID` "
                + "LEFT JOIN question_aspect qa ON qa.`ASPECT_ID`=q.`ASPECT_ID` "
                + "LEFT JOIN test_type  qttt ON qttt.`TEST_TYPE_ID`=q.`TEST_TYPE_ID` ";
        return this.jdbcTemplate.query(sql, new QuestionRowMapper());
    }

    @Override
    public Question getQuestionByQuestionId(int questionId) {
        String sql = "SELECT q.`QUESTION_ID`,qt.`QUESTION_TYPE_ID`,qt.`QUESTION_TYPE_NAME`,qttt.`TEST_TYPE_ID`,qttt.`TEST_TYPE_DESC`, "
                + "q.`QUESTION_NAME`,q.`SORT_ORDER`,q.`ACTIVE`,qa.`ASPECT_ID`,qa.`ASPECT_NAME`,q.`DESCRIPTION`,"
                + "qd.`DIMENSION_ID`,qd.`DIMENSION_NAME`,q.`IS_REVERSE_KEY`,qtr.`TRAIT_ID`,qtr.`TRAIT_NAME`,qo.`QUESTION_OPTION_NAME`,qo.`QUESTION_OPTION_ID`,q.`DESCIPTION_TOGGLE` "
                + "FROM question q "
                + "LEFT JOIN question_type qt ON qt.`QUESTION_TYPE_ID` = q.`QUESTION_TYPE_ID` "
                + "LEFT JOIN test_type  qttt ON qttt.`TEST_TYPE_ID`=q.`TEST_TYPE_ID` "
                + "LEFT JOIN question_option_mapping qqom ON qqom.`QUESTION_ID`= q.`QUESTION_ID` "
                + "LEFT JOIN question_option qo ON qo.`QUESTION_OPTION_ID` = qqom.`QUESTION_OPTION_ID` "
                + "LEFT JOIN question_dimension qd ON qd.`DIMENSION_ID`=q.`DIMENSION_ID` "
                + "LEFT JOIN question_trait qtr ON qtr.`TRAIT_ID`=q.`TRAIT_ID` "
                + "LEFT JOIN question_aspect qa ON qa.`ASPECT_ID`=q.`ASPECT_ID` "
                + "WHERE q.`QUESTION_ID` = ?";
        return this.jdbcTemplate.query(sql, new QuestionResultSetExtractor(), questionId);

    }

    @Override
    public int updateQuestion(Question question) throws DataStoreException {
        Date curDate = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST);
        String sql = "UPDATE question "
                + "SET  question.`DIMENSION_ID`=:dimensionId,"
                + "question.`TRAIT_ID`=:traitId,";
        if (question.getQuestionAspect().getAspectId() != 0) {
            sql = sql + "question.`ASPECT_ID`=:aspectId,";
        }
        sql = sql + "question.`DESCRIPTION`=:description,"
                + "question.`IS_REVERSE_KEY`=:isReverseKey,"
                + "question.`QUESTION_NAME`=:questionName,"
                + "question.`QUESTION_TYPE_ID`=:questionTypeId,"
                + "question.`SORT_ORDER`=:sortOrder,"
                + "question.`ACTIVE`=:active,"
                + "question.`TEST_TYPE_ID`=:testId,"
                + "question.`MODIFIER_VALUE`=:modifierValue,"
                + "question.`USES_MODIFIER_ON_QUESTION`=:usesModifierOnQuestion, "
                + "question.`DESCIPTION_TOGGLE`=:descToggle "
                + "WHERE question.`QUESTION_ID`=:questionId ;";
        Map<String, Object> params = new HashMap<>();
        if (question.getQuestionAspect().getAspectId() != 0) {
            params.put("aspectId", question.getQuestionAspect().getAspectId());
        }
        params.put("modifierValue", 0);
        params.put("usesModifierOnQuestion", 0);
        params.put("description", question.getDescription());
        params.put("dimensionId", question.getQuestionDimension().getDimensionId());
        params.put("isReverseKey", question.isIsReverseKey());
        params.put("questionName", question.getQuestionName());
        params.put("questionTypeId", question.getQuestionType().getQuestionTypeId());
        params.put("sortOrder", question.getSortOrder());
        params.put("active", question.isActive());
        params.put("testId", question.getQuestionTestType().getQuestionTestTypeId());
        params.put("traitId", question.getQuestionTrait().getTraitId());
//        params.put("curDate", curDate);
        params.put("descToggle", question.isDescToggle());
        params.put("questionId", question.getQuestionId());
        this.namedParameterJdbcTemplate.update(sql, params);
        String sqlDelete = "DELETE FROM question_option_mapping WHERE question_option_mapping.`question_id`=?";
        this.jdbcTemplate.update(sqlDelete, question.getQuestionId());

        SimpleJdbcInsert questionMappingInsert = new SimpleJdbcInsert(this.dataSource).withTableName("question_option_mapping").usingGeneratedKeyColumns("QUESTION_QUESTION_OPTION_ID");
        Map<String, Object> para = new HashMap<>();
        para.put("QUESTION_ID", question.getQuestionId());
        List<QuestionOption> questionMap = question.getQuestionOptions();
        for (QuestionOption a : questionMap) {
            para.put("QUESTION_OPTION_ID", a.getQuestionOptionId());
            int qInsert = questionMappingInsert.executeAndReturnKey(para).intValue();
        }
        return 1;
    }

    @Override
    public List<QuestionDimension> getDimensionList() {
        String sql = "SELECT qd.`DIMENSION_ID`,qd.`DIMENSION_NAME` FROM question_dimension qd ";
        return this.jdbcTemplate.query(sql, new DimensionRowMapper());
    }

    @Override
    public List<QuestionTrait> getTraitList() {
        String sql = "SELECT qt.`TRAIT_ID`,qt.`TRAIT_NAME` FROM question_trait qt;";
        return this.jdbcTemplate.query(sql, new TraitRowMapper());
    }

    @Override
    public List<QuestionAspect> getAspectList() {
        String sql = "SELECT qa.`ASPECT_ID`,qa.`ASPECT_NAME` FROM question_aspect qa;";
        return this.jdbcTemplate.query(sql, new AspectRowMapper());
    }

}
