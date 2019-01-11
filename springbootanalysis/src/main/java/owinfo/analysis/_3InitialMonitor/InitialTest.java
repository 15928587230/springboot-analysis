package owinfo.analysis._3InitialMonitor;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.UrlResource;
import org.springframework.util.ClassUtils;
import org.springframework.util.LinkedMultiValueMap;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

/**
 *  手写Springboot源码，模拟ApplicationContextInitializer和ApplicationListener初始化流程
 * @see owinfo.analysis._2Classloader.MyClassLoader
 * @see owinfo.analysis._2Classloader.MyClassUtilsTest
 * @see org.springframework.core.io.support.SpringFactoriesLoader
 * @see org.springframework.boot.SpringApplication
 */
public class InitialTest {

    /**
     * 模拟SpringbootApplication中需要初始化的listener和initializer
     */
    private static List<ApplicationContextInitializer> initializers= new ArrayList<>();
    private static List<ApplicationListener> listeners = new ArrayList<>();

    private static final String initializerName = "org.springframework.context.ApplicationContextInitializer";
    private static final String listenerName = "org.springframework.context.ApplicationListener";

    public static void makeAccssible(Constructor cort) {
        if (!Modifier.isPublic(cort.getModifiers()) || !cort.isAccessible()) {
            /**
             * 破解私有构造器，提高性能
             */
            cort.setAccessible(true);
        }
    }

    public static void main(String[] args) {

        try {
            LinkedMultiValueMap multiValueMap = new LinkedMultiValueMap();
            /**
             * 加载配置文件
             */
            ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
            Enumeration<URL> urlEnumeration = defaultClassLoader.getResources("META-INF/spring.factories");
            while (urlEnumeration.hasMoreElements()) {
                URL url = urlEnumeration.nextElement();
                /**
                 * 打印加载文件的路径，文件在spring-boot-2.1.0这个jar包下面
                 * 还有spring-boot-autoconfigure-2.1.0这个jar包下面
                 */
                System.out.println(url);
                UrlResource urlResource = new UrlResource(url);
                Properties properties = new Properties();
                properties.load(urlResource.getInputStream());

                /**
                 * 解析配置文件，放入到LinkedMultiValueMap，固定的是factoryClassName
                 * 配置文件内容格式也是固定的，通常一个key和多个用逗号隔开的value比如下面这样
                 * list=\
                 * java.util.ArrayList,\
                 * java.util.LinkedList
                 */
                Iterator iterator = properties.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                    String factoryClassName = entry.getKey();
                    String[] values = entry.getValue().split(",");

                    int var1 = values.length;
                    for (int var2 = 0; var2 < var1; var2++) {
                        String var3 = values[var2];
                        multiValueMap.add(factoryClassName, var3);
                    }
                }
            }

            System.out.println(multiValueMap);
            /**
             * 获取我们需要的初始化器或者监听器的多个 子类的名字集合
             */
            List<String> initializerNames = multiValueMap.get(initializerName);
            List<String> listenerNames = multiValueMap.get(listenerName);

            /**
             * 下面对初始化器和监听器初始化并赋值
             */

            for (String name: initializerNames) {
                Constructor<?> cort = defaultClassLoader.loadClass(name)
                        .getDeclaredConstructor(new Class[0]);
                makeAccssible(cort);
                initializers.add((ApplicationContextInitializer)cort.newInstance());
            }

            for (String name: listenerNames) {
                Constructor<?> cort = defaultClassLoader.loadClass(name)
                        .getDeclaredConstructor(new Class[0]);
                makeAccssible(cort);
                listeners.add((ApplicationListener) cort.newInstance());
            }

            System.out.println("\n 需要加载的初始化器: ");
            for (int i = 0; i < initializers.size(); i++) {
                System.out.println(initializers.get(i));
            }

            System.out.println("需要加载的监听器: ");
            for (int i = 0; i < listeners.size(); i++) {
                System.out.println(listeners.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
