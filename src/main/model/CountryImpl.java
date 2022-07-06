package main.model;

import main.model.enumeration.ResourceType;
import main.model.staticData.StaticData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryImpl {

    public Country create(String name) {
        Map<Resource, Integer> resourceMap = this.initResourceMap();
        return new Country(name, resourceMap);
    }

    private Map<Resource, Integer> initResourceMap() {
        Map<Resource, Integer> resourceMap = new HashMap<>();
        List<Resource> resourcesList = StaticData.DEFAULT_RESOURCE_LIST;

        resourcesList
                .forEach(resource -> {
                    if (resource.getType() != ResourceType.BASIC) {
                        resourceMap.put(resource, 0);
                    } else {
                        // random the resource from 0 to 20
                        resourceMap.put(resource, (int) (Math.random() * 20));
                    }
                });

        return resourceMap;
    }
}
