package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	public static WebElement element = null;
	
	
	 //Sisteme Giriş'i bul
	 
	public static WebElement sistemeGiris(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[11]/a"));
		return element;
	}
	//Sisteme girişe tıkla
	public static void sistemeGirisClick(WebDriver driver) {
		element = sistemeGiris(driver);
		element.click();;
	}

	// Luca Mali Müsavir Anasayfa
	 
	public static WebElement lucaMaliMusavirAna(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[11]/div/div/div/div[2]/div/div[1]/a/div"));
		return element;
	}
	public static void lucaMaliMusavirAnaClick(WebDriver driver) {
		element=lucaMaliMusavirAna(driver);
		element.click();
	}
	
	//Üye Girişi Sayfası
	
		//Üye No
	public static WebElement userNoTextBox(WebDriver driver) {
		element=driver.findElement(By.xpath("//*[@id=\"musteriNo\"]"));
		return element;
	}
	public static void userNoTextBoxFill(WebDriver driver,String userNo) {
		element=userNoTextBox(driver);
		element.sendKeys(userNo);
	}
		//Kullanıcı Adı
	public static WebElement userNameTextBox(WebDriver driver) {
		element=driver.findElement(By.xpath("//*[@id=\"kullaniciAdi\"]"));
		return element;
	}
	public static void userNameTextBoxFill(WebDriver driver,String userName) {
		element=userNameTextBox(driver);
		element.sendKeys(userName);
	}
		//Parola
	public static WebElement userPasswordTextBox(WebDriver driver) {
		element=driver.findElement(By.xpath("//*[@id=\"parola\"]"));
		return element;
	}
	public static void userPasswordTextBoxFill(WebDriver driver,String password) {
		element=userPasswordTextBox(driver);
		element.sendKeys(password);		
	}
	
	public static WebElement maliMusavirPaketiButon(WebDriver driver) {
		element=driver.findElement(By.xpath("//div[@class='uygLogoIc lucaMmpLogo']"));
		return element;
	}
	//ÜyeGiriş Sayfası bitiş
	
	//Müşavir sayfası
	public static WebElement musteriDropDown(WebDriver driver) {
		element=driver.findElement(By.id("SirketCombo"));
		return element;
	}
	
	//Personel
	public static WebElement personel(WebDriver driver) {
		element=driver.findElement(By.id("apy1000m0i0ITX"));
		return element;
	}
	//Sol sekme(Personel başlığı altında) Bordro Dökümü
	public static WebElement bordroDokumu(WebDriver driver) {
		element=driver.findElement(By.id("apy1000m1i4I"));
		return element;
	}
	
	//Bordro Hesapla Button
	public static WebElement bordroHesaplaButton1(WebDriver driver) {
		element=driver.findElement(By.xpath("//button[@class='green-btn'][contains(text(),'Bordro Hesapla')]"));
		return element;
	}
	//Bordro Hesapla Pop-up Button
	public static WebElement bordroHesaplaButton2(WebDriver driver) {
		element=driver.findElement(By.id("hesapla_button1"));
		return element;
	}
	//Sağ Sekme Bordro Dökümü
	public static WebElement bordroDokumuSagSekme(WebDriver driver) {
		element=driver.findElement(By.id("apy_t0i4font"));
		return element;
	}
	
	//Ay DropDown
	public static WebElement ayDropDown(WebDriver driver) {
		element=driver.findElement(By.id("P_AY"));
		return element;
	}
	//Bordro Rapor DropDown
	public static WebElement bordroRaporDropDown(WebDriver driver) {
		element=driver.findElement(By.xpath("//select[@id='REPORT_TYPE']"));
		return element;
	}
	
	//Ücret Hesap Pusulası
	public static WebElement ucretHesapPusulasi(WebDriver driver) {
		element=driver.findElement(By.id("apy_t0i5font"));
		return element;
	}
	//Ücret Hesap Pusulası Rapor Türü
	public static WebElement ucretHesapPusulasiRaporTuru(WebDriver driver) {
		element=driver.findElement(By.id("REPORT_TYPE"));
		return element;
	}
	

}
