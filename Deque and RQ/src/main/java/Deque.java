import java.util.Iterator;
public class Deque<Item> implements Iterable<Item>
{
    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    private Node first = null;
    private Node last = null;
    private int size = 0;

    // construct an empty deque
    public Deque()
    {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return (first == null || last == null);
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null");
        }

        Node placeholder = first;
        first = new Node();
        first.item = item;
        first.next = placeholder;
        first.prev = null;

        if (isEmpty()) {
            last = first;
        } else if (placeholder != null) {
            placeholder.prev = first;
        }

        size++;

        if (size == 1)
        {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException("null");
        }

        Node placeholder = last;
        last = new Node();
        last.item = item;
        last.prev = placeholder;
        last.next = null;


        if (isEmpty()) {
            first = last;
        } else if (placeholder != null) {
            placeholder.next = last;
        }

        size++;

        if (size == 1) {
            first = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }

        Item i = first.item;
        first = first.next;
        if (first != null)
        {
            first.prev = null;
        }

        if (isEmpty())
        {
            last = null;
            first = null;
        }

        size--;
        return i;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }

        Item i = last.item;
        last = last.prev;
        if (last != null)
        {
            last.next = null;
        }

        if (isEmpty())
        {
            last = null;
            first = null;
        }

        size--;
        return i;
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext()
        {
            return current != null;
        }

        public Item next()
        {

            if (!hasNext())
            {
                throw new java.util.NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> deck = new Deque<Integer>();

        for (int i : deck)
        {
            System.out.println(i);
        }
    }

}