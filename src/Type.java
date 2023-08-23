public enum Type {
    PAWN("P"), ROOK("R"), KNIGHT("N"), BISHOP("B"), QUEEN("Q"), KING("K"), EMPTY("-");

    private final String symbol;

    Type(String symbol){
        this.symbol = symbol;
    }

    @Override
    public String toString(){
        return this.symbol;
    }
}
