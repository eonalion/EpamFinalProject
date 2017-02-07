package by.suboch.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 */
public class NotNullElement  extends TagSupport{
    private Object test;

    public void setTest(Object test) {
        this.test = test;
    }

    @Override
    public int doStartTag() throws JspException {
        return test != null ? EVAL_BODY_INCLUDE : SKIP_BODY;
    }
}
