package ait.homework.forum.dao;

import ait.homework.forum.model.Post;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class ForumImpl implements Forum {
    private Post[]posts;
    private int size;

//    private Comparator<Post> comparator = (p1, p2) -> {
//        int res = p1.getDate().compareTo(p2.getDate());
//        return res != 0 ? res : Integer.compare(p1.getPostId(), p2.getPostId());


    public ForumImpl(){

        this.posts=new Post[0];
    }


    @Override
    public boolean addPost(Post post) {
        if (post==null||getPostById(post.getPostId())!=null){
            return false;
        }
        posts = Arrays.copyOf(posts, posts.length + 1);
        posts[posts.length - 1] = post;
        size++;
        return true;
    }

    @Override
    public boolean removePost(int postId) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getPostId()==postId){
                System.arraycopy(posts,i+1,posts,i,size-i-1);
                posts = Arrays.copyOf(posts, posts.length - 1);
                size--;
                return true;

            }
        }
        return false;
    }

    @Override
    public boolean updatePost(int postId, String content) {
        Post post=getPostById(postId);
        if (post==null){
            return false;
        }
        post.setContent(content);
        return true;
    }

    @Override
    public Post getPostById(int postId) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getPostId()==postId);
            return posts[i];
        }
        return null;
    }

    @Override
    public Post[] getPostByAuthor(String author) {

        return findByPredicate(p->p.getAuthor().equals(author));
    }

    @Override
    public Post[] getPostByAuthor(String author, LocalDate dateFrom, LocalDate dateTo) {

        return findByPredicate(p->p.getAuthor().equals(author))&&
                 p.getDate().toLocalDate().isAfter(dateFrom. minusDays(1))&&
                        p.getDate().toLocalDate().isBefore(dateTo.plusDays(1)));

    }

    @Override
    public int size() {
        return size;
    }

    private Post[] findByPredicate(Predicate<Post>predicate){
        Post[]res=new Post[size];
        int j=0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(posts[i])){
                res[j++]=posts[i];
            }
        }return Arrays.copyOf(res,j);
    }
}
