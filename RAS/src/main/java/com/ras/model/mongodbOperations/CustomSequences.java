package com.ras.model.mongodbOperations;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customSequences")
public class CustomSequences {

	 	@Id
	    private String id;
	 	
	    private int systemFormIdSeq;
	    
	    private int pageIdSeq;
	    
	    private int questionIdSeq;
	    
	    private int dynamicFormTableIdSeq;
	    
	    private int studyApplicationIdSeq;
	    
	    private int studyDataFormIdSeq;
	    
	    private int userIdSeq;
	    
	    private int studyContactsIdSeq;
	    
	    private int rbStudyApplicationIdSeq;
	    
	    private int loginHistoryIdSeq;
	    

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public int getSystemFormIdSeq() {
			return systemFormIdSeq;
		}
		public void setSystemFormIdSeq(int systemFormIdSeq) {
			this.systemFormIdSeq = systemFormIdSeq;
		}
		public int getPageIdSeq() {
			return pageIdSeq;
		}
		public void setPageIdSeq(int pageIdSeq) {
			this.pageIdSeq = pageIdSeq;
		}
		public int getQuestionIdSeq() {
			return questionIdSeq;
		}
		public void setQuestionIdSeq(int questionIdSeq) {
			this.questionIdSeq = questionIdSeq;
		}
		public int getDynamicFormTableIdSeq() {
			return dynamicFormTableIdSeq;
		}
		public void setDynamicFormTableIdSeq(int dynamicFormTableIdSeq) {
			this.dynamicFormTableIdSeq = dynamicFormTableIdSeq;
		}
		public int getStudyApplicationIdSeq() {
			return studyApplicationIdSeq;
		}
		public void setStudyApplicationIdSeq(int studyApplicationIdSeq) {
			this.studyApplicationIdSeq = studyApplicationIdSeq;
		}
		public int getStudyDataFormIdSeq() {
			return studyDataFormIdSeq;
		}
		public void setStudyDataFormIdSeq(int studyDataFormIdSeq) {
			this.studyDataFormIdSeq = studyDataFormIdSeq;
		}
		public int getUserIdSeq() {
			return userIdSeq;
		}
		public void setUserIdSeq(int userIdSeq) {
			this.userIdSeq = userIdSeq;
		}
		public int getStudyContactsIdSeq() {
			return studyContactsIdSeq;
		}
		public void setStudyContactsIdSeq(int studyContactsIdSeq) {
			this.studyContactsIdSeq = studyContactsIdSeq;
		}
		public int getRbStudyApplicationIdSeq() {
			return rbStudyApplicationIdSeq;
		}
		public void setRbStudyApplicationIdSeq(int rbStudyApplicationIdSeq) {
			this.rbStudyApplicationIdSeq = rbStudyApplicationIdSeq;
		}
		public int getLoginHistoryIdSeq() {
			return loginHistoryIdSeq;
		}
		public void setLoginHistoryIdSeq(int loginHistoryIdSeq) {
			this.loginHistoryIdSeq = loginHistoryIdSeq;
		}
		
		
	    
}
