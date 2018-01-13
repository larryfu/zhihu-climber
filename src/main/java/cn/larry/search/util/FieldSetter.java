package cn.larry.search.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;


/**
 * @author gzfu
 */
public class FieldSetter {


    private FieldSetter() {
    }

    /**
     * 对于hibernate延迟加载的对象，必须手动传递class对象，使用setFields(Object obj, Map<String, String> pm,Class objectClass)方法
     * 使用否则由于hibernate的代理机制，无法正确获取对象的class，导致无法设值
     *
     * @param obj
     * @param pm
     * @return
     */

    public static Object setFields(Object obj, Map<String, String> pm) {
        return setFields(obj, pm, null, null);
    }

    /**
     * @param obj
     * @param pm
     * @param objectClass 对于hibernate延迟加载的对象，必须手动传递class对象，否则由于hibernate的代理机制，
     *                    无法正确获取对象的class，导致无法设值
     * @return
     */
    public static Object setFields(Object obj, Map<String, String> pm, Class objectClass) {
        return setFields(obj, pm, null, objectClass);
    }

    public static Object setFields(Object obj, Map<String, String> pm, String[] keepFiled) {
        return setFields(obj, pm, keepFiled, null);
    }

    public static <T> T getField(Object obj, String fieldName) {
        try {
            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(fieldName))
                    return (T) field.get(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object setFields(Object obj, Map<String, String> pm, String[] keepFiled, Class objectClass) {
        if (obj == null || pm == null)
            return obj;
        Class<? extends Object> cl = objectClass == null ? obj.getClass() : objectClass;
        Field[] fields = cl.getDeclaredFields();
        if (keepFiled == null)
            keepFiled = new String[0];

        List<String> keepList = new ArrayList(Arrays.asList(keepFiled));
        keepList.add("id");
        for (int j = 0; j < fields.length; j++) {
            fields[j].setAccessible(true);
            try {
                String fieldName = fields[j].getName();
                if (!keepList.contains(fieldName)) {

                    Class<?> type = fields[j].getType();
                    char[] fieldChar = fieldName.toCharArray();
                    if (fieldChar[0] > 96 && fieldChar[0] < 123)
                        fieldChar[0] -= 32;

                    String setMethod = "set" + new String(fieldChar);

                    //logger.info("set method" + setMethod + ", fieldtype:"+type.getName());
                    //对于枚举字段，调用对象的String参数的枚举setter方法
                    Method me = cl.getMethod(setMethod, type);
                    if (type.isEnum())
                        me = cl.getMethod(setMethod, String.class);
                    String value = pm.get(fieldName);

                    if (value != null) {
                        if (type.equals(String.class) || type.isEnum())
                            me.invoke(obj, getString(value));

                        if ((type.equals(int.class) || type.equals(Integer.class)) && isNotBlank(value))
                            me.invoke(obj, getInt(value));

                        if (type.equals(Date.class) && isNotBlank(value))
                            me.invoke(obj, TimeUtils.getDate(value));

                        if (type.equals(long.class) && isNotBlank(value))
                            me.invoke(obj, getLong(value));

                        if (type.equals(float.class) && isNotBlank(value))
                            me.invoke(obj, getFloat(value));

                        if (type.equals(double.class) && isNotBlank(value))
                            me.invoke(obj, getDouble(value));

                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new RuntimeException("number fromat failed:" + e.getMessage());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * 去除参数中的script style img a 标签，防止脚本注入
     *
     * @param value
     * @return
     */
    private static String getString(String value) {
        return value.replaceAll("(?i)</?\\s*(script|style|link|img|a)[^>]*>", "");
    }

    static int getInt(String value) {
        return Integer.parseInt(value);
    }

    static long getLong(String value) {
        return Long.parseLong(value);
    }

    static float getFloat(String value) {
        return Float.parseFloat(value);
    }

    static double getDouble(String value) {
        return Double.parseDouble(value);
    }


}
