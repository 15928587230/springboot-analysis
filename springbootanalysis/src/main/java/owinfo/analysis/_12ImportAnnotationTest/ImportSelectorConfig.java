package owinfo.analysis._12ImportAnnotationTest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 返回String[]会注入容器中，beanName为包名.类名
 */
public class ImportSelectorConfig implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"java.util.LinkedList"};
	}
}
