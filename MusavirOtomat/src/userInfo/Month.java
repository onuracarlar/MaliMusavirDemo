package userInfo;

import org.openqa.selenium.WebElement;

public class Month {

	private String monthName;
	private int monthNumber;
	private String monthLineID;
	private String monthDropDownXpath;
	
	public Month(String monthNamep,int monthNumberp){
		monthName=monthNamep;
		monthNumber=monthNumberp;
		monthLineID="tr"+Integer.toString(monthNumberp);	
		monthDropDownXpath="//option[contains(text(),"+monthNamep+")]";
	}
	
	public String getMonthName() {
		return monthName;
	}
	public int getMonthNumber() {
		return monthNumber;
	}
	public String getMonthLineID() {
		return monthLineID;
	}
	public String getMonthDropDownXpath() {
		return monthDropDownXpath;
	}
	
}
