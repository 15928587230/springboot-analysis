package owinfo.analysis._13ConditionalAnnotationTest;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionalTest {

	/**
	 * @Conditional 注解测试
	 */
	@Test
	public void conditionalTest() {
		AnnotationConfigApplicationContext context
				= new AnnotationConfigApplicationContext(ConditionConfig.class);
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println("name: " + name + ", instance: " + context.getBean(name));
		}
	}
}
