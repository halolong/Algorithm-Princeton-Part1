package week1;

class QuickFind {
	
	public int[] id;
	//init 
	public QuickFind(int N){
		id = new int[N];
		for (int i = 0; i < id.length; i++){
			id[i] = i;
		}
	}
	
	public boolean connected(int p, int q){
		return id[p] == id[q];
	}
	
	public void union(int p, int q){
		int N = id.length;
		int temp  = id[p];
		for (int i = 0; i < N; i++){
			if (id[i] == temp){
				// mistake! should be id[q]
				id[i] = id[q];
			}
		}
	}
	public static void main(String[] args) {
		QuickFind test = new QuickFind(10);
		test.union(2, 4);
		int[] id = test.id;
		for (int i = 0; i < 10; i++){
			System.out.println(id[i]);
		}
	}
}