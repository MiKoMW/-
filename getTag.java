import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Mac on 2017/11/11.
 */
public class getTag {

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

        ArrayList<String> input = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> output = new ArrayList<String>();

        try {
            input = readLine("tag.in");
        }catch (Exception e){
            e.printStackTrace();
        }

        for(String st: input){
            st = st.substring(st.indexOf(' ') + 1,st.length());
            temp.add(st);
        }
        for(String st: temp){
            st = st.substring(0,st.indexOf(' ') );
            output.add(st);
        }
        try {

        writeLine(output,"tag.out");}
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
