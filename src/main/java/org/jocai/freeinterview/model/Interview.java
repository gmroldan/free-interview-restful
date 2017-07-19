package org.jocai.freeinterview.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Gerardo Martín Roldán on 02/06/17.
 */
@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Interviewer interviewer;
    private Date date;
//    private InterviewDetail detail;

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
    /**
     * Class constructor.
     *
     * @param id
     * @param interviewer
     * @param date
     */
    public Interview(Long id, Interviewer interviewer, Date date) {
        this.id = id;
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

    /*public InterviewDetail getDetail() {
        return detail;
    }

    public void setDetail(InterviewDetail detail) {
        this.detail = detail;
    }*/
}
