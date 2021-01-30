package com.bookbin.coconeobank;

public class currhelper {
    private String to;
    private String from;
    private String toamt;
    private String fromamt;

    public currhelper(String to, String from, String toamt, String fromamt) {
        this.to = to;
        this.from = from;
        this.toamt = toamt;
        this.fromamt = fromamt;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getToamt() {
        return toamt;
    }

    public void setToamt(String toamt) {
        this.toamt = toamt;
    }

    public String getFromamt() {
        return fromamt;
    }

    public void setFromamt(String fromamt) {
        this.fromamt = fromamt;
    }
}
