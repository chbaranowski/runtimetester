package de.propix.runtimetester.core.runner;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;

public class SubjectResult {

	private final Object subject;
	
	private final ArrayList<TestResult> testResults = new ArrayList<TestResult>();
	
	private final Bundle bundle;
	
	public SubjectResult(Bundle bundle, Object subject) {
		this.subject = subject;
		this.bundle = bundle;
	}
	
	public List<TestResult> getTestResults(){
		return testResults;
	}
	
	public void addTestResult(TestResult result){
		testResults.add(result);
	}
	
	public boolean wasSuccessful() {
		for (TestResult result : testResults) {
			if(!result.wasSuccessful())
				return false;
		}
		return true;
	}
	
	public Object getSubject(){
		return subject;
	}
	
	public Bundle getBundle(){
		return bundle;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getSubject().getClass().getSimpleName() + ":\n");
		for (TestResult testResult : testResults) {
			builder.append(testResult.toString());
		}
		return builder.toString();
	}
}
