package main;

import main.model.Country;
import main.model.CountryImpl;
import main.model.State;
import main.model.StateImpl;
import main.model.staticData.StaticData;
import main.reward.DiscountedRewardImpl;
import main.schedule.MainCountrySearch;
import main.schedule.ScheduleSearchImpl;
import main.successors.PruningStrategy;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorldSimulation {
    public static void main(String[] args) {

        System.out.println("===========================================================");
        System.out.println("World Simulation - Resource Utilization : AI Project 2022");
        System.out.println("===========================================================");

        CountryImpl countryImpl = new CountryImpl();
        StateImpl stateImpl = new StateImpl();

        String mainCountryName = "self";
        List<String> countryNameList = new ArrayList<>() {{
            add(mainCountryName);
            add("U.S");
        }};

        List<Country> countryList = countryNameList
                .stream()
                .map(countryImpl::create)
                .collect(Collectors.toList());

        State rootState = stateImpl.createRootState(countryList);

        ScheduleSearchImpl scheduleSearchImpl = new ScheduleSearchImpl(
                new MainCountrySearch(
                        new PruningStrategy(),
                        new DiscountedRewardImpl(),
                        StaticData.DEFAULT_RESOURCE_LIST,
                        StaticData.MANUFACTURING_INPUT_MANUAL,
                        StaticData.MANUFACTURING_OUTPUT_MANUAL
                )
        );

        Instant start = Instant.now();
        try {
            State finalState = scheduleSearchImpl.executeStrategy(rootState, 4, mainCountryName);
            finalState.printHistorySteps();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Time takes : " + timeElapsed);

    }
}