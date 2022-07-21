package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  1. 파라미터 전송 기능
 *  http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name="requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회(최신 람다 표현식)");
        request.getParameterNames().asIterator().forEachRemaining(paramName->System.out.println(paramName+"="+request.getParameter(paramName)));
        System.out.println("전체 파라미터 끝");
        System.out.println();

        System.out.println("단일 파라미터 조회");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println(username+" "+age);
        System.out.println("단일 파라미터 끝");
        System.out.println();

        System.out.println("파라미터 이름이 같은경우");
        String[] usernames = request.getParameterValues("username");
        for (String s : usernames) {
            System.out.println(s);
        }
        System.out.println("파라미터 이름이 같은경우 끝");

        response.getWriter().write("ok");

        /*
         파라미터 key값이 같은경우 이론상 first-search라고 생각하자 (먼저 찾아진 키의 벨류만 취한다)
         만약 중복을 사용하려면 iterator 패턴을 사용하자 (31~34라인 참고)
         */
    }
}
