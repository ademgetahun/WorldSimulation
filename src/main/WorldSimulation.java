package main;

import main.model.Country;
import main.model.CountryImpl;
import main.model.State;
import main.model.StateImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorldSimulation {
    public static void main(String[] args) {

        System.out.println("Hello how are you?!");

        CountryImpl countryFactory = new CountryImpl();
        StateImpl stateFactory = new StateImpl();

        String mainCountryName = "self";
        List<String> countryNameList = new ArrayList<>() {{
            add(mainCountryName);
            add("U.S");
        }};

        List<Country> countryList = countryNameList
                .stream()
                .map(countryFactory::create)
                .collect(Collectors.toList());

        State rootState = stateFactory.createRootState(countryList);
    }
}