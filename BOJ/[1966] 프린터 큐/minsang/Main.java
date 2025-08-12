import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
    static int TC, N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());

        // 테스트 케이스 수 입력 받기
        for(int t = 0; t < TC; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            // N: 문서의 개수, M: 몇번째로 인쇄되었는지
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            Queue<int[]> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < N; i++){
                int priority = Integer.parseInt(st.nextToken());
                queue.add(new int[]{i, priority}); // 인덱스, 중요도
                pq.offer(priority);
            }

            int cnt = 0;

            while (!queue.isEmpty()){
                int[] cur = queue.poll();

                if(cur[1] == pq.peek()){
                    pq.poll();
                    cnt++;

                    if(cur[0] == M){
                        System.out.println(cnt);
                        break;
                    }
                } else {
                    queue.offer(cur);
                }
            }
        }
    }

}
