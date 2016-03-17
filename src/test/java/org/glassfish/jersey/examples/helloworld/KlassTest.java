package org.glassfish.jersey.examples.helloworld;

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

public class KlassTest {
    private SqlSessionFactory sqlSessionFactory;
    private KlassRepository klassRepository;
    private StudentRepository studentRepository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);
        klassRepository = session.getMapper(KlassRepository.class);
        studentRepository = session.getMapper(StudentRepository.class);

    }
    @Test
    public void should_insert_a_record_into_class_table_when_student_not_exsit(){
        Integer klassId=3;
        String klassName="english";
        Klass klass=new Klass(klassId,klassName,null);
        klassRepository.insertKlass(klass);

        assertThat(klassRepository.findKlass().get(2).getKlassId(),is(klassId));
    }

    @Test
    public void should_return_a_record_of_class_table_when_student_table_has_no_record(){

        Integer klassId=1;
        String klassName="math";
        Klass klass=klassRepository.selectKlassById(klassId);

        assertThat( klass.getKlassId(),is(klassId));
        assertThat( klass.getKlassName(),is(klassName));
    }

    @Test
    public void should_update_a_record_when_student_not_exsit(){
        Integer klassId=1;
        String klassName="chinese";
        Klass klass=new Klass(klassId,klassName,null);
        klassRepository.updateKlass(klass);

        assertThat( klassRepository.selectKlassById(klassId).getKlassName(),is(klassName));
    }

    @Test
    public void should_delete_a_record_when_student_not_exsit(){
        Integer klassId=1;
        klassRepository.deleteKlassById(klassId);
        assertThat( klassRepository.findKlass().size(),is(1));
    }

    @Test
    public void should_return_a_record_of_class_table_when_student_table_exsit(){

        Integer klassId=1;
        String studentName="LJY";
        Klass klass=klassRepository.selectKlassById(klassId);

        assertThat( klass.getStudents().get(0).getStudentName(),is(studentName));
    }
    @Test
    public void should_delete_record_and_relationship_with_student(){

        Integer klassId=1;
        klassRepository.deleteKlassAndRelationshipWithStudent(klassId);

        assertThat( klassRepository.selectKlassById(klassId),is(nullValue()));
        assertThat( studentRepository.selectById(1),is(nullValue()));
    }

    @Test
    public void should_update_klass_and_students(){

        Integer klassId=1;
        Klass klass=klassRepository.selectKlassById(klassId);
        String studentName="LJ";
        klass.getStudents().get(0).setStudentName(studentName);
        klassRepository.updateKlass(klass);

        assertThat(klassRepository.selectKlassById(klassId).getStudents().get(0).getStudentName(),is(studentName));
    }
}