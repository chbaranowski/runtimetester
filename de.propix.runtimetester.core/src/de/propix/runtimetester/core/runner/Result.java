package de.propix.runtimetester.core.runner;

import java.util.ArrayList;
import java.util.List;

public class Result {

	private final ArrayList<SubjectResult> subjectResults = new ArrayList<SubjectResult>();
	
	public void addSubjectResult(SubjectResult result){
		subjectResults.add(result);
	}
	
	public List<SubjectResult> getResults(){
		return subjectResults;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Runtime Test Result: \n");
		for (SubjectResult subjectResult : subjectResults) {
			builder.append(subjectResult.toString() + "\n");
		}
		return builder.toString();
	}
	
}
