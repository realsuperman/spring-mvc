package hello.springmvc.basic;

import lombok.Data;

@Data // getter,setter,생성자,toString 등 기본적인 설정 다 만들어줌
public class HelloData {
    private String username;
    private int age;
}
