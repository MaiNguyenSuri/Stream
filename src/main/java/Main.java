import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        List<String> lstPath = Arrays.asList(
                "D:/Java/text1.txt",
                "D:/Java/text2.txt"
        );


        List<InputStream> lstIns = new ArrayList<>();

        for(String path : lstPath){
            InputStream f1 = readFile(path);
            if(null != f1) {
                lstIns.add(f1);
            }
        }

        writeFile("D:/Java/merge.txt", lstIns);
    }

    public static InputStream readFile(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            return fis;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeFile(String path, List<InputStream> lstIns)
    {
        try {
            if(null != lstIns && lstIns.size() > 0) {
                FileOutputStream fos = new FileOutputStream(path);
                ByteOutputStream bos = new ByteOutputStream();

                String breakLine = "\n";
                byte[] bLine = breakLine.getBytes();

                for (InputStream is : lstIns) {
                    // write data from input stream
                    bos.write(is);

                    // Break line
                    bos.write(bLine);
                }

                // write file
                bos.writeTo(fos);

                bos.flush();
                bos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
