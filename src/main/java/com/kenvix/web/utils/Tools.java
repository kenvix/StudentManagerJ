//--------------------------------------------------
// Class Tools
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix.web.utils;

import com.kenvix.utils.exception.NotFoundException;
import com.kenvix.utils.exception.UserViolationException;

import java.util.Collection;
import java.util.function.Function;

public class Tools {
    public static <R> R assertExist(R check) {
        if (check == null) {
            throw new NotFoundException("输入非法，有些参数不能为空");
        }

        return check;
    }

    public static <R> R assertNotEmpty(R check) {
        if (check == null || (check instanceof String && ((String) check).isBlank())
                || (check instanceof Collection && ((Collection<?>) check).isEmpty())) {
            throw new NotFoundException("输入非法，有些参数不能为空");
        }

        return check;
    }

    public static <T> T check(T value, Function<T, Boolean> checker) {
        if (!checker.apply(value))
            throw new UserViolationException("输入数据非法");

        return value;
    }
}
