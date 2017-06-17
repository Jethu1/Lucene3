package demo2;

/**
 * Created by Administrator on 2017/6/17.
 */
import  java.io.IOException;

import  org.apache.lucene.analysis.Analyzer;
import  org.apache.lucene.analysis.standard.StandardAnalyzer;
import  org.apache.lucene.queryParser.ParseException;
import  org.apache.lucene.queryParser.QueryParser;
import  org.apache.lucene.search.Hits;
import  org.apache.lucene.search.IndexSearcher;
import  org.apache.lucene.search.Query;



public   class  TestQuery  {
    public   static   void  main(String[] args)  throws  IOException, ParseException  {
        Hits hits  =   null ;
        String queryString  = "����" ;
        Query query  =   null ;
        IndexSearcher searcher  =   new  IndexSearcher( "d://index" );

        Analyzer analyzer  =   new  StandardAnalyzer();
        try   {
            QueryParser qp  =   new  QueryParser( "body" , analyzer);
            query  =  qp.parse(queryString);
        }   catch  (ParseException e)  {
        }
        if  (searcher  !=   null )  {
            hits  =  searcher.search(query);
            if  (hits.length()  >   0 )  {
                System.out.println( "�ҵ�: "   +  hits.length()  +   "  �����! " );
            }
        }

        for (int i = 0; i <hits.length() ; i++) {
            org.apache.lucene.document.Document document = hits.doc(i);
            System.out.println("����"+document.get("body"));
            System.out.println("·��:"+document.get("path"));
            }
        }

    }

