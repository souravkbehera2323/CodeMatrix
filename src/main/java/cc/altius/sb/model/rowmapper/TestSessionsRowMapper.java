/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.sb.model.Question;
import cc.altius.sb.model.QuestionAspect;
import cc.altius.sb.model.QuestionDimension;
import cc.altius.sb.model.QuestionOption;
import cc.altius.sb.model.QuestionResponse;
import cc.altius.sb.model.QuestionTrait;
import cc.altius.sb.model.QuestionType;
import cc.altius.sb.model.Taker;
import cc.altius.sb.model.TestSessions;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class TestSessionsRowMapper implements RowMapper<TestSessions> {

    @Override
    public TestSessions mapRow(ResultSet rs, int i) throws SQLException {
        TestSessions testsession = new TestSessions();
        testsession.setTestSessionId(rs.getInt("TEST_SESSION_ID"));
        testsession.setLastQuestionId(rs.getInt("LAST_SORT_ORDER"));
        testsession.setTestStarted(rs.getDate("TEST_STARTED"));
        testsession.setTestEnded(rs.getDate("TEST_ENDED"));
        Taker taker = new Taker();
        taker.setTakerId(rs.getInt("TAKER_ID"));
        taker.setFirstName(rs.getString("FIRST_NAME"));
        taker.setLastName(rs.getString("LAST_NAME"));
        testsession.setTaker(taker);

        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setQuestionOptionValue(rs.getString("QUESTION_OPTION_VALUE"));
        questionResponse.setQuestionOptionAdditionalValue(rs.getString("QUESTION_OPTION_ADDITIONAL_VALUE"));
        
        Question question = new Question();
        question.setQuestionName(rs.getString("QUESTION_NAME"));
        question.setQuestionId(rs.getInt("QUESTION_ID"));
        question.setSortOrder(rs.getInt("SORT_ORDER"));
        
        QuestionType questionType=new QuestionType(); 
        questionType.setQuestionTypeName(rs.getString("QUESTION_TYPE_NAME"));
        questionType.setQuestionTypeId(rs.getInt("QUESTION_TYPE_ID"));
        question.setQuestionType(questionType);
        
        QuestionDimension questionDimension=new QuestionDimension();
        questionDimension.setDimensionId(rs.getInt("DIMENSION_ID"));
        questionDimension.setDimensionName(rs.getString("DIMENSION_NAME"));
        question.setQuestionDimension(questionDimension);

        QuestionOption questionOption = new QuestionOption();
        questionOption.setQuestionOptionId(rs.getInt("QUESTION_OPTION_ID"));
        questionOption.setSortOrder(rs.getInt("qoSortOrder"));
        questionOption.setQuestionOptionName(rs.getString("QUESTION_OPTION_NAME"));
        questionResponse.setQuestionOption(questionOption);
        
        QuestionTrait questionTrait=new QuestionTrait();
        questionTrait.setTraitId(rs.getInt("TRAIT_ID"));
        questionTrait.setTraitName(rs.getString("TRAIT_NAME"));
        question.setQuestionTrait(questionTrait);
        
        QuestionAspect questionAspect=new QuestionAspect();
        questionAspect.setAspectName(rs.getString("ASPECT_NAME"));
        question.setQuestionAspect(questionAspect);
        
        questionResponse.setQuestion(question);
        testsession.setQuestionResponses(questionResponse);
         
        return testsession;
    }

}
