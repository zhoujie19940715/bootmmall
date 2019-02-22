package com.zhoujie.mall.pojo;
/**
 * 每一个学生都有对应的归属地
 * 姓名 年龄
 *  注意：姓名、年龄相同为同一个学生
 *  保证学生的唯一性:
 *
 *
 */
 class Student implements Comparable<Student>{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public int hashCode() {

        return name.hashCode()+age*34;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Student)){
            throw new ClassCastException("类型");
        }

        Student s = (Student) obj;
        return this.name.equals(s.name )&& this.age ==s.age;
    }
    @Override
    public int compareTo(Student o) {
        //根据年龄
        int num =new Integer(this.age).compareTo(new Integer(o.age));
        //年龄相同再根据姓名
        if(num ==0)
            return this.name.compareTo(o.name);
        return num;
    }

}
