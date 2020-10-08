package stepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Response;
import org.junit.jupiter.api.Assertions;
import requestes.WeatherRequester;

public class WeatherStepDefs {
    private String city;
    private String country;
    private Response response;

    @Given ("city is: {string}")
    public void set_city(String city){

        this.city = city;
    }

    @Given("country is: {string}")
    public void set_country(String country){

        this.country = country.toLowerCase();
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.requestWeather(city, country);
    }

    @Then("lon is: {double}")
    public void check_lon(Double lon){
        Assertions.assertEquals(lon, response.getCoord().getLon(),
        "Wrong lon value");
    }
    @Then("lat is: {double}")
    public void check_lat(Double lat){
        Assertions.assertEquals(lat, response.getCoord().getLat(),
                "Wrong lat value");
    }
    @Then("weather ID is:{int}")
    public void check_weather_id(int weatherID){
        Assertions.assertEquals(weatherID, response.getWeather().get(0).getId(),
                "Wrong weather ID");
    }
    @Then("weather mainly is:{string}")
    public void check_main_weather(String mainWeather){
        Assertions.assertEquals(mainWeather, response.getWeather().get(0).getMain(),
                "Wrong main weather");
    }
    @Then("weather description is:{string}")
    public void check_weather_description(String weatherDescription){
        Assertions.assertEquals(weatherDescription, response.getWeather().get(0).getDescription(),
                "Wrong Description");
    }
    @Then("icon name is:{string}")
    public void check_icon(String icon){
        Assertions.assertEquals(icon, response.getWeather().get(0).getIcon(),
                "Wrong Icon");
    }
    @Then("base information is from:{string}")
    public void check_base(String base){
        Assertions.assertEquals(base,response.getBase(),
                "Wrong base");
    }
    @Then("temperature is:{double}")
    public void check_temperature(Double temperature){
        Assertions.assertEquals(temperature, response.getMain().getTemp(),
                "Wrong temperature");
    }
    @Then("pressure is:{int}")
    public void check_pressure(int pressure){
        Assertions.assertEquals(pressure, response.getMain().getPressure(),
                "Wrong preasure");
    }
    @Then("humidity is:{int}")
    public void check_humidity(int humidity){
        Assertions.assertEquals(humidity, response.getMain().getHumidity(),
                "Wrong humidity");
    }
    @Then("minimal temperature is:{double}")
    public void check_min_temperature(Double minTemperature){
        Assertions.assertEquals(minTemperature, response.getMain().getTemp_min(),
                "Wrong min temperature");
    }
    @Then("maximum temperature is:{double}")
    public void check_max_temperature(Double maxTemperature){
        Assertions.assertEquals(maxTemperature, response.getMain().getTemp_max(),
                "Wrong max temperature");
    }
    @Then("visibility is:{int}")
    public void check_visibility(int visibility){
        Assertions.assertEquals(visibility, response.getVisibility(),
                "Wrong visibility");
    }
    @Then("wind speed is:{double}")
    public void check_wind_speed(Double windSpeed){
        Assertions.assertEquals(windSpeed, response.getWind().getSpeed(),
                "Wrong speed");
    }
    @Then("wind degree is:{int}")
    public void check_wind_degree(int windDegree){
        Assertions.assertEquals(windDegree, response.getWind().getDeg(),
                "Wrong degree");
    }
    @Then("all cloudiness is:{int}")
    public void check_all_clouds(int allClouds){
        Assertions.assertEquals(allClouds, response.getClouds().getAll(),
                "Wrong 'all clouds'");
    }
    @Then("Time of data calculation, unix, UTC:{int}")
    public void check_time_of_data(int timeOfData){
        Assertions.assertEquals(timeOfData, response.getDt(),
                "Wrong time of data");
    }
    @Then("system type:{int}")
    public void check_system_type(int systemType){
        Assertions.assertEquals(systemType, response.getSys().getType(),
                "Wrong system type");
    }
    @Then("system ID:{int}")
    public void check_system_id(int systemID){
        Assertions.assertEquals(systemID, response.getSys().getId(),
                "Wrong system ID");
    }
    @Then("system message:{double}")
    public void check_system_message(Double systemMessage){
        Assertions.assertEquals(systemMessage, response.getSys().getMessage(),
                "Wrong message");
    }
    @Then("country is:{string}")
    public void check_country(String countryCheck){
        Assertions.assertEquals(countryCheck, response.getSys().getCountry(),
                "Wrong country");
    }
    @Then("sunrise time:{int}")
    public void check_sunrise_time(int sunriseTime){
        Assertions.assertEquals(sunriseTime, response.getSys().getSunrise(),
                "Wrong sunrise time");
    }
    @Then("sunset time:{int}")
    public void check_sunset_time(int sunsetTime){
        Assertions.assertEquals(sunsetTime, response.getSys().getSunset(),
                "Wrong sunset time");
    }
    @Then("city ID is:{int}")
    public void check_city_id(int cityId){
        Assertions.assertEquals(cityId, response.getId(),
                "Wrong city id");
    }
    @Then("city name is:{string}")
    public void check_city_name(String cityName){
        Assertions.assertEquals(cityName, response.getName(),
                "Wrong city Name");
    }
    @Then("cod is:{int}")
    public void check_cod(int cod){
        Assertions.assertEquals(cod, response.getCod(),
                "Wrong cod");
    }

}
