package com.company;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

public class QueryPrintAll {

    public void printAllDocuments() {

        //connection to MongoDB Collection
        ConnectionToMongo connectionToMongo = new ConnectionToMongo();
        DBCollection collection = connectionToMongo.connection();  //returns collection

        //ArrayList to store documents
        List <PersonalDetails> profileList = new ArrayList<>();

        //Query Find all documents in Collection
        DBCursor cursor = collection.find();

        while (cursor.hasNext()) {

            BasicDBObject profileObject = (BasicDBObject) cursor.next(); //Creating BasicDBObject

            //converting Mongo Object (BSON) to java object
            ConvertingBsonToJava convert = new ConvertingBsonToJava();
            PersonalDetails personalDetails = convert.convertingObject(profileObject);

            //adding java object to list
            profileList.add(personalDetails);
            System.out.println(personalDetails);
        }

        System.out.println("");
        System.out.println("Total amount in collection " + collection.count());
        System.out.println("");
    }
}