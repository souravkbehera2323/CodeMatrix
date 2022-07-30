/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.rowmapper;

import cc.altius.matrixCodeVerse.model.Taker;
import cc.altius.matrixCodeVerse.model.TestSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yogesh
 */
public class TakerSessionResultRowMapper implements RowMapper<TestSession> {

    @Override
    public TestSession mapRow(ResultSet rs, int i) throws SQLException {
       TestSession testSession = new TestSession();
        testSession.setTestStartedTime(rs.getDate("TEST_STARTED"));
        testSession.setTestEndedTime(rs.getDate("TEST_ENDED"));
        testSession.setLastSortOrderId(rs.getInt("LAST_SORT_ORDER"));
//       testSession.setLastSortOrderId(rs.getInt("questionCount"));
        Taker taker = new Taker();
        taker.setTakerId(rs.getInt("TAKER_ID"));
        taker.setFirstName(rs.getString("FIRST_NAME"));
        taker.setLastName(rs.getString("LAST_NAME"));
        taker.setEmail(rs.getString("EMAIL"));
   
       // taker.setTestSessions(testSession);
       testSession.setTaker(taker);
       return testSession;
    }

}
    