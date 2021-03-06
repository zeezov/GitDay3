package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
// Log in tests are maintained here
// without page object approach
public class LogInTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void logInTest1() {

        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test" + Keys.ENTER);

        String title = driver.getTitle();
        Assert.assertEquals(title, "Web Orders");

        String errorMessage = driver.findElement(By.id("ctl100_MainContent_status")).getText();
        Assert.assertEquals(errorMessage, "Invalid Login or Password.");
    }

    @Test
    public void negativeLogInTest() {

        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester2");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test" + Keys.ENTER);
        String errorMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(errorMessage, "Invalid Login or Password.");

    }

    @Test
    public void logOutTest() {

        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test" + Keys.ENTER);

        driver.findElement(By.linkText("Logout")).click();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Web Orders Login");

    }

    @AfterMethod
    public void cleanUp() {
        //driver.close();
    }



}
