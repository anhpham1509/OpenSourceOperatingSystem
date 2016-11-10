package lab02;

/**
 * Created by DuyAnhPham on 03/11/2016.
 */
public class BetterDictionary {
    Node[] dictionary;
    private static int N;

    public BetterDictionary(int n) {
        N = nextPrime(n);
        dictionary = new Node[N];
    }

    Object get(String key) {
        // get idx
        int idx = getIdx(key);
        // get key from linked list
        if (dictionary[idx] != null) {
            if (dictionary[idx].getKey().equals(key)) {
                return dictionary[idx].getVal();
            } else {
                int tmpIdx;
                Node tmp;
                if (idx + 1 > N - 1) {
                    tmp = dictionary[0];
                    tmpIdx = 0;
                } else {
                    tmp = dictionary[idx + 1];
                    tmpIdx = idx + 1;
                }
                // for until null to get
                while (tmp != null) {
                    if (tmp.getKey().equals(key) && tmp.getIndex() == idx) {
                        return tmp.getVal();
                    }

                    if (tmpIdx == idx) {
                        break;
                    }

                    tmpIdx++;
                    if (tmpIdx > N - 1) {
                        tmpIdx = 0;
                    }
                    tmp = dictionary[tmpIdx];
                }
            }
        }
        return null;
    }

    int put(Object item, String key) {
        // get idx
        int idx = getIdx(key);
        // put into linkedlist
        if (dictionary[idx] != null) {
            // for until null to put or full --> return -1
            int tmpIdx;
            Node tmp;
            if (idx + 1 > N - 1) {
                tmp = dictionary[0];
                tmpIdx = 0;
            } else {
                tmp = dictionary[idx + 1];
                tmpIdx = idx + 1;
            }
            // for until null to put
            while (tmp != null) {
                if (tmpIdx == idx) {
                    return -1;
                }

                tmpIdx++;
                if (tmpIdx >= N) {
                    tmpIdx = 0;
                }
                tmp = dictionary[tmpIdx];
            }

            dictionary[tmpIdx] = new Node(key, item, idx);
            return 0;
        } else {
            dictionary[idx] = new Node(key, item, idx);
            return 0;
        }
    }

    int del(String key) {
        // get idx
        int idx = getIdx(key);
        // delete in linkedlist
        if (dictionary[idx] != null && dictionary[idx].getKey().equals(key)) {
            System.out.println("Hello");
            dictionary[idx] = null;
            // get the element back
            // for until null to move back
            int tmpIdx;
            Node tmp;
            if (idx + 1 > N - 1) {
                tmp = dictionary[0];
                tmpIdx = 0;
            } else {
                tmp = dictionary[idx + 1];
                tmpIdx = idx + 1;
            }
            if (tmp != null) {
                if (tmp.getIndex() == idx) {
                    return 0;
                } else {
                    Node[] items = new Node[N];
                    int i = 0;
                    while (tmp != null) {
                        if (tmpIdx == idx) {
                            break;
                        }

                        items[i] = dictionary[tmpIdx];
                        i++;
                        dictionary[tmpIdx] = null;

                        tmpIdx++;
                        if (tmpIdx > N - 1) {
                            tmpIdx = 0;
                        }
                        tmp = dictionary[tmpIdx];
                    }

                    for (int j = 0; j < i; j++) {
                        put(items[j].getVal(), items[j].getKey());
                    }
                }
            }
            return 0;
        } else {
            System.out.println("Hello2");
            // for until null to delete --> return -1
            int tmpIdx;
            Node tmp;
            if (idx + 1 >= N - 1) {
                tmp = dictionary[0];
                tmpIdx = 0;
            } else {
                tmp = dictionary[idx + 1];
                tmpIdx = idx + 1;
            }
            // for until null to delete
            while (tmp != null) {
                if (tmp.getKey().equals(key) && tmp.getIndex() == idx) {
                    dictionary[tmpIdx] = null;
                    // get the element back
                    int tmpIdx2;
                    Node tmp2;
                    if (idx + 1 > N - 1) {
                        tmp2 = dictionary[0];
                        tmpIdx2 = 0;
                    } else {
                        tmp2 = dictionary[idx + 1];
                        tmpIdx2 = idx + 1;
                    }
                    if (tmp2 != null) {
                        if (tmp2.getIndex() == idx) {
                            return 0;
                        } else {
                            Node[] items = new Node[N];
                            int i = 0;
                            while (tmp2 != null) {
                                if (tmpIdx2 == idx) {
                                    break;
                                }

                                items[i] = dictionary[tmpIdx2];
                                i++;
                                dictionary[tmpIdx2] = null;

                                tmpIdx2++;
                                if (tmpIdx2 > N - 1) {
                                    tmpIdx2 = 0;
                                }
                                tmp2 = dictionary[tmpIdx2];
                            }

                            for (int j = 0; j < i; j++) {
                                put(items[j].getVal(), items[j].getKey());
                            }
                        }
                    }
                    return 0;
                }
                if (tmpIdx == idx) {
                    return -1;
                }
                tmpIdx++;
                if (tmpIdx > N - 1) {
                    tmpIdx = 0;
                }
                tmp = dictionary[tmpIdx];
            }
            return -1;
        }
    }

    void printDictionary() {
        for (int i = 0; i < dictionary.length; i++) {
            if (dictionary[i] != null) {
                System.out.printf("[%2d]: %s\n", i, dictionary[i].getKey());
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

    private class Node {
        private String key;
        private Object val;
        private int index;

        public Node(String key, Object val, int index) {
            this.key = key;
            this.val = val;
            this.index = index;
        }

        public String getKey() {
            return key;
        }

        public Object getVal() {
            return val;
        }

        public int getIndex() {
            return index;
        }
    }
}

