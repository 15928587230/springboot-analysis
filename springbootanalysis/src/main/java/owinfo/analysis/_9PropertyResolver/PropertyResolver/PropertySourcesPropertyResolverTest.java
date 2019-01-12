package owinfo.analysis._9PropertyResolver.PropertyResolver;

import org.springframework.boot.ansi.AnsiPropertySource;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link AbstractPropertyResolver}
 * {@link PropertySourcesPropertyResolver}
 */
public class PropertySourcesPropertyResolverTest {

    private static MutablePropertySources sources = new MutablePropertySources();

    private static Map<String, Object> systemProperties = new HashMap<>();

    // 初始化PropertySource
    static {
        systemProperties.put("name", "pjj");
        systemProperties.put("age", 23);
        systemProperties.put("sex", "male");
        systemProperties.put("pjjName", "${name}");

        sources.addFirst(new AnsiPropertySource("AnsiProperties", false));
        sources.addFirst(new SimpleCommandLinePropertySource("--spring.profiles.active=dev", "-DXss=20m"));
        sources.addFirst(new SystemEnvironmentPropertySource("systemProperties", systemProperties));
    }

    /**
     * PropertySourcesPropertyResolver成员变量 MutablePropertySources(存储各种类型的属性)
     * PropertySourcesPropertyResolver成员变量 主要提供一系列获取属性值得方法
     *
     * @param args
     */
    public static void main(String[] args) {

        PropertySourcesPropertyResolver resolver = new PropertySourcesPropertyResolver(sources);
        System.out.println(resolver.getProperty("name"));
        System.out.println(resolver.getProperty("spring.profiles.active"));
        System.out.println(resolver.getProperty("AnsiColor.RED"));

        /**
         *  getProperty(String)默认获取的值为String类型
         *  getProperty(String,Class<?>)可以将获取的值转换成指定的类型。
         *      如果获取到的值是String类型，还可以进行占位符解析。比如获取到的值为${pjjName}
         *  (类型转换方法全在父类AbstractPropertySource中实现)
         *  (类型转换具体转换方法在ConversionService接口中定义) 会单独建一个目录分析ConversionService接口
         */
        String pjjName = resolver.getProperty("pjjName", String.class);
        System.out.println(pjjName); //${name} = pjj

    }
}
