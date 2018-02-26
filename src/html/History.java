package html;

import java.sql.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class History implements Serializable{
    private String url;
    private Date date;

    public History(String u, Date  d){
        url = u;
        date = d;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String u) {
        url = u;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date d) {
        date = d;
    }
}
