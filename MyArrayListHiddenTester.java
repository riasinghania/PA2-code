/*
 * Name: [Ria Singhania]
 * Email: risinghania@ucsd.edu
 * PID: A17331656
 * Sources Used: write-up, 
 */
import static org.junit.Assert.*;
import org.junit.*;

public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */
    static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 3;
    static final int LARGE_CAPACITY = 10;

    Object[] arr = new Object[10];
    Integer[] arrInts = { 1, 2, 3 };
    Integer[] arr2 = {1, 2, null, 4}; // 

    private MyArrayList listEmpty, listArr2, listDefaultCap, 
            listCustomCapacity, listWithNull, listWithInt, list;
    @Before
    public void setUp() throws Exception {
        listEmpty = new MyArrayList();
        listArr2 = new MyArrayList<Integer>(arr2);
        //listArr2.size = 1;
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listCustomCapacity = new MyArrayList(MY_CAPACITY);
        listWithNull = new MyArrayList(arr);
        listWithInt = new MyArrayList<Integer>(arrInts);
        list = new MyArrayList(LARGE_CAPACITY); 
    }
    
    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){
        assertThrows​(IllegalArgumentException.class, () -> new MyArrayList<>(-1));
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        Integer[] arr = null;
        listEmpty = new MyArrayList(arr);
        assertEquals(0, listEmpty.size);
        assertEquals(5, listEmpty.getCapacity());
        //assertNull(arr);
        //MyArrayList<Integer> list = new MyArrayList<>(arr);
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
        assertEquals(1, listArr2.get(0));
        assertEquals(2, listArr2.get(1));
        assertNull(listArr2.get(2));
        assertEquals(4, listArr2.get(3));
    
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        //append full list
        listWithInt.append(4);
        //checking the size and capacity
        assertEquals(4, listWithInt.size);
        assertEquals(6, listWithInt.getCapacity());
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        listCustomCapacity.append("one");
        listCustomCapacity.append("two");

        // Verify that the size and capacity fields are correct before appending null
        assertEquals(2, listCustomCapacity.size);
        assertEquals(3, listCustomCapacity.getCapacity());

        // Append null to the full list
        listCustomCapacity.append(null);

        // Verify that the size and capacity fields are updated correctly
        assertEquals(3, listCustomCapacity.size);
        assertEquals(3, listCustomCapacity.getCapacity());
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        //prepend to a list that is at capacity 
        listWithInt.prepend(0);
        //testing whether size and capacity updated right
        assertEquals(4, listWithInt.size);
        assertEquals(6, listWithInt.getCapacity());
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
         // add two elements to the list
         listCustomCapacity.append("foo");
         listCustomCapacity.append("bar");
         
         // verify that the list has size 2 and capacity 3
         assertEquals(2, listCustomCapacity.size);
         assertEquals(3, listCustomCapacity.getCapacity());
         
         // prepend a null element to the list
         listCustomCapacity.prepend(null);
         
         // verify that the size and capacity of the list have increased
         assertEquals(3, listCustomCapacity.size);
         assertEquals(3, listCustomCapacity.getCapacity());
         
         // verify that the null element was successfully prepended
         assertNull(listCustomCapacity.get(0));
         assertEquals("foo", listCustomCapacity.get(1));
         assertEquals("bar", listCustomCapacity.get(2));
        
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBounds(){ 
        assertThrows​(IndexOutOfBoundsException.class, () -> listWithInt.insert(4, 4));  
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        // Insert more than initialCapacity elements sequentially
        int numElements = 1000;
        for (int i = 0; i < numElements; i++) {
            listCustomCapacity.insert(i, i);
        }

        // Verify that the size and capacity fields are updated correctly
        assertEquals(numElements,listCustomCapacity.size);
        assertTrue(listCustomCapacity.getCapacity() >= numElements);

        // Verify that all the elements were inserted correctly
        for (int i = 0; i < numElements; i++) {
            assertEquals(Integer.valueOf(i), listCustomCapacity.get(i));
        }
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        assertThrows​(IndexOutOfBoundsException.class, () -> listWithInt.get(4));
        //throw exception
        
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
        assertThrows​(IndexOutOfBoundsException.class, () -> listWithInt.set(4, 4));
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        // add three elements to the list
        listCustomCapacity.append("foo");
        listCustomCapacity.append("bar");
        listCustomCapacity.append("baz");
        // remove two elements from the list
        listCustomCapacity.remove(1);
        listCustomCapacity.remove(0);
        // verify that the remaining element is "baz"
        assertEquals("baz", listCustomCapacity.get(0)); 
          
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        assertThrows​(IndexOutOfBoundsException.class, () -> listWithInt.remove(4));       
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
         assertThrows​(IllegalArgumentException.class, () -> listArr2.expandCapacity(1));
        //throw excpetion 
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        // add 8 elements to the list
        for (int i = 0; i < 8; i++) {
            list.append("element" + i);
        }
        
        // verify that the list has size 8 and capacity 10
        assertEquals(8, list.size);
        assertEquals(10, list.getCapacity());
        
        // attempt to expand the capacity to 15 (which is greater than the current capacity + 3)
        list.expandCapacity(15);
        
        // verify that the list now has capacity 15
        assertEquals(15, list.getCapacity());
    }
    

}
