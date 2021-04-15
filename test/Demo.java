public class Demo {
    public boolean isSame(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;

        if (root1 != null && root2 != null && root1.value == root2.value) {
            return isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
        }

        return false;
    }
}
