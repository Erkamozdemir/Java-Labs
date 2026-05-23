public class LinkedList {

    private Node first;
    private Node last;
    private int length;

    public LinkedList() {
        this.first = null;
        this.last = null;
        this.length = 0;
    }

    public LinkedList(LinkedList other) {
        for (int i = 0; i < other.size(); i++) {
            this.add(other.get(i).data);
        }
    }

    public void add(int item) {
        if (this.length == 0) {
            this.first = new Node(item, null, null);
            this.last = this.first;
        } else {
            this.last.next = new Node(item, null, this.last);
            this.last = this.last.next;
        }
        this.length++;
    }

    public void add(int index, int item) {
        if (index > this.length) {
            throw new IndexOutOfBoundsException();
        }
        if (index == this.length) {
            this.add(item);
            return;
        }
        if (index == 0) {
            this.first = new Node(item, this.first, null);
            this.first.next.previous = this.first;
            this.length++;
            return;
        }
        this.get(index).previous.next = new Node(item, this.get(index), this.get(index).previous);
        this.get(index).next.previous = this.get(index);
        this.length++;
    }

    public void remove(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            this.first = this.first.next;
            if (this.first != null) {
                this.first.previous = null;
            } else {
                this.last = null;
            }
            this.length--;
            return;
        }
        if (index == this.length - 1) {
            this.last = this.last.previous;
            this.last.next = null;
            this.length--;
            return;
        }
        this.get(index).previous.next = this.get(index).next;
        this.get(index).previous = this.get(index).previous.previous;
        this.length--;
    }

    public Node get(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        Node returningNode = this.first;
        for (int i = 0; i < index; i++) {
            returningNode = returningNode.next;
        }
        return returningNode;
    }

    public int getValue(int index) {
        return this.get(index).data;
    }

    public Node getFromLast(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        Node returningNode = this.last;
        for (int i = this.size(); i > index; i--) {
            returningNode = returningNode.previous;
        }
        return returningNode;
    }

    public int size() {
        return this.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node currentNode = this.first;
        for (int i = 0; i < this.length; i++) {
            sb.append(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        return sb.toString();
    }

    public boolean equals(Object otherList) {
        if (this == otherList)
            return true;
        if (!(otherList instanceof LinkedList))
            return false;
        LinkedList other = (LinkedList) otherList;
        if (this.length != other.length)
            return false;
        if (this.toString().equals(other.toString()))
            return true;
        return false;
    }

    private static class Node {
        final int data;
        Node next;
        Node previous;

        Node(int data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }

    public void swapNodes(int index1, int index2) {
        if (index1 == index2 || index1 < 0 || index2 < 0 || index1 >= this.length || index2 >= this.length) {
            return;
        }

        if (index1 > index2) {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }

        Node node1 = this.get(index1);
        Node node2 = this.get(index2);
        Node before1 = node1.previous;
        Node after1 = node1.next;
        Node before2 = node2.previous;
        Node after2 = node2.next;

        if (node1.next == node2) {
            node2.previous = before1;
            node2.next = node1;
            node1.previous = node2;
            node1.next = after2;
            if (before1 != null) {
                before1.next = node2;
            } else {
                this.first = node2;
            }
            if (after2 != null) {
                after2.previous = node1;
            } else {
                this.last = node1;
            }
        } else {
            node2.previous = before1;
            node2.next = after1;
            if (before1 != null) {
                before1.next = node2;
            } else {
                this.first = node2;
            }
            if (after1 != null)
                after1.previous = node2;
            node1.previous = before2;
            node1.next = after2;
            if (before2 != null)
                before2.next = node1;
            if (after2 != null)
                after2.previous = node1;
            else
                this.last = node1;
        }
    }

    public void insertionSort() {
        for (int i = 1; i < this.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (this.get(j - 1).data > this.get(j).data) {
                    this.swapNodes(j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    public void mergeSort() {
        mergeSort(0, this.length - 1);
    }

    private void mergeSort(int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(start, mid);
        mergeSort(mid + 1, end);
        merge(start, mid, end);
    }

    private void merge(int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (this.get(i).data <= this.get(j).data) {
                i++;
            } else {
                for (int k = j; k > i; k--) {
                    this.swapNodes(k, k - 1);
                }
                i++;
                mid++;
                j++;
            }
        }
    }

    public void quickSort() {
        quickSort(0, this.length - 1);
    }

    public void quickSort(int lowest, int highest) {
        if (lowest < highest) {
            int index = partition(lowest, highest);
            quickSort(lowest, index);
            quickSort(index + 1, highest);
        }
    }

    public int partition(int lowest, int highest) {
        int pivot = this.get(lowest + (highest - lowest) / 2).data;
        while (true) {
            while (pivot > this.get(lowest).data) {
                lowest++;
            }
            while (pivot < this.get(highest).data) {
                highest--;
            }
            if (lowest >= highest) {
                return highest;
            }
            swapNodes(lowest, highest);
            lowest++;
            highest--;
        }
    }

    public void threeWayQuickSort() {
        if (this.length <= 1) {
            return;
        }
        threeWayQuickSort(0, this.length - 1);
    }

    private void threeWayQuickSort(int lowest, int highest) {
        if (lowest >= highest) {
            return;
        }

        int lowerBound = lowest;
        int current = lowest;
        int upperBound = highest;
        int pivot = this.get(lowest + (highest - lowest) / 2).data;

        while (current <= upperBound) {
            int currentData = this.get(current).data;
            if (currentData < pivot) {
                this.swapNodes(lowerBound, current);
                lowerBound++;
                current++;
            } else if (currentData > pivot) {
                this.swapNodes(current, upperBound);
                upperBound--;
            } else {
                current++;
            }
        }

        threeWayQuickSort(lowest, lowerBound - 1);
        threeWayQuickSort(upperBound + 1, highest);
    }

}
