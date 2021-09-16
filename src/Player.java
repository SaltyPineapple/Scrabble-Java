public class Player {
    private Tile[] hand;
    private int score;

    public Player(){
        hand = new Tile[7];
        score = 0;
    }

    public boolean draw(Tile tile){
        for(int x=0; x<7; x++) {
            if (hand[x] == null) {
                hand[x] = tile;
                return true;
            }
        }
        return false;
    }

    public Tile play(int index){
        return hand[index];
    }

    public void remove(int index){
        hand[index] = null;
    }

    public void updateHand(TileSet set){
        if(draw(set.grab())){
           updateHand(set);
        }
    }

    public void updateHand(LinkedList.Node temp){

        if(draw(temp.getData())){
            LinkedList.Node newTemp = temp.next;
            if(newTemp != null){
                updateHand(newTemp);
            }
        }

    }

    public void returnTiles(LinkedList list){

        TileSet newSet = new TileSet();
        for(LinkedList.Node temp = list.head.next; temp.next != null; temp = temp.next){
           System.out.println(temp.data.getLetter());
           newSet.add(temp.data);
           System.out.println(newSet.getSize());
        }
        for(int x=0; x<7; x++){
            updateHand(newSet);
        }

    }





    public Tile[] getHand() {
        return hand;
    }

    public void setHand(Tile[] hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder playerHand = new StringBuilder();
        for(int x=0; x<7; x++){
            if(hand[x] == null){
                playerHand.append(" ");
            }
            else{
                playerHand.append(hand[x].getLetter() + " ");
            }
        }
        return playerHand.toString();
    }
}
