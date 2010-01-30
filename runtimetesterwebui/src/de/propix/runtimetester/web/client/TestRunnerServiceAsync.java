package de.propix.runtimetester.web.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TestRunnerServiceAsync {

	void runTests(AsyncCallback<ResultDTO> callback);

	void serializableWhiteList(SerializableWhiteList s,
			AsyncCallback<SerializableWhiteList> callback);

}
