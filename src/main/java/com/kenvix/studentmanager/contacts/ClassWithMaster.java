//--------------------------------------------------
// Class Classes
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.contacts;

public class ClassWithMaster extends com.kenvix.studentmanager.orm.tables.pojos.Classes {
    private String masterName;
    private Integer studentNum;

    public String getMasterName() {
        return masterName;
    }

    public ClassWithMaster setMasterName(String masterName) {
        this.masterName = masterName;
        return this;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public ClassWithMaster setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
        return this;
    }
}
