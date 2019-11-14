package owinfo.analysis._6ReflectGeneric;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class 类的反射方法测试 Field、Method、Class、Annotation等等
 *
 * @author thinkpad
 */
@Reflects(reflect = {
		@Reflect(desc = "注解反射测试"),
		@Reflect
})
public class ReflectTest extends ReflectParentTest implements ReflectParentInterface {

	/**
	 * 两个构造方法
	 */
	public ReflectTest() {
	}

	private ReflectTest(String value, Map<String, Integer> map) {
		this.reflectEntry = map;
		this.reflectName = value;
	}

	public Map<String, List> reflectMap(Map<String, Integer> map) {
		return new HashMap<>();
	}

	public class ReflectInner1Class {
	}

	private class ReflectInner2Class {
	}

	class ReflectInner3Class {
	}


	private Map<String, Integer> reflectEntry;

	public String reflectName;

	public static void main(String[] args) throws Exception {

		Class clazz = ReflectTest.class;
		/**
		 *  构造器相关
		 *  getConstructors() 获取元素共有的所有构造器类型数组
		 *  declaredConstructors() 获取元素私有、共有的所有构造器类型数组
		 *  getDeclaredConstructor(Class<T>... classes) 获取私有或者共有的指定构造器
		 *  getConstructor(Class<T>... classes)获取共有的指定构造器
		 */
		Constructor declaredConstructor = clazz.getDeclaredConstructor(new Class[]{String.class, Map.class});
		/**
		 *  如果构造器类型是共有的，该方法也可以获取
		 */
		if (Modifier.isPublic(clazz.getDeclaredConstructor(new Class[]{String.class, Map.class}).getModifiers())) {
			Constructor constructor = clazz.getConstructor(new Class[]{String.class, Map.class});
		}
		Constructor[] constructors = clazz.getConstructors();
		Constructor[] declaredConstructors = clazz.getDeclaredConstructors();

		/**
		 *  注解相关
		 *  getAnnotatedInterfaces() 获取父接口类型数组
		 *  getAnnotatedSuperclass() 获取父类类型数组
		 *  getAnnotations() 获取元素上所有的注解，包括从父类继承的注解
		 *  getDeclaredAnnotations() 获取元素上所有的注解，不包括从父类继承的注解
		 *  isAnnotationPresent(Class<T> annotationClass) 元素上是否存在指定注解类型的注解（包括继承的注解）
		 *  isAnnotation() 判断该类型是否是一个注解
		 */
		AnnotatedType[] annotatedInterfaces = clazz.getAnnotatedInterfaces();
		AnnotatedType annotatedSuperclass = clazz.getAnnotatedSuperclass();
		Annotation[] annotations = clazz.getAnnotations();
		Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
		boolean annotationPresent = clazz.isAnnotationPresent(Reflect.class);
		boolean isAnnotation = Reflect.class.isAnnotation();
		Annotation declaredAnnotation = clazz.getDeclaredAnnotation(Reflect.class);
		Annotation annotation = clazz.getAnnotation(Reflect.class);
		Annotation[] annotationsByType = clazz.getAnnotationsByType(Reflect.class);


		/**
		 *  内部类相关
		 *  getClasses() 获取所有的public内部类(包括父类的public内部类)
		 *  getDeclaredClasses() 获取所有的内部类
		 *  getComponentType()返回数组组件的类型，不是数组则返回null
		 *  getGenericInterfaces() 获取类接口
		 *  getGenericSuperclass() 获取类父类
		 */
		Class[] classes = clazz.getClasses();
		Class[] declaredClasses = clazz.getDeclaredClasses();
		Class componentType = clazz.getComponentType(); // null
		Class<?> type = ReflectParentTest[].class.getComponentType(); //ReflectParentTest 类型
		Type[] genericInterfaces = clazz.getGenericInterfaces();
		Type genericSuperclass = clazz.getGenericSuperclass();

		/**
		 *  字段相关
		 *  getFields 获取当前类所有的公共字段，包括父类继承的字段(当然public可以被继承)
		 */
		Field[] fields = clazz.getFields();

		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			System.out.println(field.getName());
			System.out.println(field.getGenericType());
			System.out.println(field.getType());
			System.out.println(field.getModifiers());
		}

		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			if ("reflectMap".equals(method.getName())) {
				// 方法参数类型
				Type[] genericParameterTypes = method.getGenericParameterTypes();
				for (Type type1 : genericParameterTypes) {
					System.out.println(type1.getTypeName());
				}

				TypeVariable<Method>[] typeParameters = method.getTypeParameters();
				for (TypeVariable typeVariable : typeParameters) {
					System.out.println(typeVariable.getName());
				}
			}
		}
	}
}
