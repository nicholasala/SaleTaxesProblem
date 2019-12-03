import model.BascketProduct;
import model.Receipt;
import model.ShoppingBascket;
import model.TaxedProduct;

import java.math.BigDecimal;

enum TaxFree{
    BOOK,
    CHOCOLATE,
    PILLS
}

public class CashDesk {
    private final int basicTax;
    private final int importTax;

    public CashDesk(int basicTax, int importTax) {
        this.basicTax = basicTax;
        this.importTax = importTax;
    }

    public Receipt generate(ShoppingBascket bascket) {
        Receipt receipt = new Receipt();

        for(BascketProduct p : bascket.getProducts())
            receipt.addProduct(taxProduct(p));

        return receipt;
    }

    public TaxedProduct taxProduct(BascketProduct product){
        double taxRate = 0;

        if(product.getName().contains("imported"))
            taxRate += importTax;

        for(TaxFree label : TaxFree.values())
            if(product.getName().contains(label.toString().toLowerCase()))
                return  new TaxedProduct(product, getTaxAmount(product, taxRate), getTotal(product, taxRate));

        taxRate += basicTax;
        return  new TaxedProduct(product, getTaxAmount(product, taxRate), getTotal(product, taxRate));
    }

    public double getTotal(BascketProduct product, double taxRate) {
        return new BigDecimal(String.valueOf(product.getPrice()))
                .multiply(new BigDecimal(product.getVolume()))
                .add(new BigDecimal(String.valueOf(getTaxAmount(product, taxRate))))
                .doubleValue();

//        int iVal = (int) new BigDecimal(String.valueOf(price)).multiply(new BigDecimal("100")).doubleValue();
//        iVal *= volume;
//        iVal += (int) (getTaxAmount() * 100);
//        return ((double) iVal / 100);
    }

    public double getTaxAmount(BascketProduct product, double taxRate) {
        return new BigDecimal(String.valueOf(roundTax(product.getPrice() * (taxRate/100))))
                .multiply(new BigDecimal(product.getVolume()))
                .doubleValue();

//        int iVal = (int) (roundTax(price * (taxRate/100)) * 100);
//        return ((double)(iVal * volume)) / 100;
    }

    //metodo per l'arrotondamento al valore 0.05 superiore
    public double roundTax(double value){
        int decCount = String.valueOf(value).split("\\.")[1].length();

        if (decCount == 1) return value;

        decCount = decCount > 7 ? 7 : decCount;
        int multiplier = 1;
        int mod = 5;

        for(int i=0; i<decCount; i++)
            multiplier *= 10;

        for(int i=0; i<decCount - 2; i++)
            mod *= 10;

        int iVal = (int) (value*multiplier);

        if(iVal % mod != 0)
            iVal += (mod - (iVal % mod));

        return ((double)iVal) / multiplier;
    }
}
