package dubbo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider {
	

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application-config.xml");
			context.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (DubboProvider.class) {
			while(true) {
				try {
					DubboProvider.class.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
				
				
	}

}
