# SpringBoot-Blog

### 介绍：

- 测试
    - 持续集成，前期用了下circleci，后面采用github actions

### 收获：

- 1、自动化测试：
    - 意义：
        - 不用手工测试，手工测试费时费力，不消耗精力
        - 人总会出错
        - 测试也可以锻炼能
    - 分类
        - 单元测试 unit test（简单快速）
            - 只拿出来很小的模块检查
        - 黑盒测试
            - 只关注接口
            - 使用许多mock
        - 集成测试（可靠、安全、慢、服装）
            - 对整个应用装配好后测试对外接口
        - 冒烟测试
            - 应用开发完成后，做个简单的检查
            - 例如：web项目完成后，简单访问下网页
        - 回归测试
            - 发布新功能，是否破坏了之前的功能
    - 流行度：
        - 1、Junit4
        - 2、Junit5
        - 3、TestNG
    - 实战
        - 命令：mvn test
            - 不需要代码被打包或部署
        - 创建测试类的默认按键为： ctrl+shift+t
- 2、持续集成雨集成测试：
    - 持续集成：简单理解为不断的自动化检测
    - 类型：
        - TravisCI
        - Carracci
        - appveyorci
        - 中小公司：Jenkins
    - 命令
        - docker run -p 8081:8080 -v 'pwd'/Jenkins-data:/var/jenkins_home jenkins/jenkins
            - 得到Jenkins密码：23083ac830a5497da64d3ab5229c3f53
        - 若需清除数据：rm -rf Jenkins-data/*
    - 数据迁移：mvn flyway:migrate
- 3、maven安装脚本生成命令
    - mvn -N io.takari:maven:0.7.7:wrapper

