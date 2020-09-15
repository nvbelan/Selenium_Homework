import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 30, 1000);

        String baseUrl = "http://www.rgs.ru";
        driver.get(baseUrl);
    }

    @Test
    public void bodyCode() {

        String xPathMenu = "//li/a[@class = 'hidden-xs']";
        WebElement locMenu = driver.findElement(By.xpath(xPathMenu));
        waitUtilElementToBeClickable(locMenu);
        locMenu.click();

        String xPathDMS = "//li/a[contains(text(), 'ДМС')]";
        WebElement locDMS = driver.findElement(By.xpath(xPathDMS));
        waitUtilElementToBeClickable(locDMS);
        locDMS.click();

        String xPathControlText = "//h1[contains(text(),' добровольное медицинское страхование')]";
        WebElement controlText1 = driver.findElement(By.xpath(xPathControlText));
        waitUtilElementToBeVisible(controlText1);
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "ДМС — добровольное медицинское страхование", controlText1.getText());

        String xPathButton1 = "//a[contains(text(),'Отправить заявку')]";
        WebElement button1 = driver.findElement(By.xpath(xPathButton1));
        waitUtilElementToBeClickable(button1);
        button1.click();

        String xPathControlText2 = "//b [contains(text(),'Заявка на добровольное медицинское страхование')]";
        WebElement controlText2 = driver.findElement(By.xpath(xPathControlText2));
        waitUtilElementToBeVisible(controlText2);
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Заявка на добровольное медицинское страхование", controlText2.getText());

        String xPathLastName = "//input[@name='LastName']";
        String xPathName = "//input[@name='FirstName']";
        String xPathMiddle = "//input[@name='MiddleName']";
        String xPathTelephone = "//div[@class='form-group col-md-6 col-xs-12']/input [contains(@data-bind,'value: Phone')]";
        String xPathEmail = "//div[@class='form-group col-md-6 col-xs-12']/input [contains(@data-bind,'value: Email')]";
        String xPathComment = "//textarea [contains(@data-bind,'value: Comment')]";
        String xPathRegion = "//select [@name = 'Region']";
        String xPathRegionMoscow = "//select/option[@value = '77']";
        String xPathTick = "//input[@class ='checkbox']";
        String xPathButtonSend = "//button[@id='button-m']";


        fillInputField(driver.findElement(By.xpath(xPathLastName)), "Иванов");
        fillInputField(driver.findElement(By.xpath(xPathName)), "Иван");
        fillInputField(driver.findElement(By.xpath(xPathMiddle)), "Иванович");
        fillInputField(driver.findElement(By.xpath(xPathTelephone)), "7777777777", "+7 (777) 777-77-77");
        fillInputField(driver.findElement(By.xpath(xPathEmail)), "qwertyqwerty");
        fillInputField(driver.findElement(By.xpath(xPathComment)), "Присутствует");

        WebElement region = driver.findElement(By.xpath(xPathRegion));
        region.click();

        WebElement regionMoscow = driver.findElement(By.xpath(xPathRegionMoscow));
        regionMoscow.click();

        WebElement tick = driver.findElement(By.xpath(xPathTick));
        tick.click();

        WebElement bigBUTTON = driver.findElement(By.xpath(xPathButtonSend));
        bigBUTTON.click();

        String checkedErrorMessage = "//label[@class = 'validation-error']/span[text() = 'Введите адрес электронной почты']";
        WebElement checkFinal = driver.findElement(By.xpath(checkedErrorMessage));
        wait.until(ExpectedConditions.visibilityOf(checkFinal));
        Assert.assertEquals("Сообщение отсутствует/ не соответствует требуемому", "Введите адрес электронной почты", checkFinal.getText());

        out();
    }
        @After
                public void out(){driver.quit();}








        private void waitUtilElementToBeClickable(WebElement element) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        private void waitUtilElementToBeVisible(WebElement element) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }

        private void fillInputField(WebElement element, String value) {
            waitUtilElementToBeClickable(element);
            element.click();
            element.sendKeys(value);
            Assert.assertEquals("Поле было заполнено некорректно",
                    value, element.getAttribute("value"));
        }
    private void fillInputField(WebElement element, String value1, String value2) {
        waitUtilElementToBeClickable(element);
        element.click();
        element.sendKeys(value1);
        Assert.assertEquals("Поле было заполнено некорректно",
                value2, element.getAttribute("value"));
    }
    }


