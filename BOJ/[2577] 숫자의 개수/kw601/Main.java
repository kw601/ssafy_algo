import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = new int[10];
        int mul = 1;
        
        // 읽어서 mul에 곱하기
        for(int i = 0; i < 3; i++){
            String line = br.readLine();
            mul *= Integer.parseInt(line);
        }
        
        // 곱한 값 다시 string으로
        String result = Integer.toString(mul);
        
        // 개수 세기
        for(int i = 0; i < result.length(); i++){
            nums[result.charAt(i) - '0'] ++;
        }
        
        // 출력
        for(int i = 0; i < 10; i++){
            System.out.println(nums[i]);
        }
        
    }
}