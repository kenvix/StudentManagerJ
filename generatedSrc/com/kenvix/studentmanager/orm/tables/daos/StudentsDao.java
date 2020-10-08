/*
 * This file is generated by jOOQ.
 */
package com.kenvix.studentmanager.orm.tables.daos;


import com.kenvix.studentmanager.orm.enums.StudentsStatus;
import com.kenvix.studentmanager.orm.tables.Students;
import com.kenvix.studentmanager.orm.tables.records.StudentsRecord;

import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class StudentsDao extends DAOImpl<StudentsRecord, com.kenvix.studentmanager.orm.tables.pojos.Students, Long> {

    /**
     * Create a new StudentsDao without any configuration
     */
    public StudentsDao() {
        super(Students.STUDENTS, com.kenvix.studentmanager.orm.tables.pojos.Students.class);
    }

    /**
     * Create a new StudentsDao with an attached configuration
     */
    public StudentsDao(Configuration configuration) {
        super(Students.STUDENTS, com.kenvix.studentmanager.orm.tables.pojos.Students.class, configuration);
    }

    @Override
    public Long getId(com.kenvix.studentmanager.orm.tables.pojos.Students object) {
        return object.getPersonId();
    }

    /**
     * Fetch records that have <code>person_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.Students> fetchRangeOfPersonId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Students.STUDENTS.PERSON_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>person_id IN (values)</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.Students> fetchByPersonId(Long... values) {
        return fetch(Students.STUDENTS.PERSON_ID, values);
    }

    /**
     * Fetch a unique record that has <code>person_id = value</code>
     */
    public com.kenvix.studentmanager.orm.tables.pojos.Students fetchOneByPersonId(Long value) {
        return fetchOne(Students.STUDENTS.PERSON_ID, value);
    }

    /**
     * Fetch records that have <code>is_graduable BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.Students> fetchRangeOfIsGraduable(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(Students.STUDENTS.IS_GRADUABLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>is_graduable IN (values)</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.Students> fetchByIsGraduable(Byte... values) {
        return fetch(Students.STUDENTS.IS_GRADUABLE, values);
    }

    /**
     * Fetch records that have <code>status BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.Students> fetchRangeOfStatus(StudentsStatus lowerInclusive, StudentsStatus upperInclusive) {
        return fetchRange(Students.STUDENTS.STATUS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.Students> fetchByStatus(StudentsStatus... values) {
        return fetch(Students.STUDENTS.STATUS, values);
    }
}