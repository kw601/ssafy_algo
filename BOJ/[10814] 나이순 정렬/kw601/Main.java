import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		LinkedList<Object[]> members = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			// 정렬해서 삽입 -> 시간초과
//			int idx = 0;
//            while (idx < members.size() && (int) members.get(idx)[0] <= age) {
//                idx++;
//            }
//			members.add(idx, new Object[]{age, name});
			
			members.add(new Object[]{age, name});
		}
		Collections.sort(members, (m1, m2) -> ((Integer) m1[0]).compareTo((Integer) m2[0]));
		for(Object[] member : members) {
			System.out.println(member[0] + " " + member[1]);
		}
	}
}

// ?
// HashMap 써서 정렬하려고 했으나 -> 먼저 들어온 사람을 먼저 출력 <- 에서 실패
// 정렬이 너무 어렵습니다... sort를 원해요...
// linkedList에 <name, age> 넣고...
// while (age >= list.age) list.next
// 그리고 삽입 -> 시간초과
// Collections.sort(list, (비교1, 비교2) -> 비교1.compareTo(비교2)) : 오름차순