package demo3;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.StringReader;

/**
 * Created by Administrator on 2017/6/17.
 */
public class AnalyzerTest {
    public AnalyzerTest(){}

    public static void main(String[] args) {
        Analyzer analyzer = new StandardAnalyzer();
        StringReader sr = new StringReader("lighter is a an arewhat I don't care com jave eyre");
        TokenStream ts = analyzer.tokenStream("what",sr);
        try{
            int i=0;
            Token t = ts.next();
            while(t!=null){
                i++;
                System.out.println("µÚ"+i+"ÐÐ"+t.termText());
                t = ts.next();
            }

        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
