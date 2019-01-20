package owinfo.analysis._11ImportAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * IOC初始化流程，beanFactoryPostProcessor工厂后置器执行ConfigurationClassPostProcessor扫描解析相关注解
 * @see org.springframework.context.annotation.ConfigurationClassParser
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor
 * @see org.springframework.beans.factory.config.BeanDefinition
 * @see org.springframework.beans.factory.BeanFactory
 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor
 * @see org.springframework.beans.factory.config.BeanPostProcessor
 */
@EnableScheduling
@Import(User.class)
@Component
public class ImportTest implements CommandLineRunner {

    @Resource
    private User user;

    @Autowired
    private DefaultListableBeanFactory beanFactory;

    @Override
    public void run(String... args) {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanName: beanDefinitionNames) {
            System.out.println(beanName);
        }

        User bean = beanFactory.getBean("owinfo.analysis._11ImportAnnotation.User", User.class);
        System.out.println(bean);

        System.out.println(user);
    }

    @Scheduled(cron = "* * * * * *")
    public void schedule() {
        System.out.println("呵呵");
    }
}

