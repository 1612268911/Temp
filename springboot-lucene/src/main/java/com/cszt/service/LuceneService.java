package com.cszt.service;

import com.cszt.domain.User;
import com.cszt.mapper.UserMapper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.*;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import org.apache.lucene.document.*;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lilin
 * @create 2018/11/19 14:44
 * description: 入门 helloworld
 */
@Service
public class LuceneService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 创建索引
     */
    public void create() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_43,new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        List<User> userList = userMapper.userList();
        if(!CollectionUtils.isEmpty(userList)){
            Document document = null;
            Field field = null;
            for(User user :userList){
                document = new Document();
                field = new TextField("id",user.getId()+"", Field.Store.YES);
                document.add(field);
                field = new TextField("name",user.getName(),Field.Store.YES);
                document.add(field);
                field = new TextField("pwd",user.getPwd(),Field.Store.YES);
                document.add(field);
                indexWriter.addDocument(document);
            }
        }
        indexWriter.commit();
        indexWriter.close();
    }

    /***
     * 查询
     */
    public void search() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        String queryStr = "name:lucene";
        String defaultStr = "name";
        Analyzer analyzer = new IKAnalyzer();
        QueryParser queryParser = new QueryParser(Version.LUCENE_43,defaultStr, analyzer);
        Query query = queryParser.parse(queryStr);
        TopDocs topDocs = indexSearcher.search(query, 10000);
        System.out.println("总记录数--->"+topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if(scoreDocs!=null && scoreDocs.length>0){
            for(ScoreDoc scoreDoc:scoreDocs){
                int docId = scoreDoc.doc;
                Document doc = indexSearcher.doc(docId);
                System.out.println("id="+doc.get("id")+"  name="+doc.get("name")+"  pwd="+doc.get("pwd"));
            }
        }
    }
    /**
     * @author lilin
     * @description 分词器测试
     * @date: 2018/11/19 17:32
     * @param:
     * @return:
     */
    public void testAnalyzer(Analyzer analyzer,String str) throws IOException {
        TokenStream tokenStream = analyzer.tokenStream("name",str);
        tokenStream.reset();
        while(tokenStream.incrementToken()){
            System.out.println(tokenStream);
        }
    }
}