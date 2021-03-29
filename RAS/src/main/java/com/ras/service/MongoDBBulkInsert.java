package com.ras.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBBulkInsert {

	public static int insertInDynamicTable(String systemFormName, Map<String, String> answerList) throws UnknownHostException{
		int dynamicFormId=0 ;
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase("test");

            MongoCollection<Document> collection = database.getCollection(systemFormName);

    		BasicDBObject doc = BasicDBObject.parse("{_id : '" + ObjectId.get() + "'}");
    		
    		for(String key : answerList.keySet()) {
    			
    			if(key != "studyAppDataId") {
    				doc.put(key, answerList.get(key));	
    			}
    		}
    		doc.put("createdDate", new Date());	
    		doc.put("modifiedDate", new Date());	
    		doc.put("isLock", "disabled");	
    		doc.put("createdDate", new Date());	
    		doc.put("status", "active");	
    		
    		collection.insertOne(new Document(doc));
            FindIterable<Document> insertedDoc = collection.find(new Document(doc));
            
            for (Document d : insertedDoc) {
            	System.out.println("insertedDoc id =============" + d.getString("_id"));
            	dynamicFormId = d.getInteger("_id");
            }
            mongoClient.close();

        }
		
		return dynamicFormId; 
    }
	

}
