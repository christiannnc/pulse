package parser;

import tokens.Token;

public class LinkedList {
    public Node head;

    public class Node {
        private Token value;
        private Node next;

        public Node(Token value) {
            this.value = value;
            this.next = null;
        }

        public Token getNodeValue() {
            return this.value;
        }

        public Node getNext() {
            return this.next;
        }
    }
}
