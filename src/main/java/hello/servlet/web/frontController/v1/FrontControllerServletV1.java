package hello.servlet.web.frontController.v1;

import hello.servlet.web.frontController.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontController.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontController.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV1",urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1  extends HttpServlet {
    private Map<String,ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        this.controllerMap.put("/front-controller/v1/members/new-form",new MemberFormControllerV1());
        this.controllerMap.put("/front-controller/v1/members/save",new MemberSaveControllerV1());
        this.controllerMap.put("/front-controller/v1/members",new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI(); // 뒤에 붙는 url을 그대로 가져옴
        ControllerV1 controllerV1 = controllerMap.get(requestURI);
        if(controllerV1 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controllerV1.process(request,response);
    }
}