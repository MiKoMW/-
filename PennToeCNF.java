/**
 * Created by Mac on 2017/11/10.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class analysisPenn {

    public static void main(String[] args){
        String temp;
        temp = " (TOP (S (NP (DT The) (NN pain)) (VP (VBZ subsides)) (. .))) \n";
        StringTokenizer tok = new StringTokenizer(temp," ");
        int tokLevel = 0;

        ArrayList<String> out = new ArrayList<String>();
        HashMap<Integer,String> ans = new HashMap<Integer,String>();
        while(tok.hasMoreTokens()){

            String str = tok.nextToken();
            if(str.charAt(0) == '('){
                tokLevel++;
                String temp1 = str.substring(1);
                temp1 = temp1 + " -> ";
                ans.put(tokLevel,temp1);
                if(tokLevel > 1){
                    String temp2 = ans.get(tokLevel - 1);
                    temp2 = temp2 + " " +str.substring(1);
                    ans.put(tokLevel-1,temp2);
                }
                continue;
            }

            if (str.contains(")")){
                String temp1 = str.substring(0,str.indexOf(')'));
                String temp2 = ans.get(tokLevel);

                temp2 = temp2 + " " + temp1;
                out.add(temp2);
                tokLevel--;
                int con = str.lastIndexOf(')') - str.indexOf(')');
                while(con-- > 0 ){
                    out.add(ans.get(tokLevel));
                    tokLevel--;
                }

            }
        }

        for(String st:out){
            System.out.println(st);
        }








    }

}
