package tobyspring.helloboot;

public interface HelloService {
    String sayHello(String name);

    default  int countOf(String count) {
        return 0;
    };
}
