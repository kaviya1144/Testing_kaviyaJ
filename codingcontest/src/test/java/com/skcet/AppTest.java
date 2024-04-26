package com.skcet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
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
    ExtentTest test,test2,test3;
    ExtentSparkReporter spark;
    ExtentReports reports;
    Logger logger=Logger.getLogger(AppTest.class);
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

    @BeforeTest
    public void get_report()
    {
        String path = "C:\\Users\\Lenovo\\Desktop\\IamNeo\\Software Testing\\codingcontest\\src\\Report\\report.html";
        spark = new ExtentSparkReporter(path);
        reports = new ExtentReports();
        reports.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        test = reports.createTest("TestCase 1");
    }
    
    
    /**
     * TestCase :: 1
     * 
     */
    @Test(priority = 1)
    public void test_Chetan_Bhagat() throws IOException{

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/a"))).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Books"))).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]"))).sendKeys(value);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button"))).click();
        
        WebElement totext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]")));
        
        if(totext.getText().contains("Chetan Bhagat")){
            test.log(Status.PASS,"Search result contains Chetan Bhagat");

            TakesScreenshot ts=(TakesScreenshot)driver;
            File file=ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("./screenshots/Shot.png"));
        }
        else{
            
            test.log(Status.FAIL,"Search result does not contain Chetan Bhagat");

            TakesScreenshot ts=(TakesScreenshot)driver;
            File file=ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("./screenshots/pic.png"));
        }
    }
    
    /**
     * TestCase::2
     * 
     * 
     */
    @Test(priority = 2)
     public void test_cart() throws InterruptedException, IOException{

        
         WebElement audiobook =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/a")));
        action.moveToElement(audiobook);
        action.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Audiobooks Top 100"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"resolve_1\"]"))).click();

        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"otherAvailFormats\"]/div/div/div[3]/a"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"prodInfoContainer\"]/div[3]/form[1]/input[11]"))).submit();

        try{
            String find = driver.switchTo().alert().getText();

            if (find.contains("Item Successfully Added To Your Cart")) {
                test.log(Status.PASS,"Successfully inserted into the cart");
            } else {
                test.log(Status.FAIL,"Item not inserted into the cart");
            }

        }
        catch(Exception e){

            test.log(Status.FAIL, "Alert not present");
            TakesScreenshot ts=(TakesScreenshot)driver;
		    File file=ts.getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(file, new File("./screenshots/cart.png"));
        }
        
        

     }

     /**
      * 
      * Testcase::3
      */

    @Test(priority = 3)
    public void test_membership()
    {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\\\"footer\\\"]/div/dd/div/div/div[1]/div/a[5]"))).click(); 

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rewards-modal-link']"))).click();

        WebElement textelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dialog-title']")));

        if(textelement.getText().contains("Sign in or Create an Account"))
        {
            test3.log(Status.PASS,"Pop up has Sign in or create account options");
        }
        else{
            test3.log(Status.FAIL,"Pop up does not have Sign in or create account options");

        }
    }

    @AfterTest
    public void afterAll() throws InterruptedException{

        reports.flush();
        Thread.sleep(10000);
        driver.quit();
    }
}
