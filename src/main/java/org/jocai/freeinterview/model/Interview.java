package org.jocai.freeinterview.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Interviewer interviewer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date interviewDate;
//    private InterviewDetail detail;

    /**
     * Class constructor.
     */
    public Interview() {}

    /**
     * Class constructor.
     *
     * @param interviewer
     * @param interviewDate
     */
    public Interview(Interviewer interviewer, Date interviewDate) {
        this.interviewer = interviewer;
        this.interviewDate = interviewDate;
    }
    /**
     * Class constructor.
     *
     * @param id
     * @param interviewer
     * @param interviewDate
     */
    public Interview(Long id, Interviewer interviewer, Date interviewDate) {
        this.id = id;
        this.interviewer = interviewer;
        this.interviewDate = interviewDate;
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

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    /*public InterviewDetail getDetail() {
        return detail;
    }

    public void setDetail(InterviewDetail detail) {
        this.detail = detail;
    }*/
}
