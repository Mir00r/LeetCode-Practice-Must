### Problem Link -> [Valid Anagram](https://leetcode.com/problems/valid-anagram/description/)

There is a more efficient approach to check if two strings are anagrams, especially when working with only lowercase English letters (a-z). You can use an array of size 26 to count the frequency of each character instead of using `Map<Character, Integer>`. This approach reduces both time and space complexity and is more efficient than using hash maps.

### Optimized Solution:
The idea is to use an array where each index corresponds to a character ('a' = index 0, 'b' = index 1, and so on). You increment the count while processing the first string and decrement it while processing the second string. At the end, all counts should be zero if the two strings are anagrams.

### Explanation:
1. **Time Complexity**: O(n) — where `n` is the length of the strings, since we are traversing both strings once.
2. **Space Complexity**: O(1) — as we use a fixed-size array (26) to store the character counts, which is independent of the input size.
3. **Edge Cases**: Strings of different lengths are not anagrams, so they are handled upfront.

This approach is faster and uses constant space, making it a better solution for checking anagrams when working with a known character set (like lowercase alphabets).
