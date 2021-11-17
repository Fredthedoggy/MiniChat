package me.fredthedoggy.minichat;

public class ChatFormat {
    private final String prefix;
    private final String name;
    private final String suffix;
    public ChatFormat(String prefix, String name, String suffix) {
        this.prefix = prefix;
        this.name = name;
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
}
