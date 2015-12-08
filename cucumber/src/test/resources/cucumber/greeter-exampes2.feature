Feature: Saluation
  As a user
  I want to greet a user
  So that I can send him/her a letter

  Scenario Outline: Create outline salution
    Given I have a person named "<name>"
    When the person is a "<sex>"
    Then the saluation is "<saluation>"
    And the persons sex is "<sex>"

  Examples: for men
      | name     | sex    | saluation              |
      | Enschede | MALE   | Geachte heer Enschede  |
      | Bos      | MALE   | Geachte heer Bos       |

  Examples: for women
      | name     | sex    | saluation                |
      | Waalkens | FEMALE | Geachte mevrouw Waalkens |
      | Kuiper   | FEMALE | Geachte mevrouw Kuiper   |
