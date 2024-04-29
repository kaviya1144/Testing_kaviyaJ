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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTestwithexcel {
    

    WebDriver driver;
    WebDriverWait wait;
    WebElement element1,element2,element3;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String username,password;


    @BeforeTest
    public void before_test() throws IOException
    {
        String path = "C:\\Users\\Lenovo\\Desktop\\IamNeo\\Software Testing\\Testing Framework-day8\\classexercise1\\src\\Excel\\Data.xlsx";
        FileInputStream fs = new FileInputStream(path);

        workbook = new XSSFWorkbook(fs);
        sheet = workbook.getSheet("Sheet1");

        username = sheet.getRow(1).getCell(0).getStringCellValue();
        password = sheet.getRow(1).getCell(1).getStringCellValue();

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        driver.get("http://dbankdemo.com/bank/login");

    }

    @Test(priority = 1)
    public void test1(){

        element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        element1.sendKeys(username);

        element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        element2.sendKeys(password);

        element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
        element3.click();
        
        if(driver.getCurrentUrl().contains("home"))
        {
            System.out.println("You are logged in");
        }
        else{
            System.out.println("Please register");
        }
    }


    @Test(priority = 2)
    public void Test2() {
        driver.get("http://dbankdemo.com/bank/login");
        driver.manage().window().maximize();
        driver.get("http://dbankdemo.com/bank/login");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        // NewPage
        WebElement deposit = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/aside/nav/div[2]/ul/li[5]/a")));
        deposit.click();

        WebElement drop = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selectedAccount")));
        Select options = new Select(drop);
        options.selectByVisibleText("Individual Checking(standard checking) (Savings)");

        driver.findElement(By.id("amount")).sendKeys("5000");

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/form/div[2]/button[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[4]")));
        String d = driver
                .findElement(By.xpath(
                        "/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[4]"))
                .getText();

        if (d.equals("$5000.00")) {
            System.out.println("The deposited amount is present");
        } else {
            System.out.println("The deposited amount is not present");
        }
    }

    @Test(priority = 3)
    public void Test3() {
        driver.get("http://dbankdemo.com/bank/login");
        driver.manage().window().maximize();
        driver.get("http://dbankdemo.com/bank/login");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        // NewPage
        WebElement deposit = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("withdraw-menu-item")));
        deposit.click();

        WebElement drop = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selectedAccount")));
        Select options = new Select(drop);
        options.selectByVisibleText("Individual Checking(standard checking) (Savings)");

        driver.findElement(By.id("amount")).sendKeys("3000");

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/form/div[2]/button[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[4]")));
        String d = driver
                .findElement(By.xpath(
                        "/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[4]"))
                .getText();

        if (d.equals("$-3000.00")) {
            System.out.println("The withdrawal amount is present");
        } else {
            System.out.println("The withdrawal amount is not present");
        }
    }


    @AfterTest
    public void after_test(){

        driver.quit();
    }
}
