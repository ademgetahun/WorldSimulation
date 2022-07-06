package main.reward;

import main.model.State;

public class RewardMeasureImpl {

    private final RewardMeasureInterface rewardMeasureInterface; //strategy;

    public RewardMeasureImpl(RewardMeasureInterface rewardMeasureInterface) {
        this.rewardMeasureInterface = rewardMeasureInterface;
    }

  //  public double executeStrategy(State currentState) {
  //      return this.rewardMeasureInterface.measure(currentState);
    // }
}
