//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.orm.enums.PersonsType;
import com.kenvix.studentmanager.orm.tables.daos.PersonsDao;
import com.kenvix.studentmanager.orm.tables.pojos.Persons;
import org.jooq.Configuration;
import static com.kenvix.studentmanager.orm.tables.Persons.PERSONS;

import java.util.*;

public class PersonModel extends PersonsDao implements BaseModel {
    public static final PersonModel INSTANCE = new PersonModel();

    private PersonModel() {

    }

    public List<Persons> findByType(PersonsType type) {
        return getDsl().select().from(PERSONS).where(PERSONS.TYPE.eq(type)).fetch().into(Persons.class);
    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
