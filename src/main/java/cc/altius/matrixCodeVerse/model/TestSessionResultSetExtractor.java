/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author altius
 */
public class TestSessionResultSetExtractor implements ResultSetExtractor<TestSession> {

    @Override
    public TestSession extractData(ResultSet rs) throws SQLException, DataAccessException {
        TestSession ts = new TestSession();
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
                taker.setMedicalProfessional(rs.getBoolean("IS_MEDICAL_PROFESSIONAL"));
                ts.setTaker(taker);

                TestType tt = new TestType();
                tt.setTestTypeId(rs.getInt("TEST_TYPE_ID"));
                tt.setTestTypeDesc(rs.getString("TEST_TYPE_DESC"));
                ts.setTestType(tt);
                isFirst = false;
                
            }
            if(rs.getInt("QUESTION_TYPE_ID")!=4 && rs.getInt("QUESTION_TYPE_ID")!=5)
            {
            QuestionResponseBasic qrbQ = new QuestionResponseBasic();
            qrbQ.setId(rs.getInt("QUESTION_ID"));
            qrbQ.setScore(rs.getDouble("QUESTION_OPTION_VALUE"));
            ts.getCurrentStats().addResponseForQuestion(qrbQ);
            
            QuestionResponseBasic qrbD = new QuestionResponseBasic();
            qrbD.setId(rs.getInt("DIMENSION_ID"));
            qrbD.setScore(rs.getDouble("QUESTION_OPTION_VALUE"));
            ts.getCurrentStats().addResponseForDimension(qrbD);
            
            QuestionResponseBasic qrbT = new QuestionResponseBasic();
            qrbT.setId(rs.getInt("TRAIT_ID"));
            qrbT.setScore(rs.getDouble("QUESTION_OPTION_VALUE"));
            ts.getCurrentStats().addResponseForTrait(qrbT);
            
            QuestionResponseBasic qrbA = new QuestionResponseBasic();
            qrbA.setId(rs.getInt("ASPECT_ID"));
            qrbA.setScore(rs.getDouble("QUESTION_OPTION_VALUE"));
            ts.getCurrentStats().addResponseForAspect(qrbA);
            }
        }
        return ts;
    }

}
