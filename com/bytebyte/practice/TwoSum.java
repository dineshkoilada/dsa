import java.util.ArrayList;

public class TwoSum {
    public static ArrayList<Integer> getTwoSum(ArrayList<Integer> nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        while(left < right) {
            int sum = nums.get(left) + nums.get(right);
            if(sum < target) {
                left++;
            } else if(sum > target) {
                right--;
            } else {
                ArrayList<Integer> result = new ArrayList<>();
                result.add(left);
                result.add(right);
                return result;
            }
        }
        return new ArrayList<>();
    }

    public static void main(String [] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(6);
        int target = 7;
        ArrayList<Integer> result = getTwoSum(nums, target);
        if (!result.isEmpty()) {
            System.out.println("Indices of numbers that sum to " + target + ": " + result);
            System.out.println("Values: " + nums.get(result.get(0)) + ", " + nums.get(result.get(1)));
        } else {
            System.out.println("No pair found that sums to " + target);
        }
    }
}