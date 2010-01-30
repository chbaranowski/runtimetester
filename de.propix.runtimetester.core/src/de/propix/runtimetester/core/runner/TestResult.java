package de.propix.runtimetester.core.runner;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class TestResult {
	
	private final Method testMethod;
	
	private Throwable thrownException;  
	
	private long runTime= 0;
	
	private long startTime;
	
	public TestResult(Method testMethod) {
		this.testMethod = testMethod;
	}
	
	public Throwable getThrownException() {
		return thrownException;
	}

	public void setThrownException(Throwable thrownException) {
		this.thrownException = thrownException;
	}

	public long getRunTime() {
		return runTime;
	}

	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Method getTestMethod() {
		return testMethod;
	}

	public boolean wasSuccessful() {
		return thrownException == null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(wasSuccessful()){
			builder.append("- " + testMethod.getName() + " : SUCCESS \n");
		}
		else{
			builder.append("- " + testMethod.getName() + " : FAILED \n");
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			thrownException.printStackTrace(printWriter);
			builder.append(writer.getBuffer().toString());
		}
		return builder.toString();
	}
	
}
