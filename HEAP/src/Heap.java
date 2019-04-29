import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {

    private ArrayList<E> items;


    public Heap(){

        items = new ArrayList<>();
    }



    private void siftUP(){

        int k = items.size() -1;//index of current element that we are gonna sift up

        while(k > 0){

            int p = (k-1)/2; // index of the parent

            E item = items.get(k);
            E parent = items.get(p);
            //if item is greater than the parent we swap 'em
            if(item.compareTo(parent) > 0){

                items.set(k, parent);
                items.set(p, item);

                // move current item to compare with next parent
                k = p;

            }else{
                break;
            }

        }


    }
    public void add(E key){

        items.add(key);
        siftUP();



    }

    private void siftDown(){
        int k = 0; // position of the last node on the right on the lowest branch of the tree
        int l = 2*k+1; //left child of the node that has been replaced

        while(l < items.size()){

            int max = l;
            int r = l+1; // right child of the node

            // it means that there is a right child
            if(r < items.size()) {
                //we determine here which child is greater and then swap that child with the parent which is the lowest
                //item added to the tree
                if(items.get(r).compareTo(items.get(l)) > 0){
                    max++;
                }

                if(items.get(k).compareTo(items.get(max)) < 0){
                    //swap

                    E temp = items.get(k);
                    items.set(k, items.get(max));
                    items.set(max, temp);
                    k = max;
                    l = 2*k+1;
                }else{
                    break;
                }
            }
        }




    }

    //always deletes item on the top of the tree
    public E delete() {

        if(items.size() == 0) return null;

        if(items.size() == 1) return items.remove(0);
        //we hold the value of the item we remove and then set the last added element into it's place
        E hold = items.get(0);
        items.set(0, items.remove(items.size() - 1));
        siftDown();

        return hold;
    }

    public int size(){
        return items.size();
    }



    @Override
    public String toString() {
        return items.toString();
    }


}
class Node<E extends Comparable<E>>{

    E key;

    Node leftChild;
    Node rightChild;


    public Node(E key){

        this.key = key;



    }


    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if(rightChild!=null) {
            rightChild.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(key.toString()).append("\n");
        if(leftChild!=null) {
            leftChild.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }


}



