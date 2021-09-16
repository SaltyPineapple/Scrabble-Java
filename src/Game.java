import java.util.Scanner;

public class Game {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        Player player1 = new Player();

        TileSet tiles = new TileSet();

        Board board = new Board(15);

        LinkedList word = new LinkedList();
        //apiConnection api = new apiConnection();
        boolean firstPlay;

        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("B", 3));
        tiles.add(new Tile("B", 3));
        tiles.add(new Tile("C", 4));
        tiles.add(new Tile("C", 4));
        tiles.add(new Tile("D", 2));
        tiles.add(new Tile("D", 2));
        tiles.add(new Tile("D", 2));
        tiles.add(new Tile("D", 2));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("F", 4));
        tiles.add(new Tile("F", 4));
        tiles.add(new Tile("G", 3));
        tiles.add(new Tile("G", 3));
        tiles.add(new Tile("G", 3));
        tiles.add(new Tile("H", 3));
        tiles.add(new Tile("H", 3));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("J", 8));
        tiles.add(new Tile("K", 5));
        tiles.add(new Tile("L", 2));
        tiles.add(new Tile("L", 2));
        tiles.add(new Tile("L", 2));
        tiles.add(new Tile("L", 2));
        tiles.add(new Tile("M", 4));
        tiles.add(new Tile("M", 4));
        tiles.add(new Tile("N", 2));
        tiles.add(new Tile("N", 2));
        tiles.add(new Tile("N", 2));
        tiles.add(new Tile("N", 2));
        tiles.add(new Tile("N", 2));
        tiles.add(new Tile("N", 2));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("P", 3));
        tiles.add(new Tile("P", 3));
        tiles.add(new Tile("Q", 10));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("S", 1));
        tiles.add(new Tile("S", 1));
        tiles.add(new Tile("S", 1));
        tiles.add(new Tile("S", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("U", 2));
        tiles.add(new Tile("U", 2));
        tiles.add(new Tile("U", 2));
        tiles.add(new Tile("U", 2));
        tiles.add(new Tile("V", 5));
        tiles.add(new Tile("V", 5));
        tiles.add(new Tile("W", 4));
        tiles.add(new Tile("W", 4));
        tiles.add(new Tile("X", 8));
        tiles.add(new Tile("Y", 3));
        tiles.add(new Tile("Y", 3));
        tiles.add(new Tile("Z", 10));
        tiles.add(new Tile("?", 0));
        tiles.add(new Tile("?", 0));

        player1.updateHand(tiles);


        while(true){


            board.toString();

            System.out.println(player1.toString());

            System.out.println(player1.getScore());

            int tile = input.nextInt();
            int x = input.nextInt();
            int y = input.nextInt();


            if(tile != -1){
                Tile temp = player1.play(tile);

                if(board.play(temp, x, y)){
                    player1.remove(tile);
                    word.insertOrder(temp,x,y);
                }

            }
            else{
                // if word is valid
                //  -> check with API
                // in order to check with api
                //  need to run word grab alg
                //api.api(word.toWord());

                if(board.isValid){
                    player1.updateHand(tiles);
                    System.out.println(word.toString());
                    board.clearCurrent(word);
                    board.getAllWords(word);
                    board.printArray();
                    System.out.println(board.calcWordScore());
                    player1.setScore(player1.getScore() + board.calcWordScore());


                    board.clearPlayedWords();
                    word.clear();
                    board.setFirstTile(true);
                    board.setPlayableColNum(-1);
                    board.setPlayableRowNum(-1);
                }
                else{
                    //give option to return word
                    System.out.println("Not a valid play");
                    System.out.println("Return tiles? [1(Y) / 0(N)]");
                    int returnTiles = input.nextInt();
                    if(returnTiles == 1){
                        board.removeTiles(word);
                        player1.updateHand(word.head.next);
                        word.clear();
                        board.setFirstTile(true);
                        board.setPlayableColNum(-1);
                        board.setPlayableRowNum(-1);
                    }
                }

                // otherwise don't allow to end
                // return tiles to player
            }


        }

    }
}
