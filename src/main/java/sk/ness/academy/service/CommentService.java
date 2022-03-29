package sk.ness.academy.service;

import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;

public interface CommentService {

    /** Returns {@link Comment} with provided ID */
    Comment findByID(Integer commentID);

    /** Creates new {@link Comment} */
    void createComment(Comment comment);
}
