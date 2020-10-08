//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.orm.tables.daos.PersonsDao;
import org.jooq.Configuration;

public class PersonModel extends PersonsDao implements BaseModel {
    public static final PersonModel INSTANCE = new PersonModel();

    private PersonModel() {

    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
