import model.Receipt;
import model.TaxedProduct;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiptTest{
    Receipt receipt;

    @Before
    public void init(){
        receipt = new Receipt();

        receipt.addProduct(new TaxedProduct(1, "imported bottle of perfume", 4.2, 32.19));
        receipt.addProduct(new TaxedProduct(1, "bottle of perfume", 1.9, 20.89));
        receipt.addProduct(new TaxedProduct(1, "packet of headache pills", 0, 9.75));
        receipt.addProduct(new TaxedProduct(3, "box of imported chocolates", 1.80, 35.55));
    }

    @Test
    public void getTotalAmount(){
        assertEquals(98.38, receipt.getTotalAsDouble(), 0.0);
    }

    @Test
    public void getSalesTaxes(){
        assertEquals(7.90, receipt.getSalesTaxesAsDouble(), 0.0);
    }

    @Test
    public void print(){
        String receiptText = "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "3 imported box of chocolates: 35.55\n" +
                "Sales Taxes: 7.9\n" +
                "Total: 98.38";

        assertEquals(receiptText, receipt.toString());
    }
}