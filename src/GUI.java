import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class GUI {
    public static JFrame frame = new JFrame("New document | Notepad+++ 0.2");
    public GUI() {
        saveButton.setEnabled(false);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(frame);
                String fileLocation = fc.getSelectedFile().getAbsolutePath();
                try {
                    Scanner in = new Scanner(new InputStreamReader(new FileInputStream(fileLocation), StandardCharsets.UTF_8));
                    StringBuilder sb = new StringBuilder();
                    while(in.hasNext()) {
                        sb.append(in.nextLine()+"\n");
                    }
                    in.close();
                    currentDocumentPath = fileLocation;
                    saveButton.setEnabled(true);
                    String output = sb.toString();
                    textarea.setText(output);
                    int firstIndexOfSlash = -1;
                    firstIndexOfSlash = fileLocation.lastIndexOf('\\');
                    if(firstIndexOfSlash < 0){
                        firstIndexOfSlash = 0;
                    }
                    frame.setTitle(fileLocation.substring(firstIndexOfSlash+1, fileLocation.length()) + " | Notepad+++ 0.2");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        saveAsButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(true);

                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(frame);
                String fileLocation = fc.getSelectedFile().getAbsolutePath();
                int locationLength = fileLocation.length();
                if(fileLocation.substring(locationLength-5, locationLength-1).equalsIgnoreCase(".txt")){
                    fileLocation += ".txt";
                }
                currentDocumentPath = fileLocation;
                saveFile(fileLocation);
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textarea.getText());
                if(!textarea.getText().equals(null) && !textarea.getText().equals("")){
                    int warning = JOptionPane.showConfirmDialog(frame, "Are you sure you want to discard this document?");
                    if(warning != JOptionPane.YES_OPTION) return;
                }
                textarea.setText("");
                saveButton.setEnabled(false);
                frame.setTitle("New document | Notepad+++ 0.2");
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile(currentDocumentPath);
            }
        });
    }

    private void saveFile(String path){
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.println(textarea.getText());
            writer.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
    }

    private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private JPanel panel1;
    private JTextArea textarea;
    private JScrollPane scrollpane;
    private JButton openButton;
    private JButton saveButton;
    private JCheckBox encryptedCheck;
    private JButton newButton;
    private JButton saveAsButton;
    private static String currentDocumentPath;

    public static String lastEdit;

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


}