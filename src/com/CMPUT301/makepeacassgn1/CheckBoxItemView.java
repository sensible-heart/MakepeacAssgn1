package com.CMPUT301.makepeacassgn1;
import android.widget.CheckBox;
import android.widget.TextView;


public class CheckBoxItemView {
	private CheckBox checkbox;
	private TextView itemtext;
	public CheckBoxItemView(){}//initializes the class
	public CheckBoxItemView( TextView itemtext, CheckBox checkbox ) {  
	      this.checkbox = checkbox ;  
	      this.itemtext = itemtext ;  
	    }
	public CheckBox getCheckBox() {  
	      return checkbox;  
	    }  
	    public void setCheckBox(CheckBox checkbox) {  
	      this.checkbox = checkbox;  
	    }  
	    public TextView getTextView() {  
	      return itemtext;  
	    }  
	    public void setTextView(TextView itemtext) {  
	      this.itemtext = itemtext;  
	    }

}
