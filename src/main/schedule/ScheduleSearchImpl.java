package main.schedule;

import main.model.State;

public class ScheduleSearchImpl {

    private final  ScheduleSearchInterface scheduleSearchInterface;

    public ScheduleSearchImpl(ScheduleSearchInterface scheduleSearchInterface) {
        this.scheduleSearchInterface = scheduleSearchInterface;
    }
    public State executeStrategy(State root, int depth, String mainCountryName) throws Exception {
        return this.scheduleSearchInterface.search(root, depth, mainCountryName);
    }
}
