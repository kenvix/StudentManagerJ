/*
 * This file is generated by jOOQ.
 */
package com.kenvix.studentmanager.orm.tables.records;


import com.kenvix.studentmanager.orm.tables.Courses;
import com.kenvix.studentmanager.orm.tables.interfaces.ICourses;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.13.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoursesRecord extends UpdatableRecordImpl<CoursesRecord> implements Record2<Long, String>, ICourses {

    private static final long serialVersionUID = -360498024;

    /**
     * Setter for <code>courses.id</code>.
     */
    @Override
    public CoursesRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>courses.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>courses.name</code>.
     */
    @Override
    public CoursesRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>courses.name</code>.
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Courses.COURSES.ID;
    }

    @Override
    public Field<String> field2() {
        return Courses.COURSES.NAME;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public CoursesRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public CoursesRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public CoursesRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ICourses from) {
        setId(from.getId());
        setName(from.getName());
    }

    @Override
    public <E extends ICourses> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoursesRecord
     */
    public CoursesRecord() {
        super(Courses.COURSES);
    }

    /**
     * Create a detached, initialised CoursesRecord
     */
    public CoursesRecord(Long id, String name) {
        super(Courses.COURSES);

        set(0, id);
        set(1, name);
    }
}
