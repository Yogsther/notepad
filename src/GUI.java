import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GUI {
    public static JFrame frame = new JFrame("Notepad+++");
    public GUI() {
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(frame);
                String fileLcation = fc.getSelectedFile().getAbsolutePath();
                try {
                    Scanner in = new Scanner(new InputStreamReader(new FileInputStream(fileLcation), StandardCharsets.UTF_8));
                    StringBuilder sb = new StringBuilder();
                    while(in.hasNext()) {
                        sb.append(in.nextLine()+"\n");
                    }
                    in.close();
                    String output = sb.toString();
                    textarea.setText(output);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    // https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java todo

    public static void main(String[] args) {

        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setPreferredSize(new Dimension(640, 480));
        frame.setSize(new Dimension(640, 480));
        frame.setResizable(false);
        frame.setVisible(true);
        final JFileChooser fc = new JFileChooser();
    }

    private JPanel panel1;
    private JTextArea textarea;
    private JScrollPane scrollpane;
    private JButton openButton;
    private JButton saveButton;
    private JCheckBox encryptedCheck;
    private JPasswordField passwordField;
    private JButton newButton;
    private JButton saveAsButton;
}
