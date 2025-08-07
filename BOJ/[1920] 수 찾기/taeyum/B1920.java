import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// N -> 100,000
// 배열, 리스트등 완탐 돌리면 터질 듯
// Hash index 사용하는 Set 이나 Map 으로 하면 평균 O(1)
// O(N + M) -> O(N) -> 가능
public class B1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int B[] = new int[M];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < M ; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        for (int i : B) {
            if(set.contains(i)) {
                System.out.println(1);
            }else {
                System.out.println(0);
            }
        }
    }
}