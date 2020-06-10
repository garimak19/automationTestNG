package automationTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test1 {
	public static WebDriver driver;
	@BeforeSuite
	public void Bsuite() {
		System.out.println("before suite");
	}
	@BeforeMethod
	public void launchBrowser() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://qa-tekarch.firebaseapp.com/");
	
		driver.manage().window().maximize();
		System.out.println("before method");
		
		Thread.sleep(5000);
	}
	
	@Test(priority=2)
	public void login() {
		driver.findElement(By.id("email_field")).sendKeys("admin123@gmail.com");;
		driver.findElement(By.id("password_field")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[contains(text(),'Login to Account')]")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[contains(text(),'Student Registration Form')]"))));
		
		WebElement w=driver.findElement(By.xpath("//h1[contains(text(),'Student Registration Form')]"));
		System.out.println(w.getText());
		
		
	}
	@Test(priority=1)
	public void login2() {
		driver.findElement(By.id("email_field")).sendKeys("admin123@gmail.com");;
		driver.findElement(By.id("password_field")).sendKeys("admin");
		driver.findElement(By.xpath("//button[contains(text(),'Login to Account')]")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[contains(text(),'Student Registration Form')]"))));
		
		WebElement w=driver.findElement(By.xpath("//h1[contains(text(),'Student Registration Form')]"));
		System.out.println(w.getText());
		
		
	}
	@AfterMethod
	public void logout() throws Exception {
		Thread.sleep(3000);
System.out.println("after method");
		driver.quit();
	}
	
	
	@BeforeTest
	public void Btest() {
		System.out.println("before test");
	}
	@AfterSuite
	public void Asuite() {
		System.out.println("after suite");
	}
	@AfterTest
	public void Atest() {
		System.out.println("after test");
	}
	@BeforeClass
	public void Bclass() {
		System.out.println("before class");
	}
	@AfterClass
	public void AClass() {
		System.out.println("After class");
	}
}
