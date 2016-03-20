package org.glassfish.jersey.examples.helloworld;

/**
 * Created by liujia on 3/15/16.
 */
public class Company {
    private Integer companyId;
    private String companyName;
    private Integer bossId;
    private Boss boss;

    public Company(){}

    public Company(Integer companyId, String companyName) {
        this.companyId=companyId;
        this.companyName=companyName;
        this.bossId=null;
    }

    public Company(Integer companyId, String companyName, Integer bossId) {
        this.companyName=companyName;
        this.companyId=companyId;
        this.bossId=bossId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (companyId != null ? !companyId.equals(company.companyId) : company.companyId != null) return false;
        if (companyName != null ? !companyName.equals(company.companyName) : company.companyName != null) return false;
        return bossId != null ? bossId.equals(company.bossId) : company.bossId == null;

    }

    @Override
    public int hashCode() {
        int result = companyId != null ? companyId.hashCode() : 0;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (bossId != null ? bossId.hashCode() : 0);
        return result;
    }

    public Boss getBoss() {
        return boss;
    }

    public Integer getBossId() {
        return bossId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }


    public void setBossId(Integer bossId) {
        this.bossId = bossId;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }
}
