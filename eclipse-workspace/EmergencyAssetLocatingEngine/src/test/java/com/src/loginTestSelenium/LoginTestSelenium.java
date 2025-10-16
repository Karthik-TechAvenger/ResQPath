package com.src.loginTestSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTestSelenium {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        
        driver.get("http://localhost:8080/EmergencyAssetLocatingEngine/register.jsp");

       
        driver.findElement(By.name("email")).sendKeys("testuser@example.com");

       
        driver.findElement(By.name("uname")).sendKeys("testUser123");

        
        driver.findElement(By.name("pwd")).sendKeys("TestPassword@123");

       
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        
        Thread.sleep(2000);

       
        driver.quit();
    }
}
