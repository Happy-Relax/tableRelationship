package org.glassfish.jersey.examples.helloworld;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liujia on 3/15/16.
 */
public interface CompanyRepository {
    List<Company> selectCompany();

    void insertCompany(Company company);

    void updateCompany(@Param("company") Company company);

    void deleteCompanyById(Company company);

    void updateRelationship(@Param ("bossId") Integer bossId,@Param ("companyId") Integer companyId);

    Company selectCompanyById(@Param ("companyId") int companyId);
}
