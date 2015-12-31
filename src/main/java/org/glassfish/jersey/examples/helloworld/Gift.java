package org.glassfish.jersey.examples.helloworld;

public class Gift {
    public String barcode;
    public String name;
    public String unit;
    public float saveprice;
    public long savenumber;

    public Gift() {

    }

    public Gift(Save save) {
        this.barcode = save.barcode;
        this.name = save.name;
        this.unit = save.unit;
        this.savenumber= save.count/3;
        this.saveprice =save.price*(save.count/3);

    }


}
