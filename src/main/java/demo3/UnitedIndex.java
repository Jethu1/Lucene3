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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/17.
 */
public class UnitedIndex {



    public void UnitedIndex1() throws IOException {
        IndexWriter writer = new IndexWriter(FSDirectory.getDirectory("D:\\index",true),new StandardAnalyzer(),true);
        Document docDisc = new Document();
        docDisc.add(new Field("name","程序员zhi假",Field.Store.YES,Field.Index.TOKENIZED));

        writer.addDocument(docDisc);

        RAMDirectory ramDir = new RAMDirectory();
        IndexWriter writer2 = new IndexWriter(ramDir,new StandardAnalyzer(),true);
        Document docRam = new Document();
        docRam.add(new Field("name","程序员杂志",Field.Store.YES,Field.Index.TOKENIZED));
        writer2.addDocument(docRam);

        writer2.close();//when we unite this part we should close its indexWriter
        writer.addIndexes(new Directory[]{ramDir});
        writer.close();

    }

    public void UniteSearch() throws ParseException, IOException {
        QueryParser queryParser = new QueryParser("name",new StandardAnalyzer());
        Query query = queryParser.parse("程序员");
        IndexSearcher indexSearcher = new IndexSearcher("D:\\index");
        Hits hits = indexSearcher.search(query);
        System.out.println("找到了"+hits.length()+"结果");
        for (int i = 0; i <hits.length() ; i++) {
            Document document = hits.doc(i);
            System.out.println(document.get("name"));
        }


    }

    public static void main(String[] args) throws IOException, ParseException {
        UnitedIndex unitedIndex = new UnitedIndex();
        unitedIndex.UnitedIndex1();
        unitedIndex.UniteSearch();
    }

}
