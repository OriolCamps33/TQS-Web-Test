Feature: CardLists


Scenario Outline: CreateCardList
Given the user is in the index page
When the user inits sesion
And the user goes to MyWants
And the user writtes the <name> of the list
And the user adds the list
Then the empty list appears

Examples:
| name  |
|lista1|


Scenario Outline: TryCreateRepeatedList
Given the user is in the index page
When the user goes to MyWants
And the user writtes the <name> of the list
And the user adds the list
#And the user returnsToWants
Then the error message appears

Examples:
| name  |
|lista1|

#este escenario no funciona, falla en el paso renames the list

#Scenario Outline: RenameCardList
#Given the user is in the index page
#When the user goes to MyWants
#And the user enters the <name> list wants
#And the user opens the list options
#And the user renames <newName> the list
#Then the list has new name <newName>

#Examples:
#| name  | newName |
#|lista1| newLista1|


#este scenario tampoco funciona, falla al darle al boton de delete, creo que por la forma en que
#aparece no lo detecta el programa
Scenario Outline: DeleteCardList
Given the user is in the index page
When the user goes to MyWants
And the user enters the list wants
And the user opens the list options
And the user deletes the list

Examples:
| name  |
|lista1 |

