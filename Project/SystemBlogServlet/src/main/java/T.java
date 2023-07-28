/**
 * @Author zh
 * @Date 2023/7/23 15:11
 * @PackageName:PACKAGE_NAME
 * @ClassName: T
 * @Description: TODO
 * @Version 1.0
 */
public class T {


}
class Solution {
    public int getUglyNumber(int n) {
        int[] ans = new int[n];
        int i = 0,j = 0,k = 0;
        ans[0] = 1;
        int idx = 0;
        for(int q = 0; q < n; q++){
                int t = Math.min(Math.min(ans[i]*2,ans[j]*3),ans[k]*5);
                ans[++idx] = t;
                if(ans[i]*2==t) i++;
                if(ans[j]*3==t) j++;
                if(ans[k]*5==t) k++;
        }
        return ans[n-1];
    }
}