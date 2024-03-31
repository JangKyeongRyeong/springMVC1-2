package hello.springMVC12.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public  String toTest(){
        String name = "Spring";

        //LEVEL: TRACE > DEBUG > INFO > WARN > ERROR
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); // 보통 개발서버에 사용
        log.info ("info log={}" , name); // 보통 운영서버에 사용
        log.warn ("warn log={}" , name);
        log.error("error log={}", name);

        //로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
        log.debug("String concat log=" + name);

        //@RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
        //따라서 실행 결과로 "ok" 메세지를 받을 수 있다.
        return "ok";
    }
}
