import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;


public class SAP {
    private Digraph G;
    public SAP(Digraph G){
        this.G = new Digraph(G);
    }
    public int length(int v, int w){
        if (!validate(v) || !validate(w)){
            throw new IndexOutOfBoundsException("Graph does not contain this number");
        }
        BreadthFirstDirectedPaths vPath = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wPath = new BreadthFirstDirectedPaths(G, w);
        return getLength(vPath, wPath);
        
    }
    
    public int ancestor(int v, int w){ 
        if (!validate(v) || !validate(w)){
            throw new IndexOutOfBoundsException("Graph does not contain this number");
        }
        BreadthFirstDirectedPaths vPath = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wPath = new BreadthFirstDirectedPaths(G, w);
        return getAncestor(vPath, wPath);
    } 
    public int length(Iterable<Integer> v, Iterable<Integer> w){ 
        if (!validateIterable(v) || !validateIterable(w)){
            throw new IndexOutOfBoundsException("Graph does not contain this number");
        }
        BreadthFirstDirectedPaths vPath = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wPath = new BreadthFirstDirectedPaths(G, w);
        return getLength(vPath, wPath);
    } 
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (!validateIterable(v) || !validateIterable(w)){
            throw new IndexOutOfBoundsException("Graph does not contain this number");
        }
        BreadthFirstDirectedPaths vPath = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wPath = new BreadthFirstDirectedPaths(G, w);
        return getAncestor(vPath, wPath);
    }
    private int getAncestor(BreadthFirstDirectedPaths vPath, BreadthFirstDirectedPaths wPath){
        int ancestor = -1;
        Stack<Integer> ancestorstack = new Stack<Integer>();
        Stack<Integer> lengthstack = new Stack<Integer>();
        int l = Integer.MAX_VALUE;
        for (int a = 0; a < G.V();a++){
            if (vPath.hasPathTo(a) && wPath.hasPathTo(a)){
                l = vPath.distTo(a)+wPath.distTo(a);
                if (lengthstack.isEmpty()){
                    lengthstack.push(l);
                    ancestorstack.push(a);
                } else if (l < lengthstack.peek()){
                    lengthstack.push(l);
                    ancestorstack.push(a);
                }
            }
        }

        if (!ancestorstack.isEmpty()) ancestor = ancestorstack.peek();  
        return ancestor;
    }
    private int getLength(BreadthFirstDirectedPaths vPath, BreadthFirstDirectedPaths wPath){
        Stack<Integer> lengthstack = new Stack<Integer>();
        int length = -1;
        int l = Integer.MAX_VALUE;
        for (int a = 0; a < G.V();a++){
            if (vPath.hasPathTo(a) && wPath.hasPathTo(a)){
                l = vPath.distTo(a)+wPath.distTo(a);
                if (lengthstack.isEmpty()){
                    lengthstack.push(l);
                } else if (l < lengthstack.peek()){
                    lengthstack.push(l);
                }
            }
        }
        if (!lengthstack.isEmpty())length = lengthstack.peek();
        return length; 
    }
    private boolean validate(int a) {
        if (a < 0 || a > G.V()){
            System.out.println(a + " is not valid");
            return false;
        } else {
            return true;
        }
    }
    private boolean validateIterable(Iterable<Integer> a) {
        if (a == null){
            throw new NullPointerException(a + " is null");
        } else {
            return true;
        }
    }
    public static void main(String[] args) {
//        StdOut.println("starting: ");
//        In in = new In(args[0]);
//        Digraph G = new Digraph(in);
//        SAP sap = new SAP(G);
//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//            int length   = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
//        StdOut.println("done");
    }

}
