package org.glassfish.jersey.examples.helloworld;

public class Shopping{
    public String barcode;
    public String name;
    public String unit;
    public float price;
    public long count;
    public float totalprice;



    public Shopping(Inputs inputs){
        this.barcode=inputs.barcode;
        this.name=inputs.name;
        this.unit=inputs.unit;
        this.price=inputs.price;
        this.count=inputs.count;
        this.totalprice=(this.count-(this.count/3))*this.price;

    }


    public String getBarcode(){
        return this.barcode;
    }

    public String getName(){
        return this.name;
    }

    public String getUnit(){
        return this.unit;
    }

    public float getPrice(){
        return this.price;
    }


}

