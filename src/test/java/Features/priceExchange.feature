Feature: Validating Exchange Price API's

  @Price
  Scenario: Verify if rates are being Succesfully coming up or not
    Given I set the base URL as "https://api.exchangeratesapi.io"
    When I make API GET call using URL "/api"
    And I set date as "latest" and GET Exchange Rates
    Then I verify API response status code
