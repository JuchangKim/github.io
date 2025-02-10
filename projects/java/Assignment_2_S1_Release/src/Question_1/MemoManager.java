/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xhu
 */
public class MemoManager <E extends Comparable> {
    public BinaryTree<Date, Date> bTreeDate;
    public BinaryTree<String, String> bTreeTitle;

    public MemoManager() {
        bTreeDate = new BinaryTree<>();
        bTreeTitle = new BinaryTree<>();
    }

    public void addMemo(String date, String title, String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Memo memo = new Memo();
        try {
            memo.date = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(MemoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        memo.title = title;
        memo.message = message;

        addToTree(memo, (E) memo.date);
        addToTree(memo, (E) memo.title);
    }

    public void addToTree(Memo memo, E key) {
        if (key instanceof Date) {
            bTreeDate.addElement(memo.date, (Date) key);
        } else if (key instanceof String) {
            bTreeTitle.addElement(memo.title, (String) key);
        }
    }

    public Memo findMemo(Object key) {
        Memo newMemo = new Memo();
        if (key instanceof Date) {
            Date dateNode = bTreeDate.searchElement((Date) key);
            if (dateNode != null) {
                newMemo.date = dateNode;
            }
            else
            {
                return null;
            }
        } else if (key instanceof String) {
            String titleNode = bTreeTitle.searchElement((String) key);
            if (titleNode != null) {
                newMemo.title = titleNode;
            } else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
        return newMemo;
    }

    public Memo[] getSortedMemoList(E key) {
    if (key instanceof Date) {
        Node<Date, Date>[] sortedNodes = bTreeDate.toSortedList();
        Memo[] memoArray = new Memo[sortedNodes.length];
        for (int i = 0; i < sortedNodes.length; i++) {
            memoArray[i] = new Memo();
            memoArray[i].date = sortedNodes[i].element;
        }
        return memoArray;
    } else if (key instanceof String) {
        Node<String, String>[] sortedNodes = bTreeTitle.toSortedList();
        Memo[] memoArray = new Memo[sortedNodes.length];
        for (int i = 0; i < sortedNodes.length; i++) {
            memoArray[i] = new Memo();
            memoArray[i].title = sortedNodes[i].element;
        }
        return memoArray;
    } else {
        return null;
    }
}


    public void reverseOrder() {
        bTreeDate.reverseOrder();
        bTreeTitle.reverseOrder();
    }
}