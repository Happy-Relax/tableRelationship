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
 * Created by liujia on 3/16/16.
 */
public class TeacherTest {
    private SqlSessionFactory sqlSessionFactory;
    private TeacherRepository teacherRepository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);
        teacherRepository = session.getMapper(TeacherRepository.class);
    }
    @Test
    public void should_select_a_record_when_student_not_exsit(){
        Integer teacherId=1;
        Teacher teacher =teacherRepository.selectById(teacherId);

        assertThat(teacher.getTeacherName(),is("TJ"));
    }
    @Test
    public void should_insert_a_record_when_student_not_exsit(){
        Integer teacherId=3;
        String teacherName="LX";
        Teacher teacher =new Teacher(teacherId,teacherName,null);
        teacherRepository.insertTeacher(teacher);

        assertThat(teacherRepository.selectById(teacherId).getTeacherName(),is("LX"));
    }

    @Test
    public void should_update_a_record_when_student_not_exsit(){
        Integer teacherId=1;
        Teacher teacher=teacherRepository.selectById(teacherId);
        String teacherName="LM";
        teacher.setTeacherName(teacherName);
        teacherRepository.updateTeacher(teacher);

        assertThat( teacherRepository.selectById(teacherId).getTeacherName(),is(teacherName));
    }

    @Test
    public void should_delete_a_record_when_student_not_exsit(){
        Integer teacherId=1;
        teacherRepository.deleteTeacherById(teacherId);
        assertThat( teacherRepository.selectById(teacherId),is(nullValue()));
    }

    @Test
    public void should_select_a_record_when_student_exsit(){
        Integer teacherId=1;
        Teacher teacher =teacherRepository.selectById(teacherId);

        assertThat(teacher.getStudents().get(0).getStudentId(),is(1));
    }
    @Test
    public void should_create_relationship_with_student(){

        Integer teacherId=2;
        Integer studentId=2;
        teacherRepository.createRelationship(teacherId,studentId);

        assertThat( teacherRepository.selectById(teacherId).getStudents().get(0).getStudentId(),is(studentId));
    }
    @Test
    public void should_update_a_record_when_student_exsit(){
        Integer teacherId=1;
        Integer studentId=2;

        teacherRepository.updateRelationship(teacherId,studentId);

        assertThat( teacherRepository.selectById(teacherId).getStudents().get(0).getStudentId(),is(studentId));;
    }
    @Test
    public void should_delete_relationship_with_student(){
        Integer teacherId=1;
        teacherRepository.deleteRelationshipWithStudent(teacherId);
        assertThat( teacherRepository.selectById(teacherId),is(nullValue()));
    }
    @Test
    public void should_update_teacher_and_students(){
        Integer teacherId=1;
        String studentName="LJ";

        Teacher teacher=teacherRepository.selectById(teacherId);
        teacher.getStudents().get(0).setStudentName(studentName);
        teacherRepository.updateTeacher(teacher);
        assertThat( teacherRepository.selectById(teacherId).getStudents().get(0).getStudentName(),is(studentName));;
    }

    @Test
    public void should_get_error_when_primary_key_create_as_null(){
        Integer teacherId=null;
        Teacher teacher=new Teacher(teacherId,"test",null);
        Boolean except=false;
        try {
            teacherRepository.insertTeacher(teacher);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_primary_key_create_not_unique(){
        Integer teacherId=1;
        Teacher teacher=new Teacher(teacherId,"test",null);
        Boolean except=false;
        try {
            teacherRepository.insertTeacher(teacher);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_delete(){
        Integer teacherId=1;
        Boolean except=false;
        try {
            teacherRepository.deleteTeacherById(teacherId);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }

    @Test
    public void should_get_error_when_create_relationship_when_teacher_primary_key_not_exist(){

        Integer teacherId=3;
        Integer studentId=1;

        Boolean except=false;
        try {
            teacherRepository.createRelationship(teacherId,studentId);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
    @Test
    public void should_get_error_when_update_relationship_when_student_primary_key_not_exist(){

        Integer teacherId=1;
        Integer studentId=3;

        Boolean except=false;
        try {
            teacherRepository.updateRelationship(teacherId,studentId);
        }catch (PersistenceException ex){
            except=true;
        }
        assertThat( except,is(true));
    }
}
