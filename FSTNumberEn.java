/**
 * Created by Mac on 2017/8/4.
 */
public class FSTNumberEn {

    final static int otherState = 1;
    final static int numberState = 2;
    final static int englishState = 3;
    final static char startChar = '0';

    int next[][];

    public void setTrans(int s, char c, int t){
        next[s-1][c-startChar] = t;
    }

    public FSTNumberEn(){
        next = new int[3][127];

        for(int i =(int) '0' ; i <= '9' ; ++i){
            setTrans(numberState,(char) i, numberState);
            setTrans(otherState,(char) i, numberState);
        }

        for(int i = (int) 'a'; i <= 'z'; ++i){
            setTrans(englishState,(char) i,englishState);
            setTrans(otherState,(char) i,englishState);
        }
        for(int i = (int) 'A'; i <= 'Z'; ++i){
            setTrans(englishState,(char) i,englishState);
            setTrans(otherState,(char) i,englishState);
        }
    }


    public int matchNumOrEn(String text, int offset){
        int s = otherState;
        int i = offset;
        while (i < text.length()){
            char c = text.charAt(i);
            int pos = c-startChar;
            if(pos>next[0].length){
                return i;
            }
            int t = next[s-1][pos];
            if(t==0){
                return i;
            }

            if(s != t && i > offset){
                return i;
            }
            i++;
            s=t;
        }
        return i;
    }

    public static void main(String[] args){

        FSTNumberEn temp = new FSTNumberEn();
        int ans = temp.matchNumOrEn("你好吗Miko1？", 3);
        System.out.print(ans);
    }

}