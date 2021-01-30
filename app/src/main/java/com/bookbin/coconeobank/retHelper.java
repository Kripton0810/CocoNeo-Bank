package com.bookbin.coconeobank;

public class retHelper {
    int cage;
    int rage;
    int eage;
    int yeargap;
    int afteryeargap;
    double roi;
    double inflation;
    double inflation_adjust;
    double anualexp;
    double future_value;

    public retHelper(int cage, int rage, int eage, int yeargap, int afteryeargap, double roi, double inflation, double inflation_adjust, double anualexp, double future_value) {
        this.cage = cage;
        this.rage = rage;
        this.eage = eage;
        this.yeargap = yeargap;
        this.afteryeargap = afteryeargap;
        this.roi = roi;
        this.inflation = inflation;
        this.inflation_adjust = inflation_adjust;
        this.anualexp = anualexp;
        this.future_value = future_value;
    }

    public int getCage() {
        return cage;
    }

    public void setCage(int cage) {
        this.cage = cage;
    }

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public int getEage() {
        return eage;
    }

    public void setEage(int eage) {
        this.eage = eage;
    }

    public int getYeargap() {
        return yeargap;
    }

    public void setYeargap(int yeargap) {
        this.yeargap = yeargap;
    }

    public int getAfteryeargap() {
        return afteryeargap;
    }

    public void setAfteryeargap(int afteryeargap) {
        this.afteryeargap = afteryeargap;
    }

    public double getRoi() {
        return roi;
    }

    public void setRoi(double roi) {
        this.roi = roi;
    }

    public double getInflation() {
        return inflation;
    }

    public void setInflation(double inflation) {
        this.inflation = inflation;
    }

    public double getInflation_adjust() {
        return inflation_adjust;
    }

    public void setInflation_adjust(double inflation_adjust) {
        this.inflation_adjust = inflation_adjust;
    }

    public double getAnualexp() {
        return anualexp;
    }

    public void setAnualexp(double anualexp) {
        this.anualexp = anualexp;
    }

    public double getFuture_value() {
        return future_value;
    }

    public void setFuture_value(double future_value) {
        this.future_value = future_value;
    }
}
