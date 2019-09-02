package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roman.pogorelov on 02.09.2019
 */
public class Profiles {
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().sorted(Comparator.comparing(o -> o.getAddress().getStreet())).map(Profile::getAddress).distinct().collect(Collectors.toList());
    }
}
