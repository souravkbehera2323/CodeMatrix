/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.sb.dao.ImportDao;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author akil
 */
@Repository
public class ImportDaoImpl implements ImportDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void doImport() {
//        System.out.println("Starting import");
     //   System.out.println("Shutting down ForeignKey Checks");
        this.jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0");

       // System.out.println("Begin import for test_type");
       // System.out.println("Shutting down AutoIncrement for test_type");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`test_type` CHANGE COLUMN `TEST_TYPE_ID` `TEST_TYPE_ID` INT(10) UNSIGNED NOT NULL ");
       // System.out.println("Truncate test_type");
        this.jdbcTemplate.update("truncate matrixcodeverse.test_type");
       // System.out.println("Inserting data into test_type");
        int rows = this.jdbcTemplate.update("insert into matrixcodeverse.test_type select t.id, t.name, 1, 1, t.created, 1, t.modified from code.tests t");
       // System.out.println(rows + " new Rows inserted");
       // System.out.println("Restoring AutoIncrement for test_type");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`test_type` CHANGE COLUMN `TEST_TYPE_ID` `TEST_TYPE_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT");

       // System.out.println("Begin import for question_type");
       // System.out.println("Shutting down AutoIncrement for question_type");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`question_type` CHANGE COLUMN `QUESTION_TYPE_ID` `QUESTION_TYPE_ID` INT(10) UNSIGNED NOT NULL");
       // System.out.println("Truncate question_type");
        this.jdbcTemplate.update("truncate matrixcodeverse.question_type");
       // System.out.println("Inserting data into question_type");
        rows = this.jdbcTemplate.update("insert into matrixcodeverse.question_type select qt.id, qt.name, 1, 1, now(), 1, now() from code.question_types qt");
       // System.out.println(rows + " new Rows inserted");
       // System.out.println("Restoring AutoIncrement for question_type");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`question_type` CHANGE COLUMN `QUESTION_TYPE_ID` `QUESTION_TYPE_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT");

       // System.out.println("Begin import for question_option");
       // System.out.println("Shutting down AutoIncrement for question_option");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`question_option` CHANGE COLUMN `QUESTION_OPTION_ID` `QUESTION_OPTION_ID` INT(10) UNSIGNED NOT NULL");
       // System.out.println("Truncate question_option");
        this.jdbcTemplate.update("truncate matrixcodeverse.question_option");
       // System.out.println("Inserting data into question_option");
        rows = this.jdbcTemplate.update("insert into matrixcodeverse.question_option select qo.id, qo.name, qo.question_value, qo.label_value, qo.label_position, qo.sort_order, 1, 1, now(), 1, now() from code.question_options qo");
       // System.out.println(rows + " new Rows inserted");
       // System.out.println("Restoring AutoIncrement for question_option");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`question_option` CHANGE COLUMN `QUESTION_OPTION_ID` `QUESTION_OPTION_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT");

       // System.out.println("Begin import for question_option_mapping");
       // System.out.println("Shutting down AutoIncrement for question_option_mapping");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`question_option_mapping` CHANGE COLUMN `QUESTION_QUESTION_OPTION_ID` `QUESTION_QUESTION_OPTION_ID` INT(10) UNSIGNED NOT NULL");
       // System.out.println("Truncate question_option_mapping");
        this.jdbcTemplate.update("truncate matrixcodeverse.question_option_mapping");
       // System.out.println("Inserting data into question_option_mapping");
        rows = this.jdbcTemplate.update("insert into matrixcodeverse.question_option_mapping select qqo.id, qqo.question_id, qqo.question_option_id from code.questions_question_options qqo");
       // System.out.println(rows + " new Rows inserted");
       // System.out.println("Restoring AutoIncrement for question_option_mapping");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`question_option_mapping` CHANGE COLUMN `QUESTION_QUESTION_OPTION_ID` `QUESTION_QUESTION_OPTION_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT");

       // System.out.println("Begin import for taker");
       // System.out.println("Shutting down AutoIncrement for taker");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`taker` CHANGE COLUMN `TAKER_ID` `TAKER_ID` INT(10) UNSIGNED NOT NULL");
       // System.out.println("Truncate taker");
        this.jdbcTemplate.update("truncate matrixcodeverse.taker");
        //System.out.println("Inserting data into taker");
        rows = this.jdbcTemplate.update("insert into matrixcodeverse.taker select t.id, t.first_name, t.last_name, t.phone, t.email, t.age, if(t.gender='Male',1,if(t.gender='Female',2,1)), t.referred_by, t.created, t.modified, 0 from code.takers t");
       // System.out.println(rows + " new Rows inserted");
       // System.out.println("Restoring AutoIncrement for taker");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`taker` CHANGE COLUMN `TAKER_ID` `TAKER_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT");

      //  System.out.println("Begin import for test_session");
      //  System.out.println("Shutting down AutoIncrement for test_session");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.test_session CHANGE COLUMN `TEST_SESSION_ID` `TEST_SESSION_ID` INT(10) UNSIGNED NOT NULL");
       // System.out.println("Truncate test_session");
        this.jdbcTemplate.update("truncate matrixcodeverse.test_session");
       // System.out.println("Inserting data into test_session, ignoring those rows where last_question_id is null");
        rows = this.jdbcTemplate.update("insert into matrixcodeverse.test_session select ts.id, ts.taker_id, ts.last_questionid, ts.status, ts.test_started, ts.test_ended, ts.created, ts.modified from code.test_sessions ts where ts.last_questionid is not null");
       // System.out.println(rows + " new Rows inserted");
       // System.out.println("Restoring AutoIncrement for test_session");
        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.test_session CHANGE COLUMN `TEST_SESSION_ID` `TEST_SESSION_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT");

       // System.out.println("Begin import for question_response");
//        System.out.println("Shutting down AutoIncrement for question_response");
//        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`question_response` CHANGE COLUMN `QUESTION_RESPONSE_ID` `QUESTION_RESPONSE_ID` INT(10) UNSIGNED NOT NULL");
      //  System.out.println("Truncate question_response");
        this.jdbcTemplate.update("truncate matrixcodeverse.question_response");
       // System.out.println("Inserting data into question_response for Question Type = 1");
        rows = this.jdbcTemplate.update("insert into matrixcodeverse.question_response "
                + "select qr.id, qr.taker_id, qr.question_id, qr.test_id, qr.test_session_id, qr.question_option_id, qr.question_option_value, qr.question_option_additional_value, qr.started_question, qr.ended_question, qr.created, qr.modified question_response "
                + "from code.question_responses qr "
                + "left join code.questions q on q.id=qr.question_id "
                + "left join code.question_options qo on qr.question_option_id=qo.id "
                + "left join code.test_sessions ts on qr.test_session_id=ts.id "
                + "where "
                + "	q.question_type_id in (1) "
                + "    and qr.question_option_value is not null "
                + "    and qr.started_question is not null");
       // System.out.println(rows + " new Rows inserted");
       // System.out.println("Inserting data into question_response for Question Type = 1");
        rows = this.jdbcTemplate.update("insert into matrixcodeverse.question_response "
                + "select qr.id, qr.taker_id, qr.question_id, qr.test_id, qr.test_session_id, qr.question_option_id, qr.question_option_value, qr.question_option_additional_value, qr.started_question, qr.ended_question, qr.created, qr.modified "
                + "from code.question_responses qr "
                + "left join code.questions q on q.id=qr.question_id "
                + "left join code.question_options qo on qr.question_option_id=qo.id "
                + "left join code.test_sessions ts on qr.test_session_id=ts.id "
                + "where "
                + "	q.question_type_id in (2,3) "
                + "     and (qr.question_option_value is not null or qr.question_option_additional_value is not null)"
                + "     and qr.started_question is not null");
       // System.out.println(rows + " new Rows inserted");

        List<Map<String, Object>> result = this.jdbcTemplate.queryForList("select qr.id, qr.taker_id, qr.question_id, qr.test_id, qr.test_session_id, qr.question_option_id, qr.question_option_value, qr.question_option_additional_value, qr.started_question, qr.ended_question, qr.created, qr.modified "
                + "from code.question_responses qr "
                + "left join code.questions q on q.id=qr.question_id  "
                + "left join code.question_options qo on qr.question_option_id=qo.id "
                + "left join code.test_sessions ts on qr.test_session_id=ts.id "
                + "where "
                + "	q.question_type_id in (4) "
                + "     and (qr.question_option_value is not null or qr.question_option_additional_value is not null)"
                + "     and qr.started_question is not null");
      //  System.out.println(rows + " new Rows inserted");
        String sql = "";
        sql = "INSERT INTO question_response (TAKER_ID,QUESTION_ID,TEST_TYPE_ID,TEST_SESSION_ID, "
                + "QUESTION_OPTION_ID, QUESTION_OPTION_VALUE, QUESTION_OPTION_ADDITIONAL_VALUE, STARTED_QUESTION, ENDED_QUESTION, "
                + "CREATED_DATE, LAST_MODIFIED_DATE) VALUES "
                + "(?, ? ,? ,? ,? ,"
                + "?, ?, ?, ?, ?, "
                + "?)";
        int rowCnt = 0;
        int insertedCnt = 0;
        for (Map<String, Object> row : result) {
            String additionalValue = (String) row.get("question_option_additional_value");

            String[] value = additionalValue.split("\r\n");
            for(int i=0;i<value.length;i++)
            {
         //   System.out.println("value===> " + value[i]+"    "+(value[i].substring(value[i].indexOf("[")+1,value[i].indexOf("]")))+"              "+(value[i].split("-")[1]));
         //   System.out.println("==============");
            }
            if (additionalValue != null && additionalValue.indexOf(",") > 0) {
                for (String val : additionalValue.split(",")) {
                    insertedCnt += this.jdbcTemplate.update(sql, row.get("taker_id"), row.get("question_id"), row.get("test_id"), row.get("test_session_id"),
                            row.get("question_option_id"), row.get("question_option_value"), val, row.get("started_question"), row.get("ended_question"),
                            row.get("created"), row.get("modified"));
                }
            } else {
                insertedCnt += this.jdbcTemplate.update(sql, row.get("taker_id"), row.get("question_id"), row.get("test_id"), row.get("test_session_id"),
                        row.get("question_option_id"), row.get("question_option_value"), row.get("question_option_additional_value"), row.get("started_question"), row.get("ended_question"),
                        row.get("created"), row.get("modified"));
            }
            rowCnt++;
        }
      //  System.out.println(rowCnt + " rows found and split into " + insertedCnt + " rows");

        result.clear();
        result = this.jdbcTemplate.queryForList("select qr.id, qr.taker_id, qr.question_id, qr.test_id, qr.test_session_id, qr.question_option_id, qr.question_option_value, qr.question_option_additional_value, qr.started_question, qr.ended_question, qr.created, qr.modified "
                + "from code.question_responses qr "
                + "left join code.questions q on q.id=qr.question_id "
                + "left join code.question_options qo on qr.question_option_id=qo.id "
                + "left join code.test_sessions ts on qr.test_session_id=ts.id "
                + "where "
                + "	q.question_type_id in (5) "
                + "     and (qr.question_option_value is not null or qr.question_option_additional_value is not null)"
                + "     and qr.started_question is not null");
      //  System.out.println(rows + " new Rows inserted");
        sql = "INSERT INTO question_response (TAKER_ID,QUESTION_ID,TEST_TYPE_ID,TEST_SESSION_ID, "
                + "QUESTION_OPTION_ID, QUESTION_OPTION_VALUE, QUESTION_OPTION_ADDITIONAL_VALUE, STARTED_QUESTION, ENDED_QUESTION, "
                + "CREATED_DATE, LAST_MODIFIED_DATE) VALUES "
                + "(?, ? ,? ,? ,? ,"
                + "?, ?, ?, ?, ?, "
                + "?)";
        rowCnt = 0;
        insertedCnt = 0;
        for (Map<String, Object> row : result) {
            String additionalValue = (String) row.get("question_option_additional_value");
            if (additionalValue != null && additionalValue.indexOf(",") > 0) {
                for (String val : additionalValue.split(",")) {
                    insertedCnt += this.jdbcTemplate.update(sql, row.get("taker_id"), row.get("question_id"), row.get("test_id"), row.get("test_session_id"),
                            row.get("question_option_id"), row.get("question_option_value"), val, row.get("started_question"), row.get("ended_question"),
                            row.get("created"), row.get("modified"));
                }
            } else {
                insertedCnt += this.jdbcTemplate.update(sql, row.get("taker_id"), row.get("question_id"), row.get("test_id"), row.get("test_session_id"),
                        row.get("question_option_id"), row.get("question_option_value"), row.get("question_option_additional_value"), row.get("started_question"), row.get("ended_question"),
                        row.get("created"), row.get("modified"));
            }
            rowCnt++;
        }
      //  System.out.println(rowCnt + " rows found and split into " + insertedCnt + " rows");
//        System.out.println("Restoring AutoIncrement for question_response");
//        this.jdbcTemplate.update("ALTER TABLE `matrixcodeverse`.`question_response` CHANGE COLUMN `QUESTION_RESPONSE_ID` `QUESTION_RESPONSE_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT");

      //  System.out.println("Resetting ForeignKey Checks");
        this.jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1");
    }
}


