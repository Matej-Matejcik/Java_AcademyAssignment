package sk.ness.academy.domain;


import sk.ness.academy.dao.ArticleDAO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "comments")
@SequenceGenerator(name = "articles_seq_store", sequenceName = "article_seq", allocationSize = 1)
public class Comment {

    public Comment() {
        this.createdDate = new Date();
    }

    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articles_seq_store")
    private Integer id;

    @Column(name = "article_id")
    private Integer articleID;

    @Column(name = "author", length = 250)
    private String author;

    @Column(name = "text", length = 2000)
    private String text;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
