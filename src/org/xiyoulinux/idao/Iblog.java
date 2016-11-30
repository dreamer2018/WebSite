package org.xiyoulinux.idao;

import org.xiyoulinux.model.Blog;

import java.util.ArrayList;

/**
 * Created by zhoupan on 10/31/16.
 */
public interface Iblog {
    public boolean insert(Blog blog);

    public boolean delete(int blog_id);

    public boolean update(Blog blog);

    public Blog getBlogByID(int blog_id);

    public ArrayList<Blog> getBlogByTitle(String title);

    public ArrayList getBlogByPage(int page, String title);

    public ArrayList getBlogByNumber(int number);
}
