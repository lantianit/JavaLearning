package com.bite.book.mapper;

import com.bite.book.model.BookInfo;
import com.bite.book.model.PageRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BookInfoMapper {
    @Insert("insert into book_info (book_name, author, count, price, publish) " +
            "values (#{bookName}, #{author}, #{count}, #{price}, #{publish} )")
    Integer insertBook(BookInfo bookInfo);

    /**
     * 查询总数
     */
    @Select("select count(1) from book_info where `status` <>0")
    Integer count();
    /**
     * 查询当前页的数据
     */
    @Select("select * from book_info where `status` <>0 order by id desc limit #{offset}, #{pageSize}")
    List<BookInfo> queryListBypage(PageRequest pageRequest);
    /**
     * 根据ID查询图书信息
     */
    @Select("select * from book_info where `status` <>0 and id= #{id}")
    BookInfo queryBookById(Integer id);
    /**
     * 根据ID, 修改图书信息
     */
    Integer updateBook(BookInfo bookInfo);

    Integer batchDelete(List<Integer> ids);
}
