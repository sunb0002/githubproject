import java.util.*;
import java.util.stream.Collectors;

class Solution {

  static class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  private static void log(Object msg) {
    System.out.println(msg);
  }

  private static void logListNode(ListNode head) {
    StringBuilder sb = new StringBuilder();
    ListNode cur = head;
    if (cur == null) {
      log("[]");
      return;
    }
    sb.append('[');
    while (cur != null) {
      sb.append(cur.val).append(',');
      cur = cur.next;
    }
    sb.setLength(sb.length() - 1);
    sb.append(']');
    log(sb.toString());
  }

  private static void logArray(int[] arr) {
    log(Arrays.toString(arr));
  }

  private static void logArray(double[] arr) {
    StringBuilder sb = new StringBuilder();
    for (double n : arr) sb.append(n).append(',');
    log(sb.toString());
  }

  private static ListNode makeListNode(int[] arr) {
    ListNode dummy = new ListNode(-999);
    ListNode cur = dummy;
    for (int n : arr) {
      ListNode node = new ListNode(n);
      cur.next = node;
      cur = cur.next;
    }
    return dummy.next;
  }

  private static void swap(int[] arr, int a, int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }

  // ===========
  // ===========
  // ===========
  public static void main(String[] args) {
    Solution sol = new Solution();
    String[] sArray = new String[] { "This", "is", "justification." };

    int[] arr = new int[] { 2, 6, 8 };
    List<String> list = new ArrayList<>();
    for (String s : sArray) list.add(s);
    // ListNode listNode = makeListNode(new int[] { 1, 1, 2, 3, 3, 3, 4, 4 });
    // =================
    // =================

    // log(sol.getSpecialString(6, "abccde"));

    var x = list.stream().distinct().collect(Collectors.toList());

    log(x);
  }
}
