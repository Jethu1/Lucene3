package demo1;

import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import sun.nio.cs.HistoricallyNamedCharset;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/17.
 */
public class FindIndex {

    public static void main(String[] args) throws IOException {
        Hits hits = null;
        String queryString = "java";
         Query query =null;
        IndexSearcher searcher = new IndexSearcher("D:\\index");
        StandardAnalyzer analyzer = new StandardAnalyzer();

        QueryParser qp = new QueryParser("body",analyzer);
        try {
            query = qp.parse(queryString);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        if(searcher!=null)
        hits = searcher.search(query);
        System.out.println(hits.length());
        if(hits.length()>0){
            System.out.println("ÃÜÂï¸öÊı£º"+hits.length());
        }
        for (int i = 0; i <hits.length() ; i++) {
            System.out.println(hits.doc(i));
        }

    }

}
