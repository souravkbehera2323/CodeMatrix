/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.matrixCodeVerse.model.Property;
import cc.altius.matrixCodeVerse.model.Question;
import cc.altius.matrixCodeVerse.model.QuestionOption;
import cc.altius.matrixCodeVerse.model.Taker;
import cc.altius.matrixCodeVerse.model.TestSessionWithQuestionList;
import cc.altius.matrixCodeVerse.model.TestType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author altius
 */
public class TestSessionForAllQuestionResultSetExtractor implements ResultSetExtractor<TestSessionWithQuestionList> {

    @Override
    public TestSessionWithQuestionList extractData(ResultSet rs) throws SQLException, DataAccessException {
        TestSessionWithQuestionList ts = new TestSessionWithQuestionList();
        List<Question> list = new LinkedList<>();
        boolean isFirst = true;
        while (rs.next()) {
            if (isFirst) {
                ts.setTestSessionId(rs.getInt("TEST_SESSION_ID"));
                ts.setTestStartedTime(rs.getDate("TEST_STARTED"));
                ts.setTestEndedTime(rs.getDate("TEST_ENDED"));
                ts.setLastSortOrderId(rs.getInt("LAST_SORT_ORDER"));

                Taker taker = new Taker();
                taker.setTakerId(rs.getInt("TAKER_ID"));
                taker.setFirstName(rs.getString("FIRST_NAME"));
                taker.setLastName(rs.getString("LAST_NAME"));
                taker.setEmail(rs.getString("EMAIL"));
                ts.setTaker(taker);

                TestType tt = new TestType();
                tt.setTestTypeId(rs.getInt("TEST_TYPE_ID"));
                tt.setTestTypeDesc(rs.getString("TEST_TYPE_DESC"));
                ts.setTestType(tt);

                isFirst = false;
            }
            Question question = new Question();
            question.setQuestionId(rs.getInt("QUESTION_ID"));
            question.setName(rs.getString("QUESTION_NAME"));
            question.setSortOrder(rs.getInt("SORT_ORDER"));
            question.setQuestionTypeId(rs.getInt("QUESTION_TYPE_ID"));
            question.setDimension(new Property(rs.getInt("DIMENSION_ID"), rs.getString("DIMENSION_NAME")));
            question.setTrait(new Property(rs.getInt("TRAIT_ID"), rs.getString("TRAIT_NAME")));
            question.setAspect(new Property(rs.getInt("ASPECT_ID"), rs.getString("ASPECT_NAME")));

            QuestionOption questionOption = new QuestionOption();
            questionOption.setQuestionOptionId(rs.getInt("QUESTION_OPTION_ID"));
            questionOption.setQuestionOptionValue(rs.getString("QUESTION_OPTION_VALUE"));
            questionOption.setQuestionOptionAdditionalValue(rs.getString("QUESTION_OPTION_ADDITIONAL_VALUE"));
            question.getQuestionOptionList().add(questionOption);
            list.add(question);
            ts.setCurrentQuestion(list);
        }
        return ts;
    }

}
