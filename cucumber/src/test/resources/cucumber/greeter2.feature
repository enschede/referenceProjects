Feature: Calculator
  As a user
  I want to greet a user
  So that I can send him/her a letter

  Background:
    Given I have a person

  Scenario: Create male salution
    When the person is named "Enschede"
    And the person is a "MALE"
    Then the saluation is "Geachte heer Enschede"
    And the persons sex is "MALE"

  Scenario: Create male salution
    # Hetzelfde als de bovenstaande
    When the person is named "Enschede"
    When the person is a "MALE"
    Then the saluation is "Geachte heer Enschede"
    Then the persons sex is "MALE"

  Scenario: Create female salution
    When the person is named "Kuiper"
    And the person is a "FEMALE"
    Then the saluation is "Geachte mevrouw Kuiper"
    And the persons sex is "FEMALE"
    # But is hetzelfde als And, het klinkt alleen anders voor de gebruiker
    But the persons sex is "FEMALE"
