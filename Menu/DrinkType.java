package Menu;

public class DrinkType {
    private final String name;
    private final String explanation;

    public DrinkType(String name, String explanation) {
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
