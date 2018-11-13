public class DHArrayList<E>{
    private int size;
    private int capacity;
    private E[] myArray;
    private static final int INITIAL_CAPACITY = 10;

    public DHArrayList()
    {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        myArray = (E[])new Object[this.capacity];

    }

    public DHArrayList(int capacity)
    {
        this.capacity = capacity;
        this.size = 0;
        myArray = (E[])new Object[this.capacity];
    }

    public void add(E a)   //This method element at the end of the list
    {
        if(size< capacity)//This means if there is space
        {
            myArray[size] = a;
            size++;
        }
        else
        {
            System.out.println("There isn't enough space");
            System.out.println("Calling reallocate() method");
            this.reallocate();// Call reallocate and increase capacity
            this.add(a);//Once there is more space
            // we can call this method again
        }
    }


    private void reallocate()
    {
        this.capacity *=2;
        E[] temp = (E[]) new Object[this.capacity];

        for(int i =0; i<myArray.length;i++)
        {
            temp[i] = myArray[i];
        }

        this.myArray = temp;
    }


    public void add(E a, int index)//Overload version, adds at index
    {
        //this add method adds the data tat specified index
        if (index < 0 || index >size)
        {
            System.out.println("Invalid Index");
        }
        else if(index==size)
        {
            //this means we are inserting at the end of the list
            // So, just use the other method already implements
            this.add(a);
        }
        else
        {
            //we  have to shift some elements to the right
            //make sure there is space
            if(this.capacity==this.size)
            {
                this.reallocate();
            }
            //move the data
            for(int i = size; i>index; i--)
            {
                this.myArray[i] = this.myArray[i-1];
            }
            //once shifting is done insert the data at index
            this.myArray[index] = a;
            size++;
        }


    }

    public void remove(int index)//this method is going to delete and return the deleted value at index
    {
        if(index<0 || index> size)
        {
            System.out.println("Invalid Index! Cannot get the data");
        }

        //Store deleted data in a variable so that it can be returned
        E temp = this.myArray[index];
        //use a loop to shift the elements one place left after index
        for(int i=index;i<size;i++)
        {
            this.myArray[i] = this.myArray[i+1];


        }
        size--;
    }

    public E get(int index)
    {
        if(index<0 || index>= size)
        {
            System.out.println("Invalid Index! No item found");
        }
        return this.myArray[index];
    }

    public void set(E a, int index)
    {
        if(index < 0 || index>= size)
        {
            System.out.println("Invalid Index. Can not place anything there");
            return;
        }
        this.myArray[index]=a;//Else, update the value
    }


    public int getSize()
    {
        return size;
    }//Total number of elements


    public int indexOf(E a)
    {
        for(int i=0;i<size;i++)
        {
            if(myArray[i].equals(a))
            {
                return i;
            }
        }
        return -1;
    }

    public String toString()
    {
        System.out.print("");
        String S = "";
        //We populate String S, with the data elements of the list
        //Iterate over the list, and add th elemetns to the string
        for(int i =0;i<size;i++)
        {
            S+= myArray[i];
        }
        return S;
    }
}
