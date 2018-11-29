package com.cszt.service;

import com.cszt.domain.User;
import com.cszt.repository.UserMapper;
import org.apache.lucene.document.*;
import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.List;

/**
 * @author lilin
 * @create 2018/11/22 10:43
 * description: 创建索引，修改索引，删除索引
 */
@Service
public class IndexCRUD {

    @Autowired
    private UserMapper userMapper;
    /**
     * 创建索引
     */
    public void create() throws Exception {
        //建立索引目录
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        //配置分词器
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_43,new IKAnalyzer());
        //创建索引写入工具
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        //获取数据
        List<User> userList = userMapper.userList();
        if(!CollectionUtils.isEmpty(userList)){
            Document document = null;
            Field field = null;
            FieldType fieldType = new FieldType();
            //是否保存到document
            fieldType.setStored(true);
            //是否建立索引
            fieldType.setIndexOptions(FieldInfo.IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            //是否用拆分分词
            fieldType.setTokenized(false);
            /***
             *  IndexableField->interface
             *  使用实现类Field
             *  一般使用子类longField,TextField,StringField
             *  LongField添加龙类型的字段
             *  TextField添加文本，分词
             *  StringField添加文本，不分词
             */
            for(User user :userList){
                document = new Document();
                field = new LongField("id",user.getId(), Field.Store.YES);
                document.add(field);
                field = new TextField("name",user.getName(),Field.Store.YES);
                document.add(field);
                field = new TextField("pwd",user.getPwd(),Field.Store.YES);
                document.add(field);
                document.add(new LongField("createTime", user.getCreateTime().getTime(),Field.Store.YES));
                //添加document
                indexWriter.addDocument(document);
            }
        }
        //提交
        indexWriter.commit();
        indexWriter.close();
    }
    public void remove() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_43,new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory,conf);
        //*****************start********************
//        //删除所有
//        indexWriter.deleteAll();
        //根据字段删除
        indexWriter.deleteDocuments(new Term("id","4"));
//        //根据查询条件
//        String queryStr = "name:java";
//        String defaultStr = "name";
//        Analyzer analyzer = new IKAnalyzer();
//        QueryParser queryParser = new QueryParser(Version.LUCENE_43,defaultStr, analyzer);
//        Query query = queryParser.parse(queryStr);
//        indexWriter.deleteDocuments(query);
        //**************end**************
        indexWriter.commit();
        indexWriter.close();
    }
    public void update() throws Exception{
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_43,new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory,conf);
        //*****************start********************
        //先删除，再添加
        //单条修改
        Document document = new Document();
        document.add(new TextField("id","5", Field.Store.YES));
        document.add(new TextField("name","java", Field.Store.YES));
        document.add(new TextField("pwd","789", Field.Store.YES));
        indexWriter.updateDocument(new Term("id","5"),document);
        //批量修改
//        List<Document> list = new ArrayList<Document>();
//        list.add(document);
//        indexWriter.updateDocuments(new Term("name","java"),list);
        //**************end**************
        indexWriter.commit();
        indexWriter.close();
    }

}