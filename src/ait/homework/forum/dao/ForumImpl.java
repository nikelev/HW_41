package ait.homework.forum.dao;

import ait.homework.forum.model.Post;

import java.time.LocalDate;
import java.util.Comparator;

public class ForumImpl implements Forum {
    private Post[]posts;
    private int size=10;

//    private Comparator<Post> comparator = (p1, p2) -> {
//        int res = p1.getDate().compareTo(p2.getDate());
//        return res != 0 ? res : Integer.compare(p1.getPostId(), p2.getPostId());


    public ForumImpl(){
        this.posts=new Post[size];
    }


    @Override
    public boolean addPost(Post post) {
        if (post==null||size==posts.length||getPostById(post.getPostId())!=null){
            return false;


        }




            posts[].setLikes() =0;
        return true;
    }

    @Override
    public boolean removePost(int postId) {
        return false;
    }

    @Override
    public boolean updatePost(int postId, String content) {
        return false;
    }

    @Override
    public boolean getPostById(int postId) {
        return false;
    }

    @Override
    public Post[] getPostByAuthor(String author) {
        return new Post[0];
    }

    @Override
    public Post[] getPostByAuthor(String author, LocalDate dateFrom, LocalDate dateTo) {
        return new Post[0];
    }

    @Override
    public int size() {
        return size;
    }
}
