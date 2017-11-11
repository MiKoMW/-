import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SCToScr {





    public static ArrayList<String> readLine(String filePath) throws IOException
    {

        Charset charset = Charset.forName("utf-8");
        FileInputStream fileStream = new FileInputStream(new File(filePath));
        BufferedReader buffer = new BufferedReader(new InputStreamReader(fileStream,"utf-8"));

        ArrayList<String> ans = new ArrayList<String>();


        String line;
        while ((line = buffer.readLine()) != null){
            if(line.length() > 0){
            if( line.contains("Dialogue:")) {

                int temp = line.lastIndexOf(",,") + 2;
                String stnew = line.substring(temp,line.length());
                stnew = ' ' + stnew + ' ';

                stnew = stnew.replaceAll("([{].+[}])", " ");

                stnew = stnew.replaceAll("[^A-Z0-9\\u4E00-\\u9FA5]+", " ");

                if (stnew.length() < 2){continue;}
                stnew = stnew.substring(1,stnew.length()-1);

                ans.add(stnew);
                //System.out.println(stnew);
            }}

        }
            return ans;
        }

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
                bufferOut.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                bufferOut.newLine();
                bufferOut.write( "<aiml>");
                bufferOut.newLine();


                String temp = "";
                boolean isEm = true;
                for(String st: sts){

                    if(isEm){
                        isEm = false;
                        temp = st;

                        continue;
                    }
                    bufferOut.write("<category><pattern>"+temp+"</pattern><template>"+st+"</template></category>");
                    //<category><pattern>我们都有一个家*</pattern><template>哈哈哈.</template></category>
                    temp = st;

                    bufferOut.newLine();
                }
                bufferOut.write("</aiml>");

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
         ans = readLine("miko.txt");}
        catch (IOException e){
            e.printStackTrace();
        }
        try{
            writeLine(ans,"Mikoout.txt");}
        catch (IOException ea){
            ea.printStackTrace();
        }





    }






    }



