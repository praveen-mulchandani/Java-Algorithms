package com.java.algorithms.knapsack;


/**
 * Given w[] weights
 * Given v[] value
 * Given W - size of Knapsack
 * O/p - max value
 *
 * Example
 *  int[] val = new int[]{60, 100, 120};
 *  int[] weights = new int[]{3, 3, 2};
 *  int W = 5;
 *  O/p - 220
 */

public class KnapsackZeroOne {

    private int knapsackUsingRecursion(int[] weights, int[] values, int W, int n) {
        if (n == 0 || W == 0) {
            return 0;
        }
        if (weights[n - 1] <= W) {
            return Math.max(values[n - 1] + knapsackUsingRecursion(weights, values, W - weights[n - 1], n - 1),
                    knapsackUsingRecursion(weights, values, W, n - 1));
        } else {
            return knapsackUsingRecursion(weights, values, W, n - 1);
        }

    }


    /**
     * Using the bottom up approach with Memoization
     */
    private int knapsackUsingMemoization(int[] weights, int[] values, int W, int n, int[][] dp) {
        if (n < 0) {
            return 0;
        }
        if (dp[n][W] != -1) {
            return dp[n][W];
        }
        if (weights[n] <= W) {
            dp[n][W] = Math.max(values[n] + knapsackUsingMemoization(weights, values, W - weights[n], n - 1, dp),
                    knapsackUsingMemoization(weights, values, W, n - 1, dp));
            return dp[n][W];
        } else {
            dp[n][W] = knapsackUsingMemoization(weights, values, W, n - 1, dp);
            return dp[n][W];
        }

    }

    private int knapsackUsingBottomUp(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1; j++) {
                dp[i][j] = 0;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - weights[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        //Using recursion
        KnapsackZeroOne knapsackZeroOne = new KnapsackZeroOne();
        int[] val = new int[]{60, 100, 120};
        int[] weights = new int[]{3, 3, 2};
        int W = 5;
        int n = val.length;
        System.out.println("Knapsack Using Recursion");
        System.out.println(knapsackZeroOne.knapsackUsingRecursion(weights, val, W, n));
        int[][] dp = new int[n + 1][W + 1];
        //Knapsack using memorization
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println("Knapsack Using Memoization");
        System.out.println(knapsackZeroOne.knapsackUsingMemoization(weights, val, W, n - 1, dp));
        System.out.println("Knapsack Using Bottom up");
        System.out.println(knapsackZeroOne.knapsackUsingBottomUp(weights, val, W));
    }

}
