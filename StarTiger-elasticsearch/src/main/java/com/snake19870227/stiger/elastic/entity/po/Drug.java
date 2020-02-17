package com.snake19870227.stiger.elastic.entity.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Bu HuaYang
 */
@Document(indexName = "drug", type = "_doc")
public class Drug {

    @Id
    private String id;

    private String name;
    private String factory;
    private String content;

    @Override
    public String toString() {
        return "Drug{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", factory='" + factory + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
