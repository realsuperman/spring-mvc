package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns = "/hello")
public class helloServlet  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request = "+request.getParameter("username"));
        System.out.println("HelloServlet.service");
        System.out.println("response = "+response);

        response.setContentType("text/plain"); // 응답 헤더 지정
        response.setCharacterEncoding("utf-8"); // 응답 헤더 지정
        response.getWriter().write("hello "+request.getParameter("username")); // 응답 바디 지정
    }
}
