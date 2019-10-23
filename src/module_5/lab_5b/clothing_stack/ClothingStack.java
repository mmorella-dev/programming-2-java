package module_5.lab_5b.clothing_stack;

/**
 * ClothingStack
 * @author Mae Morella
 */
public class ClothingStack implements Cloneable {
    private Clothing[] stack;
    private final int capacity;
    private int size = 0;
    private int pointer = -1; // The index of the current topmost element

    /**
     * Initialize a new stack of clothes
     * @param size The maximum number of clothes that can fit in the array
     * @param clothes An optional array of clothes to start the stack with
     */
    public ClothingStack(int capacity, Clothing ... clothes) {
        this.capacity = capacity;
        this.stack = new Clothing[capacity];
        for (Clothing c : clothes) {
            this.push(c);
        }
    }
    /**
     * Initialize a new stack of clothes with the default size of 20
     */
    public ClothingStack() {
        this(20);
    }

    /** @return the current number of Clothing items in the stack */
    public int count() {
        return pointer + 1;
    }
    /** @return the maximum number of Clothing items in the stack */
    public int capacity() {
        return capacity;
    }
    /** @return whether or not the stack is empty */
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Access the topmost article without removing it 
     * @return The topmost piece of Clothing
     */
    public Clothing peek() {
        if (isEmpty()) {
            return null;
        }
        return stack[pointer];
    }
    /**
     * Access the topmost article, and remove it from the array.
     * @return The topmost piece of clothing, or {@null if the stack is empty}
     */
    public Clothing pop() {
        if (isEmpty()) {
            return null;
        }
        // Return stack[pointer], then decrement
        size--;
        return stack[pointer--];

    }
    /**
     * Inserts an article of clothing into the stack
     * @param c The clothing to insert
     * @return {@code true} if the insertion is successful, 
     * {@code false} if the stack is full
     */
    public boolean push(Clothing c) {
        // If the stack is full, do nothing.
        if (count() == capacity()) {
            return false;
        }
        // Increment pointer, then insert c at stack[pointer]
        stack[++pointer] = c;
        size++;
        return true;
    }

    private ClothingStack(int capacity, Clothing[] stack, int pointer, int size) {
        this.capacity = capacity;
        this.stack = stack;
        this.pointer = pointer;
        this.size = size;
    }

    public ClothingStack clone() {
        return new ClothingStack(this.capacity(), this.stack, this.pointer, this.size);
    }
    /**
     * Returns all clothes that match a given color
     * @param color The color to check for (case insensitive)
     * @return A stack of clothes which all match that color
     */
    public ClothingStack matchesColor(String color) {
        ClothingStack newStack = new ClothingStack(this.capacity());
        for (int i = 0; i < count(); i++) {
            Clothing c = stack[i];
            if (c.getColor().toLowerCase().equals(color.toLowerCase())) {
                newStack.push(c);
            }
        }
        return newStack;
    }
    /**
     * Returns all clothes that match a given washability value
     * @param hightemp the kind of clothes to search for
     * @return A stack of clothes
     */
    public ClothingStack matchesWashable(boolean hightemp) {
        ClothingStack newStack = new ClothingStack(this.capacity());
        for (int i = 0; i < count(); i++) {
            Clothing c = stack[i];
            if (c.isHighTempWashable() == hightemp) {
                newStack.push(c);
            }
        }
        return newStack;
    }

    public void display() {
        for (int i = 0; i < count(); i++) {
            Clothing c = stack[i];
            System.out.println("#" + (i + 1) + ": " + c.toSimpleString());
        }
    }
}