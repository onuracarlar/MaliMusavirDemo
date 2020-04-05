package userInfo;

public class Customer {

	private String musteriName;
	private String musteriXpath;
	
	public Customer(String musteriNamep) {
		musteriName=musteriNamep;
		musteriXpath="//option[contains(text(),'"+musteriNamep+"')]";
	}
	public String getMusteriXpath() {
		return musteriXpath;
	}
	
}
