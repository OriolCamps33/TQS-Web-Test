
Feature: ChangeGame
	Scenario Outline: Change Card Game
		
		Given the user is in the index page
		When the user clicks the game selector
		And the user clicks <game> chosen game
		Then the <title> shop appears
		
		Examples:
		|  game  |  title  |
		| Pokémon| POKÉMON |
		|One Piece|ONE PIECE|
		|Digimon | DIGIMON |


