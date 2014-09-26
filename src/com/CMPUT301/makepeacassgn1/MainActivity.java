package com.CMPUT301.makepeacassgn1;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	private EditText ItemAdder;
	private List<ToDoItem> ArchiveToDos = new ArrayList<ToDoItem>();
	private List<ToDoItem> CurrentToDos = new ArrayList<ToDoItem>();
	private ListView ToDoListView;
	private ToDoItemAdapter adapter;//creates a new item adapter to use for displaying list items
	private UpdateToDoLists FileUpdater= new UpdateToDoLists();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ItemAdder = (EditText)findViewById(R.id.AddItems);//grabs id of edittext box which we use to grab its contents
        Button Add = (Button)findViewById(R.id.AddButton);//grabs id of the Add button
        ListView ToDoListView = (ListView)findViewById(R.id.ToDoListView);//grabs ID of the listview for our main activity
        
        Add.setOnClickListener(new View.OnClickListener() {//once the add button is clicked that triggers the save mechanism for our entered text
			
			@Override
			public void onClick(View v) {
		    	setResult(RESULT_OK);
		    	String item = ItemAdder.getText().toString();//this is the text pulled from our edittext box
		    	ToDoItem now = new ToDoItem(item);//creates a new ToDo item out of our pulled text
		    	CurrentToDos.add(now);//adds this new item to our list of CurrentToDos
		    	ItemAdder.setText("");//resets the edit text box to a blank string deleting our current text making the box ready for the new entry
		    	
		    	FileUpdater.saveInFile(CurrentToDos, getBaseContext());//saves our CurrentToDos list into a file for use later
		    	adapter.notifyDataSetChanged();//tells the adapter to update the listview
			}
		});
        
        registerForContextMenu(ToDoListView);  
    }
    public void toArchive(View view){
    	Intent intent = new Intent(this,ArchiveActivity.class);
    	startActivity(intent);
    }
    //Adapted from a tutorial http://www.mikeplate.com/2010/01/21/show-a-context-menu-for-long-clicks-in-an-android-listview/ on 09/23/14
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
      if (v.getId()==R.id.ToDoListView) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(CurrentToDos.get(info.position).GetName());
        String[] menuItems = getResources().getStringArray(R.array.menu);
        for (int i = 0; i<menuItems.length; i++) {
          menu.add(Menu.NONE, i, i, menuItems[i]);
        }
      }
    }
  //Adapted from a tutorial http://www.mikeplate.com/2010/01/21/show-a-context-menu-for-long-clicks-in-an-android-listview/ on 09/23/14
    @Override
    public boolean onContextItemSelected(MenuItem item) {
      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
      int menuItemIndex = item.getItemId();
      ToDoItem current = CurrentToDos.get(info.position);
      if (menuItemIndex == 0){
    	  moveToArchive(current);
      }
      if (menuItemIndex == 1){
    	  CurrentToDos.remove(info.position);
      }
      FileUpdater.saveInFile(CurrentToDos, this);
      adapter.notifyDataSetChanged();
      return true;
    }

    @Override
    protected void onStart(){
    	//TODO Auto-generated method stub
    	super.onStart();
    	FileUpdater = new UpdateToDoLists();
    	ToDoListView = (ListView)findViewById(R.id.ToDoListView);
    	if (CurrentToDos != null)//checks to see basically if there are any items in currentToDos
        	CurrentToDos = FileUpdater.loadFromFile(CurrentToDos,this);//then loads from file to populate
    	adapter = new ToDoItemAdapter(this,CurrentToDos);//creates a custom adapter for CurrentToDos
    	ToDoListView.setAdapter(adapter);//sets the adapter to reflect CurrentToDos
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void moveToArchive(ToDoItem item){
    	for (int i=0; i<CurrentToDos.size();i++){//goes through all items of CurrentToDos and removes each instance of item
    		if (CurrentToDos.get(i)==item){
    			CurrentToDos.remove(i);//removes instance of i from CurrentToDos
    		}	
    	}
    	ArchiveToDos.add(item);//adds the desired item to our List of archived items
    	FileUpdater.saveInFile2(ArchiveToDos,this);//saves the new ArchiveToDos list complete with new items
    	FileUpdater.saveInFile(CurrentToDos, this);//saves the altered CurrentToDos
    	adapter.notifyDataSetChanged();//tells the view to change to reflect list changes
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
