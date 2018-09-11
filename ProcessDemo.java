import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Mac on 2018/9/11.
 */
public class ProcessDemo {

    public static void main(String[] args) {
        String s;
        StringBuilder sb = new StringBuilder();
        InputStream fis = null;
        try {
             Process process = Runtime.getRuntime().exec("ping localhost");
            fis = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            while((s=bufferedReader.readLine()) != null) {
                sb.append(s);
                //sb.append(\n);
            }
            System.out.println(sb.toString());
            process.waitFor();
            System.out.println(process.exitValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
