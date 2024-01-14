package com.example.realinstagram.daos;

import com.example.realinstagram.models.Comment;
import com.example.realinstagram.models.Post;
import com.example.realinstagram.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentDao {
    Connection connect = DriverManager.getConnection("jdbc:postgresql://rosie.db.elephantsql.com:5432/ipslotcr", "ipslotcr", "87xlYrK7AqXosaDu--G1qAhfoImf9GRE");

    final UserDao userDao;

    public CommentDao(UserDao userDao) throws SQLException {
        this.userDao = userDao;
    }
    public void addCommentToPost(User user,Long idPost,String comment) throws SQLException {
        String addComment = "insert into comments(user_id,comment,created_ad,post_id) values (?,?,current_date,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(addComment);
        preparedStatement.setLong(1,user.getId());
        preparedStatement.setString(2,comment);
        preparedStatement.setLong(3,idPost);
        int i = preparedStatement.executeUpdate();

    }
    public void deleteComment (Long idComment) throws SQLException {
        String delete = "delete from comments where id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(delete);
        preparedStatement.setLong(1,idComment);
        preparedStatement.executeUpdate()
    }
    public List<Comment> getAllComments(Post post) throws SQLException {
        List<Comment> comments = new ArrayList<>();

        String getComment = "select * from comments where post_id = ?";
        PreparedStatement getCommentPre = connect.prepareStatement(getComment);
        getCommentPre.setLong(1, post.getId());
        ResultSet resultSet = getCommentPre.executeQuery();
        while (resultSet.next()) {
            Comment comment = new Comment();
            comment.setId((long) resultSet.getInt("id"));
            comment.setComment(resultSet.getString("comment"));
            int userId = resultSet.getInt("user_id");
            Map<Integer, String> integerStringMap = userDao.onlyUsersLogin();
            String userLogin = integerStringMap.get(userId);
            comment.setUserLogin(userLogin);
            comment.setCreatedAt(resultSet.getDate("data"));
            comments.add(comment);
        }
        return comments;
    }
}