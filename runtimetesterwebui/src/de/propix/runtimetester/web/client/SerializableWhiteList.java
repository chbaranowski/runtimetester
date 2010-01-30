package de.propix.runtimetester.web.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SerializableWhiteList implements IsSerializable {

	@SuppressWarnings("unused")
	SubjectResultDTO webSubjectResult;
	
	@SuppressWarnings("unused")
	TestResultDTO webTestResult;
	
	public SerializableWhiteList() {}
	
}
