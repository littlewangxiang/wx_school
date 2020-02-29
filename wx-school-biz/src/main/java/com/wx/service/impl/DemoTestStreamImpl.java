package com.wx.service.impl;

import com.wx.function.EmptyStringPredict;
import com.wx.function.SquaresFunction;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DemoTestStreamImpl {

    public static void main(String[] args) {
        System.out.println("使用 Java 7: ");

        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("列表: " + strings);
        long count = getCountEmptyStringUsingJava8(strings);

        System.out.println("空字符数量为: " + count);
        count = getCountLength3UsingJava8(strings);
//
        System.out.println("字符串长度为 3 的数量为: " + count);
//
        // 删除空字符串
        List<String> filtered = deleteEmptyStringsUsingJava8(strings);
        System.out.println("筛选后的列表: " + filtered);
//
        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava8(strings, ", ");
        System.out.println("合并字符串: " + mergedString);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        // 获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表: " + squaresList);
        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);

        List<Integer> sort = integers.stream().sorted((n1, n2) -> (n1.compareTo(n2))).collect(Collectors.toList());
        System.out.println("排序：" + sort);
//
        System.out.println("列表: " + integers);
        IntSummaryStatistics statistics = integers.stream().mapToInt(n -> n).summaryStatistics();

        System.out.println("列表中最大的数 : " + statistics.getMax());
        System.out.println("列表中最小的数 : " + statistics.getMin());
        System.out.println("所有数之和 : " + statistics.getSum());
        System.out.println("平均数 : " + statistics.getAverage());
        System.out.println("随机数: ");

        // 输出10个随机数
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }

        System.out.println("使用 Java 8: ");
        System.out.println("列表: " + strings);

        count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);
//
//        mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
//        System.out.println("合并字符串: " + mergedString);
//
//        squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
//        System.out.println("Squares List: " + squaresList);
//        System.out.println("列表: " +integers);
//
//        IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();
//
//        System.out.println("列表中最大的数 : " + stats.getMax());
//        System.out.println("列表中最小的数 : " + stats.getMin());
//        System.out.println("所有数之和 : " + stats.getSum());
//        System.out.println("平均数 : " + stats.getAverage());
//        System.out.println("随机数: ");
//
//        random.ints().limit(10).sorted().forEach(System.out::println);
//
//        // 并行处理
//        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
//        System.out.println("空字符串的数量为: " + count);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream().map(new SquaresFunction()).collect(Collectors.toList());
    }

    private static String getMergedStringUsingJava8(List<String> strings, String token) {
        return strings.stream().filter(n -> StringUtils.isNotBlank(n)).collect(Collectors.joining(token));
    }

    private static List<String> deleteEmptyStringsUsingJava8(List<String> strings) {
        return strings.stream().filter(n -> StringUtils.isNotBlank(n)).collect(Collectors.toList());
    }

    private static long getCountLength3UsingJava8(List<String> strings) {
        return strings.stream().filter(n -> (StringUtils.isNotBlank(n) && n.length() == 3)).count();
    }

    private static long getCountEmptyStringUsingJava8(List<String> strings) {
        return strings.stream().filter(new EmptyStringPredict()).count();
    }
}
