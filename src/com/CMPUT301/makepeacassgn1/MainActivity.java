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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;



public class MainActivity extends ActionBarActivity {
	EditText ItemAdder;
	String[] ToDos;
	List<ToDoItem> CurrentToDos = new ArrayList<ToDoItem>();
	ArrayAdapter<ToDoList> listAdapter;
	ListView ToDoListView;
	ArrayAdapter<ToDoItem> adapter;
	//CheckedTextView CheckItem;
	private static final String FILENAME = "file.sav";

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ItemAdder = (EditText)findViewById(R.id.AddItems);
        Button Add = (Button)findViewById(R.id.AddButton);
        ListView ToDoListView = (ListView)findViewById(R.id.ToDoListView);
        final CheckedTextView CheckItem = (CheckedTextView)findViewById(R.id.CheckedView);
        
//        ToDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//        	public void onItemClick(View v){
//        		
//        	}
//        });
        
        Add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		    	setResult(RESULT_OK);
		    	String item = ItemAdder.getText().toString();;
		    	ToDoItem now = new ToDoItem(item);
		    	CurrentToDos.add(now);
		    	ItemAdder.setText("");
		    	saveInFile();
		    	adapter.notifyDataSetChanged();
			}
		});
        
        
        
//        ToDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
//            
//        	@Override  
//            public void onItemClick( AdapterView<?> parent, View item,int position, long id) {  
//				// TODO Auto-generated method stub
//        		//ToDoI List = listAdapter.getItem(position);
//        		ToDoItem current = (ToDoItem) listAdapter.getItem(position);
////        		if(CheckItem.isChecked()){
////        			CheckItem.setChecked(false);
////        		} else {
////        			CheckItem.setChecked(true);
////        		}
//        		CheckedTextView checkedItem = (CheckedTextView)item.findViewById(R.id.CheckedView);
//        		checkedItem.toggle();
//        		current.changeChecked();
//        		CheckBoxItemView CheckBoxView = (CheckBoxItemView)item.getTag();
//        		CheckBoxView.getCheckBox().setChecked(current.isChecked());
//			}
//		});
        
        
    }

    @Override
    protected void onStart(){
    	//TODO Auto-generated method stub
    	super.onStart();
    	ToDoListView = (ListView)findViewById(R.id.ToDoListView);
    	if (CurrentToDos != null)
        	loadFromFile();
    	adapter = new ToDoItemAdapter(this,CurrentToDos);
    	ToDoListView.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void loadFromFile(){
		try {
			FileInputStream fis = openFileInput(FILENAME);
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
	}
    
    
    private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,0);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void updateData()
    {
	  //TODO Auto-generated method stub
		super.onStart();
		ToDoListView = (ListView)findViewById(R.id.ToDoListView);
		loadFromFile();
		ArrayAdapter<ToDoItem> adapter = new ArrayAdapter<ToDoItem>(this,R.layout.blandlayout, CurrentToDos);
		ToDoListView.setAdapter(adapter);
    	
    }
}
