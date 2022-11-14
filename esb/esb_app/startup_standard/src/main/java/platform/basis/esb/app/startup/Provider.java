package platform.basis.esb.app.startup;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import platform.basis.esb.app.support.AliveKeeping;

public class Provider
{

	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) throws IOException
	{
		context = new ClassPathXmlApplicationContext("classpath*:esb.xml");
		context.start();
		System.out.println("服务提供者已经启动 ...");
		AliveKeeping.start();

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run()
			{
				AliveKeeping.stop();
			}
		}));
	}
}