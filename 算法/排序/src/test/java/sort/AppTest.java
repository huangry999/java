/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class AppTest {

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public String serialize(TreeNode root) {
        if(root == null)
            return null;
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.pollFirst();
            if(node != root){
                sb.append(",");
            }
            if(node == null){
                sb.append("null");
            }else{
                sb.append(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null)
            return null;
        Integer[] vs = Arrays.stream(data.split(","))
                .map(s->{
                    if(s.equals("null")){
                        return null;
                    }else{
                        return Integer.valueOf(s);
                    }
                })
                .toArray(Integer[]::new);
        int index = 1;
        TreeNode root = new TreeNode(vs[0]);
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.pollFirst();
            Integer lv = vs[i++];
            Integer rv = vs[i++];
            if(lv != null){
                TreeNode l = new TreeNode(lv);
                node.left = l;
                queue.add(l);
            }
            if(rv != null){
                TreeNode r = new TreeNode(rv);
                node.right = r;
                queue.add(r);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        AppTest test = new AppTest();
        int[] in = new int[]{3,2,1};
        int[] post = new int[]{3,2,1};
        TreeNode root = test.buildTree(in, post);
        System.out.println(root);
    }

    @Test public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}
