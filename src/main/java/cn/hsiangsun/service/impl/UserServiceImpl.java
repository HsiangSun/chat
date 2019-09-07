package cn.hsiangsun.service.impl;

import cn.hsiangsun.beans.User;
import cn.hsiangsun.mapper.UserMapper;
import cn.hsiangsun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean checkUserNameIsExits(String username) {
        User user = new User();
        user.setUsername(username);
        User one = userMapper.selectOne(user);
        return one != null ? true : false;
    }

    @Override
    public User userLogin(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",username);
        User user = userMapper.selectOneByExample(example);
        return user;
    }

    @Override
    public boolean register(User registerUser) {
        int i = userMapper.insertSelective(registerUser);
        return i == 1 ? true : false ;
    }
}
