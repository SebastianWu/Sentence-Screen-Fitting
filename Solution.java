class Solution {
    private HashMap<Integer,int[]> lookup = new HashMap();
    // solve this problem in subproblems. If there are 5 words in sentence, then there are only 5 possibilities that a line can begin
    private int solveSubProb(int index,String[] sentence, int cols){ 
        int init = index;
        int c = cols;
        int count = 0;
        while(c > 0){
            // try fill word init
            String word = sentence[index];
            if(c > word.length() || c == word.length()){
                c -= word.length() + 1;
                index = (index + 1) % sentence.length;
                count += 1;
            }else{ // word with index not fit
                c = 0;
            }
        }
        // add to look up
        lookup.put(init, new int[]{index, count});
        return index; // return next row start index
    }
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int wrd_ct = 0;
        int index = 0; // inital word index of sentence
        for(int r = 0; r<rows; r++){
            // check in lookup or not
            if(lookup.get(index)!=null){
                index = lookup.get(index)[0];
                wrd_ct += lookup.get(index)[1];
            }else{
                int nxt_index = solveSubProb(index, sentence, cols);
                wrd_ct += lookup.get(index)[1];
                index = nxt_index;
            }
        }
        //System.out.println(wrd_ct);
        //for(int i : lookup.keySet()){
        //    System.out.println(i+" "+lookup.get(i)[0]+" "+lookup.get(i)[1]);
        //}
        return wrd_ct / sentence.length;
    }
}
