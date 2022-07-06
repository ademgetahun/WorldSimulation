package main.successors;

import main.model.Resource;
import main.model.State;

import java.util.List;
import java.util.Map;

public class SuccessorsImpl {

    private final SuccessorsInterface successorsInterface;

    public SuccessorsImpl(SuccessorsInterface successorsInterface) {
        this.successorsInterface = successorsInterface;
    }

    public List<State> executeStrategy(
            String targetCountryName,
            State currentState,
            List<Resource> resourceList,
            Map<Resource, Map<Resource, Integer>> manufacturingInputManual,
            Map<Resource, Map<Resource, Integer>> manufacturingOutputManual
    ) throws Exception {
        return this.successorsInterface.execute(
                targetCountryName,
                currentState,
                resourceList,
                manufacturingInputManual,
                manufacturingOutputManual
        );
    }
}
