Feature: Login test

  Scenario: Test if it is possible to login with valid email and password
    Given go to url
    When enter email and password
    Then verify that user logged in successfully