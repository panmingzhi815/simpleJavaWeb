package org.opensource.webapp.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 使对象可以进行安全序列化,将一些懒加载的元素设置默认值
 * Created by panmingzhi815 on 14-4-15.
 */
public class LazySerializationUtil {

    /**
     * 使对象可安全序列化
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T serializeBean(T t) {
        return serializeBean(t, new ArrayList<>());
    }

    /**
     * 使集合可安全序列化
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> Collection<T> serializeCollections(Collection<T> collection) {
        if (collection == null) return collection;
        for (T t : collection) {
            serializeBean(t);
        }
        return collection;
    }

    private static <T> T serializeBean(T t, List<Object> unserilizeList) {
        if (t == null) return null;
        if (unserilizeList.contains(t)) return null;
        //  获取类中所有属性
        List<Field> fieldList = getClassAllFieldList(t.getClass());
        for (Field field : fieldList) {
            Class<?> fieldType = field.getType();
            Method getMethod = null;
            Method setMethod = null;
            try {
                //属性名称,并大写首字母
                String name = capitalize(field.getName());
                getMethod = t.getClass().getDeclaredMethod("get" + name, null);
                setMethod = t.getClass().getDeclaredMethod("set" + name, fieldType);

                if (fieldType.isMemberClass()) {
                    Object obj = getMethod.invoke(t, null);
                    obj.toString();
                    serializeBean(obj);
                    continue;
                }

                if (fieldType.isAssignableFrom(Set.class) || fieldType.isAssignableFrom(List.class)) {
                    Object obj = getMethod.invoke(t, null);
                    Collection collection = (Collection) obj;
                    Iterator iterator = collection.iterator();
                    while (iterator.hasNext()) {
                        serializeBean(iterator.next());
                    }
                    continue;
                }
            } catch (Exception e) {
                if (setMethod != null) {
                    try {
                        if (fieldType.isMemberClass()) {
                            setMethod.invoke(t, null);
                            continue;
                        }
                        if (fieldType.isAssignableFrom(Set.class)) {
                            setMethod.invoke(t, Collections.EMPTY_SET);
                            continue;
                        }
                        if (fieldType.isAssignableFrom(List.class)) {
                            setMethod.invoke(t, Collections.EMPTY_LIST);
                            continue;
                        }
                    } catch (Exception e1) {
                    }
                }
            }
        }
        unserilizeList.add(t);
        return t;
    }

    private static List<Field> getClassAllFieldList(Class<?> clz) {
        List<Field> resultList = new ArrayList<Field>();
        Class<?> aClass = clz;
        while (aClass.getDeclaredFields().length != 0) {
            resultList.addAll(Arrays.asList(aClass.getDeclaredFields()));
            aClass = aClass.getSuperclass();
        }
        return resultList;
    }

    private static String capitalize(String s) {
        if (s.length() == 1 || s.length() == 0) return s.toUpperCase();
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
