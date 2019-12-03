import model.BascketProduct;
import model.ShoppingBascket;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingBascketTest {
    ShoppingBascket bascket;

    @Before
    public void init(){
        bascket = new ShoppingBascket();
    }

    @Test
    public void itemCount(){
        assertEquals(0, bascket.itemCount());

        bascket.addProduct(new BascketProduct(1, "music CD", 12.9));
        bascket.addProduct(new BascketProduct(1, "music CD", 12.9));

        assertEquals(2, bascket.itemCount());
    }

    @Test
    public void contains(){
        bascket.addProduct(new BascketProduct(1, "Book", 3.9));
        BascketProduct product = new BascketProduct(12, "Music CD", 12.9);

        assertFalse(bascket.contains(product));
        bascket.addProduct(product);
        assertTrue(bascket.contains(product));
    }
}
