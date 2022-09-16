Feature: Calculator base operations

  Scenario Outline: Division
    Given I open "https://www.google.com/search?q=%калькулятор"
    When I <operand1> <operator> <operand2>
    Then I get <result> as a result

    Examples:
      | operand1 | operator | operand2 | result        |
      | 12       | "/"      | 4.5      | 2.66666666667 |
      | 5.76     | "*"      | 3.29     | 18.9504       |
      | 1000     | "+"      | 0        | 1000          |
      | 1        | "-"      | 2        | -1            |
