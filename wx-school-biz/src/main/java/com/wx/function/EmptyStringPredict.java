package com.wx.function;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public class EmptyStringPredict implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return StringUtils.isBlank(s);
    }
}
