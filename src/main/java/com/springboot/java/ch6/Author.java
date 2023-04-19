package com.springboot.java.ch6;

public class Author {
    private String name;
    private int relatedArticleId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRelatedArticleId() {
        return relatedArticleId;
    }

    public void setRelatedArticleId(int relatedArticleId) {
        this.relatedArticleId = relatedArticleId;
    }
}
