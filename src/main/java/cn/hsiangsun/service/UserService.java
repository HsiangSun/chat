package cn.hsiangsun.service;

import cn.hsiangsun.beans.User;
import org.springframework.stereotype.Service;

public interface UserService {
    boolean checkUserNameIsExits(String username);
    User userLogin(String username,String password);
    boolean register(User registerUser);
}
