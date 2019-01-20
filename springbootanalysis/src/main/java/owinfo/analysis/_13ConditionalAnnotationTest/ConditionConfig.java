package owinfo.analysis._13ConditionalAnnotationTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;

@Configuration
public class ConditionConfig {

    @Conditional(WindowsConfig.class)
    @Bean
    public ArrayList arrayList() {
        return new ArrayList();
    }

    @Conditional(LinuxConfig.class)
    @Bean
    public LinkedList linkedList() {
        return new LinkedList();
    }
}
