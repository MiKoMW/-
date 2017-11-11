import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Mac on 2017/10/24.
 */
public class mips {




    public static ArrayList<String> readLine(String filePath) throws IOException
    {

        Charset charset = Charset.forName("utf-8");
        FileInputStream fileStream = new FileInputStream(new File(filePath));
        BufferedReader buffer = new BufferedReader(new InputStreamReader(fileStream,"utf-8"));

        ArrayList<String> ans = new ArrayList<String>();

        String temp = "                                                                                                          ";
        String line;
        while ((line = buffer.readLine()) != null){
            if(line.length() < 40 && !line.contains("#")){


                String stnew = line + temp;
                stnew = stnew.substring(0,40);
                stnew = stnew + "#";


                    ans.add(stnew);
                    //System.out.println(stnew);
                }
                else{
                ans.add(line);
            }

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


                //<category><pattern>我们都有一个家*</pattern><template>哈哈哈.</template></category>

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            bufferOut.close();
        }


    }



    public static void main (String[] args){
        ArrayList<String> ans = new ArrayList<String>();
        try{
            ans = readLine("a.s");}
        catch (IOException e){
            e.printStackTrace();
        }
        try{
            writeLine(ans,"aout.s");}
        catch (IOException ea){
            ea.printStackTrace();
        }





    }
}
