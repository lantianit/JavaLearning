import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Problem;
import dao.ProblemDAO;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@WebServlet("/compile")
class Com extends HttpServlet {
    
    static class CompileRequest {
        public int id;
        public String code;
    }
    
    static class CompileResponse {
        public int error;
        public String reason;
        public String stdout;
    }
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        CompileRequest request = null;
        CompileResponse response = new CompileResponse();
        try {
            resp.setStatus(200);
            resp.setContentType("application/json;charset=utf8");
            String body = readBody(req);
            request = objectMapper.readValue(body,CompileRequest.class);
            ProblemDAO problemDAO = new ProblemDAO();
            Problem problem = problemDAO.selectOne(request.id);
            
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String readBody(HttpServletRequest req) throws UnsupportedEncodingException {
        int contentLength = req.getContentLength();
        byte[] buffer = new byte[contentLength];
        try (InputStream inputStream = req.getInputStream()) {
            inputStream.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(buffer,"UTF8");
    }

}