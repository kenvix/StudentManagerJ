//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.orm.tables.daos.ClassesDao;
import com.kenvix.studentmanager.orm.tables.daos.CoursesDao;
import org.jooq.Configuration;

public class ClassesModel extends ClassesDao implements BaseModel {
    public static final ClassesModel INSTANCE = new ClassesModel();

    private ClassesModel() {

    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
