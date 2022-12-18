/*
llly, to that of Longest Common Subsequence
here you need to print common subsequence instead of total length;
i have just modified Longest Common Subsequence to just print ans .
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
        int[][] dp = new int[n+1][m+1];
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1)==str2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = 0 + Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
      
      //Main Program: 
        
        StringBuilder sb = new StringBuilder();
        
        int i = n;
        int j = m;
        while(i>0 && j>0){
            if(str1.charAt(i-1)==str2.charAt(j-1)){
                sb.insert(0, str1.charAt(i-1));     //insert in reverse order 😂 StringBuilder is awesome MAN!!!
                i--;
                j--;
            }
            else if(dp[i][j-1] > dp[i-1][j])
                j--;
            else
                i--;
        }
        
        System.out.println(sb.toString());
        return dp[n][m];
    }
}
