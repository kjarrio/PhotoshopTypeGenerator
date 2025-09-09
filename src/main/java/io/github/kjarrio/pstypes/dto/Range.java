package io.github.kjarrio.pstypes.dto;

public class Range {

    private Integer startLine;
    private Integer endLine;

    public Range() {

    }

    public Range(Integer startLine, Integer endLine) {
        this.startLine = startLine;
        this.endLine = endLine;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    public Integer getEndLine() {
        return endLine;
    }

    public void setEndLine(Integer endLine) {
        this.endLine = endLine;
    }
}
