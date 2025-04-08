import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    WebDriver driver;

    // Elementos da página
    @FindBy(xpath = "//input[@name='name']")
    WebElement nameField;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement emailField;

    @FindBy(xpath = "//button[contains(text(),'Signup')]")
    WebElement signupButton;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "first_name")
    WebElement firstName;

    @FindBy(id = "last_name")
    WebElement lastName;

    @FindBy(id = "address1")
    WebElement address;

    @FindBy(id = "state")
    WebElement state;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(id = "zipcode")
    WebElement zipCode;

    @FindBy(id = "mobile_number")
    WebElement mobileNumber;

    @FindBy(xpath = "//button[contains(text(),'Create Account')]")
    WebElement createAccountButton;

    // Construtor
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Métodos para interagir com os elementos
    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void clickSignup() {
        signupButton.click();
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void enterFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void enterAddress(String address) {
        this.address.sendKeys(address);
    }

    public void enterState(String state) {
        this.state.sendKeys(state);
    }

    public void enterCity(String city) {
        this.city.sendKeys(city);
    }

    public void enterZipCode(String zipCode) {
        this.zipCode.sendKeys(zipCode);
    }

    public void enterMobileNumber(String mobileNumber) {
        this.mobileNumber.sendKeys(mobileNumber);
    }

    public void clickCreateAccount(JavascriptExecutor js) {
        js.executeScript("arguments[0].scrollIntoView();", createAccountButton);
        createAccountButton.click();
    }
}
