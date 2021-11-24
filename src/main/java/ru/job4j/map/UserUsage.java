package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserUsage {
    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar birthday = new GregorianCalendar(1985, Calendar.FEBRUARY, 24);
        birthday.set(Calendar.HOUR, 10);
        birthday.set(Calendar.MINUTE, 42);
        birthday.set(Calendar.SECOND, 12);
        birthday.set(Calendar.MILLISECOND, 14);
        map.put(new User("Ivan", 1, birthday), new Object());
        map.put(new User("Ivan", 1, birthday), new Object());
        for (User user: map.keySet()) {
            System.out.println(map.get(user));
            System.out.println(user);
            System.out.println(user.hashCode());
        }
        System.out.println("New map:");
        Map<String, String> map2 = new HashMap<>();
        map2.put("Petr", "I am Petr");
        map2.put("Petr", "I am Petr");
        for (String user: map2.keySet()) {
            System.out.println(map2.get(user));
        }
    }
}
