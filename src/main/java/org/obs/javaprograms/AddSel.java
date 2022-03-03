package org.obs.javaprograms;

public class AddSel {
    int a=10;
    int b=20;

    public void add()
    {
        System.out.println("a+b= "+(a+b));
    }


    public static void main(String[] args) {
        AddSel obj=new AddSel();
        obj.add();
    }
}
