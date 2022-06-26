package com.backend.services;

import com.backend.entities.Article;
import com.backend.entities.Articles_Tags;
import com.backend.entities.Tag;
import com.backend.repositories.tag.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    @Inject
    TagRepository tagRepository;

    public List<Tag> allTags() {
        return tagRepository.allTags();
    }

    public Tag addTag(Tag tag) {
        return tagRepository.addTag(tag);
    }

    public Tag findTag(Integer id) {
        return tagRepository.findTag(id);
    }
    public Tag findTag(String name) {
        return tagRepository.findTag(name);
    }

    public void deleteTag(Integer id) {
        tagRepository.deleteTag(id);
    }

    public Tag updateTag(Integer id, Tag tag) {
        return tagRepository.updateTag(id,tag);
    }

    public List<Tag> tagsByArticleId(Integer id) {
        return tagRepository.tagsByArticleId(id);
    }

    public void deleteByArticleId(Integer articleId,Integer tagId) {
        tagRepository.deleteByArticleId(articleId,tagId);
    }

    public Articles_Tags articleTag(Articles_Tags articleTag) {
        return this.tagRepository.articleTag(articleTag);
    }
}
