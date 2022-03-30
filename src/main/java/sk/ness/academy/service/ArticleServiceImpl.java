package sk.ness.academy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sk.ness.academy.dao.ArticleDAO;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.ArticleWithoutComments;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

  @Resource
  private ArticleDAO articleDAO;

  @Override
  public Article findByID(final Integer articleId) {
	  return this.articleDAO.findByID(articleId);
  }

  @Override
  public List<ArticleWithoutComments> findAll() {
	  return this.articleDAO.findAll();
  }

  @Override
  public void deleteByID(Integer articleId) {
    this.articleDAO.deleteByID(articleId);
  }

  @Override
  public void createArticle(final Article article) {
    this.articleDAO.persist(article);
  }

  @Override
  public void ingestArticles(final String jsonArticles) {
    throw new UnsupportedOperationException("Article ingesting not implemented.");
  }

  @Override
  public List<ArticleWithoutComments> searchArticle(String searchText) {
    return articleDAO.searchArticle(searchText);
  }
}
