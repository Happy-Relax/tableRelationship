package org.glassfish.jersey.examples.helloworld;

import org.glassfish.jersey.internal.util.collection.UnsafeValue;

import java.util.List;

/**
 * Created by liujia on 3/16/16.
 */
public class Teacher {
    private String teacherName;
    private Integer teacherId;
    private List<Student> students;

    public Teacher(){}

    public Teacher(Integer teacherId, String teacherName,List<Student> students) {
        this.teacherId=teacherId;
        this.teacherName=teacherName;
        this.students=students;

    }

    public String getTeacherName() {
        return teacherName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
