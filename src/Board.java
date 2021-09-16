import java.util.ArrayList;

public class Board {

    private final Cell[][] grid;
    private final int SIZE;
    private boolean isPlayableRow;
    private boolean isPlayableCol;
    private boolean isFirstTile = true;
    private int playableRowNum = -1;
    private int playableColNum = -1;
    private boolean isFirstPlay = true;
    private ArrayList<LinkedList> playedWords;

    apiConnection api = new apiConnection();
    boolean isValid;

    /**
     * Constructor called when board is created in game
     * size is the grid length and width
     * @param size
     */
    public Board(int size){
        SIZE = size;
        grid = new Cell[SIZE][SIZE];
        for(int row=0; row<SIZE; row++){
            for(int col=0; col<SIZE; col++){
                grid[row][col] = new Cell(row, col);
            }
        }

        playedWords = new ArrayList<>();
    }

    /**
     * Used every time the user plays a tile on the board
     * @param tile -> the tile to be played
     * @param row -> the row they selected
     * @param col -> the column they selected
     * @return -> true if valid play
     */

    public boolean play(Tile tile, int row, int col){

        // check if the space is empty first
        if(!grid[row][col].isOccupied()){
            if(isFirstTile){

                isPlayableRow = true;
                isPlayableCol = true;
                playableRowNum = row;
                playableColNum = col;

                grid[row][col].setData(tile);
                grid[row][col].setOccupied(true);
                // if true -> allow to play word
                if(checkNeighbor(row, col)){
                    System.out.println("Valid placement");
                }
                isFirstTile = false;
                grid[row][col].setCurrent(true);
                return true;
            }
            else{

                if(row == playableRowNum){
                    if(isPlayableRow){
                        grid[row][col].setData(tile);
                        grid[row][col].setOccupied(true);

                        if(checkNeighbor(row, col)){
                            System.out.println("Valid placement");
                        }
                        isPlayableCol = false;
                        playableColNum = -1;
                        grid[row][col].setCurrent(true);
                        return true;
                    }
                    else{
                        System.out.println("Invalid placement col");
                        return false;
                    }
                }
                else if(col == playableColNum){
                    if(isPlayableCol){
                        grid[row][col].setData(tile);
                        grid[row][col].setOccupied(true);

                        if(checkNeighbor(row, col)){
                            System.out.println("Valid placement");
                        }
                        isPlayableRow = false;
                        playableRowNum = -1;
                        grid[row][col].setCurrent(true);
                        return true;
                    }
                    else{
                        System.out.println("invalid placement row");
                        return false;
                    }
                }
                else{
                    System.out.println("invalid placement completely");
                    return false;
                }
            }

        }
        return false;
    }

    // creates vertical words

    public LinkedList northSouth(int row, int col){
        LinkedList ns = new LinkedList();
        ns.insertOrder(grid[row][col].getData(), row, col);

        int newCol = col-1;

        while(newCol >=0 && grid[row][newCol].isOccupied()){
            ns.insertOrder(grid[row][newCol].getData(), row, newCol);
            newCol--;
        }
        newCol = col+1;
        while(newCol <= SIZE-1 && grid[row][newCol].isOccupied()){
            ns.insertOrder(grid[row][newCol].getData(), row, newCol);
            newCol++;
        }

        return ns;

    }

    // creates horizontal words

    public LinkedList eastWest(int row, int col){
        LinkedList ew = new LinkedList();

        ew.insertOrder(grid[row][col].getData(), row, col);

        int newRow = row-1;
        while(newRow >= 0 && grid[newRow][col].isOccupied()){
            ew.insertOrder(grid[newRow][col].getData(), newRow, col);
            newRow--;
        }

        newRow = row+1;
        while(newRow <= SIZE-1 && grid[newRow][col].isOccupied()){
            ew.insertOrder(grid[newRow][col].getData(), newRow, col);
            newRow++;
        }


        return ew;
    }


    // calls NS and EW
    // calls API
    // calls check border

    public boolean checkNeighbor(int row, int col){
        LinkedList northSouth = northSouth(row, col);
        LinkedList eastWest = eastWest(row, col);

        System.out.println(northSouth.toString());
        System.out.println(eastWest.toString());

        boolean isTrue = false;

        // makes sure word is >= 2
        // checks if word is valid
        // checks if borders anything
        if (northSouth.toWord().length() > 1 && api.api(northSouth.toWord()) && checkBorder(northSouth)) {
            isTrue = true;
        }

        if (eastWest.toWord().length() > 1 && api.api(eastWest.toWord()) && checkBorder(eastWest)) {
            isTrue = true;
        }


        isValid = isTrue;
        return isTrue;

    }

    public boolean checkBorder(LinkedList list){

//        if(isFirstPlay){
//            //
//            // set first play to be in middle
//            //
//            LinkedList.Node temp = list.head;
//            while(temp.next != null){
//                if(temp.y == SIZE/2 && temp.x == SIZE/2){
//                    isFirstPlay = false;
//                    return true;
//                }
//                temp = temp.next;
//            }
//            isFirstPlay = false;
//            return true;
//        }

        LinkedList.Node head = list.head;
        boolean borders = false;
        while(head.next.next != null){
            LinkedList.Node temp = head.next;

            // check four borders of tile

            if(temp.y +1 <= SIZE){
                if(grid[temp.x][temp.y + 1].isOccupied()){
                    if(!grid[temp.x][temp.y + 1].isCurrent()){
                        borders = true;
                        break;
                    }
                }
            }
            if(temp.y-1 >= 0){
                if(grid[temp.x][temp.y -1].isOccupied()){
                    if(!grid[temp.x][temp.y -1].isCurrent()){
                        borders = true;
                        break;
                    }
                }
            }

            if(temp.x + 1 <= SIZE){
                if(grid[temp.x +1][temp.y].isOccupied()){
                    if(!grid[temp.x +1][temp.y].isCurrent()){
                        borders = true;
                        break;
                    }
                }
            }
            if(temp.x - 1 >= 0){
                if(grid[temp.x - 1][temp.y].isOccupied()){
                    if(!grid[temp.x - 1][temp.y].isCurrent()){
                        borders = true;
                        break;
                    }
                }
            }

            head = head.next;
        }
        return borders;
    }

    public void clearCurrent(LinkedList list){
        LinkedList.Node head = list.head;
        while(head.next.next != null){
            grid[head.x][head.y].setCurrent(false);
            head = head.next;
        }
    }


    // run EW NS on all current tiles
    // save each list in an array list

    // get if row or col, run one NS/EW accordingly
    // run as many of other as needed
    // give word, that way it will always start with first letter
    // pretty much just need index of first letter,


    public ArrayList<LinkedList> getAllWords(LinkedList word){
        if(isPlayableRow){
            LinkedList mainWord = northSouth(word.head.next.x, word.head.next.y);
            if(mainWord.getSize() > 1){
                playedWords.add(mainWord);
            }
            for(LinkedList.Node temp = word.head.next; temp != null; temp = temp.next) {
                LinkedList otherWord = eastWest(temp.x, temp.y);
                if(otherWord.getSize() > 1){
                    playedWords.add(otherWord);
                }
            }
        }
        else if(isPlayableCol) {
            LinkedList mainWord = eastWest(word.head.next.x, word.head.next.y);
            if(mainWord.getSize() > 1){
                playedWords.add(mainWord);
            }
            for(LinkedList.Node temp = word.head.next; temp != null; temp = temp.next) {
                LinkedList otherWord = northSouth(temp.x, temp.y);
                if(otherWord.getSize() > 1){
                    playedWords.add(otherWord);
                }
            }
        }
        return playedWords;
    }


    public void clearPlayedWords(){
        playedWords.clear();
    }

    public void printArray(){
        int num = 1;
        for (LinkedList item: playedWords) {
            System.out.print(String.format("Word Num %d: ", num));
            System.out.println(item.toString());
            num++;
        }
    }

    public int calcWordScore(){
        int score = 0;
        for(LinkedList item : playedWords){
            for(LinkedList.Node temp = item.head.next; temp != null; temp = temp.next){
                score += temp.getData().getScore();
            }
        }
        return score;
    }


    public void removeTiles(LinkedList list){

        // loop through given list
        // remove tile from board
        //
        for(LinkedList.Node temp = list.head.next; temp != null; temp = temp.next){
            grid[temp.x][temp.y].setData(null);
            grid[temp.x][temp.y].setOccupied(false);
        }




    }

    public void setPlayableRowNum(int playableRowNum) {
        this.playableRowNum = playableRowNum;
    }

    public void setPlayableColNum(int playableColNum) {
        this.playableColNum = playableColNum;
    }

    public void setFirstTile(boolean firstTile) {
        isFirstTile = firstTile;
    }

    @Override
    public String toString() {
        System.out.println("==================");
        for(int y=0; y< SIZE; y++){
            for(int x=0; x<SIZE; x++){
                if(grid[y][x].getData() == null){
                    if(y == playableRowNum || x == playableColNum){
                        System.out.print(" * ");
                    }
                    else{
                        System.out.print(" - ");
                    }
                }
                else{
                    System.out.print(String.format(" %s ", grid[y][x].getData().getLetter()));
                }

            }

            System.out.println("");
        }


        return "";
    }



}
