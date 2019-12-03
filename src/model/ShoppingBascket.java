package model;

import java.util.ArrayList;

public class ShoppingBascket {
    private ArrayList<BascketProduct> products = new ArrayList<BascketProduct>();

    public void addProduct(BascketProduct product){ products.add(product); }

    public int itemCount() {
        return products.size();
    }

    public boolean contains(BascketProduct product){
        for(BascketProduct e : products)
            if(e.equals(product))
                return true;

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingBascket bascket = (ShoppingBascket) o;

        for(BascketProduct product : products)
            if(!bascket.contains(product))
                return false;

        return true;
    }

    public ArrayList<BascketProduct> getProducts() {
        return products;
    }
}
