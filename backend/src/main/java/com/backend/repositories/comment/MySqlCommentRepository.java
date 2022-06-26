package com.backend.repositories.comment;

import com.backend.entities.Article;
import com.backend.entities.Category;
import com.backend.entities.Comment;
import com.backend.repositories.MySqlAbstractRepository;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository{
    @Override
    public List<Comment> allComments() {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from comments");
            while (resultSet.next()) {
                Date date = resultSet.getTimestamp("time");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);
                comments.add(new Comment(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("articleId")
                        ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }

    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(date);
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO comments (name,content,time,articleId) VALUES(?,?,?,?)", generatedColumns);
            preparedStatement.setString(1, comment.getName());
            preparedStatement.setString(2, comment.getContent());
            preparedStatement.setString(3, dateTime);
            preparedStatement.setInt(4, comment.getArticleId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        comment.setDateTime(dateTime);
        return comment;
    }

    @Override
    public Comment findComment(Integer id) {
        Comment comment = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comments where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();


            if(resultSet.next()) {
                Date date = resultSet.getTimestamp("time");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);
                comment = new Comment(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("articleId")
                );
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public Comment findComment(String name) {
        Comment comment = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comments where name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();


            if(resultSet.next()) {
                Date date = resultSet.getTimestamp("time");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);
                comment = new Comment(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("articleId")
                );
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public void deleteComment(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM comments where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public Comment updateComment(Integer id, Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = sdf.format(date);

            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE comments SET name = ?, content = ?, time = ?, articleId = ? WHERE id = ?");
            preparedStatement.setString(1, comment.getName());
            preparedStatement.setString(2, comment.getContent());
            preparedStatement.setString(3, dateTime);
            preparedStatement.setInt(4, comment.getArticleId());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            //System.out.println(preparedStatement.toString());
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        comment.setId(id);
        return comment;
    }

    @Override
    public List<Comment> commentsByArticleId(Integer id) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from comments where articleId = ?");
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Date date = resultSet.getTimestamp("time");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);

                comments.add(new Comment(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("articleId")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }
}
