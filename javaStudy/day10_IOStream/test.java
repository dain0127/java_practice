import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class test{
    public static void main(String[] args) throws IOException {
        OutputStream out = new FileOutputStream("data.dat");
        InputStream in = new FileInputStream("data.dat");
    }
}