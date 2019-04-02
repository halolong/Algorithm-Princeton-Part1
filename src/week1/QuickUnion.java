package week1;// structure: Tree

class QuickUnion {
	
	public int[] id;
	//init 
	public QuickUnion(int N){
		id = new int[N];
		for (int i = 0; i < id.length; i++){
			id[i] = i;
		}
	}
	// get the root
	public int root(int p){
		while (p != id[p]) {
			p = id[p];
		}
		return id[p];
	}
	public boolean connected(int p, int q){
		return root(p) == root(q);
	}
	
	public void union(int p, int q){
		int rootP = root(p);
		int rootQ = root(q);
		id[rootP] = rootQ;
	}
	public static void main(String[] args) {
		QuickUnion test = new QuickUnion(5);
		int[] id = test.id;
		id[1] = 2;
		id[2] = 3;
		id[3] = 4;
		System.out.print(test.root(1));	
	}
}