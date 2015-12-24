package org.glassfish.jersey.examples.helloworld;

/**
 * Created by liujia on 12/22/15.
 */

import java.util.List;

public interface InputRepository {

    List<Item> findinput();

    void insertinput(String barcode);

}
