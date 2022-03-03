package com.noseryoung.uek223.domain.utils;

import java.util.Arrays;


/**
 * @author Baeldung
 * @see <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">Levenshtein distance</a>
 * @see <a href="https://www.baeldung.com/java-levenshtein-distance">Baeldung Levenshtein Code</a>
 */

public class LevenshteinDistance {

    public static int calculate(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1]; //TODO: what is dp? -> Naming

        for (int str1Index = 0; str1Index <= str1.length(); str1Index++) {

            for (int str2Index = 0; str2Index <= str2.length(); str2Index++) {

                // if str1Index == 0, the cost is the cost of adding all the characters of str2
                if (str1Index == 0) {
                    dp[str1Index][str2Index] = str2Index;

                // if str2Index == 0, the cost is the cost of adding all the characters of str1
                } else if (str2Index == 0) {
                    dp[str1Index][str2Index] = str1Index;

                // if the characters at str1Index and str2Index are the same, the cost is the cost of the previous cell
                } else {
                    dp[str1Index][str2Index] = min(dp[str1Index - 1][str2Index - 1]
                                    + costOfReplacement(str1.charAt(str1Index - 1), str2.charAt(str2Index - 1)),
                            dp[str1Index - 1][str2Index] + 1,
                            dp[str1Index][str2Index - 1] + 1);
                }

            }
        }

        return dp[str1.length()][str2.length()];
    }


    /**
     *
     * @param a the first character
     * @param b the second character
     * @return 1 if a != b, 0 otherwise
     */
    public static int costOfReplacement(char a, char b) {
        return a == b ? 0 : 1;
    }

    /**
     * Gets the minimum of the provided values
     * @param numbers the values to get the minimum of
     * @return the minimum of the provided values
     */
    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }

    private LevenshteinDistance() {
    }
}
