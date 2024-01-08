package Menu;

public class Menu {
    private String name;
    private String explanation;

    public Menu(String name, String explanation) {
        this.name = name;
        this.explanation = explanation;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }
}
