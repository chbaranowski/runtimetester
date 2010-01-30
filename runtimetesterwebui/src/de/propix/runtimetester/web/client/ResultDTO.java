package de.propix.runtimetester.web.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ResultDTO implements IsSerializable {

	private List<SubjectResultDTO> subjectResults = new ArrayList<SubjectResultDTO>();
	
	public void addSubjectResult(SubjectResultDTO result){
		subjectResults.add(result);
	}
	
	public List<SubjectResultDTO> getResults(){
		return subjectResults;
	}
	
	public boolean wasSuccessful() {
		for (SubjectResultDTO subjectResult : subjectResults) {
			if(!subjectResult.wasSuccessful())
				return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (SubjectResultDTO subjectResult : subjectResults) {
			buffer.append(subjectResult.toString());
		}
		return buffer.toString();
	}
	
}
