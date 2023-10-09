package much0221;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

// 标识测试套件类 + 选择测试类
//@Suite
//@SelectClasses({aaa.class,bbb.class})

@Suite
@SelectPackages("much0221")
public class runSuite {
}
