package sk.ness.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.exception.ApiRequestException;
import sk.ness.academy.service.ArticleServiceImpl;

import javax.annotation.Resource;

@Repository
public class CommentHibernateDAO implements CommentDAO{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Comment findByID(Integer commentID) {
        Comment comment = this.sessionFactory.getCurrentSession().get(Comment.class, commentID);
        if (comment == null) throw new ApiRequestException("Comment with id " + commentID + " do not exists.", HttpStatus.NOT_FOUND);
        return this.sessionFactory.getCurrentSession().get(Comment.class, commentID);
    }

    @Override
    public void createComment(Comment comment) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(comment);
    }

    @Override
    public void deleteComment(Integer commentID) {
        this.sessionFactory.getCurrentSession().delete(findByID(commentID));
    }
}
