package Chapter19;

public class Tree {
    public String key;
    public int val;
    public Tree left, right;
    public Tree(String k, int v, Tree l, Tree r){
        key=k; val=v; left=l; right=r;
    }
}
