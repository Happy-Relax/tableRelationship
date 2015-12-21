package org.glassfish.jersey.examples.helloworld;

/**
 * Created by liujia on 12/21/15.
 */
public class Item {
    public String barcode;
    public String name;
    public String unit;
    public float price;

    public Item(String barcode, String name, String unit, float price){
        this.barcode=barcode;
        this.name=name;
        this.unit=unit;
        this.price=price;
    }
}