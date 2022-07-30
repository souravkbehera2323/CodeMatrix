/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model.DTORowMapper;

import cc.altius.sb.model.DTO.TakerNoOfQuestionAttempted;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yogesh
 */
public class TakerNoOfQuestionAttemptedRowMapper implements RowMapper<TakerNoOfQuestionAttempted> {

    @Override
    public TakerNoOfQuestionAttempted mapRow(ResultSet rs, int i) throws SQLException {
        TakerNoOfQuestionAttempted attempted = new TakerNoOfQuestionAttempted();
        attempted.setTakerId(rs.getInt("TAKER_ID"));
        attempted.setFirstName(rs.getString("FIRST_NAME"));
        attempted.setLastName(rs.getString("LAST_NAME"));
        attempted.setNoOfQuestionAttempted(rs.getString("No_of_question_attempted"));
        attempted.setTestStarted(rs.getDate("TEST_STARTED"));
        attempted.setTestEnded(rs.getDate("TEST_ENDED"));
        attempted.setReferredBy(rs.getString("REFERRED_BY"));
        attempted.setEmail(rs.getString("EMAIL"));
        attempted.setPhone(rs.getString("MOBILE_NO"));
        attempted.setZipCode(rs.getString("ZIP_CODE"));
        attempted.setActive(rs.getBoolean("ACTIVE"));
        return attempted;
    }

}
