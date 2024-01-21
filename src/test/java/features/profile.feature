Feature: Profile

  Scenario Outline: ChangeName
  	Given the user enters in the users page
  	And the user go to his profile
  	And the user edit the <name>
  	Then the name changes
  	
  	Examples: 
		|  name  |
		|  Javi  |
		|  Pol   |
		|  Pedro |
	
	Scenario Outline: SameName
		Given the user is in the index page
  	When the user enters in the users page
  	And the user go to his profile
  	And the user edit the <name>
  	Then the same name message appears
		
		Examples: 
		|  name  |
		|  Pedro |
		