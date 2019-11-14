package owinfo.analysis._2Classloader;

import java.lang.reflect.InvocationTargetException;

/**
 * ClassLoader测试
 *
 * @see org.springframework.util.ClassUtils
 */
public class MyClassUtilsTest {

	public static Class<?> getClass(ClassLoader classLoader, String className)
			throws ClassNotFoundException {
		if (classLoader == null) {
			/**
			 * 获取当前类的ClassLoader
			 */
			classLoader = MyClassUtilsTest.class.getClassLoader();
			if (classLoader == null) {
				/**
				 * 获取当前线程的ClassLoader
				 */
				classLoader = Thread.currentThread().getContextClassLoader();
				if (classLoader == null) {
					/**
					 *  获取系统默认的ClassLoader
					 */
					classLoader = ClassLoader.getSystemClassLoader();
				}
			}
		}
//        return Class.forName(className, false, classLoader);
		return classLoader.loadClass(className);
	}


	public static void main(String[] args) throws ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

		Class<?> loadClass = getClass(MyClassUtilsTest.class.getClassLoader(), "java.util.ArrayList");
		System.out.println(loadClass.getDeclaredConstructor().newInstance()); //打印空数组 []
	}
}
