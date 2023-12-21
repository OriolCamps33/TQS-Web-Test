
Feature: SearchCard
Scenario Outline: User Search Card

Given the user is in the page onePiece
When the user writes <name> of the card
And the user clicks search button
And the user clicks <card> he wants
Then the <card> description appears

Examples:
|  name  |              card               |
|  luffy | Monkey.D.Luffy (OP04-090) (V.1) |
|  kaido |      Kaido (OP01-094) (V.1)     |
|  zoro  |  Roronoa Zoro (OP01-025) (V.2)  |


