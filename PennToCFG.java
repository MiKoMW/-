/**
 * Created by Mac on 2017/11/10.
 */

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class analysisPenn {
    
    public static ArrayList<String> getGrammars(String penn){
        StringTokenizer tok = new StringTokenizer(penn," ");
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
        return out;
    }

    public static HashMap<String,Integer> countGrammar(ArrayList<String> gras){
        HashMap<String,Integer> ans = new HashMap<String,Integer>();
        for(String st:gras){

            if(ans.get(st) == null) {

                ans.put(st,1);
            }
            else{
                ans.put(st,ans.get(st)+1);
            }
        }



        return ans;

    }



    public static ArrayList<String> readLine(String filePath) throws IOException
    {
        Charset charset = Charset.forName("utf-8");
        FileInputStream fileStream = new FileInputStream(new File(filePath));
        BufferedReader buffer = new BufferedReader(new InputStreamReader(fileStream,"utf-8"));
        ArrayList<String> ans = new ArrayList<String>();
        String line;
        while ((line = buffer.readLine()) != null){
                ans.add(line);
        }
        return ans;
    }


    public static void writeLine(ArrayList<String> sts, String OutPutFile) throws IOException{

        FileWriter fileOutput = new FileWriter(OutPutFile);
        BufferedWriter bufferOut = new BufferedWriter(fileOutput);
        File fileOut = new File(OutPutFile);
        if (!fileOut.exists()) {
            try {
                fileOut.createNewFile(); // 文件的创建，注意与文件夹创建的区别
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            for(String st: sts){
                bufferOut.write(st);
                bufferOut.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            bufferOut.close();
        }
    }

    public static void main (String[] args){
        ArrayList<String> in = new ArrayList<String>();
        ArrayList<String> ans = new ArrayList<String>();
        HashMap<String,Integer> count = new HashMap<String,Integer>();
        ArrayList<String> out = new ArrayList<String>();


        try{
            in = readLine("treebank.in");}
        catch (IOException e){
            e.printStackTrace();
        }
        for(String st : in){
            ArrayList<String> temp = getGrammars(st);
            ans.addAll(temp);
        }
        count = countGrammar(ans);
        for(String st : count.keySet()){
            String temp1 = st + " " +count.get(st);
            out.add(temp1);
        }
        try{
            writeLine(out,"grammar.out");}
        catch (IOException ea){
            ea.printStackTrace();
        }
    }
    
}
