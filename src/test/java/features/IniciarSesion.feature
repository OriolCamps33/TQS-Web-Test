
Feature: Sesiones
	Scenario Outline: Iniciar Sesion
	
		Given the user is in the index page
		When the user is log out
		And the user writes <username> username
		And the user writes <password> password
		And the user clicks IniciarSesion button
		Then the index page changes
		
		Examples:
		|  username  |  password  |
		|   PGutCa   |  MarcOriol3  |		
	
	
	Scenario Outline: Fallo Iniciar Sesion
	
		Given the user is in the index page
		When the user is log out
		And the user writes <username> username
		And the user writes <password> password
		And the user clicks IniciarSesion button
		Then the alert box appears
		
		Examples:
		|  username  |  password  |
		|    asdf    |    asdf    |
	
	
	Scenario Outline: Fallo contrasenya Iniciar Sesion
	
		Given the user is in the index page
		When the user writes <username> username
		And the user writes <password> password
		And the user clicks IniciarSesion button
		Then the wrong password message appears
		
		Examples:
		|   username   |    password     |
		|    PGutCa    |    MarcOriol    |
	
	
	Scenario Outline: Fallo usuario Iniciar Sesion
	
		Given the user is in the index page
		When the user writes <username> username
		And the user writes <password> password
		And the user clicks IniciarSesion button
		Then the wrong password message appears
		
		Examples:
		|   username   |    password     |
		|    PGutCaa   |   MarcOriol3    |