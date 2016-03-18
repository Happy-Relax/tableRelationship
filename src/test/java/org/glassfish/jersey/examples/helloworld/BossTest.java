package org.glassfish.jersey.examples.helloworld;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by liujia on 3/15/16.
 */
public class BossTest {
    private SqlSessionFactory sqlSessionFactory;
    private BossRepository bossRepository;
    private CompanyRepository companyRepository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis.xml";

        Reader reader = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);
        bossRepository = session.getMapper(BossRepository.class);
        companyRepository = session.getMapper(CompanyRepository.class);

    }
    @Test
    public void should_select_a_record_when_record_exsit_and_company_is_not_exsit(){
        assertThat( bossRepository.selectBoss().get(0).getBossId(),is(1));
    }
    @Test
    public void should_create_new_record_when_record_not_exsit_and_company_is_not_exsit(){
        Integer bossId=2;
        String bossName="two";

        Boss boss =new Boss(bossId,bossName);
        bossRepository.insertBoss(boss);

        assertThat( bossRepository.selectBoss().get(1).getBossId(),is(bossId));
    }
    @Test
    public void should_updata_a_record_when_company_not_exsit(){
        Integer bossId=1;
        String bossNewName="2";
        Boss boss =new Boss(bossId,bossNewName);
        bossRepository.updateBoss(boss);
        assertThat( bossRepository.selectBoss().get(0).getBossName(),is(bossNewName));
    }
//    @Test
//    public void should_delete_a_record_when_company_not_exist(){
//        Integer bossId=1;
//        String bossNewName="2";
//        Boss boss =new Boss(bossId,bossNewName);
//        bossRepository.deleteBossById(boss);
//        assertThat( bossRepository.selectBoss().size(),is(0));
//    }

    @Test
    public void should_select_a_record_when_company_exsit(){

        Integer companyId=1;
        String companyName="baidu";
        Company company =new Company(companyId,companyName);

        assertThat( bossRepository.selectBoss().get(0).getCompany().getCompanyId(),is(companyId));
        Integer bossId=1;
        assertThat( bossRepository.selectBossById(bossId).getCompany().getCompanyId(),is(companyId));
    }

    @Test
    public void should_delete_a_record_and_relathionship_when_company_exsit(){
        Integer bossId=1;
        bossRepository.deleteBossAndRelationshipWithCompany(bossId);
        assertThat( bossRepository.selectBoss().size(),is(0));
        assertThat(companyRepository.selectCompanyById(1),is(nullValue()));
    }
    @Test
    public void should_updata_boss_and_company(){
        Integer bossId=1;
        Boss boss =bossRepository.selectBossById(bossId);
        String companyName="one";
        boss.getCompany().setCompanyName(companyName);
        bossRepository.updateBoss(boss);
        assertThat( bossRepository.selectBossById(bossId).getCompany().getCompanyName(),is(companyName));
    }
    @Test
    public void should_get_error_when_primary_key_create_as_null(){
        Integer bossId=null;
        Boss boss=new Boss(bossId,"test");
        Boolean except=false;
        try {
            bossRepository.insertBoss(boss);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_primary_key_create_not_unique(){
        Integer bossId=1;
        Boss boss=new Boss(bossId,"test");
        Boolean except=false;
        try {
            bossRepository.insertBoss(boss);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_delete(){
        Integer bossId=1;
        Boss boss=new Boss(bossId,"test");
        Boolean except=false;
        try {
            bossRepository.deleteBossById(boss);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }


}