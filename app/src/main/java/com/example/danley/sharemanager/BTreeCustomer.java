package com.example.danley.sharemanager;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    Customer mCustomer;
    TreeNode left, right;


    // Constructor assigns a new word, and assignsit to word while making word_count be 1 and making sure left and right are null
    public TreeNode(Customer customer) {
        mCustomer = customer;
        left= null;
        right = null;

    }
}

public class BTreeCustomer {
    TreeNode root;
    private List<String> mCustomersID = null;

    public BTreeCustomer() { // root a node, which is NULL
        root = null;
        mCustomersID = new ArrayList<String>();
    }


    // this is a bit interesting
    public void insert(TreeNode node, Customer newCustomer) {
        int compare = node.mCustomer.getID().toString().compareTo(newCustomer.getID().toString());

        if (compare == 0) { // This means that this is already in here, no need to add
            return; // we just add a word count, and that's IT, to confirm we've found the same word
        } else if (compare > 0) { // If our node is a bigger word or greater than
            if (node.left != null) // If left isn't null
                insert (node.left, newCustomer); // RECURSION TIME, ON TO THE LEFT MI AMIGO
            else
                node.left = new TreeNode(newCustomer); // If it is, we just make a new child node for node
        }
        else if (compare < 0) // Same thing, but different side
        {
            if (node.right != null)
                insert(node.right, newCustomer);
            else
                node.right = new TreeNode(newCustomer);
        }
    }

    public void insertCustomer(Customer customer) // insert_word
    {
        if (root == null) // If it's null,
            root = new TreeNode(customer); // new root!
        else // if it anit
            insert (root, customer); // let's go on an adventure

    }

    private TreeNode find (TreeNode node, String ID)
    {
        int compare = node.mCustomer.getID().toString().compareTo(ID.toString()); // We're finding,so let's compare

        if (compare == 0) // If it's 0, aka we found our word, so...
            return node; // let's return a TreeNode
        else if (compare > 0) // If it's greater,
        {
            if (node.left != null) // let's send it right.. but is node.left a null?
                return find(node.left, ID); // if not, let's check it
            else
                return null; // it isn't here
        }
        else {
            if (node.right != null)
                return find(node.right, ID);
            else
                return null;
        }
    }

    public Customer find (String ID) {
        if (root != null) {
            TreeNode node = find(root, ID);
            if (node != null)
                return node.mCustomer;
            else
                return null;
        }
        return null;
    }

    public int size() {
        return size(root);
    }

    private int size(TreeNode node) {
        if (node == null)
            return 0;
        else
            return(size(node.left) + size(node.right) + 1 );
    }


    //This function is a bad mistake, but it works for Holder...
    public List<String> getIDList() {

        if (mCustomersID.size() > 0) {
            mCustomersID.clear();
        }

        traverse(root);
        return mCustomersID;
    }

    private void traverse (TreeNode root) {

        if (root == null)
            return;

        if (root.left != null) {
            traverse( root.left);
        }
        mCustomersID.add(root.mCustomer.getID().toString());
        if (root.right != null) {
            traverse(root.right);
        }
    }
}
