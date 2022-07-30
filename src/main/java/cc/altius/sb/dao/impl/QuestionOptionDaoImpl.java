/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.dao.impl;

import cc.altius.maven.AllAltiusUtil;
import cc.altius.sb.model.rowmapper.QuestionOptionsListRowMapper;
import cc.altius.sb.dao.QuestionOptionDao;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.QuestionOption;
import cc.altius.sb.utils.LogUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
//import cc.altius.utils.DateUtils;

/**
 *
 * @author altius
 */
@Repository

public class QuestionOptionDaoImpl implements QuestionOptionDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int addQuestionOption(QuestionOption questionOption) {
        int curUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        SimpleJdbcInsert userInsert = new SimpleJdbcInsert(this.dataSource).withTableName("question_option").usingGeneratedKeyColumns("QUESTION_OPTION_ID");
        Map<String, Object> params = new HashMap<>();
        params.put("QUESTION_OPTION_NAME", questionOption.getQuestionOptionName());
        params.put("QUESTION_VALUE", questionOption.getQuestionValue());
        params.put("LABEL_VALUE", questionOption.getLabelValue());
        params.put("LABEL_POSITION", 0);
        params.put("SORT_ORDER", questionOption.getSortOrder());
        params.put("ACTIVE", questionOption.isActive());
        Date dt = AllAltiusUtil.getCurrentDateObject(AllAltiusUtil.EST);
        params.put("CREATED_BY", curUser);
        params.put("CREATED_DATE", dt);
        params.put("LAST_MODIFIED_BY", curUser);
        params.put("LAST_MODIFIED_DATE", dt);
        int qOId = userInsert.executeAndReturnKey(params).intValue();
        return qOId;
    }

    @Override
    public List<QuestionOption> getQuestionOptionsList() {
        return this.jdbcTemplate.query("SELECT question_option.* FROM question_option ORDER BY question_option.`QUESTION_OPTION_ID`", new QuestionOptionsListRowMapper());
    }

    @Override
    public QuestionOption getQuestionOptionByquestionOptionId(int questionOptionId) {
        String sql = "SELECT question_option.`QUESTION_OPTION_ID`,question_option.`QUESTION_OPTION_NAME`,question_option.`QUESTION_VALUE`,question_option.`SORT_ORDER`, "
                + "question_option.`ACTIVE`,question_option.`LABEL_VALUE`,"
                + "question_option.`LABEL_POSITION` "
                + "FROM question_option "
                + "WHERE question_option.`QUESTION_OPTION_ID`=?";
        Object params[] = new Object[]{questionOptionId};
        try {
            logger.info(LogUtils.buildStringForSystemLog(sql, params));
            return this.jdbcTemplate.queryForObject(sql, params, new QuestionOptionsListRowMapper());
        } catch (EmptyResultDataAccessException erda) {
            logger.info(LogUtils.buildStringForSystemLog("No User found with username"));
            return null;
        }
    }

    @Override
    public int updateQuestionOption(QuestionOption questionOption) {
        Object params[];
        String sql = "UPDATE `question_option` SET `QUESTION_OPTION_NAME`=?,`QUESTION_VALUE`=?,`LABEL_VALUE`=?,`SORT_ORDER`=?,`ACTIVE`=? WHERE `QUESTION_OPTION_ID`=?";
        params = new Object[]{questionOption.getQuestionOptionName(), questionOption.getQuestionValue(), questionOption.getLabelValue(), questionOption.getSortOrder(), questionOption.isActive(), questionOption.getQuestionOptionId()};
        int i = this.jdbcTemplate.update(sql, params);
        return i;
    }

}
