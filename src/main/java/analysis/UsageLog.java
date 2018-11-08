package analysis;

import java.util.HashMap;
import java.util.Map;

public class UsageLog {

    public UsageLog(){}

    private Map<String, Integer> usageMap = new HashMap<>();

    public void addStringUsage(String word) {
        if(usageMap.containsKey(word)){
            usageMap.put(word, usageMap.get(word) + 1);
        } else {
            usageMap.put(word, 1);
        }
    }

    public Map<String, Integer> getUsageMap() {
        return usageMap;
    }
}
