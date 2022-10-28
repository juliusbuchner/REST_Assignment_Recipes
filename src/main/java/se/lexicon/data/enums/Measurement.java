package se.lexicon.data.enums;

public enum Measurement {
    TBSP("Tablespoon", 1), TSP("Teaspoon", 2), G("Gram" ,3), HG("Hectogram", 4),
    KG("Kilogram", 5), ML("Milliliter", 6), CL("Centiliter", 7), DL("Deciliter", 8),
    STS("Pieces", 9), ST("Piece", 10);
    private final String measurementName;
    private final Integer id;

    Measurement(String measurementName, Integer id){this.measurementName = measurementName; this.id = id;}

    public String getMeasurementName() {
        return measurementName;
    }
    public Integer getId() {return id;}
}
