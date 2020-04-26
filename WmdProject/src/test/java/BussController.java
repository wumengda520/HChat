import com.cn.wmd.pojo.Cat;
import com.cn.wmd.pojo.Mouse;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Descripation:
 * @Author: wmd
 * @Date: 2020-04-22 15:08
 * @since 1.0
 */
@RestController
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BussController.class)
public class BussController {

    @EventListener
    @Async
    public void acceptSend(Cat cat) {
        Mouse mouse = new Mouse();
        mouse.setSize("20");
        String s = cat.getCool().toUpperCase();
        System.out.println(s);
        System.out.println(mouse.getSize() + "的老鼠看到" + cat.getCool() + "的猫来抓自己了");
    }


}