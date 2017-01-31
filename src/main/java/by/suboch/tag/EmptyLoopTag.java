package by.suboch.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 */
public class EmptyLoopTag extends TagSupport{
    private int iterNum;

    public void setIterNum(int iterNum) {
        this.iterNum = iterNum;
    }

    @Override
    public int doStartTag() throws JspException {
        return iterNum == 0 ? EVAL_BODY_INCLUDE : SKIP_BODY;
    }
}
