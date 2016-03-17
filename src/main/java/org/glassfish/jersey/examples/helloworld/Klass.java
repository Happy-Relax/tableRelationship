package org.glassfish.jersey.examples.helloworld;

import java.util.List;

/**
 * Created by liujia on 3/13/16.
 */
public class Klass {
    private Integer klassId;
    private String klassName;
    private List<Student> students=null;

    public Klass(){}

    public Klass(Integer klassId, String klassName, List<Student> students) {
        this.klassId=klassId;
        this.klassName=klassName;
        this.students=students;

    }

    public Integer getKlassId() {
        return this.klassId;
    }

    public String getKlassName() {
        return klassName;
    }



    public List<Student> getStudents() {
        return this.students;
    }

    public void setKlassName(String klassName) {
        this.klassName = klassName;
    }
}
