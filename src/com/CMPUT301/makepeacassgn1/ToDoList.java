package com.CMPUT301.makepeacassgn1;

import java.util.ArrayList;
import java.util.List;


public class ToDoList{
	public List<ToDoItem> CurrentList = new ArrayList<ToDoItem>();
	public ToDoList(){
		//empty constructor
	}
	public void AddItemToList(String Item){
		ToDoItem newitem = new ToDoItem(Item);
		CurrentList.add(newitem);
	}
	public ToDoItem getItemAtPosition(int position){
		return CurrentList.get(position);	
	}
	public void toggleChecked(int position){
		CurrentList.get(position).changeChecked();
	}

}
