package Main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
import Entities.Call;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList; 
import java.util.Random;
import java.util.Scanner;

public class CallLoader {
    class Pair<C, B> {  // GENERICS HEH!
        private C call; private B bool; 
        public Pair(C c, B b){ call = c; bool = b; }
        public C getCall(){ return call; }
        public void setBoolean(B val){ bool = val; }
        public B getBoolean(){ return bool; }
    }
    
    private ArrayList<Pair<Call, Boolean>> allCalls; 
    private ArrayList<Call[]> callshift; 
    private String callScript = "src/Main/CALL_SCRIPT.txt";
    
//    
//    public static void main(String[] args) {
//        CallLoader cpt = new CallLoader();
//    }
    
    public CallLoader(){
        System.out.println("BUILDING CALL LOADER...");
        allCalls = new ArrayList<>();
        callshift = new ArrayList<>();
        loadCalls(callScript);
        assignCalls();
        //readCalls();
    }
    
    public Call[] getCallShift(int idx){ return callshift.get(idx); }
    public ArrayList<Call[]> getCallShifts(){ return callshift; }
    
    //HELPER
    private void loadCalls(String script){
        System.out.println("LOADING CALLS...");
        File encodedFile = new File(script);
        try (Scanner sc = new Scanner(encodedFile)) {
            while(sc.hasNextLine()){
                String txt = sc.nextLine();
                if(txt.isBlank()) continue;
                if(!txt.startsWith("NEW_CALL:")) continue;

                String name = txt.split(":")[1].trim();
                String diff = sc.nextLine().split(":")[1].trim();
                String callDesc = sc.nextLine().split(":")[1].trim();
                
                String[] best = sc.nextLine().split(":"); 
                String[] good = sc.nextLine().split(":"); 
                String[] bad = sc.nextLine().split(":"); 
                String[] noanswer = sc.nextLine().split(":"); 

                Call call = new Call(name, diff);
                call.setCallDescription(callDesc);
                
                String[] bestParts = best[1].split("\\|");
                String[] goodParts = good[1].split("\\|");
                String[] badParts = bad[1].split("\\|");
                
                call.setOptionDialogue("BEST", extractOption(best[0]), bestParts);
                call.setOptionDialogue("GOOD", extractOption(good[0]), goodParts);
                call.setOptionDialogue("BAD", extractOption(bad[0]), badParts);

                call.setCallOption("NOANSWER", "No Response");
                call.setCallerReply("NOANSWER", noanswer[1]);
                
                allCalls.add(new Pair(call, false));
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + script);
            ex.printStackTrace();
        }
    }

    // to extract options from ()
    private String extractOption(String line) {
        int startColon = line.indexOf('(')+1;
        int endColon = line.indexOf(')');
        return line.substring(startColon, endColon);
    }

    private void assignCalls(){
        System.out.println("ASSIGNING CALLS...");
        Random random = new Random(); 
        int maxCalls = 3;
        int numCalls = allCalls.size();

        for(int i = 0; i < numCalls; ){
            int remaining = numCalls - i;
            int callsForThisShift = Math.min(maxCalls, remaining);

            Call[] assignedCalls = new Call[callsForThisShift];
            for(int j = 0; j < callsForThisShift; ){
                int callIdx = random.nextInt(numCalls);
                if(!allCalls.get(callIdx).getBoolean()){ 
                    assignedCalls[j] = allCalls.get(callIdx).getCall();
                    allCalls.get(callIdx).setBoolean(true);
                    j++; i++;
                }
            }
            callshift.add(assignedCalls);
        }
    }
    
    public void shuffleCalls(){
        for(int i = 0; i<allCalls.size(); i++)
            allCalls.get(i).setBoolean(false);
        assignCalls();
    }
    
    //    private void readCalls(){
//        System.out.println("READING CALLS...");
//        for(Call[] c: callshift){
//            System.out.println("CALLSHIFT - " + callshift.indexOf(c));
//            for(Call call: c){
//                System.out.println(call.getCallerName());
//                System.out.println(call.getCallDescription());
//                System.out.println("BEST: " + call.getCallOption("BEST"));
//                System.out.println("GOOD: " + call.getCallOption("GOOD"));
//                System.out.println("BAD: " + call.getCallOption("BAD"));
//                System.out.println("NOANSWER: " + call.getCallOption("NOANSWER"));
//                
//                call.selectAnswer("BEST");
//                System.out.println("\nSELECTED ANSWER: ");
//                System.out.println("Player: " + call.getPlayerReply());
//                System.out.println("Caller: " + call.getCallerReply());
//            }
//            System.out.println("=================================");
//        }
//    }
    
}
