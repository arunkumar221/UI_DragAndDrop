package app;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Framework {
	
	WebDriver driver;
	
	public void setUp()
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
    }
	
	public void launchApplication(String URL) throws IOException
	{
		driver.get(URL);
		driver.manage().window().fullscreen();	
		this.takeScreenshot(driver);
	}
	
	public void clickObject(String xpath) throws InterruptedException, IOException
	{
		Thread.sleep(1000);
		driver.findElement(By.xpath(xpath)).click();
		this.takeScreenshot(driver);
	}
	
	public void EnterText(String xpath,String name) throws InterruptedException, IOException
	{
		Thread.sleep(1000);
		driver.findElement(By.xpath(xpath)).sendKeys(name);
		this.takeScreenshot(driver);
	}
	
	public void dragAndDrop(String Source ,String Destination) throws InterruptedException, IOException
	{
		
		WebElement IWebSource =driver.findElement(By.xpath(Source));
		WebElement IWebDestination = driver.findElement(By.xpath(Destination));
		Actions action =new Actions(driver);
		IWebSource.click();
	    Thread.sleep(1000);
		action.dragAndDrop(IWebSource, IWebDestination).build().perform();
		this.takeScreenshot(driver);
	}
	
	public void dragAndDropByIndex(String Source,int X,int Y) throws IOException
	{
		WebElement IWebSource =driver.findElement(By.xpath(Source));		
        Actions act=new Actions(driver);					
        act.dragAndDropBy(IWebSource,X, Y).build().perform();
        this.takeScreenshot(driver);
	}
	
	public void takeScreenshot(WebDriver ssdriver) throws IOException
	{
		File src = ((TakesScreenshot)ssdriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:/Reports/"+System.currentTimeMillis()+".png"));
	}
	
	public void close()
	{
		driver.close();
	}
	

}
