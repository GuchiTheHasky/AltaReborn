package com.alta.web.util;

import com.alta.exception.PageableException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.alta.web.util.PageableValidator.pageableValidation;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PageableValidatorTest {

    @Test
    @DisplayName("Test - valid page and size should not throw exception")
    void testValidPageAndSize() {
        assertDoesNotThrow(() -> pageableValidation(1, 10));
    }

    @Test
    @DisplayName("Test - null page or size should not throw exception")
    void testPageOrSizeIsNull() {
        assertDoesNotThrow(() -> pageableValidation(null, 10));
        assertDoesNotThrow(() -> pageableValidation(1, null));
        assertDoesNotThrow(() -> pageableValidation(null, null));
    }

    @Test
    @DisplayName("Test - negative page value should throw PageableException")
    void testNegativePageValue() {
        assertThrows(PageableException.class, () -> pageableValidation(-1, 10));
    }

    @Test
    @DisplayName("Test - negative size value should throw PageableException")
    void testNegativeSizeValue() {
        assertThrows(PageableException.class, () -> pageableValidation(1, -10));
    }

    @Test
    @DisplayName("Test - large page and size causing overflow should throw PageableException")
    void testLargePageAndSizeCausingOverflow() {
        assertThrows(PageableException.class, () -> pageableValidation(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}