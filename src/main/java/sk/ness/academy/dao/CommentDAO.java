package sk.ness.academy.dao;

import sk.ness.academy.domain.Comment;

public interface CommentDAO {

    /** Returns {@link Comment} with provided ID */
    Comment findByID(Integer commentID);

    /** Creates new {@link Comment} */
    void createComment(Comment comment);

    /** Delete comment with provided ID */
    void deleteComment(Integer commentID);
}
