package beans.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Track {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-yyyy");

    private int id;
    private int userId;
    private int taskId;
    private Date begin;
    private Date end;
    private String comment;

    public Track(int id, int userId, int taskId, java.util.Date begin, java.util.Date end, String comment) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.begin = new Date(begin.getTime());
        this.end = new Date(end.getTime());
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public java.util.Date getBegin() {
        return begin;
    }

    public void setBegin(java.util.Date begin) {
        this.begin = new Date(begin.getTime());
    }

    public java.util.Date getEnd() {
        return end;
    }

    public void setEnd(java.util.Date end) {
        this.end = new Date(end.getTime());
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFormattedEnd() {
        return dateFormat.format(getEnd());
    }

    public String getFormattedBegin() {
        return dateFormat.format(getBegin());
    }

    @Override
    public String toString() {
        return "Track {" +
                "id=" + getId() + "," +
                "user_id=" + getUserId() + "," +
                "task_id=" + getTaskId() + "," +
                "begin=" + dateFormat.format(getBegin()) + "," +
                "end=" + dateFormat.format(getEnd()) + "," +
                "comment=" + getComment() +
                "}";
    }
}
