package hello.springMVC12.basic.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @RestController 는 반환 값이 HTTP 메세지 바디에 바로 입력한다. 따라서 실행 결과로 return "ok" 이므로 ok 메세지를 받는다.
 */
@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 요청
     * 둘다 허용 @RequestMapping({"/hello-basic", "/hello-go"})
     * @RequestMapping에 method 속성으로 HTTP 메서드를 지정하지 않으면,
     * HTTP 메서드 모두 허용 (GET, HEAD, POST, PUT, PATCH, DELETE)
     */
    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("helloBasic");

        return "ok";
    }

    /**
     * method 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET) // 만약 POST 요청 시 상태코드 405 반환 함
    public String mappingGetV1(){
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2(){
        log.info("maapingGetV2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * 특정 파라미터가 있거나 없는 조건을 추가할 수 있다. 잘 사용하지는 않는다.
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consume : 해당 Media-Type으로 받을(소모 할)거야. 라는 뜻으로 해석하자
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * consumes = {"text/plain", "application/*"}
     * MediaType.APPLICATION_JSON_VALUE
     * 만약 맞지 않으면 HTTP 415 상태코드(Unsupported Media Type)을 반환한다.
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes(){
        log.info("maapingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces : 해당 Media-Type으로 제공 할거야 라고 해석
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     * produces = MediaType.TEXT_PLAIN_VALUE
     * produces = "text/plain;charset=UTF-8"
     * produces = {"text/plain", "application/*"}
     * 만약 맞지 않으면 HTTP 406 상태코드(Not Acceptable)을 반환한다.
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
