package org.glassfish.jersey.examples.helloworld;

/**
 * Created by liujia on 12/22/15.
 */

import java.util.List;
import java.util.Map;
public interface ItemRepository {

    List<Item> findItem();

    void insertItem(Item item);

}
