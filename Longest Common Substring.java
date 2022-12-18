/*
Problem Statement

You have been given two strings 'STR1' and 'STR2'. You have to find the length of the longest common substring.
A string “s1” is a substring of another string “s2” if “s2” contains the same characters as in “s1”, 
in the same order and in continuous fashion also.

For Example :
If 'STR1' = “abcjklp” and 'STR2' = “acjkp”  
   then the output will be 3.

Explanation: 
       The longest common substring is “cjk” which is of length 3.
-------------------------------------------------------------------       

Sample Input 1:
  2
  abcjklp acjkp
  wasdijkl wsdjkl

Sample Output 1:
  3
  3
--------------------  
Explanation For Sample Output 1:
In test case 1, the longest common substring is "cjk" of length 3.

In test case 2, the longest common substring is "jkl" of length 3.

-------------------------------------------------------------------
Sample Input 2:
  2
  abcy acby
  tyfg cvbnuty
Sample Output 2:
  1
  2
--------------------
Explanation For Sample Output 2:
In test case 1, the longest common substring is "a" of length 1. Other substrings of length 1 are also possible as answers.

In test case 2, the longest common substring is "ty" of length 2.
*/

//Using Tabulation DP

import java.util.* ;
import java.io.*; 
public class Solution {
	public static int lcs(String str1, String str2) {
		// Write your code here.
        int n = str1.length();
        int m = str2.length();
        int ans = 0;
        int[][] dp = new int[n+1][m+1];
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    ans = Math.max(ans, dp[i][j]);
                }
                 
            }
        }
        
        return ans;
	}
}

//SpaceOptimisation : 

import java.util.* ;
import java.io.*; 
public class Solution {
	public static int lcs(String str1, String str2) {
		// Write your code here.
        int n = str1.length();
        int m = str2.length();
        int ans = 0;
        int[] prev = new int[m+1];
        
        
        for(int i=1; i<=n; i++){
            int[] cur = new int[m+1];
            
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    cur[j] = 1 + prev[j-1];
                    ans = Math.max(ans, cur[j]);
                }
            }
            prev = cur;
        }
        
        return ans;
	}
}
