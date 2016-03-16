package org.glassfish.jersey.examples.helloworld;

/**
 * Created by liujia on 3/15/16.
 */
public class Boss {

    private String bossName;
    private Integer bossId;
    private Company company;

    public Boss(Integer BossId, String BossName) {
        this.bossId = BossId;
        this.bossName = BossName;
    }

    public Integer getBossId() {
        return bossId;
    }

    public String getBossName() {
        return bossName;
    }

    public Company getCompany() {
        return company;
    }
}
