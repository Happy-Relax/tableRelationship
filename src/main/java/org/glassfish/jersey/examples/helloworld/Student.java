package org.glassfish.jersey.examples.helloworld;

import org.glassfish.jersey.internal.util.collection.UnsafeValue;

import java.util.List;

/**
 * Created by liujia on 3/15/16.
 */
public class Student {
    private Integer studentId;
    private String studentName;
    private Integer klassId;
    private Klass klass;
    private List<Teacher> teachers;

    public Student(){}

    public Student(Integer studentId, String studentName, Klass klass, List<Teacher> teachers) {
        this.studentId=studentId;
        this.studentName=studentName;
        this.klass=klass;
        this.teachers=teachers;
        if(klass!=null){
        this.klassId=klass.getKlassId();
        }
    }




    public String getStudentName() {
        return studentName;
    }

    public Klass getKlass() {
        return klass;
    }

    public Integer getKlassId() {
        return klassId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
