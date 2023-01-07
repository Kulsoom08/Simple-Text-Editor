import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class simpleeditor implements ActionListener {
    static JFrame frame;
    static JTextArea textarea;
    static JButton button;
    static JMenuBar menubar;
    static JMenu file;
    static JMenu edit;
    static JMenu close;
    static JMenuItem newfile;
    static JMenuItem openfile;
    static JMenuItem savefile;
    static JMenuItem printfile;
    static JMenuItem copy;
    static JMenuItem cut;
    static JMenuItem paste;
    static JMenuItem end;
    simpleeditor()
    {
        frame=new JFrame("Simple Text Editor");
        frame.setLayout(null);
        frame.setBounds(0,0,1000,800);

        textarea=new JTextArea("Welcome to Simple Text Editor!!");
        textarea.setBounds(0,0,1000,500);
        frame.add(textarea);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        button=new JButton("Press");
        button.setBounds(200,225,100,50);
        button.addActionListener(this);
        frame.add(button);

        menubar=new JMenuBar();
        file=new JMenu("File");
        edit=new JMenu("Edit");
        close=new JMenu("Close");
        newfile=new JMenuItem("New");
        newfile.addActionListener(this);

        openfile=new JMenuItem("Open");
        openfile.addActionListener(this);

        savefile=new JMenuItem("Save");
        savefile.addActionListener(this);

        printfile=new JMenuItem("Print");
        printfile.addActionListener(this);

        copy=new JMenuItem("Copy");
        copy.addActionListener(this);

        cut=new JMenuItem("Cut");
        cut.addActionListener(this);

        paste=new JMenuItem("Paste");
        paste.addActionListener(this);

        end=new JMenuItem("close");
        end.addActionListener(this);


        file.add(savefile);
        file.add(newfile);
        file.add(openfile);
        file.add(printfile);
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        close.add(end);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(close);
        frame.setJMenuBar(menubar);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        simpleeditor f=new simpleeditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        if((s).equals("Copy")) {
            textarea.copy();
        }
        else if((s).equals("Cut"))
        {
            textarea.cut();
        }
        else if((s).equals("Paste"))
        {
            textarea.paste();
        }
        else if((s).equals("Print"))
        {
            try {
                textarea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if((s).equals("New"))
        {
            textarea.setText("");
        }
        else if((s).equals("Close"))
        {
            frame.setVisible(false);
        }
        else if((s).equals("Open"))
        {
            JFileChooser jFileChooser=new JFileChooser("C");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION) {
                File file = new File(jFileChooser.getSelectedFile().getAbsoluteFile().toURI());
                String s1 = "",s2="";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while ((s1 = bufferedReader.readLine()) != null) {
                        s2 += s1 + "\n";
                    }
                    textarea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s.equals("Save"))
        {
            JFileChooser jFileChooser=new JFileChooser("C");
            int ans=jFileChooser.showSaveDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION) {
                File file = new File(jFileChooser.getSelectedFile().getAbsoluteFile().toURI());
                BufferedWriter writer=null;
                try{
                    writer=new BufferedWriter(new FileWriter(file,false));
                    writer.write((textarea.getText()));
                    writer.flush();
                    writer.close();
                }
                catch(IOException ex){
                    throw new RuntimeException(ex);
                }
            }
        }
        }

    }


