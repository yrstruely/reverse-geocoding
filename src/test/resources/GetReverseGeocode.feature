Feature: Get Reverse Geocode

  Scenario Outline: Get reverse geocode
    Given I am on the RSRP Look up API
    When I make a get request for "<RP Point>" RP Point with units measured in km
    Then I get a response code of <Status Code>
    And the response contains "lrms_found" field with a value of "<lrms_found value>"
    And the response contains "id_road" field with a value of "<id_road value>"

    Examples:
      | RP Point       | Status Code | lrms_found value | id_road value |
      | 058-0000/0.8-B | 200         | true             | 1231          |

