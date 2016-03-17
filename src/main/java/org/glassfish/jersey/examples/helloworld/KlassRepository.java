package org.glassfish.jersey.examples.helloworld;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liujia on 3/13/16.
 */
public interface KlassRepository {
    List<Klass> findKlass();


    void insertKlass(Klass klass);

    Klass selectKlassById(@Param("classId") Integer klassId);

    void updateKlass(@Param("class") Klass klass);

    void deleteKlassById(@Param("classId") Integer klassId);

    void deleteKlassAndRelationshipWithStudent(@Param("classId") Integer klassId);
}
