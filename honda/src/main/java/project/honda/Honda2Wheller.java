package project.honda;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Honda2Wheller {
	
    public static void main( String[] args ) throws InterruptedException {
        
    	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    	System.setProperty("webdriver.chrome.silentOutput", "true");
    	
    	WebDriver driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	
    	Actions action = new Actions(driver);
    	    	
    	//1) Go to https://www.honda2wheelersindia.com/
    	
    	driver.get("https://www.honda2wheelersindia.com");
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	driver.findElement(By.className("close")).click();
 
    	String parentWindow = driver.getWindowHandle();
    	
    	//2) Click on scooters and click dio
    	
    	driver.findElement(By.id("link_Scooter")).click();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("(//div[@class='owl-wrapper'])[2]/div[1]/div/a/img")).click();
    	Thread.sleep(4000);
  
    	//3) Click on Specifications and mouseover on ENGINE
    	
    	driver.findElement(By.xpath("//a[text()='Specifications']")).click();
    	Thread.sleep(3000);
    	action.moveToElement(driver.findElement(By.xpath("//a[text()='ENGINE']"))).perform();
    	Thread.sleep(3000);
    	
    	//4) Get Displacement value
    	
    	String dioDisp = driver.findElement(By.xpath("//span[text()='Displacement']//parent::li/span[2]")).getText();
    	String sortedDio = dioDisp.replaceAll("\\D\\D", "");
    	double dioDisplacement = Double.parseDouble(sortedDio);
    	System.out.println("Displacedment of the dio is : "+ dioDisplacement);
    	
    	//5) Go to Scooters and click Activa 125
    	
    	driver.findElement(By.id("link_Scooter")).click();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("(//div[@class='owl-wrapper'])[2]/div[2]/div/a/img")).click();
    	Thread.sleep(4000);
    	
    	
    	//6) Click on Specifications and mouseover on ENGINE
    	
    	driver.findElement(By.xpath("//a[text()='Specifications']")).click();
    	Thread.sleep(3000);
    	action.moveToElement(driver.findElement(By.xpath("//a[text()='Engine']"))).click().perform();
    	Thread.sleep(3000);
    	
    	//7) Get Displacement value
    	
    	String activaDisp = driver.findElement(By.xpath("//span[text()='Displacement']//parent::li/span[2]")).getText();
    	String sortedActiva = activaDisp.replaceAll("\\s[\\D]*", "");
    	double activaDisplacement = Double.parseDouble(sortedActiva);
    	System.out.println("Displacedment of the activa is : "+ activaDisplacement);
    	
    	//8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
    	
    	if (dioDisplacement == activaDisplacement) {
			System.out.println("Both has same performance");
    	} else if (dioDisplacement > activaDisplacement) {
			System.out.println("Dio has higher performance");
		} else {
			System.out.println("active have higher performance");
		}
    	
    	
    	//9) Click FAQ from Menu 
    	
    	driver.findElement(By.xpath("(//a[text()='FAQ'])[1]")).click();
    	Thread.sleep(3000);
    	
    	
    	//10) Click Activa 125 BS-VI under Browse By Product
    	
    	driver.findElement(By.xpath("//a[text()='Activa 125 BS-VI']")).click();
    	
    	
    	//11) Click  Vehicle Price 
    	
    	driver.findElement(By.xpath("//ul[contains(@class,'nav nav-pills tabb-design ')]/li[6]/a")).click();
    	
    	
    	//12) Make sure Activa 125 BS-VI selected and click submit    	
    	
    	WebElement scooter = driver.findElement(By.id("ModelID1"));
    	Select options = new Select(scooter);
    	options.selectByIndex(3);
    	
    	driver.findElement(By.id("submit6")).click();
    	Thread.sleep(3000);
    	
    	//13) click the price link
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Activa 125 BS-VI']//following-sibling::td/a"))).click();
    	
    	
    	//14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
    	
    	Set<String> windowsSet = driver.getWindowHandles();
    	List<String> windows = new ArrayList<String>();
    	windows.addAll(windowsSet);
    	
    	driver.switchTo().window(windows.get(1));
    	
    	WebElement state = driver.findElement(By.id("StateID"));
    	Select stateSelect = new Select(state);
    	stateSelect.selectByVisibleText("Tamil Nadu");
    	
    	WebElement city = driver.findElement(By.id("CityID"));
    	Select citySelect = new Select(city);
    	citySelect.selectByVisibleText("Chennai");
    	Thread.sleep(3000);
    	
    	//15) Click Search
    	
    	driver.findElement(By.xpath("//button[text()='Search']")).click();
    	Thread.sleep(3000);
    	
    	//16) Print all the 3 models and their prices
    	WebElement table = driver.findElement(By.xpath("//table[@id='gvshow']//tbody"));
    	List<WebElement> rows = table.findElements(By.tagName("tr"));
    	for (int i = 1; i <= rows.size(); i++) {
    		String schooterModel = driver.findElement(By.xpath("//table[@id='gvshow']//tbody/tr["+i+"]//td[contains(text(),'ACTIVA 125')]")).getText();
    		String schooterPrice = driver.findElement(By.xpath("//table[@id='gvshow']//tbody/tr["+i+"]//td[contains(text(),'ACTIVA 125')]//following-sibling::td")).getText();
    		System.out.println("Schooter model "+schooterModel+" price is "+schooterPrice);
    		
    	}
    	
    	/*WebElement table = driver.findElement(By.xpath("//table[@id='gvshow']//tbody"));
    	List<WebElement> rows = table.findElements(By.tagName("tr"));
    	int count = 0;
    	for (int i = count ; i <= rows.size(); i++) {
			List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
				WebElement price = columns.get(2+count);
				String bikePrice = price.getText();
				System.out.println(bikePrice);				
		count++;
    	}*/	
    	
    	//17) Close the Browser    	
    	Thread.sleep(3000);
    	driver.switchTo().window(windows.get(1)).close();
    	Thread.sleep(3000);
    	driver.switchTo().window(parentWindow).close();
    }
}