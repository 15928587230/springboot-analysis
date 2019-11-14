package owinfo.analysis._12ImportAnnotationTest;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashMap;

/**
 * 手动注入Bean并指定名字
 */
public class ImportRegistrarConfig implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
	                                    BeanDefinitionRegistry registry) {
		registry.registerBeanDefinition("hashMap", new RootBeanDefinition(HashMap.class));
	}
}
