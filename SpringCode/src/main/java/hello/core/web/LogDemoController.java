package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; /* Provider기능으로 HTTP 호출 시점에 빈을 생성할 수 있음  */

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURI().toString();
        System.out.println("MyLogger:"+myLogger.getClass());
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller Test");
        logDemoService.logic("testID");
        return "OK";
    }
}
