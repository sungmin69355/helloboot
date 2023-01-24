package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class HellobootApplication {
	public static void main(String[] args) {

		//직접 스프링컨테이너 생성
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); //서블릿 웹서버를 쉽게만들어주는 팩토리
				//익명 함수
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",
							//DispatcherServlet은 웹환경에서 좀더 편하게 사용할 수 있는 하나의 서블릿(WebApplicationContext를 사용해야한다.)
							new DispatcherServlet(this)
					).addMapping("/*");
				});
				webServer.start();

			}
		};
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();
	}

}
