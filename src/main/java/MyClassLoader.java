import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

class MyClassLoader extends ClassLoader {
    private final String classFile;

    public MyClassLoader(String classFile) {
        this.classFile = classFile;
    }

    @Override
    protected Class<?> findClass(String name) {
        try (
                FileInputStream fis = new FileInputStream(classFile);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ) {
            int content;
            while ((content = fis.read()) != -1) {
                bos.write(content);
                bos.flush();
            }
            byte[] buffer = bos.toByteArray();
            return defineClass(name, buffer, 0, buffer.length);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

