package org.opensource.webapp.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.LazyInitializationException;
import org.opensource.webapp.framework.domain.BasicDomain;

/**
 * 利用泛型的特点，创建一个新对象，然后把传入的对象赋值到这个新的对象上。方便处理json序列化时的懒加载问题。
 * @author xiaopan
 *
 */
public class MapOutUtil {
	
	@SuppressWarnings("unchecked")
	public static <T> T mapOutObj(T o){
		if(o == null){
			return null;
		}
		
		T newInstance = null;
		try {
			newInstance = (T)o.getClass().getConstructor(null).newInstance();
		} catch (Exception e) {
			return null;
		}
		
		
		Field[] declaredFields = o.getClass().getDeclaredFields();
		
		//处理继承类中的值
		if(o.getClass().getSuperclass() == BasicDomain.class){
			try {
				Method method = BasicDomain.class.getMethod("getId", null);
				Method method2 = BasicDomain.class.getMethod("setId", Long.class);
				method2.invoke(newInstance, method.invoke(o, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (Field field : declaredFields) {
			String name = field.getName();
			String getMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
			String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
			Class<?> returnType = null;
			Method getMethod = null;
			Method setMethod = null;
			try {
				getMethod = o.getClass().getMethod(getMethodName, null);
				returnType = getMethod.getReturnType();
				setMethod = o.getClass().getMethod(setMethodName, returnType);
				Object obj = getMethod.invoke(o, null);
                obj.toString();
                if(returnType.getSuperclass() == BasicDomain.class){
                    obj = mapOutObj(obj);
                }
                if(obj instanceof List){
                    mapOutList((List)obj);
                }
                if(obj instanceof Set){
                    mapOutSet((Set) obj);
                }
				setMethod.invoke(newInstance, obj);
			} catch (Exception e) {
			}
        }
		return newInstance;
	}
	
	public static <T> List<T> mapOutList(List<T> list){
		List<T> returnList = new ArrayList<T>();
		for (T t: list) {
			returnList.add(mapOutObj(t));
		}
		return returnList;
	}

    public static <T> Set<T> mapOutSet(Set<T> set){
        Set<T> returnSet = new HashSet<T>();
        for (T t: set) {
            returnSet.add(mapOutObj(t));
        }
        return returnSet;
    }

} 
