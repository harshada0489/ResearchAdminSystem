package com.ras.service;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Eq;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.bson.types.ObjectId;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoCommandException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.function.Consumer;

public class MongoListCollections {
	

    public static void createCollection(String formId) {

        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            MongoDatabase database = mongoClient.getDatabase("test");
            String tableName = "systemForm";
            String systemForm = formId;
            
            
            String createTableName= getDynamicCreateTableNamebySystemFormFilterList(database, tableName, systemForm);
            System.out.println("createTableName =====" + createTableName);
            
     
            HashMap<String,String> hmap = new HashMap<>();
            
            
            if(createTableName.isEmpty()) {
            	System.out.println("Table exists with included systemForm =" + systemForm);
            }else {
            	hmap= getDBcolumnName(database,createTableName, formId)	;

                System.out.println("hmap = " + hmap);
                
                createDynamicTable(database,createTableName, hmap);
            }
            
            
        }
    }

    
    public static String getDynamicCreateTableNamebySystemFormFilterList(MongoDatabase databaseName, String tableName ,String systemForm) {
    	System.out.println("inside method getDynamicCreateTableNamebySystemFormFilterList()");
    	MongoCollection<Document> systemFormDB = databaseName.getCollection(tableName);
    	
    	 FindIterable<Document> sysFormFit = systemFormDB.find(eq("_id", new ObjectId(systemForm)));
         
         String filterList="";
         
         
         
         for (Document docs : sysFormFit) {
				if(docs.containsKey("filterList")) {
					
					filterList = (String)docs.get("filterList");
					filterList = filterList.replaceAll(",","_");
					
					System.out.println(filterList);
				}
             
         }
         
         String name = assignFormNumberDynamically(databaseName, filterList, systemForm);
         System.out.println("name = " + name);
         if(name.isEmpty()) {
        	 return name;
         }else {
        	 filterList = filterList.concat("_").concat(name);
         }
    	return filterList;
    }

    public static String assignFormNumberDynamically(MongoDatabase databaseName, String firstPartOfTableName , String formId) {
    	System.out.println("inside method assignFormNumberDynamically()");
    	String formNumber = "";
    	
    	ArrayList<String> sortedDBNames = new ArrayList<>();
    	
    	for (String name : databaseName.listCollectionNames()) {
    		if(name.contains(firstPartOfTableName)) {
    			sortedDBNames.add(name);
    		}
    		
        }
    	Collections.sort(sortedDBNames);
    	
    	System.out.println("sorted List = " + sortedDBNames);
    	System.out.println("last element of the sorted List = " + sortedDBNames.get(sortedDBNames.size()-1));
    	
    	if(sortedDBNames.isEmpty()) {
    		int counter  = 1;
    		formNumber = "00" + counter;
    	}else {
    		
    		String exists = "false";
    		
    		for (String name : sortedDBNames) {
    			MongoCollection<Document> collection = databaseName.getCollection(name);
    			FindIterable<Document> fit = collection.find();

	       		 for (Document docs : fit) {
	       				if(docs.containsKey("systemFormId")) {
	
	       					String systemFormId = docs.get("systemFormId").toString();
	       					
	       					
	       					if(systemFormId.equals(formId)) {
	       						exists = "true";
//	       						System.out.println("formId already exists");
	       						 break;
	       					}
	                    
	                }
       		 
        		
            }
    		
    		
	       		
				}
    		
    		
    		if (exists.equalsIgnoreCase("false")){
				System.out.println("formId does not exists");
				
				String lastInsertedForm = sortedDBNames.get(sortedDBNames.size()-1).toString();
				String [] bits = lastInsertedForm.split("_");
	    		String lastOne = bits[bits.length-1];
	    		int counter = Integer.parseInt(lastOne);
	    		if(counter != 0) {
	    			counter++;
	    			formNumber = "00" + counter;
	    		}
	    		
	    		
			}else {
				formNumber = "";
			}
    		

    		 System.out.println("form Number = " + formNumber);
    		
    		
//    		Random rand = new Random();
//    		int counter = rand.nextInt(1000);
    		
    	}
       
    	return formNumber;
    }
    
    public static void createDynamicTable(MongoDatabase database,String createTableName,HashMap<String, String> hmap) {
    	System.out.println("inside method createDynamicTable()");
    	try {

            database.createCollection(createTableName);
        } catch (MongoCommandException e) {

            database.getCollection(createTableName).drop();
        }
        ArrayList<Document> docs = new ArrayList<Document>();  
        
        MongoCollection<Document> collection = database.getCollection(createTableName);
	
    	Document d1 = new Document();
    	
    	for(String key : hmap.keySet()) {
    		d1.append(key, hmap.get(key));
    	}
    	d1.append("isLock", "disabled");

        docs.add(d1);
    	
    collection.insertMany(docs);	
    }
    
    public static HashMap<String, String> getDBcolumnName(MongoDatabase database,String createTableName,String formId) {
    	
    	HashMap<String, String> hmap = new HashMap<>();
                MongoCollection<Document> collection = database.getCollection("question");
              
                FindIterable<Document> fit = collection.find(eq("formId", formId));

                for (Document docs : fit) {
       				if(docs.containsKey("dbColumnName")) {

       					System.out.println( "dbColumnName =" + docs.get("dbColumnName"));
       					String questionColumnName = docs.get("dbColumnName").toString();
       					hmap.put(questionColumnName, "");
       				}
                    
                }
      
                hmap.put("status", "active");
                hmap.put("systemFormId", formId);
                hmap.put("studyId", "");
                hmap.put("isLock", "disabled");
                
                Date createdDate = new java.util.Date() ;
                Date modifiedDate = new java.util.Date() ;
                
                hmap.put("createdDate", createdDate.toString());
                hmap.put("modifiedDate", modifiedDate.toString());


    	
    	return hmap;
    	
    }
    
}