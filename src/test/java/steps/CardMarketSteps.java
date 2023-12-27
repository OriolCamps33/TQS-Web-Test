package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.util.List;


public class CardMarketSteps {

	static WebDriver driver;
	static WebDriverWait wait;
	
	//Funcion comprueba pagina de inicio, todas las features la usan
	
	@BeforeAll
	public static void before_or_after_all()
	{
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver-win64/chromedriver.exe");

		driver = new ChromeDriver();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	
	@AfterAll
	public static void after_all() {
	}
	
	@Given("the user is in the page onePiece")
	public void UserInIndexPageOnePiece() 
	{
		driver.navigate().to("https://www.cardmarket.com/es/OnePiece");
		WebElement element = driver.findElement(By.partialLinkText("One Piece"));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	@Given("the user is in the index page")
	public void UserInIndexPage() 
	{
		driver.navigate().to("https://www.cardmarket.com/es/Magic");
		
	}
	
	//------------------------------------------------------------------------------------------------------------
	//funciones feature change game
	
	@When("the user clicks the game selector")
	public void UserClicksGameSelector() 
	{
		driver.findElement(By.className("nav-link")).click();
	}
	
	@When("^the user clicks (.*) chosen game") 
	public void UserClicksGameChosen(String game){
		WebElement element = driver.findElement(By.xpath("//a[contains(@title, '" + game + "')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	@Then("^the (.*) shop appears")
	public void NewIndexAppears(String title) {
		String titulo = driver.findElement(By.partialLinkText(title)).getText();
		Assert.assertTrue(titulo.contains(title));
	}
	
	//------------------------------------------------------------------------------------------------------------
	//funciones feature change product
	
	@When("the user clicks Productos") 
	public void UserClicksProductos() {
		driver.findElement(By.className("fonticon-products")).click();
	}
	
	
	@When("^the user clicks (.*) chosen product") 
	public void UserClicksProductTypeChosen(String type) {
		WebElement element = driver.findElement(By.partialLinkText(type));
		wait.until(ExpectedConditions.visibilityOf(element));
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@Then("^the (.*) shop changes")
	public void NewProductTitle(String type) {
		String titulo = driver.findElement(By.className("H1_PageTitle")).getText();
		Assert.assertTrue(titulo.contains(type));
	}
	
	
	//----------------------------------------------------------------------------------------------------------------
	//funciones feature iniciar sesion
	
	// Scenario Iniciar Sesion
	@When("^the user writes (.*) username") 
	public void UserWritesUsername(String username) {		
		driver.findElement(By.name("username")).sendKeys(username);
	}
	
	@When("^the user writes (.*) password") 
	public void UserWritesPassword(String password) {
		driver.findElement(By.name("userPassword")).sendKeys(password);
	}

	@When("the user clicks IniciarSesion button")
	public void userClicksIniciarSesionButton() {
		WebElement element = driver.findElement(By.className("btn-outline-primary"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		//prguntar al profe. buscar btn-outline-primary esta feo, hay mas de uno, solo que el primero que pilla es el que queremos
	}
	
	@Then("the index page changes")
	public void IndexPageChanges() {
		String titulo = driver.findElement(By.id("taskbar")).getText();
		Assert.assertTrue(titulo.contains("Tareas"));
		
	}
	
	// Scenario Fallo Iniciar Sesion
	@When("the user is log out") 
	public void UserLogOut() {
		driver.findElement(By.id("navbar-toggler-icon-custom")).click();
		WebElement element = driver.findElement(By.id("logout-link"));
		element.click();
	}
	
	@Then("the alert box appears")
	public void AlertBoxAppears() {
		WebElement element = driver.findElement(By.className("alert-heading"));
		wait.until(ExpectedConditions.visibilityOf(element));		
		Assert.assertTrue(element.getText().contains("try to login again"));
		
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------------
	//funciones feature buscar cartas
	
	// Scenario busca carta buena
	@When("^the user writes (.*) of the card") 
	public void UserWritesNameOfCard(String name) {
		driver.findElement(By.id("ProductSearchInput")).sendKeys(name);
	}
	
	
	@When("the user clicks search button") 
	public void UserClicksSearchButton() {
		driver.findElement(By.id("search-btn")).click();
	}
	

	
	@When("^the user clicks (.*) he wants") 
	public void UserClicksCardWants(String card) {
		
		WebElement element = driver.findElement(By.partialLinkText(card));
		wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}


	
	@Then("^the (.*) description appears")
	public void CardDescriptionAppears(String card) {
		String titulo = driver.findElement(By.className("page-title-container")).getText();
		Assert.assertTrue(titulo.contains(card));
	}
	
	
	// Scenario buscar carta mala
	@Then("the card not found message apperas")
	public void CardNotFoundMessageAppears() {
		WebElement element = driver.findElement(By.className("noResults"));
		
		
		Assert.assertTrue(element.isDisplayed());
		String titulo = element.getText();
		Assert.assertTrue(titulo.contains("No hay resultados para su consulta"));
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------------
	//funciones feature filtrar idioma
	
	@Given("the user is in a card page")
	public void UserInCardPage() 
	{
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver-win64/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://www.cardmarket.com/es/Magic/Products/Singles/Commander-The-Lost-Caverns-of-Ixalan/Sol-Ring");
		
	}
	
	@When("the user clicks sidebarFilter") 
	public void UserClicksSidebarFilter() {
		//driver.findElement(By.className("fonticon-filter")).click();
		WebElement element = driver.findElement(By.className("fonticon-filter"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@When("the user clicks language") 
	public void UserClicksLanguage() {
		//driver.findElement(By.className("fonticon-language")).click();
		WebElement element = driver.findElement(By.className("fonticon-language"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@When("^the user marks (.*)") 
	public void UserMarksLanguage(String language) {
		//driver.findElement(By.name(language)).click();
		WebElement element = driver.findElement(By.name(language));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@When("the user aply the filter") 
	public void UserAplyFilter() {
		//driver.findElement(By.name("apply")).click();
		WebElement element = driver.findElement(By.name("apply"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@Then("^the (.*) appears checked")
	public void CardLanguageChecked(String language) {
		WebElement element = driver.findElement(By.name(language));
		Assert.assertEquals(element.getAttribute("checked"), "true");
		driver.close();
	}
	
}





























