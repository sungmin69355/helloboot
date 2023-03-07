package tobyspring.config.autoconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

import java.text.Annotation;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
    @Bean
    BeanPostProcessor propertyPostProcessor(Environment env){
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                MyAConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyAConfigurationProperties.class);
                if(annotation == null) return bean;

                return Binder.get(env).bindOrCreate("", bean.getClass());
            }
        };
    }
}
