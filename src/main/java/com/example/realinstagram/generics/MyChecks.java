package com.example.realinstagram.generics;

import com.example.realinstagram.models.User;

import java.util.List;

public class MyChecks {
    public static boolean allcheck(String name) {
        return name.length() > 3;
    }

    public static boolean checkForUnical(String name, List<User> users) {
        for (int u = 0; u < users.size(); u++) {
            if (users.get(u).getLogin().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }


}
