/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.sb.dao.AdminDao;
import cc.altius.sb.framework.ApplicationSession;
import cc.altius.sb.model.BusinessFunction;
import cc.altius.sb.model.Role;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author akil
 */
@Repository
public class AdminDaoImpl implements AdminDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int addRole(Role role) {
        SimpleJdbcInsert si = new SimpleJdbcInsert(jdbcTemplate).withTableName("us_role");
        Map<String, Object> params = new HashMap<>();
        params.put("ROLE_ID", role.getRoleId());
        params.put("ROLE_NAME", role.getRoleName());
        int rows1 = si.execute(params);
        si = new SimpleJdbcInsert(jdbcTemplate).withTableName("us_role_business_function");
        SqlParameterSource[] paramList = new SqlParameterSource[role.getBusinessFunctions().size()];
        int i = 0;
        for (BusinessFunction bf : role.getBusinessFunctions()) {
            params = new HashMap<>();
            params.put("ROLE_ID", role.getRoleId());
            params.put("BUSINESS_FUNCTION_ID", bf.getBusinessFunctionId());
            paramList[i] = new MapSqlParameterSource(params);
            i++;
        }
        int result[] = si.executeBatch(paramList);
        ApplicationSession.getCurrent().reloadBusinessFunctionList();
        ApplicationSession.getCurrent().reloadRoleList();
        return (rows1 == 1 && result.length > 0 ? 1 : 0);
    }

    @Override
    public int updateRole(Role role) {
        Map<String, Object> params = new HashMap<>();
        params.put("ROLE_ID", role.getRoleId());
        params.put("ROLE_NAME", role.getRoleName());
        this.jdbcTemplate.update("UPDATE us_role r SET r.ROLE_NAME=? WHERE ROLE_ID=?", role.getRoleName(), role.getRoleId());
        this.jdbcTemplate.update("DELETE rbf.* FROM us_role_business_function rbf where rbf.ROLE_ID=?", role.getRoleId());
        SimpleJdbcInsert si = new SimpleJdbcInsert(jdbcTemplate).withTableName("us_role_business_function");
        SqlParameterSource[] paramList = new SqlParameterSource[role.getBusinessFunctions().size()];
        int i = 0;
        for (BusinessFunction bf : role.getBusinessFunctions()) {
            params = new HashMap<>();
            params.put("ROLE_ID", role.getRoleId());
            params.put("BUSINESS_FUNCTION_ID", bf.getBusinessFunctionId());
            paramList[i] = new MapSqlParameterSource(params);
            i++;
        }
        si.executeBatch(paramList);
        ApplicationSession.getCurrent().reloadBusinessFunctionList();
        ApplicationSession.getCurrent().reloadRoleList();
        return 1;
    }

}
