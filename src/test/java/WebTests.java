import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
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
        try {
            WebElement signupLoginButton = driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
            signupLoginButton.click();

            SignupPage signupPage = new SignupPage(driver);

            // Preencher informações básicas
            signupPage.enterName("Gabriel Borel");
            signupPage.enterEmail("testemail998@hotmail.com");
            signupPage.clickSignup();

            // Preencher informações adicionais
            signupPage.enterPassword("Senha123!");
            signupPage.enterFirstName("Gabriel");
            signupPage.enterLastName("Borel");
            signupPage.enterAddress("Endereço Teste, 93, 301");
            signupPage.enterState("RJ");
            signupPage.enterCity("VR");
            signupPage.enterZipCode("27259170");
            signupPage.enterMobileNumber("+5524999899256");

            // Clicar em criar conta
            JavascriptExecutor js = (JavascriptExecutor) driver;
            signupPage.clickCreateAccount(js);

            // Verificar se a conta foi criada
            String currentUrl = driver.getCurrentUrl();
            Assertions.assertTrue(currentUrl.contains("account_created"));
        } catch (Exception e) {
            capturarTela(driver);
            throw e;
        }
    }

    @Test
    public void testaLoginInvalido() {
        try {
            // Navegar até a página de login
            WebElement signupLoginButton = driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
            signupLoginButton.click();

            LoginPage loginPage = new LoginPage(driver);

            loginPage.enterEmail("invalido@example.com");
            loginPage.enterPassword("senhaerrada");
            loginPage.clickLogin();

            // Verificar mensagem de erro
            String errorMessage = loginPage.getErrorMessage();
            Assertions.assertTrue(errorMessage.contains("Your email or password is incorrect!"));
        } catch (Exception e) {
            capturarTela(driver);
            throw e;
        }
    }

    private void capturarTela(WebDriver driver) {
        TakesScreenshot scrShot = (TakesScreenshot) driver;
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        try {
            int random = (int) (Math.random() * 10000);
            File DestFile = new File("./screenshot-" + random + ".png");
            FileHandler.copy(SrcFile, DestFile);
        } catch (IOException ex) {
            System.out.println("Erro ao salvar a captura de tela: " + ex.getMessage());
        }
    }
}
