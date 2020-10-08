Feature: Testing weather API for one city

  Scenario: Testing weather data for London
    Given city is: "London"
    And country is: "UK"

    When we are requesting weather data

    Then lon is: -0.13
    And lat is: 51.51

    And weather ID is:300
    And weather mainly is:"Drizzle"
    And weather description is:"light intensity drizzle"
    And icon name is:"09d"

    And base information is from:"stations"

    And temperature is:280.32
    And pressure is:1012
    And humidity is:81
    And minimal temperature is:279.15
    And maximum temperature is:281.15

    And visibility is:10000
    And wind speed is:4.1
    And wind degree is:80

    And all cloudiness is:90

    And Time of data calculation, unix, UTC:1485789600
    And system type:1
    And system ID:5091
    And system message:0.0103
    And country is:"GB"
    And sunrise time:1485762037
    And sunset time:1485794875

    And city ID is:2643743
    And city name is:"London"
    And cod is:200