/*
 * This file is generated by jOOQ.
 */
package com.kenvix.studentmanager.orm.enums;


import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


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
public enum PersonsType implements EnumType {

    teacher("teacher"),

    student_undergraduate("student_undergraduate"),

    student_postgraduate("student_postgraduate");

    private final String literal;

    private PersonsType(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return null;
    }

    @Override
    public Schema getSchema() {
        return null;
    }

    @Override
    public String getName() {
        return "persons_type";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}