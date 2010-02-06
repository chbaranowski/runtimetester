package de.propix.runtimetester.web.server;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.osgi.util.tracker.ServiceTracker;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.propix.runtimetester.core.runner.Result;
import de.propix.runtimetester.core.runner.Tester;
import de.propix.runtimetester.core.runner.SubjectResult;
import de.propix.runtimetester.core.runner.TestResult;
import de.propix.runtimetester.web.client.ResultDTO;
import de.propix.runtimetester.web.client.SerializableWhiteList;
import de.propix.runtimetester.web.client.SubjectResultDTO;
import de.propix.runtimetester.web.client.TestResultDTO;
import de.propix.runtimetester.web.client.TestRunnerService;

public class TestRunnerServiceImpl extends RemoteServiceServlet implements
		TestRunnerService {

	public ResultDTO runTests() {
		ServiceTracker serviceTracker = new ServiceTracker(
				Activator.bundleContext, Tester.class.getName(), null);
		serviceTracker.open();
		Tester runtimeTester = (Tester) serviceTracker
				.getService();
		Result result = runtimeTester.runAllTests();
		ResultDTO webResult = map(result);
		serviceTracker.close();
		return webResult;
	}

	private ResultDTO map(Result result) {
		ResultDTO webResult = new ResultDTO();
		for (SubjectResult subjectResult : result.getResults()) {
			SubjectResultDTO webSubjectResult = new SubjectResultDTO();
			webSubjectResult.setSubjectName(subjectResult.getSubject()
					.getClass().getSimpleName());
			webSubjectResult.setBundleName(subjectResult.getBundle().getSymbolicName());
			for (TestResult testResult : subjectResult.getTestResults()) {
				TestResultDTO webTestResult = new TestResultDTO();
				webTestResult.setTestMethod(testResult.getTestMethod().getName());
				if(testResult.getThrownException() != null){
					StringWriter writer = new StringWriter();
					PrintWriter printWriter = new PrintWriter(writer);
					testResult.getThrownException().printStackTrace(printWriter);
					webTestResult.setThrownException(writer.toString());
				}
				webSubjectResult.addTestResult(webTestResult);
			}
			webResult.addSubjectResult(webSubjectResult);
		}
		return webResult;
	}

	public SerializableWhiteList serializableWhiteList(SerializableWhiteList s) {
		throw new RuntimeException("Should never be called");
	}

}
