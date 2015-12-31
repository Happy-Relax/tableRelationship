package org.glassfish.jersey.examples.helloworld;

/**
 * Created by liujia on 12/22/15.
 */

import java.util.List;

public interface ReceiptRepository {

    List<Item> findItem();

    List<Shopping> findShoppinglist();
    List<Inputs> findinputs();
    List<Save> findsave();
    List<Gift> findgiftlist();

    void insertshoppinglist(Shopping shopping);
    void insertgiftlist(Gift gift);
    void insertItem(Item item);

}
