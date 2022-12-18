/*
Problem Statement

You have been given two Strings “STR1” and “STR2” of characters. Your task is to find the length of the longest common subsequence.
A String ‘a’ is a subsequence of a String ‘b’ if ‘a’ can be obtained from ‘b’ by deletion of several (possibly, zero or all) characters.
A common subsequence of two Strings is a subsequence that is common to both Strings.

Sample Input 1 :
  2
  abc cadb
  pqr tpuqvr
Sample Output 1 :
  2
  3
  
Explanation For Sample Input 1 :
  For the first test case, the longest common subsequence is “ab” and its length is 2.

  For the second test case, the longest common subsequence is “pqr” and its length is 3.  

Sample Input 2 :
  2
  a b
  a a
Sample Output 2 :
  0
  1
Explanation For Sample Input 2 :
  For the first test case, any non-empty common subsequence doesn’t exist. 

  For the second test case, the longest common subsequence is “a” and its length is 1.    
*/

//TopDown memoization : 

import java.util.* ;
import java.io.*; 
public class Solution 
{
    static Integer[][] dp;
    public static int getLengthOfLCS(String  str1, String  str2)
    {
        // Write your code here.
        int n = str1.length();
        int m = str2.length();
        dp = new Integer[n+1][m+1];
        return helper(n-1, m-1, str1, str2);
    }
    
    public static int helper(int i, int j, String str1, String str2){
        if(i==-1 || j==-1)
            return 0;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        if(str1.charAt(i)==str2.charAt(j))
            return dp[i][j] = 1 + helper(i-1, j-1, str1, str2);
        
        return dp[i][j] = 0 + Math.max(helper(i-1, j, str1, str2), helper(i, j-1, str1, str2));
        
    }
}


//SpaceOptimisation (tabulation) using couple of 1D array:
/*
We have Done Shifting of Index ⭐️
*/

import java.util.* ;
import java.io.*; 
public class Solution 
{
    public static int getLengthOfLCS(String  str1, String  str2)
    {
        // Write your code here.
        int n = str1.length();
        int m = str2.length();
        int[] prev = new int[m+1];
        
        
        for(int i=1; i<=n; i++){
            int[] cur = new int[m+1];
            
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1)==str2.charAt(j-1))
                    cur[j] = 1 + prev[j-1];
                else
                    cur[j] = 0 + Math.max(prev[j], cur[j-1]);
            }
            prev = cur;
        }
        
        return prev[m];
    }
}
