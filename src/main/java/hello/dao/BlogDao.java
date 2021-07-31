package hello.dao;

import hello.entity.Blog;

import java.util.List;

public class BlogDao {
    public List<Blog> getBlogs(Integer page, Integer pageSize, Integer userId) {
//        Map<String, Object> parameters = asMap(
//                "user_id", userId,
//                "offset", (page - 1) * pageSize,
//                "limit", pageSize);
//        return sqlSession.selectList("selectBlog", parameters);
        return null;
    }

    public int count(Integer userId) {
        return 3;
    }
}
