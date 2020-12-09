import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class WebDriverConverseTest {
    WebDriver driver;
    private static ChromeDriverService service;

    @BeforeTest(alwaysRun = true)
    public void BrowserSetup() throws IOException {
        System.setProperty("webdriver.chrome.driver","H:\\3\\5\\epam\\chromedriver.exe");
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("H:\\3\\5\\epam\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void AddProductToFavoriteTest(){
        driver.get("https://www.converse.com/shop/p/chuck-taylor-all-star-unisex-high-top-shoe/M9160_040.html?pid=M9006MP&dwvar_M9006MP_size=040&dwvar_M9006MP_color=black&dwvar_M");
        WebElement closeBlockButton = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//button[@class='window-modal__close']")));
        closeBlockButton.click();
        
        WebElement signIn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//div[@class='header-utility__item header-utility__item--icon header-user flex display--small-up']")));
        signIn.click();
        
        WebElement inputLogin = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//input[@class='input-text validate-strict-email tab-hidden  required']")));
        inputLogin.sendKeys("ogurtsova-01@mail.ru");
        
        WebElement inputPassword = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//input[@class='input-text tab-hidden  required']")));
        inputPassword.sendKeys("20022001_Alog");
        
        WebElement signInButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//button[@class='button button--primary set--full']")));
        signInButton.click();
        
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//span[text()='Hey, Alexandra']")));
        
        WebElement addToFavoriteButton = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@class='primary-information__favorite invert-theme']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(addToFavoriteButton).click().build().perform();
        addToFavoriteButton.click();
        
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector(".favorite-action__add")));
        
        WebElement goToFavoritesButton = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//a[@class='header-favorites__link flex']")));
        goToFavoritesButton.click();
        
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
               .xpath("//div[@class='account-head__title flex flex-align-center']")));

        Assert.assertEquals( "Chuck Taylor All Star", driver.findElement(By
                .xpath("//a[@class='product-tile__url link--underline']")).getText());
    }
    

    @AfterTest
    public void TearDown(){
        driver.quit();
        driver=null;
    }
}
