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
public enum PersonsStatus implements EnumType {

    normal("normal"),

    graduated("graduated"),

    dropped("dropped");

    private final String literal;

    private PersonsStatus(String literal) {
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
        return "persons_status";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}
