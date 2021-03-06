package main.reward;

import main.model.Country;
import main.model.Operation;
import main.model.Resource;
import main.model.State;
import main.model.enumeration.OperationType;

import java.util.Map;

public class DiscountedRewardImpl implements RewardMeasureInterface{

    private final double GAMA = 0.5;

    @Override
    public double measure(State currentState) {
        Operation currentOperation = currentState.getOperation();
        String mainCountryName = currentOperation.getDestinationCountryName();
        double masterCountryDiscountedReward = getDiscountedRewardScore(currentState, mainCountryName);
        double transferSuccessProbability = 1D;

        if (currentOperation.getType() == OperationType.TRANSFER) {
            String slaveCountry = currentOperation.getOriginCountryName();
            transferSuccessProbability = calculateTransferSuccessProbability(currentState, slaveCountry);
        }

        double finalDiscountedReward = masterCountryDiscountedReward * transferSuccessProbability;
        currentState.setFinalDiscountedReward(finalDiscountedReward);
        return finalDiscountedReward;
    }

    // Trade origin country possibility of accepting the trade
    private double calculateTransferSuccessProbability(State currentState, String targetCountryName) {
        double discountedReward = getDiscountedRewardScore(currentState, targetCountryName);
        return sigmoid(discountedReward);
    }

    private double sigmoid(double x) {
        return (1 / (1 + Math.pow(Math.E, (-1 * x))));
    }

    // Process and get final discounted reward
    private double getDiscountedRewardScore(State currentState, String targetCountryName) {
        double stateQuality = calculateStateQuality(currentState, targetCountryName);
        double undiscountedReward = calculateUndiscountedReward(currentState, stateQuality);
        double discountedReward = calculateDiscountedReward(currentState, undiscountedReward);
        return discountedReward;
    }

    // Current state quality minus initial state quality
    private double calculateDiscountedReward(State currentState, double undiscountedReward) {
        return undiscountedReward * Math.pow(GAMA, currentState.getDepth());
    }

    // Current state quality minus initial state quality
    private double calculateUndiscountedReward(State currentState, double currentStateQuality) {
        return currentStateQuality - currentState.getRoot().getStateQuality();
    }

    //  w_Ri * A_Ri / A_Population
    private double calculateStateQuality(State currentState, String targetCountryName) {
        if (currentState.getStateQuality() != 0D) {
            return currentState.getStateQuality();
        }

        Map<String, Country> countryStates = currentState.getCountryStates();
        Country targetCountry = countryStates.get(targetCountryName);
        Map<Resource, Integer> resourceMap = targetCountry.getResourceMap();
        double totalScore = 0D;
        int population = 0;

        for (Map.Entry<Resource, Integer> resourceEntry : resourceMap.entrySet()) {
            Resource resource = resourceEntry.getKey();
            int amount = resourceEntry.getValue();

            if (resource.getName() == "Population") {
                population = amount;
            }

            totalScore += resource.getWeight() * amount;
        }

        double stateQuality = totalScore / population;
        currentState.setStateQuality(stateQuality);
        return stateQuality;
    }
}
