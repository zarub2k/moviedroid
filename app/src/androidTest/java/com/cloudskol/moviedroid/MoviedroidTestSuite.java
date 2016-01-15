package com.cloudskol.moviedroid;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author tham
 */
public class MoviedroidTestSuite extends TestSuite {
    public MoviedroidTestSuite() {
        super();
    }

    public static Test suite() {
        return new TestSuiteBuilder(MoviedroidTestSuite.class)
                .includeAllPackagesUnderHere()
                .build();
    }
}
