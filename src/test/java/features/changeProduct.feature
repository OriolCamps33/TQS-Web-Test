Feature: ChangeProducts

	Scenario Outline: Change Products Type
	
		Given the user is in the index page
		When the user clicks Productos
		When the user clicks <type> chosen product
		Then the <type> shop changes
		
		Examples:
		|      type       |
		| Cartas Sueltas  |
		|     Sobres      |
		| Cajas de Sobres |