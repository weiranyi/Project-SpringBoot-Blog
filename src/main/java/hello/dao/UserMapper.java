package hello.dao;

import hello.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * TODO
 *
 * @author https://github.com/weiranyi
 * @version 1.0
 * @date 2021/7/24 19:06
 */
@Mapper
public interface UserMapper {
//    @Select("SELECT * FROM user WHERE id = #{id}")
//    User findUserById(@Param("id")Integer id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findUserByUsername(@Param("username")String username);

    @Select("insert into user(username,encrypted_password,created_at,updated_at) " +
            "values(#{username},#{encryptedPassword},now(),now())")
    void save(@Param("username")String username, @Param("encryptedPassword")String encryptedPassword);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(@Param("id")Integer id);
}
