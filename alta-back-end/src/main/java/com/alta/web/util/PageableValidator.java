package com.alta.web.util;

import com.alta.exception.PageableException;
import lombok.experimental.UtilityClass;


@UtilityClass
public class PageableValidator {
    public static void pageableValidation(Integer page, Integer size) {
        if (page == null || size == null) {
            return;
        }
        long offset = (long) page * size;
        if (page < 0 || size <= 0 || offset > Integer.MAX_VALUE) {
            throw new PageableException(page, size);
        }
    }
}
