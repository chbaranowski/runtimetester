package de.propix.runtimetester.web.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("runner")
public interface TestRunnerService extends RemoteService {

	ResultDTO runTests();
	
	public SerializableWhiteList serializableWhiteList(SerializableWhiteList s);
}
