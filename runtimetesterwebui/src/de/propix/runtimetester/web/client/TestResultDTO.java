package de.propix.runtimetester.web.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class TestResultDTO implements IsSerializable {
	
	private String testMethod;
	
	private String thrownException;  
	
	private long runTime= 0;
	
	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}

	private long startTime;
	
	public String getThrownException() {
		return thrownException;
	}

	public void setThrownException(String thrownException) {
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

	public String getTestMethod() {
		return testMethod;
	}

	public boolean wasSuccessful() {
		return thrownException == null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(wasSuccessful()){
			builder.append("- " + testMethod + " : SUCCESS </br>");
		}
		else{
			builder.append("- " + testMethod + " : FAILED </br>");
		}
		return builder.toString();
	}
	
}
