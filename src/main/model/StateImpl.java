package main.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StateImpl {

    public State createRootState(List<Country> countryList) {
        Map<String, Country> countryInitialStates = countryList
                .stream()
                .collect(Collectors
                        .toMap(country -> country.getName(), country -> country)
                );

        return new State(countryInitialStates);
    }
}
