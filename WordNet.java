import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {
    private String synsets;
    private String hypernyms;
    private ArrayList<String> nouns;
    private HashMap<String, Bag<Integer>> nounsmap;
    private HashMap<Integer, ArrayList<String>> indexmap;
    private Digraph G;
    private SAP sap;
    
    public WordNet(String synsets, String hypernyms){
        this.synsets = synsets;
        this.hypernyms = hypernyms;
        this.nouns = new ArrayList<String>();
        this.nounsmap = new HashMap<String ,Bag<Integer>>();
        this.indexmap = new HashMap<Integer, ArrayList<String>>();
        setWords(synsets);
        this.G = new Digraph(nouns.size());
        setEdges(hypernyms, G);
        this.sap = new SAP(G);
    }
    public Iterable<String> nouns(){
        Set<String> ns = nounsmap.keySet();
        return ns;
    }
    public boolean isNoun(String word){
        return nounsmap.containsKey(word);
    }
    public int distance(String nounA, String nounB){
        if (!isNoun(nounA) || !isNoun(nounB)){
           throw new IllegalArgumentException("Not a valid noun either: " + nounA + " or " + nounB);
        }
        Bag<Integer> a = nounsmap.get(nounA);
        Bag<Integer> b = nounsmap.get(nounB);
        SAP clone = sap;
        return clone.length(a, b);
    }
    public String sap(String nounA, String nounB){
        if (!isNoun(nounA) || !isNoun(nounB)){
            throw new IllegalArgumentException("Not a valid noun either: " + nounA + " or " + nounB);
        }
        Bag<Integer> a = nounsmap.get(nounA);
        Bag<Integer> b = nounsmap.get(nounB);
        SAP clone = sap;
        int index = clone.ancestor(a, b);
        return indexmap.get(index).get(0);
          
    }
    private void setWords(String synsets){
        In in = new In(synsets);
        while (!in.isEmpty()){
            String line = in.readLine();
            String[] groupings = line.split(",");
            String[] words = groupings[1].split("\\s+");
            ArrayList<String> ws = new ArrayList<String>();
            for (String w : words){
                ws.add(w);
                nouns.add(w);
                if (nounsmap.containsKey(w)){
                    Bag<Integer> i = nounsmap.get(w);
                    i.add(Integer.parseInt(groupings[0]));
                    nounsmap.replace(w, i);
                } else {
                    Bag<Integer> i = new Bag<Integer>();
                    i.add(Integer.parseInt(groupings[0]));
                    nounsmap.put(w, i);
                }
            }
            indexmap.put(Integer.parseInt(groupings[0]), ws);
            
        }
    }
    private void setEdges(String hypernyms, Digraph g){
        In in2 = new In(hypernyms);
        while (!in2.isEmpty()){
            String line = in2.readLine();
            int root = firstNumber(line);
            int[] connections = connections(line);
            for (int a : connections){
                G.addEdge(root, a);
            }
        }
    }
    private int firstNumber(String line){
        String[] nums = line.split(",");
        int value = Integer.parseInt(nums[0]);
        return value;
    }
    private int[] connections(String line){
        String[] wordnums = line.split(",");
        int[] nums = new int[wordnums.length-1];
        for (int i =0;i<nums.length;i++){
            nums[i] = Integer.parseInt(wordnums[i+1]);
        }
        return nums;
    }
    public static void main(String[] args) {
//       WordNet w = new WordNet(args[0], args[1]);
//       String wordone = "sleaziness";
//       String wordtwo = "sleepiness";
//       String wordthree = "mango";
//       String wordfour = "peach";
//       System.out.println("For inputs " + wordone + " and " + wordtwo);
//       System.out.println("Ancestor is " + w.sap(wordone, wordtwo));
//       System.out.println("Distance is " + w.distance(wordone, wordtwo));
//       System.out.println("Ancestor is " + w.sap(wordthree, wordfour));
//       System.out.println("Distance is " + w.distance(wordthree, wordfour));
//       System.out.println(w.isNoun("sleaziness"));

    }

}
