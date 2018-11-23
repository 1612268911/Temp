package com.cszt.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lilin
 * @create 2018/11/23 11:34
 * description:
 */
@Service
public class SortSearch {
    /*****  排序  *****/
    /***
     * 排序查询
     * 1.被排序的字段必须被索引过(Indexecd)，在索引时不能 用 Field.Index.TOKENIZED
     *   (用UN_TOKENIZED可以正常实现.用NO时查询正常，但排序不能正常设置升降序)
     * 2.SortField类型
     *   SCORE、DOC、AUTO、STRING、INT、FLOAT、CUSTOM 此类型主要是根据字段的类型选择
     * 3.SortField的第三个参数代表是否是降序true:降序  false:升序
     */
    public void sortSearch() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        PhraseQuery query = new PhraseQuery();
        query.add(new Term("name","hello"));
        //排序字段id
        //数据类型long
        //降序true
        Sort sort = new Sort();
        SortField sortField = new SortField("name", SortField.Type.STRING, true);
        sort.setSort(sortField);
//        //多字段排序
//        SortField sf1 = new SortField("name", SortField.Type.STRING, true);
//        SortField sf2 = new SortField("id", SortField.Type.LONG, true);
//        sort.setSort(new SortField[]{sf1,sf2});
        TopDocs topDocs = indexSearcher.search(query,10000, sort);
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
     * 时间，日期排序
     * private Date createTime;
     * 创建时 document.add(new LongField("createTime", user.getCreateTime().getTime(),Field.Store.YES));
     * SortField sortField = new SortField("createTime", SortField.Type.LONG, true);
     */
    public void dateSortSearch() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        WildcardQuery query = new WildcardQuery(new Term("name","*"));
        Sort sort = new Sort();
        SortField sortField = new SortField("createTime", SortField.Type.LONG, false);
        sort.setSort(sortField);
        TopDocs topDocs = indexSearcher.search(query,10000, sort);
        System.out.println(topDocs.totalHits);
        System.out.println("***********************************");
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if (scoreDocs != null && scoreDocs.length > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (ScoreDoc sd : scoreDocs) {
                int docId = sd.doc;
                Document document = indexSearcher.doc(docId);
                String createTime = "";
                if(!StringUtils.isEmpty(document.get("createTime"))){
                    createTime = sdf.format(new Date(Long.parseLong(document.get("createTime"))));
                }
                System.out.println(document.get("id") + "   " + document.get("name") + "    " + document.get("pwd")+"   "+createTime);
            }
        }
    }
}