package de.propix.runtimetester.web.server;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
		
	
	public static BundleContext bundleContext;
	
	public void start(BundleContext context) throws Exception {
		bundleContext = context;
	}

	public void stop(BundleContext context) throws Exception {
		bundleContext = null;
	}

}
