package day4.portal.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeleniumApplication {

	public static void main(String[] args) {

		WebDriver driverchrome = new ChromeDriver();
		driverchrome.get("https://www.google.com");
		driverchrome.get("https://www.shoppersstop.com/");
		driverchrome.manage().window().fullscreen();
		driverchrome.findElement(By.className("user-icon")).click();

		WebDriver driverfirefox = new FirefoxDriver();
		driverfirefox.get("https://www.google.com");
		
		WebDriver driveredge = new EdgeDriver();
		driveredge.get("https://www.google.com");



		SpringApplication.run(SeleniumApplication.class, args);
	}

}
