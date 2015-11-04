import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
//d
public class JTextFieldLimit extends JTextField {
    private int limit;
    private final String allowedChar="1234567890";

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    @Override
    protected Document createDefaultModel() {
        return new LimitDocument();
    }

    private class LimitDocument extends PlainDocument {

        @Override
        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            char[] input = str.toCharArray();
            for (char c : input) {
                if(Character.isLetter(c)) {
                    return;
                }
            }
            
            
            if ((getLength() + str.length()) <= limit) {
            	
            	
                super.insertString(offset, str, attr);
            }
        }       

    }

}