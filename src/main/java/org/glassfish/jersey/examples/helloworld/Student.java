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
}
