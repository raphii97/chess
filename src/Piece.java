public class Piece {
    private final Player player;
    private final Type type;

    public Piece(Player player, Type type){
        this.player = player;
        this.type = type;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Type getType(){
        return this.type;
    }
}
