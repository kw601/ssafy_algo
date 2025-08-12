import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static int A, B, C;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        int value = A * B * C;
        int[] arr = new int[10];

        while(value !=0){
            arr[value%10]++;
            value /= 10;
        }

        for(int i = 0; i < 10; i++){
            System.out.println(arr[i]);
        }

    }
}