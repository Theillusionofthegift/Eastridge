import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{

    private Item[] q;
    private int size;

    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        size = 0;
    }

    private class RQIterator implements Iterator<Item> {
        private Item[] copy = (Item[]) new Object[q.length];
        private int copySize = size;

        public RQIterator() {
            for (int i = 0; i < q.length; i++) {
                copy[i] = q[i];
            }
        }

        public boolean hasNext() {
            return copySize > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            int rd = (int) (Math.random() * (copySize -1));
            Item item = copy[rd];
            if (rd != copySize - 1)
                copy[rd] = copy[copySize - 1];
            copy[copySize - 1] = null;
            copySize--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == q.length) {
            resize(q.length * 2);
        }
        q[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int rd = (int) (Math.random() * (size -1));
        Item item = q[rd];
        if (rd != size-1)
        {
            q[rd] = q[size-1];
        }
        q[size-1] = null;
        size--;
        if (size > 0 && size == q.length/4) {
            resize(q.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }

        int rd = (int) (Math.random() * (size - 1));
        Item item = q[rd];
        return item;
    }

    public Iterator<Item> iterator()
    {
        return new RQIterator();
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
        {
            copy[i] = q[i];
        }
        q = copy;
    }
    // unit testing
    public static void main(String[] args)
    {

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        for (int i : rq)
        {
            System.out.println(i);
        }
    }
}

