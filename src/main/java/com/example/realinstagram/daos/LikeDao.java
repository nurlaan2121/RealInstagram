package com.example.realinstagram.daos;

import com.example.realinstagram.models.Like;
import com.example.realinstagram.models.Post;
import com.example.realinstagram.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LikeDao {
    final UserDao userDao;

    public LikeDao(UserDao userDao) throws SQLException {
        this.userDao = userDao;
    }


    public void addLikeToPost(User user, Long idPost) throws SQLException {
        String addLike = "insert into likes(user_id,created_ad,post_id) values (?,current_date,?)";
        Connection connect = UserDao.connection;
        PreparedStatement preparedStatement = connect.prepareStatement(addLike);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, idPost);
        int i = preparedStatement.executeUpdate();


    }

    public void deleteLikeInPost(Long idLike) throws SQLException {
        String deleteLike = "delete from likes where id = ?";
        Connection connect = UserDao.connection;
        PreparedStatement preparedStatement = connect.prepareStatement(deleteLike);
        preparedStatement.setLong(1, idLike);
        int i = preparedStatement.executeUpdate();
    }


    public List<Like> getAllLikes(Post post) throws SQLException {
        List<Like> likes = new ArrayList<>();
        String getLikes = "select * from likes where post_id = ?";
        Connection connect = UserDao.connection;
        PreparedStatement getLikePre = connect.prepareStatement(getLikes);
        getLikePre.setLong(1, post.getId());
        ResultSet getLikeRes = getLikePre.executeQuery();
        while (getLikeRes.next()) {
            Like like = new Like();
            like.setId((long) getLikeRes.getInt("id"));
            int userId = getLikeRes.getInt("user_id");
            Map<Integer, String> integerStringMap = userDao.onlyUsersLogin();
            String userLogin = integerStringMap.get(userId);
            like.setUser(userLogin);
            like.setCreatedAd(getLikeRes.getDate("created_ad"));
            likes.add(like);
        }
        return likes;
    }
}
