import com.cszt.LuceneApplication;
import com.cszt.service.SearchLucene;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lilin
 * @create 2018/11/22 9:34
 * description:lucene查询测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuceneApplication.class)
public class SearchTest {
    @Autowired
    private SearchLucene searchService;
    @Test
    public void singSearch0() throws Exception {
        //或者defaultStr=name,queryStr=hello
        //或者defaultStr="",queryStr=name:hello
        searchService.singSearch("name:hello");
    }
    @Test
    public void singSearch() throws Exception {
        //单词查询，只要name包含hello或lucene都会被命中
        searchService.singSearch("name:hello lucene");
    }
    @Test
    public void singSearch2() throws Exception {
        //termQuery单词查询
        Query query = new TermQuery(new Term("name","java"));
        searchService.singSearch(query);
    }

    @Test
    public void singSearch3() throws Exception {
        searchService.PhraseQuery();
    }
    @Test
    public void search() throws Exception {
        searchService.WildcardQuery();
    }
    @Test
    public void searchTest() throws Exception {
        searchService.testSeach();
    }
    @Test
    public void booleanSearch() throws Exception {
        searchService.booleanQuery();
    }
}