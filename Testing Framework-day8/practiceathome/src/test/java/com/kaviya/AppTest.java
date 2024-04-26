package com.kaviya;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest 
{

    WebDriver driver;
    WebDriverWait wait;
    XSSFWorkbook workbook;
    XSSFSheet sheet,sheet2,sheet3;
    String value,value2,value3,text,titletext;
    
    @BeforeTest
    public void setUpServer(){

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://www.firstcry.com/");
    }

    @BeforeTest
    public void setUpExcel() throws IOException{

        String path = "C:\\Users\\Lenovo\\Desktop\\IamNeo\\Software Testing\\Testing Framework-day8\\practiceathome\\src\\Excel\\Data.xlsx";
        FileInputStream fs = new FileInputStream(path);
        workbook = new XSSFWorkbook(fs);
        sheet = workbook.getSheetAt(0);
        value = sheet.getRow(1).getCell(0).getStringCellValue();

        sheet2 = workbook.getSheetAt(1);
        value2 = sheet2.getRow(1).getCell(0).getStringCellValue();

        sheet3 = workbook.getSheetAt(2);
        value3 = sheet3.getRow(1).getCell(0).getStringCellValue();

    }

    @Test(priority = 1)
    public void test_toy(){

        //Step 1 search toy
        WebElement toy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_box")));
        toy.click();
        toy.sendKeys(value);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[5]/div/div[2]/form/span"))).click();

        // Step 2 check whether it is Toys section
        if(driver.getCurrentUrl().contains("kids-toys")){
            System.out.println("Confirmed");
        }
        else{
            System.out.println("Not have Toys section");
        }
    }

    /**
     * TestCase::2
     */
    @Test(priority = 2)
    public void test_cloth(){

        driver.navigate().to("https://www.firstcry.com/");

        //Step 1 Search Clothes
        WebElement cloth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_box")));
        cloth.click();
        cloth.sendKeys(value2);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[5]/div/div[2]/form/span"))).click();

        // Step 2 Select Ethnic wear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[4]/a/span"))).click();

        WebElement wear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[1]/div[1]/h1")));
        text = wear.getText();
    }

    /**
     * TestCase::3
     */
    @Test(priority = 3)
    public void test_baloons(){

        driver.navigate().to("https://www.firstcry.com/");

        //Step 1 Search Baloons
        WebElement baloon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_box")));
        baloon.click();
        baloon.sendKeys(value3);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[5]/div/div[2]/form/span"))).click();

        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[1]/div[2]/a")));
        titletext = firstProduct.getText();

        WebElement baloonnext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[3]/div[1]/div[2]/div[1]/div")));
        baloonnext.click();

        String currentUrl = driver.getWindowHandle();
        for(String option:driver.getWindowHandles()){

            if(!option.equals(currentUrl)){

                driver.switchTo().window(option);
                break;
            }
        }
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[7]/div[3]/h1")));
        String checkName = name.getText();
        if(checkName.equals(titletext))
        {
            System.out.println("Checked");
        }
        else{
            System.out.println("Unchecked");
            
        }
    }

    @AfterTest
    public void destruct() throws InterruptedException{

        Thread.sleep(3000);
        driver.close();
    }
}
