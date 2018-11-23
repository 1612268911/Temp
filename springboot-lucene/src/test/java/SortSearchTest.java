import com.cszt.LuceneApplication;
import com.cszt.domain.User;
import com.cszt.service.SortSearch;
import org.apache.lucene.document.Field;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author lilin
 * @create 2018/11/23 11:44
 * description:
 */
@SpringBootTest(classes = LuceneApplication.class)
@RunWith(SpringRunner.class)
public class SortSearchTest {
    @Autowired
    private SortSearch sortSearch;

    @Test
    public void sortSearch() throws Exception {
        sortSearch.sortSearch();
    }
    @Test
    public void dateSortSearch() throws Exception {
        sortSearch.dateSortSearch();
    }
}