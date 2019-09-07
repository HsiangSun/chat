package cn.hsiangsun.service;

import cn.hsiangsun.beans.User;

public interface UserService {
    boolean checkUserNameIsExits(String username);
    User userLogin(String username);
    boolean register(User registerUser);

}
