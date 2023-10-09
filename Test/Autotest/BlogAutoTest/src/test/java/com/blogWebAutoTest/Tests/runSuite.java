package com.blogWebAutoTest.Tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

// 测试套件运行
@Suite
@SelectClasses({BlogLoginTest.class, BlogListTest.class,BlogEditTest.class,BlogDetailTest.class,DriverQuiteTest.class})
//@SelectPackages("com.blogWebAutoTest.Tests")  // 发现不可用
public class runSuite {
}
