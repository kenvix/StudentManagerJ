//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.orm.tables.daos.CoursesDao;
import com.kenvix.studentmanager.orm.tables.daos.PapersDao;
import org.jooq.Configuration;

public class PapersModel extends PapersDao implements BaseModel {
    public static final PapersModel INSTANCE = new PapersModel();

    private PapersModel() {

    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
