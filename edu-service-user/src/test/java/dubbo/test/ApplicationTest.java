package dubbo.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edu.facade.user.entity.PmsUser;
import com.edu.service.user.dao.PmsUserDao;

public class ApplicationTest {

	ApplicationContext context;
	
	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("spring/application-config.xml");
		
	}

	@Test
	public void test() {
		PmsUserDao pmsUserDao = (PmsUserDao) context.getBean("pmsUserDao");
		PmsUser pmsUser = pmsUserDao.getById((long) 1);
		System.out.println(pmsUser.getUserName());
	}
	
	
}
