Feature: Calculator base operations
  Scenario: Division
    Given I open "https://www.google.com/search?q=%калькулятор"
    When I divide 12 by 4.5
    Then I get 2.66666666667 as a result
