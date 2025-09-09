package io.github.kjarrio.pstypes.dto;

public class Pair {

    private final String key;
    private final String value;
    private String comment = "";

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Boolean hasComment() {
        return !comment.isEmpty();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
