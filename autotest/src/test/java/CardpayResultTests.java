import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CardpayResultTests {

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
    public void check3DSAccept() {
        WebElement sumField = driver.findElement(By.id("total-amount"));
        String sumFieldText = sumField.getText();

        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

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

        WebElement successButton = driver.findElement(By.id("success"));
        successButton.click();

        WebElement resultSumField = driver.findElement(By.id("payment-item-total-amount"));
        String resultSumFieldText = resultSumField.getText();
        assert sumFieldText.equals(resultSumFieldText);

        WebElement resultStatusTitle = driver.findElement(By.id("payment-status-title"));
        String resultStatusTitleText = resultStatusTitle.findElement(By.tagName("span")).getText();
        assert resultStatusTitleText.equals("Success");

        //Объявление переменной = поиск эл-та по id и присваивание его значения в переменную
        WebElement resultCardNumberField = driver.findElement(By.id("payment-item-cardnumber"));
        //Объявление строки = эл-т который нашли выше.поиск эл-та по классу.получение текста
        String resultCardNumberText = resultCardNumberField.findElement(By.className("payment-info-item-data")).getText();
        //assert - проверка выполнения условия, equals - сравнение на равенство
        //Строку объявленную в предыдущем шаге сравниваем с значением в скобках
        assert resultCardNumberText.equals("...0002");

        WebElement resultCardHolderField = driver.findElement(By.id("payment-item-cardholder"));
        String resultCardHolderText = resultCardHolderField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardHolderText.equals("IVANOV IVAN");

        WebElement resultCardTypeField = driver.findElement(By.id("payment-item-cardtype"));
        String resultCardTypeText = resultCardTypeField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardTypeText.equals("VISA");
    }

    @Test
    public void check3DSDecline() {
        WebElement sumField = driver.findElement(By.id("total-amount"));
        String sumFieldText = sumField.getText();

        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000002");

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

        WebElement successButton = driver.findElement(By.id("failure"));
        successButton.click();

        WebElement resultSumField = driver.findElement(By.id("payment-item-total-amount"));
        String resultSumFieldText = resultSumField.getText();
        assert sumFieldText.equals(resultSumFieldText);

        WebElement resultStatusTitle = driver.findElement(By.id("payment-status-title"));
        String resultStatusTitleText = resultStatusTitle.findElement(By.tagName("span")).getText();
        assert resultStatusTitleText.equals("Decline");

        WebElement resultCardNumberField = driver.findElement(By.id("payment-item-cardnumber"));
        String resultCardNumberText = resultCardNumberField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardNumberText.equals("...0002");

        WebElement resultCardHolderField = driver.findElement(By.id("payment-item-cardholder"));
        String resultCardHolderText = resultCardHolderField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardHolderText.equals("IVANOV IVAN");

        WebElement resultCardTypeField = driver.findElement(By.id("payment-item-cardtype"));
        String resultCardTypeText = resultCardTypeField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardTypeText.equals("VISA");

    }

    @Test
    public void checkNo3DSAccept() {
        WebElement sumField = driver.findElement(By.id("total-amount"));
        String sumFieldText = sumField.getText();

        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("4000000000000077");

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

        WebElement resultSumField = driver.findElement(By.id("payment-item-total-amount"));
        String resultSumFieldText = resultSumField.getText();
        assert sumFieldText.equals(resultSumFieldText);

        WebElement resultStatusTitle = driver.findElement(By.id("payment-status-title"));
        String resultStatusTitleText = resultStatusTitle.findElement(By.tagName("span")).getText();
        assert resultStatusTitleText.equals("Success");

        WebElement resultCardNumberField = driver.findElement(By.id("payment-item-cardnumber"));
        String resultCardNumberText = resultCardNumberField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardNumberText.equals("...0077");

        WebElement resultCardHolderField = driver.findElement(By.id("payment-item-cardholder"));
        String resultCardHolderText = resultCardHolderField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardHolderText.equals("IVANOV IVAN");

        WebElement resultCardTypeField = driver.findElement(By.id("payment-item-cardtype"));
        String resultCardTypeText = resultCardTypeField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardTypeText.equals("VISA");


    }

    @Test
    public void checkNo3DSDecline() {
        WebElement sumField = driver.findElement(By.id("total-amount"));
        String sumFieldText = sumField.getText();

        WebElement cardNumberField = driver.findElement(By.id("input-card-number"));
        cardNumberField.sendKeys("5555555555554477");

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

        WebElement resultSumField = driver.findElement(By.id("payment-item-total-amount"));
        String resultSumFieldText = resultSumField.getText();
        assert sumFieldText.equals(resultSumFieldText);

        WebElement resultStatusTitle = driver.findElement(By.id("payment-status-title"));
        String resultStatusTitleText = resultStatusTitle.findElement(By.tagName("span")).getText();
        assert resultStatusTitleText.equals("Decline");

        WebElement resultCardNumberField = driver.findElement(By.id("payment-item-cardnumber"));
        String resultCardNumberText = resultCardNumberField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardNumberText.equals("...4477");

        WebElement resultCardHolderField = driver.findElement(By.id("payment-item-cardholder"));
        String resultCardHolderText = resultCardHolderField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardHolderText.equals("IVANOV IVAN");

        WebElement resultCardTypeField = driver.findElement(By.id("payment-item-cardtype"));
        String resultCardTypeText = resultCardTypeField.findElement(By.className("payment-info-item-data")).getText();
        assert resultCardTypeText.equals("MASTERCARD");

    }

}
