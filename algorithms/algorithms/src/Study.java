class TestException {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        try {
            System.out.println(nums[4]);
            System.out.println("出现异常hou的代码");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally里的代码");
        }
        System.out.println("出现异常后的代码");
    }
}