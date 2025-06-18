
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_13460_구슬탈출2 {

	static int N, M;
	static char[][] map;
	static int answer = 11;
	static int [] red, blue; 


	public static void main(String[] args) throws Exception {

		input();
		
		findBeads();
		
		move(1, red[0], red[1], blue[0], blue[1]);
		
		if(answer == 11) System.out.println(-1);
		else System.out.println(answer);
		
	}

	public static void input() throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());

		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		}
		

	}
	
	public static void findBeads() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 'B') {
					blue = new int [] {r,c};
					map[r][c] = '.';
				} else if(map[r][c] == 'R') {
					red = new int [] {r,c};
					map[r][c] = '.';
				} 
			}
		}
	}
	
	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	
	public static void move(int turn, int redR, int redC, int blueR, int blueC) {
		if(turn == 11) return;
		
		for(int d=0; d<4; d++) {
			int [] redNext = roll(d,redR, redC);
			int [] blueNext = roll(d,blueR, blueC);
			
			if(map[blueNext[0]][blueNext[1]] == 'O') {
				continue;
			} 

			 switch(d) {
			 case 0 : 
				 if(redC == blueC  && redNext[0] == blueNext[0] ) {
					 if(redR > blueR) 
						 redNext[0]++;
					 else 
						 blueNext[0]++;
				 }
				 break;
			 case 1 : 
				 if(redC == blueC && redNext[0] == blueNext[0] ) {
					 if(redR > blueR) 
						 blueNext[0]--;
					 else 
						 redNext[0]--;
				 }
				 break;
			 case 2 : 
				 if(redR == blueR && redNext[1] == blueNext[1] ) {
					 if(redC > blueC) 
						 redNext[1]++;
					 else 
						 blueNext[1]++;
				 }
				 break;
			 case 3 : 
				 if(redR == blueR && redNext[1] == blueNext[1]) {
					 if(redC > blueC) 
						 blueNext[1]--;
					 else 
						 redNext[1]--;
				 }
				 break;
			 }
			 

			if(map[redNext[0]][redNext[1]] == 'O') {
					answer = Math.min(answer, turn);
					continue;
			}
			 move(turn+1,redNext[0], redNext[1], blueNext[0], blueNext[1]);
		}
		
	}
	
	public static int[] roll(int direction, int r, int c) {
		
		while(true) {
			int nr = r+dr[direction]; 
			int nc = c+dc[direction];
			
			if(map[nr][nc] == '#' ) {
				return new int[] {r,c};
			} else if(map[nr][nc] == 'O') {
				return new int[] {nr,nc};
			}
			r = nr;
			c = nc;
			
		}
	}


}
