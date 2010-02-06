package de.propix.runtimetester.core.runner.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import de.propix.runtimetester.core.RuntimeTestService;
import de.propix.runtimetester.core.IntegratedTest;
import de.propix.runtimetester.core.runner.Result;
import de.propix.runtimetester.core.runner.RuntimeTester;
import de.propix.runtimetester.core.runner.SubjectResult;
import de.propix.runtimetester.core.runner.TestResult;

public class OsgiRuntimeTesterImpl implements RuntimeTester {

	private final BundleContext context;
	
	public OsgiRuntimeTesterImpl(BundleContext context){
		this.context = context;
	}
	
	public Result runAllTests() {
		Result result = new Result();
		ServiceTracker runtimeTestServiceTracker = new ServiceTracker(context,
				RuntimeTestService.class.getName(), null);
		runtimeTestServiceTracker.open();
		try {
			ServiceReference[] serviceReferences = runtimeTestServiceTracker.getServiceReferences();
			if (serviceReferences != null)
				for (ServiceReference serviceReference : serviceReferences) {
					Object subject = runtimeTestServiceTracker.getService(serviceReference);
					SubjectResult subjectResult = new SubjectResult(serviceReference.getBundle(), subject);
					Method[] testMethods = getTestMethods(subject);
					for (Method testMethod : testMethods) {
						TestResult testResult = runTestMethod(subject,
								testMethod);
						subjectResult.addTestResult(testResult);
					}
					result.addSubjectResult(subjectResult);
				}
		} finally {
			runtimeTestServiceTracker.close();
		}
		return result;
	}

	TestResult runTestMethod(Object subject, Method testMethod) {
		TestResult testResult = new TestResult(testMethod);
		try {
			testMethod.invoke(subject);
		} catch (Throwable exp) {
			testResult.setThrownException(exp);
		}
		return testResult;
	}

	Method[] getTestMethods(Object subject) {
		ArrayList<Method> testMethods = new ArrayList<Method>();
		Method[] methods = subject.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(IntegratedTest.class)) {
				testMethods.add(method);
			}
		}
		return testMethods.toArray(new Method[testMethods.size()]);
	}

}
