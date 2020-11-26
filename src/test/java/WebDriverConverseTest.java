import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class WebDriverConverseTest {
    WebDriver driver;

    @BeforeTest
    public void AddProductToFavoriteTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.converse.com/shop/p/chuck-taylor-all-star-unisex-high-top-shoe/M9160_040.html?pid=M9006MP&dwvar_M9006MP_size=040&dwvar_M9006MP_color=black&dwvar_M");
        WebElement closeBlockButton = (new WebDriverWait(driver, 100))
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
        new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//span[text()='Hey, Alexandra']")));
        WebElement addToFavoriteButton = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@class='primary-information__favorite invert-theme']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(addToFavoriteButton).click().build().perform();
        addToFavoriteButton.click();
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector(".favorite-action__add")));
        WebElement goToFavoritesButton = (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//a[@class='header-favorites__link flex']")));
        goToFavoritesButton.click();
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
               .xpath("//div[@class='account-head__title flex flex-align-center']")));
    }

    @Test
    public void CheckNameTest(){
        Assert.assertEquals( "Chuck Taylor All Star", driver.findElement(By
                .xpath("//a[@class='product-tile__url link--underline']")).getText());
    }

    @Test
    public void CheckPriceTest(){
        Assert.assertEquals("$55.00", driver.findElement(By
                .xpath("//span[@class='product-price--sales']")).getText());
    }

    @Test
    public void CheckImageTest(){
        Assert.assertEquals("https://www.converse.com/dw/image/v2/BCZC_PRD/on/demandware.static/-/Sites-cnv-master-catalog/default/dw12233c7f/images/e_08/M9160_E_08X1.jpg?sw=406",
                driver.findElement(By .xpath("//picture[@class='product-tile__img ratio-media ratio-media--fit product-tile__img--main--picture-element']/img")).getAttribute("src"));
    }

    @AfterTest
    public void TearDown(){
        driver.quit();
    }
}
