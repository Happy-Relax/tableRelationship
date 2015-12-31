package org.glassfish.jersey.examples.helloworld;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class UsersRepositoryTest {
    private SqlSessionFactory sqlSessionFactory;
    private ReceiptRepository receiptRepository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis.xml";
        
        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);
        receiptRepository = session.getMapper(ReceiptRepository.class);

    }
    
    @After
    public void tearDown(){
        session.rollback();
        session.close();
        
    }
        

    @Test
    public void should_find_all_users() {
        List<Item> users1 = receiptRepository.findItem();
        assertThat(users1.size(), is(6));
        
    }


    
    
}
