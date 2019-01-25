package com.zhoujie.mall.pojo;

import java.util.Iterator;
import java.util.TreeMap;

public class TestSort {
    public static void main(String[] args) {
       // Map<Student, String> hm = new HashMap<Student, String>();
        TreeMap<Student, String> hm = new TreeMap<Student, String>();
        hm.put(new Student("lisi0", 21), "ashanghai");
        hm.put(new Student("lisi1", 21), "bshanghai");
        hm.put(new Student("lisi2", 20), "cbeijing");
        hm.put(new Student("lisi3", 22), "dguangzhou");
        hm.put(new Student("lisi4", 25), "ewuhan");
        Iterator<Student> iterator = hm.keySet().iterator();
        while (iterator.hasNext()){
            Student stu = iterator.next();
            String addr = hm.get(stu);
            System.out.println(stu.getAge()+ "  "+ addr);
        }
    }
}
