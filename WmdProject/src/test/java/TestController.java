import com.cn.wmd.pojo.Cat;
import jdk.management.resource.internal.inst.SimpleAsynchronousFileChannelImplRMHooks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.FileChannelImpl;

import java.nio.channels.FileChannel;

/**
 * @Descripation:
 * @Author: wmd
 * @Date: 2020-04-22 15:00
 * @since 1.0
 */
@RestController
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestController.class, BussController.class})
public class TestController {
    @Autowired
    private ApplicationContext applicationContext;


    @Test
    public void handlerEvent() {
        Cat cat = new Cat();
        cat.setCool(null);
        System.out.println("发送成功");
        applicationContext.publishEvent(cat);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }


    }


}