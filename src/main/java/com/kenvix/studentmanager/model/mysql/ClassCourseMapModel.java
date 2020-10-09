//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.orm.tables.daos.CoursesDao;
import org.jooq.Configuration;

public class ClassCourseMapModel extends CoursesDao implements BaseModel {
    public static final ClassCourseMapModel INSTANCE = new ClassCourseMapModel();

    private ClassCourseMapModel() {
        var a = 0;
        var b = 0;
        b = 1;
    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
