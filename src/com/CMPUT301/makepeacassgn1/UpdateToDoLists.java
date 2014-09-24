package com.CMPUT301.makepeacassgn1;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class UpdateToDoLists {
	private static final String FILENAME = "file.sav";//name of file we will save list items to for CurrentToDo items
	private static final String FILENAME2 = "file2.sav";
	
	public UpdateToDoLists(){
		super();
	}
	
    public void saveInFile(List <ToDoItem> CurrentToDos, Context context) {
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME,0);
			Gson gson = new Gson();
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(CurrentToDos,osw);
			osw.flush();
			fos.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public List<ToDoItem> loadFromFile(List <ToDoItem> CurrentToDos, Context context){
		try {
			FileInputStream fis =  context.openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			//learned in Lab 3 09/23/14
			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
			CurrentToDos = gson.fromJson(in, listType);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CurrentToDos;
	}
    public void saveInFile2(List <ToDoItem> ArchiveToDos, Context context) {
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME2,0);
			Gson gson = new Gson();
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(ArchiveToDos,osw);
			osw.flush();
			fos.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public List<ToDoItem> loadFromFile2(List <ToDoItem> ArchiveToDos, Context context){
		try {
			FileInputStream fis =  context.openFileInput(FILENAME2);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			//learned in Lab 3 09/23/14
			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
			ArchiveToDos = gson.fromJson(in, listType);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ArchiveToDos;
	}

}
