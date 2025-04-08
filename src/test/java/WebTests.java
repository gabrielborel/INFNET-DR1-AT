import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebTests {
    private WebDriver driver;
    private JavascriptExecutor js;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
        js = (JavascriptExecutor) driver;
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testaCadastroValido() {
        WebElement signupLoginButton = driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
        signupLoginButton.click();

        WebElement nameField = driver.findElement(By.xpath("//input[@name='name']"));
        nameField.sendKeys("Gabriel Borel");

        WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
        emailField.sendKeys("teste01291@hotmail.com");

        WebElement signupButton = driver.findElement(By.xpath("//button[contains(text(),'Signup')]"));
        signupButton.click();

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Senha123!");

        WebElement firstName = driver.findElement(By.id("first_name"));
        firstName.sendKeys("Gabriel");

        WebElement lastName = driver.findElement(By.id("last_name"));
        lastName.sendKeys("Borel");

        WebElement address = driver.findElement(By.id("address1"));
        address.sendKeys("Endereço Teste, 93, 301");

        WebElement state = driver.findElement(By.id("state"));
        state.sendKeys("RJ");

        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("VR");

        WebElement zipCode = driver.findElement(By.id("zipcode"));
        zipCode.sendKeys("27259170");

        WebElement mobileNumber = driver.findElement(By.id("mobile_number"));
        mobileNumber.sendKeys("+5524999899256");

        WebElement createAccountButton = driver.findElement(By.xpath("//button[contains(text(),'Create Account')]"));
        js.executeScript("arguments[0].scrollIntoView();", createAccountButton);
        createAccountButton.click();

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("account_created"));
    }

    @Test
    public void testaLoginInvalido() {
        // Navegar até a página de login
        WebElement signupLoginButton = driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
        signupLoginButton.click();

        // Preencher credenciais inválidas
        WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys("invalido@example.com");

        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("senhaerrada");

        // Submeter o formulário de login
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        loginButton.click();

        // Verificar a mensagem de erro
        WebElement errorMessage = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"));
        Assertions.assertTrue(errorMessage.isDisplayed());
    }
}
