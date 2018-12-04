package com.longfor.gais.gfs.apollo.controllers;

import com.longfor.gais.gfs.apollo.dto.Blog;
import com.longfor.gais.gfs.apollo.services.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/v1/blogs")
@RestController
@Api(description = "blog相关API")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ApiOperation("创建Blog")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Blog createBlog(@RequestBody @Valid Blog blog) {
        return blogService.saveBlog(blog);
    }

    @ApiOperation("删除Blog")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteBlog(@PathVariable String id) {
        return blogService.deleteBlog(id);
    }

    @ApiOperation("查找Blog")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Blog getBlog(@PathVariable String id) {
        return blogService.getBlog(id);
    }

    @ApiOperation("Blog列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Blog> getAllBlog() {
        return blogService.getAllBlog();
    }

    @ApiOperation("清空所有Blog")
    @RequestMapping(value = "/actions/clear", method = RequestMethod.PUT)
    public Boolean clear() {
        blogService.clear();
        return true;
    }

}
