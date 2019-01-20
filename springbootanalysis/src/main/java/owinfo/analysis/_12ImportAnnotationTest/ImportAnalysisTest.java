package owinfo.analysis._12ImportAnnotationTest;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportAnalysisTest {

    /**
     * 测试Import注解的功能
     */
    @Test
    public void testImport() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ImportAnnotationConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name: beanDefinitionNames) {
                System.out.println("beanName: " + name + ", instance: " + context.getBean(name));
            }
        }
}
