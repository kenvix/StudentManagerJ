/*
 * This file is generated by jOOQ.
 */
package com.kenvix.studentmanager.orm;


import com.kenvix.studentmanager.orm.tables.ClassCourseMap;
import com.kenvix.studentmanager.orm.tables.Classes;
import com.kenvix.studentmanager.orm.tables.Courses;
import com.kenvix.studentmanager.orm.tables.Papers;
import com.kenvix.studentmanager.orm.tables.Persons;
import com.kenvix.studentmanager.orm.tables.StudentClassMap;
import com.kenvix.studentmanager.orm.tables.records.ClassCourseMapRecord;
import com.kenvix.studentmanager.orm.tables.records.ClassesRecord;
import com.kenvix.studentmanager.orm.tables.records.CoursesRecord;
import com.kenvix.studentmanager.orm.tables.records.PapersRecord;
import com.kenvix.studentmanager.orm.tables.records.PersonsRecord;
import com.kenvix.studentmanager.orm.tables.records.StudentClassMapRecord;

import javax.annotation.processing.Generated;

import org.jooq.Identity;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code></code> schema.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.13.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ClassCourseMapRecord, Long> IDENTITY_CLASS_COURSE_MAP = Identities0.IDENTITY_CLASS_COURSE_MAP;
    public static final Identity<ClassesRecord, Long> IDENTITY_CLASSES = Identities0.IDENTITY_CLASSES;
    public static final Identity<CoursesRecord, Long> IDENTITY_COURSES = Identities0.IDENTITY_COURSES;
    public static final Identity<PapersRecord, Long> IDENTITY_PAPERS = Identities0.IDENTITY_PAPERS;
    public static final Identity<PersonsRecord, Long> IDENTITY_PERSONS = Identities0.IDENTITY_PERSONS;
    public static final Identity<StudentClassMapRecord, Long> IDENTITY_STUDENT_CLASS_MAP = Identities0.IDENTITY_STUDENT_CLASS_MAP;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ClassCourseMapRecord> KEY_CLASS_COURSE_MAP_PRIMARY = UniqueKeys0.KEY_CLASS_COURSE_MAP_PRIMARY;
    public static final UniqueKey<ClassesRecord> KEY_CLASSES_PRIMARY = UniqueKeys0.KEY_CLASSES_PRIMARY;
    public static final UniqueKey<CoursesRecord> KEY_COURSES_PRIMARY = UniqueKeys0.KEY_COURSES_PRIMARY;
    public static final UniqueKey<PapersRecord> KEY_PAPERS_PRIMARY = UniqueKeys0.KEY_PAPERS_PRIMARY;
    public static final UniqueKey<PersonsRecord> KEY_PERSONS_PRIMARY = UniqueKeys0.KEY_PERSONS_PRIMARY;
    public static final UniqueKey<StudentClassMapRecord> KEY_STUDENT_CLASS_MAP_PRIMARY = UniqueKeys0.KEY_STUDENT_CLASS_MAP_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<ClassCourseMapRecord, Long> IDENTITY_CLASS_COURSE_MAP = Internal.createIdentity(ClassCourseMap.CLASS_COURSE_MAP, ClassCourseMap.CLASS_COURSE_MAP.ID);
        public static Identity<ClassesRecord, Long> IDENTITY_CLASSES = Internal.createIdentity(Classes.CLASSES, Classes.CLASSES.ID);
        public static Identity<CoursesRecord, Long> IDENTITY_COURSES = Internal.createIdentity(Courses.COURSES, Courses.COURSES.ID);
        public static Identity<PapersRecord, Long> IDENTITY_PAPERS = Internal.createIdentity(Papers.PAPERS, Papers.PAPERS.ID);
        public static Identity<PersonsRecord, Long> IDENTITY_PERSONS = Internal.createIdentity(Persons.PERSONS, Persons.PERSONS.ID);
        public static Identity<StudentClassMapRecord, Long> IDENTITY_STUDENT_CLASS_MAP = Internal.createIdentity(StudentClassMap.STUDENT_CLASS_MAP, StudentClassMap.STUDENT_CLASS_MAP.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<ClassCourseMapRecord> KEY_CLASS_COURSE_MAP_PRIMARY = Internal.createUniqueKey(ClassCourseMap.CLASS_COURSE_MAP, "KEY_class_course_map_PRIMARY", new TableField[] { ClassCourseMap.CLASS_COURSE_MAP.ID }, true);
        public static final UniqueKey<ClassesRecord> KEY_CLASSES_PRIMARY = Internal.createUniqueKey(Classes.CLASSES, "KEY_classes_PRIMARY", new TableField[] { Classes.CLASSES.ID }, true);
        public static final UniqueKey<CoursesRecord> KEY_COURSES_PRIMARY = Internal.createUniqueKey(Courses.COURSES, "KEY_courses_PRIMARY", new TableField[] { Courses.COURSES.ID }, true);
        public static final UniqueKey<PapersRecord> KEY_PAPERS_PRIMARY = Internal.createUniqueKey(Papers.PAPERS, "KEY_papers_PRIMARY", new TableField[] { Papers.PAPERS.ID }, true);
        public static final UniqueKey<PersonsRecord> KEY_PERSONS_PRIMARY = Internal.createUniqueKey(Persons.PERSONS, "KEY_persons_PRIMARY", new TableField[] { Persons.PERSONS.ID }, true);
        public static final UniqueKey<StudentClassMapRecord> KEY_STUDENT_CLASS_MAP_PRIMARY = Internal.createUniqueKey(StudentClassMap.STUDENT_CLASS_MAP, "KEY_student_class_map_PRIMARY", new TableField[] { StudentClassMap.STUDENT_CLASS_MAP.ID }, true);
    }
}