//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.orm.tables.daos.CoursesDao;
import com.kenvix.studentmanager.orm.tables.daos.PersonsDao;
import org.jooq.Configuration;

public class CourseModel extends CoursesDao implements BaseModel {
    public static final CourseModel INSTANCE = new CourseModel();

    private CourseModel() {

    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
