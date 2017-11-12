import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Mac on 2017/11/11.
 */
public class alltag {

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

    public static void main(String[] args){

        ArrayList<String> in = new ArrayList<String>();
        ArrayList<String> gra = new ArrayList<String>();

        try{
            in = readLine("posCollection.out");
            gra = readLine("grammar.out");

        }
        catch (IOException e){
            e.printStackTrace();
        }
        String temp = new String();
        for(String st:gra){

            temp = temp + st;
        }

        for(String st : in){
            if(!temp.contains(st)){
                System.out.println(st);
            }
        }



    }
}
