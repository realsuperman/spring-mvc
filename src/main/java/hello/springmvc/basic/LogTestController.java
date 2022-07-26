package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info(" info log={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);
        return "ok";
    }
}

// Controller는 뷰리졸버를 이용하지만 RestController는 해당 string 자체가 반환됨
// log를 사용할 땐 string concat 연산됨을 주의하자 즉, log를 할 때 {}를 이용하자 해당 연산은 파라미터 넘기는 연산임(붙이는 연산 아님)