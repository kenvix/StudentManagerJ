//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

public class StudentModel implements BaseModel {
    public static final StudentModel INSTANCE = new StudentModel();

    private StudentModel() {
    }
}
