import java.util.Stack;


public class Outcast {
    private WordNet wordnet;
    public Outcast(WordNet wordnet){
        this.wordnet = wordnet;
        
    }
    public String outcast(String[] nouns){
        Stack<String> topword = new Stack<String>();
        int topdistance = 0;
        int distance = 0;
        for (int y = 0; y<nouns.length;y++){
            distance = 0;
            for (int i = 0; i<nouns.length;i++){
                distance = distance + wordnet.distance(nouns[i], nouns[y]);
            }
            if (topdistance < distance){
                topdistance = distance;
                topword.add(nouns[y]);
            } 
        }
        return topword.peek();
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
