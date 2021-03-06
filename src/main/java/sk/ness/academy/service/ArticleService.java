package sk.ness.academy.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.ArticleWithoutComments;

public interface ArticleService {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  /** Returns all available {@link Article}s */
	  List<ArticleWithoutComments> findAll();

	  /** Deletes {@link Article} by ID */
	  void deleteByID(Integer articleId);

	  /** Creates new {@link Article} */
	  void createArticle(Article article);

	  /** Creates new {@link Article}s by ingesting all articles from json */
	  void ingestArticles(String jsonArticles);

	  /** Search and return {@link ArticleWithoutComments}s that contains {@param searchText} */
      List<ArticleWithoutComments> searchArticle(String searchText);
}
