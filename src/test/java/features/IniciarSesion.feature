
Feature: IniciarSesion
Scenario Outline: IniciarSesion

Given the user is in the index page
When the user writes <username> username
And the user writes <password> password
And the user clicks IniciarSesion button
Then the index page changes

Examples:
|  username  |  password  |
|   Nark10   |  Marc1010  |



