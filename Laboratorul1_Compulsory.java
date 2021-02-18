import java.util.stream.BaseStream;

public class Compulsory {

    public static void main(String[] args) {
        System.out.println("Hello world!\n");
        String[] languages = new String[] {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        int result = n * 3;
        int aux = Integer.parseInt("10101",2);
        result = result + aux;
        aux = Integer.parseInt("FF",16);
        result = result + aux;
        result = result * 6;
        int result_new;
        do {
            result_new = 0;
            while(result>0) {
                result_new = result_new + result % 10;
                result = result / 10;
            }
            result = result_new;
        }while(result_new > 9);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result_new]);
    }
}
