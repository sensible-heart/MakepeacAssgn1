package com.CMPUT301.makepeacassgn1;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
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
		ListView archiveList = (ListView) findViewById(R.id.ArchiveList);
		registerForContextMenu(archiveList);
		
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
    //Adapted from a tutorial http://www.mikeplate.com/2010/01/21/show-a-context-menu-for-long-clicks-in-an-android-listview/ on 09/23/14
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
      if (v.getId()==R.id.ArchiveList) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(ArchiveToDos.get(info.position).GetName());
        String[] menuItems = getResources().getStringArray(R.array.Archive);
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
      String[] menuItems = getResources().getStringArray(R.array.Archive);
      String menuItemName = menuItems[menuItemIndex];
      String listItemName = ArchiveToDos.get(info.position).GetName();
      ToDoItem current = ArchiveToDos.get(info.position);
      if (menuItemIndex == 0){//deletes chosen Archive Item
    	  ArchiveToDos.remove(info.position);
      }
      FileUpdater.saveInFile2(ArchiveToDos, this);
      adapter.notifyDataSetChanged();
      return true;
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
