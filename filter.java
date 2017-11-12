import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Mac on 2017/11/11.
 */
public class filter {

    public static ArrayList<String> toCNF(String st) {
        ArrayList<String> rule = new ArrayList<String>();
        StringTokenizer thistok = new StringTokenizer(st, " ");
        ArrayList<String> cnf = new ArrayList<String>();

        int con = thistok.countTokens();
        int times = 0;
        if (thistok.hasMoreTokens()) {
            String str = thistok.nextToken();
            times = Integer.parseInt(str);
        }
        // System.out.print(times);
        if (thistok.hasMoreTokens()) {
            String str = thistok.nextToken();
            rule.add(str);
        }
        if (thistok.hasMoreTokens()) {
            String str = thistok.nextToken();
        }
        while (thistok.hasMoreTokens()) {
            String str = thistok.nextToken();
            rule.add(str);
        }

        int sub = rule.size();
        String temp = new String();

        //first grammar
        String left = rule.get(0);
        String right1 = rule.get(1);
        String right2 = new String();
        temp = new String();
        for (int b = 2; b < sub; b++) {
            right2 = right2 + rule.get(b);
        }
        temp = times + " " + left + "  " + right1 + " " + right2;

        //System.out.println(temp);

        cnf.add(temp);
        //cnf grammar
        for(int a=2 ; a < sub -1; a ++){
            left = right2;
            right1 = rule.get(a);
            right2 = "";
            for (int b = a+1; b < sub ; b++) {
                right2 = right2 + rule.get(b);
            }
            temp = times + " " + left + "  " + right1 + " " + right2;
          //  System.out.println(temp);

            cnf.add(temp);
        }
        return cnf;
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





    public static void main(String[] args){

        ArrayList<String> in = new ArrayList<String>();
        ArrayList<String> out = new ArrayList<String>();
        ArrayList<String> pos = new ArrayList<String>();
        ArrayList<String> ans = new ArrayList<String>();


        try{
            in = readLine("grammar.out");
            pos= readLine("posCollection.out");

    }
        catch (IOException e){
            e.printStackTrace();
        }
        for(String st:in){
          /*  StringTokenizer thistok = new StringTokenizer(st," ");

            int con = thistok.countTokens();
            int times;
            if(thistok.hasMoreTokens()){
                String str = thistok.nextToken();
                times = Integer.parseInt(str);

            }
            if(thistok.hasMoreTokens()){
                String str = thistok.nextToken();
            }*/
            ArrayList<String> temp = toCNF(st);
            for(String cnf:temp){
                ans.add(cnf);
            }
        }



        try{
            writeLine(ans,"grammarFilter.out");}
        catch (IOException ea){
            ea.printStackTrace();
        }

}}
