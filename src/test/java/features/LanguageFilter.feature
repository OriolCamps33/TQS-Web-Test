
Feature: FilterLanguage
Scenario Outline: Filter Language Card

Given the user is in a card page
When the user clicks sidebarFilter
And the user clicks language
And the user marks <language>
And the user aply the filter
#Then the <title> shop appears

Examples:
|  language  |
| language[1]|
| language[2]|
| language[3]|


