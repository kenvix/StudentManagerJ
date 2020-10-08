/*
 * This file is generated by jOOQ.
 */
package com.kenvix.studentmanager.orm.tables.interfaces;


import com.kenvix.studentmanager.orm.enums.PersonsSex;
import com.kenvix.studentmanager.orm.enums.PersonsType;

import java.io.Serializable;

import javax.annotation.processing.Generated;


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
public interface IPersons extends Serializable {

    /**
     * Setter for <code>persons.id</code>.
     */
    public IPersons setId(Long value);

    /**
     * Getter for <code>persons.id</code>.
     */
    public Long getId();

    /**
     * Setter for <code>persons.name</code>.
     */
    public IPersons setName(String value);

    /**
     * Getter for <code>persons.name</code>.
     */
    public String getName();

    /**
     * Setter for <code>persons.sex</code>.
     */
    public IPersons setSex(PersonsSex value);

    /**
     * Getter for <code>persons.sex</code>.
     */
    public PersonsSex getSex();

    /**
     * Setter for <code>persons.type</code>.
     */
    public IPersons setType(PersonsType value);

    /**
     * Getter for <code>persons.type</code>.
     */
    public PersonsType getType();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IPersons
     */
    public void from(IPersons from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IPersons
     */
    public <E extends IPersons> E into(E into);
}