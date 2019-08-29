package cn.hsiangsun.controller;

import cn.hsiangsun.beans.User;
import cn.hsiangsun.result.QueryResponse;
import cn.hsiangsun.service.UserService;
import cn.hsiangsun.util.IdWork;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/registerOrLogin")
    public QueryResponse syHello(@RequestBody User user) {

        if (StringUtils.isBlank(user.getUsername() ) || StringUtils.isBlank( user.getPassword() ) ){
            return QueryResponse.FAIL("用户名或密码不能为空!");
        }

        boolean isExits = userService.checkUserNameIsExits(user.getUsername());
        if (isExits){
            //登录
            User login = userService.userLogin(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()));
            if (login == null){
                return QueryResponse.FAIL("用户名或密码不正确");
            }
        }else {
            //注册
            User registerUser = new User();
            registerUser.setId( (Long.toString(new IdWork(1L,1L).nextId())) );
            registerUser.setUsername(user.getUsername());
            registerUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            registerUser.setFaceImage("");

            boolean b = userService.register(registerUser);
            if (b){
                return QueryResponse.OK();
            }else {
                return QueryResponse.FAIL("注册失败请稍后再试!");
            }

        }

        return QueryResponse.OK();
    }
}
