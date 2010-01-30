package de.propix.runtimetester.example.component.user;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import de.propix.runtimetester.core.RuntimeTestService;
import de.propix.runtimetester.example.component.user.internal.UserServiceImpl;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		// register the service
		UserServiceImpl service = new UserServiceImpl();
		context.registerService(
				UserService.class.getName(), 
				service, 
				new Hashtable());
		context.registerService(
				RuntimeTestService.class.getName(), 
				service, 
				new Hashtable());
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	}

}
