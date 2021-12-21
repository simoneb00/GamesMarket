package GamesMarket.control;


import GamesMarket.bean.PostBean;
import GamesMarket.graphicControl.forum.ForumGraphicController;
import GamesMarket.graphicControl.forum.PostGraphicController;
import GamesMarket.model.DAO.PostDAO;
import GamesMarket.model.Post;
import GamesMarket.model.User;

import java.util.ArrayList;
import java.util.List;

public class ForumController {

    PostDAO postDAO = new PostDAO();


    public void delete(PostBean postBean) {
        String username = postBean.getUsername();
        String text = postBean.getText();

        Post post = new Post();
        post.setUsername(username);
        post.setText(text);

        postDAO.delete(post);
    }

    public List<Post> retrievePosts() {
        List<Post> posts = new ArrayList<>();
        posts = postDAO.retrievePosts();
        return posts;
    }

    public List<Post> retrieveUserPosts() {
        List<Post> posts = new ArrayList<>();
        posts = postDAO.retrieveUserPosts();
        return posts;
    }

    public void savePost(PostBean postBean){
        PostDAO postDAO = new PostDAO();

        Post post = new Post();
        post.setUsername(postBean.getUsername());
        post.setText(postBean.getText());

        postDAO.savePost(post);
    }
}
