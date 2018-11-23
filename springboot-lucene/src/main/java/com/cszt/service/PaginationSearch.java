package com.cszt.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.apache.lucene.document.Field;

import java.io.File;
import java.io.IOException;

/**
 * @author lilin
 * @create 2018/11/23 9:00
 * description:
 */
@Service
public class PaginationSearch {
    /***
     * 分页搜索
     * @throws Exception
     */
    public void Search() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        String field = "name";
        QueryParser queryParser = new QueryParser(Version.LUCENE_47, field, new IKAnalyzer());

        String value = "hello";
        Query query = queryParser.parse(value);

        int pageIndex = 1;
        int pageSize = 3;
        int num = pageIndex * pageSize;
        TopDocs topDocs = indexSearcher.search(query, num);
        //topDocs.totalHits为总记录数
        System.out.println(topDocs.totalHits);
        System.out.println("***********************************");
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if (topDocs.totalHits > 0) {
            for (ScoreDoc sd : scoreDocs) {
                int docId = sd.doc;
                Document document = indexSearcher.doc(docId);
                System.out.println(document.get("id") + "   " + document.get("name") + "    " + document.get("pwd"));
            }
            System.out.println("***********************************");
            //最后一条记录
            ScoreDoc lastScoreDoc = scoreDocs[scoreDocs.length - 1];
            TopDocs topDocs1 = indexSearcher.searchAfter(lastScoreDoc, query, pageSize);
            //pageIndex=0时，即第一页直接
            //TopDocs topDocs1 = indexSearcher.searchAfter(null, query, pageSize);
            System.out.println(topDocs1.totalHits);
            System.out.println("***********************************");
            ScoreDoc[] scoreDocs1 = topDocs1.scoreDocs;
            if (scoreDocs1 != null && scoreDocs1.length > 0) {
                for (ScoreDoc sd : scoreDocs1) {
                    int docId = sd.doc;
                    Document document = indexSearcher.doc(docId);
                    System.out.println(document.get("id") + "   " + document.get("name") + "    " + document.get("pwd"));
                }
            }
        }
        directoryReader.close();
    }

    /***
     * 数字范围搜索
     * @throws Exception
     */
    public void numberSearch() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        NumericRangeQuery<Long> longNumericRangeQuery = NumericRangeQuery.newLongRange("id",1L,5L,true,true);

        TopDocs topDocs = indexSearcher.search(longNumericRangeQuery, 10000);
        System.out.println(topDocs.totalHits);
        System.out.println("***********************************");
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if (scoreDocs != null && scoreDocs.length > 0) {
            for (ScoreDoc sd : scoreDocs) {
                int docId = sd.doc;
                Document document = indexSearcher.doc(docId);
                System.out.println(document.get("id") + "   " + document.get("name") + "    " + document.get("pwd"));
            }
        }
        directoryReader.close();
    }

    /***
     * 前缀搜索
     * @throws IOException
     */
    public void prefixSearch() throws IOException {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        PrefixQuery prefixQuery = new PrefixQuery(new Term("name","w"));

        TopDocs topDocs = indexSearcher.search(prefixQuery, 10000);
        System.out.println(topDocs.totalHits);
        System.out.println("***********************************");
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if (scoreDocs != null && scoreDocs.length > 0) {
            for (ScoreDoc sd : scoreDocs) {
                int docId = sd.doc;
                Document document = indexSearcher.doc(docId);
                System.out.println(document.get("id") + "   " + document.get("name") + "    " + document.get("pwd"));
            }
        }
    }

    /***
     * 测试组合条件，通配符* 和 数值范围
     * @throws IOException
     */
    public void test() throws IOException {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        BooleanQuery booleanQuery = new BooleanQuery();
        NumericRangeQuery<Long> longNumericRangeQuery = NumericRangeQuery.newLongRange("id",1L,8L,true,true);
        WildcardQuery wildcardQuery = new WildcardQuery(new Term("name","w*"));
        booleanQuery.add(longNumericRangeQuery,BooleanClause.Occur.MUST);
        booleanQuery.add(wildcardQuery,BooleanClause.Occur.MUST);
        TopDocs topDocs = indexSearcher.search(booleanQuery, 10000);
        System.out.println(topDocs.totalHits);
        System.out.println("***********************************");
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if (scoreDocs != null && scoreDocs.length > 0) {
            for (ScoreDoc sd : scoreDocs) {
                int docId = sd.doc;
                Document document = indexSearcher.doc(docId);
                System.out.println(document.get("id") + "   " + document.get("name") + "    " + document.get("pwd"));
            }
        }
        directoryReader.close();
    }
}