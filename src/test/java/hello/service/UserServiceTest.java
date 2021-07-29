package hello.service;

import hello.entity.User;
import hello.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;

import static org.mockito.Mockito.*;
// 单元测试
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    BCryptPasswordEncoder mockEncoder;
    @Mock
    UserMapper mockMapper;
    @InjectMocks
    UserService userService;

    @Test
    public void testSave() {
        // 调用userService
        // userService将请求转发给了userMapper
        // given:模拟一些操作
        when(mockEncoder.encode("123456")).thenReturn("12345678");
        // when:执行测试操作
        userService.save("yiweiran", "123456");
        // then:验证
        verify(mockMapper).save("yiweiran", "12345678");
    }

    @Test
    public void testGetUserByUsername() {
        userService.getUserByUsername("weiranyi");
        verify(mockMapper).findUserByUsername("weiranyi");
    }

    @Test
    public void throwExceptionWhenUserNotFound() {
//        try {
//            userService.loadUserByUsername("weiran");
//            Assertions.assertFalse(false);
//        }catch (UsernameNotFoundException e){
//        }
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("weiran"));
    }

    @Test
    public void returnUserDetailsWhenUserFound() {
        when(mockMapper.findUserByUsername("weiran"))
                .thenReturn(new User(1, "weiran", null, "123456", Instant.now(), Instant.now()));
        UserDetails userDetails = userService.loadUserByUsername("weiran");
        Assertions.assertEquals("123456",userDetails.getPassword());
    }

}