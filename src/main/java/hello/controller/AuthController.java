package hello.controller;

import hello.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class AuthController {
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    @Inject
    public AuthController(UserDetailsService userDetailsService,
                          AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }



    @GetMapping("/auth")
    @ResponseBody
    public Object auth(ModelMap map){
        // 获取M（数据）：ModelMap map
        return new Result("ok","您没有登录",false);
    }
    /* https://spring.io/guides/gs/securing-web/ */
    @PostMapping("/auth/login")
    @ResponseBody
    public Result login(@RequestBody Map<String,Object> usernameAndPasswordJson){
        String username = usernameAndPasswordJson.get("username").toString();
        String paddword = usernameAndPasswordJson.get("password").toString();
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        }catch (UsernameNotFoundException e){
            return new Result("fail","用户不存在",false);
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,paddword,userDetails.getAuthorities());
        try {
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);
            User loggedInUser = new User(1,"张三");
            return new Result("ok","登录成功",true,loggedInUser);
        }catch (BadCredentialsException e){
            return new Result("fail","密码不正确",false);
        }
    }

    private static class Result{
        String status;
        String msg;
        boolean isLogin;
        Object data;

        public Result(String status, String msg, boolean isLogin) {
            this(status,msg,isLogin,null);
        }
        public Result(String status, String msg, boolean isLogin,Object data) {
            this.status = status;
            this.msg = msg;
            this.isLogin = isLogin;
            this.data = data;
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isLogin() {
            return isLogin;
        }

        public void setLogin(boolean login) {
            isLogin = login;
        }
    }
}
