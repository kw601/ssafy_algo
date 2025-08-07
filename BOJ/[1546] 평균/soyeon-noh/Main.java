import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 아이디어
    // 1. 실수 자료형을 조심해야 함
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nArr = new int[N];
        int maxScore = -1;
        int maxScoreIndex = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            
            nArr[i] = Integer.parseInt(st.nextToken());
            if(maxScore < nArr[i]) {
                maxScore = nArr[i]; 
                maxScoreIndex = i;
            }
        }

        double total = 0.0;
        
        // 이부분 실수함
        // maxScore 0인 경우 평균이 0이 되는 걸 따로 출력하자
        if(maxScore == 0){
            System.out.println(0.0);
            return;
        }

        for(int i = 0; i < N; i++){
            total += (double) nArr[i] / (double) maxScore * 100.0;
        }
        System.out.println(total / (double)N);
        
    }
}
