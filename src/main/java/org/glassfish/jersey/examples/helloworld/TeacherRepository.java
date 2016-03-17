package org.glassfish.jersey.examples.helloworld;

import org.apache.ibatis.annotations.Param;

/**
 * Created by liujia on 3/16/16.
 */
public interface TeacherRepository {
    Teacher selectById(@Param("teacherId") Integer teacherId);

    void updateTeacher(@Param("teacher") Teacher teacher);

    void deleteTeacherById(@Param("teacherId") Integer teacherId);

    void createRelationship(@Param("teacherId") Integer teacherId, @Param("studentId") Integer studentId);

    void updateRelationship(@Param("teacherId") Integer teacherId, @Param("studentId") Integer studentId);

    void deleteRelationshipWithStudent(@Param("teacherId") Integer teacherId);
}
