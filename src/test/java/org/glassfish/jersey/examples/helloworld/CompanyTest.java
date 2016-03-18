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
public class CompanyTest {
    private SqlSessionFactory sqlSessionFactory;
    private CompanyRepository companyRepository;
    private BossRepository bossRepository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis.xml";

        Reader reader = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);
        companyRepository = session.getMapper(CompanyRepository.class);
        bossRepository = session.getMapper(BossRepository.class);

    }
    @Test
    public void should_select_a_record_when_record_is_exsit_and_Boss_is_not_exsit(){
        int companyId=1;
        assertThat( companyRepository.selectCompanyById(companyId).getCompanyName(),is("baidu"));
    }
    @Test
    public void should_create_new_record_when_record_is_not_exsit_and_Boss_is_not_exsit(){

        Integer companyId=3;
        String companyName="Tencent";
        Company company =new Company(companyId,companyName);
        companyRepository.insertCompany(company);

        assertThat( companyRepository.selectCompany().get(2).getCompanyId(),is(companyId));
    }
    @Test
    public void should_updata_a_record_when_Boss_is_not_exsit(){

        Integer companyId=1;
        String companyName="two";
        Company company =new Company(companyId,companyName);
        companyRepository.updateCompany(company);
        assertThat( companyRepository.selectCompanyById(companyId).getCompanyName(),is(companyName));
    }
    @Test
    public void should_delete_a_record_when_Boss_is_not_exsit(){
        Integer companyId=1;
        String companyName="two";
        Company company =new Company(companyId,companyName);
        companyRepository.deleteCompanyById(company);
        assertThat( companyRepository.selectCompany().size(),is(1));
    }
    @Test
    public void should_select_a_record_when_Boss_exsit(){

        Integer bossId=1;

        assertThat( companyRepository.selectCompany().get(0).getBoss().getBossId(),is(bossId));
    }
    @Test
    public void should_create_relationship(){

        Integer bossId=2;
        Integer companyId=2;
        Boss boss=new Boss(bossId,"two");
        bossRepository.insertBoss(boss);
        companyRepository.updateRelationship(bossId,companyId);

        assertThat( companyRepository.selectCompany().get(1).getBossId(),is(bossId));
    }

    @Test
    public void should_delete_relationship_with_Boss(){

        Integer companyId=1;
        companyRepository.updateRelationship(null,companyId);

        assertThat( companyRepository.selectCompany().get(1).getBossId(),is(nullValue()));
    }
    @Test
    public void should_update_company_and_boss(){
        Integer companyId=1;
        String bossName="mayun";
        Company company=companyRepository.selectCompanyById(companyId);
        company.getBoss().setBossName(bossName);
        companyRepository.updateCompany(company);

        assertThat( companyRepository.selectCompanyById(companyId).getBoss().getBossName(),is(bossName));
    }

    @Test
    public void should_get_error_when_primary_key_create_as_null(){
        Integer companyId=null;
        Company company=new Company(companyId,"test");
        Boolean except=false;
        try {
            companyRepository.insertCompany(company);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_primary_key_create_not_unique(){
        Integer companyId=1;
        Company company=new Company(companyId,"test");
        Boolean except=false;
        try {
            companyRepository.insertCompany(company);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_create_foreign_not_exist_in_Boss(){
        Integer companyId=3;
        Integer bossId=4;
        Company company=new Company(companyId,"test",bossId);
        Boolean except=false;
        try {
            companyRepository.insertCompany(company);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_update_foreign_not_exist_in_Boss(){
        Integer companyId=3;
        Integer bossId=4;
        Company company=new Company(companyId,"test",bossId);
        Boolean except=false;
        try {
            companyRepository.updateCompany(company);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_update_unique_key_not_unique(){
        Integer companyId=3;
        Integer bossId=1;
        Company company=new Company(companyId,"test",bossId);
        Boolean except=false;
        try {
            companyRepository.updateCompany(company);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_create_unique_key_not_unique(){
        Integer companyId=3;
        Integer bossId=1;
        Company company=new Company(companyId,"test",bossId);
        Boolean except=false;
        try {
            companyRepository.insertCompany(company);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
}
