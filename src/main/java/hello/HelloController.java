package hello;

import hello.entity.User;
import hello.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private UserService userService;

    public HelloController(UserService userService){
        this.userService = userService;
    }


    @RequestMapping("/")
    public User index() {
        return null;
    }

}
