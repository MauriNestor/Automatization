package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("Ingresar a la pagina {string}")
    public void ingresar_a_la_pagina(String myUrl) {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(myUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Tiempo de espera de 10 segundos
        sleepFor(3);
    }

    @When("Introducir el usuario {string} en username")
    public void introducir_el_usuario_en_username(String username) {
        driver.findElement(By.id("usuario")).sendKeys(username);
        sleepFor(3);
    }

    @When("Introducir {string} en Contraseña")
    public void introducir_en_contraseña(String password) {
        driver.findElement(By.id("password-login")).sendKeys(password);
        sleepFor(3);
    }

    @When("Seleccionar {string} en rol")
    public void seleccionar_en_rol(String rol) {
        WebElement combo = driver.findElement(By.id("rol"));
        Select select = new Select(combo);
        select.selectByVisibleText(rol);
        sleepFor(3);
    }

    @When("Click en el boton iniciar sesion")
    public void click_en_el_boton_iniciar_sesion() {
        driver.findElement(By.id("submit-login")).click();
        sleepFor(3);
    }

    @When("Click en icono de carrito")
    public void click_en_icono_de_carrito() {
        driver.findElement(By.cssSelector("a[href='/carrito']")).click();
        // Agregar una espera después de hacer clic en el icono del carrito para asegurarse de que se cargue la página
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Ver Curso']")));
        sleepFor(5);
    }

    @When("Click en Ver Curso")
    public void click_en_ver_curso() {
        WebElement verCursoLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Ver Curso']")));
        verCursoLink.click();
        sleepFor(5);
    }

    @Then("mostrar detalles del curso agregado al carrito")
    public void mostrar_detalles_del_curso_agregado_al_carrito() {
        WebElement botonVolver = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btn.btn-success")));
        assertTrue("El botón 'Volver' no se muestra correctamente", botonVolver.isDisplayed());
        sleepFor(5);
        driver.quit();
    }

    private void sleepFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
