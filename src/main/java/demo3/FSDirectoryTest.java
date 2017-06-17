package demo3;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/17.
 */
public class FSDirectoryTest {

    public static final String path = "D:\\index";

    public static void main(String[] args) throws IOException, ParseException {
        Document doc1 = new Document();
        doc1.add(new Field("name","lighter javaeye com",Field.Store.YES,Field.Index.TOKENIZED));
        Document doc2 = new Document();
        doc2.add(new Field("name","lighter blog java",Field.Store.YES,Field.Index.TOKENIZED));

        IndexWriter writer = new IndexWriter(FSDirectory.getDirectory(path,true),new StandardAnalyzer(),true);
        writer.setMaxFieldLength(3);
        writer.addDocument(doc1);
        writer.setMaxFieldLength(3);
        writer.addDocument(doc2);
        writer.close();

        IndexSearcher searcher = new IndexSearcher(path);
        Hits hits = null;
        Query query = null;
        QueryParser qp = new QueryParser("name",new StandardAnalyzer());

        query = qp.parse("lighter");
        hits = searcher.search(query);
        System.out.println("total search results: lighter "+hits.length());

        query = qp.parse("java");
        hits = searcher.search(query);
        System.out.println("total search results: java "+hits.length());

    }
}
