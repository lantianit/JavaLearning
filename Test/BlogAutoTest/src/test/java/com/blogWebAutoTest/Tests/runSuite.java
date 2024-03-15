package com.blogWebAutoTest.Tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import java.util.ArrayList;
import java.util.List;

@Suite
@SelectClasses({BlogLoginTest.class,BlogListTest.class,BlogEditTest.class,BlogDetailTest.class,driverQuitTest.class})
//@SelectPackages("com.blogWebAutoTest.Tests")
public class runSuite {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.remove(0);
    }
}
