package CS102_Sec3_Asgn6_Ozdemir_Erkam.Data_Structures;

import java.util.EmptyStackException;

public class RepeatStack {
    private class RepeatNode {
        int startIp;
        int count;
        RepeatNode next;

        RepeatNode(int startIp, int count) {
            this.startIp = startIp;
            this.count = count;
        }
    }

    private RepeatNode top;
    private int size;

    public RepeatStack() {
        top = null;
        size = 0;
    }

    public void push(int startIp, int count) {
        RepeatNode newNode = new RepeatNode(startIp, count);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public boolean decrementAndCheck() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        top.count--;
        if (top.count > 0) {
            return true;
        } else {
            pop();
            return false;
        }
    }

    public int peekStartIp() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.startIp;
    }

    public void pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        top = top.next;
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
