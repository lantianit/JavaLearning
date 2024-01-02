package homework.week04.api;

import homework.week04.utils.HttpClientUtilNew;
import homework.week04.utils.ResponseData;

import java.util.HashMap;
import java.util.Map;

public class EmpApi {
    // 添加员工
    private final String addEmpUrl = "http://ihrm-test.itheima.net/api/sys/user";
    //查询员工
    private final String queryEmpUrl = "http://ihrm-test.itheima.net/api/sys/user/%s";
    //修改员工
    private final String updateEmpUrl = "http://ihrm-test.itheima.net/api/sys/user/%s";
    //删除员工
    private final String delEmpUrl = "http://ihrm-test.itheima.net/api/sys/user/%s";

    public ResponseData addEmp(String username, String mobile, String workNumber) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("mobile", mobile);
        params.put("workNumber", workNumber);
        return HttpClientUtilNew.post(this.addEmpUrl, null, params);
    }

    public ResponseData queryEmp(String empId) throws Exception {
        String url = String.format(this.queryEmpUrl, empId);
        return HttpClientUtilNew.get(url);
    }

    public ResponseData updateEmp(String empId, String username) throws Exception {
        String url = String.format(this.updateEmpUrl, empId);
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        return HttpClientUtilNew.put(url, null, params);
    }

    public ResponseData delEmp(String empId) throws Exception {
        String url = String.format(this.delEmpUrl, empId);
        return HttpClientUtilNew.delete(url, null);
    }
}
