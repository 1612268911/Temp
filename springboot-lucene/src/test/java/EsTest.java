
import com.cszt.LuceneApplication;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;


@SpringBootTest(classes = LuceneApplication.class)
@RunWith(SpringRunner.class)
public class EsTest {

    /**
     * 人脉搜索最新  ，调这里
     *
     * @throws Exception
     */
    @Test
    public void connectionList2() throws Exception {
        Directory directory = null;
        IndexReader reader = null;
        IndexSearcher indexSearcher = null;

        directory = FSDirectory.open(new File("E:\\Temp\\springboot-lucene\\src\\main\\resources\\index"));
        reader = DirectoryReader.open(directory);
        indexSearcher = new IndexSearcher(reader);

        Analyzer analyzer = new IKAnalyzer();
        //
        String[] values = {"name","pwd"};
        //字段值，不允许开头出现*,?
        String[] fields = {"java","33*"};
        //
        BooleanClause.Occur[] occurs = new BooleanClause.Occur[]{BooleanClause.Occur.MUST,BooleanClause.Occur.MUST};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_47, new String[]{}, analyzer);
        //
        multiFieldQueryParser.setAllowLeadingWildcard(true);
        System.out.println(multiFieldQueryParser.getAllowLeadingWildcard());

        Query query = multiFieldQueryParser.parse(Version.LUCENE_43,fields,values,occurs,analyzer);
        System.out.println("query-->"+query.toString());
        TopDocs topDocs = indexSearcher.search(query, 10000);
        System.out.println("总记录数-->"+topDocs.totalHits);
        for (ScoreDoc doc2 : topDocs.scoreDocs) {
            Document doc = indexSearcher.doc(doc2.doc);
            System.out.println(doc.get("id") + "    " + doc.get("name") + "   " + doc.get("pwd"));
        }
    }

//    /**
//     * 创建人脉索引
//     * @throws Exception
//     */
//    @Test
//    public void  createConnectionIndex() throws Exception{
//        Directory directory = FSDirectory.open(new File("./Index/connectionIndex"));
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
//        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43,analyzer);
//        IndexWriter writer = new IndexWriter(directory,config);
//        List<UserInfoExt> userInfoExtList = userInfoExtMapper.selectByExample(null);
//        Document doc = null ;
//        String resourceLabels = null ;
//        String customResourceLabels = null ;
//        String demandLabels = null ;
//        String customDemandLabels = null ;
//        for(UserInfoExt userInfoExt : userInfoExtList){
//            UserInfoExtDto userInfoExtDto = authService.getUserInfoExtDtoByUserId(userInfoExt.getUserId());
//            doc = new Document();
//            doc.add(new StringField("id",userInfoExtDto.getUserId()+"", Field.Store.YES));
//            String content = "";
//
//            String name = userInfoExtDto.getName();
//            name = StringUtils.isBlank(name) ? "" : name;
//            content+=name;
//            String grade  = userInfoExtDto.getGradeStr();
//            grade = StringUtils.isBlank(grade) ? "" : grade;
//            content+=" "+grade;
//            String classNumber = userInfoExtDto.getClassNumberStr();
//            classNumber = StringUtils.isBlank(classNumber) ? "" :classNumber;
//            content+=" "+classNumber;
//            String industry = userInfoExtDto.getIndustryStr2();
//            industry = StringUtils.isBlank(industry) ? "" :industry;
//            content+=" "+industry;
//            String job = userInfoExtDto.getJob();
//            job = StringUtils.isBlank(job) ? "" : job;
//            content+=" "+job;
//            doc.add(new TextField("content",content,Field.Store.YES));
//
//            resourceLabels = userInfoExtDto.getResourcesLables();
//            customResourceLabels = userInfoExtDto.getCustomResourcesLables();
//            resourceLabels = StringUtils.isBlank(resourceLabels) ? "" :resourceLabels;
//            customResourceLabels = StringUtils.isBlank(customResourceLabels) ? "" :customResourceLabels;
//            if(StringUtils.isNotBlank(customResourceLabels)){
//                resourceLabels += ","+customResourceLabels;
//            }
//            doc.add(new StringField("resourcesLablesStr",resourceLabels,Field.Store.YES));
//            for(String tag : Arrays.asList(resourceLabels.split(","))){
//                doc.add(new StringField("resourcesLables",tag,Field.Store.YES));
//            }
//
//            demandLabels = userInfoExtDto.getDemandLabels();
//            customDemandLabels =  userInfoExtDto.getCustomDemandLabels();
//            demandLabels = StringUtils.isBlank(demandLabels) ? "" :demandLabels;
//            customDemandLabels = StringUtils.isBlank(customDemandLabels) ? "" :customDemandLabels;
//            if(StringUtils.isNotBlank(customDemandLabels)){
//                demandLabels += ","+customDemandLabels;
//            }
//            doc.add(new StringField("demandLabelsStr",demandLabels,Field.Store.YES));
//            for(String tag : Arrays.asList(demandLabels.split(","))){
//                doc.add(new StringField("demandLabels",tag,Field.Store.YES));
//            }
//            writer.addDocument(doc);
//        }
//        writer.close();
//    }
//
//
//    /**
//     * 人脉搜索
//     * @throws Exception
//     */
//    //@Test
//    public void  connectionList()throws Exception{
//        Directory directory = FSDirectory.open(new File("./Index/connectionIndex"));
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
//        IndexReader reader = DirectoryReader.open(directory);
//        IndexSearcher indexSearcher = new IndexSearcher(reader);
//
//        Document doc = null;
//
//        doc = indexSearcher.doc(2);
//        System.out.println(doc.getField("content").stringValue());
//
//        BooleanQuery query = new BooleanQuery();
//        query.add(new WildcardQuery(new Term("content", "*2016*")),BooleanClause.Occur.MUST);
//        TopDocs topDocs = indexSearcher.search(query,reader.maxDoc());
//        for(ScoreDoc scoreDoc : topDocs.scoreDocs){
//            System.out.println(scoreDoc.score);
//            System.out.println(scoreDoc.doc);
//            doc = reader.document(scoreDoc.doc);
//            System.out.println(doc.getField("content").stringValue());
//        }
//
//    }
//
//    /**
//     * 推荐人脉
//     */
//    //@Test
//    public void recommendList() throws Exception{
//        Directory directory = FSDirectory.open(new File("./Index/connectionIndex"));
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
//        IndexReader reader = DirectoryReader.open(directory);
//        IndexSearcher indexSearcher = new IndexSearcher(reader);
//        MoreLikeThis mlt = new MoreLikeThis(reader);
//        mlt.setAnalyzer(analyzer);
//        mlt.setFieldNames(new String[] {"resourcesLables","demandLabels"});
//        mlt.setMinDocFreq(1);
//        mlt.setMinTermFreq(1);
//
//        Query query = mlt.like(0);
//
//        Query query3 = new TermQuery(new Term("id","1"));
//
//        Query query2 = new BooleanQuery();
//        ((BooleanQuery) query2).add(query,BooleanClause.Occur.MUST);
//        ((BooleanQuery) query2).add(query3,BooleanClause.Occur.MUST_NOT);
//
//        TopDocs topDocs = indexSearcher.search(query2,50);
//        for(ScoreDoc scoreDoc : topDocs.scoreDocs){
//            System.out.println(scoreDoc.score);
//            Document d = reader.document(scoreDoc.doc);
//            System.out.println(d.getField("id").stringValue());
//            System.out.println(d.getField("resourcesLables").stringValue());
//            System.out.println(d.getField("demandLabels").stringValue());
//            System.out.println();
//        }
//    }
//
//    /**
//     * 推荐用户索引
//     */
//    //@Test
//    public void  createRecommendIndex() throws Exception{
//        Directory directory = FSDirectory.open(new File("./Index/recommendIndex"));
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
//        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43,analyzer);
//        IndexWriter writer = new IndexWriter(directory,config);
//        List<UserInfoExt> userInfoExtList = userInfoExtMapper.selectByExample(null);
//        Document doc = null ;
//        String resourceLabels = null ;
//        String customResourceLabels = null ;
//        String demandLabels = null ;
//        String customDemandLabels = null ;
//        FieldType TYPE_STORED = new FieldType();
//        TYPE_STORED.setIndexed(true);
//        TYPE_STORED.setTokenized(true);
//        TYPE_STORED.setStored(true);
//        TYPE_STORED.setStoreTermVectors(true);
//        TYPE_STORED.freeze();
//        for(UserInfoExt userInfoExt : userInfoExtList){
//            UserInfoExtDto userInfoExtDto = authService.getUserInfoExtDtoByUserId(userInfoExt.getUserId());
//            doc = new Document();
//            doc.add(new StringField("id",userInfoExtDto.getUserId()+"", Field.Store.YES));
//            resourceLabels = userInfoExtDto.getResourcesLables();
//            customResourceLabels = userInfoExtDto.getCustomResourcesLables();
//            resourceLabels = StringUtils.isBlank(resourceLabels) ? "" :resourceLabels;
//            customResourceLabels = StringUtils.isBlank(customResourceLabels) ? "" :customResourceLabels;
//            if(StringUtils.isNotBlank(customResourceLabels)){
//                resourceLabels += ","+customResourceLabels;
//            }
//            doc.add(new Field("resourceLabels", resourceLabels.replace(","," "), TYPE_STORED));
//
//            demandLabels = userInfoExtDto.getDemandLabels();
//            customDemandLabels =  userInfoExtDto.getCustomDemandLabels();
//            demandLabels = StringUtils.isBlank(demandLabels) ? "" :demandLabels;
//            customDemandLabels = StringUtils.isBlank(customDemandLabels) ? "" :customDemandLabels;
//            if(StringUtils.isNotBlank(customDemandLabels)){
//                demandLabels += ","+customDemandLabels;
//            }
//            doc.add(new Field("demandLabels", resourceLabels.replace(","," "), TYPE_STORED));
//            writer.addDocument(doc);
//        }
//        writer.close();
//    }
//
//
//
//    //@Test
//    public void ppzTest(){
//        Set<String> aList = new HashSet<>();
//        aList.add("标签1");
//        aList.add("标签2");
//        aList.add("标签3");
//        aList.add("标签4");
//        aList.add("标签5");
//        aList.add("标签6");
//        aList.add("自定义标签1");
//
//        Set<String> bList = new HashSet<>();
//        bList.add("标签1");
//        bList.add("标签2");
//        bList.add("标签5");
//        bList.add("标签6");
//        bList.add("自定义标签2");
//
//        // 交集
//        Sets.SetView<String> intersection = Sets.intersection(aList,bList);
//        //并集
//        Sets.SetView<String> union = Sets.union(aList,bList);
//        System.out.println(intersection.size() / (union.size() * 1.0));
//
//    }
//
//

}
