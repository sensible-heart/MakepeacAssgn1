package com.CMPUT301.makepeacassgn1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
	//CheckedTextView CheckItem;
	private static final String FILENAME = "file.sav";

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ItemAdder = (EditText)findViewById(R.id.AddItems);
        Button Add = (Button)findViewById(R.id.AddButton);
        ListView ToDoListView = (ListView)findViewById(R.id.ToDoListView);
        
//        final CheckedTextView CheckItem = (CheckedTextView)findViewById(R.id.CheckedView);
//        CheckItem.setOnClickListener(new View.OnClickListener(){
//        	@Override
//        	public void onClick(View v){
//        		if(CheckItem.isChecked()){
//        			CheckItem.setChecked(false);
//        		} else {
//        			CheckItem.setChecked(true);
//        		}
//        	}
//        });
        
        Add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		    	setResult(RESULT_OK);
		    	String item = ItemAdder.getText().toString();;
		    	ToDoItem now = new ToDoItem(item);
		    	saveInFile(item);
		    	CurrentToDos.add(now);
		    	ItemAdder.setText("");
		    	updateData();
			}
		});
        
        
        
        ToDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
            
        	@Override  
            public void onItemClick( AdapterView<?> parent, View item,int position, long id) {  
				// TODO Auto-generated method stub
        		//ToDoI List = listAdapter.getItem(position);
        		ToDoItem current = (ToDoItem) listAdapter.getItem(position);
        		current.changeChecked();
        		CheckBoxItemView CheckBoxView = (CheckBoxItemView)item.getTag();
        		CheckBoxView.getCheckBox().setChecked(current.isChecked());
			}
		});
        
        
    }
    	
    	
    
//    protected void onClose(){
//    	
//    }

    @Override
    protected void onStart(){
    	//TODO Auto-generated method stub
    	super.onStart();
    	ToDoListView = (ListView)findViewById(R.id.ToDoListView);
    	ToDos = loadFromFile();
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.blandlayout, ToDos);
    	ToDoListView.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private String[] loadFromFile(){
    	ArrayList<String> ToDos = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				ToDos.add(line);
				line = in.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ToDos.toArray(new String[ToDos.size()]);
	}
    
    
    private void saveInFile(String name) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
//			for (t i=0;i<CurrentToDos.size();i++){
//				String name = CurrentToDos.get(i).GetName();
			fos.write(new String(name+"\n").getBytes());
			//}
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
		ToDos = loadFromFile();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.blandlayout, ToDos);
		ToDoListView.setAdapter(adapter);
    	
    }
}
