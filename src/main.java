public class main {
    public static void main(String[] args){
        for(int col=0; col<8; col++){
            for(int row=0; row<8; row++){
                System.out.print(String.format("| %d , %d |", row, col));
            }
            System.out.println();
        }


    }
}
