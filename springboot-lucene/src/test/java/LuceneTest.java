import com.cszt.LuceneApplication;
import com.cszt.service.LuceneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

/**
 * @author lilin
 * @create 2018/11/19 15:21
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuceneApplication.class)
public class LuceneTest {

    @Autowired
    private LuceneService luceneService;

    @Test
    public void create() throws Exception {
        luceneService.create();
    }
    @Test
    public void search() throws Exception {
        luceneService.search();
    }
    @Test
    public void testAnalyzer() throws Exception {
        //true表示匹配到最大值后不匹配小的值 如匹配到我的，就不会匹配到 我/的
        //IKAnalyzer分词器支持中文和英文
        IKAnalyzer analyzer = new IKAnalyzer(true);
        String str = "jj无数据jjjj118jj 我的 hello world 小心";
        luceneService.testAnalyzer(analyzer,str);
    }
}