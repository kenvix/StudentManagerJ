//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.orm.tables.daos.ClassesDao;
import org.jooq.Configuration;

public class ClassModel extends ClassesDao implements BaseModel {
    public static final ClassModel INSTANCE = new ClassModel();

    private ClassModel() {

    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
