import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Mac on 2018/7/18.
 */
public class Comp {

    public static void main(String[] args){

        ArrayList<String> right = new ArrayList<String>();
        ArrayList<String> test = new ArrayList<String>();
        try{
            right = FileIO.readLine("ac.out");
            test = FileIO.readLine("my.out");

        }catch (Exception e){
            e.printStackTrace();
        }

        if(right.size() != test.size()){
            System.out.println("My output size is : " + test.size());
            System.out.println("AC code output size is : " + right.size());
            return;
        }

        int size = right.size();

        boolean correct = true;

        for(int con = 0; con < size; con++){
            String t = right.get(con);
            String r = test.get(con);


            if(!t.equals(r)){
                System.out.println("Output: " + (con + 1) + " is not correct.");
                correct = correct && t.equals(r);
            }

        }

        if (correct){
            System.out.println("Answer accepted.");
        }


    }

}


/*
 * <summary>Songbo Hu</summary>
 * <author></author>
 * <email>s1647079@sms.ed.ac.uk</email>
 * <create-date>2017/11/30</create-date>
 */


/**
 *
 * FileIO, read/write a file and put it in arraylist line by line.
 *
 * @author Songbo
 * Created by Mac on 2017/11/30.
 */
 class FileIO {


    /**
     * Read a file line by line and return an arraylist.
     *
     * @param filePath Input File
     * @return arraylist of string
     * @throws IOException
     */
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


    /**
     * Write a file line by line and return an arraylist.
     *
     * @param sts output arraylist of string
     * @param OutPutFile Output File
     * @throws IOException
     */
    public static void writeLine(ArrayList<String> sts, String OutPutFile) throws IOException{

        FileWriter fileOutput = new FileWriter(OutPutFile);
        BufferedWriter bufferOut = new BufferedWriter(fileOutput);
        File fileOut = new File(OutPutFile);
        if (!fileOut.exists()) {
            try {
                fileOut.createNewFile();
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
}