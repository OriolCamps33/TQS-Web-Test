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
	
	//------------------------------------------------------------------------------------------------------------
	//Funcion comprueba pagina de inicio, todas las features la usan
	
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
		wait.until(ExpectedConditions.visibilityOf(element));	
		element.click();
	}
	
	@Then("the alert box appears")
	public void AlertBoxAppears() {
		WebElement element = driver.findElement(By.className("alert-heading"));
		wait.until(ExpectedConditions.visibilityOf(element));		
		Assert.assertTrue(element.getText().contains("La contraseña es incorrecta"));
		
	}
	
	//scenario wrong password
	@Then("the wrong password message appears")
	public void WrongPasswordMessageAppears() {
		WebElement element = driver.findElement(By.className("alert-heading"));
		wait.until(ExpectedConditions.visibilityOf(element));		
		Assert.assertTrue(element.getText().contains("La contraseña es incorrecta"));
		
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
	
	
	//----------------------------------------------------------------------------------------------------------------
	//funciones feature tendencias
	
	@When("the user clicks the tendencias button") 
	public void UserClicksTendenciasButton() {
		WebElement element = driver.findElement(By.partialLinkText("VER TENDENCIAS"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@When("^the user clicks (.*) tendencia he wants to see") 
	public void UserClicksBestSellerButton(String tendencia) {
		WebElement element = driver.findElement(By.partialLinkText(tendencia));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@Then("^the (.*) tendencia appears")
	public void theBestSellersAppears(String tendenciaTitle) {
		String titulo = driver.findElement(By.partialLinkText(tendenciaTitle)).getText();
		Assert.assertTrue(titulo.contains(tendenciaTitle));
	}
	
	
	//----------------------------------------------------------------------------------------------------------------
	//funciones feature cardList
	
	//scenario crear listas
	@When("the user inits sesion") 
	public void UserInitsSesion() {
		driver.findElement(By.name("username")).sendKeys("PGutCa");
		driver.findElement(By.name("userPassword")).sendKeys("MarcOriol3");
		WebElement element = driver.findElement(By.className("btn-outline-primary"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	@When("the user goes to MyWants") 
	public void UserGoesMyWants() {
		WebElement element = driver.findElement(By.id("buying-dropdown"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		element = driver.findElement(By.xpath("//a[contains(@title, 'Mis wants')]"));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@When("^the user writtes the (.*) of the list") 
	public void UserWrittesList(String name) {
		driver.findElement(By.id("swWantsListName")).sendKeys(name);
	}
	
	@When("the user adds the list") 
	public void UserAddsTheList() {
		WebElement element = driver.findElement(By.className("newList-btn"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@Then("the empty list appears")
	public void theEmptyListAppears() {
		String titulo = driver.findElement(By.className("noResults")).getText();
		Assert.assertTrue(titulo.contains("Todavía no has añadido cartas a tu lista de wants."));
	}
	
	
	//scenario eliminar listas
	@When("the user returnsToWants") 
	public void UserReturnToWants() {
		WebElement element = driver.findElement(By.partialLinkText("Wants"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@Then("the error message appears")
	public void theErrorMessageAppears() {
		String titulo = driver.findElement(By.className("alert-heading")).getText();
		Assert.assertTrue(titulo.contains("Ya tienes una lista de wants con ese nombre"));
	}
	
	//scenario rename + delete
	@When("the user enters the list wants") 
	public void UserEntersListWants() {
		WebElement element = driver.findElement(By.partialLinkText("VIEW / EDIT LIST"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@When("the user opens the list options") 
	public void UserOpensListOptions() {
		WebElement element = driver.findElement(By.partialLinkText("WANTS OPTIONS"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@When("^the user renames (.*) the list") 
	public void UserRenamesList(String newName) {
		//driver.findElement(By.className("form-control")).sendKeys(newName);
		
		WebElement element = driver.findElement(By.xpath("//a[contains(@name, 'newName')]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		
		element = driver.findElement(By.partialLinkText("RENAME"));
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@When("the user deletes the list") 
	public void UserDeletesList() {
		WebElement element = driver.findElement(By.className("d-grid"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@Then("^the list has new name (.*)")
	public void theListHasNewName(String newName) {
		String titulo = driver.findElement(By.className("flex-grow-1")).getText();
		Assert.assertTrue(titulo.contains(newName));
	}
	
	
	
	
}
