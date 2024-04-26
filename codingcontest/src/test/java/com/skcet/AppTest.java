package com.skcet;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class AppTest 
{
    WebDriver driver;
    WebDriverWait wait;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String value;
    Actions action;
    ExtentTest test;
    ExtentSparkReporter spark;
    ExtentReports reports;
    @BeforeTest
    public void setUpServer(){

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://www.barnesandnoble.com/");   
        action = new Actions(driver);

    }

    @BeforeTest
    public void setExcel() throws IOException{

        String path = "C:\\Users\\Lenovo\\Desktop\\IamNeo\\Software Testing\\codingcontest\\src\\Excel\\Data.xlsx";
        FileInputStream fs = new FileInputStream(path);
        workbook = new XSSFWorkbook(fs);
        sheet = workbook.getSheetAt(0);
        value = sheet.getRow(1).getCell(0).getStringCellValue();  
    }

    @BeforeMethod
    public void get_report()
    {
        String path = "C:\\Users\\Lenovo\\Desktop\\IamNeo\\Software Testing\\codingcontest\\src\\Report\\report.html";
        spark = new ExtentSparkReporter(path);
        reports = new ExtentReports();
        reports.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        test = reports.createTest("TestCases");
    }


    /**
     * TestCase :: 1
     */
    @Test(priority = 1)
    public void test_Chetan_Bhagat(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/a"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Books"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]"))).sendKeys(value);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button"))).click();

        WebElement totext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]")));

        if(totext.getText().contains("Chetan Bhagat")){
            test.log(Status.PASS,"Search result contains Chetan Bhagat");
        }
        else{
            
            test.log(Status.FAIL,"Search result does not contain Chetan Bhagat");
        }
    }

    /**
     * TestCase::2
     * 
     */
    @Test(priority = 2)
     public void test_cart(){

        // driver.navigate().to("https://www.barnesandnoble.com/");

        WebElement audiobook =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/a")));
        action.moveToElement(audiobook);
        action.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Audiobooks Top 100"))).click();

        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]/div[1]/div/div[2]/div/div[2]/section[2]/ol/li[1]/div/div[2]/div[5]/div[2]/div/div/form/input[11]"))).click();

     }

    @AfterTest
    public void afterAll() throws InterruptedException{
        Thread.sleep(10000);
        driver.quit();
    }
}
