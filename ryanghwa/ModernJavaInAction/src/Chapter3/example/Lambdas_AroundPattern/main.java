package Chapter3.example.Lambdas_AroundPattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {
    private static final String FILE = main.class.getResource("./data.txt").getFile();
    public static void main(String[] args) {
        try {
            String result = processFile();
            System.out.println(result);

            String oneLine = processFile((BufferedReader b) -> b.readLine());
            System.out.println(oneLine);

            String twoLine = processFile((BufferedReader b)->b.readLine()+b.readLine());
            System.out.println(twoLine);


        } catch (IOException e) {
            e.printStackTrace();
        }

        //예외 처리 방법

        Function<BufferedReader, String> f = (BufferedReader b)->{
            try {
                return b.readLine();
            }catch (IOException e){
                throw new RuntimeException();
            }
        };

        System.out.println(f); //?????


    }

    public static String processFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))){
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))){
            return p.process(br);
        }
    }
    @FunctionalInterface
    public interface BufferedReaderProcessor{
        String process(BufferedReader b) throws IOException;
    }
    public interface Function<T, R>{
        R test (T t);
    }

}
