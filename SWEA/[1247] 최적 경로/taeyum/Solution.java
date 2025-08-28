import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static Point company;
    static Point house;
    static Point[] customer;
    static boolean[] visited;
    static int minLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            
            company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            
            customer = new Point[N];
            for (int i = 0; i < N; i++) {
                customer[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            visited = new boolean[N];
            minLength = Integer.MAX_VALUE;

            dfs(company, 0, 0);

            System.out.println("#" + test_case + " " + minLength);
        }
    }

    static void dfs(Point start_point, int count, int sum) {
        if (count == N) {
            sum += getDiff(start_point, house);
            minLength = Math.min(minLength, sum);
            return;
        }
        if (sum >= minLength) {
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                Point next_point = customer[i];
                dfs(next_point, count + 1, sum + getDiff(start_point, next_point));
                visited[i] = false;
            }
        }
    }
    
    static int getDiff(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}