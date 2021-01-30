package com.bookbin.coconeobank;

public class homeloanHelper {
    private double p;
    private double r;
    private double t;
    private double emi;

    public homeloanHelper(double p, double r, double t, double emi) {
        this.p = p;
        this.r = r;
        this.t = t;
        this.emi = emi;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }
}
