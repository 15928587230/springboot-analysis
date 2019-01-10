package owinfo.analysis._2Classloader;

import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * ClassLoader测试
 * @see org.springframework.util.ClassUtils
 */
public class MyClassLoader extends ClassLoader {

    private final static String className = "ArrayList";

    public MyClassLoader(){}

    /**
     * 重写该方法加载 util.ArrayList抛出异常
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Assert.notNull(name, "Name can not be null");
        if (name.equals("ArrayList")) {
            name = "util." + name;
            return super.loadClass(name, false);
        } else {
            return null;
        }
    }

    /**
     * 重写该方法，加载java.util.ArrayList
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Assert.notNull(name, "Name can not be null");
        name = "java." + name;
        return ClassUtils.getDefaultClassLoader().loadClass(name);
    }

    public static void main(String[] args) throws IllegalAccessException,
            InstantiationException, ClassNotFoundException {
        ClassLoader classLoader = new MyClassLoader();
        Class<?> arrayList = classLoader.loadClass(className);
        System.out.println(arrayList.newInstance());

        URLClassLoader urlClassLoader = (URLClassLoader) URLClassLoader.getSystemClassLoader();
        System.out.println(Arrays.toString(urlClassLoader.getURLs()));
    }
}
