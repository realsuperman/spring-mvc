package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // jsp 경로로 이동시킨다
        dispatcher.forward(request,response);
    }
}

/*
    WEB-INF밑에 있는 자원들은 절대로 url을 쳐서 접속되는 경로가 아니다 이건 was의 룰임
    다시말하면 WEB-INF밑에 있는 자원들을 접근하려면 컨트롤러를 거쳐야 한다
 */