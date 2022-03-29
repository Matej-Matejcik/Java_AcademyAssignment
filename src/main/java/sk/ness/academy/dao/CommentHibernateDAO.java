package sk.ness.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;

@Repository
public class CommentHibernateDAO implements CommentDAO{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Comment findByID(Integer commentID) {
        return (Comment) this.sessionFactory.getCurrentSession().get(Comment.class, commentID);
    }

    @Override
    public void createComment(Comment comment) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(comment);
    }

}
