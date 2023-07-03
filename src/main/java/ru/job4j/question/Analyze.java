package ru.job4j.question;

import java.util.*;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int change = 0;
        HashMap<Integer, String> users = new HashMap<>();
        for (User user : previous) {
            users.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (!users.containsKey(user.getId())) {
                add++;
            } else if (!users.get(user.getId()).equals(user.getName())) {
              change++;
            }
            users.put(user.getId(), user.getName());
        }
        return new Info(add, change, users.size() - current.size());
    }
}