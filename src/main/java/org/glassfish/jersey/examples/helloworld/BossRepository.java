package org.glassfish.jersey.examples.helloworld;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liujia on 3/15/16.
 */
public interface BossRepository {

      void insertBoss(Boss boss);

      List<Boss> selectBoss();
      void updateBoss(@Param("boss") Boss boss);

      void deleteBossById(Boss boss);

      List<Boss> selectCompanyByBoss();

      void deleteBossAndRelationshipWithCompany(@Param("bossId") Integer bossId);

      Boss selectBossById(Integer bossId);

}
