package sk.ness.academy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.ArticleWithoutComments;
import sk.ness.academy.dto.Author;
import sk.ness.academy.dto.AuthorStats;
import sk.ness.academy.exception.ApiRequestException;
import sk.ness.academy.service.ArticleService;
import sk.ness.academy.service.ArticleServiceImpl;
import sk.ness.academy.service.AuthorService;
import sk.ness.academy.service.CommentService;

@RestController
public class BlogController {

  @Resource
  private ArticleService articleService;

  @Resource
  private AuthorService authorService;

  @Resource
  private CommentService commentService;

  // ~~ Article
  @RequestMapping(value = "articles", method = RequestMethod.GET)
  public List<ArticleWithoutComments> getAllArticles() {
    return this.articleService.findAll();
  }

  @RequestMapping(value = "articles/{articleId}", method = RequestMethod.GET)
  public Article getArticle(@PathVariable final Integer articleId) {
    try {
      return this.articleService.findByID(articleId);
    } catch (NullPointerException e) {
      throw new ApiRequestException("Article with id " + articleId + " do not exists.",HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "articles/search/{searchText}", method = RequestMethod.GET)
  public List<ArticleWithoutComments> searchArticle(@PathVariable final String searchText) {
    return this.articleService.searchArticle(searchText);
  }

  @RequestMapping(value = "articles/{articleId}", method = RequestMethod.DELETE)
  public void deleteArticle(@PathVariable final Integer articleId) {
    try {
      this.articleService.deleteByID(articleId);
    } catch (NullPointerException e) {
      throw new ApiRequestException("Article with id " + articleId + " do not exists.",HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "articles", method = RequestMethod.PUT)
  public void addArticle(@RequestBody (required = false) final Article article) {
    if (article == null) throw new ApiRequestException("Json body (article) is empty", HttpStatus.BAD_REQUEST);
    if (article.getTitle() == null || article.getAuthor() == null || article.getText() == null) throw new ApiRequestException("Json body do not contain whole article. Either title, text, or author is missing.", HttpStatus.BAD_REQUEST);
    this.articleService.createArticle(article);
  }


  // ~~ Author
  @RequestMapping(value = "authors", method = RequestMethod.GET)
  public List<Author> getAllAuthors() {
	  return this.authorService.findAll();
  }

  @RequestMapping(value = "authors/stats", method = RequestMethod.GET)
  public List<AuthorStats> authorStats() {
	  return this.authorService.getAuthorStats();
  }

  // ~~ Comments

  @RequestMapping(value = "comments/{commentId}", method = RequestMethod.GET)
  public Comment getComment(@PathVariable final Integer commentId) {
    try {
      return this.commentService.findByID(commentId);
    } catch (NullPointerException e) {
      throw new ApiRequestException("Comment with id " + commentId + " do not exists.",HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "comments", method = RequestMethod.PUT)
  public void addComment(@RequestBody (required = false) Comment comment) {
    if (comment == null) throw new ApiRequestException("Json body (comment) is empty", HttpStatus.BAD_REQUEST);
    if (comment.getAuthor() == null || comment.getText() == null) throw new ApiRequestException("Json body do not contain whole comment. Either author or text is missing.", HttpStatus.BAD_REQUEST);
    if (getArticle(comment.getArticleID()) != null) this.commentService.createComment(comment);
  }

  @RequestMapping(value = "comments/{commentId}", method = RequestMethod.DELETE)
  public void removeComment(@PathVariable final Integer commentId) {
    try {
      this.commentService.deleteComment(commentId);
    } catch (NullPointerException e) {
      throw new ApiRequestException("Comment with id " + commentId + " do not exists.",HttpStatus.NOT_FOUND);
    }
  }

  // ~~ Others

  @RequestMapping(value = {"**","authors/**"}, method = RequestMethod.GET)
  public void didNotMatchAnything() {
    throw new ApiRequestException("Bad mapping.", HttpStatus.BAD_REQUEST);
  }

}
