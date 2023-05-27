package com.example.codegymfoods.service.blog;

import com.example.codegymfoods.model.blog.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    List<Blog> getBlog ();
    void save (Blog blog);
    void delete (int id);
    void update (Blog blog);
    Blog findById (int id);
}
