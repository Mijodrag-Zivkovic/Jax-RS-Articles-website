package com.backend.repositories.article;

import com.backend.entities.Article;


import com.backend.entities.Category;
import com.backend.entities.Tag;
import com.backend.repositories.MySqlAbstractRepository;
import org.apache.commons.codec.digest.DigestUtils;


import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlArticleRepository extends MySqlAbstractRepository implements ArticleRepository {

    @Override
    public List<Article> allArticles() {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from articles");
            while (resultSet.next()) {

                Date date = resultSet.getTimestamp("timeOfWriting");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);

                        articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("numberOfVisits"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("categoryId")
                        ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }

    @Override
    public Article addArticle(Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = sdf.format(date);
            article.setTimeOfWriting(dateTime);

            preparedStatement = connection.prepareStatement("INSERT INTO articles (title,content,timeOfWriting,numberOfVisits,authorId,categoryId) VALUES(?,?,?,?,?,?)", generatedColumns);

            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setString(3, dateTime);
            preparedStatement.setInt(4, article.getNumberOfVisits());
            preparedStatement.setInt(5, article.getAuthorId());
            preparedStatement.setInt(6, article.getCategoryId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                article.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return article;
    }

    @Override
    public Article findArticle(Integer id) {
        Article article = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articles where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();


            if(resultSet.next()) {
                Date date = resultSet.getTimestamp("timeOfWriting");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);
                article = new Article(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("numberOfVisits"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("categoryId")
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

        return article;
    }

    @Override
    public Article findArticle(String title) {
        Article article = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articles where title = ?");
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Date date = resultSet.getTimestamp("timeOfWriting");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);
                article = new Article(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("numberOfVisits"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("categoryId")
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

        return article;
    }

    @Override
    public void deleteArticle(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM articles where id = ?");
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
    public Article updateArticle(Integer id, Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = sdf.format(date);
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE articles SET title = ?, content = ?, timeOfWriting = ?, numberOfVisits = ?, authorId = ?, categoryId = ? WHERE id = ?");
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setString(3, dateTime);
            preparedStatement.setInt(4, article.getNumberOfVisits());
            preparedStatement.setInt(5, article.getAuthorId());
            preparedStatement.setInt(6, article.getCategoryId());
            preparedStatement.setInt(7, id);
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
        article.setId(id);
        return article;
    }

    @Override
    public List<Article> articlesByCategoryId(Integer id) {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from articles where categoryId = ?");
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Date date = resultSet.getTimestamp("timeOfWriting");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);

                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("numberOfVisits"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("categoryId")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }

    @Override
    public List<Article> articlesByTagId(Integer id) {
        List<Article> articles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select a.* from articles a " +
                    "inner join articles_tags at on a.id = at.articleId " +
                    "inner join tags t on at.tagId = t.id "+
                    "where t.id = ?");
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
//                System.out.println(resultSet.getInt("id"));
//                System.out.println(resultSet.getString("name"));
                Date date = resultSet.getTimestamp("timeOfWriting");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);
                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("numberOfVisits"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("categoryId")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }

    @Override
    public List<Article> pagination(Integer left, Integer right) {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from articles ORDER BY timeOfWriting DESC LIMIT ?,?");
            statement.setInt(1,left);
            statement.setInt(2,right);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Date date = resultSet.getTimestamp("timeOfWriting");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(date);

                articles.add(new Article(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        dateTime,
                        resultSet.getInt("numberOfVisits"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("categoryId")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }

    @Override
    public Integer count() {
        Integer count = Integer.valueOf(0);
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select COUNT(*) from articles");
            if (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return count;
    }
}
