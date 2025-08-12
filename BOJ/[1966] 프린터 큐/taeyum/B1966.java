import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int test_case = Integer.parseInt(st.nextToken());

        for (int t = 0 ; t < test_case ; t++) {
            int count = 0;
            st = new StringTokenizer(br.readLine(), " ");

            // N -> Print 수
            // M -> 궁금한 Print의 index
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<Print> queue = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0 ; i < N; i++) {
                Print p = new Print(i,Integer.parseInt(st.nextToken()));
                queue.offer(p);
            }

            // queue가 빌 때 까지 반복
            while (!queue.isEmpty()) {
                // 중요도 순으로 내보내야 하니 queue에서 가장 큰 중요도를 받기
                int max_importance = 0;
                for (Print print : queue) {
                    max_importance = Math.max(max_importance, print.importance);
                }

                // 가장 앞에 있는거 뽑아서 중요도가 가장 높은 중요도면 뺼거니까 count++
                // 그리고 해당 print의 인덱스가 M 이랑 같으면 while 문 종료
                Print currentPrint = queue.poll();
                if (currentPrint.importance == max_importance) {
                    count++;
                    if (currentPrint.index == M) {
                        sb.append(count).append("\n");
                        break;
                    }
                } else {
                    // 아니면 다시 큐 맨 뒤에 추가
                    queue.offer(currentPrint);
                }
            }
        }
        System.out.println(sb);
    }

    // 문서의 인덱스와 중요도 저장할 클래스 생성
    static class Print {
        int index;
        int importance;

        public Print(int index, int importance) {
            this.index = index;
            this.importance = importance;
        }

    }
}
// 근데 while 문에 for 전체 순회 해버리면... 시간 복잡도 너무 심한딩...
