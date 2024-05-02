package com.kaviya.modellabexam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModellabexamApplication {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.shoppersstop.com/");
		driver.findElement(By.xpath("/html/body/main/header/div[1]/div/div[2]/div[2]/ul/li[4]/a")).click();

		driver.manage().window().fullscreen();

		System.out.println("Title of the page :" + driver.getTitle());
		System.out.println("-----------");

		System.out.println("Page Source:");
		System.out.println(driver.getPageSource());
		System.out.println("-----------");

		System.out.println("Page Source Length:");
		System.out.println(driver.getPageSource().length());

		driver.navigate().to("https://www.google.com/");
		driver.navigate().back();
		if (driver.getCurrentUrl().equals("https://www.shoppersstop.com/")) {
			System.out.println("URL matched");
		} else {
			System.out.println("URL didn't match");
		}
		driver.close();
		SpringApplication.run(ModellabexamApplication.class, args);
	}

}
