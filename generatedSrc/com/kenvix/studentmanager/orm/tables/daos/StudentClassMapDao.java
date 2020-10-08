/*
 * This file is generated by jOOQ.
 */
package com.kenvix.studentmanager.orm.tables.daos;


import com.kenvix.studentmanager.orm.tables.StudentClassMap;
import com.kenvix.studentmanager.orm.tables.records.StudentClassMapRecord;

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
public class StudentClassMapDao extends DAOImpl<StudentClassMapRecord, com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap, Long> {

    /**
     * Create a new StudentClassMapDao without any configuration
     */
    public StudentClassMapDao() {
        super(StudentClassMap.STUDENT_CLASS_MAP, com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap.class);
    }

    /**
     * Create a new StudentClassMapDao with an attached configuration
     */
    public StudentClassMapDao(Configuration configuration) {
        super(StudentClassMap.STUDENT_CLASS_MAP, com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap.class, configuration);
    }

    @Override
    public Long getId(com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(StudentClassMap.STUDENT_CLASS_MAP.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap> fetchById(Long... values) {
        return fetch(StudentClassMap.STUDENT_CLASS_MAP.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap fetchOneById(Long value) {
        return fetchOne(StudentClassMap.STUDENT_CLASS_MAP.ID, value);
    }

    /**
     * Fetch records that have <code>student_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap> fetchRangeOfStudentId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(StudentClassMap.STUDENT_CLASS_MAP.STUDENT_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>student_id IN (values)</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap> fetchByStudentId(Long... values) {
        return fetch(StudentClassMap.STUDENT_CLASS_MAP.STUDENT_ID, values);
    }

    /**
     * Fetch records that have <code>class_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap> fetchRangeOfClassId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(StudentClassMap.STUDENT_CLASS_MAP.CLASS_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>class_id IN (values)</code>
     */
    public List<com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap> fetchByClassId(Long... values) {
        return fetch(StudentClassMap.STUDENT_CLASS_MAP.CLASS_ID, values);
    }
}