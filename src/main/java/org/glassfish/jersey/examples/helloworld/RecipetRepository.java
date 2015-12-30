package org.glassfish.jersey.examples.helloworld;

/**
 * Created by liujia on 12/22/15.
 */

import java.util.List;
import java.util.Map;
public interface RecipetRepository {

    List<Item> findItem();

    List<Shopping> findShoppinglist();
    List<Inputs> findinputs();
    List<Save> findsave();

    void insertshoppinglist(Shopping shopping);
    void insertgiftlist(Giftlist giftlist);
    void insertItem(Item item);

}
