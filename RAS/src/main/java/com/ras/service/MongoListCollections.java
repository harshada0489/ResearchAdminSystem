package com.ras.service;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Eq;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.bson.types.ObjectId;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
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
import com.mongodb.client.model.Updates;
import com.ras.model.Question;
import com.ras.service.mongodbOperations.NextSequenceService;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.function.Consumer;

public class MongoListCollections {
	
	
    public static String createCollection(Integer formId) {

        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        String createTableName="";
        
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            MongoDatabase database = mongoClient.getDatabase("test");
            String tableName = "systemForm";
            int systemForm = formId;
            
            
             createTableName= getDynamicCreateTableNamebySystemFormFilterList(database, tableName, systemForm);
            System.out.println("createTableName =====" + createTableName);
            
     
            HashMap<String,String> hmap = new HashMap<>();
            
            
            if(createTableName.isEmpty()) {
            	System.out.println("Table exists with included systemForm =" + systemForm);
            }else {
//            	hmap= getDBcolumnName(database,createTableName, formId)	;

                System.out.println("hmap = " + hmap);
                
                createDynamicTable(database,createTableName);
            }
            
            
        }
        return createTableName;
    }

    
    public static String getDynamicCreateTableNamebySystemFormFilterList(MongoDatabase databaseName, String tableName ,int systemForm) {
    	System.out.println("inside method getDynamicCreateTableNamebySystemFormFilterList()");
    	MongoCollection<Document> systemFormDB = databaseName.getCollection(tableName);
    	
    	 FindIterable<Document> sysFormFit = systemFormDB.find(eq("_id",systemForm));
         
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

    public static String assignFormNumberDynamically(MongoDatabase databaseName, String firstPartOfTableName , int formId) {
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
    	if(sortedDBNames.size()>0) {
    		System.out.println("last element of the sorted List = " + sortedDBNames.get(sortedDBNames.size()-1));
    	}
    	
    	
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
    
    public static String createDynamicTable(MongoDatabase database,String createTableName) {
    	System.out.println("inside method createDynamicTable()");
    	try {

            database.createCollection(createTableName);
        } catch (MongoCommandException e) {

            database.getCollection(createTableName).drop();
        }
        
    
    return createTableName;
    }
    
    public static HashMap<String, String> getDBcolumnName(MongoDatabase database, String createTableName,Integer formId) {
    	
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
//                hmap.put("systemFormId", formId.toString());
                hmap.put("studyId", "");
                hmap.put("isLock", "disabled");
                
                Date createdDate = new java.util.Date() ;
                Date modifiedDate = new java.util.Date() ;
                
                hmap.put("createdDate", createdDate.toString());
                hmap.put("modifiedDate", modifiedDate.toString());


    	
    	return hmap;
    	
    }
    
    
    public static HashMap<String, String> getSystemFormByFilters(String filterCombo) {
    		ArrayList<String> sortedDBNames = new ArrayList<>();
    		HashMap<String, String> hmap = new HashMap<>();
    		
    		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.SEVERE);

            try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

                MongoDatabase database = mongoClient.getDatabase("test");
                
                for (String name : database.listCollectionNames()) {
    	    		if(name.contains(filterCombo)) {
    	    			sortedDBNames.add(name);
    	    		}
    	    		
    	        }
    	    	Collections.sort(sortedDBNames);
    	    	
    	    	System.out.println("sorted List = " + sortedDBNames);
    	    	
    	    	System.out.println("last element of the sorted List = " + sortedDBNames.get(sortedDBNames.size()-1));
                
    	    	String latestSystemForm= sortedDBNames.get(sortedDBNames.size()-1);
    	    	hmap.put("systemFormName", latestSystemForm);	
    	    	System.out.println("");
//    	    	MongoCollection<Document> collection = database.getCollection(latestSystemForm);
//    	    	System.out.println("latest System Form found in db =" + collection);
//
//    	    	
//    	    	FindIterable<Document> fit = collection.find();
//
//                for (Document docs : fit) {
//       					
//       					for(String key : docs.keySet()) {
//       						if(key.contains("_id")) {
//       							String value = docs.get(key).toString();
//           						System.out.println("studyAppDataId = "+ value);
//           						hmap.put("studyAppDataId",value );
//       						}
//       						
//       						if(key.contains("systemFormId")) {
//       							String value = docs.get(key).toString();
//           						System.out.println("systemFormId Id = "+ value);
//           						hmap.put("systemFormId",value );
//       						}
//       						
//       					}
//                }
                
                
    	    	return hmap;
            }

    }
    
    
    
   
	public static String insertStudyDataFormIdInDynamicTable(String dynamicTableName, Integer studyDataFormId, Integer systemFormId) throws Exception {
    	
    	Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

        	
        	HashMap<String,String> hmap = new HashMap<>();
            MongoDatabase database = mongoClient.getDatabase("test");
            

            MongoCollection<Document> collection = database.getCollection(dynamicTableName);
            hmap= getDBcolumnName(database,dynamicTableName, systemFormId)	;
            
            hmap.put("studyDataFormId", studyDataFormId+"");
            
            String dynamicTableDataId= insertInDynamicTable(database,dynamicTableName,hmap);

//            collection.updateOne(new Document("systemFormId", systemFormId+ ""),
//                    new Document("$set", new Document("studyDataFormId", studyDataFormId+"")));
            
            System.out.println("collection = " + collection);
            return dynamicTableDataId;
		}
	}
 
    public static String insertInDynamicTable(MongoDatabase database,String dynamicTableName,HashMap<String,String> hmap) throws Exception {
    	
    	ArrayList<Document> docs = new ArrayList<Document>();  
        
        MongoCollection<Document> collection = database.getCollection(dynamicTableName);
	
    	Document d1 = new Document();
    	
    	for(String key : hmap.keySet()) {
    		d1.append(key, hmap.get(key));
    	}
    	d1.append("isLock", "disabled");
    	
    	System.out.println("Before dynamicTableId generated");
 
    	System.out.println("hmap = " + hmap);
    	
    	d1.append( "_id", getNextSequence("customSequences"));
    	System.out.println("d1 = " + d1);

        docs.add(d1);
        System.out.println("docs = " + docs);
        collection.insertMany(docs);	
        String dynamicId = d1.get("_id").toString();
        System.out.println("dynamic id generated = " + dynamicId );
        return dynamicId;
    }
    
    
    public static Object getNextSequence(String name) throws Exception{
    	Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

        	
        	HashMap<String,String> hmap = new HashMap<>();
            MongoDatabase database = mongoClient.getDatabase("test");
            

            MongoCollection<Document> collection = database.getCollection("customSequences");
            BasicDBObject find =new BasicDBObject();
            find.put("_id", name);
            BasicDBObject update = new BasicDBObject();
            update.put("$inc",new BasicDBObject("dynamicFormTableIdSeq",1));
            Document obj = collection.findOneAndUpdate(find, update);
            return obj.get("dynamicFormTableIdSeq");
        }
    }
    
    public static void insertdbColumnNamesInDynamicTable(String dynamicTableName, Integer dynamicTableDataId, Map<String, Object> dbColumnNamesAnswerList) {
    	
    	System.out.println("Inside class : MongoListCollections and method : insertdbColumnNamesInDynamicTable()");
    	Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
        	HashMap<String,String> hmap = new HashMap<>();
            MongoDatabase database = mongoClient.getDatabase("test");
            

            MongoCollection<Document> collection = database.getCollection(dynamicTableName);
            
            BasicDBObject query = new BasicDBObject();
            query.put("_id", dynamicTableDataId);
          
           BasicDBObject newDocument = new BasicDBObject();
           
           for(String key : dbColumnNamesAnswerList.keySet()) {
        	   newDocument.put(key,dbColumnNamesAnswerList.get(key));
           }
           
           BasicDBObject updateObject = new BasicDBObject();
           updateObject.put("$set", newDocument);
           
           collection.updateOne(query, updateObject);
            
           FindIterable<Document> iterDoc = collection.find();
           Iterator it = iterDoc.iterator();
           while (it.hasNext()) {
              System.out.println(it.next());
           }
    
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
//	public static void insertInDynamicTable(String systemFormName, Map<String, String> answerList) {
//		ArrayList<String> sortedDBNames = new ArrayList<>();
//		Map<String, Object> saveDynamicMap = new HashMap<>();
//		
//		
//		Iterator<String> ansIterator = answerList.keySet().iterator();
//		while(ansIterator.hasNext()) {
//			String key = ansIterator.next();
//			if(!(key == "studyAppDataId")) {
//				String value = answerList.get(key);
//				saveDynamicMap.put(key,value );
//
//			}
//		}
//		
//		saveDynamicMap.put("createdDate", new Date());
//		saveDynamicMap.put("modifiedDate", new Date());
//		saveDynamicMap.put("isLock", "disabled");
//		saveDynamicMap.put("status", "active");
//        
//		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
//        mongoLogger.setLevel(Level.SEVERE);
//
//        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
//
//            MongoDatabase database = mongoClient.getDatabase("test");
//            
//            String dynamicTable = answerList.get("");
//            MongoCollection<Document> collection = database.getCollection(systemFormName);
//            
//            ArrayList<Document> docs = new ArrayList<Document>();  
//            
//        	Document d1 = new Document();
//        	
//        	for(String key : saveDynamicMap.keySet()) {
//        		d1.append(key, saveDynamicMap.get(key));
//        	}
//
//            docs.add(d1);
//        	
//        collection.insertMany(docs);
//        FindIterable<Document> fit =collection.find();
//        
//        
//        for (Document document : fit) {
//				
//				for(String key : document.keySet()) {
//					if(key.contains("_id")) {
//						String value = document.get(key).toString();
//						System.out.println("dynamic form inserted values in _id = " + value);
//						
//					}
//				}
//    }
//       
//      
//  
//            
//        }
//		
//		
//
//        }


    
    

//    public static List<Document> getQuestionSet(String systemFormId, String pageNumber) {
//    	
//    	List<Document> list = new ArrayList<>();
//    	
//    	
//    	Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
//        mongoLogger.setLevel(Level.SEVERE);
//
//        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
//
//            MongoDatabase database = mongoClient.getDatabase("test");
//            
//            MongoCollection<Document> collection = database.getCollection("question");
//            
//            FindIterable<Document> fit = collection.find(and(eq("formId", systemFormId), eq("pageNumber", pageNumber)));
//
//            for (Document docs : fit) {
//            	
//            	System.out.println("docs = " + docs);
//                
//            	list.add(docs);
//            } 
//        }
//    	
//    	
//    	
//    	return list;
//    }
    
}