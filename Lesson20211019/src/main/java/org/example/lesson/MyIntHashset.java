package org.example.lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yu Jian
 * @create 2021/10/17 14:41
 */

public class MyIntHashset {
    private int size;
    private List<List<Integer>> set;

    public MyIntHashset(int size) {
        this.size = size;
        set = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            set.add(new ArrayList<>());
        }
    }

    public void add(Integer value) {
        int position = value % this.size;
        List<Integer> list = set.get(position);
        if (!list.contains(value)) {
            list.add(value);
        }
    }

    public void remove(Integer value) {
        int position = value % this.size;
        List<Integer> list = set.get(position);
        list.remove(value);
    }

    public boolean contains(Integer value) {
        int position = value % this.size;
        List<Integer> list = set.get(position);
        return list.contains(value);
    }

    public static void main(String[] args) {
        MyIntHashset hashset = new MyIntHashset(10);
        hashset.add(10);
        System.out.println(hashset.contains(15));
    }
}
