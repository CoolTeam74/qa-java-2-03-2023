import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    @FindBy(xpath= "//*[@id='passp-field-login']")
    private WebElement loginField;

    @FindBy(xpath= "//*[@id='passp-field-passwd']")
    private WebElement passwordField;

    @FindBy(xpath= "//*[contains(text(), 'Войти')]/..")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        // init filed
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }
}
