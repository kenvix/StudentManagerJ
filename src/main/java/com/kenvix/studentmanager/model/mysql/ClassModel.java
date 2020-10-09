//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.contacts.ClassWithMaster;
import com.kenvix.studentmanager.orm.tables.daos.ClassesDao;
import com.kenvix.studentmanager.orm.tables.pojos.Classes;
import org.jooq.Configuration;
import static com.kenvix.studentmanager.orm.tables.ClassCourseMap.CLASS_COURSE_MAP;
import static com.kenvix.studentmanager.orm.tables.Persons.PERSONS;
import static com.kenvix.studentmanager.orm.tables.Classes.CLASSES;

import java.util.List;

public class ClassModel extends ClassesDao implements BaseModel {
    public static final ClassModel INSTANCE = new ClassModel();

    private ClassModel() {

    }

    public List<ClassWithMaster> fetchAllWithMaster() {
        return getDsl()
                .select(CLASSES.ID, CLASSES.MASTER_ID, PERSONS.NAME.as("master_name"))
                .from(CLASSES)
                .innerJoin(PERSONS)
                .on(PERSONS.ID.eq(CLASSES.MASTER_ID))
                .fetchInto(ClassWithMaster.class);
    }

    public List<ClassWithMaster> fetchWithMasterByClassId(Long id) {
        return getDsl()
                .select(CLASSES.ID, CLASSES.MASTER_ID, PERSONS.NAME.as("master_name"))
                .from(CLASSES)
                .innerJoin(PERSONS)
                .on(PERSONS.ID.eq(CLASSES.MASTER_ID))
                .where(CLASSES.ID.eq(id))
                .fetchInto(ClassWithMaster.class);
    }

    public List<Classes> fetchByPersonId(Long id) {
        return getDsl()
                .select(CLASSES.ID, CLASSES.MASTER_ID)
                .from(PERSONS)
                .where(PERSONS.ID.eq(id))
                .fetchInto(Classes.class);
    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
