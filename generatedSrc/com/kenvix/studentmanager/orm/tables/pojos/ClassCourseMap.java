/*
 * This file is generated by jOOQ.
 */
package com.kenvix.studentmanager.orm.tables.pojos;


import com.kenvix.studentmanager.orm.tables.interfaces.IClassCourseMap;

import javax.annotation.processing.Generated;


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
public class ClassCourseMap implements IClassCourseMap {

    private static final long serialVersionUID = 1909192537;

    private Long id;
    private Long classId;
    private Long courseId;

    public ClassCourseMap() {}

    public ClassCourseMap(IClassCourseMap value) {
        this.id = value.getId();
        this.classId = value.getClassId();
        this.courseId = value.getCourseId();
    }

    public ClassCourseMap(
        Long id,
        Long classId,
        Long courseId
    ) {
        this.id = id;
        this.classId = classId;
        this.courseId = courseId;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public ClassCourseMap setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Long getClassId() {
        return this.classId;
    }

    @Override
    public ClassCourseMap setClassId(Long classId) {
        this.classId = classId;
        return this;
    }

    @Override
    public Long getCourseId() {
        return this.courseId;
    }

    @Override
    public ClassCourseMap setCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ClassCourseMap (");

        sb.append(id);
        sb.append(", ").append(classId);
        sb.append(", ").append(courseId);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IClassCourseMap from) {
        setId(from.getId());
        setClassId(from.getClassId());
        setCourseId(from.getCourseId());
    }

    @Override
    public <E extends IClassCourseMap> E into(E into) {
        into.from(this);
        return into;
    }
}
