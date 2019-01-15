package owinfo.analysis._10ServletEnvironment;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.StandardEnvironment;

/**
 * Environment结构 初始化StandardServletEnvironment。
 *  包括activeProfiles、defaultProfiles、converters、propertySources、systemProperties等等
 *
 * 上面涉及到的环境初始化，是用下面的API结构体系来解析完成的
 *
 * @see org.springframework.core.env.Environment
 * @see org.springframework.core.env.ConfigurableEnvironment
 * @see AbstractEnvironment
 * @see org.springframework.web.context.support.StandardServletEnvironment
 * @see org.springframework.core.env.PropertySourcesPropertyResolver
 * @see org.springframework.core.env.AbstractPropertyResolver
 * @see org.springframework.core.env.PropertySources
 * @see org.springframework.boot.autoconfigure.web.servlet.MultipartProperties
 * @see org.springframework.core.env.PropertySource
 * @see org.springframework.core.env.PropertyResolver
 * @author Mr.peng
 */
public class ServletEnvironmentTest {

    public static void main(String[] args) {

        /**
         *  初始化PropertyResolver。systemProperties。systemEnvironment。resolverHelper等等。
         */
        AbstractEnvironment abstractEnvironment = new StandardEnvironment();
        /**
         *  直接初始化ConversionService转换服务，默认添加132个Converter
         */
        abstractEnvironment.setConversionService((ConfigurableConversionService)
                ApplicationConversionService.getSharedInstance());
    }
}
