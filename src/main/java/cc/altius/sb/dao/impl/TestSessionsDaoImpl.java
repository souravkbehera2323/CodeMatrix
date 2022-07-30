/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.matrixCodeVerse.model.DarkFactorCalculatorDTO;
import cc.altius.matrixCodeVerse.model.NextQuestionResultSetExtractor;
import cc.altius.matrixCodeVerse.model.PopulationStatsWithId;
import cc.altius.matrixCodeVerse.model.PopulationStatsWithIdRowMapper;
import cc.altius.matrixCodeVerse.model.Question;
import cc.altius.matrixCodeVerse.model.PopulationStatsGroup;
import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.matrixCodeVerse.model.TestSessionResultSetExtractor;
import cc.altius.matrixCodeVerse.model.TestSessionWithQuestionList;
import cc.altius.maven.AllAltiusUtil;
import cc.altius.sb.dao.TestSessionsDao;
import cc.altius.sb.model.TestSessions;
import cc.altius.sb.model.rowmapper.TestSessionForAllQuestionResultSetExtractor;
import cc.altius.sb.model.rowmapper.TestSessionsRowMapper;
import cc.altius.sb.utils.LogUtils;
//import cc.altius.utils.DateUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author altius
 */
@Repository
public class TestSessionsDaoImpl implements TestSessionsDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //take perticular taker data using lastName and EmailId
    @Override
    public TestSession getTestSessionData(String lastName, String emailId) {
        TestSession testSession = null;
        String sql = " SELECT  t.`TAKER_ID`, t.`EMAIL`, t.`FIRST_NAME`, t.`LAST_NAME`,  tt.`TEST_TYPE_ID`, tt.`TEST_TYPE_DESC`,  ts.`TEST_SESSION_ID`, ts.`TEST_STARTED`, ts.`TEST_ENDED` "
                + " , ts.`LAST_SORT_ORDER`,  q.`QUESTION_ID`, q.`SORT_ORDER`, q.`QUESTION_NAME`, q.`QUESTION_TYPE_ID`, q.`DIMENSION_ID`, q.`TRAIT_ID`, q.`ASPECT_ID`"
                + " ,  qr.`QUESTION_RESPONSE_ID`, qr.`QUESTION_OPTION_ID`,IF(qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\+)?([0-9]+\\\\.[0-9]*|[0-9]*\\\\.[0-9]+|[0-9]+)$',qr.`QUESTION_OPTION_VALUE`,0) `QUESTION_OPTION_VALUE`"
                + " ,t.`IS_MEDICAL_PROFESSIONAL` "
                + " FROM taker t "
                + "  LEFT JOIN test_session ts ON ts.`TAKER_ID`=t.`TAKER_ID` "
                + "  LEFT JOIN question q ON  ts.`LAST_SORT_ORDER`>=q.`SORT_ORDER` "
                + "  LEFT JOIN test_type tt ON q.`TEST_TYPE_ID`=tt.`TEST_TYPE_ID` "
                + "  LEFT JOIN question_response qr ON ts.`TEST_SESSION_ID`=qr.`TEST_SESSION_ID` AND qr.`QUESTION_ID`=q.`QUESTION_ID` "
                + "  WHERE t.`LAST_NAME`=? AND t.`EMAIL`=?  "
                + "  ORDER BY q.`SORT_ORDER`";
        testSession = this.jdbcTemplate.query(sql, new TestSessionResultSetExtractor(), lastName, emailId);
        return testSession;
    }
//Showing Population Normal Distribution using questionId And TestSessionId

    @Override
    public PopulationStatsGroup getPopulationStatsForQuestionFromLiveData(int questionId, int testSessionId, String type) {
        NamedParameterJdbcTemplate nm = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> params = new HashMap<>();
        params.put("questionId", questionId);
        params.put("testSessionId", testSessionId);
        PopulationStatsGroup stats = new PopulationStatsGroup();
        int maxValue;
        String sql1 = "SELECT MAX(qo.`QUESTION_VALUE`) `MAX_VALUE`,MIN(qo.`QUESTION_VALUE`) `MIN_VALUE` FROM question q LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` WHERE q.`QUESTION_ID`=? ";
        Map<String, Object> Mapvalue = this.jdbcTemplate.queryForMap(sql1, questionId);
        if (Mapvalue.get("MIN_VALUE").equals("0")) {
            maxValue = Integer.parseInt(Mapvalue.get("MAX_VALUE").toString());
        } else {
            maxValue = Integer.parseInt(Mapvalue.get("MAX_VALUE").toString()) + 1;
        }
        String sql = "SELECT `ID`, SUM(`NO_OF_QUESTIONS`) `NO_OF_QUESTIONS`, SUM(`SUM_OF_VALUE`) `TOTAL_SCORE`, AVG(`MEAN_OF_VALUE`) `MEAN`, STDDEV_SAMP(`MEAN_OF_VALUE`) `STDDEV` FROM ("
                + "SELECT "
                + "	qr.`TEST_SESSION_ID`, "
                + "	MAX(q.`QUESTION_ID`) `ID`, "
                + "	COUNT(IF(q.`IS_REVERSE_KEY`," + maxValue + "-qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_VALUE`)) `NO_OF_QUESTIONS`, "
                + "	SUM(IF(q.`IS_REVERSE_KEY`," + maxValue + "-qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_VALUE`)) `SUM_OF_VALUE`, "
                + "	AVG(IF(q.`IS_REVERSE_KEY`," + maxValue + "-qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_VALUE`)) `MEAN_OF_VALUE` "
                + "     FROM question_response qr "
                + "     LEFT JOIN test_session ts ON qr.`TEST_SESSION_ID`=ts.`TEST_SESSION_ID` "
                + "     LEFT JOIN question q ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
                + "     WHERE "
                + "	q.`QUESTION_ID`=:questionId "
                + "	AND qr.`TEST_SESSION_ID`!=:testSessionId "
                + "	AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                + "	AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\+)?([0-9]+\\\\.[0-9]*|[0-9]*\\\\.[0-9]+|[0-9]+)$' "
                + "	AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 10 "
                + "	AND ts.`TEST_ENDED` IS NOT NULL "
                + "     AND q.`QUESTION_TYPE_ID`=1 "
                + "     AND (SELECT t.TAKER_ID FROM taker t WHERE ts.`TAKER_ID`=t.TAKER_ID AND t.`ACTIVE`=1)   "
                + "     GROUP BY qr.`TEST_SESSION_ID`) s1";
        List<PopulationStatsWithId> questionStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
        stats.setStats(PopulationStatsGroup.QUESTION_STATS, questionStats);
//        sql = "SELECT `ID`, SUM(`NO_OF_QUESTIONS`) `NO_OF_QUESTIONS`, SUM(`SUM_OF_VALUE`) `TOTAL_SCORE`, AVG(`MEAN_OF_VALUE`) `MEAN`, STDDEV_SAMP(`MEAN_OF_VALUE`) `STDDEV` FROM ("
//                + "SELECT "
//                + "	qr.`TEST_SESSION_ID`, "
//                + "	MAX(q.`DIMENSION_ID`) `ID`, "
//                + "	COUNT(qr.`QUESTION_OPTION_VALUE`) `NO_OF_QUESTIONS`, "
//                + "	SUM(qr.`QUESTION_OPTION_VALUE`) `SUM_OF_VALUE`, "
//                + "	AVG(qr.`QUESTION_OPTION_VALUE`) `MEAN_OF_VALUE` "
//                + "FROM question_response qr "
//                + "LEFT JOIN test_session ts ON qr.`TEST_SESSION_ID`=ts.`TEST_SESSION_ID` "
//                + "LEFT JOIN question q ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
//                + "WHERE "
//                + "	q.`DIMENSION_ID`=(SELECT q1.`DIMENSION_ID`FROM question q1 WHERE q1.`QUESTION_ID`=:questionId) "
//                + "	AND q.`SORT_ORDER`<= (SELECT q2.`SORT_ORDER` FROM question q2 WHERE q2.`QUESTION_ID`=:questionId) "
//                + "	AND qr.`TEST_SESSION_ID`!=:testSessionId "
//                + "	AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
//                + "	AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\+)?([0-9]+\\\\.[0-9]*|[0-9]*\\\\.[0-9]+|[0-9]+)$' "
//                + "	AND qr.`QUESTION_OPTION_VALUE` BETWEEN 1 AND 5 "
//                + "	AND ts.`TEST_ENDED` IS NOT NULL "
//                + "	AND ts.`TEST_TYPE_ID`=(SELECT ts1.`TEST_TYPE_ID` FROM test_session ts1 WHERE ts1.`TEST_SESSION_ID`=:testSessionId) "
//                + " GROUP BY qr.`TEST_SESSION_ID`) s1";
//        List<PopulationStatsWithId> dimensionStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
//        stats.setStats(PopulationStatsGroup.DIMENSION_STATS, dimensionStats);
        sql = "SELECT `ID`, SUM(`NO_OF_QUESTIONS`) `NO_OF_QUESTIONS`, SUM(`SUM_OF_VALUE`) `TOTAL_SCORE`, AVG(`MEAN_OF_VALUE`) `MEAN`, STDDEV_SAMP(`MEAN_OF_VALUE`) `STDDEV` FROM ("
                + "SELECT "
                + "	qr.`TEST_SESSION_ID`, "
                + "	MAX(q.`TRAIT_ID`) `ID`, "
                + "	COUNT(IF(q.`IS_REVERSE_KEY`," + maxValue + "-qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_VALUE`)) `NO_OF_QUESTIONS`, "
                + "	SUM(IF(q.`IS_REVERSE_KEY`," + maxValue + "-qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_VALUE`)) `SUM_OF_VALUE`, "
                + "	AVG(IF(q.`IS_REVERSE_KEY`," + maxValue + "-qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_VALUE`)) `MEAN_OF_VALUE` "
                + "FROM question_response qr "
                + "LEFT JOIN test_session ts ON qr.`TEST_SESSION_ID`=ts.`TEST_SESSION_ID` "
                + "LEFT JOIN question q ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
                + "WHERE "
                + "	q.`TRAIT_ID`=(SELECT q1.`TRAIT_ID`FROM question q1 WHERE q1.`QUESTION_ID`=:questionId) "
                + "	AND q.`SORT_ORDER`<= (SELECT q2.`SORT_ORDER` FROM question q2 WHERE q2.`QUESTION_ID`=:questionId) "
                + "	AND qr.`TEST_SESSION_ID`!=:testSessionId "
                + "	AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                + "	AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\+)?([0-9]+\\\\.[0-9]*|[0-9]*\\\\.[0-9]+|[0-9]+)$' "
                + "	AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 10 "
                + "	AND ts.`TEST_ENDED` IS NOT NULL "
                + "     AND q.`QUESTION_TYPE_ID`=1 "
                + "     AND (SELECT t.TAKER_ID FROM taker t WHERE ts.`TAKER_ID`=t.TAKER_ID AND t.`ACTIVE`=1)   "
                + "GROUP BY qr.`TEST_SESSION_ID`) s1";
        List<PopulationStatsWithId> traitStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());

        stats.setStats(PopulationStatsGroup.TRAIT_STATS, traitStats);
        sql = "SELECT `ID`, SUM(`NO_OF_QUESTIONS`) `NO_OF_QUESTIONS`, SUM(`SUM_OF_VALUE`) `TOTAL_SCORE`, AVG(`MEAN_OF_VALUE`) `MEAN`, STDDEV_SAMP(`MEAN_OF_VALUE`) `STDDEV` FROM ("
                + "SELECT "
                + "	qr.`TEST_SESSION_ID`, "
                + "	MAX(q.`ASPECT_ID`) `ID`, "
                + "	COUNT(IF(q.`IS_REVERSE_KEY`," + maxValue + "-qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_VALUE`)) `NO_OF_QUESTIONS`, "
                + "	SUM(IF(q.`IS_REVERSE_KEY`," + maxValue + "-qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_VALUE`)) `SUM_OF_VALUE`, "
                + "	AVG(IF(q.`IS_REVERSE_KEY`," + maxValue + "-qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_VALUE`)) `MEAN_OF_VALUE` "
                + "FROM question_response qr "
                + "LEFT JOIN test_session ts ON qr.`TEST_SESSION_ID`=ts.`TEST_SESSION_ID` "
                + "LEFT JOIN question q ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
                + "WHERE "
                + "	q.`ASPECT_ID`=(SELECT q1.`ASPECT_ID`FROM question q1 WHERE q1.`QUESTION_ID`=:questionId) "
                + "	AND q.`SORT_ORDER`<= (SELECT q2.`SORT_ORDER` FROM question q2 WHERE q2.`QUESTION_ID`=:questionId) "
                + "	AND qr.`TEST_SESSION_ID`!=:testSessionId "
                + "	AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                + "	AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\+)?([0-9]+\\\\.[0-9]*|[0-9]*\\\\.[0-9]+|[0-9]+)$' "
                + "	AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 10 "
                + "	AND ts.`TEST_ENDED` IS NOT NULL "
                + "	AND q.`QUESTION_TYPE_ID`=1 "
                + "     AND (SELECT t.TAKER_ID FROM taker t WHERE ts.`TAKER_ID`=t.TAKER_ID AND t.`ACTIVE`=1)   "
                + " GROUP BY qr.`TEST_SESSION_ID`) s1";
        List<PopulationStatsWithId> aspectStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
        stats.setStats(PopulationStatsGroup.ASPECT_STATS, aspectStats);
        return stats;
    }

    @Override
    public Question getNextQuestion(int testSessionId) {
        Question question = null;
        String sql = "SELECT q.`QUESTION_ID`, q.`QUESTION_NAME`, q.`QUESTION_TYPE_ID`, q.`DIMENSION_ID`, q.`TRAIT_ID`, q.`ASPECT_ID`, q.`SORT_ORDER`, "
                + " q.`IS_REVERSE_KEY`,qo.`QUESTION_OPTION_ID`, qo.`QUESTION_OPTION_NAME`, qo.`QUESTION_VALUE`, q.`DESCRIPTION`, q.`DESCIPTION_TOGGLE` "
                + " FROM taker t LEFT JOIN test_session ts ON ts.`TAKER_ID`=t.`TAKER_ID` "
                + "  LEFT JOIN question q ON (SELECT `SORT_ORDER` FROM `question` WHERE `SORT_ORDER`>ts.`LAST_SORT_ORDER` AND ACTIVE='1'  "
                + "  ORDER BY SORT_ORDER LIMIT 1)=q.`SORT_ORDER`  "
                + " LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID`  "
                + " LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID`  "
                + " WHERE ts.`TEST_SESSION_ID`=? ORDER BY qo.`SORT_ORDER`";
        question = this.jdbcTemplate.query(sql, new NextQuestionResultSetExtractor(), testSessionId);
        return question;
    }

    @Override
    @Transactional
    public int saveQuestionResponses(TestSession testSession, Question currentQuestion, String[] selectedOptions, String[] selectedOptionValues) {
        SimpleJdbcInsert si = new SimpleJdbcInsert(jdbcTemplate).withTableName("question_response");
        Map<String, Object> params = new HashMap<>();
        Date curDate = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST); // To change to EST
        int rowsEffected = 0;
        for (int x = 0; x < selectedOptions.length; x++) {
            params.put("TAKER_ID", testSession.getTaker().getTakerId()); // Remove 
            params.put("QUESTION_ID", currentQuestion.getQuestionId());
            if (testSession.getTestType().getTestTypeId() != 0) {
                params.put("TEST_TYPE_ID", testSession.getTestType().getTestTypeId()); // Remove
            } else {
                params.put("TEST_TYPE_ID", 1); // Remove 
            }
            params.put("TEST_SESSION_ID", testSession.getTestSessionId());
            switch (currentQuestion.getQuestionTypeId()) {
                //  QUESTION_TYPE_ID_RADIO
                case 1:
                    params.put("QUESTION_OPTION_ID", selectedOptions[x]);
                    params.put("QUESTION_OPTION_VALUE", selectedOptionValues[x]);
                    break;

                //  QUESTION_TYPE_ID_ORDERED_GROUP
                case 4:

                //  QUESTION_TYPE_ID_MULTISELECT
                case 5:
                    params.put("QUESTION_OPTION_ID", selectedOptions[x]);
                    params.put("QUESTION_OPTION_VALUE", selectedOptionValues[x]);
                    break;

                //  QUESTION_TYPE_ID_TEXT_FIELD
                case 2:
                    params.put("QUESTION_OPTION_ADDITIONAL_VALUE", selectedOptionValues[x]);
                    break;

                //  QUESTION_TYPE_ID_TEXT_AREA
                case 3:
                    params.put("QUESTION_OPTION_ADDITIONAL_VALUE", selectedOptionValues[x]);
                    break;

                //  QUESTION_TYPE_ID_NUMBER    
                case 6:
                    params.put("QUESTION_OPTION_VALUE", selectedOptionValues[x]);
                    break;
                default:
                    break;
            }
            params.put("STARTED_QUESTION", testSession.getQuestionStartedTime()); // ToChange
            params.put("ENDED_QUESTION", testSession.getQuestionEndedTime()); // ToChange
            params.put("CREATED_DATE", curDate);
            params.put("LAST_MODIFIED_DATE", curDate);
            rowsEffected += si.execute(params);
        }
        String sql = "UPDATE test_session SET LAST_SORT_ORDER=?, LAST_MODIFIED_DATE=? WHERE TEST_SESSION_ID=?";
        this.jdbcTemplate.update(sql, currentQuestion.getSortOrder(), curDate, testSession.getTestSessionId());
        return rowsEffected;
    }

    @Override

    public int addQuestionResponses(int takerId, int testSessionId, int questionId, int testId, String outputData, String questionValue, String createdDate, String modifiedDate, long startedQuestion, long endedQuestion, String outputType) {
        SimpleJdbcInsert userInsert = new SimpleJdbcInsert(this.dataSource).withTableName("question_response").usingGeneratedKeyColumns("QUESTION_RESPONSE_ID");
        Map<String, Object> params = new HashMap<>();
        params.put("TAKER_ID", takerId);
        params.put("TEST_SESSION_ID", testSessionId);
        params.put("QUESTION_ID", questionId);
        params.put("TEST_TYPE_ID", testId);
        if (outputType.equals("Radio")) {
            params.put("QUESTION_OPTION_ID", outputData);
            params.put("QUESTION_OPTION_ADDITIONAL_VALUE", null);
        } else if (outputType.equals("Multi-Select")) {
            params.put("QUESTION_OPTION_ID", outputData);
            params.put("QUESTION_OPTION_ADDITIONAL_VALUE", questionValue);
        } else if (outputType.equals("Ordered Group")) {
            params.put("QUESTION_OPTION_ID", outputData);
            params.put("QUESTION_OPTION_ADDITIONAL_VALUE", null);
        } else {
            params.put("QUESTION_OPTION_ID", null);
            params.put("QUESTION_OPTION_ADDITIONAL_VALUE", outputData);
        }
        params.put("QUESTION_OPTION_VALUE", questionValue);
        params.put("CREATED_DATE", createdDate);
        params.put("LAST_MODIFIED_DATE", modifiedDate);
        params.put("STARTED_QUESTION", startedQuestion);
        params.put("ENDED_QUESTION", endedQuestion);
        int Id = userInsert.executeAndReturnKey(params).intValue();
        return Id;
    }

    @Override
    public int noOfQuestionAnswered(int testSessionId) {
        String sql = "SELECT "
                + "    IFNULL(COUNT(qr.QUESTION_RESPONSE_ID),0) questionCount "
                + "FROM test_session ts  "
                + "LEFT JOIN question_response qr ON ts.TAKER_ID=qr.TAKER_ID AND qr.TEST_SESSION_ID=ts.TEST_SESSION_ID "
                + "WHERE ts.TEST_SESSION_ID=?";

        int a = this.jdbcTemplate.queryForObject(sql, Integer.class, testSessionId);
        return a;
    }

    @Override
    public int getLastQuestionId() {
        String sql = "SELECT MAX(sort_order) FROM question;";
        int sortOrder = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return sortOrder;
    }

    @Override
    public int getDimensionIdBySortOrder(int sortOrder) {
        String sql = "SELECT q.`DIMENSION_ID` FROM question q WHERE q.`SORT_ORDER` = ? ";
        int a = this.jdbcTemplate.queryForObject(sql, Integer.class, sortOrder);
        return a;
    }

    @Override
    public double getQuestionOptionValueByQuestionOptionId(int questionId, String questionOptionId) {
        int maxValue;
        String sql1 = "SELECT MAX(qo.`QUESTION_VALUE`) `MAX_VALUE`,MIN(qo.`QUESTION_VALUE`) `MIN_VALUE` FROM question q LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` WHERE q.`QUESTION_ID`=? ";
        Map<String, Object> Mapvalue = this.jdbcTemplate.queryForMap(sql1, questionId);
        if (Mapvalue.get("MIN_VALUE").equals("0")) {
            maxValue = Integer.parseInt(Mapvalue.get("MAX_VALUE").toString());
        } else {
            maxValue = Integer.parseInt(Mapvalue.get("MAX_VALUE").toString()) + 1;
        }
        try {
            String sql = "SELECT IF(q.`IS_REVERSE_KEY`," + maxValue + "-qo.`QUESTION_VALUE`, qo.`QUESTION_VALUE`) `VALUE` FROM question q LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` WHERE q.`QUESTION_ID`=? AND qo.`QUESTION_OPTION_ID`=?";
            return this.jdbcTemplate.queryForObject(sql, Double.class, questionId, questionOptionId);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public Map<String, Object> getQuestionOptionValueByQuestionOptionId1(int questionId, String questionOptionId) {
        int maxValue;
        double score = 0.0;
        Map<String, Object> Mapvalue = new HashMap<>();
//        String sql1 = "SELECT MAX(qo.`QUESTION_VALUE`) `MAX_VALUE`,MIN(qo.`QUESTION_VALUE`) `MIN_VALUE` "
//                + "FROM question q "
//                + "LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` "
//                + " LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` "
//                + "WHERE q.`QUESTION_ID`=? ";
//        System.out.println("MaxValue---> "+sql1+"  "+questionId);
//        Map<String, Object> Mapvalue = this.jdbcTemplate.queryForMap(sql1, questionId);
//        if (Mapvalue.get("MIN_VALUE").equals("0")) {
//            maxValue = Integer.parseInt(Mapvalue.get("MAX_VALUE").toString());
//        } else {
//            maxValue = Integer.parseInt(Mapvalue.get("MAX_VALUE").toString()) + 1;
//        }
//        try {
//            String sql = "SELECT IF(q.`IS_REVERSE_KEY`," + maxValue + "-qo.`QUESTION_VALUE`, qo.`QUESTION_VALUE`) `VALUE` "
//                    + "FROM question q "
//                    + " LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID`"
//                    + " LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` "
//                    + "WHERE q.`QUESTION_ID`=? AND qo.`QUESTION_OPTION_ID`=?";
//            System.out.println("ReverseKey Query---> "+sql+"   "+questionId+"  "+questionOptionId);
//            score = this.jdbcTemplate.queryForObject(sql, Double.class, questionId, questionOptionId);
        try {
            String sql = " SELECT "
                    + "IF(q.`IS_REVERSE_KEY`,IF(MaxValue1.MIN_VALUE=0,MaxValue1.MAX_VALUE,MaxValue1.MAX_VALUE+1)-qo.`QUESTION_VALUE`, "
                    + "qo.`QUESTION_VALUE`) `score`,IF(MaxValue1.MIN_VALUE=0,MaxValue1.MAX_VALUE,MaxValue1.MAX_VALUE+1) `maxValue` "
                    + "FROM (SELECT MAX(qo.`QUESTION_VALUE`) `MAX_VALUE`,MIN(qo.`QUESTION_VALUE`) `MIN_VALUE`,q.`QUESTION_ID` "
                    + "FROM question q LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` "
                    + " LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` "
                    + " WHERE q.`QUESTION_ID`=?)AS MaxValue1 "
                    + " LEFT JOIN question q ON q.`QUESTION_ID`=MaxValue1.`QUESTION_ID` "
                    + "LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` "
                    + "LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID`"
                    + "WHERE q.`QUESTION_ID`=? AND qo.`QUESTION_OPTION_ID`=? ";
            Mapvalue = this.jdbcTemplate.queryForMap(sql, questionId, questionId, questionOptionId);
//            Mapvalue = this.jdbcTemplate.queryForMap("CALL getQuestionOptionValueByQuestionOptionId(?,?)", questionId, questionOptionId);
            Mapvalue.put("score", Mapvalue.get("score"));
            Mapvalue.put("maxValue", Mapvalue.get("maxValue"));
            return Mapvalue;
        } catch (Exception e) {
            e.printStackTrace();
            Mapvalue.put("score", "0.0");
            Mapvalue.put("maxValue", 0);
            return Mapvalue;
        }
    }

    @Override
    public int getTraitIdByQuestionId(int sortOrder) {
        String sql = "SELECT q.`TRAIT_ID` FROM question q WHERE q.`SORT_ORDER` = ? ";
        return this.jdbcTemplate.queryForObject(sql, Integer.class, sortOrder);
    }

    @Override
    public int getAspectIdByQuestionId(int sortOrder) {
        String sql = "SELECT q.`ASPECT_ID` FROM question q WHERE q.`SORT_ORDER` = ? ";
        return this.jdbcTemplate.queryForObject(sql, Integer.class, sortOrder);
    }

    @Override
    public int getCountForAspect() {
        String sql = "SELECT COUNT(*) FROM question_aspect qa ;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int getCountForDimension() {
        String sql = "SELECT COUNT(*) FROM question_dimension qd ;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int getCountForTrait() {
        String sql = "SELECT COUNT(*) FROM question_trait qt ;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<TestSessions> getPrivateDossierPdf(int takerId) {
        String sql = "SELECT t.`TAKER_ID`,t.`FIRST_NAME`,t.`LAST_NAME`,ts.TEST_SESSION_ID,ts.`TEST_STARTED`,ts.`TEST_ENDED`,ts.`LAST_SORT_ORDER`,qd.`DIMENSION_ID`,qd.`DIMENSION_NAME`, "
                + "`question`.`QUESTION_NAME`,`question`.`QUESTION_ID`,qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_ADDITIONAL_VALUE`,qt.`QUESTION_TYPE_NAME` ,qt.`QUESTION_TYPE_ID` ,`question`.`SORT_ORDER`,qo.`QUESTION_OPTION_ID` ,qtr.`TRAIT_ID`,qtr.`TRAIT_NAME`,qa.`ASPECT_NAME`,qo.`SORT_ORDER` AS qoSortOrder ,qo.`QUESTION_OPTION_NAME`  "
                + "FROM question LEFT JOIN question_response qr ON qr.`QUESTION_ID`= question.`QUESTION_ID` "
                + "LEFT JOIN question_dimension qd ON qd.`DIMENSION_ID`=question.`DIMENSION_ID` "
                + "LEFT JOIN question_option qo ON qo.`QUESTION_OPTION_ID`=qr.`QUESTION_OPTION_ID`  "
                + "LEFT JOIN taker t ON t.`TAKER_ID`=qr.`TAKER_ID` "
                + "LEFT JOIN test_session ts ON ts.`TEST_SESSION_ID`=qr.`TEST_SESSION_ID` "
                + "LEFT JOIN  question_type qt ON qt.`QUESTION_TYPE_ID`=`question`.`QUESTION_TYPE_ID` "
                + "LEFT JOIN `question_trait`  qtr  ON qtr.`TRAIT_ID`=question.`TRAIT_ID` "
                + "LEFT JOIN `question_aspect` qa  ON qa.`ASPECT_ID`=question.`ASPECT_ID`"
                + "WHERE t.`TAKER_ID`=? AND qtr.`TRAIT_ID`<>'6' AND qtr.`TRAIT_ID`<>'7'  "
                //                + "ORDER BY qd.`DIMENSION_ID`";
                + " ORDER BY question.`QUESTION_ID`";
        return this.jdbcTemplate.query(sql, new TestSessionsRowMapper(), takerId);
    }

    @Override
    public List<TestSessions> getExtremismDimensionData(int takerId) {
        String sql = "SELECT t.`TAKER_ID`,t.`FIRST_NAME`,t.`LAST_NAME`,ts.TEST_SESSION_ID,ts.`TEST_STARTED`,ts.`TEST_ENDED`,ts.`LAST_SORT_ORDER`,qd.`DIMENSION_ID`,qd.`DIMENSION_NAME`, "
                + "`q`.`QUESTION_NAME`,`q`.`QUESTION_ID`,qr.`QUESTION_OPTION_VALUE`,qr.`QUESTION_OPTION_ADDITIONAL_VALUE`,qt.`QUESTION_TYPE_NAME`,qt.`QUESTION_TYPE_ID` ,`q`.`SORT_ORDER`,qo.`QUESTION_OPTION_ID` ,qtr.`TRAIT_ID`,qtr.`TRAIT_NAME`,qa.`ASPECT_NAME`,qo.`SORT_ORDER` AS qoSortOrder,qo.`QUESTION_OPTION_NAME` "
                + "FROM question_response qr   "
                + "LEFT JOIN question q ON q.`QUESTION_ID`=qr.`QUESTION_ID`  "
                + "LEFT JOIN question_dimension qd ON qd.`DIMENSION_ID`=q.`DIMENSION_ID`  "
                + "LEFT JOIN question_option qo ON qo.`QUESTION_OPTION_ID`=qr.`QUESTION_OPTION_ID`  "
                + "LEFT JOIN taker t ON t.`TAKER_ID`=qr.`TAKER_ID`  "
                + "LEFT JOIN test_session ts ON ts.`TEST_SESSION_ID`=qr.`TEST_SESSION_ID`  "
                + "LEFT JOIN question_type qt ON qt.`QUESTION_TYPE_ID`=q.`QUESTION_TYPE_ID`  "
                + "LEFT JOIN `question_trait`  qtr ON qtr.`TRAIT_ID`=q.`TRAIT_ID` "
                + "LEFT JOIN `question_aspect` qa ON qa.`ASPECT_ID`=q.`ASPECT_ID`"
                + "WHERE t.`TAKER_ID`=? AND q.`TRAIT_ID` IN (1,2,3,4,5)   AND qd.`DIMENSION_ID`=2 ORDER BY qtr.`TRAIT_NAME`";
        return this.jdbcTemplate.query(sql, new TestSessionsRowMapper(), takerId);
    }

    @Override
    public List<Map<String, Object>> getCognitionDimensionData(int takerId, int questionId) {
        String sql = "SELECT  qo.QUESTION_OPTION_ID FROM `question_response` qr "
                + " LEFT JOIN `question`  q ON q.`QUESTION_ID`=qr.`QUESTION_ID` "
                + " LEFT JOIN question_option qo ON qo.`QUESTION_OPTION_ID`=qr.`QUESTION_OPTION_ID` "
                + "WHERE qr.`TAKER_ID`=? AND q.QUESTION_TYPE_ID='4' AND q.QUESTION_ID=? GROUP BY qr.`QUESTION_OPTION_ID`  ";
        return this.jdbcTemplate.queryForList(sql, takerId, questionId);

    }

    @Override
    public List<Map<String, Object>> getQuestionOptionList(int takerId, int questionId) {
        String sql = "SELECT  qr.QUESTION_OPTION_ID FROM `question_response` qr "
                + " LEFT JOIN `question`  q ON q.`QUESTION_ID`=qr.`QUESTION_ID` "
                + "WHERE qr.`TAKER_ID`=? AND q.question_type_id='4' AND q.QUESTION_ID=? ";
        return this.jdbcTemplate.queryForList(sql, takerId, questionId);
    }

    @Override
    public List<Map<String, Object>> getBehaviourQuestion3(int testSessionId) {
        try {
            String sql = "SELECT * FROM `question_response`  qr LEFT JOIN question q ON q.`QUESTION_ID` = qr.`QUESTION_ID`  WHERE qr.`TEST_SESSION_ID` = ? AND q.`QUESTION_ID` = '43' ";
            return this.jdbcTemplate.queryForList(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Map<String, Object> getBehaviourQuestion2(int testSessionId) {
        try {
            String sql = "SELECT * FROM `question_response`  qr LEFT JOIN question q ON q.`QUESTION_ID` = qr.`QUESTION_ID`  WHERE qr.`TEST_SESSION_ID` = ? AND q.`QUESTION_ID` = '42' ";
            return this.jdbcTemplate.queryForMap(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String getBehaviourMasterySum(int testSessionId) {
        try {
            String sql = "SELECT SUM(qr.question_option_value) FROM `question_response`  qr LEFT JOIN question  q ON q.`QUESTION_ID`= qr.`QUESTION_ID`  WHERE q.`TRAIT_ID` = '25' AND q.`TEST_TYPE_ID` = '6' AND qr.`TEST_SESSION_ID` = ?";
            return this.jdbcTemplate.queryForObject(sql, String.class, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getBehaviourQuestion1(int testSessionId) {
        try {
            //qr.`QUESTION_OPTION_VALUE`,
            String sql = "SELECT * FROM  question  q  LEFT JOIN `question_response` qr  "
                    + "ON  q.`QUESTION_ID`=qr.`QUESTION_ID` LEFT JOIN `question_option` qo ON qo.`QUESTION_OPTION_ID`=qr.`QUESTION_OPTION_ID` WHERE qr.`TEST_SESSION_ID` = ? AND qr.`QUESTION_ID` = '40'";
            return this.jdbcTemplate.queryForList(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String getBehaviouMindLikertSum(int testSessionId) {
        try {
            String sql = "SELECT SUM(qr.`QUESTION_OPTION_VALUE`) FROM `question_response` qr "
                    + "LEFT JOIN question q ON q.`QUESTION_ID` = qr.`QUESTION_ID` "
                    + "WHERE q.`TEST_TYPE_ID` = 6 AND q.`TRAIT_ID` = '22' AND qr.`TEST_SESSION_ID` = ?  ";
            return this.jdbcTemplate.queryForObject(sql, String.class, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Map<String, Object> getBehaviourQuestion4(int testSessionId) {
        try {
            String sql = "SELECT * FROM `question_response`  qr LEFT JOIN question q ON q.`QUESTION_ID` = qr.`QUESTION_ID`  WHERE qr.`TEST_SESSION_ID` = ? AND q.`QUESTION_ID` = '44' ";
            return this.jdbcTemplate.queryForMap(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String getBhaviourAbstinenceSum(int testSessionId) {
        try {
            String sql = "SELECT SUM(qr.question_option_value) FROM `question_response` qr  LEFT JOIN question q ON q. QUESTION_ID=qr.QUESTION_ID  WHERE q.TRAIT_ID = '24' AND q.`TEST_TYPE_ID` = '6' AND qr.TEST_SESSION_ID = ? ";
            return this.jdbcTemplate.queryForObject(sql, String.class, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getMentalImprovementActivities_QL2(int testSessionId) {
        try {
            String sql = "SELECT qr.question_option_value FROM  `question_response`  qr "
                    + "  LEFT JOIN question  q ON q.question_id = qr.question_id  "
                    + "WHERE q.test_type_id = 6  AND q.trait_id IN ('22') AND test_session_id = ? AND q.question_id IN ('45','46','47','48','49','50','51','52','53','54','55','56','57')";
            return this.jdbcTemplate.queryForList(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Map<String, Object> getTendenciesTestPercForQList(int testSessionId) {
        try {
            String sql = "SELECT  ROUND(  SUM(  CASE   WHEN q.`uses_modifier_on_question` <> 1  "
                    + "  THEN qr.question_option_value WHEN q.`uses_modifier_on_question` <> 0  "
                    + "  THEN ABS( qr.`question_option_value` - q.`modifier_value` ) + qr.question_option_value  "
                    + "  ELSE 0  END ) / 47, 4) AS ratio, "
                    + "  ROUND(  SUM(  CASE  WHEN q.`uses_modifier_on_question` <> 1  THEN qr.question_option_value  "
                    + "  WHEN q.`uses_modifier_on_question` <> 0  THEN ABS(qr.`question_option_value` - q.`modifier_value` "
                    + "  ) + qr.question_option_value  ELSE 0 END ) / 47 * 100,  2  ) AS percent  "
                    + "FROM  `question_response` qr  LEFT JOIN question q ON q.question_id = qr.question_id  "
                    + "WHERE q.`TEST_TYPE_ID`= 2 AND q.`TRAIT_ID` IN ('28', '31') AND qr.test_session_id = ? ";
            return this.jdbcTemplate.queryForMap(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Map<String, Object> getTendenciesTestPercForUList(int testSessionId) {
        try {
            String sql = "  SELECT    "
                    + "  ROUND(SUM(CASE WHEN  q.`uses_modifier_on_question`<>1 THEN  qr.question_option_value   "
                    + "  WHEN  q.`uses_modifier_on_question`<>0  THEN  ABS(q.`modifier_value`-qr.question_option_value)   "
                    + "  ELSE 0  END)/45,4) AS ratio ,  ROUND(SUM(CASE WHEN  q.`uses_modifier_on_question`<>1 THEN  qr.question_option_value   "
                    + "  WHEN  q.`uses_modifier_on_question`<>0  THEN  ABS(q.`modifier_value`-qr.question_option_value)   "
                    + "  ELSE 0  END)/45*100,2) AS percent  FROM  `question_response`  qr   "
                    + "  LEFT JOIN question  q ON q.question_id = qr.question_id WHERE q.`TEST_TYPE_ID` = 2    "
                    + "  AND q.`TRAIT_ID` IN ('27', '31','33') AND qr.test_session_id = ? ";
            return this.jdbcTemplate.queryForMap(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Map<String, Object> getTendenciesTestPercForOList(int testSessionId) {
        try {
            String sql = " SELECT   "
                    + " ROUND((SUM(qr.question_option_value)/40),4) AS ratio, ROUND((SUM(qr.question_option_value)/40*100),2) AS  percentage  "
                    + "FROM  `question_response`  qr  LEFT JOIN question  q ON q.question_id = qr.question_id   "
                    + "WHERE q.`TEST_TYPE_ID` = 2 AND q.`TRAIT_ID` IN ('29', '32') AND qr.test_session_id = ?    ";
            return this.jdbcTemplate.queryForMap(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int getTendenciesTestPercForRList(int testSessionId) {
        try {
            String sql = " SELECT SUM(qr.question_option_value) AS total  FROM `question_response`  qr  "
                    + "  LEFT JOIN question  q  ON q.question_id = qr.question_id WHERE q.`TEST_TYPE_ID` = 2   "
                    + "  AND q.`TRAIT_ID` IN ('30', '31','33')  AND qr.test_session_id = ?   ";
            int a = this.jdbcTemplate.queryForObject(sql, Integer.class, testSessionId);
            return a;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getTendenciesTestPercForWithRList(int testSessionId) {
        try {
            String sql = " SELECT SUM(IF( q.`uses_modifier_on_question`<>0 ,  q.`modifier_value`-qr.question_option_value,0))  AS Total  "
                    + " FROM `question_response`  qr   LEFT JOIN question  q ON q.question_id = qr.question_id   "
                    + "WHERE q.`TEST_TYPE_ID` = 2   AND q.`TRAIT_ID` IN ('32')  AND qr.test_session_id = ?   ";
            int a = this.jdbcTemplate.queryForObject(sql, Integer.class, testSessionId);
            return a;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public List<Double> getPopAverageForAspect(int sortOrder) {
        String sql = "SELECT AVG(qr.`QUESTION_OPTION_VALUE`) as popAspectAverage FROM question q "
                + "LEFT JOIN question_response qr ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
                + "GROUP BY q.`SORT_ORDER` ";
        return this.jdbcTemplate.queryForList(sql, Double.class);
    }

    @Override
    public List<Double> getPopAspectScoreList(int sortOrder) {
        String sql = "SELECT qr.`QUESTION_OPTION_VALUE` "
                + "FROM question_response qr "
                + "LEFT JOIN question q ON q.`QUESTION_ID`=qr.`QUESTION_ID` WHERE q.`SORT_ORDER`=?  ";
        return this.jdbcTemplate.queryForList(sql, Double.class, sortOrder);
    }

    @Override
    public TestSessionWithQuestionList getTestSessionAllQueData(int takerId) {
        String sql = "SELECT   "
                + "    t.`TAKER_ID`, t.`EMAIL`,  t.`FIRST_NAME`,t.`LAST_NAME`,tt.`TEST_TYPE_ID`, tt.`TEST_TYPE_DESC`, "
                + "    ts.`TEST_SESSION_ID`,  ts.`TEST_STARTED`, ts.`TEST_ENDED`, ts.`LAST_SORT_ORDER`, "
                + "    q.`QUESTION_ID`,  q.`SORT_ORDER`,  q.`QUESTION_NAME`,  q.`QUESTION_TYPE_ID`, q.`IS_REVERSE_KEY`,"
                + "    q.`DIMENSION_ID`,   qd.`DIMENSION_NAME`, q.`TRAIT_ID`, qt.`TRAIT_NAME`, "
                + "    q.`ASPECT_ID`,  qa.`ASPECT_NAME`, qr.`QUESTION_RESPONSE_ID`,qr.`QUESTION_OPTION_ID`, "
                + "    IF(q.`IS_REVERSE_KEY`, IF(qmv.`MIN_VALUE`=0,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE`+1-qr.`QUESTION_OPTION_VALUE`), qr.`QUESTION_OPTION_VALUE`)  `QUESTION_OPTION_VALUE`, "
                + "    qr.`QUESTION_OPTION_ADDITIONAL_VALUE`  "
                + "FROM taker t   "
                + "    LEFT JOIN test_session ts   ON ts.`TAKER_ID` = t.`TAKER_ID`  "
                + "    LEFT JOIN question_response qr  ON ts.`TEST_SESSION_ID` = qr.`TEST_SESSION_ID`  "
                + "    LEFT JOIN test_type tt   ON qr.`TEST_TYPE_ID` = tt.`TEST_TYPE_ID`  "
                + "    LEFT JOIN question q   ON qr.`QUESTION_ID` = q.`QUESTION_ID`  "
                + "    LEFT JOIN (SELECT MAX(qo.`QUESTION_VALUE`) `MAX_VALUE`, MIN(qo.`QUESTION_VALUE`) `MIN_VALUE`, q.`QUESTION_ID` FROM question q LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` GROUP BY q.QUESTION_ID) qmv ON q.`QUESTION_ID`=qmv.`QUESTION_ID`"
                + "    LEFT JOIN question_dimension qd  ON q.`DIMENSION_ID` = qd.`DIMENSION_ID` "
                + "    LEFT JOIN question_trait qt  ON q.`TRAIT_ID` = qt.`TRAIT_ID` "
                + "    LEFT JOIN question_aspect qa  ON q.`ASPECT_ID` = qa.`ASPECT_ID`  "
                + "WHERE "
                + "    t.`TAKER_ID` = ?  "
                + "    AND  qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                + "    AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 10 "
                + "    AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\\\\\\\\\\\\\+)?([0-9]+\\\\\\\\\\\\\\\\.[0-9]*|[0-9]*\\\\\\\\\\\\\\\\.[0-9]+|[0-9]+)$' "
                + "    AND q.`QUESTION_TYPE_ID`=1 "
                + "ORDER BY q.`QUESTION_ID`";
        return this.jdbcTemplate.query(sql, new TestSessionForAllQuestionResultSetExtractor(), takerId);
    }

    @Override
    public List<Map<String, Object>> getSelfActualizationData(int testSessionId) {
        try {
            String sql = " SELECT   ROUND(  SUM(  ABS(  "
                    + "  qr.`QUESTION_OPTION_VALUE` - IF(q.`MODIFIER_VALUE` = 6, 6, 0)  "
                    + "   )  ) / 20 * 100, 2  ) AS AspectSum , SUM(  ABS(  "
                    + "   qr.`QUESTION_OPTION_VALUE` - IF(q.`MODIFIER_VALUE` = 6, 6, 0)  )  "
                    + "    ) TraitSum ,qt.`TRAIT_NAME`,qa.`ASPECT_NAME` FROM  `question_response`  qr  "
                    + "  LEFT JOIN question q   ON q.`QUESTION_ID` = qr.`QUESTION_ID`   "
                    + "  LEFT JOIN `question_trait` qt  ON q.`TRAIT_ID`= qt.`TRAIT_ID`  "
                    + "  LEFT JOIN `question_aspect` qa  ON q.`ASPECT_ID`= qa.`ASPECT_ID`  "
                    + "WHERE qr.test_session_id = ? AND qr.`TEST_TYPE_ID` = 3  AND q.`TRAIT_ID`  IN ('14' )  "
                    + "  AND q.`ASPECT_ID` IN  ('11','12','13','14')  GROUP BY  q.`ASPECT_ID`   "
                    + "  ORDER BY  q.`TRAIT_ID`";
            return this.jdbcTemplate.queryForList(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Map<String, Object> getSatisfactionWithLifeScale(int testSessionId) {
        try {
            String sql = "SELECT ROUND( SUM(  ABS(  qr.`QUESTION_OPTION_VALUE` - IF( "
                    + "  q.`USES_MODIFIER_ON_QUESTION` = 0, 0, q.`MODIFIER_VALUE`  ) "
                    + "  )   ) / 35 * 100 ,2)  AS aspectPercentile, (  SUM( "
                    + "  ABS( qr.`QUESTION_OPTION_VALUE` - IF( q.`USES_MODIFIER_ON_QUESTION` = 0, "
                    + "   0,  q.`MODIFIER_VALUE`  )  )  ) / 100 * 100  )  AS  compositeSWLSscore "
                    + "FROM `question_response` qr LEFT JOIN question q   ON qr.question_id = q.`QUESTION_ID`  "
                    + "WHERE q.`TEST_TYPE_ID` = 3  AND q.`TRAIT_ID` = '38' AND qr.`TEST_SESSION_ID` = ? ";
            return this.jdbcTemplate.queryForMap(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Map<String, Object> getLonelinessData(int testSessionId) {
        try {
            String sql = "SELECT  ROUND((  SUM(   ABS(  qr.QUESTION_OPTION_VALUE - IF(  "
                    + "   q.USES_MODIFIER_ON_QUESTION = 0,  0,  q.MODIFIER_VALUE  "
                    + "     )  )   ) / 36   ) * 100 ,2) AS lonelinessPercentile,ROUND((SUM(ABS(question_option_value - IF(uses_modifier_on_question=0,0,modifier_value)))/ 100)*100,2) AS compositeLonelinessScore  "
                    + "FROM  `question_response`  qr LEFT JOIN `question` q  "
                    + " ON qr.QUESTION_ID = q.QUESTION_ID  WHERE q.TEST_TYPE_ID = '3'  AND q.`TRAIT_ID`= '39'   "
                    + " AND q.`ASPECT_ID` = '22'  AND qr.`TEST_SESSION_ID` = ?  ";
            return this.jdbcTemplate.queryForMap(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getUCLAquestions(int testSessionId) {
        try {
            String sql = "SELECT ABS (question_option_value - IF(uses_modifier_on_question=0,0,modifier_value)) As result,q.`QUESTION_NAME` "
                    + "FROM  `question_response`  qr LEFT JOIN `question` q  ON qr.QUESTION_ID = q.QUESTION_ID   "
                    + "WHERE q.TEST_TYPE_ID = '3'   AND q.`TRAIT_ID`= '39'  AND q.`ASPECT_ID` = '22'   "
                    + "  AND qr.`TEST_SESSION_ID` = ?  GROUP BY q.`QUESTION_NAME`   ";
            return this.jdbcTemplate.queryForList(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getSelfRsesList(int testSessionId) {
        try {
            String sql = "SELECT  SUM(   ABS( qr.QUESTION_OPTION_VALUE - IF( "
                    + "  q.USES_MODIFIER_ON_QUESTION = 0, 0, q.MODIFIER_VALUE  )   )  ) As SelfRowResult ,q.`QUESTION_NAME` "
                    + "FROM `question_response`  qr LEFT JOIN question q   ON qr.QUESTION_ID = q.QUESTION_ID "
                    + "WHERE q.TEST_TYPE_ID = '3'  AND q.TRAIT_ID = '16'    AND q.ASPECT_ID  IN ( '16' ,'15') "
                    + " AND qr.TEST_SESSION_ID= ? GROUP BY q.ASPECT_ID ";
            return this.jdbcTemplate.queryForList(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getANSPTraitSum(int testSessionId, int is_reverse_key) {
        String sql = "SELECT q.`TRAIT_ID` ,SUM(qr.`QUESTION_OPTION_VALUE`) "
                + "FROM  `question_response`  qr  LEFT JOIN question   q "
                + " ON qr.`QUESTION_ID` = q.`QUESTION_ID` WHERE q.TRAIT_ID  IN ('46' ,'42','44','43','45','47','48','49') AND "
                + "   q.`IS_REVERSE_KEY` = ?  AND q.`TEST_TYPE_ID` = 5  AND qr.`TEST_SESSION_ID` = ?  "
                + "GROUP BY q.`TRAIT_ID`;";
        return this.jdbcTemplate.queryForList(sql, is_reverse_key, testSessionId);
    }

    @Override
    public List<DarkFactorCalculatorDTO> getDARKTraitSum(int testSessionId, int isReverseKey) {
        String sql = "SELECT q.`TRAIT_ID`,SUM(qr.`QUESTION_OPTION_VALUE`) As questionOptionValue "
                + " FROM `question_response` qr "
                + " LEFT JOIN question q ON qr.`QUESTION_ID` = q.`QUESTION_ID` "
                + " WHERE q.TRAIT_ID IN ('11','12','13','15','17','18','19','20','41')"
                + " AND q.`IS_REVERSE_KEY` = ?  AND q.`TEST_TYPE_ID` = 4  AND qr.`TEST_SESSION_ID` = ?   "
                + " GROUP BY q.`TRAIT_ID` ";
        return this.jdbcTemplate.query(sql, new RowMapper<DarkFactorCalculatorDTO>() {
            @Override
            public DarkFactorCalculatorDTO mapRow(ResultSet rs, int i) throws SQLException {
                DarkFactorCalculatorDTO dfc = new DarkFactorCalculatorDTO();
                dfc.setTakerId(rs.getInt("TRAIT_ID"));
                dfc.setQuestionOptionValue(rs.getDouble("questionOptionValue"));
                return dfc;
            }
        }, isReverseKey, testSessionId);
    }

    @Override
    public void updateTestSession(int sortOrder, int testSessionId, int questionTypeId) {
        Date curDate = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST); // To change to EST
        String sql = "UPDATE test_session SET LAST_SORT_ORDER=?, LAST_MODIFIED_DATE=?,TEST_TYPE_ID=? WHERE TEST_SESSION_ID=?";
        this.jdbcTemplate.update(sql, sortOrder, curDate, testSessionId, questionTypeId);
    }

    @Override
    public void updateTestSessionForMedicalProferssion(int sortOrder, int testSessionId) {
        Date curDate = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST); // To change to EST
        String sql = "UPDATE test_session SET LAST_SORT_ORDER=? WHERE TEST_SESSION_ID=?";
        this.jdbcTemplate.update(sql, sortOrder, testSessionId);
    }

    @Override
    public String getQuestionOptionValueByQuestionOptionIdMultSelectQue(int questionId, String questionOptionId) {
        try {
            String sql = "SELECT qo.`QUESTION_VALUE` `VALUE` FROM question q LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` WHERE q.`QUESTION_ID`=? AND qo.`QUESTION_OPTION_ID`=?";
            return this.jdbcTemplate.queryForObject(sql, String.class, questionId, questionOptionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String getQuestionValueByQuestionOptionIdRadio(String questionOptionId) {
        try {
            String sql = "SELECT qo.`QUESTION_VALUE` `VALUE` FROM question_option qo WHERE  qo.`QUESTION_OPTION_ID`=?";
            return this.jdbcTemplate.queryForObject(sql, String.class, questionOptionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public TestSessionWithQuestionList getTestSessionAllCovidQueDataForResult(int userId, int dimensionId) {
        String sql = "SELECT   t.`TAKER_ID`, t.`EMAIL`,  t.`FIRST_NAME`,t.`LAST_NAME`,tt.`TEST_TYPE_ID`, tt.`TEST_TYPE_DESC`, "
                + "  ts.`TEST_SESSION_ID`,  ts.`TEST_STARTED`, ts.`TEST_ENDED`, ts.`LAST_SORT_ORDER`, "
                + "  q.`QUESTION_ID`,  q.`SORT_ORDER`,  q.`QUESTION_NAME`,  q.`QUESTION_TYPE_ID`, "
                + "  q.`DIMENSION_ID`,   qd.`DIMENSION_NAME`, q.`TRAIT_ID`, qt.`TRAIT_NAME`, "
                + "  q.`ASPECT_ID`,  qa.`ASPECT_NAME`, qr.`QUESTION_RESPONSE_ID`,qr.`QUESTION_OPTION_ID`, "
                + "  qr.`QUESTION_OPTION_VALUE`,  qr.`QUESTION_OPTION_ADDITIONAL_VALUE`  "
                + "FROM taker t   LEFT JOIN test_session ts   ON ts.`TAKER_ID` = t.`TAKER_ID`  "
                + "  LEFT JOIN question_response qr  ON ts.`TEST_SESSION_ID` = qr.`TEST_SESSION_ID`  "
                + "  LEFT JOIN test_type tt   ON qr.`TEST_TYPE_ID` = tt.`TEST_TYPE_ID`  "
                + "  LEFT JOIN question q   ON qr.`QUESTION_ID` = q.`QUESTION_ID`  "
                + " LEFT JOIN question_dimension qd  "
                + "  ON q.`DIMENSION_ID` = qd.`DIMENSION_ID` LEFT JOIN question_trait qt  "
                + "  ON q.`TRAIT_ID` = qt.`TRAIT_ID` LEFT JOIN question_aspect qa  ON q.`ASPECT_ID` = qa.`ASPECT_ID`  "
                + "WHERE t.`TAKER_ID` = ?  AND  qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                + "AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 100 "
                + "AND q.`QUESTION_TYPE_ID` IN (1,6) AND q.`DIMENSION_ID`=? ORDER BY q.`SORT_ORDER` ";
        return this.jdbcTemplate.query(sql, new TestSessionForAllQuestionResultSetExtractor(), userId, dimensionId);
    }

    @Override
    public String getLabelByNumberOfQuestions(int numberOfQuestions) {
        try {
            String sql = "SELECT q.`LABEL` FROM question_number_label_mapping q WHERE ? BETWEEN q.`MIN_VAL` AND q.`MAX_VAL`;";
            String a = this.jdbcTemplate.queryForObject(sql, String.class, numberOfQuestions);
            return a;
        } catch (EmptyResultDataAccessException e) {
            return "";
        }
    }

    @Override
    public int deleteLastSorder() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = dateFormat.format(AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST));
        try {
            String sql = "DELETE test_session,taker FROM test_session "
                    + "LEFT JOIN taker ON test_session.`TAKER_ID`=taker.`TAKER_ID` "
                    + "WHERE LAST_SORT_ORDER=0 AND TEST_STARTED< '" + curDate + "'";
            int rows = 0;
            rows = this.jdbcTemplate.update(sql);
            return rows;
        } catch (EmptyResultDataAccessException e) {
            return 1;
        }
    }

    @Override
    public TestSession getTestSessionDataByUserId(int userId) {
        String sql = " SELECT  t.`TAKER_ID`, t.`EMAIL`, t.`FIRST_NAME`, t.`LAST_NAME`,  tt.`TEST_TYPE_ID`, tt.`TEST_TYPE_DESC`,  ts.`TEST_SESSION_ID`, ts.`TEST_STARTED`, ts.`TEST_ENDED` , ts.`LAST_SORT_ORDER`,  q.`QUESTION_ID`, q.`SORT_ORDER`, q.`QUESTION_NAME`, q.`QUESTION_TYPE_ID`, q.`DIMENSION_ID`, q.`TRAIT_ID`, q.`ASPECT_ID`,  qr.`QUESTION_RESPONSE_ID`, qr.`QUESTION_OPTION_ID`,IF(qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\+)?([0-9]+\\\\.[0-9]*|[0-9]*\\\\.[0-9]+|[0-9]+)$',qr.`QUESTION_OPTION_VALUE`,0) `QUESTION_OPTION_VALUE`,t.`IS_MEDICAL_PROFESSIONAL` "
                + " FROM taker t "
                + "  LEFT JOIN test_session ts ON ts.`TAKER_ID`=t.`TAKER_ID` "
                + "  LEFT JOIN question q ON  ts.`LAST_SORT_ORDER`>=q.`SORT_ORDER` "
                + "  LEFT JOIN test_type tt ON q.`TEST_TYPE_ID`=tt.`TEST_TYPE_ID` "
                + "  LEFT JOIN question_response qr ON ts.`TEST_SESSION_ID`=qr.`TEST_SESSION_ID` AND qr.`QUESTION_ID`=q.`QUESTION_ID` "
                + "  WHERE t.`USER_ID`=? "
                + "  ORDER BY q.`SORT_ORDER`";
        return this.jdbcTemplate.query(sql, new TestSessionResultSetExtractor(), userId);
    }

    @Override
    public PopulationStatsGroup getPopulationStatsForCovidQuestion(int questionId, int testSessionId, String type) {
        NamedParameterJdbcTemplate nm = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> params = new HashMap<>();
        params.put("questionId", questionId);
        params.put("testSessionId", testSessionId);
        PopulationStatsGroup stats = new PopulationStatsGroup();
        String sql = "SELECT `ID`, SUM(`NO_OF_QUESTIONS`) `NO_OF_QUESTIONS`, SUM(`SUM_OF_VALUE`) `TOTAL_SCORE`, AVG(`MEAN_OF_VALUE`) `MEAN`, STDDEV_SAMP(`MEAN_OF_VALUE`) `STDDEV` FROM ("
                + "SELECT "
                + "	qr.`TEST_SESSION_ID`, "
                + "	MAX(q.`QUESTION_ID`) `ID`, "
                + "	COUNT(qr.`QUESTION_OPTION_VALUE`) `NO_OF_QUESTIONS`, "
                + "	SUM(qr.`QUESTION_OPTION_VALUE`) `SUM_OF_VALUE`, "
                + "	AVG(qr.`QUESTION_OPTION_VALUE`) `MEAN_OF_VALUE` "
                + "     FROM question_response qr "
                + "     LEFT JOIN test_session ts ON qr.`TEST_SESSION_ID`=ts.`TEST_SESSION_ID` "
                + "     LEFT JOIN question q ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
                + "     WHERE "
                + "	q.`QUESTION_ID`=:questionId "
                + "	AND qr.`TEST_SESSION_ID`!=:testSessionId "
                + "	AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                + "	AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\+)?([0-9]+\\\\.[0-9]*|[0-9]*\\\\.[0-9]+|[0-9]+)$' "
                + "	AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 100 "
                + "	AND ts.`TEST_ENDED` IS NOT NULL "
                + "     AND q.`QUESTION_TYPE_ID` IN (1,6) "
                + "     AND (SELECT t.TAKER_ID FROM taker t WHERE ts.`TAKER_ID`=t.TAKER_ID AND t.`ACTIVE`=1)   "
                + "     GROUP BY qr.`TEST_SESSION_ID`) s1";
        List<PopulationStatsWithId> questionStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
        stats.setStats(PopulationStatsGroup.QUESTION_STATS, questionStats);
        return stats;
    }

    @Override
    public int getLastQuestionIdWithoutCovidQue() {
        String sql = "SELECT COUNT(*) FROM `question` WHERE `DIMENSION_ID`!=6";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public String getDescByPercentile(double percentile, int parameterType, int parameterValue) {
        String sql = "SELECT REPLACE(GROUP_CONCAT(CONCAT('You are ',p.`SEVERITY_ADJ`,' ',t.`DESCRIPTION`,'.')),'.,','.  &nbsp')"
                + " `Description` FROM trait_desc_mapping t "
                + "LEFT JOIN percentile_severity_adj_mapping p ON FIND_IN_SET (p.`ADJ_MAPPING_ID`,t.`ADJ_MAPPING`)  "
                + "WHERE ? BETWEEN p.`MIN_PERCENTILE` AND p.`MAX_PERCENTILE`";
        if (parameterType == 1) {//For Trait
            sql += "AND t.`TRAIT_ID` =?";
        } else if (parameterType == 2) {//For aspect
            sql += "AND t.`ASPECT_ID` =?";
        }
        return this.jdbcTemplate.queryForObject(sql, String.class, percentile, parameterValue);
    }

    public List<Map<String, Object>> getValueHealthAndDevelopmentGraph(int testSessionId) {
        try {
            String sql = "SELECT qr.question_option_value FROM  `question_response`  qr "
                    + "  LEFT JOIN question  q ON q.question_id = qr.question_id  "
                    + "WHERE test_session_id = ? AND q.question_id IN ('6','7','8','9','10','11','12','13','14','15')";
            return this.jdbcTemplate.queryForList(sql, testSessionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Question getCurrentQuestionData(int testSessionId) {
        try {
            Question question = null;
            String sql = "SELECT q.`QUESTION_ID`, q.`QUESTION_NAME`, q.`QUESTION_TYPE_ID`, q.`DIMENSION_ID`, q.`TRAIT_ID`, q.`ASPECT_ID`, q.`SORT_ORDER`, q.`IS_REVERSE_KEY`,"
                    + "qo.`QUESTION_OPTION_ID`, qo.`QUESTION_OPTION_NAME`, qo.`QUESTION_VALUE`, q.`DESCRIPTION`, q.`DESCIPTION_TOGGLE` "
                    + "FROM taker t "
                    + "LEFT JOIN test_session ts ON ts.`TAKER_ID`=t.`TAKER_ID` "
                    + "LEFT JOIN question q ON ts.`LAST_SORT_ORDER`=q.`SORT_ORDER` "
                    + "LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` "
                    + "LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` "
                    + "WHERE ts.`TEST_SESSION_ID`=? ORDER BY qo.`SORT_ORDER`";
            question = this.jdbcTemplate.query(sql, new NextQuestionResultSetExtractor(), testSessionId);
            return question;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getAnpsTotalScore(int traitId, int takerId) {
        try {
            int totalScore = 0;
            if (traitId == 46 || traitId == 42 || traitId == 44 || traitId == 43 || traitId == 45 || traitId == 47) {
                String sql = " SELECT SUM(IF(q.`IS_REVERSE_KEY`=0,qr.QUESTION_OPTION_VALUE,0))+(21-SUM(IF(q.`IS_REVERSE_KEY`=1,qr.QUESTION_OPTION_VALUE,0))) AS TotalScore "
                        + " FROM `question` q "
                        + "LEFT JOIN `question_response` qr ON q.QUESTION_ID=qr.QUESTION_ID "
                        + "LEFT JOIN `question_trait` qt ON q.`TRAIT_ID`=qt.`TRAIT_ID` "
                        + " WHERE q.`TRAIT_ID`=? AND TAKER_ID=? GROUP BY q.`TRAIT_ID` ";
                totalScore = this.jdbcTemplate.queryForObject(sql, Integer.class, traitId, takerId);
            }
            if (traitId == 48) {
                String sql = " SELECT SUM(IF(q.`IS_REVERSE_KEY`=0 ,qr.QUESTION_OPTION_VALUE,0))+(18-SUM(IF(q.`IS_REVERSE_KEY`=1,qr.QUESTION_OPTION_VALUE,0))) AS TotalScore  "
                        + " FROM `question` q  "
                        + "LEFT JOIN `question_response` qr ON q.QUESTION_ID=qr.QUESTION_ID  "
                        + "LEFT JOIN `question_trait` qt ON q.`TRAIT_ID`=qt.`TRAIT_ID`  "
                        + " WHERE q.`TRAIT_ID`=? AND TAKER_ID=? AND q.QUESTION_ID NOT IN('418') GROUP BY q.`TRAIT_ID`  ";
                totalScore = this.jdbcTemplate.queryForObject(sql, Integer.class, traitId, takerId);
            }
            if (traitId == 49) {
                String sql = "SELECT SUM(IF(q.`IS_REVERSE_KEY`=0 ,qr.QUESTION_OPTION_VALUE,0))+(18-SUM(IF(q.`IS_REVERSE_KEY`=1,qr.QUESTION_OPTION_VALUE,0))) AS TotalScore   "
                        + " FROM `question` q   "
                        + "LEFT JOIN `question_response` qr ON q.QUESTION_ID=qr.QUESTION_ID   "
                        + "LEFT JOIN `question_trait` qt ON q.`TRAIT_ID`=qt.`TRAIT_ID`   "
                        + " WHERE  TAKER_ID=? AND q.QUESTION_ID IN('311','329','347','365','383','401','427','418','320','338','356','374','392','410')   ";
                totalScore = this.jdbcTemplate.queryForObject(sql, Integer.class, takerId);
            }
            return totalScore;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public PopulationStatsGroup getPopulationStatsForQuestionFromSavedData(int questionId, int testSessionId, String pdf) {
        try {
            NamedParameterJdbcTemplate nm = new NamedParameterJdbcTemplate(jdbcTemplate);
            Map<String, Object> params = new HashMap<>();
            String sql;
            params.put("questionId", questionId);
            params.put("testSessionId", testSessionId);
            PopulationStatsGroup stats = new PopulationStatsGroup();
            sql = "SELECT ps.ID AS ID ,ps.NO_OF_QUESTIONS,ps.TOTAL_SCORE,ps.MEAN,ps.STD_DEV AS STDDEV FROM population_stats ps where ps.POPULATION_STATS_GROUP=1 AND ps.QUESTION_ID=:questionId ORDER BY ps.ID ";
            List<PopulationStatsWithId> questionStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
            stats.setStats(PopulationStatsGroup.QUESTION_STATS, questionStats);
            sql = "SELECT ps.ID AS ID ,ps.NO_OF_QUESTIONS,ps.TOTAL_SCORE,ps.MEAN,ps.STD_DEV AS STDDEV FROM population_stats ps where ps.POPULATION_STATS_GROUP=2 AND ps.QUESTION_ID=:questionId ORDER BY ps.ID ";
            List<PopulationStatsWithId> dimensionStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
            stats.setStats(PopulationStatsGroup.DIMENSION_STATS, dimensionStats);
            sql = "SELECT ps.ID AS ID ,ps.NO_OF_QUESTIONS,ps.TOTAL_SCORE,ps.MEAN,ps.STD_DEV AS STDDEV FROM population_stats ps where ps.POPULATION_STATS_GROUP=3 AND ps.QUESTION_ID=:questionId ORDER BY ps.ID ";
            List<PopulationStatsWithId> traitStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
            stats.setStats(PopulationStatsGroup.TRAIT_STATS, traitStats);
            sql = "SELECT ps.ID AS ID ,ps.NO_OF_QUESTIONS,ps.TOTAL_SCORE,ps.MEAN,ps.STD_DEV AS STDDEV FROM population_stats ps where ps.POPULATION_STATS_GROUP=4 AND ps.QUESTION_ID=:questionId ORDER BY ps.ID ";
            List<PopulationStatsWithId> aspectStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
            stats.setStats(PopulationStatsGroup.ASPECT_STATS, aspectStats);
            return stats;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatePopulationStatsInDatabase() {
        logger.info(LogUtils.buildStringForSystemLog("population stats method start "));
        NamedParameterJdbcTemplate nm = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> params = new HashMap<>();
        params.put("curDate", AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST));
        params.put("questionStats", PopulationStatsGroup.QUESTION_STATS);
        params.put("dimensionStats", PopulationStatsGroup.DIMENSION_STATS);
        params.put("traitStats", PopulationStatsGroup.TRAIT_STATS);
        params.put("aspectStats", PopulationStatsGroup.ASPECT_STATS);
        // Truncate temp table
        String sqlForTemp = "TRUNCATE TABLE `temp_population_stats`";
        this.jdbcTemplate.update(sqlForTemp);

        // Insert into temp table
        String sqlMain = "SELECT q.QUESTION_ID FROM question_response qr "
                + " LEFT JOIN question q ON qr.QUESTION_ID=q.QUESTION_ID "
                + " WHERE q.QUESTION_TYPE_ID=1  "
                + " AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                + " AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\\\\\+)?([0-9]+\\\\\\\\.[0-9]*|[0-9]*\\\\\\\\.[0-9]+|[0-9]+)$' "
                + " AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 10  "
                + " GROUP BY q.QUESTION_ID";

        List<Integer> listOfAllQuestions = nm.queryForList(sqlMain, params, Integer.class);

        //Question Query
        NamedParameterJdbcTemplate nm1 = new NamedParameterJdbcTemplate(jdbcTemplate);
//        for (int testSessionId : listOfAllTestSessionId) {
        for (int questionId : listOfAllQuestions) {
            params.put("questionId", questionId);
//                params.put("testSessionId", testSessionId);
            String sql = "INSERT INTO temp_population_stats SELECT null,:questionStats, `ID`, SUM(`NO_OF_QUESTIONS`) `NO_OF_QUESTIONS`, SUM(`SUM_OF_VALUE`) `TOTAL_SCORE`, AVG(`MEAN_OF_VALUE`) `MEAN`, STDDEV_SAMP(`MEAN_OF_VALUE`) `STDDEV`, :curDate,:questionId   FROM ("
                    + " SELECT "
                    + "	qr.`TEST_SESSION_ID`, "
                    + "	MAX(q.`QUESTION_ID`) `ID`, "
                    + "	COUNT(IF(q.`IS_REVERSE_KEY`,IF(qmv.`MIN_VALUE`=0,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`+1),qr.`QUESTION_OPTION_VALUE`)) `NO_OF_QUESTIONS`, "
                    + "	SUM(IF(q.`IS_REVERSE_KEY`,IF(qmv.`MIN_VALUE`=0,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`+1),qr.`QUESTION_OPTION_VALUE`)) `SUM_OF_VALUE`, "
                    + "	AVG(IF(q.`IS_REVERSE_KEY`,IF(qmv.`MIN_VALUE`=0,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`+1),qr.`QUESTION_OPTION_VALUE`)) `MEAN_OF_VALUE` "
                    + " FROM question_response qr "
                    + " LEFT JOIN test_session ts ON qr.`TEST_SESSION_ID`=ts.`TEST_SESSION_ID` "
                    + " LEFT JOIN question q ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
                    + " LEFT JOIN (SELECT MAX(qo.`QUESTION_VALUE`) `MAX_VALUE`, MIN(qo.`QUESTION_VALUE`) `MIN_VALUE`, q.`QUESTION_ID` FROM question q LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` GROUP BY q.QUESTION_ID) qmv ON q.`QUESTION_ID`=qmv.`QUESTION_ID` "
                    + " WHERE "
                    + "	q.`QUESTION_ID`=:questionId "
                    //                        + "     AND qr.`TEST_SESSION_ID`!=:testSessionId "
                    + "	AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                    + "	AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\+)?([0-9]+\\\\.[0-9]*|[0-9]*\\\\.[0-9]+|[0-9]+)$' "
                    + "	AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 10 "
                    + "	AND ts.`TEST_ENDED` IS NOT NULL "
                    + "     AND q.`QUESTION_TYPE_ID`=1 "
                    + "     AND (SELECT t.TAKER_ID FROM taker t WHERE ts.`TAKER_ID`=t.TAKER_ID AND t.`ACTIVE`=1)   "
                    + "     GROUP BY qr.`TEST_SESSION_ID` ) s1  ";
            nm1.update(sql, params);
        }
//        }
        // List<PopulationStatsWithId> questionStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
        // stats.setStats(PopulationStatsGroup.QUESTION_STATS, questionStats);

        // Dimension Query
        NamedParameterJdbcTemplate nm2 = new NamedParameterJdbcTemplate(jdbcTemplate);
//        for (int testSessionId : listOfAllTestSessionId) {
        for (int questionId : listOfAllQuestions) {
            params.put("questionId", questionId);
//                params.put("testSessionId", testSessionId);
            String sql = "INSERT INTO temp_population_stats SELECT null,:dimensionStats,`ID`, SUM(`NO_OF_QUESTIONS`) `NO_OF_QUESTIONS`, SUM(`SUM_OF_VALUE`) `TOTAL_SCORE`, AVG(`MEAN_OF_VALUE`) `MEAN`, STDDEV_SAMP(`MEAN_OF_VALUE`) `STDDEV`, :curDate ,:questionId  FROM ("
                    + "SELECT "
                    + "	qr.`TEST_SESSION_ID`, "
                    + "	MAX(q.`DIMENSION_ID`) `ID`, "
                    + "	COUNT(IF(q.`IS_REVERSE_KEY`,IF(qmv.`MIN_VALUE`=0,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`+1),qr.`QUESTION_OPTION_VALUE`)) `NO_OF_QUESTIONS`, "
                    + "	SUM(IF(q.`IS_REVERSE_KEY`,IF(qmv.`MIN_VALUE`=0,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`+1),qr.`QUESTION_OPTION_VALUE`)) `SUM_OF_VALUE`, "
                    + "	AVG(IF(q.`IS_REVERSE_KEY`,IF(qmv.`MIN_VALUE`=0,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE`-qr.`QUESTION_OPTION_VALUE`+1),qr.`QUESTION_OPTION_VALUE`)) `MEAN_OF_VALUE` "
                    + " FROM question_response qr "
                    + " LEFT JOIN test_session ts ON qr.`TEST_SESSION_ID`=ts.`TEST_SESSION_ID` "
                    + " LEFT JOIN question q ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
                    + " LEFT JOIN (SELECT MAX(qo.`QUESTION_VALUE`) `MAX_VALUE`, MIN(qo.`QUESTION_VALUE`) `MIN_VALUE`, q.`QUESTION_ID` FROM question q LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID`=qom.`QUESTION_ID` LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID`=qo.`QUESTION_OPTION_ID` GROUP BY q.QUESTION_ID) qmv ON q.`QUESTION_ID`=qmv.`QUESTION_ID` "
                    + " WHERE "
                    + "	q.`DIMENSION_ID`=(SELECT q1.`DIMENSION_ID`FROM question q1 WHERE q1.`QUESTION_ID`=:questionId) "
                    + "	AND q.`SORT_ORDER`<= (SELECT q2.`SORT_ORDER` FROM question q2 WHERE q2.`QUESTION_ID`=:questionId) "
                    + "	AND q.`QUESTION_ID`=:questionId "
                    //                        + "	AND qr.`TEST_SESSION_ID`!=:testSessionId "
                    + "	AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                    + "	AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\+)?([0-9]+\\\\.[0-9]*|[0-9]*\\\\.[0-9]+|[0-9]+)$' "
                    + "	AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 10 "
                    + "	AND ts.`TEST_ENDED` IS NOT NULL "
                    + "     AND (SELECT t.TAKER_ID FROM taker t WHERE ts.`TAKER_ID`=t.TAKER_ID AND t.`ACTIVE`=1)   "
                    // + "	AND ts.`TEST_TYPE_ID`=(SELECT ts1.`TEST_TYPE_ID` FROM test_session ts1 WHERE ts1.`TEST_SESSION_ID`=:testSessionId) "
                    + " GROUP BY qr.`TEST_SESSION_ID`) s1 ";
            nm2.update(sql, params);
        }
//        }
//        List<PopulationStatsWithId> dimensionStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
//        stats.setStats(PopulationStatsGroup.DIMENSION_STATS, dimensionStats);

        // Trait Query
        NamedParameterJdbcTemplate nm3 = new NamedParameterJdbcTemplate(jdbcTemplate);
//        for (int testSessionId : listOfAllTestSessionId) {
        for (int questionId : listOfAllQuestions) {
            params.put("questionId", questionId);
//                params.put("testSessionId", testSessionId);
            String sql = "INSERT INTO temp_population_stats "
                    + " SELECT null,:traitStats,`ID`, SUM(`NO_OF_QUESTIONS`) `NO_OF_QUESTIONS`, SUM(`SUM_OF_VALUE`) `TOTAL_SCORE`, AVG(`MEAN_OF_VALUE`) `MEAN`, STDDEV_SAMP(`MEAN_OF_VALUE`) `STDDEV`, :curDate,:questionId  "
                    + " FROM (SELECT qr.`TEST_SESSION_ID`, "
                    + "              MAX(q.`TRAIT_ID`) `ID`,  "
                    + "              COUNT( IF( q.`IS_REVERSE_KEY`, IF( qmv.`MIN_VALUE` = 0,qmv.`MAX_VALUE` -qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE` + 1 ), qr.`QUESTION_OPTION_VALUE`)) `NO_OF_QUESTIONS`, "
                    + "	         SUM( IF(q.`IS_REVERSE_KEY`, IF(qmv.`MIN_VALUE` = 0, qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE`,  qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE` + 1 ),qr.`QUESTION_OPTION_VALUE`) ) `SUM_OF_VALUE`,"
                    + "	         AVG( IF( q.`IS_REVERSE_KEY`, IF( qmv.`MIN_VALUE` = 0,qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE` + 1), qr.`QUESTION_OPTION_VALUE` )) `MEAN_OF_VALUE` "
                    + " FROM question_response qr "
                    + " LEFT JOIN test_session ts ON qr.`TEST_SESSION_ID`=ts.`TEST_SESSION_ID` "
                    + " LEFT JOIN question q ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
                    + " AND q.`TRAIT_ID`=(SELECT q1.`TRAIT_ID`FROM question q1 WHERE q1.`QUESTION_ID`=:questionId) "
                    + " AND q.`SORT_ORDER`<= (SELECT q2.`SORT_ORDER` FROM question q2 WHERE q2.`QUESTION_ID`=:questionId) "
                    + " LEFT JOIN (SELECT  MAX(qo.`QUESTION_VALUE`) `MAX_VALUE`, MIN(qo.`QUESTION_VALUE`) `MIN_VALUE`, q.`QUESTION_ID` FROM  question q "
                    + " LEFT JOIN question_option_mapping qom ON q.`QUESTION_ID` = qom.`QUESTION_ID` LEFT JOIN question_option qo ON qom.`QUESTION_OPTION_ID` = qo.`QUESTION_OPTION_ID` GROUP BY q.QUESTION_ID) qmv ON q.`QUESTION_ID` = qmv.`QUESTION_ID` "
                    + " WHERE 1 "
                    //                        + "	AND qr.`TEST_SESSION_ID`!=:testSessionId"
                    + "	AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                    + "	AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\\\\\+)?([0-9]+\\\\\\\\.[0-9]*|[0-9]*\\\\\\\\.[0-9]+|[0-9]+)$' "
                    + "	AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 10 "
                    + "	AND ts.`TEST_ENDED` IS NOT NULL "
                    + "     AND q.`QUESTION_TYPE_ID`=1 "
                    + "     AND (SELECT t.TAKER_ID FROM taker t WHERE ts.`TAKER_ID`=t.TAKER_ID AND t.`ACTIVE`=1)   "
                    + " GROUP BY qr.`TEST_SESSION_ID` ) s1  ";
            nm3.update(sql, params);
        }
//        }
//        List<PopulationStatsWithId> traitStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
//        stats.setStats(PopulationStatsGroup.TRAIT_STATS, traitStats);

        // Aspect
        NamedParameterJdbcTemplate nm4 = new NamedParameterJdbcTemplate(jdbcTemplate);
//        for (int testSessionId : listOfAllTestSessionId) {
        for (int questionId : listOfAllQuestions) {
            try {
                params.put("questionId", questionId);
//                    params.put("testSessionId", testSessionId);
                String sql = " INSERT INTO temp_population_stats SELECT null,:aspectStats,`ID`, SUM(`NO_OF_QUESTIONS`) `NO_OF_QUESTIONS`, SUM(`SUM_OF_VALUE`) `TOTAL_SCORE`, AVG(`MEAN_OF_VALUE`) `MEAN`, STDDEV_SAMP(`MEAN_OF_VALUE`) `STDDEV`, :curDate,:questionId   FROM ("
                        + " SELECT qr.`TEST_SESSION_ID`, "
                        + "        MAX(q.`ASPECT_ID`) `ID`, "
                        + "        COUNT( IF( q.`IS_REVERSE_KEY`,  IF(  qmv.`MIN_VALUE` = 0,  qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE`, qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE` + 1 ), qr.`QUESTION_OPTION_VALUE`)) `NO_OF_QUESTIONS`, "
                        + "	       SUM( IF(q.`IS_REVERSE_KEY`, IF(qmv.`MIN_VALUE` = 0, qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE` + 1 ),qr.`QUESTION_OPTION_VALUE`) ) `SUM_OF_VALUE`,"
                        + "	       AVG(IF( q.`IS_REVERSE_KEY`, IF( qmv.`MIN_VALUE` = 0,qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE`,qmv.`MAX_VALUE` - qr.`QUESTION_OPTION_VALUE` + 1), qr.`QUESTION_OPTION_VALUE` )) `MEAN_OF_VALUE`  "
                        + " FROM question_response qr "
                        + " LEFT JOIN test_session ts ON qr.`TEST_SESSION_ID`=ts.`TEST_SESSION_ID` "
                        + " LEFT JOIN question q ON qr.`QUESTION_ID`=q.`QUESTION_ID` "
                        + " AND q.`ASPECT_ID`=(SELECT q1.`ASPECT_ID`FROM question q1 WHERE q1.`QUESTION_ID`=:questionId) "
                        + " AND q.`SORT_ORDER`<= (SELECT q2.`SORT_ORDER` FROM question q2 WHERE q2.`QUESTION_ID`=:questionId) "
                        + " LEFT JOIN (SELECT  MAX(qo.`QUESTION_VALUE`) `MAX_VALUE`, MIN(qo.`QUESTION_VALUE`) `MIN_VALUE`,q.`QUESTION_ID` FROM question q LEFT JOIN question_option_mapping qom  ON q.`QUESTION_ID` = qom.`QUESTION_ID` LEFT JOIN question_option qo  ON qom.`QUESTION_OPTION_ID` = qo.`QUESTION_OPTION_ID` GROUP BY q.QUESTION_ID) qmv ON q.`QUESTION_ID` = qmv.`QUESTION_ID` "
                        + " WHERE 1 "
                        //                            + " AND qr.`TEST_SESSION_ID`!=:testSessionId "
                        + " AND qr.`QUESTION_OPTION_VALUE` IS NOT NULL "
                        + " AND qr.`QUESTION_OPTION_VALUE` REGEXP '^(-|\\\\\\\\+)?([0-9]+\\\\\\\\.[0-9]*|[0-9]*\\\\\\\\.[0-9]+|[0-9]+)$' "
                        + " AND qr.`QUESTION_OPTION_VALUE` BETWEEN 0 AND 10 "
                        + " AND ts.`TEST_ENDED` IS NOT NULL "
                        + " AND q.`QUESTION_TYPE_ID`=1 "
                        + " AND (SELECT t.TAKER_ID FROM taker t WHERE ts.`TAKER_ID`=t.TAKER_ID AND t.`ACTIVE`=1)   "
                        + " GROUP BY qr.`TEST_SESSION_ID`) s1  ";
                nm4.update(sql, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        }
//        List<PopulationStatsWithId> aspectStats = nm.query(sql, params, new PopulationStatsWithIdRowMapper());
//        stats.setStats(PopulationStatsGroup.ASPECT_STATS, aspectStats);
//        return stats;

        // truncate main population stats table
        String sqlPopulationStats = "TRUNCATE TABLE `population_stats`";
        this.jdbcTemplate.update(sqlPopulationStats);

        // insert from temp table intp popuplation status
        String sql = "INSERT INTO population_stats "
                + " SELECT NULL,ps.POPULATION_STATS_GROUP,ID, "
                + " ps.NO_OF_QUESTIONS,ps.TOTAL_SCORE,ps.MEAN,ps.STD_DEV,ps.DATE,ps.QUESTION_ID "
                + " FROM temp_population_stats ps ";
        nm.update(sql, params);
        logger.info(LogUtils.buildStringForSystemLog("population stats method end "));
    }

}
