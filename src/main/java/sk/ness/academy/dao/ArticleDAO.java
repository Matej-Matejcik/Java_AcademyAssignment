package sk.ness.academy.dao;

import java.util.List;

import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.ArticleWithoutComments;

public interface ArticleDAO {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  /** Returns all available {@link Article}s */
	  List<ArticleWithoutComments> findAll();

	  /** Delete {@link Article} by ID*/
	  void deleteByID(Integer articleId);

	  /** Persists {@link Article} into the DB */
	  void persist(Article article);

	/** Search and return {@link ArticleWithoutComments}s that contains {@param searchText} */
	List<ArticleWithoutComments> searchArticle(String searchText);
}
