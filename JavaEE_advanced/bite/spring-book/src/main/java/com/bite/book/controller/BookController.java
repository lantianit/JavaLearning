package com.bite.book.controller;

import com.bite.book.constonts.Constants;
import com.bite.book.model.*;
import com.bite.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RequestMapping("/book")
//@RestController
@ResponseBody
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/addBook",produces = "application/json")
    public Result addBook(BookInfo bookInfo){
        log.info("添加图书, bookInfo:{}",bookInfo);
        //参数校验
        if (!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount()<=0
                || bookInfo.getPrice()==null
                || !StringUtils.hasLength(bookInfo.getPublish())){
            return Result.fail("参数错误");
        }
        //添加图书
        try {
            bookService.insertBook(bookInfo);
        }catch (Exception e){
            log.error("添加图书失败, e:{}",e);
            return Result.fail("内部发生错误, 请联系管理员");
        }
        return Result.success("");

    }
    @RequestMapping("/getListByPage")
    public Result<PageResult<BookInfo>> getListByPage(PageRequest pageRequest, HttpSession session){
//        long start = System.currentTimeMillis();
        log.info("查询列表信息, pageRequest:{}", pageRequest);
//        //验证用户是否登录
//        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.USER_SESSION_KEY);
//        if (userInfo==null || userInfo.getId()<1){
//            return Result.unlogin();
//        }
        if (pageRequest.getCurrentPage()<1){
            return Result.fail("非法参数");
        }
        PageResult<BookInfo> pageResult = bookService.getListByPage(pageRequest);
//        long end = System.currentTimeMillis();
//        log.info("getListByPage, cost:"+(end-start)+"ms");
        return Result.success(pageResult);
    }

    @RequestMapping("/queryBookById")
    public BookInfo queryBookById(Integer bookId){

        log.info("查询图书信息, bookId:"+bookId);
        //参数校验
        if (bookId<1){
            log.error("非法图书ID, bookId:"+bookId);
            return null;
        }
        return bookService.queryBookById(bookId);
    }

    /**
     * 更新图书/删除图书
     * @param bookInfo
     * @return
     */
    @RequestMapping("/updateBook")
    public Result updateBook(BookInfo bookInfo){
        log.info("更新图书, updateBook:{}",bookInfo);
        if (bookInfo.getId()<0){
            return Result.fail(false,"ID 不合法");
        }
        try {
            Integer result = bookService.updateBook(bookInfo);
            if (result<=0){
                return Result.fail(false,"更新失败");
            }
            return Result.success(true);
        }catch (Exception e){
            log.error("更新图书失败, e:{}", e);
            return Result.fail(false,e.getMessage());
        }
    }
    /**
     * 批量删除
     */
    @RequestMapping("/batchDelete")
    public boolean batchDelete(@RequestParam List<Integer> ids){
        log.info("批量删除数据, ids:{}", ids);
        try {
            Integer result = bookService.batchDelete(ids);
            if (result<=0){
                return false;
            }
            return true;
        }catch (Exception e){
            log.error("批量删除数据失败, ids:{}, e:{}", ids, e);
            return false;
        }
    }


}
