/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.utils;

import cc.altius.maven.AllAltiusUtil;
import cc.altius.sb.model.CustomUserDetails;
//import cc.altius.utils.StringUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 *
 * @author akil
 */
public class LogUtils {
 @Autowired
//    public static Logger systemLogger = Logger.getLogger("systemLogging");
//    public static Logger debugLogger = Logger.getLogger("debugLogging");

     public static String buildStringForSystemLog(Exception e) {
        StringWriter sWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(sWriter));
        return (new StringBuilder(getIpAddress()).append(" -- ").append(getUsername()).append("(").append(getUserId()).append(") -- ").append(sWriter.toString()).toString());
    }

    public static String buildStringForSystemLog(String sqlString, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder(getIpAddress()).append(" -- ").append(getUsername()).append("(").append(getUserId()).append(") -- ").append(sqlString).append(" -- parameters(");
        boolean firstRun = true;
        for (Map.Entry tmpEntry : params.entrySet()) {
            if (firstRun) {
                firstRun = false;
                sb.append(tmpEntry.getKey()).append(":").append(tmpEntry.getValue());
            } else {
                sb.append(", ").append(tmpEntry.getKey()).append(":").append(tmpEntry.getValue());
            }
        }
        sb.append(")");
        return (sb.toString());
    }

    public static String buildStringForSystemLog(String sqlString, Object[] params) {
        StringBuilder sb = new StringBuilder(getIpAddress()).append(" -- ").append(getUsername()).append("(").append(getUserId()).append(") -- ").append(sqlString).append(" -- parameters(");
        boolean firstRun = true;
        for (Object tmpParam : params) {
            if (firstRun) {
                firstRun = false;
                sb.append(tmpParam);
            } else {
                sb.append(", ").append(tmpParam);
            }
        }
        sb.append(")");
        return (sb.toString());
    }

    public static String buildStringForSystemLog(String sqlString, List<Object[]> paramList) {
        StringBuilder sb = new StringBuilder(getIpAddress()).append(" -- ").append(getUsername()).append("(").append(getUserId()).append(") -- ").append(sqlString).append(" -- parameters( ");
        boolean firstRun = true;
        for (Object params[] : paramList) {
            sb.append(" (");
            firstRun = true;
            for (Object tmpParam : params) {
                if (firstRun) {
                    firstRun = false;
                    sb.append(tmpParam);
                } else {
                    sb.append(", ").append(tmpParam);
                }
            }
            sb.append(")");
        }
        sb.append(")");
        return (sb.toString());
    }

    public static String buildStringForSystemLog(String msg) {
        StringBuilder sb = new StringBuilder(getIpAddress()).append(" -- ").append(getUsername()).append("(").append(getUserId()).append(") -- ").append(msg);
        return sb.toString();
    }

    public static String getArgsString() {
        return " IP:{} User:{}";
    }

    public static String buildStringForLog(String sqlString, Object[] params) {
        StringBuilder sb = new StringBuilder(AllAltiusUtil.escapeQuotes(sqlString)).append(" -- parameters(");
        boolean firstRun = true;
        for (Object tmpParam : params) {
            if (firstRun) {
                firstRun = false;
                sb.append(AllAltiusUtil.escapeQuotes(tmpParam.toString()));
            } else {
                sb.append(", ").append(AllAltiusUtil.escapeQuotes(tmpParam.toString()));
            }
        }
        return sb.toString();
    }

    public static String buildStringForLog(String sqlString, List<Object[]> paramList) {
        StringBuilder sb = new StringBuilder(AllAltiusUtil.escapeQuotes(sqlString)).append(" -- parameters(");
        boolean firstRun = true;
        for (Object params[] : paramList) {
            sb.append(" (");
            for (Object tmpParam : params) {
                if (firstRun) {
                    firstRun = false;
                    sb.append(AllAltiusUtil.escapeQuotes(tmpParam.toString()));
                } else {
                    sb.append(", ").append(AllAltiusUtil.escapeQuotes(tmpParam.toString()));
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public static String buildStringForLog(String sqlString, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder(AllAltiusUtil.escapeQuotes(sqlString)).append(" -- parameters(");
        boolean firstRun = true;
        for (Map.Entry tmpEntry : params.entrySet()) {
            if (firstRun) {
                firstRun = false;
                sb.append(tmpEntry.getKey()).append(":").append(tmpEntry.getValue());
            } else {
                sb.append(", ").append(tmpEntry.getKey()).append(":").append(tmpEntry.getValue());
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static String getIpAddress() {
        try {
            return ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getRemoteAddress();
        } catch (NullPointerException n) {
            return "0.0.0.0";
        }
    }

    public static String getUsername() {
        try {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass() == CustomUserDetails.class) {
                return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            } else {
                return "";
            }
        } catch (NullPointerException n) {
            return "blank";
        }
    }
      private static int getUserId() {
        try {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass() == CustomUserDetails.class) {
                return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
            } else {
                return 0;
            }
        } catch (NullPointerException n) {
            return 0;
        }
    }
}
