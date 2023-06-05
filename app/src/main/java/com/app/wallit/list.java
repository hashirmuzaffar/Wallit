package com.app.wallit;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

public class list extends ArrayList<list> {
    int amount;
    String category;
    String date;

    public list(int amount, String str) {
        this.amount = amount;
        this.category = str;
        Date date = new Date();
        this.date = ""+date;
    }

    public list(int amount, String str, String date) {
        this.amount = amount;
        this.category = str;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getAmountString() {
        return "" + amount +"Rs";
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @NonNull
    @Override
    public Stream<list> stream() {
        return super.stream();
    }

    @NonNull
    @Override
    public Stream<list> parallelStream() {
        return super.parallelStream();
    }
}
