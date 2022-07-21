package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet",urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest requesteq, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = requesteq.getInputStream(); // 바디정보 가져오기
        String msg = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 바디정보 스트링으로 변환
        System.out.println(msg);
        response.getWriter().write("ok");
    }
}
