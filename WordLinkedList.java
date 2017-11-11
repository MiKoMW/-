/**
 * Created by Mac on 2017/8/2.
 */
public class WordLinkedList {
    class Node{ //internal node
        public char element;
        public Node next;

        public Node(char item) {
            this.element = item;
            next = null;
        }
    }

    private Node root; //first node

    public WordLinkedList(){
        root = null;
    }

    public void add(String key){
        if (root == null){
            root = new Node(key.charAt(0));

        }
        Node parNode = root;
        for (int i = 1; i < key.length(); i++){
            char c = key.charAt(i);
            Node currNode = new Node(c);
            parNode.next = currNode;
            parNode = currNode;
        }
    }

    public boolean find(String input){
        Node curNode = root;
        int i = 0;
        while (curNode != null && i < input.length()){
            if(curNode.element!= input.charAt(i)){
                return false;
            }
            curNode = curNode.next;
            i++;
        }
        return true;
    }

    public String toString(){
        StringBuffer buf = new StringBuffer();
        Node current = root;

        while (current != null){
            buf.append(current.element);
            buf.append('\t');
            current = current.next;
        }

        return buf.toString();
    }





    public static void main(String[] args){

        WordLinkedList c = new WordLinkedList();
        c.add("Miko是我老婆！");
        boolean ans = false;
        ans = c.find("");
        System.out.println(ans);

        System.out.println(c.toString());

    }




}



