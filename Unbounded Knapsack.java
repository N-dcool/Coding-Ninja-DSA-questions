/*
Problem Statement

You are given ‘N’ items with certain ‘PROFIT’ and ‘WEIGHT’ and a knapsack with weight capacity ‘W’. 
You need to fill the knapsack with the items in such a way that you get the maximum profit. 
You are allowed to take one item multiple times.

For Example
  Let us say we have 'N' = 3 items and a knapsack of capacity 'W' =  10
  'PROFIT' = { 5, 11, 13 }
  'WEIGHT' = { 2, 4, 6 }

  We can fill the knapsack as:

  1 item of weight 6 and 1 item of weight 4.
  1 item of weight 6 and 2 items of weight 2.
  2 items of weight 4 and 1 item of weight 2.
  5 items of weight 2.

The maximum profit will be from case 3 i.e ‘27’. Therefore maximum profit = 27.

*/

//.         TopDown Memoization (recursive) :

import java.util.* ;
import java.io.*; 
public class Solution {
    static Integer[][] dp;
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.
        dp = new Integer[n+1][w+1];
        return helper(n-1, w, profit, weight);
    }
    
    public static int helper(int i, int w, int[] profit, int[] weight){
        if(i==0)
            return w>=weight[0] ? profit[0]*(w/weight[0]) : 0;
        if(dp[i][w]!=null)
            return dp[i][w];
        
        int notTake = helper(i-1, w, profit, weight);
        int take = -100000;
        if(w >= weight[i])
            take = profit[i] + helper(i, w-weight[i], profit, weight);
        
        return dp[i][w] = Math.max(take, notTake);
    }
}


//.         BottomUp Tabulation (Iterative):

import java.util.* ;
import java.io.*; 
public class Solution {
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.
        int[][] dp = new int[n+1][w+1];
        for(int j=1; j<=w; j++)
            if(j>=weight[0])
                dp[0][j] = profit[0]*(j/weight[0]);
        for(int i=1; i<n; i++){
            for(int j=1; j<=w; j++){
                int notTake = dp[i-1][j];
                int take = -100000;
                if(j >= weight[i])
                    take = profit[i] + dp[i][j-weight[i]];

                dp[i][j] = Math.max(take, notTake);
            }
        }
        
        
        return dp[n-1][w];
    }
}


//.         SpaceOptimisation :(using couple of array ie. prev[] & cur[])

import java.util.* ;
import java.io.*; 
public class Solution {
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.
        int[] prev = new int[w+1];
        for(int j=1; j<=w; j++)
            if(j>=weight[0])
                prev[j] = profit[0]*(j/weight[0]);
        for(int i=1; i<n; i++){
            int[] cur = new int[w+1];
            for(int j=1; j<=w; j++){
                int notTake = prev[j];
                int take = -100000;
                if(j >= weight[i])
                    take = profit[i] + cur[j-weight[i]];

                cur[j] = Math.max(take, notTake);
            }
            
            prev = cur;
        }
        
        
        return prev[w];
    }
}
