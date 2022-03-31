package sk.ness.academy.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticleWithoutComments;
import sk.ness.academy.exception.ApiRequestException;

@Repository
public class ArticleHibernateDAO implements ArticleDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public Article findByID(final Integer articleId) {
    Article article = this.sessionFactory.getCurrentSession().get(Article.class, articleId);
    Hibernate.initialize(article.getComments());
    return article;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ArticleWithoutComments> findAll() {
      return this.sessionFactory.getCurrentSession().createQuery("select new sk.ness.academy.dto.ArticleWithoutComments(id, title, text, author, createTimestamp) from Article ", ArticleWithoutComments.class).list();
  }

  @Override
  public void deleteByID(Integer articleId) {
    this.sessionFactory.getCurrentSession().delete(findByID(articleId));
  }

  @Override
  public void persist(final Article article) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(article);
  }

  @Override
  public List<ArticleWithoutComments> searchArticle(String searchText) {
    return findAll().stream()
            .filter(a -> a.getAuthor().contains(searchText) || a.getText().contains(searchText) || a.getTitle().contains(searchText))
            .collect(Collectors.toList());
  }
}
