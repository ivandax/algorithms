public class Compressed {
    private int[] pointers;
    private int[] sizes;

    // Compressed paths - weighted quick union: Flattens the tree so all items reference the origin node
    public Compressed(int N){
        pointers = new int[N];
        sizes = new int[N];
        for(int i = 0; i < N; i++){
            pointers[i] = i;
            sizes[i] = 1;
        }
    }

    private int root(int i){
        while (i != pointers[i]){
            pointers[i] = pointers[pointers[i]];
            i = pointers[i];
        }
        return i;
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if(i == j){
            return;
        }
        if(sizes[i] > sizes[j]){
            pointers[j] = i;
            sizes[i] += sizes[j];
        } else {
            pointers[i] = j;
            sizes[j] += sizes[i];
        }
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public int[] getPointers() {
        return pointers;
    }

    public void setPointers(int[] pointers) {
        this.pointers = pointers;
    }

    public int[] getSizes() {
        return sizes;
    }

    public void setSizes(int[] sizes) {
        this.sizes = sizes;
    }
}
