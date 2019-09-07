package cn.hsiangsun.controller;

import cn.hsiangsun.beans.User;
import cn.hsiangsun.result.QueryResponse;
import cn.hsiangsun.service.UserService;
import cn.hsiangsun.util.IdWork;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerOrLogin")
    public QueryResponse syHello(@RequestBody User user) {


        if (StringUtils.isBlank(user.getUsername() ) || StringUtils.isBlank( user.getPassword() ) ){
             return new  QueryResponse().FAIL("用户名或密码不能为空!");
        }

        boolean isExits = userService.checkUserNameIsExits(user.getUsername());
        User loginUser = null;
        if (isExits){
            //登录
            loginUser = userService.userLogin(user.getUsername());
            if (loginUser == null){
                return new QueryResponse().FAIL("用户名不正确");
            }

            String password = loginUser.getPassword();
            boolean checkpw = BCrypt.checkpw(user.getPassword(), password);
            if (!checkpw){
                return new QueryResponse().FAIL("密码错误");
            }

        }else {
            //注册
            User registerUser = new User();
            registerUser.setId( (Long.toString(new IdWork(1L,1L).nextId())) );
            registerUser.setUsername(user.getUsername());
            registerUser.setPassword( BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()) );
           /* registerUser.setFaceImage("");
            registerUser.setFaceBigImage("");
            registerUser.setNickName("");
            registerUser.setQrcode("");
            registerUser.setCid("");*/


            boolean b = userService.register(registerUser);
            if (b){
                return new  QueryResponse().OK();
            }else {
                return new  QueryResponse().FAIL("注册失败请稍后再试!");
            }
        }
        return new QueryResponse(200,"登录成功");
    }
}
