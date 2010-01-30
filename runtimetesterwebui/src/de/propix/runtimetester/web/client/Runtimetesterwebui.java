package de.propix.runtimetester.web.client;

import java.util.Iterator;

import org.eclipse.jdt.internal.compiler.flow.LabelFlowContext;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Runtimetesterwebui implements EntryPoint {

	private final TestRunnerServiceAsync runnerService = GWT.create(TestRunnerService.class);

	final Button runButton = new Button("Run Tests");

	final Tree testResultTree = new Tree();
	
	final TextArea testErrorDetailTextArea = new TextArea();
	
	final TextBox textField = new TextBox();
	
	final Label textLable = new Label();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get("runButtonContainer").add(runButton);
		RootPanel.get("testResultContainer").add(testResultTree);
		RootPanel.get("testErrorResultContainer").add(testErrorDetailTextArea);
		
		runButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent arg0) {
				runTests();
			}
		});
		
		testResultTree.addSelectionHandler(new SelectionHandler<TreeItem>() {
			
			public void onSelection(SelectionEvent<TreeItem> event) {
				TreeItem selectedItem = event.getSelectedItem();
				Object userObject = selectedItem.getUserObject();
				testErrorDetailTextArea.setVisible(false);
				if (userObject instanceof TestResultDTO) {
					TestResultDTO result = (TestResultDTO) userObject;
					if(!result.wasSuccessful()){
						testErrorDetailTextArea.setText(result.getThrownException());
						testErrorDetailTextArea.setVisible(true);
					}
				}
			}
			
		});
		
		testErrorDetailTextArea.setVisible(false);
		testErrorDetailTextArea.setSize("1000", "300");
	}
	
	void runTests() {
		testErrorDetailTextArea.setVisible(false);
		testResultTree.removeItems();
		runnerService.runTests(new AsyncCallback<ResultDTO>() {
			
			public void onSuccess(ResultDTO result) {
				TreeItem root = new TreeItem();
				if(result.wasSuccessful()){
					root.setHTML("<img id=\"testsuiteok\"src=\"images/tsuiteok.gif\"></img> All Tests");
				}
				else{
					root.setHTML("<img id=\"testsuiteok\"src=\"images/tsuiteerror.gif\"></img> All Tests");
				}
				for (SubjectResultDTO subjectResult : result.getResults()) {
					TreeItem subjectTreeItem = new TreeItem();
					if(subjectResult.wasSuccessful()) {
						subjectTreeItem.setHTML("<img id=\"testsuiteok\"src=\"images/tsuiteok.gif\"></img> " 
								+ subjectResult.getSubjectName() + " - (Bundle: " + subjectResult.getBundleName() + ")");
					}
					else {
						subjectTreeItem.setHTML("<img id=\"testsuiteok\"src=\"images/tsuiteerror.gif\"></img> " 
								+ subjectResult.getSubjectName() + " - (Bundle: " + subjectResult.getBundleName() + ")");
					}
					subjectTreeItem.setUserObject(subjectResult);
					for (TestResultDTO testResult : subjectResult.getTestResults()) {
						if(testResult.wasSuccessful()){
							TreeItem testResultTreeItem = new TreeItem("<img id=\"testok\"src=\"images/testok.gif\"></img> " 
									+ testResult.getTestMethod());
							subjectTreeItem.addItem(testResultTreeItem);
							testResultTreeItem.setUserObject(testResult);
						}
						else
						{
							TreeItem testResultTreeItem = new TreeItem("<img id=\"testok\"src=\"images/testerr.gif\"></img> " 
									+ testResult.getTestMethod());
							testResultTreeItem.setUserObject(testResult);
							subjectTreeItem.addItem(testResultTreeItem);
						}
					}
					root.addItem(subjectTreeItem);
				}
				
				testResultTree.addItem(root);
				
				collapseResultTreeItems(root);
			}
			
			public void onFailure(Throwable result) {
				//TODO: Error Handling
			}
			
		});
	}
	
	void collapseResultTreeItems(TreeItem root) {
		Iterator<TreeItem> treeItemIterator = testResultTree.treeItemIterator();
		for (Iterator<TreeItem> iterator = treeItemIterator; iterator.hasNext();) {
			TreeItem item = (TreeItem) iterator.next();
			Object userObject = item.getUserObject();
			if (userObject instanceof SubjectResultDTO) {
				SubjectResultDTO subjectResult = (SubjectResultDTO) userObject;
				if(!subjectResult.wasSuccessful()){
					item.setState(true);
				}
				
			}
			
		}
		root.setState(true);
	}
}
