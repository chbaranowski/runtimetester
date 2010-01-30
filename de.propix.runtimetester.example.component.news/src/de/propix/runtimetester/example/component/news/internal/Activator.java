package de.propix.runtimetester.example.component.news.internal;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.propix.runtimetester.core.RuntimeTestService;
import de.propix.runtimetester.example.component.news.NewsService;

public class Activator implements BundleActivator {

	private NewsService simpleLogService;
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		simpleLogService = new NewsServiceImpl(context);
		context.registerService(
				NewsService.class.getName(), 
				simpleLogService, 
				new Hashtable());
		context.registerService(
				RuntimeTestService.class.getName(), 
				simpleLogService, 
				new Hashtable());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		simpleLogService = null;
	}

}
