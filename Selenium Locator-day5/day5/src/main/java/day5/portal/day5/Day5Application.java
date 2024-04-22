package day5.portal.day5;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day5Application {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://demoblaze.com/");
		driver.findElement(By.linkText("Laptops")).click();
		Thread.sleep(10000);
		driver.findElement(By.linkText("MacBook air")).click();
		Thread.sleep(10000);
		driver.findElement(By.linkText("Add to cart")).click();
		Thread.sleep(10000);
		driver.switchTo().alert().accept();
		Thread.sleep(10000);
		driver.findElement(By.linkText("Cart")).click();
		SpringApplication.run(Day5Application.class, args);
	}

}
