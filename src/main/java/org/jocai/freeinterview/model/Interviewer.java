package org.jocai.freeinterview.model;

/**
 * Created by martin on 02/06/17.
 */
public class Interviewer {
    private String name;

    /**
     * Class constructor with no-args.
     */
    public Interviewer() {
        this.name = name;
    }

    /**
     * Class constructor.
     *
     * @param name
     */
    public Interviewer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
