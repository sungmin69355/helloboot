package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // 동작원리 -> DispatcherServlet이 applicationContext 생성자로 받아서 만들었다.
    // bean을 뒤지면서 웹 요청을 할 수 있는 Mapping 정보를 찾는다. ex) GetMapping, PostMapping ...
    // 메핑에 사용할 테이블을 하나 만들어놓는다. -> 그 후 요청이 들어오면 참고해서 Bean object와 메서드를 확인
    // DispatcherServlet은 매핑정보를 만들때 class -> method 순으로 찾는다.
    @GetMapping("/hello")
    @ResponseBody //@RestController에는 해당어노테이션이 생략되어있다.
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
