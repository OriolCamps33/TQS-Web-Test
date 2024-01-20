Feature: Buscador

	Scenario Outline: Change Products Type
	
		Given the user is in the search page
		When the user change the category to <type>
		And the user search for products
		Then the title changes to <type>
		
		Examples:
		|      type       |
		| Cartas Sueltas  |
		|     Sobres      |
		| Cajas de Sobres |
		
		
	Scenario Outline: Change Edition Type
	
		Given the user is in the search page
		When the user change the edition to <edit>
		And the user search for products
		Then the <edit> appears in the list
		
		Examples:
		|  		 		 edit 		       |
		|			 Paramount War			 |
		| Awakening of the New Era |
		|   Kingdoms of Intrigue   |