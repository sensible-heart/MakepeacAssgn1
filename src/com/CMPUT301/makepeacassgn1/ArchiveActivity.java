package com.CMPUT301.makepeacassgn1;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ArchiveActivity extends ActionBarActivity {
	ListView archiveList;
	UpdateToDoLists FileUpdater= new UpdateToDoLists();
	List <ToDoItem> ArchiveToDos = new ArrayList<ToDoItem>();
	ToDoItemAdapter adapter;
	ArrayAdapter<ToDoList> listAdapter;

	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		
	}
	@Override
	protected void onStart(){
    	//TODO Auto-generated method stub
    	super.onStart();
    	FileUpdater = new UpdateToDoLists();
    	archiveList = (ListView)findViewById(R.id.ArchiveList);
    	if (ArchiveToDos != null)//checks to see basically if there are any items in currentToDos
        	ArchiveToDos = FileUpdater.loadFromFile2(ArchiveToDos,this);//then loads from file to populate
    	adapter = new ToDoItemAdapter(this,ArchiveToDos);
    	archiveList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
		return true;
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
}
