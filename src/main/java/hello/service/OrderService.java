package hello.service;

import javax.inject.Inject;

public class OrderService {
    private UserService userService;

    /* 1.1、声明依赖关系
     *  下段代码告诉Spring，OrderService依赖userService
     * 1、之前开发者常用的方式
     *  @Resource 依赖注入
     *  @Autowired 自动装配
     * 2、现在用构造器注入
     *  @Inject 依赖注入新的标准
     */
    @Inject
    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public void placeOrder(Integer userId, String item) {
        userService.getUserById(userId);
    }
}
