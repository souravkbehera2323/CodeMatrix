/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.sb.dao.LoggerDao;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author akil
 */
@Repository
public class LoggerDaoImpl implements LoggerDao {
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public void createTables() {
//        String sql = "SELECT EXISTS(SELECT * FROM information_schema.tables WHERE table_schema = 'joli_base_app' AND table_name = 'logging_event') AS tableExists";
//        boolean table = this.jdbcTemplate.queryForObject(sql, Boolean.class);
//        if (!table) {
            String sql = "CREATE TABLE IF NOT EXISTS logging_event  (timestmp BIGINT NOT NULL, formatted_message TEXT NOT NULL, logger_name VARCHAR(254) NOT NULL, level_string VARCHAR(254) NOT NULL, thread_name VARCHAR(254), reference_flag SMALLINT, arg0 VARCHAR(254), arg1 VARCHAR(254), arg2 VARCHAR(254), arg3 VARCHAR(254), caller_filename VARCHAR(254) NOT NULL, caller_class VARCHAR(254) NOT NULL, caller_method VARCHAR(254) NOT NULL, caller_line CHAR(4) NOT NULL, event_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY)";
            this.jdbcTemplate.execute(sql);
            
//        }
        
//        sql = "SELECT EXISTS(SELECT * FROM information_schema.tables WHERE table_schema = 'joli_base_app' AND table_name = 'logging_event_property') AS tableExists";
//        table = this.jdbcTemplate.queryForObject(sql, Boolean.class);
//        if (!table) {
            sql = "CREATE TABLE IF NOT EXISTS logging_event_property (event_id BIGINT NOT NULL, mapped_key VARCHAR(254) NOT NULL,mapped_value TEXT, PRIMARY KEY(event_id, mapped_key), FOREIGN KEY (event_id) REFERENCES logging_event(event_id))";
            this.jdbcTemplate.execute(sql);
            
//        }
        
//        sql = "SELECT EXISTS(SELECT * FROM information_schema.tables WHERE table_schema = 'joli_base_app' AND table_name = 'logging_event_property') AS tableExists";
//        table = this.jdbcTemplate.queryForObject(sql, Boolean.class);
//        if (!table) {
            sql = "CREATE TABLE IF NOT EXISTS logging_event_exception (event_id BIGINT NOT NULL, i SMALLINT NOT NULL,trace_line VARCHAR(254) NOT NULL, PRIMARY KEY(event_id, i), FOREIGN KEY (event_id) REFERENCES logging_event(event_id))";
            this.jdbcTemplate.execute(sql);
            
//        }
    }
    
}
