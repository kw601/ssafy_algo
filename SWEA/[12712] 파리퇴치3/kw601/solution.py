def kill_fly(x, y):
    total = 0
    for i in range(max(0, x - M + 1), min(N, x + M)):
        total += maps[i][y]
    for j in range(max(0, y - M + 1), min(N, y + M)):
        total += maps[x][j]

    total -= maps[x][y]
    return total

def kill_fly_diag(x, y):
    total = maps[x][y]
    dx = [-1, -1, 1, 1]
    dy = [-1, 1, -1, 1]
    for d in range(4):  # 대각선 4방향
        for k in range(1, M):
            nx = x + dx[d] * k
            ny = y + dy[d] * k
            if 0 <= nx < N and 0 <= ny < N:
                total += maps[nx][ny]
    return total

T = int(input())
for test_case in range(1, T + 1):
    # N*N 지도, 중심부터 M 칸의 파리를 잡을 수 있음.
    global N
    global M

    N, M = map(int, input().split())
    
    # map 입력 받기
    maps = []
    for _ in range(N):
        maps.append(list(map(int, input().split())))

    max_kill = 0
    for i in range(N):
        for j in range(N):
            max_kill = max(max_kill, kill_fly(i, j), kill_fly_diag(i, j))
    
    print(f"#{test_case} {max_kill}")