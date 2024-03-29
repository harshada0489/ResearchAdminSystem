package com.ras.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.StudyDataForm;
import com.ras.repository.QuestionRepository;
import com.ras.repository.StudyDataFormRepository;
import com.ras.service.mongodbOperations.NextSequenceService;

@Service
public class StudyDataFormService {

	@Autowired
	StudyDataFormRepository repository;
	
	
	@Autowired
	NextSequenceService nextSequenceService;
	
	
	
	public int updateStudyDataForm(HashMap<String, String> hmap ) {
		System.out.println("Inside class : StudyDataFormService and method : updateStudyDataForm()");
		Integer studyAppId = null;
		Integer systemFormId = null;
		String dynamicTableName = "";
		Integer version = null;
		
		
		
		for(String key : hmap.keySet()) {
			if(key == "studyAppId") {
				String studyAppIdString = hmap.get(key);
				studyAppId = Integer.parseInt(studyAppIdString);
			}
			if(key == "systemFormId") {
				String systemFormIdString = hmap.get(key);
				System.out.println("systemFormIdString = " + systemFormIdString);
				systemFormId = Integer.parseInt(systemFormIdString);
			}
			if(key == "dynamicTableName") {
				 dynamicTableName = hmap.get(key);
			}
			
			if(key == "version") {
				String versionString = hmap.get(key);
				version = Integer.parseInt(versionString);
			}
			
		}
		System.out.println("studyAppId = " + studyAppId + " systemFormId ="+ systemFormId + " dynamicTableName = " + dynamicTableName);
		StudyDataForm studyDataForm = new StudyDataForm(studyAppId, systemFormId, dynamicTableName) ;
		
		int seq = nextSequenceService.getNextSequenceForstudyDataFormId("customSequences");
		System.out.println("studyDataForm Id generated = " + seq);
		studyDataForm.setId(seq);

		
		studyDataForm.setRound(1);
		studyDataForm.setStatus("active");
		studyDataForm.setCreatedDate(new Date());
		studyDataForm.setModifiedDate(new Date());
		studyDataForm.setDynamicTableFormVersion(version+"");
		studyDataForm.setLock(false);
		
		repository.save(studyDataForm);
		int systemFormDataId = studyDataForm.getId();
		
		return systemFormDataId;
	}
	
	public StudyDataForm getStudyDataApp(Integer systemFormDataId) {
		Optional<StudyDataForm> db = repository.findById(systemFormDataId);
	
		StudyDataForm studyDataForm= null;
		
		if(db.isPresent()) {
			 studyDataForm =db.get();
			
			String dynamicTable = studyDataForm.getDynamicTableName();
			
		}
		return studyDataForm;
	}
	
	public Integer updateDynamicTable(String dynamicTableName, Integer studyDataFormId, Integer systemFormId) throws Exception {
		
		Integer dynamicTableDataId = null;
		
		String dynamicTableDataIdString = MongoListCollections.insertStudyDataFormIdInDynamicTable(dynamicTableName, studyDataFormId, systemFormId);
		
		dynamicTableDataId = Integer.parseInt(dynamicTableDataIdString);
		return dynamicTableDataId;
	}
	
	public void updateStudyDataFormWithDynamicId(StudyDataForm studyDataForm, Integer dynamicTableDataId) {
		studyDataForm.setDynamicTableDataId(dynamicTableDataId);
		repository.save(studyDataForm);
	}
	
public StudyDataForm getStudyDataFormObj(int studyDataFormId) {
	StudyDataForm studyDataForm = null;
//	Integer studyDataFormId = Integer.parseInt(studyDataFormIdString);
	Optional<StudyDataForm> db = repository.findById(studyDataFormId);
	if(db.isPresent()) {
		studyDataForm = db.get();
	}
	return studyDataForm;
	
}


public StudyDataForm getStudyDataFormObjByStudyId(Integer studyAppId) {
	List<StudyDataForm> studyDataFormList =repository.findByStudyAppIdOrderByIdDesc(studyAppId);
	StudyDataForm studyDataForm = null;
	
	if(studyDataFormList.size()>0 ) {
		studyDataForm = studyDataFormList.get(0);
	}
	
	return studyDataForm;
	
}

public StudyDataForm getCurrentStudyDataForm(Integer currentStudyDataFormId) {
	StudyDataForm studyDataForm =null;
	Optional<StudyDataForm> db =repository.findById(currentStudyDataFormId);
	if(db.isPresent()) {
		 studyDataForm = db.get();

	}
	
	return studyDataForm;
	
}

public void updateIsLock(Integer studyAppId) {
	System.out.println("In class: StudyDataFormService and method: updateIsLock()");
	StudyDataForm studyDataForm =getStudyDataFormObjByStudyId(studyAppId);
	System.out.println("studyDataForm = " + studyDataForm.getId());
	studyDataForm.setLock(true);
	repository.save(studyDataForm);
	
}

public StudyDataForm fetchStudyDataForm(int studyDataFormId){
	StudyDataForm studyDataForm = null;
	studyDataForm = getStudyDataFormObj(studyDataFormId);
	return studyDataForm;
}

public int createNewRowInStudyDatForm(int studyDataFormId) throws Exception {
	StudyDataForm oldStudyDataForm = null;
	int newStudyDataFormId = 0;
	oldStudyDataForm = fetchStudyDataForm(studyDataFormId);
	if(oldStudyDataForm !=null) {
		int round = oldStudyDataForm.getRound();
		
		 int dynamicTableDataId = oldStudyDataForm.getDynamicTableDataId();
		 String oldDynamicTableName = oldStudyDataForm.getDynamicTableName();
		 
		 int newDynamicTableDataId =MongoListCollections.getAndInsertNewRowInDynamicTable(oldDynamicTableName, dynamicTableDataId);
		 
		 
		StudyDataForm newStudyDataForm = new StudyDataForm(oldStudyDataForm.getStudyAppId(), oldStudyDataForm.getSystemFormId(), oldDynamicTableName);
		newStudyDataForm.setRound(round + 1);
		newStudyDataForm.setDynamicTableDataId(newDynamicTableDataId);
		newStudyDataForm.setLock(false);
		
		int seq = nextSequenceService.getNextSequenceForstudyDataFormId("customSequences");
		System.out.println("studyDataForm Id generated = " + seq);
		newStudyDataForm.setId(seq);
		
		repository.save(newStudyDataForm);
		
		 newStudyDataFormId = newStudyDataForm.getId();
		
	}else {
		throw new Exception("studyDataForm doesnt Exist ...");
	}
	
	return newStudyDataFormId;
	
}

}
