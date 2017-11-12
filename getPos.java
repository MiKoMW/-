import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Mac on 2017/11/11.
 */
public class getPos {

    public static String  get(String text) {
        /**
         * 创建一个StanfordCoreNLP object
         * tokenize(分词)、ssplit(断句)、 pos(词性标注)、lemma(词形还原)、
         * ner(命名实体识别)、parse(语法解析)、指代消解？同义词分辨？
         */

        String ans = "";
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");    // 七种Annotators
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);    // 依次处理


        Annotation document = new Annotation(text);    // 利用text创建一个空的Annotation
        pipeline.annotate(document);                   // 对text执行所有的Annotators（七种）

        // 下面的sentences 中包含了所有分析结果，遍历即可获知结果。
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        System.out.println("word\tpos\tlemma\tner");

        for(CoreMap sentence: sentences) {
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                String word = token.get(CoreAnnotations.TextAnnotation.class);            // 获取分词
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);     // 获取词性标注
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);    // 获取命名实体识别结果
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);          // 获取词形还原结果

                ans = pos;
                System.out.println(word+"\t"+pos+"\t"+lemma+"\t"+ne);
            }

            // 获取parse tree
            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
            System.out.println(tree.toString());

            // 获取dependency graph
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            System.out.println(dependencies);
        }
        Map<Integer, CorefChain> graph = document.get(CorefCoreAnnotations.CorefChainAnnotation.class);

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



    public static void main(String[] args){

        ArrayList<String> pos = new ArrayList<String>();
        ArrayList<String> allowed = new ArrayList<String>();
        ArrayList<String> out = new ArrayList<String>();
        try{
            allowed = readLine("words");}
        catch (IOException e){
            e.printStackTrace();
        }

        for(String st:allowed){
            st = st.substring(13);
            String temp = "1 " +get(st) + " " + st;
            out.add(temp);
            pos.add(get(st));
        }

        try{
            writeLine(out,"pos.out");
            writeLine(pos,"posCollection.out");
    }
        catch (IOException ea){
            ea.printStackTrace();
        }



    }
}
