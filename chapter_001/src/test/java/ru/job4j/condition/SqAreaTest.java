package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class SqAreaTest {
    @Test
    public void square(){
        Assert.assertEquals(1.0, SqArea.square(4,1), 0.1);
        Assert.assertEquals(2.0, SqArea.square(6,2), 0.1);
    }
}
