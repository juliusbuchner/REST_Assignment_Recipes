package se.lexicon.data.enums;

public enum Measurement {
    TBSP("Tablespoon"), TSP("Teaspoon"), G("Gram"), HG("Hectogram"),
    KG("Kilogram"), ML("Milliliter"), CL("Centiliter"), DL("Deciliter"),
    STS("Pieces"), ST("Piece");
    private final String name;
    public String getName() {
        return name;
    }
    Measurement(String name){this.name = name;}

}