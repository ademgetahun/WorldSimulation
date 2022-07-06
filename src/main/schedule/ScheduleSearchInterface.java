package main.schedule;

import main.model.State;

public interface ScheduleSearchInterface {

    State search(State root, int maxDepth, String masterCountryName) throws Exception;
}
