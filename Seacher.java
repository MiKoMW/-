import java.io.File;
import java.io.FileWriter;

public class Seacher {

    public static String[] seacher(String pathname){

        File file = new File(pathname);
        String[] ans = file.list();
        for(String st : ans){
            System.out.println(st);
        }
        return  ans;
    }

}
