package org.glassfish.jersey.examples.helloworld;

/**
 * Created by liujia on 12/21/15.
 */
public class item{
    public String barcode;
    public String name;
    public String unit;
    public float price;

    public item(String n_barcode,String n_name,String n_unit,float n_price){
        barcode=n_barcode;
        name=n_name;
        unit=n_unit;
        price=n_price;
    }
}