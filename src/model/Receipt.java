package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Receipt{
    private ArrayList<TaxedProduct> products = new ArrayList<TaxedProduct>();
    private BigDecimal total = new BigDecimal(0);
    private BigDecimal salesTaxes = new BigDecimal(0);

    public void addProduct(TaxedProduct product){
        products.add(product);
        total = total.add(new BigDecimal(String.valueOf(product.getTotal())));
        salesTaxes = salesTaxes.add(new BigDecimal(String.valueOf(product.getTaxAmount())));

//        int iTot = (int) (total * 100);
//        iTot += (int) (product.getTotalAsDouble() * 100);
//        total = ((double) iTot) / 100;
//
//        int iSal = (int) (salesTaxes * 100);
//        iSal += (int) (product.getTaxAmount() * 100);
//        salesTaxes = ((double) iSal) / 100;
    }

    public double getTotalAsDouble(){ return total.doubleValue(); }

    public double getSalesTaxesAsDouble(){ return salesTaxes.doubleValue(); }

    public BigDecimal getTotal(){ return total; }

    public BigDecimal getSalesTaxes(){ return salesTaxes; }

    public ArrayList<TaxedProduct> getProducts() { return products; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(TaxedProduct p : products)
            sb.append(p.toString());

        sb.append("Sales Taxes: ");
        sb.append(salesTaxes);
        sb.append("\nTotal: ");
        sb.append(total);

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        if(products.size() != receipt.getProducts().size()) return false;

        for(TaxedProduct p : products)
            if(!receipt.getProducts().contains(p))
                return false;

        if (products != null ? !products.equals(receipt.getProducts()) : receipt.getProducts() != null) return false;
        if (total != null ? !total.equals(receipt.getTotal()) : receipt.getTotal() != null) return false;
        return salesTaxes != null ? salesTaxes.equals(receipt.getSalesTaxes()) : receipt.getSalesTaxes() == null;

    }
}
