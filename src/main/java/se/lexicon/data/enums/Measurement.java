package se.lexicon.data.enums;

public enum Measurement {
    TBSP("Tablespoon"), TSP("Teaspoon"), G("Gram"), HG("Hectogram"),
    KG("Kilogram"), ML("Milliliter"), CL("Centiliter"), DL("Deciliter"),
    STS("Pieces"), ST("Piece");
    private String measurementName;
    Measurement(String measurementName){this.measurementName = measurementName;}

    public String getMeasurementName() {
        return measurementName;
    }
}
