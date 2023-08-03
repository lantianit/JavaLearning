## 核心功能

1. 题目保存
2. 展示题目列表
3. 展示题目详情
4. 提交题目代码运行
5. 新增题目 [备选功能]
6. 删除题目 [备选功能]
7. 用户管理 [备选功能]



## 演示效果

![](./素材/题目列表页.png)

![](./素材/题目详情页.png)

![](./素材/题目详情页2.png)



## 前置知识: Java 文件 IO

### 认识 IO 流对象

**字节流**

InputStream & FileInputStream 

OutputStream & FileOutputStream 



**字符流**

Reader & FileReader

Writer & FileWriter



### 文件操作基本步骤

一个简单例子: 把文件A的内容读取出来写入文件B

```java
private static void copyFile(String srcPath, String destPath) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(srcPath);
    FileOutputStream fileOutputStream = new FileOutputStream(destPath);
	int ch = -1;
    while ((ch = fileInputStream.read()) != -1) {
        fileOutputStream.write(ch);
    }
    fileInputStream.close();
    fileOutputStream.close();
}
```



## 前置知识: 理解 "多进程编程"

### 啥是进程

进程 == "任务". 是一个 "动作". 

* 使用 PCB 描述进程
* 使用双向链表组织进程
* 进程之间相互独立



### 啥是多进程

* 一种并发编程的重要实现方式
* 操作系统本身就是按照多进程的方式进行工作的



### 进程和线程之间是咋回事?

> 最开始的操作系统只有进程. 为了更好更高效的实现并发编程, 才引入了线程. 
>
> 到后来线程也觉得不够轻量了, 又引入了协程 coroutine. (协程相比于线程, 最大的优势就是用户态进行任务调度, 而不是在内核态进行任务调度). 

* 进程包含线程: 进程是一个工厂, 线程是工厂里的流水线. 
* **进程更重量, 线程更轻量**. 创建/销毁/调度线程比进程更高效. 
* 进程是资源管理的最小单位, 线程是调度执行的最小单位. 
* 进程有自己的虚拟地址空间和文件描述符表, 同一个进程的多个线程之间则共享虚拟地址空间和文件描述符表
* 线程之间有自己独立的调用栈和程序计数器. 
* 如果一个进程挂了, 不会影响到其他进程. 如果一个线程挂了, 则整个进程都要异常终止. 

吃鸡的例子. 



### 啥是进程间通信?



## 前置知识: 理解 "重定向"

### 标准输入 vs 标准输出 vs 标准错误

### 理解重定向

注意和 HTTP 中的重定向的区别.



## 编译模块设计

### 实现通过命令行调用程序

> exec 方法使用注意事项 可以参考 <https://www.cnblogs.com/mingforyou/p/3551199.html>

创建 compile 包, CommandUtil 类.

注意, 

1. 理解 "标准输入", "标准输出", "标准错误" 这几个重要概念. 
2. 需要手动实现重定向的过程. 
3. exec 执行过程是异步的. 可以使用 waitFor 方法阻塞等待命令执行结束. 

```java
public class CommandUtil {
    // 1. 通过 Runtime 获取到 Runtime 对象, 这是一个单例对象. 
    //    然后 exec 得到 Process 对象
    // 2. 对标准输出进行重定向.
    // 3. 对标准错误进行重定向
    // 4. 等待程序结束.
    public static int run(String cmd, String stdoutFile,
                           String stderrFile) {
        try {
            // 1. 通过 Runtime 获取到 Runtime 对象, 这是一个单例对象. 
            //    然后 exec 得到 Process 对象
            Process process = Runtime.getRuntime().exec(cmd);
            // 2. 对标准输出进行重定向. 
            if (stdoutFile != null) {
                InputStream stdoutFrom = process.getInputStream();
                FileOutputStream stdoutTo = new FileOutputStream(stdoutFile);
                while (true) {
                    int ch = stdoutFrom.read();
                    if (ch == -1) {
                        break;
                    }
                    stdoutTo.write(ch);
                }
                stdoutFrom.close();
                stdoutTo.close();
            }
			// 3. 对标准错误进行重定向
            if (stderrFile != null) {
                InputStream stderrFrom = process.getErrorStream();
                FileOutputStream stderrTo = new FileOutputStream(stderrFile);
                while (true) {
                    int ch = stderrFrom.read();
                    if (ch == -1) {
                        break;
                    }
                    stderrTo.write(ch);
                }
                stderrFrom.close();
                stderrTo.close();
            }
            // 4. 等待程序结束. 
            int exitCode = process.waitFor();
            return exitCode;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
```

验证 CommandUtil 类

```java
public static void main(String[] args) {
    CommandUtil.run("javac", "./stdout.txt", "./stderr.txt");
}
```



### 实现编译运行过程 

通过构建命令行的方式来完成编译过程. 

创建 compile 包. 再创建以下类.



#### 创建 Question 类

```java
public class Question {
    private String code;
    // 其实这个 stdin 没有用上
    private String stdin;
}
```



#### 创建 Answer 类

```java
public class Answer {
    private int error;
    private String reason;
    private String stdout;
    private String stderr;
}
```



#### 创建 Task 类, 表示一次编译运行过程

```java
public class Task {
    // 通过这个方法封装编译命令, 并得到编译运行结果.
    public Answer compileAndRun(Question question) {
        // 0. 准备好存放临时文件的目录
		// 1. 根据 Question 创建临时文件
        // 2. 构造编译命令, 并执行
        // 3. 构造运行命令, 并执行
        // 4. 将运行结构包装到最终 Answer 对象中
    }
}
```



#### 约定临时文件名

在编译运行过程中可能会生成一些临时文件. 这里统一用临时文件的方式表示. 并约定命名. 这些临时文件放到一个统一的目录中. 

这些属性都是 Task 类的成员

```java
// 存放临时文件的目录.
private final String WORK_DIR = "./tmp/";
// 编译代码的类名
private final String CLASS = "Solution";
// 编译代码的文件名
private final String CODE = WORK_DIR + "Solution.java";
private final String STDIN = WORK_DIR + "stdin.txt";
private final String STDOUT = WORK_DIR + "stdout.txt";
private final String STDERR = WORK_DIR + "stderr.txt";
private final String COMPILE_ERROR = WORK_DIR + "compile_error.txt";
```

这里使用临时文件主要是方便调试, 相当于每个步骤的中间结果都被保留下来了. 



#### 创建 FileUtil

> **注意!** 这里测试千万不要使用中文! 使用中文无论如何都会出现乱码的. (除非使用 FileOutputWriter, 把编码设为 GBK, 但是这个会影响到未来部署). 直接就给同学们说清楚, 部署到 Linux 问题自然消失就可以了. 

这是一个准备工作, 为了方便后面的文件操作, 先进行简单封装. 

创建 common 包, 创建 FileUtil 类

```java
public class FileUtil {
    // 一次读取整个文件
    public static String readFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            StringBuilder content = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                content.append(line);
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 一次写入整个文件
    public static void writeFile(String filePath, String content) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```



上面是基于字符流的实现. 也可以简单用字节流来处理. 写起来没有那么麻烦. 

```java
public class FileUtil {
    // 一次读取整个文件
    public static String readFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            int ch = -1;
            while ((ch = fileInputStream.read()) != -1) {
                // 一定要把 ch 转成 char 再 append. 
                stringBuilder.append((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    // 一次写入整个文件
    public static void writeFile(String filePath, String content) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```



#### 实现 Task.compileAndRun 方法

```java
// 0. 准备好存放临时文件的目录
// 1. 根据 Question 创建临时文件
// 2. 构造编译命令, 并执行
// 3. 构造运行命令, 并执行
// 4. 将运行结构包装到最终 Answer 对象中
public Answer compileAndRun(Question question) {
    Answer answer = new Answer();
    // 0. 准备好存放临时文件的目录
    File workDir = new File(WORK_DIR);
    if (!workDir.exists()) {
        workDir.mkdir();
    }
    // 1. 根据 Question 创建临时文件
    FileUtil.writeFile(CODE, question.getCode());
    FileUtil.writeFile(STDIN, question.getStdin());
    // 2. 构造编译命令, 并执行
    //    javac -d 用来指定生成的 .class 文件的位置. 
    String compileCmd = String.format(
        "javac -encoding utf8 %s -d %s",
        CODE, WORK_DIR);
    System.out.println("编译命令: " + compileCmd);
    CommandUtil.run(compileCmd, null, COMPILE_ERROR);
    // 判定下编译是否成功, 看得到的编译错误文件是否为空文件即可
    String compileError = FileUtil.readFile(COMPILE_ERROR);
    if (!compileError.equals("")) {
        System.out.println("编译出错!");
        answer.setError(1);
        answer.setReason(compileError);
        return answer;
    }
    // 3. 构造运行命令, 并执行
    String runCmd = String.format(
        "java -classpath %s %s", WORK_DIR, CLASS
    );
    System.out.println("运行命令: " + runCmd);
    CommandUtil.run(runCmd, STDOUT, STDERR);
    // 判定下运行是否出错
    String runError = FileUtil.readFile(STDERR);
    if (!runError.equals("")) {
        System.out.println("运行出错!");
        answer.setError(2);
        answer.setReason(runError);
        return answer;
    }
    // 4. 将运行结构包装到最终 Answer 对象中
    answer.setError(0);
    answer.setStdout(FileUtil.readFile(STDOUT));
    return answer;
}
```

验证 Task 类

> 注意, Solution 代码可以直接创建 Solution 类, 然后把代码复制粘贴进来即可. 不必手动一行一行写. 
>
> 可以演示一下编译错误和运行错误的情况. 

```java
public static void main(String[] args) {
    Question question = new Question();
    question.setCode(
        "public class Solution {\n" +
        "   public static void main(String[] args) {\n" +
        "       System.out.println(\"hello\");\n" +
        "   }\n" +
        "}\n");
    question.setStdin("");
    Task task = new Task();
    Answer answer = task.compileAndRun(question);
    System.out.println(answer);
}
```



## 题目管理模块设计

### 数据库设计

```sql
create database if not exists oj_system;
use oj_system;

drop table if exists `oj_table`;

create table `oj_table`(
  id int not null primary key auto_increment,
  title varchar(50),
  level varchar(50),
  description varchar(4096),
  templateCode varchar(4096),
  testCode varchar(4096)
);
```

title 表示题目标题. level 表示题目难度. description 表示题目的描述. templateCode 表示代码模板(显示在网页的部分). testCode 表示测试用例代码(运行程序时需要)

其中 testCode 只包含一个 main 方法. 最终会将 testCode 和 templateCode 拼装成同一个文件. 



示例题目链接: <https://leetcode-cn.com/problems/add-digits/>

description 字段示例:

```
给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。

示例:

输入: 38
输出: 2 
解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
```

templateCode 字段示例:

```java
class Solution {
    public int addDigits(int num) {
        
    }
}
```

testCode 字段示例

```java
public static void main(String[] args) {
	Solution solution = new Solution();
    // testcase1
    if (solution.addDigits(38) == 2) {
    	System.out.println("Test OK");
    } else {
        System.out.println("Test failed");
    }
    // testcase2
    if (solution.addDigits(111) == 3) {
    	System.out.println("Test OK");
    } else {
        System.out.println("Test failed");
    }
}
```



### 创建 DBUtil 类

在 common 包中创建 DBUtil 类. 

辅助完成数据库连接操作

```java
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/oj_system?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static volatile DataSource dataSource = null;
    
    public static DataSource getDataSource() {

    }

    public static Connection getConnection() {

    }

    public static void close(Connection connection,
                             PreparedStatement statement,
                             ResultSet resultSet) {

    }
}
```

实现 getDataSource, 标准的线程安全单例模式的实现. 

```java
public static DataSource getDataSource() {
    if (dataSource == null) {
        synchronized (DBUtil.class) {
            if (dataSource == null) {
                dataSource = new MysqlDataSource();
                ((MysqlDataSource)dataSource).setUrl(URL);
                ((MysqlDataSource)dataSource).setUser(USERNAME);
                ((MysqlDataSource)dataSource).setPassword(PASSWORD);
            }
        }
    }
    return dataSource;
}
```

实现 getConnection

```java
public static Connection getConnection() {
    try {
        return getDataSource().getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}
```

实现 close

```java
public static void close(Connection connection,
                         PreparedStatement statement,
                         ResultSet resultSet) {
    try {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```


### 实现题目存储


创建 problem 包



#### 创建 Problem 类

表示一道题目. 

```java
public class Problem {
    private int id;
    private String title;
    private String level;
    private String path;
    private String description; // 题面
    // 代码模板(展示在页面上的代码), 对应 Solution.java
    private String templateCode;
    // 测试用例代码, 对应 Test.java
    private String testCode;
}
```



#### 创建 ProblemDAO 类

作为题目管理器类, 负责和数据库交互. 

```java
public class ProblemDAO {
    // 获取所有问题的基本信息
    public List<Problem> selectAll() {

    }

    // 获取指定问题的详细信息
    public Problem selectOne(int problemId) {

    }

    // 插入题目信息
    public void insert(Problem problem) {
        // TODO
    }

    // 删除题目信息
    public void delete(int id) {
        // TODO
    }
}
```

**实现 insert**

```java
// 插入题目信息
public void insert(Problem problem) {
    // 1. 获取数据库连接
    Connection connection = DBUtil.getConnection();
    PreparedStatement statement = null;
    String sql = "insert into oj_table values(null, ?, ?, ?, ?, ?)";
    try {
        // 2. 拼装 SQL 语句
        statement = connection.prepareStatement(sql);
        statement.setString(1, problem.getTitle());
        statement.setString(2, problem.getLevel());
        statement.setString(3, problem.getDescription());
        statement.setString(4, problem.getTemplateCode());
        statement.setString(5, problem.getTestCode());
        System.out.println(statement);
        // 3. 执行 SQL 语句
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        DBUtil.close(connection, statement, null);
    }
}
```

**实现 selectAll**

```java
public List<Problem> selectAll() {
    ArrayList<Problem> problems = new ArrayList<>();
    // 1. 获取数据库连接
    Connection connection = DBUtil.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String sql = "select id,title,level from oj_table";
    try {
        // 2. 拼装 SQL 语句
        statement = connection.prepareStatement(sql);
        // 3. 执行 SQL 语句
        resultSet = statement.executeQuery();
        // 4. 遍历查询出的结果
        while (resultSet.next()) {
            Problem problem = new Problem();
            problem.setId(resultSet.getInt("id"));
            problem.setTitle(resultSet.getString("title"));
            problem.setLevel(resultSet.getString("level"));
            problems.add(problem);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        DBUtil.close(connection, statement, resultSet);
    }
    return problems;
}
```

**实现 selectOne**

```java
public Problem selectOne(int problemId) {
    // 1. 获取数据库连接
    Connection connection = DBUtil.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String sql = "select * from oj_table where id = ?";
    try {
        // 2. 拼装 SQL 语句
        statement = connection.prepareStatement(sql);
        statement.setInt(1, problemId);
        // 3. 执行 SQL 语句
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Problem problem = new Problem();
            problem.setId(resultSet.getInt("id"));
            problem.setTitle(resultSet.getString("title"));
            problem.setLevel(resultSet.getString("level"));
            problem.setDescription(resultSet.getString("description"));
            problem.setTemplateCode(resultSet.getString("templateCode"));
            problem.setTestCode(resultSet.getString("testCode"));
            return problem;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        DBUtil.close(connection, statement, resultSet);
    }
    return null;
}
```

delete 暂时不实现. 交给同学们完成. 



**验证 insert**

以下代码都通过打 jar 包的方式, 上传到服务器上执行. 



注意, 描述, 模板代码, 测试代码都可以直接往 IDEA 中复制粘贴, 会自动处理转义字符. 

> Java12 才支持 raw string. 有了这个就不用蛋疼的处理这么多转义字符了. 

```java
private static void testInsert() {
    ProblemDAO problemDAO = new ProblemDAO();
    Problem problem = new Problem();
    problem.setTitle("各位相加");
    problem.setLevel("简单");
    problem.setDescription(
        "给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。\n" +
        "\n" +
        "示例:\n" +
        "\n" +
        "输入: 38\n" +
        "输出: 2 \n" +
        "解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2. ");
    problem.setTemplateCode(
        "class Solution {\n" +
        "    public int addDigits(int num) {\n" +
        "        \n" +
        "    }\n" +
        "}");
    problem.setTestCode(
        "public static void main(String[] args) {\n" +
        "\tSolution solution = new Solution();\n" +
        "    // testcase1\n" +
        "    if (solution.addDigits(38) == 2) {\n" +
        "    \tSystem.out.println(\"Test OK\");\n" +
        "    } else {\n" +
        "        System.out.println(\"Test failed\");\n" +
        "    }\n" +
        "    // testcase2\n" +
        "    if (solution.addDigits(111) == 3) {\n" +
        "    \tSystem.out.println(\"Test OK\");\n" +
        "    } else {\n" +
        "        System.out.println(\"Test failed\");\n" +
        "    }\n" +
        "}");
}
```

**验证 selectAll**

```java
public static void testSelectAll() {
    ProblemDAO problemDAO = new ProblemDAO();
    List<Problem> problems = problemDAO.selectAll();
    System.out.println(problems);
}
```

**验证 selectOne**

```java
public static void testSelectOne() {
    ProblemDAO problemDAO = new ProblemDAO();
    Problem problem = problemDAO.selectOne(1);
    System.out.println(problem);
}
```



## 服务器 API 

### 关于 Json

Json 是一种常见是数据格式组织方式. 源于 JavaScript, 是一种键值对风格的数据格式. 

Java 中可以使用 Gson 库来完成 Json 的解析和构造. 

在 Maven 中新增 Gson 的依赖

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.2</version>
</dependency>
```

简单示例 (创建一个 TestGson 类)

```java
public class TestGson {
    public static void main(String[] args) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "曹操");
        data.put("skill1", "剑气");
        data.put("skill2", "三段跳");
        data.put("skill3", "吸血加攻击");
        data.put("skill4", "释放技能加攻速");
        Gson gson = new GsonBuilder().create();
        String jsonData = gson.toJson(data);
        System.out.println(jsonData);
    }
}
```



### API 设定

1) 获取题目列表

```json
请求:
GET /problem HTTP/1.1

响应:
HTTP/1.1 200 OK
[
    {
        id: 1
        title: "各位相加",
        level: "简单",
    },
    {
        id: 2
        title: "判定回文",
        level: "简单",
    }
]
```

2) 获取题目详细信息

> 注意, 测试代码不应返回给前端.

```json
请求:
GET /problem?id=1

响应:
HTTP/1.1 200 OK
{
    id: 1
    title: "各位相加",
    level: "简单",
    description: [描述信息],
	templateCode: [模板代码],
}
```

3) 编译运行代码

```json
请求:
POST /compile
{
    id: 1,
	code: [编辑框中的代码]
}

响应:
HTTP/1.1 200 OK
{
    ok: 0,  // 0 表示编译运行通过. 1 表示编译出错. 2 表示运行出错. 
    reason: "",
    stdout: "", // 测试用例通过情况
}
```



### 实现 ProblemServlet

创建 api 包, 创建 ProblemServlet 类

```java
private Gson gson = new GsonBuilder().create();

@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("application/json; charset=utf8");
        // 获取 id 字段, 根据 id 字段决定是取所有题目还是单个题目
        String id = req.getParameter("id");
        if (id == null || "".equals(id)) {
            selectAll(resp);
        } else {
            selectOne(Integer.valueOf(id), resp);
        }
    }
}
```

在 web.xml 中添加配置

```xml
<servlet>
    <servlet-name>ProblemServlet</servlet-name>
    <servlet-class>api.ProblemServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>ProblemServlet</servlet-name>
    <url-pattern>/problem</url-pattern>
</servlet-mapping>
```

实现 selectAll 

```java
private void selectAll(HttpServletResponse resp) throws IOException {
    // 1. 创建 ProblemDao 对象
    ProblemDAO problemDAO = new ProblemDAO();
    // 2. 从数据库中查找数据
    List<Problem> problems = problemDAO.selectAll();
    // 3. 包装查找结果为 JSON 结构
    String jsonString = gson.toJson(problems);
    resp.getWriter().write(jsonString);
}
```

实现 selectOne

```java
private void selectOne(int id, HttpServletResponse resp) throws IOException {
    // 1. 创建 ProblemDao 对象
    ProblemDAO problemDAO = new ProblemDAO();
    // 2. 从数据库查找数据
    Problem problem = problemDAO.selectOne(id);
    // 3. 包装查找结构为 JSON 结构
    String jsonString = gson.toJson(problem);
    resp.getWriter().write(jsonString);
}
```



### 使用 Alibaba Cloud Toolkit 插件把项目部署到云服务器上

参考 <https://blog.csdn.net/a455368951/article/details/102037508>

注意, 部署过程需要有一定时间. tomcat 解压缩 war 包也需要一定时间. 稍等片刻才能操作, 否则就会 404



### 验证 ProblemServlet

部署成功后, 使用浏览器构造请求验证即可



### 实现 CompileServlet

创建 api 包, 创建 CompileServlet 类, 这里面包含两个内部类, 分别对应了编译请求和编译响应. 

```java
@WebServlet("/compile")
public class CompileServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    static class CompileRequest {
        private int id;
        private String code;
    }

    static class CompileResponse {
        private int ok;
        private String reason;
        private String stdout;
    }

    // 注意, 这个代码中很多容错情况处理的不好. 例如 problem 和 finalCode 都可能为 null
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws 									ServletException, IOException {
        // 1. 将请求按照 JSON 格式解析
        // 2. 使用 Gson 把字符串解析成 json 对象
        // 3. 根据 id 从数据库中读取对应的测试代码
        // 4. 把测试代码和请求中发进来的代码进行组装, 组装成可以被编译执行的代码
        // 5. 创建 Task 对象进行编译
        // 6. 把 Task 的结果包装成响应数据
    }
}
```

实现 doPost 方法

```java
// 注意, 这个代码中很多容错情况处理的不好. 例如 problem 和 finalCode 都可能为 null
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws 						ServletException, IOException {
    // 1. 将请求按照 JSON 格式解析
    String body = readBody(req);
    // 2. 使用 Gson 把字符串解析成 json 对象
    CompileRequest compileRequest = gson.fromJson(body, CompileRequest.class);
    String requestCode = compileRequest.code;
    // 3. 根据 id 从数据库中读取对应的测试代码
    ProblemDAO problemDAO = new ProblemDAO();
    Problem problem = problemDAO.selectOne(compileRequest.id);
    String testCode = problem.getTestCode();
    // 4. 把测试代码和请求中发进来的代码进行组装, 组装成可以被编译执行的代码
    String finalCode = mergeCode(requestCode, testCode);
    System.out.println("finalCode: " + finalCode);
    // 5. 创建 Task 对象进行编译
    Question question = new Question();
    question.setCode(finalCode);
    question.setStdin("");
    Task task = new Task();
    Answer answer = task.compileAndRun(question);
    // 6. 把 Task 的结果包装成响应数据
    CompileResponse compileResponse = new CompileResponse();
    compileResponse.ok = answer.getError();
    compileResponse.reason = answer.getReason();
    compileResponse.stdout = answer.getStdout();
    String respString = gson.toJson(compileResponse);
    resp.setContentType("application/json; charset=utf8");
    resp.getWriter().write(respString);
}
```

实现 readBody 方法, 用来把请求中的 body 部分全读出来. 

```java
private String readBody(HttpServletRequest req) {
    int contentLength = req.getContentLength();
    byte[] buf = new byte[contentLength];
    try (InputStream inputStream = req.getInputStream()) {
        inputStream.read(buf, 0, contentLength);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return new String(buf, "utf-8");
}
```

实现 mergeCode , 用来合并代码生成一个最终用来编译的代码

```java
private String mergeCode(String requestCode, String testCode) {
    // 1. 在 requestCode 中找最后一个 }
    int pos = requestCode.lastIndexOf("}");
    if (pos == -1) {
        return null;
    }
    // 2. 将 testCode 拼装到最后一个 } 之前
    return requestCode.substring(0, pos) + testCode + "\n}";
}
```

在 web.xml 中添加配置

```xml
<servlet>
    <servlet-name>CompileServlet</servlet-name>
    <servlet-class>api.CompileServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>CompileServlet</servlet-name>
    <url-pattern>/compile</url-pattern>
</servlet-mapping>
```



### 验证 CompileServlet

可以使用 Postman 构造请求验证. 

```json
POST /compile
{
	"id": 1,
	"code": "public class Solution {\n public int addDigits(int nums) {\n  return 2;} \n }"
}
```

出现结果为:

```json
{
    "ok": 0,
    "stdout": "Test OKTest failed"
}
```



至此, 后端工作基本完成, 接下来是前端页面的实现. 



### 提高请求的容错能力

针对 /compile 请求来说, 如果 code 为 空, 或者 id 为不存在的题目, 都会出现问题. 

需要妥善处理, 给与更友好的提示. 



### 给临时目录分配不同的名字

当前所有请求的临时目录, 都是 "tmp" , 这是不行的. 

这就会导致多个请求并发执行的时候, 相互就混淆了. 

因此需要保证每个请求, 都有唯一的路径. 

可以使用 uuid 作为路径的名字. 



## 前端页面实现

一共需要两个页面: 

1. 题目列表页: 展示当前有哪些题目
2. 题目详情页: 展示当前题目的细节, 包括提供一个代码编辑框, 让同学们编写代码. 



### 使用网页模板

直接在百度上搜索 "免费网页模板", 能找到很多免费模板网站. 可以直接基于现成的漂亮的页面进行修改. 

> tips: 做减法比做加法更容易.

将网页模板解压缩, 拷贝到项目的 webapp 目录中. 



### 制作题目列表页

根据网页模板进行裁剪, 保留自己需要的部分. 

主要是保留表格, 来作为展示题目列表的组件. 



核心代码:

```html
<div class="row mb-5" id="tables">
    <div class="col-sm-12">
        <div class="mt-3 mb-5">
            <h3>题目列表</h3>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>编号</th>
                        <th>标题</th>
                        <th>难度</th>
                    </tr>
                </thead>
                <tbody id="problemTable">
                    <!-- <tr>
                        <td>1</td>
                        <td>
                        <a href="#">两数之和</a>
                        </td>
                        <td>简单</td>
                        </tr> -->
                </tbody>
            </table>
        </div>
    </div>
</div>
```



#### 通过 ajax 获取后端数据

通过 ajax 的方式和后端交互, 获取到数据

在 methods 中创建 getProblems 方法

**注意** url 的路径要用相对路径. 

```js
// 在页面加载的时候, 尝试从服务器获取题目列表. 通过 ajax 的方式来进行获取
function getProblems() {
    // 1. 先通过 ajax 从服务器获取到题目列表. 
    $.ajax({
        url: "problem",
        type: "GET",
        success: function(data, status) {
            // data 是响应的 body, status 是响应的状态码
            // 2. 把得到的响应数据给构造成 HTML 片段
            makeProblemTable(data);
        }
    })
}

// 通过这个函数来把数据转换成 HTML 页面片段
function makeProblemTable(data) {
    let problemTable = document.querySelector("#problemTable");
    for (let problem of data) {
        let tr = document.createElement("tr");

        let tdId = document.createElement("td");
        tdId.innerHTML = problem.id;
        tr.appendChild(tdId);

        let tdTitle = document.createElement("td");
        let a = document.createElement("a");
        a.innerHTML = problem.title;
        a.href = 'problemDetail.html?id=' + problem.id;
        a.target = '_blank';
        tdTitle.appendChild(a);
        tr.appendChild(tdTitle);

        let tdLevel = document.createElement("td");
        tdLevel.innerHTML = problem.level;
        tr.appendChild(tdLevel);

        problemTable.appendChild(tr);
    }
}
```

并且在网页加载时调用该方法

```js
getProblems();
```

关于 ajax 的用法, 可以参考 JQuery 文档 <https://www.w3school.com.cn/jquery/ajax_ajax.asp>

或者直接百度上搜索 "jquery ajax"



#### 部署程序验证效果

部署到服务器上验证程序是否生效. 

此时点击题目标题, 就能够获取到题目的 json 格式的数据. 

而我们想要的不止于此. 



### 制作题目详情页

先把题目列表页拷贝一份, 修改名字为 problemDetail.html

调整页面内容.  去掉表格了. 

* 使用一个 jumbotron 表示题目详情
* 使用一个 textarea 表示代码编辑框
* 使用 button 表示提交按钮. 
* 再使用一个 jumbotron 表示题目运行结果. 

```html
<div class="container">
    <div class="row mt-4">
        <div class="col-sm-12 pb-4">
            <div class="jumbotron jumbotron-fluid">
                <div class="container" id="problemDesc">
                    <!-- <h1>Container fluid size jumbotron</h1>
<p>Think BIG with a Bootstrap Jumbotron!</p> -->
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-sm-12 pb-4">
            <div class="form-group">
                <label for="codeEditor">代码编辑框</label>               
                    <textarea class="form-control" id="codeEditor" style="width: 100%; height: 400px;"></textarea>
            </div>
        </div>
    </div>

    <button type="button" class="btn btn-primary" id="submitButton">提交</button>

    <div class="row mt-4">
        <div class="col-sm-12 pb-4">
            <div class="jumbotron jumbotron-fluid">
                <div class="container">
                    <pre id="problemResult">

                                </pre>
                    <!-- <h1>Container fluid size jumbotron</h1>
<p>Think BIG with a Bootstrap Jumbotron!</p> -->
                </div>
            </div>
        </div>
    </div>
</div>
```

> **注意**
>
> * 页面的基本结构为 .container -> .row -> .col -> 组件元素
> * 在这个页面模板中, 一行被分成了 12 份. `.col-sm-12` 表示这一列的宽度占据了 12 份(相当于 100%), 如果是 `.col-sm.6` 则表示占据 6 份(相当于 50%)
> * mt-4 表示 margin-top, pb-4 表示 padding-bottom
> * 使用 pre 标签, 可以使填充的内容保留换行. 



#### 从服务器上获取题目详情

在跳转到题目详情页中, 首先会把题目列表页的题目编号带过来. 

题目详情页获取到编号, 通过 ajax 来获取题目详情. 

```js
console.log(location.search); 

// 通过 ajax 从服务器获取到题目的详情
function getProblem() {
    // 1. 通过 ajax 给服务器发送一个请求
    $.ajax({
        url: "problem" + location.search,
        type: "GET",
        success: function (data, status) {
            makeProblemDetail(data);
        }
    })
}

function makeProblemDetail(problem) {
    // 1. 获取到 problemDesc, 把题目详情填写进去
    let problemDesc = document.querySelector("#problemDesc");

    let h3 = document.createElement("h3");
    h3.innerHTML = problem.id + "." + problem.title + "_" + problem.level
    problemDesc.appendChild(h3);

    let pre = document.createElement("pre");
    let p = document.createElement("p");
    p.innerHTML = problem.description;
    pre.appendChild(p);
    problemDesc.appendChild(pre);

    // 2. 把代码的模板填写到编辑框中. 
    let codeEditor = document.querySelector("#codeEditor");
    codeEditor.innerHTML = problem.templateCode;



}

getProblem()
```

部署代码, 验证效果



#### 实现提交代码

在刚才的 makeProblemDetail 函数中, 新增一个逻辑来实现提交代码. 

> 这个代码放在 `makeProblemDetail ` 末尾. 

```js
// 3. 给提交按钮注册一个点击事件
let submitButton = document.querySelector("#submitButton");
submitButton.onclick = function () {
    // 点击这个按钮, 就要进行提交. (把编辑框的内容给提交到服务器上)
    let body = {
        id: problem.id,
        code: codeEditor.value,
    };
    $.ajax({
        type: "POST",
        url: "compile",
        data: JSON.stringify(body),
        success: function (data, status) {
            let problemResult = document.querySelector("#problemResult");
            if (data.error == 0) {
                // 编译运行没有问题, 把 stdout 显示到页面中
                problemResult.innerHTML = data.stdout;
            } else {
                // 编译运行没有问题, 把 reason 显示到页面中
                problemResult.innerHTML = data.reason;
            }
        }
    });
}
```

部署代码, 验证效果, 看是否能正确编译运行. 



### 引入代码编辑器组件

#### 引入 ace.js 

```html
<script src="https://cdn.bootcss.com/ace/1.2.9/ace.js"></script>
<script src="https://cdn.bootcss.com/ace/1.2.9/ext-language_tools.js"></script>
```

#### 初始化编辑器

```js
function initAce() {
    // 参数 editor 就对应到刚才在 html 里加的那个 div 的 id
    let editor = ace.edit("editor");
    editor.setOptions({
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: true
    });
    editor.setTheme("ace/theme/twilight");
    editor.session.setMode("ace/mode/java");
    editor.resize();
    document.getElementById('editor').style.fontSize = '20px';

    return editor;
}

let editor = initAce();
```

并且将页面编辑框外面套一层 div, id 设为 editor, 并且一定要设置 min-height 属性. 

```html
<div id="editor" style="min-height:400px">
    <textarea style="width: 100%; height: 200px"></textarea>
</div>
```



#### 修改 makeProblemDetail 方法

把显示模板代码的逻辑改为

```js
// let codeEditor = document.querySelector("#codeEditor");
// codeEditor.innerHTML = problem.templateCode;
editor.setValue(this.problem.templateCode);
```



#### 修改提交代码

把请求中的获取编辑器代码的逻辑进行修改. 



```js
submitButton.onclick = function () {
    // 点击这个按钮, 就要进行提交. (把编辑框的内容给提交到服务器上)
    let body = {
        id: problem.id,
        // code: codeEditor.value,
        code: editor.getValue(),
    }
    // ..... 其他代码略
}
```



## 加入安全性控制

为了避免用户提交的代码包含恶意代码, 此处通过黑名单的方式, 对提交代码进行扫描限制. 如果发现用户提交代码中包含了黑名单中的关键词, 则直接报错. 



在 Task 类中新增逻辑

```java
public Answer compileAndRun(Question question) {
    Answer answer = new Answer();
    // 0. 准备好用来存放临时文件的目录
    File workDir = new File(WORK_DIR);
    if (!workDir.exists()) {
        // 创建多级目录.
        workDir.mkdirs();
    }
    // [新增代码] 进行安全性判定
    if (!checkCodeSafe(question.getCode())) {
        System.out.println("用户提交了不安全的代码!");
        answer.setError(3);
        answer.setReason("您提交的代码可能会危害到服务器, 禁止运行!");
        return answer;
    }
    // .... 其他代码略
}
```



`checkCodeSafe` 方法实现

```java
private boolean checkCodeSafe(String code) {
    List<String> blackList = new ArrayList<>();
    // 防止提交的代码运行恶意程序
    blackList.add("Runtime");
    blackList.add("exec");
    // 禁止提交的代码读写文件
    blackList.add("java.io");
    // 禁止提交的代码访问网络
    blackList.add("java.net");

    for (String target : blackList) {
        int pos = code.indexOf(target);
        if (pos >= 0) {
            // 找到任意的恶意代码特征, 返回 false 表示不安全
            return false;
        }
    }
    return true;
}
```



