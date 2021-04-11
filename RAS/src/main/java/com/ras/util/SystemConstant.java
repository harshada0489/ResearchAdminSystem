package com.ras.util;

public class SystemConstant {

	// study component types
	public static Integer STUDY_COMPONENT_APPLICATION = 1;
	
	
	
	
	// study contact type
	 public static Integer TYPE_PRINCIPAL_INVESTIGATOR = 1;
	 public static Integer TYPE_STUDY_AUTOR = 2;
	 public static Integer TYPE_STUDY_CONTACT = 3;
	 public static Integer TYPE_REVIEW = 4;
	 
	 
	 public static int STATE_DRAFT = 1000;
	 public static int STATE_PI = 2000;
	 public static int STATE_GATEKEEPER = 3000;
	 public static int STATE_REVIEWBOARD = 4000;
	 public static int STATE_RETURN_TO_RESEARCHER = 5000;
	 
	 
	 public static int TASK_STATUS_UNREAD = 10;
	 public static int TASK_STATUS_IN_PROGRESS = 20;
	 public static int TASK_STATUS_COMPLETE = 30;
	 
	 
	 public static String REVIEW_OUTCOME_APPROVED = "Approved";
	 public static String REVIEW_OUTCOME_REJECTED = "Rejected";
	 public static String REVIEW_OUTCOME_CORRECTIONS = "Corrections";
	 
	 
	 
	 public static int  REVIEWBOARD_GATEKEEPER_ID = 5;
	 public static int REVIEWBOARD_GATEKEEPER_USERID = 1;
	 public static int REVIEWBOARD_IRB_ID = 10;
	 public static int REVIEWBOARD_IRB_USERID = 4;
	 
	 
	 

	 public static String STUDY_APP_STATUS_APPROVED = "Study Approved";
	 public static String STUDY_APP_STATUS_REJECTED = "Study Rejected";
	 
	 
	 
	 
	 
	
//	public enum CountryAndOneContinent {
//
//	    PRINCIPAL_INVESTIGATOR(""),
//	    RUSSIA("Russia"),
//	    NORTH_AMERICA("North America");
//
//	    private String displayName;
//
//	    CountryAndOneContinent(String displayName) {
//	        this.displayName = displayName;
//	    }
//
//	    public String displayName() { return displayName; }
//
//	    // Optionally and/or additionally, toString.
//	    @Override public String toString() { return displayName; }
//	}
}
