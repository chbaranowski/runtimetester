package de.propix.runtimetester.core.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.propix.runtimetester.core.runner.Tester;
import de.propix.runtimetester.core.runner.internal.OsgiRuntimeTesterImpl;

public class Activator implements BundleActivator {
		
	private OsgiRuntimeTesterImpl service;
	
	public void start(BundleContext context) throws Exception {
		service = new OsgiRuntimeTesterImpl(context);
		context.registerService(Tester.class.getName(), service, null);
	}

	public void stop(BundleContext context) throws Exception {
		service = null;
	}

}
