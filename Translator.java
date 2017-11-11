import java.io.IOException;
import java.util.ArrayList;

public class Translator {

    public static void main (String[] args){

        String[] paths = Seacher.seacher(".//scfile");

        ArrayList<String> ans = new ArrayList<String>();
        try {
            for (String st : paths) {
                ans = SCToScr.readLine(".//scfile/" + st);
                SCToScr.writeLine(ans,".//aimlout/" + st + ".aiml");}
            }

        catch (IOException e){
            e.printStackTrace();
        }

        }





    }

