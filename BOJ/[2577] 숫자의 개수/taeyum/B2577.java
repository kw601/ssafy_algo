package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 997,002,999 <- 가장 큰 값이 21억 안넘으니까 int 로 받음
        int multi = 1;

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            multi *= Integer.parseInt(st.nextToken());
        }

        // String 으로 변환 후 split 으로 String 배열에 나누어 담기
        String[] str = String.valueOf(multi).split("");

        // 해당하는 숫자를 담을 배열 생성
        int[] count = new int[10];

        // for each 문으로 하나씩 받아와서 int 로 변환 후 해당 인덱스에 숫자 증가
        for (String s : str) {
            int index = Integer.parseInt(s);
            count[index]++;
        }

        // 출력
        for (int i : count) {
            System.out.println(i);
        }
        
    }
}
