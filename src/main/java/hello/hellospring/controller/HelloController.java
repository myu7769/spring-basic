package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // localhost:8080/hello 로 맵핑
    public String hello(Model model){
        model.addAttribute("data","Hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc") // localhost:8080/hello-mvc?name=spring 로 맵핑 get방식, ?name=spring
    public String helloMvc(@RequestParam("name") String name, Model model){

        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // hello spring ( name = spring 일때) 데이터를 그대로 리스판스
    }

    @GetMapping("hello-api")
    @ResponseBody // viewresolver 대신 HttpMessageConverter가 동작
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // json 방식으로 웹에서 볼 수 있음 HttpMessageConverter -> jsonConverter(객체), StringConverter
                        // 기본 객체처리 'mappingJackson2HttpMessageConverter'
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
