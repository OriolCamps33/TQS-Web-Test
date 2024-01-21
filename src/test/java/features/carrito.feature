Feature: Carrito

  Scenario Outline: addCardToCart
  	Given the user is in the page onePiece
		When the user writes <name> of the card
		And the user clicks search button
		And the user clicks <card> he wants
		And the user add the product to cart
		Then the <card> appears in the cart
		
		Examples:
		|  name  |              card               |
		|  luffy | Monkey.D.Luffy (OP04-090) (V.1) |		
		|  kaido |      Kaido (OP01-094) (V.1)     |
		|  zoro  |  Roronoa Zoro (OP01-025) (V.2)  |
		
	Scenario Outline: removeCardToCart
  	Given the user is in the page onePiece
  	When the user enters in the cart
		And the user clean the cart
		Then the cart is empty