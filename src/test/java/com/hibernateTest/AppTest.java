package com.hibernateTest;

import com.common.util.SpringForCmsWsTests;
import com.sun.nio.file.SensitivityWatchEventModifier;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple HibernateTestWithConfiguration.
 */
public class AppTest extends TestCase
{
    HibernateTestWithSpring hibernateTestWithSpring;

    public AppTest( String testName ) {
        super( testName );
        hibernateTestWithSpring = (HibernateTestWithSpring) SpringForCmsWsTests.getInstance().getBean("hibernateTestWithSpring");
    }

    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    public void testApp() {
//        hibernateTestWithSpring.getEmployees(6);
        hibernateTestWithSpring.getEmployeesUsingCriteria(20000);
//        HibernateTestWithConfiguration hibernateTestWithConfiguration = new HibernateTestWithConfiguration();
//        hibernateTestWithConfiguration.getEmployees(3);
    }
}
