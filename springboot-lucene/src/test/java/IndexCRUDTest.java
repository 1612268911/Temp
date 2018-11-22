import com.cszt.service.IndexCRUD;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lilin
 * @create 2018/11/22 10:48
 * description:
 */
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