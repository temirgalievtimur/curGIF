package mainPackage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SpringRestController.class)
@ImportAutoConfiguration(FeignAutoConfiguration.class)
public class SpringRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void startPage() throws Exception {
        mockMvc.perform(get("/start")).andExpect(status().isOk());//проверка работоспособности
    }
    @Test
    public void mainPage() throws Exception {
        mockMvc.perform(get("/main")).andExpect(status().isOk());//проверка работоспособности
    }
    @Test
    public void resultForEachCurr() throws Exception {
        mockMvc.perform(get("/result?curr=EUR")).andExpect(status().isOk());//проверка работоспособности
    }
}