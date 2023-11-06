package ait.homework.forum.test;

import ait.homework.forum.dao.Forum;
import ait.homework.forum.dao.ForumImpl;
import ait.homework.forum.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ForumTest {
    Forum forum;
    Post[]posts;
    Comparator<Post> comparator=(p1,p2)->{
        int res= Integer.compare(p1.getPostId(),p2.getPostId());
        return res;
    };
    LocalDateTime ldt=LocalDateTime.now();

    @BeforeEach
    void setUp() {
        forum =new ForumImpl();
        posts =new Post[5];
        posts[0]=new Post(001,"title1","author1","content0");
        posts[0].setDate(ldt.minusDays(1));
        posts[1]=new Post(002,"title2","author2","content1");
        posts[1].setDate(ldt.minusDays(2));
        posts[2]=new Post(003,"title1","author1","content2");
        posts[2].setDate(ldt.minusDays(1));
        posts[3]=new Post(004,"title3","author3","content3");
        posts[3].setDate(ldt.minusDays(3));
        for (int i = 0; i < posts.length; i++) {
            forum.addPost(posts[i]);
        }
    }

    @Test
    void addPost() {
        assertFalse(forum.addPost(null));
        assertFalse(forum.addPost(posts[1]));
        Post post =new Post(999,"post title","author999","content post 999");
        assertTrue(forum.addPost(post));
        assertEquals(5,forum.size());

    }

    @Test
    void removePost() {
        assertFalse(forum.removePost(9999999));
        assertTrue(forum.removePost(002));
        assertEquals(3,forum.size());
        assertNull(forum.removePost(002));
    }

    @Test
    void updatePost() {
        assertTrue(forum.updatePost(002,"content1content"));
        assertEquals("content1content",forum.getPostById(002).getContent);
    }

    @Test
    void getPostById() {
        assertEquals(posts[1], forum.getPostById(002));
        assertNull(forum.getPostById(99999));
    }

    @Test
    void getPostByAuthor() {
        Post[]actual= forum.getPostByAuthor("author1");
        Arrays.sort(actual,comparator);
        Post[]expected={posts[0],posts[2]};
        assertEquals(expected,actual);

    }

    @Test
    void testGetPostByAuthor() {
        LocalDate ld=LocalDate.now();
                Post[]actual=forum.getPostByAuthor("author1", ld.minusDays(2), ld);
                Post[]expected={posts[0],posts[2]};
                Arrays.sort(actual,comparator);
                assertEquals(expected,actual);

    }

    @Test
    void size() {
        assertEquals(4,forum.size());
    }
}