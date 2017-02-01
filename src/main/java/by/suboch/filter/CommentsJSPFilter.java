package by.suboch.filter;

import by.suboch.ajax.BiTuple;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.entity.Account;
import by.suboch.entity.Comment;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.logic.CommentLogic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
@WebFilter(filterName = "CommentsJSPFilter", urlPatterns = {"/jsp/admin/comments.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class CommentsJSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            CommentLogic commentLogic = new CommentLogic();
            AccountLogic accountLogic = new AccountLogic();
            List<BiTuple<Comment, Account>> data = new LinkedList<>();
            List<Comment> comments = commentLogic.loadAllComments();
            for (Comment comment : comments) {
                data.add(new BiTuple<>(comment, accountLogic.loadAccount(comment.getAccountId())));
            }
            request.setAttribute(CommandConstants.ATTR_COMMENT_LIST, data);
        } catch (LogicException e) {
            AbstractServletCommand.handleDBError(e, request, response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
