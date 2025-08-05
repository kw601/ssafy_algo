package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도 O(n)~
public class B1546 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] score = new int[N];
        int max = 0;
        double avg = 0.0;

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            // 최대값 구하기~
            if(max < num) {
                max = num;
            }
            score[i] = num;
        }

        // double 으로 캐스팅~
        for (int d : score) {
            avg += (double) d / max * 100;
        }

        // 결과값~
        System.out.println(avg/N);
    }
}
