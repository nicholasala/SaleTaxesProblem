import model.TaxedProduct;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaxedProductTest {

    @Test
    public void print(){
        TaxedProduct p = new TaxedProduct(3, "box of imported chocolates", 1.80, 35.55);
        assertEquals("3 imported box of chocolates: 35.55\n", p.toString());
    }
}
