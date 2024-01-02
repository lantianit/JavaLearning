package homework.week04.script;

import homework.week04.api.EmpApi;
import homework.week04.api.LoginApi;
import homework.week04.utils.HttpClientUtilNew;
import homework.week04.utils.ResponseData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestEmp {
    private LoginApi loginApi = new LoginApi();
    private EmpApi empApi = new EmpApi();
    private String empId;


    // 登录成功
    @Test
    public void test01Login() throws Exception {
        // 登录
        ResponseData response = this.loginApi.login("13800000002", "123456");
        System.out.println("login=" + response);
        // 断言
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().getBooleanValue("success"));
        Assert.assertEquals(response.getBody().getIntValue("code"), 10000);
        Assert.assertTrue(response.getBody().getString("message").contains("操作成功"));

        // 保存token
        String token = response.getBody().getString("data");
        HttpClientUtilNew.headers.put("Authorization", "Bearer " + token);
    }

    // 添加员工
    @Test
    public void test02AddEmp() throws Exception {
        // 登录
        ResponseData response = this.empApi.addEmp("tom", "13712340003", "E001");
        System.out.println("test02AddEmp=" + response);
        // 断言
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().getBooleanValue("success"));
        Assert.assertEquals(response.getBody().getIntValue("code"), 10000);
        Assert.assertTrue(response.getBody().getString("message").contains("操作成功"));
        // 保存员工id
        this.empId = response.getBody().getJSONObject("data").getString("id");
    }


    // 查询员工
    @Test
    public void test03QueryEmp() throws Exception {
        // 登录
        ResponseData response = this.empApi.queryEmp(this.empId);
        System.out.println("test02AddEmp=" + response);
        // 断言
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().getBooleanValue("success"));
        Assert.assertEquals(response.getBody().getIntValue("code"), 10000);
        Assert.assertTrue(response.getBody().getString("message").contains("操作成功"));
    }

    // 修改员工
    @Test
    public void test04UpdateEmp() throws Exception {
        // 登录
        ResponseData response = this.empApi.updateEmp(this.empId, "tom-new");
        System.out.println("test04UpdateEmp=" + response);
        // 断言
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().getBooleanValue("success"));
        Assert.assertEquals(response.getBody().getIntValue("code"), 10000);
        Assert.assertTrue(response.getBody().getString("message").contains("操作成功"));
    }

    // 删除员工
    @Test
    public void test05DelEmp() throws Exception {
        // 登录
        ResponseData response = this.empApi.delEmp(this.empId);
        System.out.println("test05DelEmp=" + response);
        // 断言
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().getBooleanValue("success"));
        Assert.assertEquals(response.getBody().getIntValue("code"), 10000);
        Assert.assertTrue(response.getBody().getString("message").contains("操作成功"));
    }
}