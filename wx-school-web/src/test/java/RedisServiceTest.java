import com.wx.WinApplication;
import com.wx.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WinApplication.class)
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void Test() {
        System.out.println(redisService.set("bb", "haha"));
    }
}
