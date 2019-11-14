package owinfo.analysis._13ConditionalAnnotationTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;

@Configuration
public class ConditionConfig {

	/**
	 * @Bean 注入容器，beanName是方法名getArrayList
	 */
	@Conditional(WindowsConfig.class)
	@Bean
	public ArrayList getArrayList() {
		return new ArrayList();
	}

	@Conditional(LinuxConfig.class)
	@Bean
	public LinkedList getLinkedList() {
		return new LinkedList();
	}
}
