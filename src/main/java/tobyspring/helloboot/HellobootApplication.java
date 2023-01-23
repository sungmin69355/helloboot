package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {

		//직접 스프링컨테이너 생성
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
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
		applicationContext.registerBean(HelloController.class); // bean 등록의 순서는 스프링이 알아서 적절하게 등록해준다.
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();
	}

}
