import java.util.Random;

public class TileSet {

    private int SIZE;
    private final Tile[] Tiles;
    Random random = new Random();


    /**
     * BAG ADT
     */

    public TileSet(){
        Tiles = new Tile[100];
        SIZE = 0;
    }

    // adds new tile to set
    //
    //finds first null index and puts tile in it
    public void add(Tile tile){
        /*if(SIZE == 0){
            Tiles[0] = tile;
            SIZE ++;
        }*/
        /*else*/
            for(int x=0; x<SIZE+1; x++){
                if(Tiles[x] == null){
                    Tiles[x] = tile;
                    SIZE ++;
                    return;
                }
            }

    }

    // finds and removes first index of specified item
    //
    // traverses until indexed item and argument equal
    public boolean remove(Tile tile){
        for(int x=0; x<SIZE; x++){
            if(Tiles[x].getLetter().equals(tile.getLetter())){
                Tiles[x] = null;
                SIZE --;
                return true;
            }
        }
        return false;
    }


    // randomly grab item from bag
    //
    // recursively go through until valid tile found
    // return it and set position to null
    // decrement SIZE

    // what is happening right now is
    // size decrements every time,
    // meaning some elements will never be accessed
    // because they are at indexes larger than size
    // cannot index by random number based on num elem in array
    //
    public Tile grab(){
        int index = random.nextInt(100);

        if(Tiles[index] != null){
            Tile temp = Tiles[index];
            Tiles[index] = null;
            SIZE--;
            return temp;
        }
        else{
            return grab();
        }
    }

    public int getSize(){
        return SIZE;
    }

    public boolean isEmpty(){
        return SIZE == 0;
    }

    // Clears the bag, empties out the tileset
    //
    // loops through tiles and sets each index to null
    public void clear(){
        for(int x=0; x<SIZE; x++){
            Tiles[x] = null;
        }
        SIZE = 0;
    }








}
