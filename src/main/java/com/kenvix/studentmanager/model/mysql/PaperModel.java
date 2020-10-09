//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.contacts.ClassWithMaster;
import com.kenvix.studentmanager.contacts.PaperWithPerson;
import com.kenvix.studentmanager.orm.tables.daos.PapersDao;
import com.kenvix.studentmanager.orm.tables.pojos.Papers;
import org.jooq.Configuration;

import java.util.List;

import static com.kenvix.studentmanager.orm.tables.Classes.CLASSES;
import static com.kenvix.studentmanager.orm.tables.Papers.PAPERS;
import static com.kenvix.studentmanager.orm.tables.Persons.PERSONS;

public class PaperModel extends PapersDao implements BaseModel {
    public static final PaperModel INSTANCE = new PaperModel();

    private PaperModel() {

    }

    public List<PaperWithPerson> fetchAllWithPerson() {
        return getDsl()
                .select(PAPERS.ID, PAPERS.PERSON_ID, PAPERS.TITLE, PERSONS.NAME.as("person_name"))
                .from(PAPERS)
                .innerJoin(PERSONS)
                .on(PERSONS.ID.eq(PAPERS.PERSON_ID))
                .fetchInto(PaperWithPerson.class);
    }

    public List<PaperWithPerson> fetchWithPersonByPersonId(Long personId) {
        return getDsl()
                .select(PAPERS.ID, PAPERS.PERSON_ID, PAPERS.TITLE, PERSONS.NAME.as("person_name"))
                .from(PAPERS)
                .innerJoin(PERSONS)
                .on(PERSONS.ID.eq(PAPERS.PERSON_ID))
                .where(PAPERS.PERSON_ID.eq(personId))
                .fetchInto(PaperWithPerson.class);
    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
