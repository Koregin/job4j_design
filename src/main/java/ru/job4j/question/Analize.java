package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        Map<Integer, String> mapPrevious =  previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> mapCurrent =  current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (Integer id : mapCurrent.keySet()) {
            if (mapPrevious.containsKey(id)) {
                if (!mapPrevious.get(id).equals(mapCurrent.get(id))) {
                    changed++;
                }
                mapPrevious.remove(id);
            } else {
                added++;
            }
        }
        return new Info(added, changed, mapPrevious.size());
    }
}
