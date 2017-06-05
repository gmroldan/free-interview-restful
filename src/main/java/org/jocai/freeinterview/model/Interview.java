package org.jocai.freeinterview.model;

import java.util.Date;

/**
 * Created by martin on 02/06/17.
 */
public class Interview {
    private Long id;
    private Interviewer interviewer;
    private Date date;
    private InterviewDetail detail;

    /**
     * Class constructor.
     */
    public Interview() {}

    /**
     * Class constructor.
     *
     * @param interviewer
     * @param date
     */
    public Interview(Interviewer interviewer, Date date) {
        this.interviewer = interviewer;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public InterviewDetail getDetail() {
        return detail;
    }

    public void setDetail(InterviewDetail detail) {
        this.detail = detail;
    }
}
