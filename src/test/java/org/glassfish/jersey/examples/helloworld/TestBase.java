package org.glassfish.jersey.examples.helloworld;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import javax.ws.rs.core.Application;

import static org.mockito.Mockito.mock;

//public class TestBase extends JerseyTest {
//    protected ReceiptRepository receiptRepository = mock(ReceiptRepository.class);
//    protected InputRepository inputRepository = mock(InputRepository.class);
//
//    @Override
//    protected Application configure() {
//
////        enable(TestProperties.LOG_TRAFFIC);
//        enable(TestProperties.DUMP_ENTITY);
////        enable(TestProperties.RECORD_LOG_LEVEL);
//
//        return new ResourceConfig().register(new AbstractBinder() {
//
//
//            @Override
//            protected void configure() {
//
//                bind(receiptRepository).to(ReceiptRepository.class);
//                bind(inputRepository).to(InputRepository.class);
//            }
//        }).packages("org.glassfish.jersey.examples.helloworld");
//    }
//}
