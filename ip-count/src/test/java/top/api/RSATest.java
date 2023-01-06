package top.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.api.utils.TokenUtils;

@SpringBootTest
public class RSATest {

    @Test
    void getStr() throws Exception {
        String token = "M9hYrywqgsz3jC9vOdSikjdgcxggNIZQpcMBXCXrg4ncB07QhlxGAiojxjrg5liJQkzCXT5BMZHw9LvtlOd8n3G-3RaiyDs78X8YYcfUM1EqmUGNjUlZemY90RnQuyO51r03lY6pwBCLmhxQZDEoSvsZbQsCPnWlwGWJmDWibbE";
        boolean tokenCheck = TokenUtils.TokenCheck(token);
        System.out.println(tokenCheck);
        String tokenStr = TokenUtils.getTokenStr(token);
        System.out.println(tokenStr);
    }
}
