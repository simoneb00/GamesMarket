package GamesMarket.control;


import GamesMarket.bean.CommentBean;
import GamesMarket.bean.PostBean;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.model.Comment;
import GamesMarket.model.DAO.CommentDAO;
import GamesMarket.model.DAO.PostDAO;
import GamesMarket.model.Post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumController {

    public void delete(PostBean postBean) throws SQLException {
        String username = postBean.getUsername();
        String text = postBean.getText();

        Post post = new Post(username, text);

        PostDAO.delete(post);
        CommentDAO.delete(post);
    }

    public List<PostBean> retrievePosts() throws SQLException{

        List<PostBean> beans = new ArrayList<>();


        List<Post> posts = PostDAO.retrievePosts();

        for (int i = 0; i < posts.size(); i++) {
            PostBean bean = new PostBean(
                    posts.get(i).getUsername(),
                    posts.get(i).getText()
            );

            beans.add(bean);
        }

        return beans;
    }

    public List<PostBean> retrieveUserPosts() throws SQLException{
        List<Post> posts = PostDAO.retrieveUserPosts();
        List<PostBean> beans = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            PostBean bean = new PostBean(
                    posts.get(i).getUsername(),
                    posts.get(i).getText()
            );

            beans.add(bean);
        }

        return beans;
    }

    public void savePost(PostBean postBean) throws SQLException{
        Post post = new Post(postBean.getUsername(), postBean.getText());
        PostDAO.savePost(post);
    }

    public void saveComment(CommentBean commentBean, PostBean postBean) throws SQLException{

        Comment comment = new Comment();
        comment.setUsername(commentBean.getUsername());
        comment.setText(commentBean.getText());

        Post post = new Post(postBean.getUsername(), postBean.getText());

        CommentDAO.saveComment(comment, post);
    }



    public List<CommentBean> retrieveComments(PostBean postBean) throws SQLException{

        Post post = new Post(postBean.getUsername(), postBean.getText());
        List<Comment> comments = CommentDAO.retrieveComments(post);
        List<CommentBean> beans = new ArrayList<>();

        for (int i = 0; i < comments.size(); i++) {
            CommentBean bean = new CommentBean();
            bean.setUsername(comments.get(i).getUsername());
            bean.setText(comments.get(i).getText());
            beans.add(bean);
        }

        return beans;
    }

}
