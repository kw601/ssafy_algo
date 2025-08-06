import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import java.util.StringTokenizer;

public class Main{
    
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 나이, 이름, 순번 순으로 입력받을 2차원 배열 생성
        String[][] userArr = new String[N][2];
        // 순서 카운트 변수 
        // int cnt = 0;

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            userArr[i][0] = st.nextToken();
            userArr[i][1] = st.nextToken();
            // usreArr[i][2] = ++cnt;
        }

        // 나이를 기준으로 COmparator 정렬
        Arrays.sort(
            userArr, (o1,o2) -> 
                Integer.parseInt(o1[0]) - Integer.parseInt(o2[0])  
        );
        
        // 출력
        for(int i = 0; i < N; i++){
            System.out.println(userArr[i][0] + " " + userArr[i][1]);
        }        
        
    }
}