package cn.ylw.common.nio;

import org.springframework.util.StopWatch;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 文件
 *
 * @author yanluwei
 * @date 2021/9/10
 */
public class FileIO {

    // 读文件，8M缓冲,效率2秒多
    public static void copyFileBIO(String src, String dest) throws IOException {
        StopWatch stopWatch = new StopWatch();
        File file = new File(src);
        stopWatch.start("BIO拷贝文件，文件大小：" + file.length());
        FileInputStream inputStream = new FileInputStream(file);
        File destFile = new File(dest);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(destFile);
        byte[] buffer = new byte[8192];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    // 不能用bufferReader读，很慢，55秒
    public static void copyFileBIOWithBufferReader(String src, String dest) throws IOException {
        StopWatch stopWatch = new StopWatch();
        File file = new File(src);
        stopWatch.start("BIO with buffer reader拷贝文件，文件大小：" + file.length());
        FileInputStream inputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        File destFile = new File(dest);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(destFile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        String buf;
        while ((buf = reader.readLine()) != null) {
            writer.write(buf);
        }
        writer.close();
        outputStream.close();
        reader.close();
        inputStream.close();
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    // 缓冲区越大，效率越高，1秒多
    public static void copyFileBIOWithBuffer(String src, String dest) throws IOException {
        StopWatch stopWatch = new StopWatch();
        File file = new File(src);
        stopWatch.start("BIO with buffer 拷贝文件，文件大小：" + file.length());
        FileInputStream inputStream = new FileInputStream(file);
        BufferedInputStream reader = new BufferedInputStream(inputStream, 1024 * 100);
        File destFile = new File(dest);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(destFile);
        BufferedOutputStream writer = new BufferedOutputStream(outputStream, 1024 * 100);
        byte[] buffer = new byte[8192];
        int len;
        while ((len = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, len);
        }
        writer.close();
        outputStream.close();
        reader.close();
        inputStream.close();
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    // 非直接内存2秒多，非直接内存也是2秒多
    public static void copyFileNIO(String src, String dest) throws IOException {
        StopWatch stopWatch = new StopWatch();
        File file = new File(src);
        stopWatch.start("NIO 单通道拷贝文件，文件大小：" + file.length());
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel in = inputStream.getChannel();
        File destFile = new File(dest);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(destFile);
        FileChannel out = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(8142);
        while (in.read(buffer) != -1) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
        out.close();
        outputStream.close();
        in.close();
        inputStream.close();
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    // 很搞笑，1秒多
    public static void copyFileNIOChanel2Chanel(String src, String dest) throws IOException {
        StopWatch stopWatch = new StopWatch();
        File file = new File(src);
        stopWatch.start("NIO 通道直接转换拷贝文件，文件大小：" + file.length());
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel in = inputStream.getChannel();
        File destFile = new File(dest);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(destFile);
        FileChannel out = outputStream.getChannel();
        out.transferFrom(in, 0, in.size());
        out.close();
        outputStream.close();
        in.close();
        inputStream.close();
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    // 高效 1秒多
    public static void copyFileWithFiles(String src, String dest) throws IOException {
        StopWatch stopWatch = new StopWatch();
        File file = new File(src);
        stopWatch.start("NIO Files拷贝文件，文件大小：" + file.length());
        File destFile = new File(dest);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        Path copy = Files.copy(Paths.get(src), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(copy);
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    public static void main(String[] args) throws IOException {
        String src = "D:\\software/pdi-ce-9.1.0.0-324.zip";
//        copyFileBIO(src, "D:\\bio\\bio.zip");
//        copyFileBIOWithBufferReader(src, "D:\\bio\\bio.zip");
//        copyFileBIOWithBuffer(src, "D:\\bio\\bio.zip");
//        copyFileNIO(src, "D:\\bio\\bio.zip");
//        copyFileNIOChanel2Chanel(src, "D:\\bio\\bio.zip");
        copyFileWithFiles(src, "D:\\bio\\bio.zip");
    }
}
