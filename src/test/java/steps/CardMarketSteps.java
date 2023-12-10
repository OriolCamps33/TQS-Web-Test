package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class CardMarketSteps {

	WebDriver driver;
	
	//Funcion comprueba pagina de inicio, todas las features la usan
	
	@Given("the user is in the index page")
	public void UserInIndexPage() 
	{
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver-win64/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://www.cardmarket.com/es/Magic");
		
	}
	
	@Given("the user is in the index page onePiece")
	public void UserInIndexPageOnePiece() 
	{
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver-win64/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://www.cardmarket.com/es/OnePiece");
		
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
		WebElement element = driver.findElement(By.partialLinkText(game));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@Then("^the (.*) shop appears")
	public void NewIndexAppears(String title) {
		String titulo = driver.findElement(By.className("nav-link")).getText();
		Assert.assertTrue(titulo.contains(title));
		driver.quit();
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
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@Then("^the (.*) shop changes")
	public void NewProductTitle(String type) {
		String titulo = driver.findElement(By.className("H1_PageTitle")).getText();
		Assert.assertTrue(titulo.contains(type));
		driver.quit();
	}
	
	
	//----------------------------------------------------------------------------------------------------------------
	//funciones feature iniciar sesion
	
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
		driver.findElement(By.className("btn-outline-primary")).click();
		//prguntar al profe. buscar btn-outline-primary esta feo, hay mas de uno, solo que el primero que pilla es el que queremos
	}
	
	@Then("the index page changes")
	public void IndexPageChanges() {
		String titulo = driver.findElement(By.id("taskbar")).getText();
		Assert.assertTrue(titulo.contains("Tareas"));
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------------
	//funciones feature buscar cartas
	
	
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
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}


	
	@Then("^the (.*) description appears")
	public void CardDescriptionAppears(String card) {
		String titulo = driver.findElement(By.className("page-title-container")).getText();
		Assert.assertTrue(titulo.contains(card));
		driver.quit();
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
		driver.quit();
	}
	
}





























