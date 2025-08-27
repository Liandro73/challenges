package org.liandro;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DatePicker {

    static WebDriver driver;

    public static void main(String[] args)  {

        ChromeOptions options = new ChromeOptions()
                .addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://jqueryui.com/datepicker/#inline");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

        Scanner scanner = new Scanner(System.in);
        Integer day = scanner.nextInt();
        String month = scanner.next();
        Integer year = scanner.nextInt();

        enterDateFrame();
        pickTheExpectedDate(getCurrentMonth(), getCurrentYear(), day, month, year);
        takeScreenShot(day, month, year);

        driver.quit();

    }

    public static void enterDateFrame() {
        WebElement dateFrame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(dateFrame);
    }

    public static Integer monthName(String month) {

        Map<String, Integer> monthHandler = new HashMap<>();
        monthHandler.put("January", 1);
        monthHandler.put("February", 2);
        monthHandler.put("March", 3);
        monthHandler.put("April", 4);
        monthHandler.put("May", 5);
        monthHandler.put("June", 6);
        monthHandler.put("July", 7);
        monthHandler.put("August", 8);
        monthHandler.put("September", 9);
        monthHandler.put("October", 10);
        monthHandler.put("November", 11);
        monthHandler.put("December", 12);

        return monthHandler.get(month);

    }

    public static String getCurrentMonth() {

        WebElement currentMonth = driver.findElement(By.className("ui-datepicker-month"));
        String nameCurrentMonth = currentMonth.getText();
        System.out.println(nameCurrentMonth);

        return nameCurrentMonth;

    }

    public static Integer getCurrentYear() {

        WebElement currentYear = driver.findElement(By.className("ui-datepicker-year"));
        Integer nameCurrentYear = Integer.parseInt(currentYear.getText());
        System.out.println(nameCurrentYear);

        return nameCurrentYear;

    }

    public static void pickTheExpectedDate(String currentMonth, Integer currentYear, Integer day, String month, Integer year) {

        int monthInt = monthName(month);
        int currentMonthInt = monthName(currentMonth);

        if (year.equals(currentYear)) {
            selectMonthAndDay(currentMonth, day, month, currentMonthInt, monthInt);
        } else if (currentYear < year) {
            int timesYear = year - currentYear;
            timesYear *= 12;
            clickOnElementParam("Next", timesYear);
            selectMonthAndDay(currentMonth, day, month, currentMonthInt, monthInt);
        } else {
            int timesYear = currentYear - year;
            timesYear *= 12;
            clickOnElementParam("Prev", timesYear);
            selectMonthAndDay(currentMonth, day, month, currentMonthInt, monthInt);
        }

    }

    private static void selectMonthAndDay(String currentMonth, Integer day, String month, int currentMonthInt, int monthInt) {
        if (month.equals(currentMonth)) {
            driver.findElement(By.xpath("//a[text()='"+ day +"']")).click();
        } else if (currentMonthInt < monthInt) {
            int times = monthInt - currentMonthInt;
            clickOnElementParam("Next", times);
            driver.findElement(By.xpath("//a[text()='"+ day +"']")).click();
        } else if (currentMonthInt > monthInt) {
            int times = currentMonthInt - monthInt;
            clickOnElementParam("Prev", times);
            driver.findElement(By.xpath("//a[text()='"+ day +"']")).click();
        }
    }

    public static void clickOnElementParam(String prevOrNext, int timesToClick) {
        for (int i = 1; i <= timesToClick; i++) {
            driver.findElement(By.xpath("//span[text()='"+ prevOrNext +"']")).click();
        }
    }

    public static void takeScreenShot(Integer day, String month, Integer year) {
        LocalDateTime date = LocalDateTime.now();
        File sc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(sc, new File("/Users/dernivalliandro/Documents/Workspace/autodoc/select_the_date_" + year + "_" + month + "_" + day +  "___" + date + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
