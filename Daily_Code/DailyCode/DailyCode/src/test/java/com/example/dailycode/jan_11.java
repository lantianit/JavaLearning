package com.example.dailycode;

import java.io.*;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

//public class jan_11 {
//}
//
//
//class HeroClassLoader extends ClassLoader {
//    private String classpath;
//    public HeroClassLoader(String classpath) {
//        this.classpath = classpath;
//    }
//    @Override
//    protected Class<?> findClass(String name) throws ClassNotFoundException
//    {
//        try {
////输入流，通过类的全限定名称加载文件到字节数组
//            byte[] classDate = getData(name);
//            if (classDate != null) {
////defineClass方法将字节数组数据 转为 字节码对象
//                return defineClass(name, classDate, 0, classDate.length);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return super.findClass(name);
//    }
//    //加载类的字节码数据
//    private byte[] getData(String className) throws IOException {
//        String path = classpath + File.separatorChar +
//                className.replace('.', File.separatorChar) + ".class";
//        try (InputStream in = new FileInputStream(path);
//             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//            byte[] buffer = new byte[2048];
//            int len = 0;
//            while ((len = in.read(buffer)) != -1) {
//                out.write(buffer, 0, len);
//            }
//            return out.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
//
//class TestMyClassLoader {
//    public static void main(String []args) throws Exception{
////自定义类加载器的加载路径
//        HeroClassLoader hClassLoader=new HeroClassLoader("D:\\lib");
////包名+类名
//        Class c=hClassLoader.loadClass("com.hero.jvm.classloader.Test");
//        if(c!=null){
//            Object obj=c.newInstance();
//            Method method=c.getMethod("say", null);
//            method.invoke(obj, null);
//            System.out.println(c.getClassLoader().toString());
//        }
//    }
//}

class TestThread {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new Thread("Thread-" + i) {
                @Override
                public void run() {
                    try {
                        String name = Thread.currentThread().getName();
                        System.out.println(name);
                        recurive(30000);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                }
            }.start();
        }
    }
    public static void recurive(double d){
        if (d ==0)
            return;
        recurive(d - 1);
    }
}
