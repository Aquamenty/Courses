import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class CardpayValidationTests {

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @After
    public void close() {
        driver.close();
    }

    @Test
    public void checkCardNumberEmpty() {
        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVANOV IVAN");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2024");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-number-field"));
//        String cardNumberErrorText = cardNumberContainer.findElement(By.className("form-input")).findElement(By.tagName("label")).getText();
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Card number is required");
    }

    @Test
    public void checkCardNumberShort() {

        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("40000000");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVANOV IVAN");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2024");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-number-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Card number is not valid");
    }

    @Test
    public void checkInvalidCardNumber() {

        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("555555555555554443");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVANOV IVAN");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2024");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-number-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Card number is not valid");
    }

    @Test
    public void checkCardHolderEmpty() {
        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2024");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-holder-field"));
//        String cardNumberErrorText = cardNumberContainer.findElement(By.className("form-input")).findElement(By.tagName("label")).getText();
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Cardholder name is required");
    }

    @Test
    public void checkCardHolderShort() {
        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVA");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2024");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-holder-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Allowed from 4 to 50 characters");
    }

    @Test
    public void checkCardHolderIncorrectCharacters() {
        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("%$$^@врарва");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2024");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-holder-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Cardholder name is not valid");
    }

    @Test
    public void checkCardDateEmpty() {
        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVAN IVANOV");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-holder-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Expiration Date is required");
    }

    @Test
    public void checkCardDateEmptyMonth() {
        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVAN IVANOV");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2024");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-holder-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Expiration Date is required");
    }

    @Test
    public void checkCardDateEmptyYear() {
        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVAN IVANOV");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-holder-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Expiration Date is required");
    }

    @Test
    public void checkCardDateInvalid() {
        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVAN IVANOV");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2018");

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("456");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-holder-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("Invalid data provided");
    }

    @Test
    public void checkCardSecurityCodeEmpty() {
        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVAN IVANOV");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2024");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardNumberContainer = driver.findElement(By.id("card-holder-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("CVV2/CVC2/CAV2 is required");
    }

    @Test
    public void checkCardSecurityCodeShort() {
        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

        WebElement cardHolderField = driver.findElement(By.id("input-card-holder"));
        cardHolderField.sendKeys("IVAN IVANOV");

        WebElement cardExpiresMonthField = driver.findElement(By.id("card-expires-month"));
        cardExpiresMonthField.sendKeys("05");

        WebElement cardExpiresYearField = driver.findElement(By.id("card-expires-year"));
        cardExpiresYearField.sendKeys("2024");

        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();

        WebElement cardCVCField = driver.findElement(By.id("input-card-cvc"));
        cardCVCField.sendKeys("45");

        WebElement cardNumberContainer = driver.findElement(By.id("card-holder-field"));
        String cardNumberErrorText = cardNumberContainer.findElement(By.xpath("//label[@class='error']")).getText();
        assert cardNumberErrorText.equals("CVV2/CVC2/CAV2 is not valid");
    }

}

