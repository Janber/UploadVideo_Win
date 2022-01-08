package rpa.video;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import rpa.common.CommonUtils;
import rpa.common.Constants;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author Jianbo
 * @version 1.0
 * @date 2021/1/23 下午1:45
 */
public class DoUpload {

    public static String AddVideo2Bilibili(String uploadPath) throws Exception {

        WebDriver driver = getWebDriver();
        driver.get("https://member.bilibili.com/video/upload.html");

        // 点击上传或将文件拖入此区域
        ClickByElement(driver, By.id("bili-upload-btn"), "");

        // 模拟选择视频文件s
        RobotSelectVideo(uploadPath);

        // 上传成功 判断
        ClickByElement(driver, By.className("upload-status-intro"), "上传完成");

        // 选择标签
        ClickByElement(driver, By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[10]/div[3]/div/div[1]"), "");
        ClickByElement(driver, By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[10]/div[3]/div/div[2]"), "");
        ClickByElement(driver, By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[10]/div[3]/div/div[3]"), "");
        ClickByElement(driver, By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[10]/div[3]/div/div[4]"), "");
        ClickByElement(driver, By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[10]/div[3]/div/div[5]"), "");
        ClickByElement(driver, By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[10]/div[3]/div/div[6]"), "");
        ClickByElement(driver, By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[10]/div[3]/div/div[7]"), "");
        ClickByElement(driver, By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[10]/div[3]/div/div[8]"), "");
        ClickByElement(driver, By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[10]/div[4]/div/div/div[2]"), "");

        // 点击上传
        ClickByElement(driver, By.className("submit-btn-group-add"), "立即投稿");

        // wait for upload
        Thread.sleep(3000);

        driver.close();

        return getNewFilepath(uploadPath, "Z:\\Downloads\\Bilibili\\");

    }

    private static String getNewFilepath(String uploadPath, String basePath) throws IOException {

        // 移动文件到 指定文件夹
        File file = new File(basePath);
        if (!file.exists()){
            file.mkdir();
        }

        // 获取要创建的文件路径
        File uploadFile = new File(uploadPath);
        String fileName = uploadFile.getName();
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        String outputFile = title + ".txt";
        String outputFilePath = basePath + outputFile;

        // 获取要移动到的文件路径
        String newFilepath =  basePath + uploadFile.getName();
        File newFile = new File(newFilepath);

        // 移动视频文件到 指定位置
        uploadFile.renameTo(newFile);

        // 创建上传已完成TXT文件
        File createFile = new File(outputFilePath);
        if (!createFile.exists()){
            createFile.createNewFile();
        }

        return newFilepath;
    }


    public static void AddToutiao(String uploadPath) throws Exception {

        WebDriver driver = getWebDriver();
        driver.get("https://mp.toutiao.com/profile_v4/xigua/upload-video");

        // 点击上传或将文件拖入此区域
//        ClickByElement(driver, By.xpath("/html/body/div[1]/div/div[3]/section/main/div[2]/div/div/div[2]/div/div[1]/div[2]/div/div[1]/div/div/div/div[2]/div/div/div/div/div/div/div[1]"), "");
        ClickByElement(driver, By.xpath("/html/body/div[1]/div/div[3]/section/main/div[2]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/div/div/div[2]/div/div/div/div/div"), "");

        // 模拟选择视频文件
        RobotSelectVideo(uploadPath);

        // 上传成功 判断
//        ClickByElement(driver, By.xpath("/html/body/div[1]/div/div[3]/section/main/div[2]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[1]/div[2]/div[1]/span[1]"), "上传成功");
        ClickByElement(driver, By.className("percent"), "上传成功");

        // 点击上次封面
        ClickByElement(driver, By.className("fake-upload-trigger"), "");

        // 需要等待时间
        Thread.sleep(10000);

        // 封面截取 下一步
        ClickByElement(driver,  By.xpath("/html/body/div[6]/div/div[2]/div/div[2]/div"), "");

        // 需要等待时间
        Thread.sleep(10000);

        // 模板 确定
        ClickByElement(driver,  By.xpath("/html/body/div[6]/div/div[2]/div/div[1]/div/div[2]/div[2]/div[3]/div[3]/button[2]"), "");

        // 等待时间
        Thread.sleep(2000);

        // 完成后无法继续编辑，是否确定完成？ 确定
        ClickByElement(driver,  By.xpath("/html/body/div[7]/div/div[2]/div/div[2]/button[2]"), "");

        // 等待时间
        Thread.sleep(5000);

        // 创作类型
        ClickByElement(driver,  By.xpath("/html/body/div[1]/div/div[3]/section/main/div[2]/div/div/div[2]/div/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[4]/div[2]/div/div/label[1]/span/div"), "");

        // 发布
        ClickByElement(driver,  By.xpath("/html/body/div[1]/div/div[3]/section/main/div[2]/div/div/div[2]/div/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[2]/div[3]/div/button[3]"), "");

        // wait for upload
        Thread.sleep(5000);

        driver.close();

    }

    // 模拟选择视频
    private static void RobotSelectVideo(String uploadPath) throws AWTException, InterruptedException {
        StringSelection selection = new StringSelection(uploadPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();

        robot.delay(3000);

        // 按下Ctrl+V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        // 释放Ctrl+V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        Thread.sleep(2000);
        // 点击回车
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }


    // common function
    private static WebDriver getWebDriver() throws InterruptedException {

        // close chrome
        CommonUtils.killProcess("chrome.exe");
        CommonUtils.killProcess("chromedriver.exe");

        //Chrome制御のためChromeDriverのパスを指定
        System.setProperty("webdriver.chrome.driver", Constants.ChromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        String userProfile = "C:\\Users\\kousei\\AppData\\Local\\Google\\Chrome\\User Data";
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
    private static void ClickByElement(WebDriver driver, By by, String keyWord) throws Exception {
        CommonUtils.waitForPresent(driver, by, keyWord);
        WebElement nextStep = driver.findElement(by);
        nextStep.click();
    }
}
