package com.wt.overflow.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class ObjectUtil {

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 */
	public static Map<String, Object> convertBean(Object bean){
		Class type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnMap;
		}
		return returnMap;
	}

	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 */
	public static Object convertMap(Class type, Map map){
		BeanInfo beanInfo = null ;
		Object obj = new Object();
		try {
			Introspector.getBeanInfo(type); // 获取类属性
			type.newInstance(); // 创建 JavaBean 对象
			// 给 JavaBean 对象的属性赋值
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();

				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					Object value = map.get(propertyName);

					Object[] args = new Object[1];
					args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return obj;
		}
		
		return obj;
	}

	/**
	 * 判断 实体类对象所有属性是否全部为空
	 * @param object
	 *            实体类对象
	 * @return
	 */
	public static boolean judgeObjectIsEmpty(Object object) {
		if (object == null)
			return false;
		JSONObject json = (JSONObject) JSONObject.toJSON(object);

		for (Map.Entry<String, Object> entry : json.entrySet()) {

			if (entry.getValue() != null) {
				if (!StringUtils.isEmpty(entry.getValue().toString())) {

					return true;
				}
			}

		}
		return false;
	}
}
