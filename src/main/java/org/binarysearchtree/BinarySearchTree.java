package org.binarysearchtree;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {

        private Key key;
        private Value val;
        private Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public Value get(Key key) {
            Node x = root;
            while(x != null) {
                int compare = key.compareTo(x.key);
                if(compare < 0) {
                    x = x.left;
                } else if(compare > 0) {
                    x = x.right;
                } else {
                    return x.val;
                }
            }
            return null;
        }

        public void put(Key key, Value val) {
            root = put(root, key, val);
        }

        private Node put(Node x, Key key, Value val) {
            if(x == null) {
                return new Node(key, val);
            }
            int compare = key.compareTo(x.key);
            if(compare < 0) {
                x.left = put(x.left, key, val);
            } else if(compare > 0) {
                x.right = put(x.right, key, val);
            } else {
                x.val = val;
            }
            return x;
        }
    }
}
