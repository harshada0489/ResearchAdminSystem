package com.ras.service.mongodbOperations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import com.ras.model.mongodbOperations.CustomSequences;

@Service
public class NextSequenceService {
	
	@Autowired
	private MongoOperations mongo;
	
	
	public int getNextSequenceForSystemFormId(String seqName) {
		
		CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("systemFormIdSeq", 1),  options().returnNew(true).upsert(true),CustomSequences.class);
		return counter.getSystemFormIdSeq();
	}
	
public int getNextSequenceForPageId(String seqName) {
		
		CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("pageIdSeq", 1),  options().returnNew(true).upsert(true),CustomSequences.class);
		return counter.getPageIdSeq();
	}


public int getNextSequenceForQuestionId(String seqName) {
	
	CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("questionIdSeq", 1),  options().returnNew(true).upsert(true),CustomSequences.class);
	return counter.getQuestionIdSeq();
}


public int getNextSequenceForDynamicFormTableId(String seqName) {
	
	CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("dynamicFormTableIdSeq", 1),  options().returnNew(true).upsert(true),CustomSequences.class);
	return counter.getDynamicFormTableIdSeq();
}

public int getNextSequenceForstudyApplicationId(String seqName) {
	
	CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("studyApplicationIdSeq", 1),  options().returnNew(true).upsert(true),CustomSequences.class);
	return counter.getStudyApplicationIdSeq();
}

public int getNextSequenceForstudyDataFormId(String seqName) {
	
	CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("studyDataFormIdSeq", 1),  options().returnNew(true).upsert(true),CustomSequences.class);
	return counter.getStudyDataFormIdSeq();
}

public int getNextSequenceForuserId(String seqName) {
	
	CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("userIdSeq", 1),  options().returnNew(true).upsert(true),CustomSequences.class);
	return counter.getUserIdSeq();
}


public int getNextSequenceForStudyContactsId(String seqName) {
	
	CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("studyContactsIdSeq", 1),  options().returnNew(true).upsert(true),CustomSequences.class);
	return counter.getStudyContactsIdSeq();
}








//
//private int userIdSeq;

}
