package org.opensource.webapp.framework.util;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 使对象可以进行安全序列化,将一些懒加载的元素设置默认值
 * Created by panmingzhi815 on 14-4-15.
 */
public class LazySerializationUtil {

    private final static Map<Class<?>,List<Field>> classFieldListMap = new HashMap();

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
     * 使Set集合可安全序列化
     *
     * @param set
     * @param <T>
     * @return
     */
    public static <T> Set<T> serializeSet(Set<T> set) {
        if (set == null){
            return null;
        }

        for (T t : set) {
            serializeBean(t);
        }
        return set;
    }

    /**
     * 使List集合可安全序列化
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> serializeList(List<T> list) {
        if (list == null){
            return null;
        }

        for (T t : list) {
            serializeBean(t);
        }
        return list;
    }

    /**
     * 使Collection集合可安全序列化
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> Collection<T> serializeCollection(Collection<T> collection) {
        if (collection == null){
            return null;
        }

        for (T t : collection) {
            serializeBean(t);
        }
        return collection;
    }

    private static <T> T serializeBean(T t, List<Object> unserilizeList) {
        if (t == null){
            return null;
        }

        if (unserilizeList.contains(t)){
            return null;
        }
        //  获取类中所有属性
        List<Field> fieldList = getClassAllFieldList(t.getClass());
        for (Field field : fieldList) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();

            try {
                if (Arrays.asList(fieldType.getAnnotations()).contains(Entity.class)) {
                    Object obj = field.get(t);
                    obj.toString();
                    serializeBean(obj);
                    continue;
                }

                if (fieldType == List.class || fieldType == Set.class) {
                    Object obj = field.get(t);
                    Collection collection = (Collection) obj;
                    Iterator iterator = collection.iterator();
                    while (iterator.hasNext()) {
                        serializeBean(iterator.next());
                    }
                    continue;
                }
            } catch (Exception e) {
                try {
                    if (Arrays.asList(fieldType.getAnnotations()).contains(Entity.class)) {
                        field.set(t,null);
                        continue;
                    }
                    if (fieldType.isAssignableFrom(Set.class)) {
                        field.set(t,Collections.EMPTY_SET);
                        continue;
                    }
                    if (fieldType.isAssignableFrom(List.class)) {
                        field.set(t, Collections.EMPTY_LIST);
                        continue;
                    }
                } catch (Exception e1) {
                }
            }
        }
        unserilizeList.add(t);
        return t;
    }

    private static List<Field> getClassAllFieldList(Class<?> clz) {
        List<Field> resultList = classFieldListMap.get(clz);
        if(resultList != null){
            return resultList;
        }
        resultList = new ArrayList<>();
        Class<?> aClass = clz;
        while (aClass != Object.class) {
            resultList.addAll(Arrays.asList(aClass.getDeclaredFields()));
            aClass = aClass.getSuperclass();
        }
        return resultList;
    }

}
