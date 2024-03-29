import com.example.transfer.dao.AccountDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class IoCTest {


    @Test
    public void testIoC() throws Exception {

        // 通过读取classpath下的xml文件来启动容器（xml模式SE应用下推荐）
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        // 不推荐使用
        //ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext("文件系统的绝对路径");

        // 第一次getBean该对象
        Object accountPojo = applicationContext.getBean("accountPojo");

//        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
//
//        accountDao.queryAccountByCardNo("1111111");
//        System.out.println("accountDao：" + accountDao);
//        AccountDao accountDao1 = (AccountDao) applicationContext.getBean("accountDao");
//        System.out.println("accountDao1：" + accountDao1);

        Object company = applicationContext.getBean("company");
        System.out.println(company);
        Object companyFactoryBean = applicationContext.getBean("&company");
        System.out.println(companyFactoryBean);


        Object connectionUtils = applicationContext.getBean("connectionUtils");
        System.out.println(connectionUtils);

        applicationContext.close();

    }

}
