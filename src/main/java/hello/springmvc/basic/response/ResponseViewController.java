package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data","hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){  // 뭘 붙여서 주려면 파라미터에 Model 필요
        model.addAttribute("data","hello!");
        return "response/hello"; // 뷰 리졸버에서 해당 이름으로 찾는다
    }

    @RequestMapping("/response/hello") // 관례적으로 void면 return이 response/hello를 스프링이 해준다(쓰지말자)
    public void responseViewV3(Model model){  // 뭘 붙여서 주려면 파라미터에 Model 필요
        model.addAttribute("data","hello!");
    }
}

// 참고로 앞의 프리픽스와 뒤의 프리픽스는 스프링부트에 기본값으로 세팅 되어있다
// 지금은 앞 프리픽스가 templates고 뒤의 프리픽스는 .html이다