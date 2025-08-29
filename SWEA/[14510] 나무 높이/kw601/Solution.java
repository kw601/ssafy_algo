import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine()); // 나무 개수
            int[] trees = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int maxTall = 0;
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                if (trees[i] > maxTall) {
                    maxTall = trees[i];
                }
            }

            int oddCnt = 0;
            int evenCnt = 0;

            for (int tree : trees) {
                int toGrow = maxTall - tree;
                oddCnt += toGrow % 2;
                evenCnt += toGrow / 2;
            }

            int day = 0;
            while (oddCnt > 0 || evenCnt > 0) {
                day++;

                // 홀수 날: 1씩 키우기
                if (day % 2 == 1) {
                    if (oddCnt > 0) {
                        oddCnt--;
                    } else if (evenCnt > 0) {
                        evenCnt--;
                        oddCnt++;
                    }
                }
                // 짝수 날: 2씩 키우기
                else {
                    if (evenCnt > 0) {
                        evenCnt--;
                    }
                }
            }

            System.out.println("#" + test_case + " " + day);
        }
    }
}
