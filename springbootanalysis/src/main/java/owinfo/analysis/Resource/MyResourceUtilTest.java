package owinfo.analysis.Resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Resource接口测试
 * @see org.springframework.core.io.Resource
 * @see org.springframework.core.io.AbstractFileResolvingResource
 */
public class MyResourceUtilTest {

    /**
     * 判断是否是URL
     *
     * @param resourceLocation
     * @return
     */
    public boolean isUrl(@Nullable String resourceLocation) {
        if (resourceLocation == null) {
            return false;
        } else if (resourceLocation.startsWith("classpath:")) {
            return true;
        } else {
            try {
                new URL(resourceLocation);
                return true;
            } catch (MalformedURLException e) {
                return false;
            }
        }
    }

    /**
     * 获取资源文件
     *
     * @param resourceLocation
     * @return
     */
    public URL getUrl(@Nullable String resourceLocation) throws FileNotFoundException {
        Assert.notNull(resourceLocation, "Resource location can not be null");
        if (resourceLocation.startsWith("classpath:")) {
            String path = resourceLocation.substring("classpath:".length());
            return ClassUtils.getDefaultClassLoader().getResource(path);
        } else {
            try {
                return new URL(resourceLocation);
            } catch (MalformedURLException e) {
                try {
                    return (new File(resourceLocation)).toURI().toURL();
                } catch (MalformedURLException e1) {
                    throw new FileNotFoundException("Resource can not be found");
                }
            }
        }
    }

    /**
     * 测试一下Resource接口体系
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("spring.factories");
        Properties properties = new Properties();
        properties.load(classPathResource.getInputStream());
        Enumeration<?> keys = properties.keys();
        while (keys.hasMoreElements()) {
            String var1 = (String) keys.nextElement();
            System.out.println(var1 + "=>" + properties.get(var1));
        }

        /**
         * springboot中的classloader往往提供了获取类路径资源文件不错的方法, 向下面这样
         */
        URL url = Thread.currentThread().getContextClassLoader().getResource("spring.factories");
        UrlResource urlResource = new UrlResource(url);
        InputStream inputStream = urlResource.getInputStream();
        properties.load(inputStream);

        /**
         * URL和File是核心操作类
         */
        File file = new File("classspring.factories");
        System.out.println(file.getAbsolutePath());
    }
}
