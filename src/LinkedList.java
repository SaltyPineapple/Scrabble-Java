public class LinkedList {

    class Node {
        Tile data;
        int x;
        int y;
        Node next;
        Node prev;
        public Node(Tile data, int x, int y){
            this.data = data;
            this.x = x;
            this.y = y;
            next = null;
            prev = null;
        }
        public Node(){

        }

        Tile getData(){
            return data;
        }


    }

    Node head;


    public LinkedList(){
        head = new Node();
    }

    void insertLast(Tile data, int x, int y){
        Node newNode = new Node(data, x, y);
        if(getSize() == 0){
            head.next = newNode;
            newNode.prev = head;
        }
        else{
            Node current = new Node();
            current.next = head.next;
            while(current.next.next != null){
                current = current.next;
            }
            current.next.next = newNode;
            newNode.prev = current.next;
        }


    }

    // insert into the linked list in order
    //

    void insertOrder(Tile data, int x, int y){
        Node newNode = new Node(data, x, y);
        if(getSize() == 0){
            head.next = newNode;
            newNode.prev = head;
        }
        else {
            /*Node current = new Node();
            current.next = head.next;*/

            for(Node j = head; j.next !=null; j = j.next){
                if(x < j.next.x || y < j.next.y){
                    newNode.next = j.next;
                    newNode.prev = j.prev;
                    if(j == head){
                        head.next = newNode;
                    }
                    else{
                        j.prev.next = newNode;
                    }
                    return;
                }

            }
            insertLast(data, x, y);
        }
    }

    int getSize(){
        int size = 0;
        if(head.next == null){
            return 0;
        }
        else{
            Node current = new Node();
            current.next = head.next;
            while(current.next != null){
                size++;
                current = current.next;
            }
        }
        return size;
    }

    Node last(){
        Node current = new Node();
        current.next = head.next;
        while(current.next != null){

            current = current.next;
        }
        return current;
    }

    void clear(){
        head.next = null;
    }


    @Override
    public String toString(){
        String list = "head";
        for(Node x = head.next; x != null; x = x.next){
            list += " --> " + x.data.getLetter();
        }

        return list;
    }

    public String toWord(){
        StringBuilder word = new StringBuilder();
        for(Node x = head; x != null; x = x.next){
            if(x.data != null){
                word.append(x.data.getLetter());
            }
        }

        return word.toString();
    }





}
