

public class Product {
//-----------------------------------------------------
// Title: Product Class
// Author: Azerhan BAĞCI
// Description: This class stores data to be accessed later.
//-----------------------------------------------------
    private String name;
    private int piece;

    public Product(String name, int piece) {
        this.name = name;
        this.piece = piece;
    }

    public int getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }
}
