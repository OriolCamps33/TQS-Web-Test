
Feature: ChangeGame
	Scenario Outline: Change Card Game
		
		Given the user is in the index page
		When the user clicks the game selector
		And the user clicks <title> chosen game
		Then the <title> shop appears
		
		Examples:
		|  game  |  title  |
		| Pokemon| Pokémon |
		|OnePiece|One Piece|
		|Digimon | Digimon |


