package com.example.expenda;

public class IncomeModel {
    String id,amount, note, type;
    long timestamp;
    String Url;

    public IncomeModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public IncomeModel(String id, String amount, String note, String type, long timestamp) {
        this.id = id;
        this.amount = amount;
        this.note = note;
        this.type = type;
        this.timestamp = timestamp;
        this.Url = Url;
    }


}
