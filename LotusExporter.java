import java.awt.Robot;
import java.awt.AWTException;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class LotusExporter{

  private static class keyStroke{
  
    public int delay;
    private int letter;
    public keyStroke(int letter, int delay)
    {
      this.letter = letter;
      this.delay = delay;
    }
    
    public void press(Robot r){
      r.keyPress(letter);
      r.keyRelease(letter);
    }
  }
  
  private static class comboStroke extends keyStroke {
    public comboStroke(int letter, int[] modifiers, int delay){
      super(letter, delay);
      this.modifiers = modifiers;
    }
    
    public comboStroke(int letter, int modifier, int delay){
      super(letter, delay);
      this.modifiers = new int[1];
      this.modifiers[0] = modifier;
    }
    
    int[] modifiers;
    
    public void press(Robot r){
      for(int i : modifiers){
        r.keyPress(i);
      }
      super.press(r);
      for(int i : modifiers){
        r.keyRelease(i);
      }
    }
  }
    

  public static void main(String[] args) throws AWTException{
    JOptionPane.showMessageDialog(null, "Close all other windows, including Lotus.\nDouble Click Lotus, then hit OK");
    int files = Integer.parseInt(JOptionPane.showInputDialog("How Many Files to copy?"));
  
    Robot robby = new Robot();
    ArrayList<keyStroke> keySequence = new ArrayList<>();
   
    keySequence.add(new keyStroke(KeyEvent.VK_HOME, 1000));
    
    for(int n = 0; n < files; n++){
      //step 3
      int[] ctrl = {KeyEvent.VK_CONTROL};
      keySequence.add(new keyStroke(KeyEvent.VK_ENTER, 500));
      keySequence.add(new comboStroke(KeyEvent.VK_P, ctrl, 2000));
      
      //step 4
      keySequence.add(new keyStroke(KeyEvent.VK_A, 500));
      
      //step 5
      int[] shift = {KeyEvent.VK_SHIFT};
      keySequence.add(new comboStroke(KeyEvent.VK_TAB, shift, 500));
      keySequence.add(new comboStroke(KeyEvent.VK_TAB, shift, 500));
      keySequence.add(new keyStroke(KeyEvent.VK_LEFT, 500));
      keySequence.add(new keyStroke(KeyEvent.VK_LEFT, 500));
      keySequence.add(new keyStroke(KeyEvent.VK_ENTER, 3000));
      
      //step 6
      for(int i = 0; i < 5; i++)
        keySequence.add(new keyStroke(KeyEvent.VK_TAB, 300));
      keySequence.add(new keyStroke(KeyEvent.VK_ENTER, 500));
      
      //step 6, first email
      if(n == 0){
        keySequence.addAll(getKeySequenceFromString(args[0], 100));
        
        //step 7, first email
        int[] ctrlShift = {KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT};
        keySequence.add(new comboStroke(KeyEvent.VK_N, ctrlShift, 1000));
        keySequence.addAll(getKeySequenceFromString("LotusExport", 100));
        
        //step 8
        keySequence.add(new keyStroke(KeyEvent.VK_ENTER, 500));
        keySequence.add(new keyStroke(KeyEvent.VK_ENTER, 500));
      } else {
        //step 6, subsequent emails
        keySequence.addAll(getKeySequenceFromString(args[0] + "\\LotusExport", 100));
      }
      
      //step 9
      keySequence.add(new keyStroke(KeyEvent.VK_TAB, 500));
      keySequence.add(new keyStroke(KeyEvent.VK_TAB, 500));
      
      //step 10
      keySequence.add(new keyStroke(KeyEvent.VK_RIGHT, 500));
      keySequence.addAll(getKeySequenceFromString("_email_" + format(n+1), 100));
      
      //step 11
      keySequence.add(new keyStroke(KeyEvent.VK_ENTER, 4000));
      
      //step 12
      int[] alt = {KeyEvent.VK_ALT};
      keySequence.add(new comboStroke(KeyEvent.VK_F4, alt, 4000));
      
      //step 13
      keySequence.add(new comboStroke(KeyEvent.VK_W, ctrl, 500));
      keySequence.add(new keyStroke(KeyEvent.VK_DOWN, 1000));
    }//end for
    
    int totalDelay = 0;
    for(keyStroke k : keySequence)
      totalDelay += k.delay;
    System.out.println("Total time required: " + totalDelay);
        

    for(keyStroke k : keySequence){
      k.press(robby);
      safeSleep(k.delay);
    }
  }
  
  public static String format(int n){
    DecimalFormat f = new DecimalFormat("0000");
    return f.format(n);
  }
  
  public static void safeSleep(int mils){
    try{
      Thread.sleep(mils);
    } catch (InterruptedException e) {
    }
  }
  
  private static ArrayList<keyStroke> getKeySequenceFromString(String s, int delay){
    ArrayList<keyStroke> strokes = new ArrayList<>();
    for(int i = 0 ; i < s.length(); i++){
      strokes.add(getKeyStrokeFromChar(s.charAt(i), delay));
    }
    return strokes;
  }
  
  private static keyStroke getKeyStrokeFromChar(char c, int delay){
    int[] shift = {KeyEvent.VK_SHIFT};
    switch(c) {
      case 'a':
        return new keyStroke(KeyEvent.VK_A, delay);
        
      case 'b':
        return new keyStroke(KeyEvent.VK_B, delay);
        
      case 'c':
        return new keyStroke(KeyEvent.VK_C, delay);
        
      case 'd':
        return new keyStroke(KeyEvent.VK_D, delay);
        
      case 'e':
        return new keyStroke(KeyEvent.VK_E, delay);
        
      case 'f':
        return new keyStroke(KeyEvent.VK_F, delay);
        
      case 'g':
        return new keyStroke(KeyEvent.VK_G, delay);
        
      case 'h':
        return new keyStroke(KeyEvent.VK_H, delay);
        
      case 'i':
        return new keyStroke(KeyEvent.VK_I, delay);
        
      case 'j':
        return new keyStroke(KeyEvent.VK_J, delay);
        
      case 'k':
        return new keyStroke(KeyEvent.VK_K, delay);
        
      case 'l':
        return new keyStroke(KeyEvent.VK_L, delay);
        
      case 'm':
        return new keyStroke(KeyEvent.VK_M, delay);
        
      case 'n':
        return new keyStroke(KeyEvent.VK_N, delay);
        
      case 'o':
        return new keyStroke(KeyEvent.VK_O, delay);
        
      case 'p':
        return new keyStroke(KeyEvent.VK_P, delay);
        
      case 'q':
        return new keyStroke(KeyEvent.VK_Q, delay);
        
      case 'r':
        return new keyStroke(KeyEvent.VK_R, delay);
        
      case 's':
        return new keyStroke(KeyEvent.VK_S, delay);
        
      case 't':
        return new keyStroke(KeyEvent.VK_T, delay);
        
      case 'u':
        return new keyStroke(KeyEvent.VK_U, delay);
        
      case 'v':
        return new keyStroke(KeyEvent.VK_V, delay);
        
      case 'w':
        return new keyStroke(KeyEvent.VK_W, delay);
        
      case 'x':
        return new keyStroke(KeyEvent.VK_X, delay);
        
      case 'y':
        return new keyStroke(KeyEvent.VK_Y, delay);
        
      case 'z':
        return new keyStroke(KeyEvent.VK_Z, delay);
        
        
      case 'A':
        return new comboStroke(KeyEvent.VK_A, shift, delay);
        
      case 'B':
        return new comboStroke(KeyEvent.VK_B, shift, delay);
        
      case 'C':
        return new comboStroke(KeyEvent.VK_C, shift, delay);
        
      case 'D':
        return new comboStroke(KeyEvent.VK_D, shift, delay);
        
      case 'E':
        return new comboStroke(KeyEvent.VK_E, shift, delay);
        
      case 'F':
        return new comboStroke(KeyEvent.VK_F, shift, delay);
        
      case 'G':
        return new comboStroke(KeyEvent.VK_G, shift, delay);
        
      case 'H':
        return new comboStroke(KeyEvent.VK_H, shift, delay);
        
      case 'I':
        return new comboStroke(KeyEvent.VK_I, shift, delay);
        
      case 'J':
        return new comboStroke(KeyEvent.VK_J, shift, delay);
        
      case 'K':
        return new comboStroke(KeyEvent.VK_K, shift, delay);
        
      case 'L':
        return new comboStroke(KeyEvent.VK_L, shift, delay);
        
      case 'M':
        return new comboStroke(KeyEvent.VK_M, shift, delay);
        
      case 'N':
        return new comboStroke(KeyEvent.VK_N, shift, delay);
        
      case 'O':
        return new comboStroke(KeyEvent.VK_O, shift, delay);
        
      case 'P':
        return new comboStroke(KeyEvent.VK_P, shift, delay);
        
      case 'Q':
        return new comboStroke(KeyEvent.VK_Q, shift, delay);
        
      case 'R':
        return new comboStroke(KeyEvent.VK_R, shift, delay);
        
      case 'S':
        return new comboStroke(KeyEvent.VK_S, shift, delay);
        
      case 'T':
        return new comboStroke(KeyEvent.VK_T, shift, delay);
        
      case 'U':
        return new comboStroke(KeyEvent.VK_U, shift, delay);
        
      case 'V':
        return new comboStroke(KeyEvent.VK_V, shift, delay);
        
      case 'W':
        return new comboStroke(KeyEvent.VK_W, shift, delay);
        
      case 'X':
        return new comboStroke(KeyEvent.VK_X, shift, delay);
        
      case 'Y':
        return new comboStroke(KeyEvent.VK_Y, shift, delay);
        
      case 'Z':
        return new comboStroke(KeyEvent.VK_Z, shift, delay);
        
      
      case '0':
        return new keyStroke(KeyEvent.VK_0, delay);
        
      case '1':
        return new keyStroke(KeyEvent.VK_1, delay);
        
      case '2':
        return new keyStroke(KeyEvent.VK_2, delay);
        
      case '3':
        return new keyStroke(KeyEvent.VK_3, delay);
        
      case '4':
        return new keyStroke(KeyEvent.VK_4, delay);
        
      case '5':
        return new keyStroke(KeyEvent.VK_5, delay);
        
      case '6':
        return new keyStroke(KeyEvent.VK_6, delay);
        
      case '7':
        return new keyStroke(KeyEvent.VK_7, delay);
        
      case '8':
        return new keyStroke(KeyEvent.VK_8, delay);
        
      case '9':
        return new keyStroke(KeyEvent.VK_9, delay);
        
      
      case ' ':
        return new keyStroke(KeyEvent.VK_SPACE, delay);
        
      case ':':
        return new comboStroke(KeyEvent.VK_SEMICOLON, shift, delay);
        
      case '/':
        return new keyStroke(KeyEvent.VK_SLASH, delay);
        
      case '\\':
        return new keyStroke(KeyEvent.VK_BACK_SLASH, delay);
      
      case '_':
        return new comboStroke(KeyEvent.VK_MINUS, shift, delay);
        
    
      default:
        throw new IllegalArgumentException("Unknown key: " + c);
    }
  }
}
