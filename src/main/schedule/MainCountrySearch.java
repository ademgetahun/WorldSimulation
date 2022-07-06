package main.schedule;

import main.model.Resource;
import main.model.State;
import main.reward.RewardMeasureImpl;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class MainCountrySearch implements  ScheduleSearchInterface{

    //private final SuccessorsGeneratorContext successorsGeneratorContext;
    private final RewardMeasureImpl rewardMeasureImpl;
    private final List<Resource> resourceList;
    private final Map<Resource, Map<Resource, Integer>> manufacturingInputManual;
    private final Map<Resource, Map<Resource, Integer>> manufacturingOutputManual;

    public MainCountrySearch(
            RewardMeasureImpl rewardMeasureImpl, List<Resource> resourceList,
            Map<Resource, Map<Resource, Integer>> manufacturingInputManual,
            Map<Resource, Map<Resource, Integer>> manufacturingOutputManual) {
        this.rewardMeasureImpl = rewardMeasureImpl;
        this.resourceList = resourceList;
        this.manufacturingInputManual = manufacturingInputManual;
        this.manufacturingOutputManual = manufacturingOutputManual;
    }

    @Override
    public State search(State root, int maxDepth, String masterCountryName) throws Exception {
        Queue<State> solutions = new PriorityQueue<>();
        Queue<State> frontier = new PriorityQueue<>() {{
            add(root);
        }};

        while (!frontier.isEmpty()) {
            State state = frontier.poll();
            if (maxDepth <= state.getDepth()) {
                solutions.add(state);
            } else {
             /*   List<State> successors = successorsGeneratorContext.executeStrategy(
                        masterCountryName,
                        state,
                        resourceList,
                        manufacturingInputManual,
                        manufacturingOutputManual
                );
                frontier.addAll(successors);

              */
            }
        }

        State solution = solutions.peek();

        return solution;
    }
}
