package tobyspring.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface MyComponent {
    // bean 오브젝트가 어떤 의미인지 커스텀, 명시해줄 때 사용 Ex) 웹 계층인지, controller, service 등등...
    // 메타어노테이션
}
