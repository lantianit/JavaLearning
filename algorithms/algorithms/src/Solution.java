class Solution {
    public int strStr(String haystack, String needle) {
        int[] ne = new int[haystack.length()];
        for (int i = 1,j = -1; i < haystack.length(); i++) {
            while (j != -1 && haystack.charAt(j)!= haystack.charAt(i)) {
                j = ne[j];
            }
            if (haystack.charAt(j) == haystack.charAt(i)) {
                j++;
            }
            ne[i] = j;
        }
        for (int i = 0; i < haystack.length(); i++) {
            System.out.print(ne[i] + " ");
        }
    }
}