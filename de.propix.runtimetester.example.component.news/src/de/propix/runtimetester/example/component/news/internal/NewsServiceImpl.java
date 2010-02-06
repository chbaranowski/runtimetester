package de.propix.runtimetester.example.component.news.internal;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import de.propix.runtimetester.core.IntegratedTest;
import de.propix.runtimetester.core.IntegratedTestService;
import de.propix.runtimetester.example.component.news.NewsService;
import de.propix.runtimetester.example.component.user.UserService;

public class NewsServiceImpl implements NewsService, IntegratedTestService {

	
	private BundleContext context;
	
	public NewsServiceImpl(BundleContext context) {
		this.context = context;
	}
	
	/**
	 * Service Method.
	 */
	public void readMail(String messageId) {
		ServiceTracker tracker = new ServiceTracker(context, UserService.class.getName(), null);
		tracker.open();
		String userName;
		try {
			UserService userService = (UserService) tracker.getService();
			if(userService == null)
				throw new RuntimeException("The user service is not avaible");
			userName = userService.getUserName(messageId);
		} finally {
			tracker.close();
		}
		
		// TOD0: Servcie Impl
	}


	/**
	 * Integrated Test Method
	 */
	@IntegratedTest
	public void testUserServiceAvaible(){
		ServiceTracker tracker = new ServiceTracker(context, UserService.class.getName(), null);
		tracker.open();
		String userName;
		try {
			UserService userService = (UserService) tracker.getService();
			if(userService == null)
				throw new RuntimeException("The user service is not avaible");
		} finally {
			tracker.close();
		}
	}
	
	@IntegratedTest
	public void testMailImapServerAvaible(){
		// always Green
	}
	
}
