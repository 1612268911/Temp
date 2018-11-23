import com.cszt.LuceneApplication;
import com.cszt.service.IndexCRUD;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lilin
 * @create 2018/11/22 10:48
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuceneApplication.class)
public class IndexCRUDTest {

    @Autowired
    private IndexCRUD indexCRUD;
    @Test
    public void create() throws Exception {
        indexCRUD.create();
    }
    @Test
    public void remove() throws Exception {
        indexCRUD.remove();
    }
    @Test
    public void update() throws Exception {
        indexCRUD.update();
    }
}