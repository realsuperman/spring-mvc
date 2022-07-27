package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}",username,age);
        response.getWriter().write("ok");
    }

    @ResponseBody // 해당 메소드가 뷰 리졸버를 사용하는게 아닌 문자열을 내린다(메소드의 restController이다)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,@RequestParam("age") int age){
        log.info("username={}, age={}",memberName,age);
        return "ok";
    }

    @ResponseBody // 해당 메소드가 뷰 리졸버를 사용하는게 아닌 문자열을 내린다(메소드의 restController이다)
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,@RequestParam int age){
        log.info("username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody // 해당 메소드가 뷰 리졸버를 사용하는게 아닌 문자열을 내린다(메소드의 restController이다)
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age){
        log.info("username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody // 해당 메소드가 뷰 리졸버를 사용하는게 아닌 문자열을 내린다(메소드의 restController이다)
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                 @RequestParam(required = true) int age){
        log.info("username={}, age={}",username,age);
        return "ok";
    } // required의 기본값은 true이다(필수값 없으면 오류남) required의 값이 false면 없어도 됨
    /* required가 false인 경우 값이 없어도 되지만 그럴경우 값이 null로 들어가는데 이런경우 자바의 기본자료형
       이라면 에러가 발생한다 (age값의 required값이 false면 age는 null로 들어가는데 age는 null을 넣을 수
       없으므로 500 서버에러가 생긴다 조심할 것)
     */

    @ResponseBody // 해당 메소드가 뷰 리졸버를 사용하는게 아닌 문자열을 내린다(메소드의 restController이다)
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true,defaultValue = "guest") String username,
                                       @RequestParam(required = false,defaultValue = "-1") int age){
        log.info("username={}, age={}",username,age);
        return "ok";
    } // defaultValue는 그 값이 안넘오는 경우 기본값으로 세팅한다는 의미다 (required가 true인 경우도 사용 가능)
    // defaultValue의 경우 빈 문자열('')의 경우에도 defaultValue값으로 세팅한다

    @ResponseBody // 해당 메소드가 뷰 리졸버를 사용하는게 아닌 문자열을 내린다(메소드의 restController이다)
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object>paramMap){ // 모든 파라미터 맵으로 받기
        log.info("username={}, age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }

/*    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username,@RequestParam int age){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }*/

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){ // RequestParam으로 날라오는 파라미터랑 객체랑 매핑함
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    } // ModelAttribute의 경우 처음에 HelloData 객체를 생성 후 requestParam이름으로 setter를 호출해준다

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){ // ModelAttribute 생략 가능
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    } // 참고로 만든 객체형이 아닌경우 @RequestParam이라고 생각하고 그외엔 @ModelAttribute라고 생각한다(스프링이)


}