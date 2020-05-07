package Chapter7.example;

public class countWordsIteratively {
    public static void main(String[] args) {
        int count = countWordsIteratively("Hello world");
        System.out.println(count);


    }

    public static int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }else{
                if(lastSpace)counter++;
                lastSpace = false;
            }
        }
        return counter;
    }
}
