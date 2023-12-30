Feature: Carrito

  Scenario Outline: addCardToCart
    #Se reciclan los steps del search card
  	Given the user is in the page onePiece
		And the user writes <name> of the card
		And the user clicks search button
		And the user clicks <card> he wants
		And the user add the product to cart
		Then the <card> appears in the cart
		
		Examples:
		|  name  |              card               |
		|  luffy | Monkey.D.Luffy (OP04-090) (V.1) |		
		|  kaido |      Kaido (OP01-094) (V.1)     |
		|  zoro  |  Roronoa Zoro (OP01-025) (V.2)  |