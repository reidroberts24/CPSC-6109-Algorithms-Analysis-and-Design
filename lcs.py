str1= 'stone'
str2 = 'longest'

def lcs(str1, str2):
    dp = [[0] * (len(str1) +1 ) ]* (len(str2) + 1)
    for i in range(1, len(str2) + 1):
        for j in range(1, len(str1) + 1):
            if str2[i-1] == str1[j - 1]:
                dp[i][j] = 1 + dp[i - 1][j - 1]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
    return dp[len(str2)][len(str1)]
print(lcs(str1, str2))