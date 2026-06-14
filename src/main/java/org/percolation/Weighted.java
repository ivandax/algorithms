public class Weighted {
    private int[] pointers;
    private int[] sizes;

    // Weighted quick union: Adds the smallest tree always on the largest to reduce size
    // Which is why we need to also track the sizes
    // Improves the quick union algorithm
    public Weighted(int N){
        pointers = new int[N];
        sizes = new int[N];
        for(int i = 0; i < N; i++){
            pointers[i] = i;
            sizes[i] = 1;
        }
    }

    private int root(int i){
        while (i != pointers[i]){
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
