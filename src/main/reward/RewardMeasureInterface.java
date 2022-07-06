package main.reward;

import main.model.State;

public interface RewardMeasureInterface {

    double measure(State currentState);
}
