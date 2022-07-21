package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper(); // 스프링에서 사용하는 JSON 파싱 라이브러리

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String str = request.getParameter("username");
        System.out.println(str);
            참고로 위처럼 getParameter의 경우는 get방식으로 쿼리 파라미터를 보낸 경우거나
            html-from으로 보낸 post의 경우에만 이런식으로 얻어올 수 있다 그외에는 전부다
            요청 http 본문을 인코딩을 해야한다 그 코드는 아래와 같으며 아래와 같이 받으면
            요청 http 본문 그자체가 날라옴(추가적인 파싱 필요할 수 있다)
         */
        ServletInputStream inputStream = request.getInputStream();
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        HelloData helloData = objectMapper.readValue(body, HelloData.class); // 간단하게 이렇게 사용 가능 두번째 인자에는 파싱 결과를 저장할 클래스를 준다
        System.out.println(helloData.getUsername()+" "+helloData.getAge());
    }
}
