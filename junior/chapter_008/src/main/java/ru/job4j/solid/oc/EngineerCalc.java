package ru.job4j.solid.oc;

import ru.job4j.solid.sr.Act;
import ru.job4j.solid.sr.ConsoleInput;
import ru.job4j.solid.sr.InteractCalc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * На базе задания из занятия SRP расширить калькулятор. Сделать инженерный калькулятор. Добавить вычисления например тригонометрии.
 * <p>
 * Created by roman.pogorelov on 17.09.2019
 */
public class EngineerCalc extends InteractCalc {
    public EngineerCalc(ConsoleInput input, List<Act> acts, List<Act> engineerActs) {
        super(input, acts);
        this.acts = Stream.concat(this.acts.stream(), engineerActs.stream()).collect(Collectors.toList());
    }
}
