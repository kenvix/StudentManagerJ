//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.orm.tables.daos.ClassesDao;
import com.kenvix.studentmanager.orm.tables.daos.StudentClassMapDao;
import org.jooq.Configuration;

public class StudentClassMapModel extends StudentClassMapDao implements BaseModel {
    public static final StudentClassMapModel INSTANCE = new StudentClassMapModel();

    private StudentClassMapModel() {

    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
