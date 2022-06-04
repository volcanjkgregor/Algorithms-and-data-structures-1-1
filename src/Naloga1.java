import java.util.Scanner;

public class Naloga1 {

    public static void main (String [] args) throws CollectionException {

        Kalkulator calc = new Kalkulator();

        String vrstica, niz;
        Scanner sc_v, sc_n;
        sc_v = new Scanner(System.in);
        while(sc_v.hasNextLine()){
            vrstica = sc_v.nextLine();
            sc_n = new Scanner(vrstica);
            Kalkulator.funScan(sc_n);
            while(sc_n.hasNext()){
                String ukaz = sc_n.next();
                calc.procesiraj(ukaz);
            }
            calc.pocistiVse();
        }

    }
}

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}

interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int size();
    String toString();
}

class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private T[] a;
    private int front, back, size;

    public ArrayDeque() {
        a = (T[])(new Object[DEFAULT_CAPACITY]);
        front = 0;
        back = 0;
        size = 0;
    }

    private int next(int i) {
        return (i+1)%DEFAULT_CAPACITY;
    }

    private int prev(int i) {
        return (i-1)%DEFAULT_CAPACITY;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");

        if(size>0)
            sb.append(a[front].toString());

        for (int i = 0;i < size - 1;i++)
            sb.append("," + a[next(front+i)].toString());

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public boolean isFull() {
        return (size==DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }



    //stack
    @Override
    public T top() throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);

        return a[prev(back)];
    }

    @Override
    public void push(T x) throws CollectionException {
        if(isFull())
            throw new CollectionException(ERR_MSG_FULL);
        a[back]=x;
        back=next(back);
        size++;
    }

    @Override
    public T pop() throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        back=prev(back);
        T o=a[back];
        a[back]=null;
        size--;

        return o;
    }


    //deque
    @Override //dopisi_done
    public T front() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return a[front];
    }

    @Override
    public T back() throws CollectionException {
        return top();
    }

    @Override
    public void enqueue(T x) throws CollectionException {
        push(x);
    }

    @Override //dopisi_done mnda
    public void enqueueFront(T x) throws CollectionException {
        if (isFull())
            throw new CollectionException(ERR_MSG_FULL);

        if (front==0)
            front=DEFAULT_CAPACITY-1;
        else
            front=prev(front);
        size++;
        if (back==0)
            back=64;

        a[front]=x;
    }

    @Override //dopisi_done
    public T dequeue() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        T o=a[front];
        a[front]=null;
        front=next(front);

        size--;
        return o;
    }

    @Override
    public T dequeueBack() throws CollectionException {
        return pop();
    }


    //sequence
    private int index(int i) {
        return (front + i)%DEFAULT_CAPACITY;
    }

    @Override
    public T get(int i) throws CollectionException {
        if(isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if(i<0 || i>=size)
            throw new CollectionException(ERR_MSG_INDEX);

        return a[index(i)];
    }

    @Override
    public void add(T x) throws CollectionException {
        push(x);
    }

}

interface Stack<T> extends Collection {
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
}


interface Deque<T> extends Collection {
    T front() throws CollectionException;
    T back() throws CollectionException;
    void enqueue(T x) throws CollectionException;
    void enqueueFront(T x) throws CollectionException;
    T dequeue() throws CollectionException;
    T dequeueBack() throws CollectionException;
}


interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
}
