package org.glassfish.jersey.examples.helloworld;

import org.apache.ibatis.annotations.Param;

/**
 * Created by liujia on 3/15/16.
 */
public interface StudentRepository {
    Student selectById(@Param("studentId") Integer studentId);

    void updateById(@Param("studentId") Integer studentId,@Param("studentName") String studentName);

    void deleteStudentById(@Param("studentId") Integer studentId);

    void updateRelationshipWithKlass(@Param("classId") Integer klassId, @Param("studentId") Integer studentId);

    void createRelationshipWithTeacher(@Param("teacherId") Integer teacherId, @Param("studentId") Integer studentId);

    void updateRelationshipWithTeacher(@Param("teacherId") Integer teacherId, @Param("studentId") Integer studentId);

    void deleteRelationshipWithTeacher(@Param("studentId") Integer studentId);
}
