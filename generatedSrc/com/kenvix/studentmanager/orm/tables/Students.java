/*
 * This file is generated by jOOQ.
 */
package com.kenvix.studentmanager.orm.tables;


import com.kenvix.studentmanager.orm.DefaultSchema;
import com.kenvix.studentmanager.orm.Keys;
import com.kenvix.studentmanager.orm.enums.StudentsStatus;
import com.kenvix.studentmanager.orm.tables.records.StudentsRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * View 'student_manager.people_student' references invalid table(s) or column(s) 
 * or function(s) or definer/invoker of view lack rights to use them
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.13.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Students extends TableImpl<StudentsRecord> {

    private static final long serialVersionUID = 807917464;

    /**
     * The reference instance of <code>students</code>
     */
    public static final Students STUDENTS = new Students();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StudentsRecord> getRecordType() {
        return StudentsRecord.class;
    }

    /**
     * The column <code>students.person_id</code>.
     */
    public final TableField<StudentsRecord, Long> PERSON_ID = createField(DSL.name("person_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>students.is_graduable</code>.
     */
    public final TableField<StudentsRecord, Byte> IS_GRADUABLE = createField(DSL.name("is_graduable"), org.jooq.impl.SQLDataType.TINYINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>students.status</code>.
     */
    public final TableField<StudentsRecord, StudentsStatus> STATUS = createField(DSL.name("status"), org.jooq.impl.SQLDataType.VARCHAR(9).nullable(false).defaultValue(org.jooq.impl.DSL.inline("normal", org.jooq.impl.SQLDataType.VARCHAR)).asEnumDataType(com.kenvix.studentmanager.orm.enums.StudentsStatus.class), this, "");

    /**
     * Create a <code>students</code> table reference
     */
    public Students() {
        this(DSL.name("students"), null);
    }

    /**
     * Create an aliased <code>students</code> table reference
     */
    public Students(String alias) {
        this(DSL.name(alias), STUDENTS);
    }

    /**
     * Create an aliased <code>students</code> table reference
     */
    public Students(Name alias) {
        this(alias, STUDENTS);
    }

    private Students(Name alias, Table<StudentsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Students(Name alias, Table<StudentsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("View 'student_manager.people_student' references invalid table(s) or column(s) or function(s) or definer/invoker of view lack rights to use them"), TableOptions.table());
    }

    public <O extends Record> Students(Table<O> child, ForeignKey<O, StudentsRecord> key) {
        super(child, key, STUDENTS);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<StudentsRecord> getPrimaryKey() {
        return Keys.KEY_STUDENTS_PRIMARY;
    }

    @Override
    public List<UniqueKey<StudentsRecord>> getKeys() {
        return Arrays.<UniqueKey<StudentsRecord>>asList(Keys.KEY_STUDENTS_PRIMARY);
    }

    @Override
    public Students as(String alias) {
        return new Students(DSL.name(alias), this);
    }

    @Override
    public Students as(Name alias) {
        return new Students(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Students rename(String name) {
        return new Students(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Students rename(Name name) {
        return new Students(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Byte, StudentsStatus> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}