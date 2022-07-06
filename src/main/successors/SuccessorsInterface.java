package main.successors;

import main.model.Resource;
import main.model.State;

import java.util.List;
import java.util.Map;

public interface SuccessorsInterface {

    List<State> execute(
            String targetCountryName,
            State currentState,
            List<Resource> resourceList,
            Map<Resource, Map<Resource, Integer>> manufacturingInputManual,
            Map<Resource, Map<Resource, Integer>> manufacturingOutputManual
    ) throws Exception;
}
