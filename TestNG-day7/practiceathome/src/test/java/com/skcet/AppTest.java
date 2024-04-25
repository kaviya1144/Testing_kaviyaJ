package com.skcet;

import java.time.Duration;

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
    WebElement element;

    @BeforeTest
    public void before_test(){

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://groww.in/");

    }
    @Test
    public void test1(){

        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Calculators")));
        element.click();

    }



    @Test(dependsOnMethods = "test1")
    public void test2(){
        
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div[2]/div[2]/a[15]/div")));
        element.click();

    }


    @Test(dependsOnMethods = "test2")
    public void test3(){

        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='LOAN_AMOUNT']")));
        element.clear();
        element.sendKeys("2300000");

    }

    @Test(dependsOnMethods = "test3")
    public void test4(){

        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='RATE_OF_INTEREST']")));
        element.clear();
        element.sendKeys("8");

    }

    @Test(dependsOnMethods = "test4")
    public void test5(){

        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='LOAN_TENURE']")));
        element.clear();
        element.sendKeys("25");

    }

    @Test(dependsOnMethods = "test5")
    public void test6(){

        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tb10Table")));
        System.out.println(element.getText());
        
    }



    @AfterTest
    public void after_test() throws InterruptedException{
        wait(4000);
        driver.quit();
    }
}
