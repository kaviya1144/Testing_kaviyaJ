package day5pah.portal.day5pah;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day5pahApplication {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://magento.softwaretestingboard.com/");
		// driver.manage().window().fullscreen();
		driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div[2]/div[2]/form/div[1]/div/input")).sendKeys("Shoes");
		driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div[2]/div[2]/form/div[2]/button")).click();
		Thread.sleep(1000);

		if(driver.findElement(By.xpath("//*[@id='maincontent']/div[1]/h1/span")).getText().contains("Shoes")){
			System.out.print("Confirms the presence");
		}
		else{
			System.out.print("Absence of presence");
		}
		Thread.sleep(2000);
		TakesScreenshot ts=(TakesScreenshot)driver;
		File file=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("./screenshots/shoe.png"));
		Thread.sleep(2000);

		WebElement hoverable = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/nav/ul/li[3]/a"));
        new Actions(driver)
                .moveToElement(hoverable)
                .perform();
        Thread.sleep(1000);
	

		WebElement hoverable2 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/nav/ul/li[3]/ul/li[1]/a"));
        new Actions(driver)
                .moveToElement(hoverable2)
                .perform();
        Thread.sleep(1000);

		
		WebElement hoverable3 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/nav/ul/li[3]/ul/li[1]/ul/li[2]/a/span"));
        new Actions(driver)
                .moveToElement(hoverable3)
                .perform();
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/nav/ul/li[3]/ul/li[1]/ul/li[2]/a/span")).click();
        Thread.sleep(1000);

		driver.navigate().back();
		driver.navigate().back();
		
		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
        new Actions(driver)
		.scrollFromOrigin(scrollOrigin, 0, 500)
		.perform();

		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div/div[3]/div[1]/div/a[2]/span[2]/span[2]")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div[1]/div[4]/ol/li[3]")).click();

		WheelInput.ScrollOrigin scrollOrigin3 = WheelInput.ScrollOrigin.fromViewport(10, 10);
        new Actions(driver)
		.scrollFromOrigin(scrollOrigin3, 0, 500)
		.perform();

		driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/form/div[1]/div/div/div[1]/div/div[3]")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/form/div[1]/div/div/div[2]/div/div[2]")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/form/div[2]/div/div/div[1]/div/input")).clear();
		Thread.sleep(100);
		driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/form/div[2]/div/div/div[1]/div/input")).sendKeys("4");
		Thread.sleep(100);
		driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/form/div[2]/div/div/div[2]/button/span")).click();


		SpringApplication.run(Day5pahApplication.class, args);
	}

}
