Feature: CardLists

	Scenario Outline: CreateCardList
		Given the user is in the index page
		And the user goes to MyWants
		And the user writtes the <name> of the list
		And the user adds the list
		Then the <name> list appears
		
		Examples:
		| name  |
		|lista1 |
	
	
	Scenario Outline: TryCreateRepeatedList
		Given the user is in the index page
		When the user goes to MyWants
		And the user writtes the <name> of the list
		And the user adds the list
		Then the error message appears
		
		Examples:
		| name  |
		|lista1 |
	
	Scenario Outline: DeleteCardList
		Given the user is in the index page
		When the user goes to MyWants
		And the user enters the list wants
		And the user opens the list options
		And the user deletes the list
		Then the empty message appears
		
		Examples:
		| name  |
		|lista1 |

