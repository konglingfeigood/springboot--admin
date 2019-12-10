package com.springboot.admin.server.test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @ClassName TestClass
 * @Author Administrator
 * @date 2019/10/9 14:03
 */
public class TestClass {

    public static void main(String[] args) {

        int num = 22617868;
        System.out.println(num % 7);
        System.out.println(Integer.MAX_VALUE);

        Hashtable<String, Object> table = new Hashtable<>();
        table.put("a", "a");


        System.out.println(table.toString());

        table.clear();

        table.forEach((k, v) -> {
            System.out.println(k + "   " + v);
        });

        LinkedHashMap<String,Object> map = new LinkedHashMap<>();


        ConcurrentHashMap<String,Object> currentHashMap = new ConcurrentHashMap<>();
        currentHashMap.put("a","b");


        System.out.println(lexicalOrder(112));


        Thread t1 = new Thread();
        t1.start();

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        String a = "xyz";
        String b = new String("xyz");
        System.out.println(a.equals(b));
        System.out.println(a == b);

    }

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n);
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            list.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }
        return list;
    }

}
