package com.CMPUT301.makepeacassgn1;


public class ToDoItem extends ToDoList {
	private String ItemName;
	private Boolean Archive;//Either in archive or in current ToDo
	private Boolean Checked = false;//initialized to an unchecked state
	
	public ToDoItem(String name){
		ItemName = name;
		Archive = false;
	}
	public String GetName(){//retrieves string name of Item
		return ItemName;
	}
	public Boolean IsInArchive(){//Retrieves whether an item in in the archive or not
		return Archive;
	}
	public Boolean isChecked(){
		return Checked;
	}
	public void changeChecked(){
		this.Checked = !Checked;
	}
	public void setChecked(boolean Checked) {  
	      this.Checked = Checked;  
	}
}
