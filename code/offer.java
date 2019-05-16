/*
剑指offer
        1.	二维数组查找
        在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

        思路：

public class Solution {
    public boolean Find(int target, int [][] array) {
        if(array.length == 0 || array[0].length == 0  )
            return false;
        int rows = array.length-1;
        int cols = array[0].length-1;
        int i = 0;
        int j = cols;
        while(i <= rows && j >= 0)
        {
            if(array[i][j] < target)
            {
                i++;
            }else if(array[i][j] > target)
            {
                j--;
            }else{
                return true;
            }
        }
        return false;
    }
}

2.	替换空格
        •	剑指offer的思路.就是一个空格变成了%20，也就是说每有一个空格，长度要增加2，所以首先先计算有多少个空格，这样长度就能增加多少，得到增加后的长度Length。
        •	然后new一个Length长度的字符数组，从尾到头开始复制原来的数组，如果复制过程中，如果字符不是空格，直接复制，如果字符是空格，那么需要把这个空格变成%20（这个复制过程就是把新建的数组比如现在到了 K这个位置，然后就是K，K-1，K-2这三个位置依次变成0,2，%这三个字符，因为是从后往前复制的所以是倒序），重复这个过程就行。
        如果相等，返回true;

public class Solution {
    //方法一
    /*public String replaceSpace(StringBuffer str) {
    	return str.toString().replaceAll(" " , "%20");
    }*/
/*
    //方法二
    public String replaceSpace(StringBuffer str) {
        String str1 = str.toString();
        if(str.equals(""))
            return str1;
        char[] ch = str1.toCharArray();
        int len = str1.length();
        int count = 0;
        for(int i=0;i<len;i++){
            if(ch[i] == ' '){
                count++;
            }
        }
        char[] array = new char[len+count*2];
        int i = len-1;
        int j = array.length-1;
        while(i>=0 && j>=0){
            if(ch[i]!=' '){
                array[j--] = ch[i--];
            }else{
                array[j--] = '0';
                array[j--]  = '2';
                array[j--]  = '%';
                i--;
            }
        }
        return new String(array);
    }

}


3.	从尾到头打印链表
        输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
        •	剑指offer的思路，递归的思路，只要链表不为空，一直往后进行遍历，然后直到到达链表的末尾，就开始用数组保存下来结果。

        /**
         *    public class ListNode {
         *        int val;
         *        ListNode next = null;
         *
         *        ListNode(int val) {
         *            this.val = val;
         *        }
         *    }
         *
         */
        import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(listNode == null)
            return arrayList;
        printListFromTailToHead(listNode,arrayList);
        return arrayList;
    }

    public void printListFromTailToHead(ListNode listNode,ArrayList<Integer> arrayList){
        if(listNode.next != null){
            printListFromTailToHead(listNode.next,arrayList);
        }
        arrayList.add(listNode.val);
    }
}
4.	重建二叉树
        输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre>endPre||startIn>endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
                break;
            }

        return root;
    }
}


5.	用两个栈来实现队列
        用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
        •	剑指offer的思路，用两个栈来实现队列，栈1的话用来入队，队列每进入一个元素就入栈1，栈2的话用来出队，队列如果要出队，首先判断栈2是不是空，是空，就把栈1弹出来进入栈2（因为栈是先入后出，两次先入后出就是先入先出了，负负得正嘛~），然后从栈2开始弹，如果栈2不为空，直接从栈2开始弹

        import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);

    }

    public int pop() {
        if(stack2.isEmpty())
        {
            while(!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }

        }
        if(stack2.isEmpty()){
            return -1;
        }
        return stack2.pop();
    }

}

6.	旋转数组的最小值
        把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

        import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0)
            return 0;
        if(array[0] < array[array.length-1])
            return array[0];
        int start = 0;
        int end = array.length - 1;

        while(start+1 != end)
        {
            int mid = (start + end) / 2;
            if(array[mid]>array[end]){
                start = mid;
            }else if(array[mid]<array[end]){
                end = mid;
            }else{
                start++;
            }
        }
        return array[end];
    }
}

7.	斐波那契数列
        大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
        n<=39

public class Solution {
    public int Fibonacci(int n) {
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        int i = 2;
        int a = 0,b = 1,sum = 0;
        while(i <= n)
        {
            sum = a + b;
            a = b;
            b = sum;
            i++;
        }
        return sum;
    }
}
8.	青蛙跳台阶
        一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
public class Solution {
    public int JumpFloor(int target) {
        if(target == 1)
            return 1;
        if(target == 2)
            return 2;
        int i = 3;
        int a = 1,b = 2,sum = 0;
        while(i <= target)
        {
            sum = a + b;
            a = b;
            b = sum;
            i++;
        }
        return sum;

    }
}
9.	变态跳台阶
        一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
public class Solution {
    public int JumpFloorII(int target) {
        return (int)Math.pow(2,target-1);
    }
}

10.	矩形覆盖
        我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
public class Solution {
    public int RectCover(int target) {
        if(target == 1)
            return 1;
        if(target == 2)
            return 2;
        int i = 3;
        int a = 1,b = 2,sum = 0;
        while(i <= target)
        {
            sum = a + b;
            a = b;
            b = sum;
            i++;
        }
        return sum;

    }
}

11.	牛客网二进制中1的个数
        输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。

public class Solution {
    public int NumberOf1(int n) {
        if(n == 0)
            return 0;

        int count = 0;
        while((n & (n-1)) != 0)
        {
            n = n & (n-1);
            count++;
        }
        count++;
        return count;

    }
}

12.	牛客网数值的整数次方
        给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。

public class Solution {
    public double Power(double base, int exponent) {
        double  result=1;
        for(int i=0;i<Math.abs(exponent);i++){
            result*=base;
        }
        if(exponent<0){
            result=1/result;
        }
        return result;
    }
}

13.	调整数组顺序使奇数位于偶数前面
        输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

        import java.util.ArrayList;
public class Solution {
    public void reOrderArray(int [] array) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        for(int i=0;i<array.length;i++)
        {
            if(array[i]%2==1)
                arr1.add(array[i]);
            else
                arr2.add(array[i]);
        }
        for(int i=0;i<arr1.size();i++)
        {
            array[i] = arr1.get(i);
        }
        for(int i=0;i<arr2.size();i++)
        {
            array[i+arr1.size()] = arr2.get(i);
        }
    }
}

14.	链表中倒数第k个结点
        输入一个链表，输出该链表中倒数第k个结点。

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        if(head == null)
            return head;
        if(head.next == null)
            return head;
        int length = 0;
        ListNode temp = head;
        while(temp!=null)
        {
            length++;
            temp = temp.next;
        }
        if(k<=0||k>length)
        {
            return null;
        }
        ListNode before = head;
        ListNode after = head;
        for(int i=0;i<k-1;i++)
        {
            before = before.next;
        }
        while(before.next!=null)
        {
            before = before.next;
            after = after.next;
        }
        return after;
    }
}

15.	反转链表
        输入一个链表，反转链表后，输出新链表的表头。

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode ReverseList(ListNode head) {
        if(head == null)
            return head;
        if(head.next == null)
            return head;
        ListNode p = head.next;
        ListNode pre = head;
        pre.next = null;
        ListNode next = p.next;
        while(p!=null)
        {
            p.next = pre;
            pre = p;
            p = next;
            if(p!=null)
                next =  p.next;
        }
        head = pre;
        return head;
    }
}
16.	合并两个有序链表
        输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode resultList = new ListNode(0);
        ListNode tempList = resultList;
        while(list1!=null && list2!=null)
        {
            if(list1.val < list2.val)
            {
                tempList.next = list1;
                list1 = list1.next;
            }else{
                tempList.next = list2;
                list2 = list2.next;
            }
            tempList = tempList.next;
        }
        if(list1 == null)
        {
            tempList.next = list2;
        }else{
            tempList.next = list1;
        }
        return resultList.next;
    }
}

17.	树的子结构
        输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
public class Solution {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root2 == null)//当Tree1和Tree2都不为零的时候，才进行比较。否则直接返回false
            return false;
        if(root1 == null)
            return false;
        boolean flag = false;
        if(root1.val == root2.val)//以这个根节点为为起点判断是否包含Tree2
        {
            flag = doHasSubtree(root1,root2);
        }
        if(flag)
            return flag;
        if(!flag)
        {//如果找不到，那么就再去root的左儿子当作起点，去判断时候包含Tree2
            flag = HasSubtree(root1.left,root2);
            if(flag)
                return true;
            else{//如果还找不到，那么就再去root的右儿子当作起点，去判断时候包含Tree2
                flag = HasSubtree(root1.right,root2);
                if(flag)
                    return true;
            }
        }
        return false;
    }
    private boolean doHasSubtree(TreeNode root1,TreeNode root2)
    {
        if(root2 == null)//如果Tree2已经遍历完了都能对应的上，返回true
            return true;
        if(root1 == null)//如果Tree2还没有遍历完，Tree1却遍历完了。返回false
            return false;
        if(root1.val != root2.val)//如果其中有一个点没有对应上，返回false
            return false;
        //如果根节点对应的上，那么就分别去子节点里面匹配
        return doHasSubtree(root1.left,root2.left) && doHasSubtree(root1.right,root2.right);
    }
}
18.	二叉树的镜像
        操作给定的二叉树，将其变换为源二叉树的镜像。
/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Solution {
    public void Mirror(TreeNode root) {
        if(root == null)
            return;
        if(root.left == null && root.right == null)
            return;
        TreeNode tempNode = root.right;
        root.right = root.left;
        root.left = tempNode;
        Mirror(root.left);
        Mirror(root.right);

    }
}
19.	顺时针打印矩阵
        输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.

        import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> resultList = new ArrayList<>();
        int cols = matrix[0].length;
        int rows = matrix.length;
        int left=0,top=0,bottom=rows-1,right=cols-1;
        int count = 0;//计数，count如果达到数组的全部个数，那么结束。
        while(count < cols*rows)
        {
            for(int i=left;i<=right;i++)//从左往右进行遍历，第一层
            {//left是目前最左边的那个边界，right是目前最右边的边界
                resultList.add(matrix[top][i]);
                count++;
                if(count >= cols*rows)
                    return resultList;
            }
            top++;//遍历完目前的最顶层，那么top就到下一层
            for(int i=top;i<=bottom;i++)
            {//从上往下进行遍历，top是目前最上的边界，bottom是目前最下的边界
                resultList.add(matrix[i][right]);
                count++;
                if(count >= cols*rows)
                    return resultList;
            }
            right--;//遍历完最右边的边界，那么right就减一，到下一个最右边边界
            for(int i=right;i>=left;i--)
            {//从右到左，和上面同理
                resultList.add(matrix[bottom][i]);
                count++;
                if(count >= cols*rows)
                    return resultList;
            }
            bottom--;
            for(int i=bottom;i>=top;i--)
            {//从下到上，和上面同理。
                resultList.add(matrix[i][left]);
                count++;
                if(count >= cols*rows)
                    return resultList;
            }
            left++;
        }
        return resultList;
    }
}

20.	包含min函数的栈
        定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
        思路：
        •	利用一个辅助栈来存放最小值
        •	栈  3，4，2，5，1
        •	辅助栈 3，3，2，2，1
        •	每入栈一次，就与辅助栈顶比较大小，如果小就入栈，如果大就入栈当前的辅助栈顶
        当出栈时，辅助栈也要出栈
        •	栈3 辅助栈入3，
        •	栈4，辅助栈栈顶比4小入3，
        •	栈入2，辅助栈栈顶是3比2大所以入2，每次都是把栈顶与入栈的值比较，入那个最小的，这样栈顶一直最小。

        import java.util.Stack;
public class Solution {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public void push(int node) {
        stack1.push(node);
        if(stack2.empty())
        {
            stack2.push(node);
        }else{
            if(node <= (int)stack2.peek()) //这个if/else是比较栈顶与入栈的值，哪个小入哪个
                stack2.push(node);
            else{
                stack2.push(stack2.peek());
            }
        }
    }
    public void pop() {
        stack2.pop();//出栈是都出栈的
        stack1.pop();
    }

    public int top() {
        return (int)stack1.peek();
    }

    public int min() {
        return (int)stack2.peek();//栈2是辅助栈，每次都是最小值
    }
}

21.	栈的压入、弹出序列
        输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

        思路
        •	一个弹出序列，一个压栈序列，弹出序列中的数，比如第一个弹出的数，肯定是在压栈过程中弹出来的，所以在这里新建一个栈stack,如果压栈序列中要压入的数和弹出序列当前的数不一样（说明没找到），那么压栈序列继续压，直到压栈序列中找到了和弹出序列当前下标值相等的数，那么弹出序列的下标值就+1,。
        •	结束条件就是弹出序列的下标值可以到达序列的末尾。

        import java.util.*;
public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length != popA.length)
            return false;
        Stack<Integer> stack1 = new Stack<>();//栈记录压栈
        int j = 1;
        stack1.push(pushA[0]);//栈中先压入push压栈序列的第一个数
        for(int i=0;i<popA.length;i++)
        {
            while(j < pushA.length && stack1.peek() != popA[i])
            {//如果栈顶的数和弹出序列不一样，就一直压栈，因为必须是从栈顶弹出的！
                stack1.push(pushA[j]);
                j++;
            }
            if(j >= pushA.length && stack1.peek() != popA[i])
                return false;//如果j已经到达压栈序列的末尾，但是栈顶的数还是和弹出序列当前的数不一致
            //说明没有这个序列
            stack1.pop();
        }
        return true;
    }
}
22.	从上往下打印出二叉树
        从上往下打印出二叉树的每个节点，同层节点从左至右打印。
        import java.util.*;
/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> resultList = new ArrayList<>();
        if(root == null)
            return resultList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(queue.size() != 0)//队列不为空一直进行
        {
            TreeNode tempRoot = queue.poll();//出队
            if(tempRoot.left != null)//左子节点不为空，左子节点入队
                queue.offer(tempRoot.left);
            if(tempRoot.right != null)//右子节点不为空，右子节点入队
                queue.offer(tempRoot.right);
            resultList.add(tempRoot.val);//把出队的节点的值保留下来
        }
        return resultList;
    }
}

23.	二叉搜索树的后序遍历序列
        输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。

        思路：采用分治法的思想，找到根结点、左子树的序列、右子树的序列，分别判断左右子序列是否为二叉树的后序序列。

        由题意可得：
        1. 后序遍历序列的最后一个元素为二叉树的根节点；
        2. 二叉搜索树左子树上所有的结点均小于根结点、右子树所有的结点均大于根结点。

        算法步骤如下：
        1. 找到根结点；
        2. 遍历序列，找到第一个大于等于根结点的元素i，则i左侧为左子树、i右侧为右子树；
        3. 我们已经知道i左侧所有元素均小于根结点，那么再依次遍历右侧，看是否所有元素均大于根结点；若出现小于根结点的元素，则直接返回false；若右侧全都大于根结点，则：
        4. 分别递归判断左/右子序列是否为后序序列；


public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0)
            return false;
        return verify(sequence,0,sequence.length-1);
    }
    public boolean verify(int [] sequence,int begin,int end)
    {
        if(begin == end)
            return true;
        int rootValue = sequence[end];
        int leftBegin = -1;//左子树的左边界
        int leftEnd = -1;//左子树的右边界
        int rightBegin = -1;//右子树的左边界
        int rightEnd = -1;//右子树的右边界
        if(sequence[begin] < rootValue)// 说明存在左子树，二叉搜索树的性质
            leftBegin = begin;//记录左子树的左边界
        for(int i=begin;i<end;i++)
        {
            if(sequence[i] < rootValue)//如果比根结点的值小，说明就是左子树
                leftEnd = i;//记录下来左子树的右边界，只要比根结点小，就进行记录
            else{
                if(rightBegin == -1) //记录右子树的左边界，这个条件判断只会记录一次。
                    rightBegin = i;
                rightEnd = i;//记录右子树的右边界
            }
        }
        if(rightBegin < leftEnd && rightBegin != -1)
            return false;//如果左子树的右边界 大于 右子树的左边界 就出问题了！false
        return verify(sequence,leftBegin,leftEnd) && verify(sequence,rightBegin,rightEnd);
//否则递归往下去判断
    }
}
24.	二叉树中和为某一值的路径
        输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)

public class Solution {
    ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {

        ArrayList<Integer> list = new ArrayList<>();
        FindPath(root,target,0,list);
        return resultList;
    }
    public void FindPath(TreeNode root,int target,int sum,ArrayList<Integer> list)
    {
        if(root == null)
            return;
        sum += root.val; //sum不是引用，所以sum在每一层的递归中都是不同的值，记录当前的节点和
        list.add(root.val);
        if(sum == target && root.left == null && root.right == null)
        {//找到这样的路径了~
            resultList.add(new ArrayList<Integer>(list));//存入结果数组
            list.remove(list.size()-1);//找到以后还要接着找啊，所以先把当前最后的叶子节点删除
            return;
        }
        FindPath(root.left,target,sum,list);//左右子树递归进去去找
        FindPath(root.right,target,sum,list);
        list.remove(list.size()-1);//这里左右子树都找完了，回到了找完的左右子树的父节点
        //由于父节点的左右子树找完了，所以父节点这里也没有用了，把父节点删除
    }
}

25.	复杂链表的复制
        输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

        方法一：使用哈希表，额外空间O(n)
        可以将链表中的结点都复制一份，用一个哈希表来保存， key是源结点， value就是副本结点，然后遍历 key取出每个对应的 value将副本结点的 next指针和 random指针设置好：


public RandomListNode Clone(RandomListNode pHead){
        if(pHead == null){
        return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap();
        RandomListNode p = pHead;
        //copy
        while(p != null){
        RandomListNode cp = new RandomListNode(p.label);
        map.put(p, cp);
        p = p.next;
        }
        //link
        p = pHead;
        while(p != null){
        RandomListNode cp = map.get(p);
        cp.next = (p.next == null) ? null : map.get(p.next);
        cp.random = (p.random == null) ? null : map.get(p.random);
        p = p.next;
        }

        return map.get(pHead);
        }

        26.	二叉搜索树与双向链表
        输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

        思路
        •	先中序遍历二叉搜索树，这样二叉搜索树就按照val值的大小从小到大排好序了，存放在数组中
        •	然后要转换为双向链表，由于数组中的存放的树的节点已经按照键值从小到大排好序了，那么就对于每个节点的左子树指向数组的上一个节点，右子树指向数组的下一个节点，这样就完成了变成双向链表。

        import java.util.*;

public class Solution {
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null || (pRootOfTree.left == null && pRootOfTree.right == null))
            return pRootOfTree;
        ArrayList<TreeNode> nodeList = new ArrayList<>();
        BuildArrayList(pRootOfTree,nodeList);//这个函数执行后，数组中每个元素按照大小前后排序
        for(int i=0;i<nodeList.size();i++)
        {
            if(i == 0)
            {//数组的第一个节点处理，只有右子树指向下一个节点
                nodeList.get(0).right = nodeList.get(1);
            }
            else if(i == nodeList.size()-1)
            {//数组的最后一个节点，只有左子树指向前一个节点
                nodeList.get(i).left = nodeList.get(i-1);
            }
            else{//数组中的中间节点，左子树指向上一个节点，右子树指向数组的下一个节点
                nodeList.get(i).left = nodeList.get(i-1);
                nodeList.get(i).right = nodeList.get(i+1);
            }
        }
        return nodeList.get(0);
    }
    public void BuildArrayList(TreeNode root,ArrayList<TreeNode> nodeList)
    {//二叉搜索的中序遍历，并把每个节点存入数组中
        if(root == null)
            return;
        if(root.left != null)//左子树
            BuildArrayList(root.left,nodeList);
        if(root != null)//根节点
            nodeList.add(root);
        if(root.right != null)//右子树
            BuildArrayList(root.right,nodeList);
    }
}
27.	字符串的排列
        输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
        输入描述:
        输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
        import java.util.*;
public class Solution {
    ArrayList<String> result = new ArrayList<>();
    TreeSet<String> set = new TreeSet<>();
    public ArrayList<String> Permutation(String str) {
        char[] strArray = str.toCharArray();
        Permutation(strArray,0);
        while(set.isEmpty() != true)
        {
            result.add(set.pollFirst());
        }
        return result;
    }

    public void Permutation(char[] strArray, int index)
    {
        if(index == strArray.length - 1)
            set.add(String.valueOf(strArray));
        else{
            for(int i=index;i<strArray.length;i++)
            {
                swap(strArray,index,i);
                Permutation(strArray,index+1);
                swap(strArray,index,i);
            }
        }
    }
    public void swap(char [] strArray,int i,int j)
    {
        char ch = strArray[i];
        strArray[i] = strArray[j];
        strArray[j] = ch;
    }
}

28.	数组中出现次数超过一半的数字
        数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length == 0)
            return 0;
        int count = 1;
        int number = array[0];
        for(int i=1;i<array.length;i++)
        {
            if(array[i] == number)
            {//如果与number相等，那么count++，可能是超过一半的那个数
                count++;
            }else{
                count--;//如果与number不相等，count就减一
                if(count == 0)
                {//如果count等于0了，说明这个数在这里出现次数已经被抵消了
                    count = 1;//重新记录count为1
                    number = array[i];//number记录当前这个数
                }
            }
        }
        if(count > 0)
        {//如果count大于0说明有可能存在这样的数，是出现次数大于数组的一半的
            //还有一种可能是最后刚好一个数连续出现了2次，导致count>0
            count = 0;
            for(int i=0;i<array.length;i++)
            {//去遍历数组，计数这个number到底出现了几次
                if(number == array[i])
                    count++;
            }
            if(count > array.length/2)
                return number;//出现超过一半
        }
        return 0;
    }
}
29.	最小的K个数
        输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,
        1)	题目详解
        思路
        •	使用一个大小为K的最大堆，然后堆里面最大的数是堆顶，然后每次比较堆顶的数和数组中的数，如果堆顶的数比数组中的数A大，那么就把堆顶的数弹出来，把数组中的数A进堆，这样子到最后堆里面的堆顶始终是比外面的数小，而堆里的其他数是小于堆顶的数（最大堆的性质），所以堆中的数就是最小的k个数
        import java.util.*;
public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> resultList = new ArrayList<>();
        if(k > input.length || k<=0)
            return resultList;
        //使用优先级队列建堆，优先级队列默认是最小堆，所以要重写比较器
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer o1,Integer o2){
                return o2.compareTo(o1);
            }
        }
        );
        for(int i=0;i<input.length;i++)
        {
            if(i < k){//如果没有达到k个数，那么直接入堆
                maxHeap.add(input[i]);
            }else{
                if(maxHeap.peek() > input[i])
                {//堆顶的数比数组当前的数大，那么就堆顶出堆
                    maxHeap.poll();
                    maxHeap.add(input[i]);//把当前数加入堆中
                }
            }
        }
        while(maxHeap.isEmpty() != true)
            resultList.add(maxHeap.poll());//把堆中的数出堆添加到结果数组中
        return resultList;
    }
}

30.	连续子数组的最大和
        2)	题目详述
        HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
        3)	题目详解
        思路
        •	大体思路就是用一个tempmax代表前面的连续数字的最大和，如果这个最大和是正的，那么加上数组的当前数字，那么这个连续的和是变大的，这个就是有可能的潜在的最大和。
        代码
        import java.util.*;
public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        if(array.length <= 0)
            return -1;
        int realMax = array[0];
        int currentMax = 0;//当前最大值
        for(int i=0;i<array.length;i++)
        {
            if(currentMax + array[i] >= array[i])
            {//当前最大值加上当前数组的数如果比数组当前这个数大，那么就累加
                //这里得好好体会，因为前面连续和大于0，所以加上当前数连续和肯定变大！所以是可能的最大连续和
                currentMax += array[i];
            }else{
                //如果当前连续最大和小于0，那么就把currentMax赋值为当前这个数组中的数，重新开始
                currentMax = array[i];
            }
            if(currentMax > realMax)//每计算出一个当前最大连续和就和最后的要返回结果进行比较
                realMax = currentMax;//更新
        }
        return realMax;
    }
}

31.	整数中1出现的次数（从1到n整数中1出现的次数）
        题目描述
        求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
        解析
        遍历一遍不就完了吗
        当然，你可从1遍历到n，然后将当前被遍历的到的数中1出现的次数累加到结果中可以很容易地写出如下代码：
public int NumberOf1Between1AndN_Solution(int n) {
        if(n < 1){
        return 0;
        }
        int res = 0;
        for(int i = 1 ; i <= n ; i++){
        res += count(i);
        }
        return res;
        }

public int count(int n){
        int count = 0;
        while(n != 0){
        //取个位
        count = (n % 10 == 1) ? ++count : count;
        //去掉个位
        n /= 10;
        }
        return count;
        }

public int NumberOf1Between1AndN_Solution(int n) {
        if(n < 1){
        return 0;
        }
        return process(n);
        }

public int process(int n){
        if(n == 0){
        return 0;
        }
        if(n < 10 && n > 0){
        return 1;
        }
        int res = 0;
        //得到十进制位数
        int bitCount = bitCount(n);
        //十进制最高位上的数
        int highestBit = numOfBit(n, bitCount);
        //1、统计最高位为1时，共有多少个数
        if(highestBit > 1){
        res += powerOf10(bitCount - 1);
        }else{
        //highestBit == 1
        res += n - powerOf10(bitCount - 1) + 1;
        }
        //2、统计其它位为1的情况
        res += powerOf10(bitCount - 2) * (bitCount - 1) * highestBit;
        //3、剩下的部分交给递归
        res += process(n % powerOf10(bitCount - 1));
        return res;
        }

//返回10的n次方
public int powerOf10(int n){
        if(n == 0){
        return 1;
        }
        boolean minus = false;
        if(n < 0){
        n = -n;
        minus = true;
        }
        int res = 1;
        for(int i = 1 ; i <= n ; i++){
        res *= 10;
        }
        return minus ? 1 / res : res;
        }

public int bitCount(int n){
        int count = 1;
        while((n /= 10) != 0){
        count++;
        }
        return count;
        }

public int numOfBit(int n, int bit){
        while(bit-- > 1){
        n /= 10;
        }
        return n % 10;
        }

        32.	把数组排成最小的数
        输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
        思路：
        和昨天的那道题一样，全排列数组中所有的情况，把所有结果存到treeSet里面，然后由于是treeSet是按照从小到大排序的，所有返回第一个结果就行。
        import java.util.*;
public class Solution {
    public String PrintMinNumber(int [] numbers) {
        if(numbers.length == 0)
            return "";
        TreeSet<String> set = new TreeSet<>();
        PrintMinNumber(numbers,0,set);
        return set.pollFirst();
    }
    public void PrintMinNumber(int [] numbers,int index,TreeSet<String> set)
    {
        if(index == numbers.length-1)
        {
            String tempStr = "";
            for(int i=0;i<numbers.length;i++)
                tempStr += String.valueOf(numbers[i]);
            set.add(tempStr);
        }else{
            for(int i=index;i<numbers.length;i++)
            {
                swap(numbers,index,i);
                PrintMinNumber(numbers,index+1,set);
                swap(numbers,index,i);
            }
        }
    }
    public void swap(int [] numbers,int i,int j)
    {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}

33.	丑数
        把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
        思路：
        通俗易懂的解释：
        首先从丑数的定义我们知道，一个丑数的因子只有2,3,5，那么丑数p = 2 ^ x * 3 ^ y * 5 ^ z，换句话说一个丑数一定由另一个丑数乘以2或者乘以3或者乘以5得到，那么我们从1开始乘以2,3,5，就得到2,3,5三个丑数，在从这三个丑数出发乘以2,3,5就得到4，6,10,6，9,15,10,15,25九个丑数，我们发现这种方法会得到重复的丑数，而且我们题目要求第N个丑数，这样的方法得到的丑数也是无序的。那么我们可以维护三个队列：
        （1）丑数数组： 1
        乘以2的队列：2
        乘以3的队列：3
        乘以5的队列：5
        选择三个队列头最小的数2加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
        （2）丑数数组：1,2
        乘以2的队列：4
        乘以3的队列：3，6
        乘以5的队列：5，10
        选择三个队列头最小的数3加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
        （3）丑数数组：1,2,3
        乘以2的队列：4,6
        乘以3的队列：6,9
        乘以5的队列：5,10,15
        选择三个队列头里最小的数4加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
        （4）丑数数组：1,2,3,4
        乘以2的队列：6，8
        乘以3的队列：6,9,12
        乘以5的队列：5,10,15,20
        选择三个队列头里最小的数5加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
        （5）丑数数组：1,2,3,4,5
        乘以2的队列：6,8,10，
        乘以3的队列：6,9,12,15
        乘以5的队列：10,15,20,25
        选择三个队列头里最小的数6加入丑数数组，但我们发现，有两个队列头都为6，所以我们弹出两个队列头，同时将12,18,30放入三个队列；
        ……………………
        疑问：
        1.为什么分三个队列？
        丑数数组里的数一定是有序的，因为我们是从丑数数组里的数乘以2,3,5选出的最小数，一定比以前未乘以2,3,5大，同时对于三个队列内部，按先后顺序乘以2,3,5分别放入，所以同一个队列内部也是有序的；
        2.为什么比较三个队列头部最小的数放入丑数数组？
        因为三个队列是有序的，所以取出三个头中最小的，等同于找到了三个队列所有数中最小的。
        实现思路：
        我们没有必要维护三个队列，只需要记录三个指针显示到达哪一步；“|”表示指针,arr表示丑数数组；
        （1）1
        |2
        |3
        |5
        目前指针指向0,0,0，队列头arr[0] * 2 = 2,  arr[0] * 3 = 3,  arr[0] * 5 = 5
        （2）1 2
        2 |4
        |3 6
        |5 10
        目前指针指向1,0,0，队列头arr[1] * 2 = 4,  arr[0] * 3 = 3, arr[0] * 5 = 5
        （3）1 2 3
        2| 4 6
        3 |6 9
        |5 10 15
        目前指针指向1,1,0，队列头arr[1] * 2 = 4,  arr[1] * 3 = 6, arr[0] * 5 = 5
        ………………

        import java.util.*;
public class Solution {
    public int GetUglyNumber_Solution(int index) {
        if(index <= 0)
            return 0;
        if(index == 1)
            return 1;
        ArrayList<Integer> resultList = new ArrayList<>();
        resultList.add(1);
        int index2=0,index3=0,index5=0;
        int count = 1;
        while(count < index)
        {
            int next = min(resultList.get(index2)*2,resultList.get(index3)*3,resultList.get(index5)*5);
            resultList.add(next);
            count++;
            while(resultList.get(index2)*2 <= next)
                index2++;//每次都往后进行更新，找到当前如果因子为2最大的index2
            while(resultList.get(index3)*3 <= next)
                index3++;//同理
            while(resultList.get(index5)*5 <= next)
                index5++;//同理
        }
        return resultList.get(index-1);
    }
    public int min(int a,int b,int c)
    {
        int temp = (a<b)?a:b;
        return (temp<c)?temp:c;
    }
}

34.	第一个只出现一次的字符
        4)	题目详述
        在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
        5)	思路
        •	字符串中的字符都是英文的字母，所以每一个字母都有一个ASCII码与其对应，然后建立一个字符数组长度是256，可以把每一个字符对应一个数组的下标
        •	然后设立一个index！然后比如字符a第一次出现那么strArray[a字符对应的ASC码] = index;然后如果下一次a再出现了，那么strArray[a字符对应的ASC码] = -1；这样子做，只要字符出现了大于等于2次，都会这样子等于-1
        •	而只出现一次的字符，由于index这个变量是每次递增的！我们只需要遍历一遍，找index最小的那个字符。
        6)	题目详解
public class Solution {
    public int FirstNotRepeatingChar(String str) {
        if(str.equals(""))
            return -1;
        int [] temp = new int[256];
        int index = 1;
        char [] strArray = str.toCharArray();
        for(int i=0;i<strArray.length;i++)
        {
            if(temp[(int)strArray[i]] == 0)
            {
                temp[(int)strArray[i]] = index;
                index++;
            }else{
                temp[(int)strArray[i]] = -1;
            }
        }//这个循环就是遍历一遍，找到出现一次的字符
        //只要index大于0就是出现一次的字符
        int minIndex = Integer.MAX_VALUE;
        char ch = '1';
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i] > 0)
            {
                if(temp[i] < minIndex)
                {//找最小的index对应的字符
                    minIndex = temp[i];
                    ch = (char)i;
                }
            }
        }
        int result = -1;
        for(int i=0;i<strArray.length;i++)
        {//去找这个字符的下标！
            if(strArray[i] == ch)
                return i;
        }
        return -1;
    }
}

35.	数组中的逆序对
        题目描述
        在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
        1.	public int InversePairs(int [] arr) {
        2.	    if(arr == null || arr.length <= 1){
        3.	        return 0;
        4.	    }
        5.	    return mergeSort(arr, 0, arr.length - 1).pairs;
        6.	}
        输入描述
        1.	题目保证输入的数组中没有相同的数字
        2.	数据范围：对于%50的数据,size<=10^4；对于%75的数据,size<=10^5；对于%100的数据,size<=2*10^5
        解析
        借助归并排序的流程，将归并流程中前一个数组的数比后一个数组的数小的情况记录下来。
        归并的原始逻辑是根据输入的无序数组返回一个新建的排好序的数组：

public int[] mergeSort(int[] arr, int start, int end){
        if(arr == null || arr.length == 0 || start < 0 || end > arr.length - 1 || start > end){
        throw new IllegalArgumentException();
        }
        if(start == end){
        return new int[]{ arr[end] };
        }

        int[] arr1 = mergeSort(arr, start, mid);
        int[] arr2 = Info right = mergeSort(arr, mid + 1, end);
        int[] copy = new int[arr1.length + arr2.length];
        int p1 = 0, p2 = 0, p = 0;

        while(p1 < arr1.length && p2 < arr2.length){
        if(arr1[p1] > arr2[p2]){
        copy[p++] = arr1[p1++];
        }else{
        copy[p++] = arr2[p2++];
        }
        }
        while(p1 < arr1.length){
        copy[p++] = arr1[p1++];
        }
        while(p2 < arr2.length){
        copy[p++] = arr2[p2++];
        }
        return copy;
        }


public int InversePairs(int [] arr) {
        if(arr == null || arr.length <= 1){
        return 0;
        }
        return mergeSort(arr, 0, arr.length - 1).pairs;
        }

class Info{
    int arr[];
    int pairs;
    Info(int[] arr, int pairs){
        this.arr = arr;
        this.pairs = pairs;
    }
}

    public Info mergeSort(int[] arr, int start, int end){
        if(arr == null || arr.length == 0 || start < 0 || end > arr.length - 1 || start > end){
            throw new IllegalArgumentException();
        }
        if(start == end){
            return new Info(new int[]{arr[end]}, 0);
        }

        int pairs = 0;
        int mid = start + ((end - start) >> 1);
        Info left = mergeSort(arr, start, mid);
        Info right = mergeSort(arr, mid + 1, end);
        pairs += (left.pairs + right.pairs) % 1000000007;

        int[] arr1 = left.arr, arr2 = right.arr, copy = new int[arr1.length + arr2.length];
        int p1 = arr1.length - 1, p2 = arr2.length - 1, p = copy.length - 1;

        while(p1 >= 0 && p2 >= 0){
            if(arr1[p1] > arr2[p2]){
                pairs += (p2 + 1);
                pairs %= 1000000007;
                copy[p--] = arr1[p1--];
            }else{
                copy[p--] = arr2[p2--];
            }
        }

        while(p1 >= 0){
            copy[p--] = arr1[p1--];
        }
        while(p2 >= 0){
            copy[p--] = arr2[p2--];
        }

        return new Info(copy, pairs % 1000000007);
    }

36.	两个链表的第一个公共结点
        7)	题目详述
        输入两个链表，找出它们的第一个公共结点。
        8)	思路
        •	剑指offer思路，就是分别求两个链表长度，求长度差
        •	长的那个先走长度差个单位
        •	这样两个就同一个起跑线了
        •	然后就是就长的短的一起走，直到节点相同就是第一个公共节点
        9)	题目详解
/*
public class ListNode {
   int val;
   ListNode next = null;

   ListNode(int val) {
       this.val = val;
   }
}*/
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null)
            return null;
        int length1 = 0;
        int length2 = 0;
        ListNode p1 = pHead1,p2 = pHead2;
        while(p1 != null)
        {
            length1++;
            p1 = p1.next;
        }
        while(p2 != null)
        {
            length2++;
            p2 = p2.next;
        }//分别求两个链表长度
        int cha = 0;
        if(length1 > length2)
        {//求长度差，长的先走长度差个单位
            cha = length1 - length2;
            p1 = pHead1;
            p2 = pHead2;
            for(int i=0;i<cha;i++)
                p1 = p1.next;
        }else{
            cha = length2 - length1;
            p2 = pHead2;
            p1 = pHead1;
            for(int i=0;i<cha;i++)
                p2 = p2.next;
        }
        while(p1 != p2)
        {//找第一个相同的节点
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}

•	题目详述
        37.	统计一个数字在排序数组中出现的次数。
        •	题目详解
        思路
        •	有序和数组这个两个字眼结合起来，肯定是要用到二分查找这一类；
        •	首先就是找最左侧的下标，利用二分查找首先是找到有一个值是与目标值target是相等的，然后因为是找最左侧的下标，所以把right=mid-1来一直往左边去逼近最左侧的值；
        •	至于找最右侧的下标就是，将left=mid+1,来去逼近最右侧的下标；
        •	如果没有找到则说明不存在返回-1；
        示例
        •	这里举一个例子帮助大家理解，对于数组[1,2,4,4,4,4,4,5,6]，找4的最左下标。
        •	对于这个数目来说，lfet,right,mid分别代表下标值首先left=0.right=8,所以mid=(0+8)/2 = 4；
        •	由于target=4与nums[mid]相等，所以此时记录下来这个下标，也就是mid的值4，这个下标是可能的最左的4的下标所以要记录保存一下；
        •	观察这个数组，可以知道，最左的4的下标是2，所以为了找到这个最左的下标，需要令right的值去等于mid-1;这样就把right这一边慢慢地往左靠，因为是找最左的嘛~，所以肯定是要缩小right的的值去逼近这个最左的4，直到找到这个最左的4为止~；
        •	找最右边的4的思路也是一样的哦，就是令left=mid+1去逼近最右边的这个4.
        •	代码
public class Solution {
    public int GetNumberOfK(int [] array , int k) {
        int leftIndex = -1,start=0,end=array.length-1,rightIndex=-1;
        while(start <= end)
        {
            int mid = (start+end)/2;
            if(array[mid] > k)
            {
                end = mid-1;
            }else if(array[mid] < k){
                start = mid+1;
            }else{
                leftIndex = mid;
                end = mid-1;
            }
        }
        start = 0;
        end = array.length-1;
        while(start <= end)
        {
            int mid = (start+end)/2;
            if(array[mid] > k)
            {
                end = mid-1;
            }else if(array[mid] < k){
                start = mid+1;
            }else{
                rightIndex = mid;
                start = mid+1;
            }
        }
        if(array.length == 0 || rightIndex == -1)
            return 0;
        return rightIndex-leftIndex+1;
    }
}
38.	二叉树的深度
        输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
        10)	思路
        •	递归思路，根的高度等于（左子树的高度和右子树的高度重高度较高的那一个高度）+1
        11)	题目详解
/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Solution {
    public int TreeDepth(TreeNode root) {
        if(root == null)
            return 0;//为空高度是0
        if(root != null && root.left == null && root.right == null)
            return 1;//只有一个节点，叶子节点高度是1
        //左右子树高度 中取较高的那一个高度+1
        return TreeDepth(root.left)>TreeDepth(root.right)?TreeDepth(root.left)+1:TreeDepth(root.right)+1;
    }
}
39.	平衡二叉树
        输入一棵二叉树，判断该二叉树是否是平衡二叉树。
        12)	思路
        •	从下往上遍历，如果子树是平衡二叉树，则返回子树的高度；如果发现子树不是平衡二叉树，则直接停止遍历，这样至多只对每个结点访问一次。
        13)	题目详解
public class Solution {
    private boolean flag = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        TreeLength(root);
        return flag;
    }
    private int TreeLength(TreeNode root)
    {
        if(root == null)
            return 0;
        int left = TreeLength(root.left);//左子树的高度
        int right = TreeLength(root.right);//右子树的高度
        if(left-right >= 2 || right - left >= 2)
        {//左右子树高度差大于等于2，标记就不是true
            flag = false;
        }
        return left>right?(left+1):(right+1);
    }
}

40.	数组中只出现一次的数字
        一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
        思路：
        首先我们考虑这个问题的一个简单版本：一个数组里除了一个数字之外，其他的数字都出现了两次。请写程序找出这个只出现一次的数字。
        这个题目的突破口在哪里？题目为什么要强调有一个数字出现一次，其他的出现两次？我们想到了异或运算的性质：任何一个数字异或它自己都等于0 。也就是说，如果我们从头到尾依次异或数组中的每一个数字，那么最终的结果刚好是那个只出现一次的数字，因为那些出现两次的数字全部在异或中抵消掉了。
        有了上面简单问题的解决方案之后，我们回到原始的问题。如果能够把原数组分为两个子数组。在每个子数组中，包含一个只出现一次的数字，而其它数字都出现两次。如果能够这样拆分原数组，按照前面的办法就是分别求出这两个只出现一次的数字了。
        我们还是从头到尾依次异或数组中的每一个数字，那么最终得到的结果就是两个只出现一次的数字的异或结果。因为其它数字都出现了两次，在异或中全部抵消掉了。由于这两个数字肯定不一样，那么这个异或结果肯定不为0 ，也就是说在这个结果数字的二进制表示中至少就有一位为1 。我们在结果数字中找到第一个为1 的位的位置，记为第N 位。现在我们以第N 位是不是1 为标准把原数组中的数字分成两个子数组，第一个子数组中每个数字的第N 位都为1 ，而第二个子数组的每个数字的第N 位都为0 。
        现在我们已经把原数组分成了两个子数组，每个子数组都包含一个只出现一次的数字，而其它数字都出现了两次。因此到此为止，所有的问题我们都已经解决

//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
public class Solution {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int temp = 0;
        for(int i=0;i<array.length;i++)
        {
            temp = temp ^ array[i];//异或，相同为0，不同为1
        }
        int index = findOne(temp);//找到第一个不为0的位置，这个就是不同点
        num1[0] = 0;
        num2[0] = 0;
        for(int i=0;i<array.length;i++)
        {
            if(IsBit1(array[i],index))//根据这个不同的位置进行区分。
            {
                num1[0] ^= array[i];//分成两组，每一组再分别异或，相同的都成0了，剩下了那唯一一个不同的
            }else{
                num2[0] ^= array[i];
            }
        }
    }
    private int findOne(int number)
    {
        int index = 0;
        while((number & 1) == 0)
        {
            index++;
            number = number >> 1;
        }
        return index;
    }
    private boolean IsBit1(int number,int index)
    {
        number = number >> index;
        if((number & 1) == 0)
            return false;
        return true;
    }
}

41.	和为S的连续正数序列
        题目描述
        小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
        1.	public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        2.
        3.	}
        输出描述
        输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
        解析
        将 1~(S/2+1)区间的数 n依次加入到队列中（因为从 S/2+1之后的任意两个正数之和都大于 S）：
        1.	将 n加入到队列 queue中并将队列元素之和 queueSum更新，更新 queueSum之后如果发现等于 sum，那么将此时的队列快照加入到返回结果 res中，并弹出队首元素（保证下次入队操作时队列元素之和是小于sum的）
        2.	更新 queueSum之后如果发现大于 sum，那么循环弹出队首元素直到 queueSum<=Sum，如果循环弹出之后发现 queueSum==sum那么将队列快照加入到 res中，并弹出队首元素（保证下次入队操作时队列元素之和是小于sum的）；如果 queueSum<sum那么入队下一个 n
        于是有如下代码：
public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        if(sum <= 1){
        return res;
        }
        LinkedList<Integer> queue = new LinkedList();
        int n = 1, halfSum = (sum >> 1) + 1, queueSum = 0;
        while(n <= halfSum){
        queue.addLast(n);
        queueSum += n;
        if(queueSum == sum){
        ArrayList<Integer> one = new ArrayList();
        one.addAll(queue);
        res.add(one);
        queueSum -= queue.pollFirst();
        }else if(queueSum > sum){
        while(queueSum > sum){
        queueSum -= queue.pollFirst();
        }
        if(queueSum == sum){
        ArrayList<Integer> one = new ArrayList();
        one.addAll(queue);
        res.add(one);
        queueSum -= queue.pollFirst();
        }
        }
        n++;
        }

        return res;
        }
        我们发现 11~15和 20~24行的代码是重复的，于是可以稍微优化一下：
public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        if(sum <= 1){
        return res;
        }
        LinkedList<Integer> queue = new LinkedList();
        int n = 1, halfSum = (sum >> 1) + 1, queueSum = 0;
        while(n <= halfSum){
        queue.addLast(n);
        queueSum += n;
        if(queueSum > sum){
        while(queueSum > sum){
        queueSum -= queue.pollFirst();
        }
        }
        if(queueSum == sum){
        ArrayList<Integer> one = new ArrayList();
        one.addAll(queue);
        res.add(one);
        queueSum -= queue.pollFirst();
        }
        n++;
        }

        return res;
        }

        42.	和为S的两个数字
        14)	题目详述
        输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
        15)	题目详解
        思路
        数列满足递增，设两个头尾两个指针i和j， 若ai + aj == sum，就是答案（相差越远乘积越小） 若ai + aj > sum，aj肯定不是答案之一（前面已得出 i 前面的数已是不可能），j -= 1 若ai + aj < sum，ai肯定不是答案之一（前面已得出 j 后面的数已是不可能），i += 1
        代码
        import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> resultList = new ArrayList<>();
        if(array.length <= 1)
            return resultList;
        for(int i=0,j=array.length-1;i<j;)
        {
            if(array[i] + array[j] == sum)
            {
                resultList.add(array[i]);
                resultList.add(array[j]);
                return resultList;//找到直接返回
            }else if(array[i] + array[j] > sum)
            {//和已经大于目标值了，所以大数要减小！
                j--;
            }else{//和小于目标值，所以小数要表大！
                i++;
            }
        }
        return resultList;

    }
}

43.	左旋转字符串
        16)	题目详述
        汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
        17)	题目详解
        思路
        ，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。 剑指offer思路，先反转整个字符串，就是fedZYXcba,然后反转前6位XYZdef再反转后三位abc。那么就是XYZdefabc。
        代码
public class Solution {
    public String LeftRotateString(String str,int n) {
        if(str == null || str.equals(""))
            return "";
        n = n % str.length();
        char [] strArray = str.toCharArray();
        reverse(strArray,0,str.length()-1);//反转整个字符串
        reverse(strArray,0,str.length()-n-1);//反转前字符串长度-n个字符
        reverse(strArray,str.length()-n,str.length()-1);//反转后n个字符
        return String.valueOf(strArray);
    }
    public void reverse(char [] strArray,int start,int end)
    {//反转字符数组
        while(start < end)
        {
            char ch = strArray[start];
            strArray[start] = strArray[end];
            strArray[end] = ch;
            start++;
            end--;
        }
    }
}

44.	翻转单词顺序列
        18)	题目详述
        牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
        19)	题目详解
        思路
        先反转整个字符串，“student. a am I”反转完就是“I ma a .tneduts”,然后依据空格分割字符串以后，再继续反转每一个单词，最后就是最终的结果
        代码
public class Solution {
    public String ReverseSentence(String str) {
        if(str == null || str == "")
            return str;
        char [] strArray = str.toCharArray();
        reverse(strArray,0,strArray.length-1);//反转整个字符串
        int begin = 0;
        for(int i=0;i<strArray.length;i++)
        {
            if(strArray[i] == ' ')
            {
                reverse(strArray,begin,i-1);
                begin = i + 1;//每当遇到空格，就是一个单词，然后反转这个单词
            }
        }
        if(begin < strArray.length-1)
        {//反转最后一个单词
            reverse(strArray,begin,strArray.length-1);
        }
        return String.valueOf(strArray);
    }
    private void reverse(char [] str,int begin,int end)
    {//反转字符数组
        while(begin < end)
        {
            char temp = str[begin];
            str[begin] = str[end];
            str[end] = temp;
            begin++;
            end--;
        }
    }
}

45.	扑克牌顺子
        1.	题目详述
        LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
        2.	题目详解
        思路
        	先排序，然后统计数字与数字之间的差值，计算差值和；统计数组中出现的0的个数，然后比较差值和与0的个数的大小，如果0的个数比差值大，那么就说明可以连成顺子，否则不能连成顺子。
        	当然还得判断里面有没有对子，如果有对子，那么一定不是顺子了！
        代码
        import java.util.*;
public class Solution {
    public boolean isContinuous(int [] numbers) {
        if(numbers.length <= 4)
            return false;
        Arrays.sort(numbers);
        int numberZero = 0;
        int numberNeed = 0;
        for(int i=0;i<numbers.length;i++)
        {
            if(numbers[i] == 0)
            {//统计0出现的个人
                numberZero++;
            }else{
                if(i < (numbers.length - 1))
                {
                    if(numbers[i+1] == numbers[i])
                        return false;//出现对子，那么肯定不是顺子
                    numberNeed += (numbers[i+1] - numbers[i] - 1);//统计数组中数字和数字之间的差值
                }
            }
        }
        if(numberZero >= numberNeed)
            return true;//如果0的个人大于差值，那么可以用0来填充，组成顺子，所以可以连成顺子
        return false;
    }
}

46.	孩子们的游戏(圆圈中最后剩下的数)
        20)	题目详述
        每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
        21)	题目详解
        思路
        Java实现的话，可以使用LinkedList，考虑删除节点的效率。模拟游戏过程即可：其实这是个约瑟夫环问题，但是绝对没必要去死记硬背数学公式，直接用链表模拟游戏过程即可。
        代码
        import java.util.*;
public class Solution {
    public int LastRemaining_Solution(int n, int m) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<n;i++)
            list.add(i);
        int a = 0;
        while(list.size() > 1)
        {
            a = (a+m-1) % list.size();
            list.remove(a);//每次删除，直到留下最后的那一个！
        }
        if(list.size() == 1)
            return list.get(0);//数组只剩下最后的一个
        return -1;
    }
}

47.	求1+2+3+...+n
        22)	题目详述
        求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
        23)	题目详解
        思路
        使用逻辑判断语，具体看代码 把(n-1)>=0放在最开始的判断上，利用&&操作符的性质，只有在（n-1)>=0的条件下才会执行后面的语句，temp = temp+n是每次进行累加的过程，temp是一个全局变量，可以保留累加和，最后的complex(n-1)>0用来进入到更深层次的递归层里面
        代码
public class Solution {
    public int temp = 0;
    public int Sum_Solution(int n) {
        complex(n);
        return temp;
    }
    public int complex(int n)
    {
        boolean flag =  (n-1) >= 0 && (temp = temp + n) > 0 && complex(n-1)>0;

        return temp;
    }
}

48.	不用加减乘除做加法
        24)	题目详述
        写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
        25)	题目详解
        思路
        首先看十进制是如何做的： 5+7=12，三步走 第一步：相加各位的值，不算进位，得到2。 第二步：计算进位值，得到10. 如果这一步的进位值为0，那么第一步得到的值就是最终结果。
        第三步：重复上述两步，只是相加的值变成上述两步的得到的结果2和10，得到12。
        同样我们可以用三步走的方式计算二进制值相加： 5-101，7-111 第一步：相加各位的值，不算进位，得到010，二进制每位相加就相当于各位做异或操作，101^111。
        第二步：计算进位值，得到1010，相当于各位做与操作得到101，再向左移一位得到1010，(101&111)<<1。
        第三步重复上述两步， 各位相加 010^1010=1000，进位值为100=(010&1010)<<1。 继续重复上述两步：1000^100 = 1100，进位值为0，跳出循环，1100为最终结果。
        代码
public class Solution {
    public int Add(int num1,int num2) {
        int sum = 0;
        int carry = 0;
        do{
            sum = num1 ^ num2;
            carry = num1 & num2;
            if(carry  != 0)
                carry = carry << 1;
            num1 = sum;
            num2 = carry;
        }while(carry != 0);
        return sum;
    }
}

49.	把字符串转换成整数
        1.	题目详述
        将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。 输入描述: 输入一个字符串,包括数字字母符号,可以为空 输出描述: 如果是合法的数值表达则返回该数字，否则返回0 示例1 输入
        1.	+2147483647
        2.	    1a33
        输出
        2.	2147483647
        3.	    0
        4.	题目详解
        思路
        	只有第一个位置上的字符可以是+或-或数字，其他位置上的字符必须是数字
        	如果第一个字符是-，返回结果必须是负数
        	如果字符串只有一个字符，且为+或-，这情况很容易被忽略
        	在对字符串解析转换时，如果发现溢出（包括正数向负数溢出，负数向正数溢出），必须有所处理（此时可以和面试官交涉），但不能视而不见。
        代码
public class Solution {
    public int StrToInt(String str) {
        if(str == null || str == "" || str.equals("+") || str.equals("-"))
            return 0;
        int flag = 0;
        long sum = 0;
        char [] strArray = str.toCharArray();
        for(int i=0;i<strArray.length;i++)
        {
            if(strArray[0] == '-' && i == 0)
            {//是负数记录下来
                flag = 1;
                continue;
            }
            if(strArray[0] == '+' && i == 0)
            {//是整数
                continue;
            }
            if(!judge(strArray[i]))//判断是不是正常的数
                return 0;
            sum = sum * 10 + strArray[i] - '0';
        }
        if(flag == 1)
        {
            sum = sum * (-1);
            if(sum < Integer.MIN_VALUE)
                return 0;//判断越界没
            return (int)sum;
        }
        if(sum > Integer.MAX_VALUE)
            return 0;//判断越界没
        return (int)sum;
    }
    private boolean judge(char ch)
    {//利用ASC码，不在这个范围内的ASC码直接返回false，说明不是正常的数
        int number = ch - '0';
        if(number >= 0 && number <= 9)
        {
            return true;
        }
        return false;
    }
}

50.	数组中重复的数字
        思路1：哈希法
        	由于所有元素值是有范围的，因此可以用一个长度为n的数组，下标表示序列中的每一个值，下标对应的值表示该下标出现的次数。
        	只需扫描一次原序列，就统计出所有元素出现的次数；
        	再扫描一次哈希数组，找到一个出现次数大于1的值即可。
        	这种方法时间复杂度和空间复杂度都为O(n)。
public boolean duplicate(int array[],int length,int [] duplication) {
        if ( array==null ) return false;

        // 判断数组是否合法（每个数都在0~n-1之间）
        for ( int i=0; i<length; i++ ) {
        if ( array[i]<0 || array[i]>length-1 ) {
        return false;
        }
        }

        // key step
        int[] hash = new int[length];
        for( int i=0; i<length; i++ ){
        hash[array[i]]++;
        }
        for(int i=0; i<length; i++){
        if ( hash[i]>1 ) {
        duplication[0] = i;
        return true;
        }
        }
        return false;
        }
        思路2：高级
        此大法利用了哈希的特性，但不需要额外的存储空间。 因此时间复杂度为O(n)，不需要额外空间！
        	把当前序列当成是一个下标和下标对应值是相同的数组；
        	遍历数组，判断当前位的值和下标是否相等： 2.1. 若相等，则遍历下一位； 2.2. 若不等，则将当前位置i上的元素和a[i]位置上的元素比较：若它们相等，则成功！若不等，则将它们两交换。- - 换完之后a[i]位置上的值和它的下标是对应的，但i位置上的元素和下标并不一定对应；重复2.2的操作，直到当前位置i的值也为i，将i向后移一位，再重复2.
public boolean duplicate(int array[],int length,int [] duplication) {
        if ( array==null ) return false;

        // 判断数组是否合法（每个数都在0~n-1之间）
        for ( int i=0; i<length; i++ ) {
        if ( array[i]<0 || array[i]>length-1 ) {
        return false;
        }
        }

        // key step
        for( int i=0; i<length; i++ ){
        while( i!=array[i] ){
        if ( array[i] == array[array[i]] ) {
        duplication[0] = array[i];
        return true;
        }

        int temp = array[i];
        array[i] = array[array[i]];
        array[array[i]] = temp;
        }
        }

        return false;
        }

        51.	构造乘积数组
        题目描述
        给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]A[1]...A[i-1]A[i+1]...A[n-1]。不能使用除法。
        1.	public int[] multiply(int[] arr) {
        2.
        3.	}
        分析
        规律题：

public int[] multiply(int[] arr) {
        if(arr == null || arr.length == 0){
        return arr;
        }
        int len = arr.length;
        int[] arr1 = new int[len], arr2 = new int[len];
        arr1[0] = 1;
        arr2[len - 1] = 1;
        for(int i = 1 ; i < len ; i++){
        arr1[i] = arr1[i - 1] * arr[i - 1];
        arr2[len - 1 - i] = arr2[len - i] * arr[len - i];
        }
        int[] res = new int[len];
        for(int i = 0 ; i < len ; i++){
        res[i] = arr1[i] * arr2[i];
        }

        return res;
        }

        52.	正则表达式匹配
        题目描述
        请实现一个函数用来匹配包括'.'和''的正则表达式。模式中的字符'.'表示任意一个字符，而''表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"abaca"匹配，但是与"aa.a"和"ab*a"均不匹配
        1.	public boolean match(char[] str, char[] pattern){
        2.
        3.	}
        解析
        使用 p1指向 str中下一个要匹配的字符，使用 p2指向 pattern中剩下的模式串的首字符
        1.	如果 p2>=pattern.length，表示模式串消耗完了，这时如果 p1仍有字符要匹配那么返回 false否则返回 true
        2.	如果 p1>=str.length，表示要匹配的字符都匹配完了，但模式串还没消耗完，这时剩下的模式串必须符合 a*b*c*这样的范式以能够作为空串处理，否则返回 false
        3.	p1和 p2都未越界，按照 p2后面是否是 *来讨论
        4.	p2后面如果是 *，又可按照 pattern[p2]是否能够匹配 str[p1]分析：
        a.	pattern[p2]==‘.’||pattern[p2]==str[p1]，这时可以选择匹配一个 str[p1]并继续向后匹配（不用跳过 p2和其后面的 *），也可以选择将 pattern[p2]和其后面的 *作为匹配空串处理，这时要跳过 p2和 其后面的 *
        b.	pattern[p2]!=str[p1]，只能作为匹配空串处理，跳过 p2
        5.	p2后面如果不是 *：
        a.	pattern[p2]==str[p1]||pattern[p2]==‘.’， p1,p2同时后移一个继续匹配
        b.	pattern[p2]==str[p1]，直接返回 false
public boolean match(char[] str, char[] pattern){
        if(str == null || pattern == null){
        return false;
        }
        if(str.length == 0 && pattern.length == 0){
        return true;
        }
        return matchCore(str, 0, pattern, 0);
        }

public boolean matchCore(char[] str, int p1, char[] pattern, int p2){
        //模式串用完了
        if(p2 >= pattern.length){
        return p1 >= str.length;
        }
        if(p1 >= str.length){
        if(p2 + 1 < pattern.length && pattern[p2 + 1] == '*'){
        return matchCore(str, p1, pattern, p2 + 2);
        }else{
        return false;
        }
        }

        //如果p2的后面是“*”
        if(p2 + 1 < pattern.length && pattern[p2 + 1] == '*'){
        if(pattern[p2] == '.' || pattern[p2] == str[p1]){
        //匹配一个字符，接着还可以向后匹配；或者将当前字符和后面的星合起来做空串
        return matchCore(str, p1 + 1, pattern, p2) || matchCore(str, p1, pattern, p2 + 2);
        }else{
        return matchCore(str, p1, pattern, p2 + 2);
        }
        }
        //如果p2的后面不是*
        else{
        if(pattern[p2] == '.' || pattern[p2] == str[p1]){
        return matchCore(str, p1 + 1, pattern, p2 + 1);
        }else{
        return false;
        }
        }
        }

        53.	表示数值的字符串
        1.	题目详述
        请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
        2.	题目详解
        思路
        	把所有情况考虑一遍
        代码
public class Solution {
    public boolean isNumeric(char[] str) {
        int index = 0;
        if(str.length == 0)
            return false;
        if(str.length == 1 && (str[0] == '+' || str[0] == '-'))
            return false;//只有一个正负号
        if(str[0] == '+' || str[0] == '-')
            index++;//跳过最开始的字符
        index = judgeDigits(str,index);//跳过前面的数字
        if(index == str.length)
            return true;//如果正好遍历完，发现是到了字符串的长度，那么返回true。
        if(str[index] == '.')
        {//跳过数字以后，发现是个小数点
            index++;//然后跳过小数点
            if(index == str.length)
                return false;//说明是1123. 这就不是合法数字
            index = judgeDigits(str,index);//跳过接下来的所有数字
            if(index == str.length)
                return true;//如果恰好对于字符数组的长度，说明是123.432是规范数字
            if(str[index] == 'e' || str[index] == 'E')
            {//说明是1.4e10 这样类型的数字
                index++;//把e跳过去
                return judgeE(str,index);//判断是不是规范的带e的数字
            }
            return false;//返回假
        }else if(str[index] == 'e' || str[index] == 'E')
        {//说明可能是12e13 这样类型的数字
            index++;//把e跳过去
            return judgeE(str,index);//判断是不是规范的带e的数字
        }
        return false;
    }
    private boolean judgeE(char[] str,int index)
    {
        if(index >= str.length)
            return false;
        if(str[index] == '+' || str[index] == '-')
            index++;//如果是符号则跳过 12e+12 这种可惜的数字
        if(index >= str.length)
            return false;//如果刚跳过e就到了字符串末尾 是12e就是不规范的
        index = judgeDigits(str,index);//跳过全部的数字
        if(index == str.length)//说明是12e23这样的数字是规范的
            return true;
        return false;
    }
    private int judgeDigits(char[] str,int index)
    {
        while(index < str.length)
        {//判断是不是在0-9之间，不是的话就break返回index下标值
            int number = str[index] - '0';
            if(number <= 9 && number >= 0)
                index++;
            else{
                break;
            }
        }
        return index;
    }
}

54.	字符流中第一个不重复的字符
        题目详述
        请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。 输出描述: 如果当前字符流没有存在出现一次的字符，返回#字符。
        题目详解
        思路
        因为使用字符，所以根据ASC码的规则，只有256个字符，所以我们建立一个数组长度的为256的一个整数数组charArray
        数组初始的时候，每一个数组下标对应一个字符，（因为ASC码的话就是一个字符对应一个数字），charArray[index] = 0,初始化都是0,
        同时有一个index标记，这个index用来表示如果字符x出现一次，那么charArray[x] = index，同时下一次这个字符x再出现,那么charArray[x] = -1;
        如果字符k在字符x后面，且出现了一次，由于index每次用完以后就+1，charArray[k] = index+1，这样就算k也出现了一次，但是我们可以利用index这个值，来确定谁先出现了，谁后出现了
        代码
public class Solution {
    //Insert one char from stringstream
    int [] charArray = new int[256];
    int index = 1;
    public void Insert(char ch)
    {
        if(charArray[(int)ch] == 0)
        {//刚出现一次
            charArray[(int)ch] = index;
            index++;//index++
        }else{
            charArray[(int)ch] = -1;//出现不止一次，-1
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        char result = '#';
        int min = Integer.MAX_VALUE;
        for(int i=0;i<256;i++)
        {
            if(charArray[i] > 0)
            {//说明这些字符是出现了一次的，我们要找的就是里面index最小的那个字符
                if(min > charArray[i])
                {
                    min = charArray[i];
                    result = (char)i;
                }
            }
        }
        return result;
    }
}

55.	链表中环的入口结点
        题目详述
        给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
        题目详解
        思路
        使用快慢指针，快指针每次走两步，慢指针每次走一步，如果快慢指针相遇说明有环；
        有环以后，需要寻找环入口节点，已经找到了一个环的中的节点，利用这个节点，去往下遍历，由于是环，所以这个节点肯定会和自身相遇，相遇以后，记录相遇过程中走的步数，就是环的长度
        知道环的长度以后，然后再利用快慢指针的思想，快指针先走环长度，然后快慢指针再一起走，这样因为快指针先走了环的长度，当两者相遇肯定是环的入口节点相遇（为啥呢，因为快慢指针肯定会进入环里面，而由于快指针先走了环的长度，所以也就是一个周期，所以只要进入环，那么这两个指针必然相遇）
        代码
/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {

    boolean flag = true;
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead == null || pHead.next == null)
            return null;
        ListNode pNode = judgeHasChain(pHead);//判断是否有环
        if(pNode != null)
        {
            int lengthChain = 1;
            ListNode pNodeCopy = pNode.next;
            while(pNodeCopy != pNode)
            {//这个节点已经在环里面了！
                lengthChain++;
                pNodeCopy = pNodeCopy.next;//计算环的长度
            }
            ListNode fast = pHead;
            ListNode slow = pHead;
            int temp = 0;
            while(temp < lengthChain)
            {//快指针先走环的长度
                fast = fast.next;
                temp++;
            }
            while(fast != slow)
            {//快慢指针一起走，因为快指针先走了环长度，当慢指针刚一进环，由于快指针刚好多走了环长度，所以快指针整好走了一个环的周期呀，所以快指针必然也在环的入口
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
        return null;
    }
    private ListNode judgeHasChain(ListNode pHead)
    {
        ListNode fast = pHead.next;
        ListNode slow = pHead;
        while(fast != slow)
        {
            if(fast != null && fast.next != null)
            {//快指针走一步
                fast = fast.next;
            }else{
                flag = false;
                break;
            }
            if(slow != null && slow.next != null)
            {//慢指针走一步
                slow = slow.next;
            }else{
                flag = false;
                break;
            }
            if(fast == slow)
            {//两者相等，说明相遇了，有环
                return fast;
            }else{
                fast = fast.next;//快指针多走一步
            }
        }
        if(flag)
            return fast;
        return null;
    }
}

56.	删除链表中重复的结点
        题目描述
        在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
        1.	public ListNode deleteDuplication(ListNode pHead){
        2.
        3.	}
        解析
        此题处理起来棘手的有两个地方：
        1.
        如果某个结点的后继结点与其重复，那么删除该结点的一串连续重复的结点之后如何删除该结点本身，这就要求我们需要保留当前遍历结点的前驱指针。
        但是如果从头结点开始就出现一连串的重复呢？我们又如何删除删除头结点，因此我们需要新建一个辅助结点作为头结点的前驱结点。

        2.
        在遍历过程中如何区分当前结点是不重复的结点，还是在删除了它的若干后继结点之后最后也要删除它本身的重复结点？这就需要我们使用一个布尔变量记录是否开启了删除模式（ deleteMode）

        经过上述两步分析，我们终于可以安心遍历结点了：
public ListNode deleteDuplication(ListNode pHead){
        if(pHead == null){
        return null;
        }
        ListNode node = new ListNode(Integer.MIN_VALUE);
        node.next = pHead;
        ListNode pre = node, p = pHead;
        boolean deletedMode = false;
        while(p != null){
        if(p.next != null && p.next.val == p.val){
        p.next = p.next.next;
        deletedMode = true;
        }else if(deletedMode){
        pre.next = p.next;
        p = pre.next;
        deletedMode = false;
        }else{
        pre = p;
        p = p.next;
        }
        }

        return node.next;
        }
        57.	二叉树的下一个结点
        26)	题目详述
        给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
        27)	题目详解
        思路
        分析二叉树的下一个节点，一共有以下情况： 1.二叉树为空，则返回空； 2.节点右孩子存在，则设置一个指针从该节点的右孩子出发，一直沿着指向左子结点的指针找到的叶子节点即为下一个节点； 3.节点不是根节点。如果该节点是其父节点的左孩子，则返回父节点；否则继续向上遍历其父节点的父节点，重复之前的判断，返回结果。代码如下：
        代码
/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode == null)
            return null;
        if(pNode.right != null)
        {//如果右子树不为空，那么中序遍历的下一个节点就是右子树的中的最左的那个节点
            TreeLinkNode temp = pNode.right;
            while(temp.left != null)
                temp = temp.left;
            return temp;
        }
        TreeLinkNode temp = pNode.next;//父节点
        TreeLinkNode pre = pNode;//当前节点
        while(temp != null)
        {
            if(temp.left == pre)
                return temp;//如果当前节点pre是父节点的左子树，那么父节点就是下一个节点，终止循环
            pre = temp;//如果不是，那么就pre指向父节点
            temp = temp.next;//父节点指向自己的父节点，也就是向上一层移动
        }
        return temp;
    }
}

58.	对称的二叉树
        28)	题目详述
        请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
        29)	题目详解
        思路
        对于一棵二叉树，从根结点开始遍历， 如果左右子结点有一个为NULL，那么肯定不是对称二叉树； 如果左右子结点均不为空，但不相等，那么肯定不是对称二叉树； 如果左右子结点均不为空且相等，那么 遍历左子树，遍历顺序为：当前结点，左子树，右子树； 遍历右子树，遍历顺序为：当前结点，右子树，左子树； 如果遍历左子树的序列和遍历右子树的序列一样，那么该二叉树为对称的二叉树。
        代码
/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    boolean isSymmetrical(TreeNode pRoot)
    {
        return judge(pRoot,pRoot);
    }
    boolean judge(TreeNode pRoot1,TreeNode pRoot2)
    {
        if(pRoot1 == null && pRoot2 == null)
            return true;//左右子树都是null，是true
        if(pRoot1 == null || pRoot2 == null)
            return false;//左右子树一个为null一个不为空，那么肯定不是对称的
        if(pRoot1.left != null && pRoot2.right != null && pRoot1.left.val != pRoot2.right.val)
            return false;//左右子树不为null，但val值不相等，不对称
        if(pRoot1.right != null && pRoot2.left != null && pRoot1.right.val != pRoot2.left.val)
            return false;//右 左子树不为null，但val值不相等，不对称
        return judge(pRoot1.left,pRoot2.right) && judge(pRoot1.right,pRoot2.left);//最后进行递归判断
    }
}
59.	按之字形顺序打印二叉树
        大家的实现很多都是将每层的数据存进ArrayList中，偶数层时进行reverse操作， 在海量数据时，这样效率太低了。 （我有一次面试，算法考的就是之字形打印二叉树，用了reverse， 直接被鄙视了，面试官说海量数据时效率根本就不行。） 下面的实现：不必将每层的数据存进ArrayList中，偶数层时进行reverse操作，直接按打印顺序存入 思路：利用Java中的LinkedList的底层实现是双向链表的特点。 1)可用做队列,实现树的层次遍历 2)可双向遍历,奇数层时从前向后遍历，偶数层时从后向前遍历
public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (pRoot == null) {
        return ret;
        }
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(null);//层分隔符
        queue.addLast(pRoot);
        boolean leftToRight = true;

        while (queue.size() != 1) {
        TreeNode node = queue.removeFirst();
        if (node == null) {//到达层分隔符
        Iterator<TreeNode> iter = null;
        if (leftToRight) {
        iter = queue.iterator();//从前往后遍历
        } else {
        iter = queue.descendingIterator();//从后往前遍历
        }
        leftToRight = !leftToRight;
        while (iter.hasNext()) {
        TreeNode temp = (TreeNode)iter.next();
        list.add(temp.val);
        }
        ret.add(new ArrayList<Integer>(list));
        list.clear();
        queue.addLast(null);//添加层分隔符
        continue;//一定要continue
        }
        if (node.left != null) {
        queue.addLast(node.left);
        }
        if (node.right != null) {
        queue.addLast(node.right);
        }
        }

        return ret;
        }
        60.	把二叉树打印成多行
        题目详述
        从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
        题目详解
        思路
        层次遍历肯定想到队列啊，通过判断队列是否为空，来判断是否遍历完整颗树
        第一层到第二层的话，如何判断进入了下一层？这里利用一个临时变量teBePrinted,比如第一层肯定只有根节点这一个节点啊，那么初始toBEpRrinted = 1 然后每当队列出去一个节点，teBePrinted就减一，当teBePrinted为0的时候就进入到下一层了；
        再次进入下一层，肯定有人要问，那么下一层的teBePrinted的这个值如何计算呢？这个就是每当上一层的节点出队，比如根节点出队root出队了，然后只要还没进入到下一层，那么就去判断这个出队节点root的左右节点left和right是不是等于null，只要不为null那么我们就记录一下这个不为null的节点数目，这样就把下一层的节点数目记录下来了！
        代码
        import java.util.*;

/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<Integer> layer = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if(pRoot == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);//头结点入队
        int nextLevelNumber = 0;//下一层的节点数目
        int toBePrint = 1;//当前层中要打印的节点
        while(!queue.isEmpty())
        {
            TreeNode printNode = queue.poll();
            layer.add(printNode.val);//layer是这一层的节点数组
            if(printNode.left != null)
            {//左子树入队，同时下一层的节点数目+1
                queue.add(printNode.left);
                nextLevelNumber++;
            }
            if(printNode.right != null)
            {//右子树入队，同时下一层的节点数目+1
                queue.add(printNode.right);
                nextLevelNumber++;
            }
            toBePrint--;//因为当前的节点入队了，所以已经遍历完了，要打印的节点就减少了一个
            if(toBePrint == 0)
            {//当前的层次的节点都遍历完了
                result.add(new ArrayList<Integer>(layer));//把当前层的节点保留下来
                layer.clear();//清空当前层节点，为了保留下一层的节点继续重复使用
                toBePrint = nextLevelNumber;//把之前统计的下一层节点赋值到要打印的节点上
                nextLevelNumber = 0;//同时，下一层要打印的节点从0开始。
            }
        }
        return result;
    }

}
61.	序列化二叉树
        题目描述
        请实现两个函数，分别用来序列化和反序列化二叉树
        解析
        怎么序列化的，就怎么反序列化。这里 deserialize反序列化时对于序列化到 String[]arr的哪个结点值来了的变量 index有两个坑（都是笔者亲自踩的）：
        将 index声明为成员的 int， Java中函数调用时不会改变基本类型参数的值的，因此不要企图使用 int表示当前序列化哪个结点的值来了
        之后笔者想用 Integer代替，但是 Integer和 String一样，都是不可变对象，所有的值更改操作在底层都是拆箱和装箱生成新的 Integer，因此也不要使用 Integer做序列化到哪一个结点数值来了的计数器
        最好使用数组或者自定义的类（在类中声明一个 int变量）
        String Serialize(TreeNode root) {
        if(root == null){
        return "#_";
        }
        //处理头结点、左子树、右子树
        String res = root.val + "_";
        res += Serialize(root.left);
        res += Serialize(root.right);
        return res;
        }

        TreeNode Deserialize(String str) {
        if(str == null || str.length() == 0){
        return null;
        }
        Integer index = 0;
        return deserialize(str.split("_"), new int[]{0});
        }

//怎么序列化的，就怎么反序列化
        TreeNode deserialize(String[] arr, int[] index){
        if("#".equals(arr[index[0]])){
        index[0]++;
        return null;
        }
        //头结点、左子树、右子树
        TreeNode root = new TreeNode(Integer.parseInt(arr[index[0]]));
        index[0]++;
        root.left = deserialize(arr, index);
        root.right = deserialize(arr, index);
        return root;
        }

        62.	二叉搜索树的第k个结点
        30)	题目详述
        给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8） 中，按结点数值大小顺序第三小结点的值为4。
        31)	题目详解
        思路
//思路：二叉搜索树按照中序遍历的顺序打印出来正好就是排序好的顺序。 // 所以，按照中序遍历顺序找到第k个结点就是结果。
        代码
/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    int count = 0;
    TreeNode resultNode = null;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        if(pRoot == null || k <= 0)
            return null;
        KthNodeCore(pRoot,k);
        return resultNode;
    }
    void KthNodeCore(TreeNode pRoot,int k)
    {//本质上是一个中序遍历
        if(pRoot == null)
            return;
        if(pRoot.left != null)
            KthNodeCore(pRoot.left,k);
        if(pRoot != null)
        {
            count++;//count用来记录遍历到第几个数了
            if(count == k)
            {//如果遍历到第k个数，那么保留下来。
                resultNode = pRoot;
            }
        }
        if(pRoot.right != null)
            KthNodeCore(pRoot.right,k);
    }

}
63.	数据流的中位数
        题目描述
        如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
public void Insert(Integer num) {

        }

public Double GetMedian() {

        }
        解析
        由于中位数只与排序后位于数组中间的一个数或两个数相关，而与数组两边的其它数无关，因此我们可以用一个大根堆保存数组左半边的数的最大值，用一个小根堆保存数组右半边的最小值，插入元素 O(logn)，取中位数 O(1)。
public class Solution {

    //小根堆、大根堆
    PriorityQueue<Integer> minHeap = new PriorityQueue(new MinRootHeadComparator());
    PriorityQueue<Integer> maxHeap = new PriorityQueue(new MaxRootHeadComparator());
    int count = 0;

    class MaxRootHeadComparator implements Comparator<Integer>{
        //返回值大于0则认为逻辑上i2大于i1（无关对象包装的数值）
        public int compare(Integer i1, Integer i2){
            return i2.intValue() - i1.intValue();
        }
    }

    class MinRootHeadComparator implements Comparator<Integer>{
        public int compare(Integer i1, Integer i2){
            return i1.intValue() - i2.intValue();
        }
    }

    public void Insert(Integer num) {
        count++;//当前这个数是第几个进来的
        //编号是奇数就放入小根堆（右半边），否则放入大根堆
        if(count % 2 != 0){
            //如果要放入右半边的数比左半边的最大值要小则需调整左半边的最大值放入右半边并将当前这个数放入左半边，这样才能保证右半边的数都比左半边的大
            if(maxHeap.size() > 0 && num < maxHeap.peek()){
                maxHeap.add(num);
                num = maxHeap.poll();
            }
            minHeap.add(num);
        }else{
            if(minHeap.size() > 0 && num > minHeap.peek()){
                minHeap.add(num);
                num = minHeap.poll();
            }
            maxHeap.add(num);
        }
    }

    public Double GetMedian() {
        if(count == 0){
            return 0.0;
        }
        if(count % 2 != 0){
            return minHeap.peek().doubleValue();
        }else{
            return (minHeap.peek().doubleValue() + maxHeap.peek().doubleValue()) / 2;
        }
    }
}

64.	滑动窗口的最大值
        题目描述
        给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
        1.	public ArrayList<Integer> maxInWindows(int [] num, int size){
        2.
        3.	}
        解析
        使用一个单调非增队列，队头保存当前窗口的最大值，后面保存在窗口移动过程中导致队头失效（出窗口）后的从而晋升为窗口最大值的候选值。
public ArrayList<Integer> maxInWindows(int [] num, int size){
        ArrayList<Integer> res = new ArrayList();
        if(num == null || num.length == 0 || size <= 0 || size > num.length){
        return res;
        }

        //用队头元素保存窗口最大值，队列中元素只能是单调递减的，窗口移动可能导致队头元素失效
        LinkedList<Integer> queue = new LinkedList();
        int start = 0, end = size - 1;
        for(int i = start ; i <= end ; i++){
        addLast(queue, num[i]);
        }
        res.add(queue.getFirst());
        //移动窗口
        while(end < num.length - 1){
        addLast(queue, num[++end]);
        if(queue.getFirst() == num[start]){
        queue.pollFirst();
        }
        start++;
        res.add(queue.getFirst());
        }

        return res;
        }

public void addLast(LinkedList<Integer> queue, int num){
        if(queue == null){
        return;
        }
        //加元素之前要确保该元素小于等于队尾元素
        while(queue.size() != 0 && num > queue.getLast()){
        queue.pollLast();
        }
        queue.addLast(num);
        }
        65.	矩形中的路径
        题目描述
        请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
        解析
        定义一个黑盒 hasPathCorechar(matrix,rows,cols,inti,intj,str,index)，表示从 rows行 cols列的矩阵 matrix中的 (i,j)位置开始走是否能走出一条与 str的子串 index~str.length-1相同的路径。那么对于当前位置 (i,j)，需要关心的只有一下三点：
        1.	(i,j)是否越界了
        2.	(i,j)上的字符是否和 str[index]匹配
        3.	(i,j)是否已在之前走过的路径上
        如果通过了上面三点检查，那么认为 (i,j)这个位置是可以走的，剩下的就是 (i,j)上下左右四个方向能否走出 str的 index+1~str.length-1，这个交给黑盒就好了。
        还有一点要注意，如果确定了可以走当前位置 (i,j)，那么需要将该位置的 visited标记为 true，表示该位置在已走过的路径上，而退出 (i,j)的时候（对应下面第 32行）又要将他的 visited重置为 false。
public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        if(matrix == null || matrix.length != rows * cols || str == null){
        return false;
        }
        boolean[] visited = new boolean[matrix.length];
        for(int i = 0 ; i < rows ; i++){
        for(int j = 0 ; j < cols ; j++){
        //以矩阵中的每个点作为起点尝试走出str对应的路径
        if(hasPathCore(matrix, rows, cols, i, j, str, 0, visited)){
        return true;
        }
        }
        }
        return false;
        }

//当前在矩阵的(i,j)位置上
//index -> 匹配到了str中的第几个字符
private boolean hasPathCore(char[] matrix, int rows, int cols, int i, int j,
        char[] str, int index, boolean[] visited){
        if(index == str.length){
        return true;
        }
        //越界或字符不匹配或该位置已在路径上
        if(!match(matrix, rows, cols, i, j, str[index]) || visited[i * cols + j] == true){
        return false;
        }
        visited[i * cols + j] = true;
        boolean res = hasPathCore(matrix, rows, cols, i + 1, j, str, index + 1, visited) ||
        hasPathCore(matrix, rows, cols, i - 1, j, str, index + 1, visited) ||
        hasPathCore(matrix, rows, cols, i, j + 1, str, index + 1, visited) ||
        hasPathCore(matrix, rows, cols, i, j - 1, str, index + 1, visited);
        visited[i * cols + j] = false;
        return res;
        }

//矩阵matrix中的(i,j)位置上是否是c字符
private boolean match(char[] matrix, int rows, int cols, int i, int j, char c){
        if(i < 0 || i > rows - 1 || j < 0 || j > cols - 1){
        return false;
        }
        return matrix[i * cols + j] == c;
        }
        66.	机器人的运动范围
        题目描述
        地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
        解析
        定义一个黑盒 walk(intthreshold,introws,intcols,inti,intj,boolean[]visited)，它表示统计从 rows行 cols列的矩阵中的 (i,j)开始所能到达的格子并返回，对于当前位置 (i,j)有如下判断：
        (i,j)是否越界矩阵了
        (i,j)是否已被统计过了
        (i,j)的行坐标和列坐标的数位之和是否大于 k
        如果通过了上面三重检查，则认为 (i,j)是可以到达的（ res=1），并标记 (i,j)的 visited为 true表示已被统计过了，然后对 (i,j)的上下左右的格子调用黑盒进行统计。
        这里要注意的是，与上一题不同， visited不会在递归计算完子状态后被重置回 false，因为每个格子只能被统计一次。 visited的含义不一样
public int movingCount(int threshold, int rows, int cols){
        if(threshold < 0 || rows < 0 || cols < 0){
        return 0;
        }
        boolean[] visited = new boolean[rows * cols];
        return walk(threshold, rows, cols, 0, 0, visited);
        }

private int walk(int threshold, int rows, int cols, int i, int j, boolean[] visited){
        if(!isLegalPosition(rows, cols, i, j) || visited[i * cols + j] == true
        || bitSum(i) + bitSum(j) > threshold){
        return 0;
        }
        int res = 1;
        visited[i * cols + j] = true;
        res += walk(threshold, rows, cols, i + 1, j, visited) +
        walk(threshold, rows, cols, i - 1, j, visited) +
        walk(threshold, rows, cols, i, j + 1, visited) +
        walk(threshold, rows, cols, i, j - 1, visited);
        return res;
        }

private boolean isLegalPosition(int rows, int cols, int i, int j){
        if(i < 0 || j < 0 || i > rows - 1 || j > cols - 1){
        return false;
        }
        return true;
        }

public int bitSum(int num){
        int res = num % 10;
        while((num /= 10) != 0){
        res += num % 10;
        }
        return res;
        }


        leetcode
        67.	leetcode-81 在有重复数字的有序数组中寻找n
        假设按照升序排序的数组在预先未知的某个点上进行了旋转。
        ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
        编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
        示例 1:
        输入: nums = [2,5,6,0,0,1,2], target = 0输出: true
        示例 2:
        输入: nums = [2,5,6,0,0,1,2], target = 3输出: false
        进阶:
        •	这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
        •	这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？

class Solution {
 2    public boolean search(int[] nums, int target) {
        3        int left = 0;
        4        int right = nums.length - 1;
        5        while(left <= right)
            6        {
            7            int mid = left + (right-left)/2;
            8            if(nums[mid] == target || nums[left] == target || target == nums[right])
                9                return true;
            10            if(nums[mid] > nums[left])
                11            {
                12                if(target > nums[mid])
                    13                     left = mid + 1;
                14                else{
                    15                    if(target > nums[left])
                        16                        right = mid - 1;
                    17                    else
                    18                        left = mid + 1;
                    19                }
                20            }else if(nums[mid] < nums[left])
                21            {
                22                if(target < nums[mid])
                    23                    right = mid -1;
                24                else{
                    25                    if(target > nums[left])
                        26                        right = mid - 1;
                    27                    else
                    28                        left = mid + 1;
                    29                }
                30            }else{
                31                left++;//right--;
                32            }
            33        }
        34        return false;
        35    }
36}

68.	leetcode33 在旋转数组中查找一个数n
        假设按照升序排序的数组在预先未知的某个点上进行了旋转。
        ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
        搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
        你可以假设数组中不存在重复的元素。
        你的算法时间复杂度必须是 O(log n) 级别。
        示例 1:
        输入: nums = [4,5,6,7,0,1,2], target = 0输出: 4
        示例 2:
        输入: nums = [4,5,6,7,0,1,2], target = 3输出: -1

class Solution {
 2    public int search(int[] nums, int target) {
        3        if(nums.length == 0)
            4            return -1;
        5        int left = 0;
        6        int right = nums.length - 1;
        7        while(left <= right)
            8        {
            9            int mid = left + (right-left)/2;
            10            if(nums[mid] == target)
                11                return mid;
            12            else if(nums[left] == target)
                13                return left;
            14            else if(nums[right] == target)
                15                return right;
            16            else{
                17                if(target > nums[mid])
                    18                {//1
                    19                    if(nums[mid] > nums[0])
                        20                    {//2
                        21                        left = mid + 1;
                        22                    }else
                    23                    {
                        24                        if(target > nums[0])//3
                            25                            right = mid - 1;
                        26                        else
                        27                            left= mid + 1;
                        28                    }
                    29                }
                30                else{
                    31                    if(nums[mid] > nums[0])
                        32                    {//4
                        33                        if(target < nums[0])//5
                            34                            left = mid + 1;
                        35                        else
                        36                            right = mid - 1;
                        37                    }
                    38                    else{
                        39                        right = mid - 1;
                        40                    }
                    41                }
                42            }
            43        }
        44        return -1;
        45    }
46}

69.	leetcode905-按奇偶排序数组
        给出一个区间的集合，请合并所有重叠的区间。
        示例 1:
        输入: [[1,3],[2,6],[8,10],[15,18]]
        输出: [[1,6],[8,10],[15,18]]
        解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
        示例 2:
        输入: [[1,4],[4,5]]
        输出: [[1,5]]
        解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
        •	思路：就是使用一个新的数组B，去存，每次判断原来的数组是不是偶数，是偶数就存到前面，是奇数就存到后面
class Solution {
 2    public int[] sortArrayByParity(int[] A) {
        3        int i=0, j= A.length - 1;
        4        while(i < j)
            5        {
            6            if(A[i] % 2 == 1 && A[j] %2 == 0)
                7            {
                8                int temp = A[i];
                9                A[i] = A[j];
                10                A[j] = temp;
                11                i++;
                12                j--;
                13            }
            14            else if(A[i] % 2 == 0)
                15            {
                16                i++;
                17            }
            18            else if(A[j] % 2 == 1)
                19            {
                20                j--;
                21            }
            22        }
        23        return A;
        24    }
25}

70.	leetcode64-最小路径和
        给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
        说明：每次只能向下或者向右移动一步。
        示例:
        输入:
        [
        [1,3,1],
        [1,5,1],
        [4,2,1]
        ]
        输出: 7
        解释: 因为路径 1→3→1→1→1 的总和最小。

        思路
        •	对于矩阵中的任意一个位置，都可以通过这个位置的上一个位置或者左边的这一个位置移动一步 来到达这个位置（因为每次只能向下或者向右移动一步。）；
        •	对于矩阵中的任意一个位置（除去边界位置），dp(i,j) = min(dp(i-1,j),dp(i,j-1)) + grid(i,j)得到，dp(i-1,j)就是（i,j）左边的这一个点，dp(i,j-1)就是(i,j)的上一个点，比较这两个数的大小，取最小的加上grid（i,j）就是（i,j）这个位置的最小值
        •	边界情况单独考虑，对于第一行和第一列，第一行只能是由左边的一个位置移动得来，第一列只能由它上面的位置移动得到（因为每次只能向下或者向右移动一步。）；

class Solution {
 2    public int minPathSum(int[][] grid) {
        3        int rows = grid.length;
        4        int cols = 0;
        5        if(rows != 0)
            6            cols = grid[0].length;
        7        int [][] equation = new int[rows][cols];
        8        for(int i=0;i<rows;i++)
            9        {
            10            for(int j=0;j<cols;j++)
                11            {
                12                if(i == 0 && j ==0)
                    13                {
                    14                    equation[i][j] = grid[0][0];
                    15                }else if(i == 0)
                    16                {
                    17                    equation[i][j] = equation[i][j-1] + grid[i][j];
                    18                }else if(j == 0)
                    19                {
                    20                    equation[i][j] = equation[i-1][j] + grid[i][j];
                    21                }else{
                    22                    if(equation[i][j-1] < equation[i-1][j])
                        23                        equation[i][j] = equation[i][j-1] + grid[i][j];
                    24                    else
                    25                        equation[i][j] = equation[i-1][j] + grid[i][j];
                    26                }
                27            }
            28        }
        29        return equation[rows-1][cols-1];
        30    }
31}

71.	leetcode-125验证回文串
        给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
        说明：本题中，我们将空字符串定义为有效的回文串。
        示例 1:
        输入: "A man, a plan, a canal: Panama"
        输出: true
        示例 2:
        输入: "race a car"
        输出: false

        思路：
        •	首先把字母变成小写，然后利用ASC码把数字和小写字母找到，并加到新的字符串中
        •	然后对于这个新的字符串，一个指针在前，一个指针在后，依次比较两者是否相等；

class Solution {
 2    public boolean isPalindrome(String s) {
        3        s = s.toLowerCase();
        4        char [] charArray = s.toCharArray();
        5        String temp = "";
        6        for(int i=0;i<charArray.length;i++)
            7        {
            8            if( ((int)charArray[i] >= 48 && (int)charArray[i] <= 57) || ((int)charArray[i] >= 97 && (int)charArray[i] <= 122))
                9            {
                10                temp += charArray[i];
                11            }
            12        }
        13        char [] resultArray = temp.toCharArray();
        14        int begin = 0;int end = resultArray.length - 1;
        15        while(begin < end)
            16        {
            17            if(resultArray[begin] == resultArray[end])
                18            {
                19                begin++;
                20                end--;
                21            }else{
                22                return false;
                23            }
            24        }
        25        return true;
        26     }
27}

72.	leetcode387-字符串中第一个唯一的字符
        给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
        案例:
        s = "leetcode"
        返回 0.
        s = "loveleetcode",
        返回 2.
        注意事项：您可以假定该字符串只包含小写字母。
        思路
        •	由于只有26个小写字母，可以把用一个数组表示26个字母，数组长度26，第0位代表a,第1位代表b,….(这里利用ASC码进行转换）
        •	首先设置一个index=1,然后遍历字符串s,如果数组中比如对于字符串中a来说，也就是数组下标为0，array[0]如果字符a第一次出现，那么array[0] = index;然后index++;每第一次出现就把对应的数组下标赋值为index；
        •	如果已经出现了，那么array[i]必然是一个不等于0的大于0的数，那么就把array[i] = -1;
        •	一趟循环以后，遍历array数组，找到不等于-1的（出现两次及以上）不等于0的
        （要出现，等于0就是没出现），然后在里面找index数字最小的，因为在前面index每次都会进行自增，所以index最小的第一次出现的字符
class Solution {
    public int firstUniqChar(String s) {
        int index = 1;
        int [] array = new int [26];//数组
        for(int i=0;i<s.length();i++)
        {
            int cha  = (int)s.charAt(i) - 97;//根据ASC码 字符转数组下标
            if(array[cha] == 0)//说明第一次出现
            {
                array[cha] = index;//把index值赋值给他
                index++;//然后index务必+1，这样字符串中以后的字符就算第一次出现，也是index值比第一个第一次出现的index的值大的。
            }else{
                array[cha] = - 1;//如果已经出现了，那么就数组赋值为-1
            }
        }
        int resultIndex = Integer.MAX_VALUE;
        char result = '#';
        for(int i=0;i<array.length;i++)
        {//遍历数组，找到index最小的那个字符
            if(array[i] > 0)
            {
                if(array[i] < resultIndex)//每次都找较小的index的，因为index越小，在字符串的位置越靠前，也就是越靠近第一次出现的字符
                {
                    resultIndex = array[i];
                    result = (char)(i+97);
                }
            }
        }
        if(result == '#')
            return -1;//没找到，返回-1
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i) == result)
                return i;//因为要返回字符在字符串的下标，所以去字符串中去寻找字符的下标，然后返回。
        }
        return -1;
    }
}

73.	两数之和
        【Leetcode-easy-1】 Two Sum两数之和
        给定一个整数数组，找到两个数，使得它们的和等于一个特定的目标数。
        函数twoSum应返回两个数的索引（index），使它们加起来等于目标，其中索引1必须小于索引2。注意返回的答案（包括索引1和索引2 ）不是从零开始的。
        可以假设每个输入有且仅有一个答案。
        输入：数字 = {2,7,11,15}，目标 = 9
        输出：索引1 = 1，索引2 = 2

        思路：
        为了提高时间的复杂度，需要用空间来换，那么就是说只能遍历一个数字，那么另一个数字呢，我们可以事先将其存储起来，使用一个HashMap，来建立数字和其坐标位置之间的映射，我们都知道HashMap是常数级的查找效率，这样，我们在遍历数组的时候，用target减去遍历到的数字，就是另一个需要的数字了，直接在HashMap中查找其是否存在即可，注意要判断查找到的数字不是第一个数字，比如target是4，遍历到了一个2，那么另外一个2不能是之前那个2，整个实现步骤为：先遍历一遍数组，建立HashMap映射，然后再遍历一遍，开始查找，找到则记录index。

        import java.util.*;
public class Solution {
    /**
     * You may assume that each input would have exactly one solution
     *
     * use Map
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++){
            int remainder = target - nums[i];
            if(map.containsKey(nums[i]))
                return new int[]{map.get(nums[i]), i};
            map.put(remainder, i);
        }
        throw new IllegalArgumentException("no solution.");
    }
}
【Leetcode-medium-2】 Add Two Numbers 两个数字相加
        思路：建立一个新链表，然后把输入的两个链表从头往后撸，每两个相加，添加一个新节点到新链表后面，就是要处理下进位问题。还有就是最高位的进位问题要最后特殊处理一下。

/**
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * 使用dummyHead避免写重复的代码，非常巧妙
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);  // 第二个结点是链表的头结点
        int increment = 0;
        ListNode currNode = dummyHead;

        for (ListNode n1 = l1, n2 = l2; l1 != null || l2 != null;){
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            int result = x + y + increment;
            currNode.next = new ListNode(result % 10);
            increment = result / 10;
            currNode = currNode.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (increment == 1){
            currNode.next = new ListNode(1);
        }

        return dummyHead.next;
    }
}
74.	最长无重复字符的子串
        给定一个字符串，找到最长无重复字符的子串的长度。例如，"abcabcbb"的最长无重复字符的子串为"abc”，它的长度为3。"bbbbb”的最长子串为"b”，长度为1。
        【Leetcode-medium-3】 Longest Substring Without Repeating Characters 最长无重复字符的子串
/**
 * Given “abcabcbb”, the answer is “abc”, which the length is 3.
 * Given “bbbbb”, the answer is “b”, with the length of 1.
 *
 */
        import java.util.*;
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        StringBuilder sub = new StringBuilder(s.length());
        int fromIndex = 0;

        for (int i = 0; i < s.length(); i ++){
            char ch = s.charAt(i);

            int index = sub.indexOf(ch+"", fromIndex);  // 重复“字符”（字符串）的位置

            if (index != -1) fromIndex = index+1;  // 不断调整起始下标

            sub.append(ch);

            int len = sub.length() - fromIndex;  // 总长度 - 起始下标 = 当前子字符串的长度

            if (maxLen < len) maxLen = len;
        }

        return maxLen;
    }
}
75.	两个有序数组的中位数
        有两个有序数组A和B，大小分别为m和n。找到两个有序数组的中位数。总的运行时间复杂度应为O(log(m+n))。
        【Leetcode-hard-4】
        Median of Two Sorted Arrays 两个有序数组的中位数

        思路：限制了时间复杂度为O(log (m+n))，应该使用二分查找法来求解。难点在于要在两个未合并的有序数组之间使用二分法，这里我们需要定义一个函数来找到第K个元素，由于两个数组长度之和的奇偶不确定，因此需要分情况来讨论，对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。下面重点来看如何实现找到第K个元素，首先我们需要让数组1的长度小于或等于数组2的长度，那么我们只需判断如果数组1的长度大于数组2的长度的话，交换两个数组即可，然后我们要判断小的数组是否为空，为空的话，直接在另一个数组找第K个即可。还有一种情况是当K = 1时，表示我们要找第一个元素，只要比较两个数组的第一个元素，返回较小的那个即可。

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m < n) return findMedianSortedArrays(nums2, nums1);
        if (n == 0) return (nums1[(m - 1) / 2] + nums1[m / 2]) / 2.0;
        int left = 0, right = 2 * n;
        while (left <= right) {
            int mid2 = (left + right) / 2;
            int mid1 = m + n - mid2;
            double L1 = mid1 == 0 ? Double.MIN_VALUE : nums1[(mid1 - 1) / 2];
            double L2 = mid2 == 0 ? Double.MIN_VALUE : nums2[(mid2 - 1) / 2];
            double R1 = mid1 == m * 2 ? Double.MAX_VALUE : nums1[mid1 / 2];
            double R2 = mid2 == n * 2 ? Double.MAX_VALUE : nums2[mid2 / 2];
            if (L1 > R2) left = mid2 + 1;
            else if (L2 > R1) right = mid2 - 1;
            else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
        }
        return -1;
    }
}
76.	最长回文子串

        【Leetcode-medium-5】 Longest Palindromic Substring 最长回文串
/**
 * Input: “babad” Output: “bab”
 * Input: “cbbd” Output: “bb”
 */
class Solution {
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length()-1; i ++){
            int len1 = expandAroundCenter(s, i, i);  // 假设回文字符串的长度是奇数
            int len2 = expandAroundCenter(s, i, i+1);  // 假设回文字符串的长度是偶数
            int len = Math.max(len1, len2);
            // 边界判断
            if (len > end-start){
                start = i - (len-1)/2;  // 计算新的边界
                end = i + len/2;
            }
        }
        return s.substring(start, end+1);

    }

    // 从left,right向左右扩展
    // 双参数真是很巧妙，一直卡在这里了
    private int expandAroundCenter(String s, int left, int right){
        int L = left, R = right;
        for (; L >= 0 && R < s.length(); L --, R ++){
            if (s.charAt(L) != s.charAt(R))
                break;
        }
        return R-L-1;  // 根据example判断是否减去1
    }
}
77.	正则表达式匹配

        【Leetcode-hard-10】 Regular Expression Matching 正则表达式匹配
        Some examples:
        isMatch(“aa”,”a”) → false
        isMatch(“aa”,”aa”) → true
        isMatch(“aaa”,”aa”) → false
        isMatch(“aa”, “a*”) → true
        isMatch(“aa”, “.*”) → true
        isMatch(“ab”, “.*”) → true
        isMatch(“aab”, “c*a*b”) → true

        思路：用递归Recursion来解，大概思路如下
        若p为空，若s也为空，返回true，反之返回false
        若p的长度为1，若s长度也为1，且相同或是p为’.’则返回true，反之返回false
        若p的第二个字符不为*，若此时s为空返回false，否则判断首字符是否匹配，且从各自的第二个字符开始调用递归函数匹配
        若p的第二个字符为*，若s不为空且字符匹配，调用递归函数匹配s和去掉前两个字符的p，若匹配返回true，否则s去掉首字母
        返回调用递归函数匹配s和去掉前两个字符的p的结果

public class Solution {
    public boolean isMatch(String s, String p) {

        if(p.length() == 0)
            return s.length() == 0;

        //p's length 1 is special case
        if(p.length() == 1 || p.charAt(1) != '*'){
            if(s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
                return false;
            return isMatch(s.substring(1), p.substring(1));

        }else{
            int len = s.length();

            int i = -1;
            while(i<len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))){
                if(isMatch(s.substring(i+1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }
}
78.	装最多水的容器

        【Leetcode-medium-11】 Container With Most Water 装最多水的容器
        思路：
        我们认为长度越长且高度越大，则面积越大。
        现在，使用两个指针分别指向首、尾，这时它的宽度是最大的。
        可能还会出现面积更大的情况，只有当高度变大的时候，所以可以移动两个指针中的较小者，这样可以能会使高度增加以弥补长度变短造成面积减少的损失。
        一直移动两者中较小者，直到两者相遇，取各种情况的最大值即是最后的结果。

public int maxArea(int[] height){
        int maxarea = 0;
        int l = 0, r = height.length-1;
        while(l < r){
        int area = calArea(l, height[l], r, height[r]);
        if (area > maxarea) maxarea = area;

        if (height[l] <= height[r]) l ++;
        else                        r --;
        }
        return maxarea;
        }


private int calArea(int i, int h1, int j, int h2){
        return  Math.min(h1, h2) * (j-i);
        }
        79.	三数之和

        【Leetcode-medium-15】 3Sum 三数之和
        思路：
        我们对原数组进行排序，然后开始遍历排序后的数组，这里注意不是遍历到最后一个停止，而是到倒数第三个就可以了。这里我们可以先做个剪枝优化，就是当遍历到正数的时候就break，为啥呢，因为我们的数组现在是有序的了，如果第一个要fix的数就是正数了，那么后面的数字就都是正数，就永远不会出现和为0的情况了。然后我们还要加上重复就跳过的处理，处理方法是从第二个数开始，如果和前面的数字相等，就跳过，因为我们不想把相同的数字fix两次。对于遍历到的数，用0减去这个fix的数得到一个target，然后只需要再之后找到两个数之和等于target即可。我们用两个指针分别指向fix数字之后开始的数组首尾两个数，如果两个数和正好为target，则将这两个数和fix的数一起存入结果中。然后就是跳过重复数字的步骤了，两个指针都需要检测重复数字。如果两数之和小于target，则我们将左边那个指针i右移一位，使得指向的数字增大一些。同理，如果两数之和大于target，则我们将右边那个指针j左移一位，使得指向的数字减小一些。

public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        ArrayList<Integer> temp = null;
        for (int i = 0; i < nums.length; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;  //选定nums[i]为第一个数，并去重
        int left = i + 1;
        int right = nums.length - 1;
        while (right > left) {
        int sum = nums[i] + nums[left] + nums[right];
        if (sum == 0) {
        temp = new ArrayList<Integer>();
        temp.add(nums[i]);
        temp.add(nums[left]);
        temp.add(nums[right]);
        result.add(temp);
        while (left < right && nums[left] == nums[left + 1]) left++;  //去重
        while (left + 1 < right && nums[right] == nums[right - 1]) right--;
        }
        if (sum <= 0) left++;
        else if (sum >= 0) right--;
        }
        }
        return result;
        }
        80.	验证括号

        【Leetcode-Easy-20】 Valid Parentheses 验证括号
        思路：需要用一个栈，我们开始遍历输入字符串，如果当前字符为左半边括号时，则将其压入栈中，如果遇到右半边括号时，若此时栈为空，则直接返回false，如不为空，则取出栈顶元素，若为对应的左半边括号，则继续循环，反之返回false

class Solution {
    public boolean isValid(String s) {
        LinkedList<String> stack  = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        set.add("(");
        set.add("[");
        set.add("{");
        for (int i = 0; i < s.length(); i ++){
            String part = s.charAt(i) + "";
            if (set.contains(part)) stack.push(part);
            else{
                if (stack.isEmpty()) return false;
                String prepart = stack.pop();
                if ("}".equals(part) && !"{".equals(prepart) ||
                        ")".equals(part) && !"(".equals(prepart) ||
                        "]".equals(part) && !"[".equals(prepart)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

    //更为巧妙的解法
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

81.	合并两个有序链表
        【Leetcode-Easy-21】 Merge Two Sorted Lists
        思路：巧用头结点（哑结点）

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode currNode = dummyHead;


        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                currNode.next = l1;
                l1 = l1.next;
            }else{
                currNode.next = l2;
                l2 = l2.next;
            }
            currNode = currNode.next;
        }

        if (l1 != null) currNode.next = l1;
        if (l2 != null) currNode.next = l2;

        return dummyHead.next;
    }
}
82.	生成括号
        【Leetcode-Medium-22】 Generate Parentheses
        思路：
        回溯，这里的想法是只添加‘(’和‘)’，我们知道这将保证我们的解决方案(而不是添加1太多关闭)。
        一旦我们添加了一个‘(’然后我们将放弃它并尝试一个‘)’，它只能关闭一个有效的’(‘。这些步骤中的每一个都被递归地调用。

public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
        }


private void backtrack(List<String> list, String str, int open, int close, int max){
        if (str.length() == 2*max){
        list.add(str);
        return;
        }

        if (open < max)
        backtrack(list, str+"(", open+1, close, max);

        if (close < open)
        backtrack(list, str+")", open, close+1, max);
        }
        83.	合并k个有序链表
        【Leetcode-hard-23】 Merge k Sorted Lists 合并k个有序链表
        思路：
        这里需要用到分治法 Divide and Conquer Approach。简单来说就是不停的对半划分，比如k个链表先划分为合并两个k/2个链表的任务，再不停的往下划分，直到划分成只有一个或两个链表的任务，开始合并。举个例子来说比如合并6个链表，那么按照分治法，我们首先分别合并1和4,2和5,3和6。这样下一次只需合并3个链表，我们再合并1和3，最后和2合并就可以了。


        public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if(lists==null || lists.size()==0)
        return null;
        return helper(lists,0,lists.size()-1);
        }
        private ListNode helper(ArrayList<ListNode> lists, int l, int r)
        {
        if(l<r)
        {
        int m = (l+r)/2;
        return merge(helper(lists,l,m),helper(lists,m+1,r));
        }
        return lists.get(l);
        }
        private ListNode merge(ListNode l1, ListNode l2)
        {
        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        ListNode cur = dummy;
        while(l1!=null && l2!=null)
        {
        if(l1.val<l2.val)
        {
        l1 = l1.next;
        }
        else
        {
        ListNode next = l2.next;
        cur.next = l2;
        l2.next = l1;
        l2 = next;
        }
        cur = cur.next;
        }
        if(l2!=null)
        cur.next = l2;
        return dummy.next;
        }
        84.	最长有效括号
        【Leetcode-hard-32】 Longest Valid Parentheses 最长有效括号
        思路：
        借助栈来求解，需要定义个start变量来记录合法括号串的起始位置，我们遍历字符串，如果遇到左括号，则将当前下标压入栈，如果遇到右括号，如果当前栈为空，则将下一个坐标位置记录到start，如果栈不为空，则将栈顶元素取出，此时若栈为空，则更新结果和i - start + 1中的较大值，否则更新结果和i - 栈顶元素中的较大值

        public class Demo2 {
        public int longestValidParentheses(String s) {
        if (s == null || s.length() < 1)
        return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0, left = -1;
        for (int i = 0; i < s.length(); i++) {
        //如果遍历到左括号，压入堆栈
        if (s.charAt(i) == '(')
        stack.push(i);
        else {
        if (!stack.isEmpty()) {
        stack.pop();
        if (!stack.isEmpty())
        max = Math.max(max, i - stack.peek());
        else
        max = Math.max(max, i - left);
        } else
        //如果堆栈为空，说明当前的有括号无法配对，需要重新设置left的值
        left = i;
        }
        }
        return max;
        }
        }
        85.	在旋转有序数组中搜索
        【Leetcode-medium-33】 Search in Rotated Sorted Array 在旋转有序数组中搜索
        Suppose a sorted array is rotated at some pivot unknown to you beforehand.
        (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
        You are given a target value to search. If found in the array return its index, otherwise return -1.
        You may assume no duplicate exists in the array.

        思路：
        这道题让在旋转数组中搜索一个给定值，若存在返回坐标，若不存在返回-1。我们还是考虑二分搜索法，但是这道题的难点在于我们不知道原数组在哪旋转了，我们还是用题目中给的例子来分析，对于数组[0 1 2 4 5 6 7] 共有下列七种旋转方法：
        0　　1　　2　　 4　　5　　6　　7
        7　　0　　1　　 2　　4　　5　　6
        6　　7　　0　　 1　　2　　4　　5
        5　　6　　7　　 0　　1　　2　　4
        4　　5　　6　　7　　0　　1　　2
        2　　4　　5　　6　　7　　0　　1
        1　　2　　4　　5　　6　　7　　0
        二分搜索法的关键在于获得了中间数后，判断下面要搜索左半段还是右半段，我们观察上面红色的数字都是升序的，由此我们可以观察出规律，如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的，我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了

        public int search(int[] A, int target) {
        if(A==null || A.length==0)
        return -1;
        int l = 0;
        int r = A.length-1;
        while(l<=r)
        {
        int m = (l+r)/2;
        if(target == A[m])
        return m;
        if(A[m]<A[r])
        {
        if(target>A[m] && target<=A[r])
        l = m+1;
        else
        r = m-1;
        }
        else
        {
        if(target>=A[l] && target<A[m])
        r = m-1;
        else
        l = m+1;
        }
        }
        return -1;
        }
        86.	搜索一个范围

        【Leetcode-medium-34】 Search for a Range 搜索一个范围
        For example,
        Given [5, 7, 7, 8, 8, 10] and target value 8,
        return [3, 4].

        思路：
        只用两次二分查找。 如果我们不寻找那个元素先，而是直接相等的时候也向一个方向继续夹逼，如果向右夹逼，最后就会停在右边界，而向左夹逼则会停在左边界，如此用停下来的两个边界就可以知道结果了，只需要两次二分查找。

        public int[] searchRange(int[] A, int target) {
        int[] res = {-1,-1};
        if(A==null || A.length==0)
        {
        return res;
        }
        int ll = 0;
        int lr = A.length-1;
        while(ll<=lr)
        {
        int m = (ll+lr)/2;
        if(A[m]<target)
        {
        ll = m+1;
        }
        else
        {
        lr = m-1;
        }
        }
        int rl = 0;
        int rr = A.length-1;
        while(rl<=rr)
        {
        int m = (rl+rr)/2;
        if(A[m]<=target)
        {
        rl = m+1;
        }
        else
        {
        rr = m-1;
        }
        }
        if(ll<=rr)
        {
        res[0] = ll;
        res[1] = rr;
        }
        return res;
        }
        87.	搜索插入位置
        【Leetcode-easy-35】 Search Insert Position 搜索插入位置
        思路：
        二分查找。每次取中间，如果等于目标即返回，否则根据大小关系切去一半。因此算法复杂度是O(logn)，空间复杂度O(1)

        public int searchInsert(int[] A, int target) {
        if(A == null || A.length == 0)
        {
        return 0;
        }
        int l = 0;
        int r = A.length-1;
        while(l<=r)
        {
        int mid = (l+r)/2;
        if(A[mid]==target)
        return mid;
        if(A[mid]<target)
        l = mid+1;
        else
        r = mid-1;
        }
        return l;
        }
        88.	组合之和
        【Leetcode-easy-39】 Combination Sum 组合之和
        For example, given candidate set 2,3,6,7 and target 7,
        A solution set is:
        [7]
        [2, 2, 3]

        思路：
        NP问题，先排好序，然后每次递归中把剩下的元素一一加到结果集合中，并且把目标减去加入的元素，然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题。算法复杂度因为是NP问题，所以自然是指数量级的。

        public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(candidates == null || candidates.length==0)
        return res;
        Arrays.sort(candidates);
        helper(candidates,0,target,new ArrayList<Integer>(),res);
        return res;
        }
        private void helper(int[] candidates, int start, int target, ArrayList<Integer> item,
        ArrayList<ArrayList<Integer>> res)
        {
        if(target<0)
        return;
        if(target==0)
        {
        res.add(new ArrayList<Integer>(item));
        return;
        }
        for(int i=start;i<candidates.length;i++)
        {
        if(i>0 && candidates[i]==candidates[i-1])
        continue;
        item.add(candidates[i]);
        helper(candidates,i,target-candidates[i],item,res);
        item.remove(item.size()-1);
        }
        }

        *注意在实现中for循环中第一步有一个判断，那个是为了去除重复元素产生重复结果的影响，
        因为在这里每个数可以重复使用，所以重复的元素也就没有作用了，所以应该跳过那层递归。
        89.	字符串全排列
        【Leetcode-Medium-46】 Permutations 全排列
        置换实际上是给出所有的排列方式，同样是用深度优先搜索，不过为了避免重复选择的情况，我们要保证两点：第一，所有数必须是数组中的，第二，数组中每个数只能用不多于也不少于一次。如果我们要单独写一个函数，来判断下一轮搜索该选择哪一个数就很麻烦了。这里有一个技巧，我们可以只将数两两交换，不过交换时只能跟自己后面的交换。

        public class Solution {

        List<List<Integer>> res;
        boolean[] used;

        public List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<List<Integer>>();
        used = new boolean[nums.length];
        List<Integer> tmp = new LinkedList<Integer>();
        helper(nums, tmp);
        return res;
        }

        private void helper(int[] nums, List<Integer> tmp){
        if(tmp.size() == nums.length){
        List<Integer> list = new LinkedList<Integer>(tmp);
        res.add(list);
        } else {
        for(int idx = 0; idx < nums.length; idx++){
        // 遇到已经加过的元素就跳过
        if(used[idx]){
        continue;
        }
        // 加入该元素后继续搜索
        used[idx] = true;
        tmp.add(nums[idx]);
        helper(nums, tmp);
        tmp.remove(tmp.size()-1);
        used[idx] = false;
        }
        }
        }
        }
        90.	最大连续子数组的和
        【Leetcode-Easy-53】 Maximum Subarray 最大连续子数组的和
        思路：定义两个变量res和curSum，其中res保存最终要返回的结果，即最大的子数组之和.
        curSum初始值为0，每遍历一个数字num，比较curSum + num和num中的较大值存入curSum，
        然后再把res和curSum中的较大值存入res，以此类推直到遍历完整个数组，可得到最大子数组的值存在res

        class Solution {
        public int maxSubArray(int[] nums) {
        int res = nums[0];
        int curSum = 0;
        for (int num : nums){
        curSum = Math.max(num, num + curSum);
        res = Math.max(res, curSum);
        }
        return res;
        }
        }
        91.	爬楼梯，n阶1步2步
        【Leetcode-Easy-70】 Climbing Stairs 爬楼梯，n阶1步2步

        思路：斐波拉契，第n阶只与第 n - 1 阶和第 n - 2 阶有关，关系为ways[n] = ways[n - 1] + ways[n - 2]

// one
        public int climbStairs(int n) {
        if (n <= 2) return n;
        int result = 0;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i ++) {
        result = first + second;
        int temp = first;
        first = second;
        second = second + temp;
        }
        return result;
        }

// two
        public int climbStairs(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; ++i) {
        dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
        }

// three
        public int climbStairs(int n) {
        if (n <= 1) return n;
        int oneStep = 1; // 走一步的可能
        int twoStep = 1;  // 走两步的可能
        int res = 0;
        for (int i = 2; i <= n; i ++) {
        res = oneStep + twoStep;
        twoStep = oneStep;
        oneStep = res;
        }
        return res;
        }
        92.	二叉树的中序遍历
        【Leetcode-Medium-94】 Binary Tree Inorder Traversal 二叉树的中序遍历
        思路：
        - 递归方式：对左子结点调用递归函数，根节点访问值，右子节点再调用递归函数
        - 迭代方式：使用栈的解法，也是符合本题要求使用的解法之一，需要用栈来做，思路是从根节点开始，先将根节点压入栈，然后再将其所有左子结点压入栈，然后取出栈顶节点，保存节点值，再将当前指针移到其右子节点上，若存在右子节点，则在下次循环时又可将其所有左子结点压入栈中。这样就保证了访问顺序为左-根-右

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
        class Solution {
        // ----------------------------------------
        // 迭代方式
        public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode currNode = root;
        while (currNode != null || !stack.isEmpty()){
        while (currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
        }
        if (!stack.isEmpty()){
        currNode = stack.pop();
        res.add(currNode.val);
        currNode = currNode.right;
        }
        }
        return res;
        }


        // --------------------------------------
        // 递归方式
        public List<Integer> inorderTraversal0(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
        }

        private void helper2(TreeNode root, List<Integer> res){
        if (root == null) return;
        helper(root.left, res);
        res.add(root.val);
        helper2(root.right, res);
        }
        }
        93.	判断对称树
        第101-200题
        【Leetcode-Easy-101】 Symmetric Tree 判断对称树
        思路：判断二叉树是否是对称树，比如有两个节点n1, n2，我们需要比较n1的左子节点的值和n2的右子节点的值是否相等，同时还要比较n1的右子节点的值和n2的左子结点的值是否相等，
        以此类推比较完所有的左右两个节点。我们可以用递归和迭代两种方法来实现，写法不同，但是算法核心都一样。

        /**
         * Definition for a binary tree node.
         * public class TreeNode {
         *     int val;
         *     TreeNode left;
         *     TreeNode right;
         *     TreeNode(int x) { val = x; }
         * }
         */
        class Solution {
        // 递归
        public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
        }

        private boolean isSymmetric(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;  // 两者只有一者为null，则返回false
        if (root1.val != root2.val) return false;  // 两者均不为null，但两者的值不相等
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
        }
        }
        94.	二叉树的最大深度
        【Leetcode-easy-104】 Maximum Depth of Binary Tree 二叉树的最大深度
        思路：
        - 递归：深度优先搜索DFS，递归的完美应用，跟求二叉树的最小深度问题原理相同
        层次遍历

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
        class Solution {
        // 递归 最大深度
        public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right)+1;
        }
        }
        95.	判断平衡二叉树
        【Leetcode-Easy-110】 Balanced Binary Tree 判断平衡二叉树
        思路：高度平衡二叉树是每一个节点的两个字数的深度差不能超过1，那么我们肯定需要一个求各个点深度的函数，然后对每个节点的两个子树来比较深度差，时间复杂度为O(NlgN)，

        /**
         * Definition for a binary tree node.
         * public class TreeNode {
         *     int val;
         *     TreeNode left;
         *     TreeNode right;
         *     TreeNode(int x) { val = x; }
         * }
         */
        class Solution {
        public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int l = depth(root.left);
        int r = depth(root.right);

        if (Math.abs(l-r) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
        }

        private int depth(TreeNode root){
        if (root == null) return 0;
        int l = depth(root.left);
        int r = depth(root.right);
        return Math.max(l, r) + 1;
        }
        }
        96.	二叉树的最小深度
        【Leetcode-easy-111】 Minimum Depth of Binary Tree 二叉树的最小深度
        思路：层次遍历二叉树

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
        class Solution {
        public int minDepth(TreeNode root) {
        if (root == null) return 0;
        // if (root.left == null || root.right == null) return 1;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()){
        level ++;
        int len = queue.size();
        for (int i = 0; i < len; i ++){
        TreeNode currNode = queue.poll();
        if (currNode.left == null && currNode.right == null)
        return level;
        if (currNode.left != null) queue.offer(currNode.left);
        if (currNode.right != null) queue.offer(currNode.right);
        }
        }
        return level;
        }
        }
        97.	买卖股票的最佳时间
        【Leetcode-Easy-121】 Best Time to Buy and Sell Stock 买卖股票的最佳时间
        思路：找出截止当前位置的最小值和截至当前位置的最大值，
        记录两者之差，保留最大的差值。

        class Solution {
        public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int profit = 0;
        int low = prices[0];
        for (int i = 0; i < prices.length; i ++){
        low = Math.min(low, prices[i]);
        profit = Math.max(profit, prices[i]-low);
        }
        return profit;
        }
        }
        98.	求二叉树的最大路径和
        【Leetcode-Hard-124】 Binary Tree Maximum Path Sum 求二叉树的最大路径和
        思路：递归，二叉树的最大路径和。
        路径，如果选择当前结点，而且其父结点被选择，则它的左右孩子结点最多只能选择一个。

        /**
         * Definition for a binary tree node.
         * public class TreeNode {
         *     int val;
         *     TreeNode left;
         *     TreeNode right;
         *     TreeNode(int x) { val = x; }
         * }
         */


/**
 *       -1
 *      /  |
 *     2  3
 *        / |
 *       -1  2
 *       /
 *      4
 */
        class Solution {

        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root){
        helper(root);
        return max;
        }

        public int helper(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        max = Math.max(max, left + right + root.val);
        return Math.max(left, right) + root.val;

        }
        }
        99.	数组中的单一数
        【Leetcode-easy-136】 Single Number 数组中的单一数
        思路：位运算——异或运算，任何整数和0异或结果是它本身，一个整数异或它本身结果等于0。
        可以进一步推出：一个整数异或另一个整数两次结果是它本身。根据这个特点，我们把数组中所有的数字都异或起来，
        则每对相同的数字都会得0，然后最后剩下来的数字就是那个只有1次的数字

        class Solution {
        public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums)
        res ^= num;
        return res;
        }
        }
        100.	单链表中的环
        【Leetcode-Easy-141】 Linked List Cycle 单链表中的环
        思路：典型快指针和慢指针，只需要设两个指针，一个每次走一步的慢指针和一个每次走两步的快指针，如果链表里有环的话，两个指针最终肯定会相遇。

        /**
         * Definition for singly-linked list.
         * class ListNode {
         *     int val;
         *     ListNode next;
         *     ListNode(int x) {
         *         val = x;
         *         next = null;
         *     }
         * }
         */
        public class Solution {
        public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode quick = head;
        ListNode slow = head;

        while (quick != null && slow != null){
        if (quick.next != null) quick = quick.next.next;
        else return false;
        slow = slow.next;
        if (quick == slow) return true;
        }

        return false;
        }
        }
        101.	最近最少使用页面置换缓存器
        【Leetcode-hard-146】 LRU Cache 最近最少使用页面置换缓存器
        思路：这道题让我们实现一个LRU缓存器，LRU是Least Recently Used的简写，就是最近最少使用的意思。那么这个缓存器主要有两个成员函数，get和put，
        其中get函数是通过输入key来获得value，如果成功获得后，这对(key, value)升至缓存器中最常用的位置（顶部），如果key不存在，则返回-1。
        而put函数是插入一对新的(key, value)，如果原缓存器中有该key，则需要先删除掉原有的，将新的插入到缓存器的顶部。如果不存在，则直接插入到顶部。
        若加入新的值后缓存器超过了容量，则需要删掉一个最不常用的值，也就是底部的值。具体实现时我们需要三个私有变量，cap,l和m，其中cap是缓存器的容量大小，
        l是保存缓存器内容的列表，m是哈希表，保存关键值key和缓存器各项的迭代器之间映射，方便我们以O(1)的时间内找到目标项。
        然后我们再来看get和put如何实现，get相对简单些，我们在m中查找给定的key，如果存在则将此项移到顶部，并返回value，若不存在返回-1。
        对于put，我们也是现在m中查找给定的key，如果存在就删掉原有项，并在顶部插入新来项，然后判断是否溢出，若溢出则删掉底部项(最不常用项)。

        class LRUCache {

        private int cap = 10;
        // value的类型是Node，因为在后面会根据结点获取key，所以不能简单地将value的类型定义为V
        private HashMap<K, Node> map;  // 保证访问结点的速度为O(1)
        private Node dummyNode;  // 双向循环链表的头结点


        private class Node{
        int key;
        int val;
        Node next;
        Node prev;

        public Node(){}
        public Node(int key, int val){
        this.key = key;
        this.val = val;
        }
        }

        public LRUCache(int capacity) {
        this.cap = capacity;
        this.dummyHead = new Node();
        this.dummyHead.next = this.dummyHead;
        this.dummyHead.prev = this.dummyHead;
        }


        public int get(int key) {
        Node node = map.get(key);
        int result = -1;
        if (node != null){
        result = node.val;
        // 删除当前结点并将其插入到链表头部
        moveToHead(node);
        }
        return result;
        }


        private void moveToHead(Node node){
        deleteThisNode(node);
        insertIntoHead(node);
        }


        // 在双向循环量表中删除一个结点（结点个数大于等于2，也即不考虑只有一个dummyHead结点的情况）
        private void deleteThisNode(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        node.prev = null;
        node.next = null;
        }
        102.	计算逆波兰表达式
        【Leetcode-medium-150】 Evaluate Reverse Polish Notation 计算逆波兰表达式
        思路：这道题应该是栈的完美应用啊，从前往后遍历数组，遇到数字则压入栈中，遇到符号，则把栈顶的两个数字拿出来运算，把结果再压入栈中，直到遍历完整个数组，栈顶数字即为最终答案

        Evaluate the value of an arithmetic expression in Reverse Polish Notation.
        Valid operators are +, -, *, /. Each operand may be an integer or another expression.
        Some examples:
        ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
        ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
        1
        2
        3
        4
        5
        import java.util.*;
        class Solution {
        public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0)
        throw new RuntimeException("illegal argument exception.");
        LinkedList<Integer> stack = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");

        for (String token : tokens){
        if (set.contains(token)){
        int num2 = Integer.valueOf(stack.pop());
        int num1 = Integer.valueOf(stack.pop());
        switch(token){
        case "+" :
        stack.push(num1 + num2);
        break;
        case "-" :
        stack.push(num1 - num2);
        break;
        case "*" :
        stack.push(num1 * num2);
        break;
        case "/" :
        stack.push(num1 / num2);
        break;
        }
        }else{
        stack.push(Integer.valueOf(token));
        }
        }
        return stack.peek();
        }
        }
        103.	最小栈
        【Leetcode-Easy-155】 Min Stack 最小栈
        Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
        push(x) -- Push element x onto stack.
        pop() -- Removes the element on top of the stack.
        top() -- Get the top element.
        getMin() -- Retrieve the minimum element in the stack.
        1
        2
        3
        4
        5
        思路：双栈
        这道最小栈跟原来的栈相比就是多了一个功能，可以返回该栈的最小值。使用两个栈来实现，一个栈来按顺序存储push进来的数据，另一个用来存出现过的最小值。

        class MinStack {

        private LinkedList<Integer> dataStack = null;
        private LinkedList<Integer> minStack = null;

        /** initialize your data structure here. */
        public MinStack() {
        dataStack = new LinkedList<>();
        minStack = new LinkedList<>();
        }

        public void push(int x) {
        dataStack.push(x);
        if (minStack.isEmpty()) minStack.push(x);
        else minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
        if(!minStack.isEmpty()) minStack.pop();
        if(!dataStack.isEmpty()) dataStack.pop();
        }

        public int top() {
        return dataStack.peek();
        }

        public int getMin() {
        return minStack.peek();
        }
        }
        104.	求两个链表的交点
        【Leetcode-Easy-160】 Intersection of Two Linked Lists 求两个链表的交点
        For example, the following two linked lists:

        A:          a1 → a2
        ↘
        c1 → c2 → c3
        ↗
        B:     b1 → b2 → b3
        begin to intersect at node c1.

        Notes:
        Your code should preferably run in O(n) time and use only O(1) memory.
        1
        2
        3
        4
        5
        6
        7
        8
        9
        10
        11
        思路：
        链表的双指针应用。计算两条链表的长度； 使用两个指针“右对齐”两个链表； 查找相同的结点，如果两个链长度相同的话，那么对应的一个个比下去就能找到，所以只需要把长链表变短即可。具体算法为：分别遍历两个链表，得到分别对应的长度。然后求长度的差值，把较长的那个链表向后移动这个差值的个数，然后一一比较即可。

        /**
         * Definition for singly-linked list.
         * public class ListNode {
         *     int val;
         *     ListNode next;
         *     ListNode(int x) {
         *         val = x;
         *         next = null;
         *     }
         * }
         */
        public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = lenOfLinkedList(headA);
        int len2 = lenOfLinkedList(headB);
        // "右对齐"两条链表
        int diff = len1 - len2;
        if (diff < 0){
        diff = - diff;
        while (diff > 0){
        headB = headB.next;
        diff --;
        }
        }else{
        while (diff > 0){
        headA = headA.next;
        diff --;
        }
        }
        // 查找两条链表的公共节点
        while (headA != null && headB != null){
        if (headA == headB){
        return headA;
        }
        headA = headA.next;
        headB = headB.next;
        }
        return null;
        }

        private int lenOfLinkedList(ListNode head){
        int len = 0;
        while (head != null){
        len ++;
        head = head.next;
        }
        return len;
        }
        }
        【Leetcode-Easy-169】 Majority Element
        思路
        充分使用数据的特征
        1
        2
        class Solution {
        public int majorityElement(int[] nums) {
        int counter = 0;
        int curr = nums[0];
        for (int i = 0; i < nums.length; i ++){
        if (counter == 0) curr = nums[i];

        if (nums[i] == curr) counter ++;
        else{
        counter --;
        if (counter <= 0) counter = 0;
        }
        }
        return curr;
        }
        }
        105.	打家劫舍
        【Leetcode-Easy-198】 House Robber
        思路
        两种状态，抢或不抢，使用列长为2的二维数组表示。
        1
        2
        class Solution {
        public int rob(int[] nums) {
        int[][] dp = new int[nums.length+1][2];
        // dp[i][1] means we rob the current house and dp[i][0] means we don't,

        for (int i = 1; i < dp.length; i ++){
        dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);  // 不抢
        dp[i][1] = dp[i-1][0] + nums[i-1];   // 抢
        }
        return Math.max(dp[nums.length][0], dp[nums.length][1]);
        }
        }
        106.	链表翻转
        第201-300题
        【Leetcode-Easy-206】 Reverse Linked List
        思路
        双指针
        1
        2
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
        class Solution {
        public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode first = head;
        first.next = null;
        ListNode second = head.next;

        while (second != null){
        ListNode tempNode = second.next;
        second.next = first;
        first = second;
        second = tempNode;
        }
        return first;
        }
        }
        107.	翻转二叉树
        【Leetcode-easy-226】 Invert Binary Tree
        递归

        和剑指offer 面试题19 二叉树的镜像基本一样。
        1
        2
        3
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
        class Solution {

        public TreeNode invertTree(TreeNode root) {
        mirrorTree(root);
        return root;
        }

        private void mirrorTree(TreeNode root) {
        if (root == null) return;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        mirrorTree(root.left);
        mirrorTree(root.right);
        }
        }
        108.	回文链表
        【Leetcode-Easy-234】 Palindrome Linked List
        思路
        双指针找到链表的中间结点；
        反转链表的后半部分；
        比较链表的前半部分和“后半部分”。
        1
        2
        3
        4
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
        class Solution {
        public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
        fast = fast.next.next;
        slow = slow.next;
        }

        // slow 是链表后半截的头指针
        if (fast != null) slow = slow.next;

        ListNode head1 = head;
        ListNode head2 = reverseList(slow); // 反转链表的后半截

        while (head1 != null && head2 != null){
        if (head1.val != head2.val) return false;

        head1 = head1.next;
        head2 = head2.next;
        }

        return true;

        }

        // 反转链表
        private ListNode reverseList(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode first = head;
        ListNode second = head.next;
        first.next = null;  // 表示链表末尾
        while (second != null){
        ListNode tempNode = second.next;
        second.next = first;
        first = second;
        second = tempNode;
        }
        return first;
        }
        }
        109.	除本身之外的数组之积
        【Leetcode-Medium-238】 Product of Array Except Self
        思路：寻找规律，
        将每因子分为两种部分，左半部分和右半部分。
        先计算左半部分的乘积，把结果存储到返回值中；
        然后循环计算右半部分的乘积，不需要额外的存储空间。

        class Solution {
    /*
    [1, 2, 3, 4]

    [-, 2, 3 ,4]
    [1, -, 3, 4]
    [1, 2, -, 4]
    [1, 2, 3, -]

    */
        public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];

        res[0] = 1;
        for (int i = 1; i < nums.length; i ++){
        res[i] = res[i-1] * nums[i-1];
        }

        int mul = 1;
        for (int j = nums.length-1; j > 0; j --){
        mul *= nums[j];
        res[j-1] *= mul;
        }

        return res;
        }
        }
        110.	移动零
        【Leetcode-Easy-283】 Move Zeroes 移动零
        思路：需要用两个指针，一个不停的向后扫，找到非零位置，然后和前面那个指针交换位置即可

// For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

        public void moveZeroes(int[] nums) {
        int pos = 0;
        for (int num : nums){
        if (num != 0) nums[pos++] = num;
        }

        while (pos < nums.length){
        nums[pos++] = 0;
        }
        }
        111.	计数位
        第301-400题
        【Leetcode-Medium338】 Counting Bits 计数位
        思路
        发现规律：
        An easy recurrence for this problem is f[i] = f[i / 2] + i % 2.

/**

 An easy recurrence for this problem is f[i] = f[i / 2] + i % 2.

 i % 2 == (i & 1), true

 */

        class Solution {
        public int[] countBits(int num) {
        int[] result = new int[num+1];
        for (int i = 1; i <= num; i ++)
        result[i] = result[i/2] + i % 2;
        return result;
        }
        }
        112.	找出数组中所有消失的数字
        第401-500题
        【Leetcode-easy-448】 Find All Numbers Disappeared in an Array 找出数组中所有消失的数字
        思路：充分使用数组长度和数组元素大小的关系，数组元素可以作为数组的下标使用

        public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i ++){
        int index = Math.abs(nums[i]) - 1;  // 元素关联的下标
        if (nums[index] > 0)  nums[index] = -nums[index]; // 元素标记下标对应数字是否出现
        }
        for (int j = 0; j < nums.length; j ++){
        if (nums[j] > 0) list.add(j+1);
        }
        return list;
        }
        113.	岛屿周长
        【Leetcode-easy-463】 Island Perimeter 岛屿周长
        Example:

        [[0,1,0,0],
        [1,1,1,0],
        [0,1,0,0],
        [1,1,0,0]]

        Answer: 16
        1
        2
        3
        4
        5
        6
        7
        8
        思路：扩展边界

        class Solution {
        public int islandPerimeter(int[][] grid) {

        int[][] newGrid = new int[grid.length+2][grid[0].length+2];

        int perimeter = 0;

        for (int i = 0; i < grid.length; i ++){
        for (int j = 0; j < grid[0].length; j ++){
        newGrid[i+1][j+1] = grid[i][j];
        }
        }

        for (int i = 1; i < newGrid.length-1; i ++){
        for (int j = 1; j < newGrid[0].length-1; j ++){
        if (newGrid[i][j] == 1){
        if (newGrid[i-1][j] == 0) perimeter ++;  // 若1的上边是0，则周长加1
        if (newGrid[i][j+1] == 0) perimeter ++;  // 若1的右边是0，则周长加1
        if (newGrid[i+1][j] == 0) perimeter ++;  // 若1的下边是0，则周长加1
        if (newGrid[i][j-1] == 0) perimeter ++;  // 若1的左边是0，则周长加1
        }
        }
        }
        return perimeter;
        }
        }
        114.	目标和
        【Leetcode-Medium-494】 Target Sum 目标和
        Example 1:
        Input: nums is [1, 1, 1, 1, 1], S is 3.
        Output: 5
        Explanation:

        -1+1+1+1+1 = 3
        +1-1+1+1+1 = 3
        +1+1-1+1+1 = 3
        +1+1+1-1+1 = 3
        +1+1+1+1-1 = 3
        1
        2
        3
        4
        5
        6
        7
        8
        9
        10
        思路：递归求解，加上当前数或者减去当前数

        class Solution {
        int counter = 0;
        public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, S, 0, 0);
        return counter;
        }

        private void calculate(int[] nums, int target, int i, int sum){
        if (i == nums.length){
        if (sum == target) counter ++;
        } else { // 有条件的递归
        calculate(nums, target, i+1, sum+nums[i]);  // 加上当前数字
        calculate(nums, target, i+1, sum-nums[i]);  // 减去当前数字
        }
        }
        }
        115.	二叉树的直径
        第501-600题
        【Leetcode-easy-543】 Diameter of Binary Tree 二叉树的直径
        Example:
        Given a binary tree

        1
        / \
        2   3
        / \
        4   5


        Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
        1
        2
        3
        4
        5
        6
        7
        8
        9
        10
        11
        思路：对于每一个结点，经过它的最长路径的长度 = 它的左子树的最大深度 + 右子树的最大深度。

        /**
         * Definition for a binary tree node.
         * public class TreeNode {
         *     int val;
         *     TreeNode left;
         *     TreeNode right;
         *     TreeNode(int x) { val = x; }
         * }
         */
        class Solution {

        int max = 0;

        public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
        }

        private int maxDepth(TreeNode root){
        if (root == null) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        max = Math.max(max, l+r);
        return Math.max(l, r) + 1;
        }
        }
        116.	朋友圈
        【Leetcode-Medium-547】 Friend Circles 朋友圈
        Input:
        [[1,1,0],
        [1,1,0],
        [0,0,1]]
        Output: 2
        1
        2
        3
        4
        5
        思路:深度优先搜索

        class Solution {
        public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int counter = 0;
        for (int i = 0; i < M.length; i ++){
        if (visited[i] == 0){
        dfs(M, i, visited);
        counter ++;
        }
        }
        return counter;
        }


        private void dfs(int[][] M, int i, int[] visited){
        for (int j = 0; j < M[i].length; j ++){
        // i 和 j 相连，而且j没有被访问过
        if (M[i][j] == 1 && visited[j] == 0){
        visited[j] = 1;
        dfs(M, j, visited);
        }
        }

        }
        }
        117.	另一个树的子树
        【Leetcode-easy-572】 Subtree of Another Tree 另一个树的子树
        Example 1:
        Given tree s:

        3
        / \
        4   5
        / \
        1   2
        Given tree t:

        4
        / \
        1   2
        Return true, because t has the same structure and node values with a subtree of s.


        Example 2:
        Given tree s:

        3
        / \
        4   5
        / \
        1   2
        /
        0
        Given tree t:

        4
        / \
        1   2
        Return false.
        从题目中的第二个例子中可以看出，子树必须是从叶结点开始的，中间某个部分的不能算是子树，那么我们转换一下思路，是不是从s的某个结点开始，跟t的所有结构都一样，那么问题就转换成了判断两棵树是否相同，也就是Same Tree的问题了，这点想通了其实代码就很好写了，用递归来写十分的简洁，我们先从s的根结点开始，跟t比较，如果两棵树完全相同，那么返回true，否则就分别对s的左子结点和右子结点调用递归再次来判断是否相同，只要有一个返回true了，就表示可以找得到

        class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
        boolean result = false;
        if (s != null && t != null){
        if (s.val == t.val) result = validate(s, t);
        if (!result) result = isSubtree(s.left, t);
        if (!result) result = isSubtree(s.right, t);
        }
        return result;
        }

        private boolean validate(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 == null) return true;
        // if (root1 == null) return false;
        if (root1 == null && root2 != null) return false;
        if (root1 != null && root2 == null) return false;
        if (root1.val != root2.val) return false;
        return validate(root1.left, root2.left) && validate(root1.right, root2.right);
        }
        }
        118.	最短无序连续子数组
        【Leetcode-Easy-581】 Shortest Unsorted Continuous Subarray 最短无序连续子数组
        思路
        发现规律：
        起始点的特征，右边存在小于其的元素；
        终止点的特征，左边存在大于其的元素。

        class Solution {

        // [2, 6, 4, 8, 10, 9, 15]
        // [6, 4, 8, 10, 9]
        // 起始点的特征，右边存在小于其的元素；
        // 终止点的特征，左边存在大于其的元素。


        public int findUnsortedSubarray(int[] nums) {

        int start = 0;
        int end = 0;

        int max = nums[0];  // 当前元素左边范围内的最大值，从前面往后面查找起始元素
        int min = nums[nums.length-1];  // 从后面往前查找终止元素


        // 从左往右遍历，如果A[i]小于左边所有元素的最大值，则其可能是右边界
        for (int i = 1; i < nums.length; i ++){
        max = Math.max(max, nums[i]);
        if (nums[i] < max) end = i;
        }


        // 从右往左遍历，如果A[j]大于右边所有元素的最小值，则其可能是左边界
        for (int j = nums.length-2; j >= 0; j --){
        min = Math.min(min, nums[j]);
        if (nums[j] > min) start = j;
        }

        return end == 0 ? 0 : end - start + 1;
        }



        // Time Limit Exceeded
        public int findUnsortedSubarray01(int[] nums) {

        int start = 0;
        int end = 0;

        outer:for (int i = 0; i < nums.length; i ++){
        for (int j = i+1; j < nums.length; j ++){
        if (nums[i] > nums[j]){  // 如果后面存在元素小于当前元素，则该元素需要排序
        start = i;
        break outer;
        }
        }
        }


        for (int i = start; i < nums.length; i ++){
        for (int j = i+1; j < nums.length; j ++){
        if (nums[i] > nums[j]){  // 如果后面存在元素小于当前元素，则该元素需要排序
        end = Math.max(end, j);
        }
        }
        }

        return end == 0 ? 0 : end - start + 1;
        }

        }
        119.	两个字符串的删除操作
        【Leetcode-Medium-583】 Delete Operation for Two Strings 两个字符串的删除操作
        Example 1:

        Input: "sea", "eat"
        Output: 2
        Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
        1
        2
        3
        4
        5
        class Solution {

        // 最长公共子序列，LCS
        public int minDistance(String word1, String word2) {

        int W = word1.length() + 1;
        int H = word2.length() + 1;

        int[][] states = new int[H][W];

        for (int i = 1; i < H; i ++){
        char ch1 = word2.charAt(i-1);
        for (int j = 1; j < W; j ++){
        char ch2 = word1.charAt(j-1);
        if (ch1 == ch2) states[i][j] = states[i-1][j-1] + 1;
        else states[i][j] = Math.max(states[i][j-1], states[i-1][j]);
        }
        }

        int maxLen = 0;
        for (int i = 0; i < H; i ++){
        for (int j = 0; j < W; j ++){
        maxLen = Math.max(maxLen, states[i][j]);
        }
        }

        return word1.length()+word2.length()-maxLen*2;
        }
        }
        120.	合并二叉树
        第601-700题
        【Leetcode-easy-617】 Merge Two Binary Trees 合并二叉树
        Input:
        Tree 1                     Tree 2
        1                         2
        / \                       / \
        3   2                     1   3
        /                           \   \
        5                             4   7
        Output:
        Merged tree:
        3
        / \
        4   5
        / \   \
        5   4   7


        Note: The merging process must start from the root nodes of both trees.
        这道题给了我们两个二叉树，让我们合并成一个，规则是，都存在的结点，就将结点值加起来，否则空的位置就由另一个树的结点来代替。那么根据过往经验，处理二叉树问题的神器就是递归，那么我们来看递归函数如何去写。根据题目中的规则，我们知道如果要处理的相同位置上的两个结点都不存在的话，直接返回即可，如果t1存在，t2不存在，那么我们就以t1的结点值建立一个新结点，然后分别对t1的左右子结点和空结点调用递归函数，反之，如果t1不存在，t2存在，那么我们就以t2的结点值建立一个新结点，然后分别对t2的左右子结点和空结点调用递归函数。如果t1和t2都存在，那么我们就以t1和t2的结点值之和建立一个新结点，然后分别对t1的左右子结点和t2的左右子结点调用递归函数

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
        class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
        }
        }
        121.	最长连续递增序列
        【Leetcode-Medium-647】 Palindromic Substrings 最长连续递增序列
        Input: [1,3,5,4,7]
        Output: 3
        Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
        Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.


        Example 2:

        Input: [2,2,2,2,2]
        Output: 1
        Explanation: The longest continuous increasing subsequence is [2], its length is 1.
        1
        2
        3
        4
        5
        6
        7
        8
        9
        10
        11
        思路：暴力验证

        class Solution {
        public int countSubstrings(String s) {
        int counter = 0;

        for (int i = 0; i < s.length(); i ++){
        for (int j = i+1; j < s.length()+1; j ++){
        String str = s.substring(i, j);
        if (isPalindromicString(str))
        counter ++;
        }
        }
        return counter;
        }


        private boolean isPalindromicString(String s){
        if (s.length() == 1) return true;

        boolean bool = true;
        for (int i = 0; i < s.length()/2; i ++){
        if (s.charAt(i) != s.charAt(s.length()-1-i)){
        bool = false;
        break;
        }
        }
        return bool;

        }
        }

        122.	字符串的全组合
        Public class Combination{
        Private StringBuilder out = new StringBuilder();
        Private final String in;
        Public Combination( final String str) { in = str;}
        Public void combine(int start){
        For(int i=0;i<in.length();i++){
        Out.append(in.charAt(i));
        System.out.println(out);
        If(I < in.length()){
        Combine(i+1);
        }
        Out.setLength(out.length() -1);
        }
        }
        }
*/