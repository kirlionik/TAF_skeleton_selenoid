Feature: Search Test
  As a user I should be able submit search request

  @severity=blocker @tmsLink=Test-Case-2 @UserStoryId=Test-2 @issue=ebay-1006
  Scenario Outline: Search Checking for unauthorized user
    Given I open home page
    When I perform search by request "<request>"
    Then URL of product search page should be valid
    Examples:
      | request |
      | Android |
      | Iphone  |

  @severity=blocker @tmsLink=Test-Case-1 @UserStoryId=Test-401 @issue=ebay-1006
  Scenario: Common Elements
    Given I open home page
    And I change language of site to English
    When I perform search by request "Syma"
    Then Check the number of snippets is "50"
    And Check that Button 'Action' is present
    And Check that Button 'Buy It Now' is present
