//--------------------------------------------------
// Class PaperWithPerson
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.contacts;

import com.kenvix.studentmanager.orm.tables.pojos.Papers;

public class PaperWithPerson extends Papers {
    private String personName;

    public String getPersonName() {
        return personName;
    }

    public PaperWithPerson setPersonName(String personName) {
        this.personName = personName;
        return this;
    }
}
