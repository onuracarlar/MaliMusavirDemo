package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import page.SearchPage;
import userInfo.Month;
import userInfo.User;



//import com.sun.tools.javac.util.List;

public class Otomat {

	//***kullanıcıdan istenilen bilgiler:
	//*Üye Bilgileri
	private User user;
	public int userNo;
	public String userName;
	public String password;
	//*Ay
	public String monthName;
	public int monthNumber;
	public Month month;
	/*Müşteri(Daha Sonra)
	public String musteriName;
	public Musteri musteriObje;
*/
	//driver
	private WebDriver driver;
	private String baseUrl;
	
	//windows
	private Set<String> allWindows;
	private String lucaMainWindow;
	private String lucaLoginWindow;
	private String lucaMusavirWindow;
	
	//musteri Listesi
	private String[] musteriler;
	
	//ay listesi
	private String[] aylar;
	
	//dropdowns
	Select dropdown;
	
	//WEb Element Personel
	private WebElement Personel;
	
	@Before
	public void setUp() throws Exception {
		driver=new FirefoxDriver();
		baseUrl="http://www.luca.com.tr/";
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		
		//kullanıcı girişleri:
		userNo=10000001;
		userName="------";
		password="------";
		
		user=new User(userName,userNo,password);
		
		monthName="---";
		monthNumber=3;
		
		month=new Month(monthName,3);
		
		
	}
	@Test
	public void test() throws InterruptedException {
		
		
		Actions action =new Actions(driver);
		
		lucaLoginWindow=driver.getWindowHandle();
		
		//sisteme giriş click
		SearchPage.sistemeGirisClick(driver);
		
		//luca malimusavir click
		SearchPage.lucaMaliMusavirAnaClick(driver);
		
		
		//yeni pencereye geçiş
		allWindows=driver.getWindowHandles();
		for (String handle : allWindows) {
			if (!handle.equals(lucaMainWindow)) {
				lucaLoginWindow=handle;
			}
		}
		driver.close();
		
		driver.switchTo().window(lucaLoginWindow);		
		//üye girişi
		
		SearchPage.userNoTextBoxFill(driver, user.getUserNumber());
		SearchPage.userNameTextBoxFill(driver, user.getUserName());
		SearchPage.userPasswordTextBoxFill(driver, user.getUserPassword());
		
		SearchPage.userNoTextBox(driver).sendKeys(Keys.ENTER);
		
		//mali müşavir paketi butonuna tıkla
		action.moveToElement(SearchPage.maliMusavirPaketiButon(driver)).click().perform();
		driver.switchTo().alert().accept(); //pop up ı kabul et
		
		//yeni ekrana geçiş
		allWindows=driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(lucaLoginWindow)) {
				lucaMusavirWindow=window;
			}
		}
		driver.close();
		driver.switchTo().window(lucaMusavirWindow);
		
		Thread.sleep(2000); //yeni sayfa beklensin
		//müşteri seçimi:	
		driver.switchTo().frame("frm4"); //frame4'te
	
		musteriler = SearchPage.musteriDropDown(driver).getText().split("\n");
		
		dropdown=new Select(SearchPage.musteriDropDown(driver));
		
		//sitede gözükmeyebilen butonu gösterebilmek için
		dropdown.selectByIndex(musteriler.length-1);
		
		
		 // bu kod bloğu dropdown içindekileri liste haline getiriyor hepsine uygulayacak
		for (int j = 0; j < musteriler.length; j++) { 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frm4");
			
			dropdown=new Select(SearchPage.musteriDropDown(driver));
			dropdown.selectByIndex(j+1);		
			
			action.moveToElement(driver.findElement(By.xpath("//button[@class='no-bold green']"))).click().perform();
		/*	
			action.moveToElement(driver.findElement(By.id("SirketCombo"))).click().perform();
			System.out.println(""+musteriler[j]);		
			------müşteri seçimi şimdilik yok
			action.moveToElement(driver.findElement(By.xpath("//option[contains(text(),'"+musteriler[j]+"')]"))).click().perform();
			driver.findElement(By.xpath("//option[contains(text(),'"+musteriler[j]+"')]")).click();
			action.moveToElement(driver.findElement(By.xpath("//button[@class='no-bold green']"))).click().perform();
			*/
			//frame değişikliği
			Thread.sleep(2000); //sayfa yenileneceği için 2 saniye bekle
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frm2");
			
			Personel=SearchPage.personel(driver);
			
			if (!Personel.getText().equals("Personel")) {
				continue;  //müşterinin Personel sayfası mevcut değil ise diğer müşteriye geç
			}
			action.moveToElement(Personel).perform();
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frm3");
			
			//Bordro Dökümü
			action.moveToElement(SearchPage.bordroDokumu(driver)).click().perform();
			
			Thread.sleep(2500);
			
			//istenilen ay satırına tıklama
			driver.findElement(By.id("tr"+Integer.toString(month.getMonthNumber())));
			action.click().perform();
		
			//bordro hesapla 1.si basıyor
			action.moveToElement(SearchPage.bordroHesaplaButton1(driver)).click().perform();

			//yeni pencere, bordro hesaplanıyor
			action.moveToElement(SearchPage.bordroHesaplaButton2(driver)).click().perform();
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frm5");
			
			//sağ sekmede genel raporlar içinde bordro dökümüne tıkla
			action.moveToElement(SearchPage.bordroDokumuSagSekme(driver)).click().perform();
			
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frm3");
			
			//ayı seç
			dropdown=new Select(SearchPage.ayDropDown(driver));
			dropdown.selectByValue(Integer.toString(month.getMonthNumber()));

			//Raporun türü
			dropdown=new Select(SearchPage.bordroRaporDropDown(driver));
			dropdown.selectByValue("PDF");
			
			action.moveToElement(driver.findElement(By.xpath("//button[@class='green-btn bold']"))).click().perform();
			//rapora tıkladıktan sonra bi iki saniye bekle
			Thread.sleep(2000);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frm5");
			
			//ücret hesap pusulası tıkla
			action.moveToElement(SearchPage.ucretHesapPusulasi(driver)).click().perform();
			
			Thread.sleep(1500);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frm3");
			
			dropdown=new Select(SearchPage.ayDropDown(driver));
			dropdown.selectByValue(Integer.toString(month.getMonthNumber()));
			
			dropdown=new Select(SearchPage.ucretHesapPusulasiRaporTuru(driver));
			dropdown.selectByValue("PDF");
			
			action.moveToElement(driver.findElement(By.xpath("//a[@class='anamenuyazi']"))).click().perform();
					
		} 		
	}
	
	@After
	public void tearDown() throws Exception {
		
		Thread.sleep(2500);
		driver.quit();
		
	}

	

}
