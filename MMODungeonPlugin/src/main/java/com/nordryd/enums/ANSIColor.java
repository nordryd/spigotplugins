package com.nordryd.enums;

public enum ANSIColor
{
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");

    private final String seq;

    private ANSIColor(String seq) {
        this.seq = seq;
    }

    /**
     * @return the ANSI sequence corresponding to the color.
     */
    public String getSeq() {
        return this.seq;
    }
}
