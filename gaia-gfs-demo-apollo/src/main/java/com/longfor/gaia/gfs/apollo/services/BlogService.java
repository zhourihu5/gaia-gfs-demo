package com.longfor.gaia.gfs.apollo.services;

import com.longfor.gaia.gfs.apollo.dto.Blog;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BlogService {

    private static Map<String, Blog> blogMap = new ConcurrentHashMap<String, Blog>();

    public Blog saveBlog(Blog blog) {
        blog.setId(UUID.randomUUID().toString());
        blog.setCreateTime(new Date());
        blogMap.put(blog.getId(), blog);
        return blog;
    }

    public boolean deleteBlog(String id) {
        Blog blog = blogMap.remove(id);
        if (blog != null) {
            return true;
        }
        return false;
    }

    public Blog getBlog(String id) {
        return blogMap.get(id);
    }

    public List<Blog> getAllBlog() {
        return Arrays.asList(blogMap.values().toArray(new Blog[0]));
    }

    public void clear() {
        blogMap.clear();
    }
}
