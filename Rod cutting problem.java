/*
Problem Statement

Given a rod of length ‘N’ units. The rod can be cut into different sizes and each size has a cost associated with it. 
Determine the maximum cost obtained by cutting the rod and selling its pieces.

 Note:
    1. The sizes will range from 1 to ‘N’ and will be integers.

    2. The sum of the pieces cut should be equal to ‘N’.

    3. Consider 1-based indexing.
    
    
Sample Input 1:

5
2 5 7 8 10

Sample Input 2:
8
3 5 8 9 10 17 17 20

Sample Output 1:
12

Sample Output 2:
24

------------------------------------------------------------------------
Test case 1:

All possible partitions are:
1,1,1,1,1           max_cost=(2+2+2+2+2)=10
1,1,1,2             max_cost=(2+2+2+5)=11
1,1,3               max_cost=(2+2+7)=11
1,4                 max_cost=(2+8)=10
5                   max_cost=(10)=10
2,3                 max_cost=(5+7)=12
1,2,2               max _cost=(1+5+5)=12    

Clearly, if we cut the rod into lengths 1,2,2, or 2,3, we get the maximum cost which is 12.


Test case 2:

Possible partitions are:
1,1,1,1,1,1,1,1         max_cost=(3+3+3+3+3+3+3+3)=24
1,1,1,1,1,1,2           max_cost=(3+3+3+3+3+3+5)=23
1,1,1,1,2,2             max_cost=(3+3+3+3+5+5)=22
and so on….

If we cut the rod into 8 pieces of length 1, for each piece 3 adds up to the cost. Hence for 8 pieces, we get 8*3 = 24.
   
*/


//.         TopDown Memoization (Recursive)

public class Solution {
    static Integer[][] dp;
	public static int cutRod(int price[], int n) {
		// Write your code here.
        dp = new Integer[n+1][n+1];
        return helper(n-1, n, price);
	}
    
    public static int helper(int i, int cut, int[] price){
        if(i==0){
           return price[0]*(cut); 
        }
        if(dp[i][cut]!=null)
            return dp[i][cut];
             
        int notTake = helper(i-1, cut, price);
        int take = -100000;
        if(cut>=i+1)
            take = price[i] + helper(i, cut-i-1, price);
        
        return dp[i][cut]=Math.max(take, notTake);
    }
}

//BottomUp Tabulation (Iterative):

public class Solution {
	public static int cutRod(int price[], int n) {
		// Write your code here.
        int[][] dp = new int[n+1][n+1];
        
        //baseCondition:
        for(int cut=1; cut<=n; cut++)
            dp[0][cut] = price[0]*cut;
        
        //tabulation:
        for(int i=1; i<n; i++){
            for(int cut=1; cut<=n; cut++){
                int notTake = dp[i-1][cut];
                int take = -100000;
                if(cut>=i+1)
                    take = price[i] + dp[i][cut-i-1];

                dp[i][cut]=Math.max(take, notTake);
            }
        }
        
        return dp[n-1][n];
	}
}

//SpaceOptimisation (using couple of 1D array prev[] & cur[]):

public class Solution {
	public static int cutRod(int price[], int n) {
		// Write your code here.
        int[] prev = new int[n+1];
        
        //baseCondition:
        for(int cut=1; cut<=n; cut++)
            prev[cut] = price[0]*cut;
        
        //tabulation:
        for(int i=1; i<n; i++){
            int[] cur = new int[n+1];
            
            for(int cut=1; cut<=n; cut++){
                int notTake = prev[cut];
                int take = -100000;
                if(cut>=i+1)
                    take = price[i] + cur[cut-i-1];

                cur[cut]=Math.max(take, notTake);
            }
            prev = cur;
        }
        
        return prev[n];
	}
}

//spaceOptimisation (1D Array Space Optimised Approach):

public class Solution {
	public static int cutRod(int price[], int n) {
		// Write your code here.
        int[] prev = new int[n+1];
        
        //baseCondition:
        for(int cut=1; cut<=n; cut++)
            prev[cut] = price[0]*cut;
        
        //tabulation:
        for(int i=1; i<n; i++){
            for(int cut=1; cut<=n; cut++){
                int notTake = prev[cut];
                int take = -100000;
                if(cut>=i+1)
                    take = price[i] + prev[cut-i-1];

                prev[cut]=Math.max(take, notTake);
            }
        }
        
        return prev[n];
	}
}
