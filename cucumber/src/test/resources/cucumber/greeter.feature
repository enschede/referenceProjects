Feature: Calculator
  As a user
  I want to greet a user
  So that I can send him/her a letter

  Scenario: Create salution
    Given I have a person named "Enschede"
    When the person is a "MALE"
    Then the saluation is "Geachte heer Enschede"

  Scenario: Create salution
    Given I have a person named "Kuiper"
    When the person is a "FEMALE"
    Then the saluation is "Geachte mevrouw Kuiper"
