package com.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.record.chart.SeriesLabelsRecord;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.qa.utilities.Base;
import com.qa.utilities.Utilities;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.RestClient;
import net.rcarz.jiraclient.greenhopper.SprintReport;
import net.rcarz.jiraclient.greenhopper.GreenHopperClient;
import net.rcarz.jiraclient.greenhopper.RapidView;
import net.rcarz.jiraclient.greenhopper.Sprint;
import net.rcarz.jiraclient.greenhopper.SprintIssue;
import net.rcarz.jiraclient.greenhopper.SprintReport;
import java.util.List;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.greenhopper.Backlog;
import net.rcarz.jiraclient.greenhopper.Epic;
import net.rcarz.jiraclient.greenhopper.GreenHopperClient;
import net.rcarz.jiraclient.greenhopper.Marker;
import net.rcarz.jiraclient.greenhopper.RapidView;
import net.rcarz.jiraclient.greenhopper.Sprint;
import net.rcarz.jiraclient.greenhopper.SprintIssue;
import net.rcarz.jiraclient.greenhopper.SprintReport;


public class JiraServiceProvider extends Base  {

	private static final RestClient RestClient = null;
	public JiraClient jira;
	public String project;
	public	String destinationScreenshotPath;
	
	public JiraServiceProvider(String jiraUrl, String username, String password, String project) {
		BasicCredentials creds = new BasicCredentials(username, password);
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;
	}






	@SuppressWarnings("unchecked")
	public void createJiraTicket(String issueType, String summary, String description, String MethodName) throws IOException {

		

		try {	

			/*Issue.SearchResult sr=jira.searchIssues("status = done AND summary ~ \"exception*\"");
				if(sr.total!=0) {

					System.out.println("SameÂ IssueÂ AlreadyÂ ExistsÂ onÂ Jira");

					return;
			}*/



			FluentCreate fleuntCreate = jira.createIssue(project, issueType);
			fleuntCreate.field(Field.SUMMARY, summary);
			fleuntCreate.field(Field.DESCRIPTION, description);
			fleuntCreate.field(Field.PRIORITY, "High");


			// fleuntCreate.field(Field.COMPONENTS, new ArrayList() {{add("BA");}});

		 //	fleuntCreate.field(Field.LABELS, new ArrayList() {{add("Automation,Vahana2.0,QA");}});
			//fleuntCreate.field(Field.ASSIGNEE,new ArrayList() {{add("Dhruv Chawla");}});
			//	fleuntCreate.field(Field.ATTACHMENT,screenshotPath1);
			//	fleuntCreate.field(Field.REPORTER,reporter);
			//	fleuntCreate.field(Field.ATTACHMENT,screen1 );
			//	fleuntCreate.field(Field.DUE_DATE,"2023-10-23");




		

			Issue newIssue = fleuntCreate.execute();
			System.out.println("new issue created in jira with ID: " + newIssue);

		
		
			String destinationScreenshotPath = Utilities.captureScreenshot(driver, MethodName);
			File finalDestination = new File(destinationScreenshotPath);


			newIssue.addComment("My comments"); 

			newIssue.addAttachment(finalDestination);

 
		} catch (JiraException e) {
			e.printStackTrace();
		}

	}


}

