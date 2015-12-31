package org.glassfish.jersey.examples.helloworld;

import java.util.List;

public class Receipt{
    public List<Gift> GiftItems;
    public List<Shopping> priceItems;
    public float total;
    public float save;

    public Receipt(List<Gift> GiftItems, List<Shopping> priceItems){
        this.GiftItems=GiftItems;
        this.priceItems=priceItems;
        float sum=0;
        for(int i=1;i<GiftItems.size();i++)
            sum=sum+GiftItems.get(i).saveprice;
        this.save=sum;
        sum=0;
        for(int i=1;i<priceItems.size();i++)
            sum=sum+priceItems.get(i).totalprice;
        this.total=sum;
    }
}
