import com.cszt.LuceneApplication;
import com.cszt.service.PaginationSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author lilin
 * @create 2018/11/23 9:18
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuceneApplication.class)
public class PaginationTest {
    @Autowired
    private PaginationSearch paginationSearch;
    @Test
    public void search() throws Exception {
        paginationSearch.Search();
    }
    @Test
    public void numberSearch() throws Exception {
        paginationSearch.numberSearch();
    }
    @Test
    public void prefixSearch() throws IOException {
        paginationSearch.prefixSearch();
    }
    @Test
    public void test() throws IOException {
        paginationSearch.test();
    }
}