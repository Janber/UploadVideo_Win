import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import rpa.common.CommonUtils;
import rpa.common.Constants;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author k_go
 * @version 1.0
 * @date 2020/05/13 9:25
 */
public class WebTest {
    public static void main(String[] args){
        //Chrome制御のためChromeDriverのパスを指定
        System.setProperty("webdriver.chrome.driver", Constants.ChromeDriverPath);

        String userProfile= "/Users/jianbo/Library/Application Support/Google/Chrome";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir="+userProfile);
        options.addArguments("--start-maximized");

        //Chromeを起動する
        WebDriver driver = new ChromeDriver(options);

        //タイムアウト10秒
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //最大化表示
        driver.manage().window().maximize();

        //指定したURLに遷移する
        driver.get("https://www.youtube.com/");

//        // 登録ボタン押下
//        WebElement login=driver.findElement(By.xpath("//*[@id=\"buttons\"]/ytd-button-renderer/a"));
//        login.click();
//
//        // メールアドレス登録
//        WebElement inputEmail=driver.findElement(By.id("identifierId"));
//        inputEmail.sendKeys("kurekenpa@gmail.com");
//
//        // 下一步ボタン押下
//        WebElement nextStep=driver.findElement(By.className("VfPpkd-vQzf8d"));
//        nextStep.click();

        WebElement searchWord=driver.findElement(By.id("search"));
        searchWord.sendKeys("NHK");
        WebElement search=driver.findElement(By.id("search-icon-legacy"));

        search.click();

        DoSleep(3000);

        // フィルタボタン押下
//        WebElement login=driver.findElement(By.xpath("//*[@id=\"container\"]/ytd-toggle-button-renderer/a"));
        WebElement login=driver.findElement(By.xpath("//*[@id=\"container\"]/ytd-toggle-button-renderer/a"));
        login.click();


        DoSleep(3000);

        // フィルタの4分以内を選択
        WebElement today=driver.findElement(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[1]/div[2]/ytd-search-sub-menu-renderer/div[1]/iron-collapse/div/ytd-search-filter-group-renderer[3]/ytd-search-filter-renderer[1]/a"));
        today.click();


        DoSleep(3000);

        // 検索結果の１行目を選択
        WebElement row1=driver.findElement(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]"));
        row1.click();


        DoSleep(10000);

        // ダウンロードボタン押下


        WebElement download=driver.findElement(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[5]/div[2]/div"));
//        WebElement download=driver.findElement(By.xpath("//*[@id=\"info-contents\"]/div//div/div[2]"));
//        WebElement download=driver.findElement(By.xpath("//*[@id=\"info-contents\"]/div//div/div[1]/a"));
//        WebElement download=driver.findElement(By.xpath("/html/body/ytd-app/div/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[5]/div[2]/div//div/div[1]/a"));
        download.click();

        DoSleep(10000);


//       class  ms-Viewport


//        WebElement Row = driver.findElement(By.xpath("//div[contains(@class, 'ms-DetailsRow-fields')]"));

//        WebElement Row=driver.findElement(By.className("ms-DetailsRow-fields"));
//        Actions action= new Actions(driver);
//        action.contextClick(Row).build().perform();  /* this will perform right click */
//        WebElement elementOpen = driver.findElement(By.id("id__291")); /*This will select menu after right click */

//        WebElement elementOpen = driver.findElement(By.xpath("//span[text()='ダウンロード']"));

//        elementOpen.click();

//        action.contextClick(Row).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();



    }


    /**
     * 秒間隔でスリーブ
     */
    private static void DoSleep(Integer sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            System.out.println(CommonUtils.GetSystemTime() + e.toString());
        }
    }

}
