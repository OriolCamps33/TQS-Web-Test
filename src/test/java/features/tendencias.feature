Feature: tendencias

	Scenario Outline: BestSellers

		Given the user is in the index page
		When the user clicks the tendencias button
		And the user clicks <tendencia> tendencia he wants to see
		Then the <tendenciaTitle> tendencia appears
		
		Examples:
		|  				 tendencia  					|					tendenciaTitle				|
		| 				Best Sellers					| 				 Best Sellers 				|
		|				 Mejores Cartas					| 				Mejores cartas 				|
		|	Mayor Descenso De Existencias	| Mayor descenso de existencias	|
		|		Mayor Incremento De Precio	| 	Mayor incremento de precio 	|

