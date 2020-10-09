//--------------------------------------------------
// Class StudentModel
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.studentmanager.model.mysql;

import com.kenvix.studentmanager.AppConstants;
import com.kenvix.studentmanager.orm.tables.daos.PapersDao;
import com.kenvix.studentmanager.orm.tables.pojos.Papers;
import org.jooq.Configuration;

import java.util.List;

public class PaperModel extends PapersDao implements BaseModel {
    public static final PaperModel INSTANCE = new PaperModel();

    private PaperModel() {

    }

    @Override
    public Configuration configuration() {
        return AppConstants.INSTANCE.getJooqConfiguration();
    }
}
