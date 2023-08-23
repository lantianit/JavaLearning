import java.util.*;

class Solution {

    static Comparator<Integer> cmp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1,Integer o2) {
            String s1 = o1+""+o2;
            String s2 = o2+""+o1;
            return s1.compareTo(s2);
        }
    };

    public String printMinNumber(int[] nums) {
        String res = "";
        Integer[] list = new Integer[nums.length];
        int n = nums.length;
        for(int i = 0; i < n; i++){
            list[i] = nums[i];
        }
        Arrays.sort(list,cmp);
        return res;

    }
}