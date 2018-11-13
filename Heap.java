public class Heap<E>
{
    protected DHArrayList myList;

    public Heap()
    {
        myList = new DHArrayList(15);
    }

    public void insert(E item,int priority,int time)                    //adds a node to the array list and sorts it
    {
        Node tempNode = new Node(priority,item,time);
         if(myList.getSize()== 0)
         {
             myList.add(tempNode);
         }
         else
         {
             myList.add(tempNode);
             sortHeapBottomTop();
         }
    }

    public void delete()                                                    //deletes an first item on list and re adjusts array list to keep it sorted
    {
        if(myList.getSize()==0)
        {
            //System.out.println("There Heap is empty");
        }
        else
        {
            Node patientCall =(Node)myList.get(0);                          //Get top root for printing
            Node child = (Node)myList.get(myList.getSize()-1);              //Takes last element and switches with top root
            myList.set(child,0);
            myList.remove(myList.getSize()-1);
            sortHeapTopBottom();
            System.out.println("----------Patient has been admitted----------");
            System.out.println(patientCall.item);
        }
    }

    public boolean isEmpty()                            //checks if array list is empty
    {
        if(myList.getSize() == 0)
            return true;
        else
            return false;
    }

    public void sortHeapTopBottom()                     //sorts an item bases on priority by swapping the parent downward until in correct position
    {
        int childLIndex = 1;
        int childRIndex = 2;
        int parentIndex = 0;

        if(myList.getSize()==2)
        {
            Node childL = (Node)myList.get(1);
            Node parent = (Node)myList.get(0);
            if(childL.priority<parent.priority)
            {
                myList.set(childL,parentIndex);
                myList.set(parent,childLIndex);
            }
        }
        else if(myList.getSize()>2)
        {
            Node childL = (Node)myList.get(1);
            Node childR = (Node)myList.get(2);
            Node parent = (Node)myList.get(0);

            //Repeats swapping until node is in proper location
            while(childLIndex<=myList.getSize()-1 || childRIndex <=myList.getSize()-1)
            {
                if(childLIndex<=myList.getSize()-1 && childRIndex<=myList.getSize()-1)                    // if both leaves exist
                {
                    parent = (Node)myList.get(parentIndex);
                    childL = (Node)myList.get(childLIndex);
                    childR = (Node)myList.get(childRIndex);
                    if (childL.priority < childR.priority)
                    {
                        myList.set(childL, parentIndex);
                        myList.set(parent, childLIndex);
                                                                                                    //Updates location of where the root node is located at according to child L
                        parentIndex = childLIndex;
                        childLIndex = parentIndex * 2 + 1;
                        childRIndex = parentIndex * 2 + 2;

                    }
                    else if (childL.priority > childR.priority)
                    {
                        myList.set(childR, parentIndex);
                        myList.set(parent, childRIndex);
                                                                                                //Updates location of where the root node is located at according to child R
                        parentIndex = childRIndex;
                        childLIndex = parentIndex * 2 + 1;
                        childRIndex = parentIndex * 2 + 1;
                    }
                    else if( childL.priority == childR.priority)                                //If priority is same first come first serve
                    {

                        if(childL.time < childR.time)
                        {
                            myList.set(childL, parentIndex);
                            myList.set(parent, childLIndex);
                            parentIndex = childLIndex;
                            childLIndex = parentIndex * 2 + 1;
                            childRIndex = parentIndex * 2 + 2;

                        }
                        else
                        {
                            myList.set(childR, parentIndex);
                            myList.set(parent, childRIndex);
                            parentIndex = childRIndex;
                            childLIndex = parentIndex * 2 + 1;
                            childRIndex = parentIndex * 2 + 1;

                        }
                    }
                }
                if(childLIndex<=myList.getSize()-1)                                                                 //if only left child exists
                {
                    if (childRIndex > myList.getSize() - 1)
                    {
                        parent = (Node) myList.get(parentIndex);
                        childL = (Node) myList.get(childLIndex);
                        if (childL.priority < parent.priority)
                        {
                            myList.set(childL, parentIndex);
                            myList.set(parent, childLIndex);
                            parentIndex = childLIndex;
                            childLIndex = parentIndex * 2 + 1;
                        }
                        else
                        {
                            parentIndex = childLIndex;
                            childLIndex = parentIndex * 2 + 1;
                        }
                    }
                }
            }
        }
    }

    public void sortHeapBottomTop()                                      // Sort Heap organizes the last Node entered ensuring its in its proper place before entering another node
    {

        Node child = (Node)myList.get(myList.getSize()-1);
        Node parent = (Node)(myList.get((myList.getSize()-1)/2));
        int childIndex = myList.getSize()-1;
        int parentIndex = (myList.getSize()-1)/2;

        while(child.priority < parent.priority)
        {
            myList.set(child,parentIndex);
            myList.set(parent,childIndex);

            if(parentIndex==0)                                  //Once we find the top of heap then we exit
                break;
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;

            child =(Node) myList.get(childIndex);
            parent = (Node) myList.get(parentIndex);
        }
    }

    public void age()                                                       //if there are items in the array while another item is popped off we adjust every items priority by 1
    {
        for(int i=0; i<myList.getSize();i++)
        {
            Node temp = (Node)myList.get(i);
            if(temp.priority >0)
                temp.priority= temp.priority -1;
        }
    }

    public static class Node
    {
        protected int priority;
        protected Object item;
        protected int time;

        public Node(int priority, Object item,int time)
        {
            this.priority=priority;
            this.item=item;
            this.time=time;
        }

        public String toString()
        {
            System.out.println(item);
            return "";
        }
    }
}
