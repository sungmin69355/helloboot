package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                // 아래 코드를 삭제해도 dispatcherServlet이 잘작동된다.
                // 그 이유는 스프링컨테이너가 dispatcherServlet는 ApplicationContext가 필요하다는 것을 인지하고 주입한다.
                // 동작원리는 dispatcherServlet가 구현하고있는 인터페이스 중 ApplicationContextAware 인터페이스에서 setApplicationContext 메서드를 통해서 넣어준다. (빈생성시점)
                dispatcherServlet.setApplicationContext(this);

                //익명 함수
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*");
                });
                webServer.start();

            }
        };
        applicationContext.register(applicationClass);
        applicationContext.refresh();
    }
}
