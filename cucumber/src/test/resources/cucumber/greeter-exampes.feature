Feature: Calculator
  As a user
  I want to greet a user
  So that I can send him/her a letter

  Scenario Outline: Create outline salution
    Given I have a person named "<name>"
    When the person is a "<sex>"
    Then the saluation is "<saluation>"
    And the persons sex is "<sex>"

  Examples:
      | name     | sex    | saluation              |
      | Enschede | MALE   | Geachte heer Enschede  |
      | Kuiper   | FEMALE | Geachte mevrouw Kuiper |
