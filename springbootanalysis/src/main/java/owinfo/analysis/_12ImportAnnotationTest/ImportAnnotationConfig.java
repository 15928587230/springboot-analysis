package owinfo.analysis._12ImportAnnotationTest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;

/**
 * Import注入Bean到容器中的三种方式
 */
@Import({ArrayList.class, ImportSelectorConfig.class, ImportRegistrarConfig.class})
@Configuration
public class ImportAnnotationConfig {
}
