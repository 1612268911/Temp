package com.cszt.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * @author lilin
 * @create 2018/11/22 9:31
 * description: lucene查询
 */
@Service
public class SearchLucene {
    /***
     * 单词查询 String
     */
    public void singSearch(String str) throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //字段
        String defaultStr = "";
        QueryParser parser = new QueryParser(Version.LUCENE_43,defaultStr,new IKAnalyzer());
        //字段值 str
        Query query = parser.parse(str);
        System.out.println("query--->"+query.toString());
        TopDocs topDocs = indexSearcher.search(query, 10000);
        int totalHits = topDocs.totalHits;
        System.out.println("总记录数-->"+totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if(scoreDocs!=null && scoreDocs.length>0){
            for (ScoreDoc scoreDoc : scoreDocs) {
                int docId = scoreDoc.doc;
                Document document = indexSearcher.doc(docId);
                System.out.println(document.get("id")+"   "+document.get("name")+"    "+document.get("pwd"));
            }
        }
    }
    /***
     * 单词查询  Query
     */
    public void singSearch(Query query) throws Exception {
        System.out.println("query--->"+query.toString());
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        TopDocs topDocs = indexSearcher.search(query, 10000);
        int totalHits = topDocs.totalHits;
        System.out.println("总记录数-->"+totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if(scoreDocs!=null && scoreDocs.length>0){
            for (ScoreDoc scoreDoc : scoreDocs) {
                int docId = scoreDoc.doc;
                Document document = indexSearcher.doc(docId);
                System.out.println(document.get("id")+"   "+document.get("name")+"    "+document.get("pwd"));
            }
        }
    }

    /***
     * 段落查询
     *
     *
     */
    public void PhraseQuery() throws Exception {
        //PhraseQuery段落查询必须包含"java lucene"才会被命中
        PhraseQuery query = new PhraseQuery();
        //name:"java world"等同
        //luceneService.singSearch("name:\"java world\"");
        query.add(new Term("name","hello"));
        query.add(new Term("name","lucene"));
        singSearch(query);
    }
    /***
     * 段落查询
     * query--->name:"hello lucene"~3
     * 总记录数-->4
     * 2   hello lucene world    222
     * 3   hello jjj lucene    333
     * 6   hello jj j a lucene    333
     * 7   hello j j a lucene    3333
     */
    public void PhraseQuery2() throws Exception {
        //PhraseQuery段落查询必须包含"java lucene"才会被命中
        PhraseQuery query = new PhraseQuery();
        //name:"java world"等同
        //luceneService.singSearch("name:\"java world\"");
        query.add(new Term("name","hello"));
        query.add(new Term("name","lucene"));
        //设置最大间隔数,空格
        query.setSlop(3);
        singSearch(query);
    }
    /***
     * 通配符 *(表示有0-n个) 查询
     * ? 占位一个 ja?a -->java
     */
    public void WildcardQuery() throws Exception {
        WildcardQuery query = new WildcardQuery(new Term("name","*ja?a"));

        singSearch(query);
    }

    /***
     * 占位符 ~ 相似，不建议用
     *
     */
    public void testSeach() throws Exception {
        singSearch("name:jav~");
    }

    /***
     * 组合查询  不支持*?
     * 与+ 或 非-  省略为或
     * 非 必须是组合条件，否则无效
     * 不能写成singSearch("name:java + name:lucene")，必须singSearch("name:java +name:lucene")
     */
    public void booleanQuery() throws Exception {
//        //或 等价于singSearch("name:java lucene");
//        singSearch("name:java name:lucene");
//        //与
//        singSearch("+name:java +name:lucene");
//        //非
//        singSearch("-name:java -name:lucene");

        //效果相同

//        //或
//        BooleanQuery shouldQuery = new BooleanQuery();
//        shouldQuery.add(new TermQuery(new Term("name","java")), BooleanClause.Occur.SHOULD);
//        shouldQuery.add(new TermQuery(new Term("name","lucen")), BooleanClause.Occur.SHOULD);
        //与
        BooleanQuery mustQuery = new BooleanQuery();
        mustQuery.add(new TermQuery(new Term("name","java")), BooleanClause.Occur.MUST);
        mustQuery.add(new TermQuery(new Term("name","lucene")), BooleanClause.Occur.MUST);
//        //非
//        BooleanQuery mustNotQuery = new BooleanQuery();
//        mustNotQuery.add(new TermQuery(new Term("name","java")), BooleanClause.Occur.MUST);
//        mustNotQuery.add(new TermQuery(new Term("name","lucene")), BooleanClause.Occur.MUST_NOT);
        singSearch(mustQuery);
    }
}