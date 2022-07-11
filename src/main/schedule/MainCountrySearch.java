package main.schedule;

import main.model.Resource;
import main.model.State;
import main.reward.RewardMeasureImpl;
import main.reward.RewardMeasureInterface;
import main.successors.SuccessorsImpl;
import main.successors.SuccessorsInterface;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class MainCountrySearch implements  ScheduleSearchInterface{

    private final SuccessorsImpl successorsImpl;
    private final RewardMeasureImpl rewardMeasureImpl;
    private final List<Resource> resourceList;
    private final Map<Resource, Map<Resource, Integer>> manufacturingInputManual;
    private final Map<Resource, Map<Resource, Integer>> manufacturingOutputManual;

    public MainCountrySearch(
            SuccessorsInterface successorsInterface,
            RewardMeasureInterface rewardMeasureInterface,
            List<Resource> resourceList,
            Map<Resource, Map<Resource, Integer>> manufacturingInputManual,
            Map<Resource, Map<Resource, Integer>> manufacturingOutputManual) {
        this.successorsImpl = new SuccessorsImpl(successorsInterface);
        this.rewardMeasureImpl = new RewardMeasureImpl(rewardMeasureInterface);
        this.resourceList = resourceList;
        this.manufacturingInputManual = manufacturingInputManual;
        this.manufacturingOutputManual = manufacturingOutputManual;
    }

    @Override
    public State search(State root, int maxDepth, String mainCountryName) throws Exception {
        Queue<State> solutions = new PriorityQueue<>();
        Queue<State> frontier = new PriorityQueue<>() {{
            add(root);
        }};

        while (!frontier.isEmpty()) {
            State state = frontier.poll();
            if (maxDepth <= state.getDepth()) {
                solutions.add(state);
            } else {
                List<State> successors = successorsImpl.executeStrategy(
                        mainCountryName,
                        state,
                        resourceList,
                        manufacturingInputManual,
                        manufacturingOutputManual
                );
                frontier.addAll(successors);

            }
        }

        State solution = solutions.peek(); // peek the highest one

        return solution;
    }
}
