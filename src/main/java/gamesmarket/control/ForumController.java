package gamesmarket.control;


import gamesmarket.bean.CommentBean;
import gamesmarket.bean.PostBean;
import gamesmarket.model.Comment;
import gamesmarket.model.dao.CommentDAO;
import gamesmarket.model.dao.PostDAO;
import gamesmarket.model.Post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumController {

    public void delete(PostBean postBean) throws SQLException {
        String username = postBean.getPostUsername();
        String text = postBean.getPostText();

        Post post = new Post(username, text);

        PostDAO.delete(post);
        CommentDAO.delete(post);
    }

    public List<PostBean> retrievePosts() throws SQLException {

        List<PostBean> beans = new ArrayList<>();


        List<Post> posts = PostDAO.retrievePosts();

        for (Post post : posts) {
            PostBean bean = new PostBean(
                    post.getUsername(),
                    post.getText()
            );

            beans.add(bean);
        }

        return beans;
    }

    public List<PostBean> retrieveUserPosts() throws SQLException {
        List<Post> posts = PostDAO.retrieveUserPosts();
        List<PostBean> beans = new ArrayList<>();

        for (Post post : posts) {
            PostBean bean = new PostBean(
                    post.getUsername(),
                    post.getText()
            );

            beans.add(bean);
        }

        return beans;
    }

    public void savePost(PostBean postBean) throws SQLException {
        Post post = new Post(postBean.getPostUsername(), postBean.getPostText());
        PostDAO.savePost(post);
    }

    public void saveComment(CommentBean commentBean, PostBean postBean) throws SQLException {

        Comment comment = new Comment();

        comment.setUsername(commentBean.getCommentUsername());
        comment.setText(commentBean.getCommentText());

        Post post = new Post(postBean.getPostUsername(), postBean.getPostText());

        CommentDAO.saveComment(comment, post);
    }


    public List<CommentBean> retrieveComments(PostBean postBean) throws SQLException {

        Post post = new Post(postBean.getPostUsername(), postBean.getPostText());
        List<Comment> comments = CommentDAO.retrieveComments(post);
        List<CommentBean> beans = new ArrayList<>();

        for (Comment comment : comments) {
            CommentBean bean = new CommentBean();
            bean.setCommentUsername(comment.getUsername());
            bean.setCommentText(comment.getText());
            beans.add(bean);
        }

        return beans;
    }

}
