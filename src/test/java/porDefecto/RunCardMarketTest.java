package porDefecto;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/*
 //no funciona cambiar juego, no encuentra donde esta, no se puede por href supongo
@CucumberOptions(features="src/test/java/features/cardMarket.feature", glue="steps")
class changeGameTest extends AbstractTestNGCucumberTests{
}



 //cambiar tipo producto funciona
@CucumberOptions(features="src/test/java/features/changeProduct.feature", glue="steps")
class changeProductsTest extends AbstractTestNGCucumberTests{
}


//funciona iniciar sesion
@CucumberOptions(features="src/test/java/features/IniciarSesion.feature", glue="steps")
class initSesionTest extends AbstractTestNGCucumberTests{
}


//buscar carta funciona
@CucumberOptions(features="src/test/java/features/searchCard.feature", glue="steps")
class searchCardTest extends AbstractTestNGCucumberTests{
}
*/

//filtrar por idioma
@CucumberOptions(features="src/test/java/features/LanguageFilter.feature", glue="steps")
class filterByLanguageTest extends AbstractTestNGCucumberTests{
}



