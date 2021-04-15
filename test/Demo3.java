public class Demo3 {
    public int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length-1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public int binarySearch2(int[] nums, int target) {
        int l = 0, r = nums.length-1;

        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return nums[l] == target ? l : -1;
    }

    public static void main(String[] args) {
        System.out.println(new Demo3().binarySearch2(new int[] {1,2,2,2,3,5,6,7}, 2));
    }
}
