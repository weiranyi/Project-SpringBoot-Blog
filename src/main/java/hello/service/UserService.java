package hello.service;

import hello.mapper.UserMapper;

import javax.inject.Inject;

public class UserService {
    // 声明有一个Bean是userMapper
    private UserMapper userMapper;

    @Inject
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserById(Integer id){
        return userMapper.findUserById(id);
    }
}
