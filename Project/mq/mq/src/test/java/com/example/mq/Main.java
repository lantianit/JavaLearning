import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<List<Integer>> ans;
    public boolean[] isUsed;

    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        isUsed = new boolean[nums.length];
        List<Integer> temp = new ArrayList<>();
        dfs(0,nums,ans,temp);
        return ans;
    }

    public void dfs(int k, int[] nums, List<List<Integer>> ans, List<Integer> temp) {
        if (k >= nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isUsed[i] == false) {
                temp.add(nums[i]);
                isUsed[i] = true;
                dfs(k+1,nums,ans,temp);
                temp.remove(temp.size()-1);
                isUsed[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.permute(nums);
    }

}