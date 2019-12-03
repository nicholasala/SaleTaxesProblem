import model.BascketProduct;
import org.junit.Test;
import static org.junit.Assert.*;

public class BascketProductTest {

    @Test
    public void productContruction(){
        BascketProduct product = new BascketProduct(12, "music CD", 12.49);

        assertEquals(12, product.getVolume());
        assertEquals("music CD", product.getName());
        assertEquals(12.49, product.getPrice(), 0.0);

        product.setName("book");
        assertEquals(product.getName(), "book");
    }
}
