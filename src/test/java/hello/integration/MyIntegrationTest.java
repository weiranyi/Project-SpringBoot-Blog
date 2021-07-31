package hello.integration;

import hello.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        // 测试 Application 类
        classes = Application.class,
        // 项目启动时使用随机端口
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class MyIntegrationTest {
    @Inject
    Environment environment;

    @Test
    public void notLoggedInByDefault() throws IOException, InterruptedException {
        // 获取当前环境中的端口
        String port = environment.getProperty("local.server.port");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/auth"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertTrue(response.body().contains("用户没有登录"));
        Assertions.assertEquals(200, response.statusCode());
    }
}
