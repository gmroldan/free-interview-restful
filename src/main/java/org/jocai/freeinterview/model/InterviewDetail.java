package org.jocai.freeinterview.model;

/**
 * Created by martin on 02/06/17.
 */
public class InterviewDetail {
    private String comments;

    /**
     * Class constructor.
     *
     * @param comments
     */
    public InterviewDetail(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
