package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3") // json객체도 RequestBody가능(메시지 컨버터가  컨텐트 타입을 보고 읽어서 객체에 매핑해줌)
    public String requestBodyJsonV3(@RequestBody HelloData helloData){ // RequestBody 생략 불가함(생략하면 ModelAtrrtibute라고 생각함)
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4") // json객체도 RequestBody가능(메시지 컨버터가  컨텐트 타입을 보고 읽어서 객체에 매핑해줌)
    public HelloData requestBodyJsonV4(@RequestBody HelloData helloData){ // RequestBody 생략 불가함(생략하면 ModelAtrrtibute라고 생각함)
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return helloData; // 받은 객체 리턴도 가능(즉 객체 타입도 리턴 가능 이러면 JSON으로 반환)
    }
}

/*
    sw가 다른 sw에게 지정된 형식으로 요청,명령을 받을 수 있는 수단을 API라 한다
    restapi란 각 요청이 어떤 동작이나 정보를 위한 것인지를 요청 모습 자체로 추론 가능하다
    즉, http 요청을 보낼 때 어떤 url에 어떤 메소드를 사용할지 개발자들 사이의 약속이다
 */