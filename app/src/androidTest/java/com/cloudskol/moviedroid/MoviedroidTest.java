package com.cloudskol.moviedroid;

import android.test.AndroidTestCase;

/**
 * @author tham
 */
public class MoviedroidTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSum() {
        int a = 10;
        int b = 5;

        assertEquals("Total should be 15", a + b, 15);
    }
}
