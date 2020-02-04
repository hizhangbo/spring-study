package io.github.hizhangbo.lambda;

import java.util.function.Function;

/**
 * @author Bob
 * @date 2020-02-01 15:08
 */
public class Example1 {

    public static void main(String[] args) {
        Function<Integer, Function<Integer, Integer>> func =
                x -> y -> x + y;
        System.out.println(func.apply(1).apply(2));

        Function<Integer, Function<Integer, Function<Integer, Integer>>> func2 =
                x -> y -> z -> x + y + z;
        System.out.println(func2.apply(1).apply(2).apply(3));

        int[] nums = {1, 2, 3};

        Function f = func2;
        for (int i = 0; i < nums.length; i++) {
            if (f instanceof Function) {
                Object obj = f.apply(nums[i]);
                if (obj instanceof Function) {
                    f = (Function) obj;
                } else {
                    System.out.println("result=" + obj);
                }
            }
        }

        System.out.println(powerFunction().apply(9));
    }

    private static Function<Integer, Integer> powerFunction() {
        return x -> x * x;
    }
}
