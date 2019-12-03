import model.BascketProduct;
import model.Receipt;
import model.ShoppingBascket;
import model.TaxedProduct;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CashDeskTest {
    CashDesk maker;
    ShoppingBascket bascket;
    BascketProduct p1;
    BascketProduct p2;
    BascketProduct p3;
    BascketProduct p4;
    BascketProduct p5;

    public void createBascketProducts(){
        p1 = new BascketProduct(2, "book", 12.49);
        p2 = new BascketProduct(1, "music CD", 14.99);
        p3 = new BascketProduct(1, "imported bottle of perfume", 27.99);
        p4 = new BascketProduct(3, "box of imported chocolates", 11.25);
        p5 = new BascketProduct(1, "bottle of perfume", 18.99);
    }

    public void createShoppingBascket(){
        bascket = new ShoppingBascket();

        bascket.addProduct(new BascketProduct(1, "imported bottle of perfume", 27.99));
        bascket.addProduct(new BascketProduct(1, "bottle of perfume", 18.99));
        bascket.addProduct(new BascketProduct(1, "packet of headache pills", 9.75));
        bascket.addProduct(new BascketProduct(3, "box of imported chocolates", 11.25));
    }

    @Before
    public void init(){
        maker = new CashDesk(10, 5);
    }

    @Test
    public void generate(){
        createShoppingBascket();
        Receipt receipt = new Receipt();
        receipt.addProduct(new TaxedProduct(1, "imported bottle of perfume", 4.2, 32.19));
        receipt.addProduct(new TaxedProduct(1, "bottle of perfume", 1.9, 20.89));
        receipt.addProduct(new TaxedProduct(1, "packet of headache pills", 0, 9.75));
        receipt.addProduct(new TaxedProduct(3, "box of imported chocolates", 1.80, 35.55));

        assertEquals(receipt, maker.generate(bascket));
    }

    @Test
    public void taxProduct(){
        createShoppingBascket();
        TaxedProduct[] taxedProducts = new TaxedProduct[4];
        taxedProducts[0] = new TaxedProduct(1, "imported bottle of perfume", 4.2, 32.19);
        taxedProducts[1] = new TaxedProduct(1, "bottle of perfume", 1.9, 20.89);
        taxedProducts[2] = new TaxedProduct(1, "packet of headache pills", 0, 9.75);
        taxedProducts[3] = new TaxedProduct(3, "box of imported chocolates", 1.80, 35.55);

        for(int i=0; i<bascket.getProducts().size(); i++)
            assertEquals(taxedProducts[i], maker.taxProduct(bascket.getProducts().get(i)));
    }

    @Test
    public void getTotal(){
        createBascketProducts();
        assertEquals(24.98, maker.getTotal(p1, 0), 0.0);
        assertEquals(16.49, maker.getTotal(p2, 10), 0.0);
        assertEquals(32.19, maker.getTotal(p3, 15), 0.0);
        assertEquals(35.55, maker.getTotal(p4, 5), 0.0);
        assertEquals(20.89, maker.getTotal(p5, 10), 0.0);
    }

    @Test
    public void getTaxAmount(){
        createBascketProducts();
        assertEquals(0, maker.getTaxAmount(p1, 0), 0.0);
        assertEquals(1.5, maker.getTaxAmount(p2, 10), 0.0);
        assertEquals(4.2, maker.getTaxAmount(p3, 15), 0.0);
        assertEquals(1.80, maker.getTaxAmount(p4, 5), 0.0);
        assertEquals(1.9, maker.getTaxAmount(p5, 10), 0.0);
    }

    @Test
    public void roundTax(){
        assertEquals(4.2, maker.roundTax(4.1985), 0.0);
        assertEquals(4.15, maker.roundTax(4.1485), 0.0);
        assertEquals(2.35, maker.roundTax(2.30085), 0.0);
        assertEquals(2.4, maker.roundTax(2.36), 0.0);
        assertEquals(1.50, maker.roundTax(1.499), 0.0);
        assertEquals(1.70, maker.roundTax(1.6875), 0.0);
        assertEquals(0.6, maker.roundTax(0.5625), 0.0);
        assertEquals(0.55, maker.roundTax(0.5025), 0.0);
        assertEquals(0.1, maker.roundTax(0.1), 0.0);
        assertEquals(0.15, maker.roundTax(0.13), 0.0);
        assertEquals(0.15, maker.roundTax(0.15), 0.0);
        assertEquals(1.65, maker.roundTax(1.6000005), 0.0);
    }
}
