package rpa.common;

import org.apache.poi.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author k_go
 * @version 1.0
 * @date 2020/05/14 9:59
 */
public final class CommonUtils {

    /**
     * システム日付取得
     * @return
     */
    public static String GetSystemTime(){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day) + " ||";
    }


    public static void waitForPresent(WebDriver webDriver, By by, String keyWord) throws InterruptedException {

        boolean isElementPresent = false;
        while (!isElementPresent) {
            // wait for ElementPresent
            Thread.sleep(3000);
            // 等待下载结束
            isElementPresent = isJudgingElement(webDriver, by, keyWord);
        }
    }

    /**
     * 判断某个元素是否存在
     */
    public static boolean isJudgingElement(WebDriver webDriver, By by, String keyWord) {
        try {
            // wait for ElementPresent
            Thread.sleep(3000);
            WebElement webElement = webDriver.findElement(by);
            if (!keyWord.equals("")){
                if (webElement.getText().contains(keyWord)){
                    return true;
                }else{
                    System.out.println(webElement.getText());
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("不存在此元素");
            return false;
        }
    }


    /**
     * 关闭 browser
     */
    public static void killProcess(String processName) throws InterruptedException {
        try {
            String line;
            Process p = Runtime.getRuntime().exec("tasklist.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (line.contains(processName)) {
                    Runtime.getRuntime().exec("taskkill /F /IM " + processName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 关闭 browser
     */
    public static void MacKillChromeProcess() throws InterruptedException {
        try {
            String strCmd[] = {"killall","Google Chrome"};
            Runtime.getRuntime().exec(strCmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // common function
    public static WebDriver getWebDriver() throws InterruptedException {

        // close chrome
//        MacKillChromeProcess();
        killProcess("chrome.exe");
        killProcess("chromedriver.exe");

        //Chrome制御のためChromeDriverのパスを指定
        System.setProperty("webdriver.chrome.driver", Constants.ChromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        String userProfile = Constants.UserProfile;
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--start-maximized");

        // NONE: 当html下载完成之后，不等待解析完成，selenium会直接返回
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(options);

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);     // 所有的findElement方法都会隐式等待10s
        driver.manage().window().maximize();
        return driver;
    }

    // ClickByElement
    public static void ClickByElement(WebDriver driver, By by, String keyWord) throws InterruptedException {
        CommonUtils.waitForPresent(driver, by, keyWord);
        WebElement nextStep = driver.findElement(by);
        DoSleep(3000);
        nextStep.click();
    }

    /**
     * 秒間隔でスリーブ
     */
    public static void DoSleep(Integer sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            System.out.println(CommonUtils.GetSystemTime() + e.toString());
        }
    }





}
