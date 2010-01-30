package de.propix.runtimetester.web.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SubjectResultDTO implements IsSerializable {

	private String subjectName;
	
	private String bundleName;

	private List<TestResultDTO> testResults = new ArrayList<TestResultDTO>();
	
	public List<TestResultDTO> getTestResults() {
		return testResults;
	}

	public void addTestResult(TestResultDTO testResult){
		testResults.add(testResult);
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getBundleName() {
		return bundleName;
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

	public boolean wasSuccessful() {
		for (TestResultDTO result : testResults) {
			if(!result.wasSuccessful())
				return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getSubjectName() + ":</br>");
		for (TestResultDTO testResult : testResults) {
			builder.append(testResult.toString());
		}
		return builder.toString();
	}
	
	
}
