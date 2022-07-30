/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.framework;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * This class has definition of all the constants used across the project. To
 * access these variables from jsp file use following code
 *
 *
 * <jsp:useBean id="GlobalConstants" class="cc.altius.domain.GlobalConstants"
 * scope="application"/> (should be kept in taglibs.jsp)
 *
 *
 * ${GlobalConstants.ORDER_STATUS_NETWORK_COMPLETED} (EL statement to access
 * variables)
 *
 *
 *
 *
 * @author akil
 */
public class GlobalConstants extends HashMap {

    private static Map map = null;
    // General values
    /**
     * sets the default value for ACTIVE=1
     *
     */
    public static int ACTIVE = 1;
    /**
     * sets the default value for DISABLED=0
     *
     */
    public static int DISABLED = 0;
    /**
     * sets the default value for MALE=1
     *
     */
    public static int MALE = 1;
    /**
     * sets the default value for FEMALE=2
     *
     */
    public static int FEMALE = 2;
    /**
     * sets the default value for PAGE_SIZE=100
     *
     */
    public static int PAGE_SIZE = 100;
    public static int SUMMARY_BY_INVOICE_DATE = 1;
    public static int SUMMARY_BY_INVOICE_PERIOD = 2;  
    
    public static int DIMENSION_COVID = 6;
       public static int DIMENSION_AVATAR = 7;
    /*Question Type*/
    public static int QUESTION_TYPE_ID_RADIO = 1;
    public static int QUESTION_TYPE_ID_TEXT_FIELD = 2;
    public static int QUESTION_TYPE_ID_TEXT_AREA = 3;
    public static int QUESTION_TYPE_ID_ORDERED_GROUP = 4;
    public static int QUESTION_TYPE_ID_MULTISELECT = 5;
    public static int QUESTION_TYPE_ID_NUMBER = 6;
    
    /**
     * sets the value for array of ALLOWED_IP_RANGE={"127.0.0.1",
     * "10.1.2.1-10.1.2.254","10.1.3.1-10.1.3.254"}
     *
     */
    public static String[] ALLOWED_IP_RANGE = new String[]{"127.0.0.1", "10.1.2.1-10.1.2.254", "10.1.3.1-10.1.3.254", "203.153.54.224-2013.153.54.239", "115.249.25.1-115.249.25.16"};

    public GlobalConstants() {
        if (map != null) {
            return;
        }
        map = new HashMap();
        Class c = this.getClass();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            int modifier = field.getModifiers();
            if (!field.getName().equals("map") && !Modifier.isPrivate(modifier)) {
                try {
                    this.put(field.getName(), field.get(this));
                } catch (IllegalAccessException e) {
                    //log.error(e);
                }
            }
        }
    }

    public Object get(Object key) {
        return map.get(key);
    }

    public Object put(Object key, Object value) {
        return map.put(key, value);
    }
}
