package tobyspring.helloboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest { }

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD}) //ElementType.ANNOTATION_TYPE이 있어야 메타 어노테이션으로 활용가능
@Test
@interface UnitTest { }

public class HelloServiceTest {
    @UnitTest
    void simpleHelloService() {
        SimpleHelloService simpleHelloService = new SimpleHelloService(helloRepositoryStub);

        String ret = simpleHelloService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("HelloTest");
    }

    private static HelloRepository helloRepositoryStub = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }

        @Override
        public void increaseCount(String name) {

        }
    };

    @FastUnitTest
    void helloDecorator() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        String ret = helloDecorator.sayHello("test");

        Assertions.assertThat(ret).isEqualTo("*test*");
    }
}
