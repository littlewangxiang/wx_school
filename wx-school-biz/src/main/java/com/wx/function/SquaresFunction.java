package com.wx.function;

import java.util.function.Function;

public class SquaresFunction implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer num) {
        return num * num;
    }
}
