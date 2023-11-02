package com.blogWebAutoTest.Tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({BlogLoginTest.class,BlogListTest.class,BlogEditTest.class,BlogDetailTest.class,driverQuitTest.class})
//@SelectPackages("com.blogWebAutoTest.Tests")
public class runSuite {
}
