package Chapter19;

public class TreeProcessor {
    public static int lookup(String k, int defaultval, Tree t){
        if(t==null)return defaultval;
        if(k.equals(t.key))return t.val;
        return lookup(k, defaultval, k.compareTo(t.key)>0?t.left:t.right);
    }
//    public static void update(String k, int newval, Tree t){
//        if(t==null){}
//        else if(k.equals(t.key))t.val = newval;
//        else update(k,newval,k.compareTo(t.key)<0?t.left:t.right);
//    }
    public static Tree update(String k, int newval, Tree t){
        if(t==null)t=new Tree(k, newval,null,null);
        else if(k.equals(t.key))t.val = newval;
        else if(k.compareTo(t.key)<0) t.left = update(k,newval,t.left);
        else t.right = update(k, newval, t.right);
        return t;
    }
    public static Tree fupdate (String k, int newval, Tree t){
        return(t==null)?
                new Tree(k,newval,null,null):
                k.equals(t.key)?
                        new Tree(k,newval,t.left,t.right):
                        k.compareTo(t.key)<0?
                                new Tree(t.key, t.val, fupdate(k,newval,t.left),t.right):
                                new Tree(t.key, t.val,t. left, fupdate(k,newval,t.right));
    }
}
