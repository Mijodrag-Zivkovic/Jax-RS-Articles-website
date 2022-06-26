package com.backend.repositories.tag;

import com.backend.entities.Article;
import com.backend.entities.Articles_Tags;
import com.backend.entities.Category;
import com.backend.entities.Tag;
import com.backend.repositories.MySqlAbstractRepository;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagRepository{
    @Override
    public List<Tag> allTags() {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tags");
            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("id"),
                        resultSet.getString("name")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public Tag addTag(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO tags (name) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, tag.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tag.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag;
    }


    @Override
    public Tag findTag(Integer id) {
        Tag tag = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                tag = new Tag(resultSet.getInt("id"), resultSet.getString("name"));
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

        return tag;
    }

    @Override
    public Tag findTag(String name) {
        Tag tag = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags where name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                tag = new Tag(resultSet.getInt("id"), resultSet.getString("name"));
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

        return tag;
    }

    @Override
    public void deleteTag(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tags where id = ?");
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
    public Tag updateTag(Integer id, Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE tags SET name = ? WHERE id = ?");
            preparedStatement.setString(1, tag.getName());
            preparedStatement.setInt(2, id);
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
        tag.setId(id);
        return tag;
    }

    @Override
    public List<Tag> tagsByArticleId(Integer id) {
        List<Tag> tags = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select t.id, t.name from tags t " +
                    "inner join articles_tags at on t.id = at.tagId " +
                    "inner join articles a on at.articleId = a.id "+
                    "where a.id = ?");
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
//                System.out.println(resultSet.getInt("id"));
//                System.out.println(resultSet.getString("name"));
                tags.add(new Tag(resultSet.getInt("id"),
                        resultSet.getString("name")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public void deleteByArticleId(Integer articleId, Integer tagId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //System.out.println("asd");
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM articles_tags WHERE articleId = ? and tagId = ? ");
            preparedStatement.setInt(1, articleId);
            preparedStatement.setInt(2, tagId);
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
    public Articles_Tags articleTag(Articles_Tags articleTag) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO articles_tags (articleId,tagId) VALUES(?,?)", generatedColumns);
            preparedStatement.setInt(1, articleTag.getArticleId());
            preparedStatement.setInt(2, articleTag.getTagId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                articleTag.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articleTag;
    }
}
