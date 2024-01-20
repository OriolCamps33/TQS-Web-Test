package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;


public class CardMarketSteps {

	static WebDriver driver;
	static WebDriverWait wait;
	
	@BeforeAll
	public static void before_or_after_all() throws InterruptedException
	{	
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver-win64/chromedriver.exe");

		driver = new ChromeDriver();
		driver.navigate().to("https://www.cardmarket.com/es/OnePiece");
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		synchronized (wait) {
			wait.wait(500);
		
			driver.findElement(By.name("username")).sendKeys("PGutCa");
			wait.wait(500);
			driver.findElement(By.name("userPassword")).sendKeys("MarcOriol3");
			wait.wait(500);
			WebElement element = driver.findElement(By.className("btn-outline-primary"));
			element.click();
			wait.wait(500);
	
			driver.findElement(By.cssSelector("[aria-label='Aceptar todas las cookies']")).click();
			wait.wait(500);
		}
	}
	
	@AfterAll
	public static void after_all() {
		//driver.quit();
	}
	
	// ------------------------------
	// Funcion Index Page
	// ------------------------------
	
	@Given("the user is in the page onePiece")
	public void UserInIndexPageOnePiece()
	{
		driver.navigate().to("https://www.cardmarket.com/es/OnePiece");		
	}
	
	@Given("the user is in the index page")
	public void UserInIndexPage() throws InterruptedException 
	{
		driver.navigate().to("https://www.cardmarket.com/es/Magic");	
	}
	
	@Given("the user is in the search page")
	public void searchPage() {
		driver.navigate().to("https://www.cardmarket.com/es/OnePiece/Products/Search");
	}
	// ------------------------------
	// feature change game
	// ------------------------------
	
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
	
	// ------------------------------
	// feature change product
	// ------------------------------
	
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
	
	
	// ------------------------------
	// feature iniciar sesion
	// ------------------------------
	
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
	
	// -------------------------------
	// feature buscar cartas
	// ------------------------------
	
	// Scenario busca carta buena
	@When("^the user writes (.*) of the card") 
	public void UserWritesNameOfCard(String name) throws InterruptedException {
		driver.findElement(By.id("ProductSearchInput")).sendKeys(name);
		synchronized (wait) {
			wait.wait(200);
		}
	}
	
	
	@When("the user clicks search button") 
	public void UserClicksSearchButton() throws InterruptedException {
		driver.findElement(By.id("search-btn")).click();
		synchronized (wait) {
			wait.wait(200);
		}
	}
	

	
	@When("^the user clicks (.*) he wants") 
	public void UserClicksCardWants(String card) throws InterruptedException {
		
		WebElement element = driver.findElement(By.partialLinkText(card));
		synchronized (wait) {
			wait.wait(200);
			wait.until(ExpectedConditions.visibilityOf(element));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}
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
	
	
	
	// ------------------------------
	// feature filtrar idioma
	// ------------------------------
	
	@Given("the user is in a card page")
	public void UserInCardPage() 
	{
		driver.navigate().to("https://www.cardmarket.com/es/Magic/Products/Singles/Commander-The-Lost-Caverns-of-Ixalan/Sol-Ring");
		
	}
	
	@When("the user clicks sidebarFilter") 
	public void UserClicksSidebarFilter() {
		WebElement element = driver.findElement(By.className("fonticon-filter"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@When("the user clicks language") 
	public void UserClicksLanguage() {
		WebElement element = driver.findElement(By.className("fonticon-language"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@When("^the user marks (.*)") 
	public void UserMarksLanguage(String language) {
		WebElement element = driver.findElement(By.name(language));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@When("the user aply the filter") 
	public void UserAplyFilter() {
		WebElement element = driver.findElement(By.name("apply"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@Then("^the (.*) appears checked")
	public void CardLanguageChecked(String language) {
		WebElement element = driver.findElement(By.name(language));
		Assert.assertEquals(element.getAttribute("checked"), "true");
	}
	
	
	// ------------------------------
	// feature tendencias
	// ------------------------------
	
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
	
	
	// ------------------------------
	// feature cardList
	// ------------------------------
	
	// scenario crear listas
	
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
	
	@Then("^the (.*) list appears")
	public void theNameListAppears(String name) {
		String titulo = driver.findElement(By.className("flex-grow-1")).getText();
		Assert.assertTrue(titulo.contains(name));
	}
	
	// scenario Repeat list	
	
	@Then("the error message appears")
	public void theErrorMessageAppears() {
		String titulo = driver.findElement(By.className("alert-heading")).getText();
		Assert.assertTrue(titulo.contains("Ya tienes una lista de wants con ese nombre"));
	}
	
	// Scenario remove list

	@When("the user opens the list options") 
	public void UserOpensListOptions() {
		WebElement element = driver.findElement(By.partialLinkText("WANTS OPTIONS"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@When("the user enters the list wants") 
	public void UserEntersListWants() {
		WebElement element = driver.findElement(By.partialLinkText("VIEW / EDIT LIST"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	@When("the user deletes the list") 
	public void UserDeletesList() throws AWTException, InterruptedException {
		
		Robot robot = new Robot();
		robot.mouseMove(800, 459); // Navigating through mouse hover. Note that the coordinates might differ, kindly check the coordinates of x and y axis and update it accordingly.
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(100);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(100);
	}
	
	@Then("the empty message appears")
	public void theEmptyListAppears() {
		String titulo = driver.findElement(By.className("noResults")).getText();
		Assert.assertTrue(titulo.contains("You haven't added any wants list yet."));
	}
	
		
	// ------------------------------
	// Feature carrito
	// ------------------------------
	
	// Scenario addCardToCart
	
	@When("the user add the product to cart")
	public void addToCart() throws InterruptedException {
		WebElement element = driver.findElement(By.cssSelector("[aria-label='Añadir a mi Carrito ']"));	
		element.click();
	}
	
	@Then("^the (.*) appears in the cart")
	public void checkTheCart(String card) throws InterruptedException {
		synchronized (wait) {
			wait.wait(2000);
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart")));
	        element.click();
	    }
		
		String titulo = driver.findElement(By.partialLinkText(card)).getText();
		Assert.assertTrue(titulo.contains(card));
	}
	
	// Scenario removeCardToCart
	
	@When("the user enters in the cart")
	public void enterTheCart() {
		synchronized (wait) {
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart")));
	        element.click();
	    }
	}
	
	@When("^the user clean the cart")
	public void removeTheCard() {
		synchronized (wait) {
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title='Vacíar carrito']")));
	        JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
	    }
	}
	
	@Then("the cart is empty")
	public void theCartEmpty() {
		String titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("h3"))).getText();
		Assert.assertTrue(titulo.contains("Tu carrito está vacío"));
	}
	
	// ------------------------------
	// Feature Profile
	// ------------------------------
		
	// Scenario ChangeName
	//When the user enters in the users page
	@Given("the user enters in the users page")
	public void enterTheUser() throws InterruptedException {
		driver.findElement(By.id("account-dropdown")).click();
		synchronized (wait) {
			wait.wait(200);
		}
		driver.findElement(By.cssSelector("[title='Cuenta']")).click();
		
	}
  	// And the user go to his profile
	@When("the user go to his profile")
	public void enterTheProfile() throws InterruptedException {
		driver.findElement(By.partialLinkText("Perfil")).click();
		synchronized (wait) {
			wait.wait(200);
		}
		
	}
  	// And the user edit the name
	@When("^the user edit the (.*)")
	public void editTheName(String name) throws InterruptedException {
		WebElement element = driver.findElement(By.className("col-md-4"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		synchronized (wait) {
			wait.wait(200);
			WebElement ele2 = driver.findElement(By.cssSelector("[name=\"firstName\"]"));
			ele2.clear();
			ele2.sendKeys(name);
			wait.wait(200);
			driver.findElement(By.className("btn-success")).click();
		}
	}
	//Then the name changes
	@Then("the name changes")
	public void nameChange() throws InterruptedException {
		synchronized (wait) {
			wait.wait(500);
		}
		String nombre = driver.findElement(By.id("AlertContainer")).getText();
		Assert.assertTrue(nombre.contains("Su petición se ha ejecutado correctamente"));
	}
	
	
	//Scenario SameName
	@Then("the same name message appears")
	public void sameName() throws InterruptedException {
		synchronized (wait) {
			wait.wait(500);
		}
		String nombre = driver.findElement(By.className("alert-heading")).getText();
		Assert.assertTrue(nombre.contains("exception_sameName"));
	}
	
	
	
	// ------------------------------
	// Feature Buscador
	// ------------------------------
	
	// Scenario Change product type
	@When("^the user change the category to (.*)")
	public void changeCategoryTo(String type) throws InterruptedException {
		Select select = new Select(driver.findElement(By.name("idCategory")));
		select.selectByVisibleText(type);
		synchronized (wait) {
			wait.wait(500);
		}
	}
	
	@When("the user search for products")
	public void searchProduct() throws InterruptedException {
		WebElement element = driver.findElement(By.className("col-auto"));
		synchronized (wait) {
			wait.wait(500);
		}
		
		element.click();
		
	}
	
	@Then("^the title changes to (.*)")
	public void changeTitle(String type) throws InterruptedException {
		synchronized (wait) {
			wait.wait(500);
		}
		String title = driver.findElement(By.className("H1_PageTitle")).getText();
		System.out.println(title);
		Assert.assertTrue(title.contains(type));
	}
	
	// Scenario Change edition type
	@When("^the user change the edition to (.*)")
	public void changeEditionTo(String edit) throws InterruptedException {
		Select select = new Select(driver.findElement(By.name("idCategory")));
		select.selectByVisibleText(edit);
		synchronized (wait) {
			wait.wait(500);
		}
	}
	
	@Then("^the (.*) appears in the list")
	public void changeEditionList(String edit) {
		String name = driver.findElement(By.id("productRow696230")).getText();
		Assert.assertTrue(name.contains(edit));
	}
	
}
