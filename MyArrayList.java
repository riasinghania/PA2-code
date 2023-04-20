/*
 * Name: [Ria Singhania]
 * Email: risinghania@ucsd.edu
 * PID: A17331656
 * Sources Used: write-up, 
 */
public class MyArrayList<E> implements MyList<E>{
    Object[] data;
    int size;

    public MyArrayList(){
        data = new Object[5];
    }

    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
        }
        data = new Object[initialCapacity];
    }

    public MyArrayList(E[] arr) {
        if (arr == null) {
            // fall back to default constructor behavior
            data = new Object[5];
        }
        else{
            data = new Object[arr.length];
            size = arr.length;
            // shallow copy of the input array
            for(int i=0; i < size; i++){
                data[i] = arr[i];
            }
        }
    }


    /** 
     * Increase capacity by three if non-zero, and 
     * If capacity is zero make it default (5). 
     * If capcity if not enough set to requiredCapacity. 
     * Perserve indices.
     * @param requiredCapacity
    */
    public void expandCapacity(int requiredCapacity) {
        if (requiredCapacity < this.data.length) {
            throw new IllegalArgumentException("requiredCapacity must be greater than or equal to the current capacity");
        }
        Object[] newData;
        if (this.data.length == 0) {
            // capacity is 0, set to default capacity
            newData = new Object[5];
        } else{
            // capacity is not enough, increase by 3 or set to required capacity
            int newCapacity = this.data.length + 3;
            newData = new Object[newCapacity];
        }
        if(newData.length < requiredCapacity){
            newData = new Object[requiredCapacity];
        }
        
        //perserve indices of old array
        for(int i =0; i< this.size; i++){
            newData[i] = this.data[i];
        }
        this.data = newData;
    }

    /**
     * Get the number of elements that the underlying array can possibly hold
     * @return lengeth of data
     */
    public int getCapacity() {
        return this.data.length;
    }

    /**
     * Insert an element at the specified index. 
     * If the array is at capacity before insertion, update the capacity according to expandCapacity()'s rules.
     * @param specified index 
     * @param specified element to be inserted
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (this.size == data.length) {
            this.expandCapacity(this.size + 1);
        }
        
        for(int i = this.size; i >= index; i--){ 
            if(i == index){
                this.data[i] = element;
            }
            else{
                this.data[i] = this.data[i-1];
            }
        }
        this.size++;
    }

    /** 
     * Add an element at the end of the list. 
     * If the array is at full capacity, update the capacity according to expandCapacity()'s rules. 
     * @param element to be added
     */
    @Override
    public void append(E element) {
        if (this.size == data.length) {
            this.expandCapacity(this.size + 1);
        }
        this.data[this.size] = element;
        this.size++;
    }
    
    /**
     * Add an element at the beginning of the list. 
     * If the array is at capacity, update the capacity according to expandCapacity()'s rules.
     * @param element to be added
     */
    @Override
    public void prepend(E element) {
        this.insert(0, element);
    }

    /**
     * Get an element at the specified index. 
     * @param specified index
     * @return element at specified index 
    */
    public E get(int index) throws IndexOutOfBoundsException  {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return (E)this.data[index];
    }

   /**
    * Set the given element at the specified 
    * @param specified index
    * @param element to override the element at specified index
    * @return overwritten element.
    */
    @Override
    public E set(int index, E element)throws IndexOutOfBoundsException  {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of range: " + index);
        }
        E oldElement = this.get(index);
        data[index] = element;
        return oldElement;
    }
    
    /**
     * Remove and return the element at the specified index.
     * @param index that element should be removed at
     * @return removed element  
     */
    public E remove(int index)throws IndexOutOfBoundsException  {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of range: " + index);
        }
        E removedElement = this.get(index);
        for(int i = index; i < this.size-1; i++){ 
            this.data[i] = this.data[i+1];
        }
        this.data[--this.size] = null;
        return removedElement;
    }
    /**
     * Return the number of elements that exist in the ArrayList 
     * @return number of elements in ArrayList
     */
    public int size() {
        return this.size;
    }
}
    



