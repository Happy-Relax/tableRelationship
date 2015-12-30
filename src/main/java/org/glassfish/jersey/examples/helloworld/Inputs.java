package org.glassfish.jersey.examples.helloworld;

public class Inputs {
    public String barcode;
    public String name;
    public String unit;
    public float price;
    public long count;


    public Inputs(String barcode, String name, Float price, String unit, Long count){
        this.barcode=barcode;
        this.name=name;
        this.unit=unit;
        this.price=price;
        this.count=count;
    }


}

