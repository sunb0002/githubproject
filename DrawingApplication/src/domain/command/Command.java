package domain.command;

public enum Command {

    CREATE_CANVAS("C"), DRAW_LINE("L"), DRAW_RECTANGLE("R"), BUCKET_FILL("B"), QUIT("Q"), INVALID("INVALID");

    private final String name;

    private Command(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}