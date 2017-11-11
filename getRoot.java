/**
 * Created by Mac on 2017/8/6.
 * Calculate a Complete Binary Search Tree root index
 * @param num node number
 * @return root index
 */
public class getRoot {
    static int getRoot(int num){
        int n =1;
        while(n <= num){
            n = n<<1;
        }
        int m = n >> 1;
        int bottom = num - m + 1;
        int leftMaxbottom = m >> 1;
        if (bottom > leftMaxbottom){
            bottom = leftMaxbottom;
        }

        int index = bottom;
        if ( m > 1){
            index += ((m>>1) - 1);
        }
        return index;
    }
    public static void main(String[] args){
        int temp = getRoot(6);
        System.out.print(temp);
    }
}
