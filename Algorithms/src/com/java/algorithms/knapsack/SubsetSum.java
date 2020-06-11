package com.java.algorithms.knapsack;

import java.util.Arrays;

/**
 * Given an array and a sum find
 * the subset which equals the sum value
 * Example
 * Given
 * Input :  int[] A = new int[]{1, 2, 3, 4, 5};
 * int sum = 5;
 * Output : true because subset with sum 5 exists - {1,4} , {2,3} and {5}
 */
public class SubsetSum {

    private boolean subsetSumUsingRecursion(int[] a, int sum, int n) {
        if (sum == 0) {
            return true;
        }
        if (n == -1) {
            return false;
        }
        if (a[n] <= sum) {
            return subsetSumUsingRecursion(a, sum - a[n], n - 1) || subsetSumUsingRecursion(a, sum, n - 1);
        } else {
            return subsetSumUsingRecursion(a, sum, n - 1);
        }
    }

    private int subSetSumUsingMemoization(int[] a, int sum, int n, int[][] t) {
        if (n < 0) {
            if (sum == 0) {
                return 1;
            }
            return 2;
        }
        if (t[n][sum] != -1) {
            return t[n][sum];
        }

        if (a[n] <= sum) {
            t[n][sum] = Math.min(subSetSumUsingMemoization(a, sum - a[n], n - 1, t),
                    subSetSumUsingMemoization(a, sum, n - 1, t));
        } else {
            t[n][sum] = subSetSumUsingMemoization(a, sum, n - 1, t);
        }
        return t[n][sum];
    }

    private boolean subsetSumUsingBottomUp(int[] input, int sum) {
        int n = input.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        //Step 1 : Initialize
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0) {
                    dp[i][j] = false;
                }
                if (j == 0) {
                    dp[i][j] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (input[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - input[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }


    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 4, 5};
        int sum = 5;
        SubsetSum subsetSum = new SubsetSum();
        System.out.println("Subset sum using recursion");
        System.out.println(subsetSum.subsetSumUsingRecursion(A, sum, A.length - 1));
        System.out.println("Subset sum using memorization");
        //Step 1 : initialization
        int[][] t = new int[A.length][sum + 1];
        for (int[] val : t) {
            Arrays.fill(val, -1);
        }
        System.out.println(subsetSum.subSetSumUsingMemoization(A, sum, A.length - 1, t));
        System.out.println("Subset sum using Bottom up");
        System.out.println(subsetSum.subsetSumUsingBottomUp(A, sum));

    }


}

