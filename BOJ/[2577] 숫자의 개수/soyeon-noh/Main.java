import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    // 문제 
    // 세 개의 자연수 A, B, C가 주어질 때 
    // A × B × C를 계산한 결과에 0부터 9까지 각각의 숫자가 몇 번씩 쓰였는지를 구하는 
    // 프로그램을 작성하시오.

    // 예를 들어 A = 150, B = 266, C = 427 이라면 
    // A × B × C = 150 × 266 × 427 = 17037300 이 되고, 
    // 계산한 결과 17037300 에는 0이 3번, 1이 1번, 3이 2번, 7이 2번 쓰였다.
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 결과의 최대값은 1000^3 = 1,000,000,000 (10억) 
        // Int 가능
        int A, B, C;
        int mul;
        int[] nArr = new int[10];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        mul = A * B * C;

      
        char[] mulResult = String.valueOf(mul).toCharArray();

        for(int i = 0; i < mulResult.length; i++){
            // 여기서 char를 int로 변환하지 않아 생기는  OutOfBounds 주의
            int num = mulResult[i] - '0'; 
            nArr[num]++;
        }
        for(int i = 0; i < 10; i++){
            System.out.println(nArr[i]);
        }
    }
}