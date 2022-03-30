package sk.ness.academy.service;

import org.springframework.stereotype.Service;
import sk.ness.academy.dao.CommentDAO;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentDAO commentDAO;

    @Override
    public Comment findByID(Integer commentID) {
        return this.commentDAO.findByID(commentID);
    }

    @Override
    public void createComment(Comment comment) {
        this.commentDAO.createComment(comment);
    }

    @Override
    public void deleteComment(Integer commentID) {
        this.commentDAO.deleteComment(commentID);
    }
}
