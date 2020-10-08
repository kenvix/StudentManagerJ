/*
 * This file is generated by jOOQ.
 */
package com.kenvix.studentmanager.orm.tables;


import com.kenvix.studentmanager.orm.DefaultSchema;
import com.kenvix.studentmanager.orm.Indexes;
import com.kenvix.studentmanager.orm.Keys;
import com.kenvix.studentmanager.orm.tables.records.StudentClassMapRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
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
public class StudentClassMap extends TableImpl<StudentClassMapRecord> {

    private static final long serialVersionUID = 1261684171;

    /**
     * The reference instance of <code>student_class_map</code>
     */
    public static final StudentClassMap STUDENT_CLASS_MAP = new StudentClassMap();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StudentClassMapRecord> getRecordType() {
        return StudentClassMapRecord.class;
    }

    /**
     * The column <code>student_class_map.id</code>.
     */
    public final TableField<StudentClassMapRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>student_class_map.student_id</code>.
     */
    public final TableField<StudentClassMapRecord, Long> STUDENT_ID = createField(DSL.name("student_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>student_class_map.class_id</code>.
     */
    public final TableField<StudentClassMapRecord, Long> CLASS_ID = createField(DSL.name("class_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>student_class_map</code> table reference
     */
    public StudentClassMap() {
        this(DSL.name("student_class_map"), null);
    }

    /**
     * Create an aliased <code>student_class_map</code> table reference
     */
    public StudentClassMap(String alias) {
        this(DSL.name(alias), STUDENT_CLASS_MAP);
    }

    /**
     * Create an aliased <code>student_class_map</code> table reference
     */
    public StudentClassMap(Name alias) {
        this(alias, STUDENT_CLASS_MAP);
    }

    private StudentClassMap(Name alias, Table<StudentClassMapRecord> aliased) {
        this(alias, aliased, null);
    }

    private StudentClassMap(Name alias, Table<StudentClassMapRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("View 'student_manager.people_student' references invalid table(s) or column(s) or function(s) or definer/invoker of view lack rights to use them"), TableOptions.table());
    }

    public <O extends Record> StudentClassMap(Table<O> child, ForeignKey<O, StudentClassMapRecord> key) {
        super(child, key, STUDENT_CLASS_MAP);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.STUDENT_CLASS_MAP_BY_CLASS_ID, Indexes.STUDENT_CLASS_MAP_BY_STUDENT_ID);
    }

    @Override
    public Identity<StudentClassMapRecord, Long> getIdentity() {
        return Keys.IDENTITY_STUDENT_CLASS_MAP;
    }

    @Override
    public UniqueKey<StudentClassMapRecord> getPrimaryKey() {
        return Keys.KEY_STUDENT_CLASS_MAP_PRIMARY;
    }

    @Override
    public List<UniqueKey<StudentClassMapRecord>> getKeys() {
        return Arrays.<UniqueKey<StudentClassMapRecord>>asList(Keys.KEY_STUDENT_CLASS_MAP_PRIMARY);
    }

    @Override
    public StudentClassMap as(String alias) {
        return new StudentClassMap(DSL.name(alias), this);
    }

    @Override
    public StudentClassMap as(Name alias) {
        return new StudentClassMap(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public StudentClassMap rename(String name) {
        return new StudentClassMap(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public StudentClassMap rename(Name name) {
        return new StudentClassMap(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}