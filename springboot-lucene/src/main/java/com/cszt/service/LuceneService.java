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
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author lilin
 * @create 2018/11/19 14:44
 * description:
 */
@Service
public class LuceneService {
    @Autowired
    private UserMapper userMapper;
    /**
     * @author lilin
     * @description 创建索引
     * @date: 2018/11/19 14:56
     * @param:
     * @return:
     */
    public void create() throws Exception {
        //建立索引目录
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        //配置分词器
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_43,new SimpleAnalyzer(Version.LUCENE_43));
        //创建索引写入工具
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        //获取数据
        List<User> userList = userMapper.userList();
        if(!CollectionUtils.isEmpty(userList)){
            Document document = null;
            for(User user :userList){
                document = new Document();
                document.add(new StringField("id",user.getId()+"", Field.Store.YES));
                document.add(new StringField("name",user.getName(), Field.Store.YES));
                document.add(new StringField("pwd",user.getPwd(), Field.Store.YES));
                //添加document
                indexWriter.addDocument(document);
            }
        }
        //提交
        indexWriter.commit();
        indexWriter.close();
    }
    public void search() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        String queryStr = "name:java";
        String defaultStr = "name";
        Analyzer analyzer = new SimpleAnalyzer(Version.LUCENE_43);
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