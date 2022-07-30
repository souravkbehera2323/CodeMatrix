/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.matrixCodeVerse.model.TestSession;
import cc.altius.maven.AllAltiusUtil;
import cc.altius.sb.dao.RegisterDao;
import cc.altius.sb.model.Taker;
import cc.altius.sb.utils.LogUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import static jxl.biff.BaseCellFeatures.logger;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
//import org.apache.xmlrpc.client.XmlRpcClient;
//import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
//import cc.altius.utils.DateUtils;
/**
 *
 * @author altius
 */
@Repository

public class RegisterDaoImpl implements RegisterDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
 
    //Add Taker in user and taker table
    @Override
    public int addTaker(Taker taker, String lastNameId) {
        Date dt = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST);
        if (lastNameId.equals("1")) {
            SimpleJdbcInsert userInsert = new SimpleJdbcInsert(this.dataSource).withTableName("taker").usingGeneratedKeyColumns("TAKER_ID");
            Map<String, Object> params = new HashMap<>();
            params.put("FIRST_NAME", taker.getFirstName());
            params.put("LAST_NAME", taker.getLastName());
            params.put("EMAIL", taker.getEmail());
            params.put("MOBILE_NO", taker.getPhone());
            params.put("AGE", taker.getAge());
            params.put("GENDER_ID", taker.getGender());
            params.put("CREATED_DATE", dt);
            params.put("LAST_MODIFIED_DATE", dt);
            params.put("REFERRED_BY", taker.getReferredBy());
            params.put("FLAG", 1);
            params.put("ZIP_CODE", taker.getZipCode());
            params.put("IS_MEDICAL_PROFESSIONAL", taker.isMedicalProfessional());
            params.put("COVID", taker.getCovid());
            params.put("USER_ID", 0);
            params.put("REFERREL", taker.getReferredBy());
            params.put("ACTIVE", 1);
            int takerId = userInsert.executeAndReturnKey(params).intValue();
            return takerId;
        } else {

            SimpleJdbcInsert userInsert1 = new SimpleJdbcInsert(this.dataSource).withTableName("us_user").usingGeneratedKeyColumns("USER_ID");
            Map<String, Object> params1 = new HashMap<>();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashPass = encoder.encode(taker.getPassword());
            params1.put("USERNAME", taker.getFirstName());
            params1.put("NAME", taker.getFirstName());
            params1.put("EMAIL_ID", taker.getEmail());
            //params.put("PHONE_NO", user.getPhoneNo());
            params1.put("PASSWORD", hashPass);
            params1.put("ACTIVE", 1);
            params1.put("EXPIRED", false);
            params1.put("EXPIRES_ON",  AllAltiusUtil.getOffsetFromCurrentDateObject(AllAltiusUtil.EST, 365));
            params1.put("FAILED_ATTEMPTS", 0);
            //params.put("OUTSIDE_ACCESS", user.isOutsideAccess());
            params1.put("CREATED_BY", 1);
            params1.put("LAST_MODIFIED_BY", 1);
            params1.put("CREATED_DATE", dt);
            params1.put("LAST_MODIFIED_DATE", dt);
            params1.put("T_ZONE_ID  ", 0);
            params1.put("IMAGE_DATA", "");
            int userId = userInsert1.executeAndReturnKey(params1).intValue();
            params1.clear();
            String sqlString = "INSERT INTO us_user_role (USER_ID, ROLE_ID) VALUES(:userId, :roleId)";
            params1.put("userId", userId);
            params1.put("roleId", "ROLE_TAKER");
            logger.debug(LogUtils.buildStringForLog(sqlString, params1));
            this.namedParameterJdbcTemplate.update(sqlString, params1);

            String sql = "UPDATE taker SET taker.`LAST_NAME`='" + taker.getLastName() + "',taker.`REFERRED_BY`='" + taker.getReferredBy() + "',taker.`FIRST_NAME`='" + taker.getFirstName() + "',"
                    + "taker.`AGE`='" + taker.getAge() + "',taker.`EMAIL`='" + taker.getEmail() + "', "
                    + "taker.`GENDER_ID`='" + taker.getGender() + "',taker.`MOBILE_NO`='" + taker.getPhone() + "', "
                    + "taker.`ZIP_CODE`='" + taker.getZipCode() + "', "
                    + "taker.`USER_ID`='" + userId + "', "
                    + "taker.`FLAG`='2' ";
            if (taker.getPhone() == null || taker.getPhone().equals("")) {
                sql += ",taker.`IS_MEDICAL_PROFESSIONAL` = " + taker.isMedicalProfessional() + ", taker.`COVID`='" + taker.getCovid() + "'";
            }
            sql += " WHERE taker.`LAST_NAME`='" + lastNameId + "';";

            this.jdbcTemplate.update(sql);
        }
        return taker.getTakerId();
    }

    @Override
    public boolean getTakerByTakerLastName(String lastName, String email) {
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT COUNT(*) FROM taker WHERE taker.`LAST_NAME`=? AND EMAIL=? ";
        int a = this.jdbcTemplate.queryForObject(sql, Integer.class, lastName, email);
        if (a == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int addTestSession(TestSession testsession, int takerId) {
        SimpleJdbcInsert userInsert = new SimpleJdbcInsert(this.dataSource).withTableName("test_session").usingGeneratedKeyColumns("TEST_SESSION_ID");
        Map<String, Object> params = new HashMap<>();
        Date dt = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST);
        params.put("TAKER_ID", takerId);
//       params.put("TEST_TYPE_ID", 1);
        params.put("CREATED_DATE", dt);
        params.put("LAST_MODIFIED_DATE", dt);
        params.put("ACTIVE", true);
        params.put("LAST_SORT_ORDER", 0);
        params.put("TEST_STARTED", dt);
        params.put("TEST_ENDED", dt);
//        params.put("SORT_ORDER", 0);
        int testSessionId = userInsert.executeAndReturnKey(params).intValue();
        return testSessionId;
    }

    @Override
    public boolean getExistTakerByEmailId(String email) {
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT COUNT(*) FROM taker WHERE EMAIL=? ";
        int a = this.jdbcTemplate.queryForObject(sql, Integer.class, email);
        if (a == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void addContactsInInfusionsoft(Taker taker) {
        try {
            //Sets up the java client, including the api url
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("https://dy791.infusionsoft.com:443/api/xmlrpc"));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            //The secure encryption key
            String key = "0e9edc94d1b8fca947f011982d907222";

            List parameters = new ArrayList();
            Map contactData = new HashMap();
            contactData.put("FirstName", taker.getFirstName());
            contactData.put("LastName", taker.getLastName());
            contactData.put("Email", taker.getEmail());

            parameters.add(key); //The secure key
            parameters.add("Contact"); //The table we will be adding to
            parameters.add(contactData); //The data to be added
            //Make the call
            Integer contactId = (Integer) client.execute("DataService.add", parameters);

            int groupId = 141; //The group we will be adding to
            List parameters2 = new ArrayList();
            parameters2.add(key); //Secure key
            parameters2.add(contactId); //Id of the contact we just added
            parameters2.add(groupId); //Id of the group we want to add to

            Boolean success = (Boolean) client.execute("ContactService.addToGroup", parameters2);

            List fields = new ArrayList(); //What fields we will be selecting
            fields.add("ContactGroup");
            fields.add("ContactId");

            List parameters3 = new ArrayList();
            parameters3.add(key); //Secure key
            parameters3.add("ContactGroupAssign");  //What table we are looking in
            parameters3.add(new Integer(50)); //How many records to return
            parameters3.add(new Integer(0)); //Which page of results to display
            parameters3.add("GroupId"); //The field we are querying on
            parameters3.add(new Integer(groupId)); //THe data to query on
            parameters3.add(fields); //what fields to select on return

            //Make call - the result is an array of structs
            Object[] contacts = (Object[]) client.execute("DataService.findByField", parameters3);

            //Loop through results
            for (int i = 0; i < contacts.length; i++) {
                //Each item in the array is a struct
                Map contact = (Map) contacts[i];
          

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
