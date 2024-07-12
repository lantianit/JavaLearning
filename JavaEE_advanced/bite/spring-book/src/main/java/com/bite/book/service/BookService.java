package com.bite.book.service;

import com.bite.book.enums.BookStatusEnums;
import com.bite.book.mapper.BookInfoMapper;
import com.bite.book.model.BookInfo;
import com.bite.book.model.PageRequest;
import com.bite.book.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    public Integer insertBook(BookInfo bookInfo){
        return bookInfoMapper.insertBook(bookInfo);
    }

    public PageResult<BookInfo> getListByPage(PageRequest pageRequest) {
        //1. 查询记录的总数
        Integer count = bookInfoMapper.count();
        //2. 查询当前页的数据
        List<BookInfo> bookInfos = bookInfoMapper.queryListBypage(pageRequest);
        for (BookInfo bookInfo:bookInfos){
            //根据状态, 设置描述
            bookInfo.setStateCN(BookStatusEnums.getDescByCode(bookInfo.getStatus()).getDesc());
        }
        return new PageResult<>(bookInfos,count,pageRequest);

    }

    public BookInfo queryBookById(Integer bookId) {
        return bookInfoMapper.queryBookById(bookId);
    }

    public Integer updateBook(BookInfo bookInfo) {
        return bookInfoMapper.updateBook(bookInfo);
    }

    public Integer batchDelete(List<Integer> ids) {
        return bookInfoMapper.batchDelete(ids);
    }
}
