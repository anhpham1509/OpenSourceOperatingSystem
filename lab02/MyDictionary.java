package lab02;

/**
 * Created by DuyAnhPham on 03/11/2016.
 */
public class MyDictionary {
    MyLinkedList<Object>[] dictionary;
    private static int N;

    public MyDictionary(int n) {
        N = nextPrime(n);
        dictionary = new MyLinkedList[N];
    }

    Object get(String key) {
        // get idx
        int idx = getIdx(key);
        // get key from linked list
        if (dictionary[idx] != null) {
            return dictionary[idx].get(key);
        }
        return null;
    }

    int put(Object item, String key) {
        // get idx
        int idx = getIdx(key);
        // put into linkedlist
        if (dictionary[idx] != null) {
            return dictionary[idx].push(key, item);
        } else {
            MyLinkedList<Object> list = new MyLinkedList<Object>();
            list.push(key, item);
            dictionary[idx] = list;
            return 0;
        }

    }

    int del(String key) {
        // get idx
        int idx = getIdx(key);
        // delete in linkedlist
        if (dictionary[idx] != null) {
            return dictionary[idx].delete(key);
        } else {
            return -1;
        }
    }

    void printDictionary() {
        for (int i = 0; i < dictionary.length; i++) {
            if (dictionary[i] != null) {
                System.out.printf("[%2d]: %s\n", i, dictionary[i].toString());
            } else {
                System.out.printf("[%2d]: [empty]\n", i);
            }
        }
    }

    private int getIdx(String key) {
        // convert key to hashCode
        int hashCode = hash(key);
        // convert hashCode to index
        return hashCode % N;
    }

    /* K & R hash function for strings */
    private static final int MULTIPLIER = 31;

    private int hash(String str) {
        int h;
        h = 0;
        for (int i = 0; i < str.length(); i++)
            h = MULTIPLIER * h + str.charAt(i);
        return h & Integer.MAX_VALUE;
    }

    /* find next prime number */
    private int nextPrime(int n) {
        int prime = 0, i, nextPrime;

        /* check first if this is a prime number */
        for (i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                prime = 1;
                break;
            }
        }
        if (prime == 1) {
            /* no, try to find next one */
            nextPrime = n;
            prime = 1;
            while (prime != 0) {
                nextPrime++;
                prime = 0;
                for (i = 2; i < nextPrime / 2; i++) {
                    if (nextPrime % i == 0) {
                        prime = 1;
                        break;
                    }
                }
            }
            return (nextPrime);
        } else
            /* yes, return this as is */
            return (n);
    }

    public class MyLinkedList<T> {
        private Node first;

        public MyLinkedList() {
            first = null;
        }

        public int push(String key, T val) {
            if (first != null) {
                Node prev = first;

                while (prev.next != null)
                    prev = prev.next;

                prev.next = new Node(key, val, null);

            } else {
                first = new Node(key, val, null);
            }
            return 0;
        }

        public int delete(String key) {
            if (first != null) {
                if (first.key.equals(key)) {
                    if (first.next != null) {
                        first = first.next;
                        return 0;
                    } else {
                        first = null;
                        return 0;
                    }
                }

                Node tmp = first;

                while (tmp.next != null) {
                    Node tmp2 = tmp.next;

                    if (tmp2.key.equals(key)) {
                        tmp.next = tmp2.next;
                        return 0;
                    }

                    if (tmp2.next == null) {
                        break;
                    }

                    tmp = tmp.next;
                }
            }

            return -1;
        }

        public T get(String key) {
            Node tmp = first;

            while (tmp != null) {
                if (tmp.key.equals(key)) {
                    return tmp.val;
                }
                tmp = tmp.next;
            }

            return null;
        }

        private class Node {
            private String key;
            private T val;
            private Node next;

            public Node(String key, T val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            Node p = first;
            while (p != null) {
                s.append(p.key);
                if (p.next != null) {
                    s.append(", ");
                }
                p = p.next;
            }

            return s.toString();
        }
    }
}

