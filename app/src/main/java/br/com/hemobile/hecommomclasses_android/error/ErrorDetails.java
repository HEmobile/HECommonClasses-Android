package br.com.hemobile.hecommomclasses_android.error;

import java.util.Date;

/**
 * Created by hemobile on 23/10/14.
 */
public class ErrorDetails {

    private String thread;
    private String stackTrace;
    private String name;
    private String cause;
    private Date datetime;

    public ErrorDetails(String name, String stackTrace, String thread, String cause) {
        this.thread = thread;
        this.stackTrace = stackTrace;
        this.name = name;
        this.cause = cause;
        this.datetime = new Date();
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
