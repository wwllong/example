import com.example.transfer.SpringConfig;
import com.example.transfer.dao.AccountDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class IoCTest {

    @Test
    public void testIoC() throws Exception {
        // 通过读取classpath下的xml文件来启动容器（xml模式SE应用下推荐）
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao);
    }

}
