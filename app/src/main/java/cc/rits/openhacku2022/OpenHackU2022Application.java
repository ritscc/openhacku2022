package cc.rits.openhacku2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class OpenHackU2022Application {

    public static void main(String[] args) {
        SpringApplication.run(OpenHackU2022Application.class, args);
    }

    @RequestMapping("/**/{path:[^\\.]*}")
    public String redirect(@PathVariable String path) {
        return "forward:/";
    }

}
