/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.matrixCodeVerse.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yogesh
 */
public class PopulationStatsWithIdRowMapper implements RowMapper<PopulationStatsWithId> {

    @Override
    public PopulationStatsWithId mapRow(ResultSet rs, int i) throws SQLException {
        return new PopulationStatsWithId(rs.getInt("ID"), rs.getInt("NO_OF_QUESTIONS"), rs.getDouble("TOTAL_SCORE"), rs.getDouble("MEAN"), rs.getDouble("STDDEV"));
    }

}
