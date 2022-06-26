package com.backend.repositories.tag;

import com.backend.entities.Article;
import com.backend.entities.Articles_Tags;
import com.backend.entities.Tag;

import java.util.List;

public interface TagRepository {
    List<Tag> allTags();

    Tag addTag(Tag tag);

    Tag findTag(Integer id);

    Tag findTag(String name);

    void deleteTag(Integer id);

    Tag updateTag(Integer id, Tag tag);

    List<Tag> tagsByArticleId(Integer id);

    void deleteByArticleId(Integer articleId,Integer tagId);

    Articles_Tags articleTag(Articles_Tags articleTag);
}
