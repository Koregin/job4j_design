package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        Set<User> tempPrevious = new HashSet<>(previous);
        for (User userCurr : current) {
            boolean newUser = true;
            for (User userPrev : tempPrevious) {
                if (userCurr.equals(userPrev)) {
                    tempPrevious.remove(userPrev);
                    newUser = false;
                    break;
                }
                if (userCurr.getId() == userPrev.getId() && !userCurr.getName().equals(userPrev.getName())) {
                    tempPrevious.remove(userPrev);
                    changed++;
                    newUser = false;
                    break;
                }
            }
            if (newUser) {
                added++;
            }
        }
        return new Info(added, changed, tempPrevious.size());
    }
}
