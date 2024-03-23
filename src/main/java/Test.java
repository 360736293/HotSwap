import java.io.File;

/*
 * 自定义类加载器实现热更新
 *
 * 1、但凡预计未来进行热更新的功能模块，全部使用自定义的类加载器进行加载实例化，再调用方法。
 * 2、维护一个全局的类的对象变量，在实例化后替换原来的对象。
 *
 * */
public class Test {
    public static void main(String[] args) throws Exception {
        String path = System.getProperty("user.dir") + "/src/main/classFile";
        File oldFile = new File(path + "/User.class");
        File newFile = new File(path + "/UserNew.class");
        System.out.println("下面执行的是初始的版本");
        loadUser(oldFile);
        System.out.println("下面执行的是现在的版本");
        loadUser(newFile);
    }

    public static void loadUser(File file) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader(file.getPath());
        Class<?> clazz = myClassLoader.findClass("User");
        clazz.newInstance();
    }

}
