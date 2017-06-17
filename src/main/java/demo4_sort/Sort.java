package demo4_sort;

import org.apache.lucene.analysis.standard.ParseException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/17.
 */
public class Sort {

    public static void main(String[] args) {

    }

    public   void  IndexSort()  throws IOException
    {
        IndexWriter writer  =   new  IndexWriter( " C://indexStore " , new StandardAnalyzer(), true );
        Document doc  =   new  Document();
        doc.add( new Field( " sort " , " 1 " ,Field.Store.YES,Field.Index.TOKENIZED));
        writer.addDocument(doc);
        doc  =   new  Document();
        doc.add( new  Field( " sort " , " 4 " ,Field.Store.YES,Field.Index.TOKENIZED));
        writer.addDocument(doc);
        doc  =   new  Document();
        doc.add( new  Field( " sort " , " 3 " ,Field.Store.YES,Field.Index.TOKENIZED));
        writer.addDocument(doc);
        doc  =   new  Document();
        doc.add( new  Field( " sort " , " 5 " ,Field.Store.YES,Field.Index.TOKENIZED));
        writer.addDocument(doc);
        doc  =   new  Document();
        doc.add( new  Field( " sort " , " 9 " ,Field.Store.YES,Field.Index.TOKENIZED));
        writer.addDocument(doc);
        doc  =   new  Document();
        doc.add( new  Field( " sort " , " 6 " ,Field.Store.YES,Field.Index.TOKENIZED));
        writer.addDocument(doc);
        doc  =   new  Document();
        doc.add( new  Field( " sort " , " 7 " ,Field.Store.YES,Field.Index.TOKENIZED));
        writer.addDocument(doc);
        writer.close();
    }




    public void SearchSort1() throws IOException,  org.apache.lucene.queryParser.ParseException {
        IndexSearcher indexSearcher = new IndexSearcher("C://indexStore");
        QueryParser queryParser = new QueryParser("sort",new StandardAnalyzer());
        Query query = queryParser.parse("4");

        Hits hits = indexSearcher.search(query);
        System.out.println("有"+hits.length()+"个结果");
        Document doc = hits.doc(0);
        System.out.println(doc.get("sort"));
    }
    public void SearchSort2() throws IOException, ParseException
    {
        IndexSearcher indexSearcher = new IndexSearcher("C://indexStore");
        Query query = new RangeQuery(new Term("sort","1"),new Term("sort","9"),true);//这个地方前面没有提到，它是用于范围的Query可以看一下帮助文档.
        Hits hits = indexSearcher.search(query);
        System.out.println("有"+hits.length()+"个结果");
        for(int i=0;i<hits.length();i++)
        {
            Document doc = hits.doc(i);
            System.out.println(doc.get("sort"));
        }
    }
    public class MyScoreDocComparator implements ScoreDocComparator
    {
        private Integer[]sort;
        public MyScoreDocComparator(String s,IndexReader reader, String fieldname) throws IOException
        {
            sort = new Integer[reader.maxDoc()];
            for(int i = 0;i<s.length();i++)
            {
                Document doc =reader.document(i);
                sort[i]=new Integer(doc.get("sort"));
            }
        }
        public int compare(ScoreDoc i, ScoreDoc j)
        {
            if(sort[i.doc]>sort[j.doc])
                return 1;
            if(sort[i.doc]<1) //problem have
            return -1;
            return 0;
        }
        public int sortType()
        {
            return SortField.INT;
        }
        public Comparable sortValue(ScoreDoc i)
        {
            // TODO自动生成方法存根
            return new Integer(sort[i.doc]);
        }
    }
    public class MySortComparatorSource implements SortComparatorSource
    {
        private static final long serialVersionUID = -9189690812107968361L;
        public ScoreDocComparator newComparator(IndexReader reader, String fieldname)
                throws IOException
        {
            if(fieldname.equals("sort"))
                return new MyScoreDocComparator("sort",reader,fieldname);
            return null;
        }
    }

}
