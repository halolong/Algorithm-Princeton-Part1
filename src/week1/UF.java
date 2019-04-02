package week1;

import java.util.*;

class UF {
	public int[] id;
	
	//constructor
	public UF(int N){
		//init id (array)  !!!!
		id = new int[N];
		for (int i = 0; i < N; i++){
			id[i] = i;	
//			System.out.print(id[i]);	
		}
	}
	
	// see if connected?
	public boolean connected(int p, int q){
		return id[p] == id[q];
	}
	
	// union two elements
	public void union(int p, int q){
		id[p] = id[q];
	}
	// Main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		UF uf = new UF(N);
		for (int i=0; i < N; i++){
			int p = sc.nextInt();
			int q = sc.nextInt();
			if(!uf.connected(p, q)){
				uf.union(p, q);
				System.out.print(p+" --- "+q);
			}
		}
	}
	
}
