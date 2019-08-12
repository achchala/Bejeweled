/**
 * Hello, the following is my code for the final GUI project. 
 * This is a modification of the game Bejeweled by PopCap Games
 * I hope you enjoy playing it!
 *
 * Course: ICS 2O8 taught by Mr. Couckuyt
 *
 * By: Achchala Deepan
 * Sunday, January 13th 2019
 */

//The following are the libraries that I imported,these help add features 
// such as audio and borders.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.io.File; 
import java.io.IOException; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip;
import javax.swing.UIManager;

public class Bejeweled extends JPanel implements ActionListener
{
    // CardLayout is a layout manager that allows the user to switch between different "cards" or screens.
    // Also, the following are global variables, or variables that are accessible in all parts of the program.
    CardLayout cdLayout = new CardLayout ();
    static JFrame window;
    JLabel title;
    JButton[][] jewelbuttons;
    ImageIcon[] img;
    int[][] jewelicons;
    boolean firstjewelselected = false,secondjewelselected = false;
    String firstjewelposition = "",secondjewelposition = "";
    int totalscore = 0, totalmoves = 0;
    JLabel moves, score, moves2;
    static Clip clip; 
    
    public static void main (String []args)
    { 
        // Prior to entering the game, there is a password of "jewel" which is on a JOptionPane.
        String answer;
        UIManager um = new UIManager();
        um.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.background", new Color(139,0,139));
        UIManager.put("Panel.background", Color.black);
        answer = JOptionPane.showInputDialog("Please Enter the Password:");

        while (!(answer.equals("jewel")))
        {
            // If the password was incorrect, the password OptionPane will stay.
            um.put("OptionPane.messageForeground", Color.white);
            UIManager.put("OptionPane.background", new Color(139,0,139));
            UIManager.put("Panel.background", Color.black);
            answer = JOptionPane.showInputDialog ("Please Enter the Password:");
        }
        if (answer.equals("jewel"))
        {
            // If the password was correct, the game begins.
            new Bejeweled();
        }

        //This creates the actual Bejeweled frame
        Bejeweled content = new Bejeweled ();
        window = new JFrame("Bejeweled");
        window.setContentPane(content);
        window.setSize(900,600);
        window.setLocation(100,100); 
        window.setVisible(true);
        audio("theme.wav", true);
        audioplay(); 

    }

    public Bejeweled ()
    {

        // All the screens are set to CardLayout to allow navigation.
        setLayout (cdLayout);
        screen1();
        screen2();
        screen3();
        screen4();

    }

    public static void audio (String filePath, boolean loopaudio)

    {
        try
        {
            // The AudioInputStream library is used to import audio to the game.
            
            AudioInputStream audioInputStream; 

            audioInputStream =  AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());  
            // This creates a clip reference. 
            clip = AudioSystem.getClip(); 

            // The following opens audioInputStream to the clip .
            clip.open(audioInputStream); 

            if (loopaudio == true)
            {
                clip.loop(Clip.LOOP_CONTINUOUSLY); 
            }
        }
        catch(Exception ex)
        {
            // If the audio file isn't found, this message will display.
            System.out.println ("The audio file was not found!");
        }
    }

    public static void audioplay()  
    { 
        //The audioplay method can be called to start the music.
        clip.start(); 

    } 

    public static void audiostop()   
    { 
        //The audiostop method can be called to stop the music.        
        clip.stop(); 
        clip.close(); 
    } 

    public void screen1 ()
    {
        // The screen1 is the first screen of my game, and it is a main menu which allows navigation to the information and game screens.
        JPanel page1 = new JPanel(new GridBagLayout());
        //GridBagConstraints are the different constraints set to place each component (widget) in it's desired position.
        GridBagConstraints c = new GridBagConstraints();
        page1.setBackground (Color.black);      

        JLabel space = new JLabel ("");
        page1.add(space);       

        //This is a png header that is the title of the game.
        JLabel header = new JLabel(createImageIcon ("header.png"));
        
        //I will describe each of the GridBagConstraints once!
        // The constraint 'fill' is
        c.fill = GridBagConstraints.HORIZONTAL;
        // The constraint 'anchor'
        c.anchor = GridBagConstraints.CENTER;
        // The constraints 'gridx' and 'gridy'set the component's position on the grid.
        c.gridx = 2;
        c.gridy = 1;
        // The constraints 'gridheight' and 'gridweight'set the amount of grid cells the component takes up.        
        c.gridwidth = 6;
        c.gridheight = 2; 
        // The constraints 'weightx' and 'weighty' distribute extra space to the components, if the space within the panel is greater than the size of the components contained.
        c.weightx = 0.5;
        c.weighty = 1;
        //This adds the component (header) and applies the GridBagConstraints (c).
        page1.add(header, c);

        //This is a play button that navigates to the game portion (screen 3).
        JButton play = new JButton("       Play       ");
        play.setFont (new Font ("Calibri", Font.BOLD, 40));
        play.setBackground (Color.white);
        play.setForeground (new Color(139,0,139));
        play.setActionCommand("3");
        play.addActionListener (this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 6;
        c.gridy = 6;
        c.gridwidth = 2;
        c.gridheight = 1;
        page1.add(play, c); 

        //This is a button that navigates to the information screen.
        JButton info = new JButton("Information");
        info.setFont (new Font ("Calibri", Font.BOLD, 40));
        info.setBackground (Color.white);
        info.setForeground (new Color(139,0,139));
        info.setActionCommand("2");
        info.addActionListener (this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 6;
        c.gridy = 9;
        c.gridwidth = 2;
        c.gridheight = 1;
        page1.add(info, c);

        //The following components are images of jewels for visual appeal.
        JLabel logo1 = new JLabel (createImageIcon ("Gem.png"));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.gridheight = 3;  
        c.weightx = 0.5;
        c.weighty = 1;
        page1.add(logo1, c);  

        JLabel logo2 = new JLabel (createImageIcon ("Gem.png"));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 8;
        c.gridy = 6;
        c.gridwidth = 2;
        c.gridheight = 3;  
        c.weightx = 0.5;
        c.weighty = 1;
        page1.add(logo2, c);

        //The following is a quit button that exits the program, and stops the music.
        JButton quit = new JButton (createImageIcon ("quit.png"));
        quit.setBackground (Color.black);        
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.gridx = 8;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1; 
        c.weightx = 0.5;
        c.weighty = 1;
        quit.setActionCommand("7");
        quit.addActionListener (this);
        page1.add(quit, c);

        JLabel space1 = new JLabel ("");
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 2;
        c.gridheight = 1; 
        page1.add(space1, c);       

        add ("1", page1); 
    }

    public void screen2 ()
    {
        // The screen2 is the second screen of my game, contains information and an audio on/off button, and a button which allows navigation to the main.        
        //The BorderLayout allows me to place two panels in appropriate locations on the page2 JPanel.
        JPanel page2 = new JPanel(new BorderLayout ());

        JPanel middle = new JPanel ();
        //The BoxLayout allows me to place components in a column (vertically) because it's set it Y axis
        middle.setLayout (new BoxLayout(middle, BoxLayout.Y_AXIS));
        middle.setBackground (Color.black);

        JLabel header = new JLabel(createImageIcon ("infotitle.png"));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        middle.add(header);        
        
        //Following, is some information about my game.
        JLabel info = new JLabel("<html><div style='text-align: center;'><br/>Welcome to Bejeweled! <br/>" + 
                " This is a world-famous tile-matching puzzle game. To play, click on two adjacent jewels" + 
                " to have them switch."); 
        info.setFont (new Font ("Calibri", Font.BOLD, 16));         
        info.setForeground (Color.white);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        middle.add(info);

        //This is an image with shows a demonstration of a playing move.        
        JLabel example = new JLabel (createImageIcon ("switch.png"));
        example.setAlignmentX(Component.CENTER_ALIGNMENT);
        middle.add(example);
        
        //Following, is the rest of the information about my game.
        JLabel restinfo = new JLabel ("<html><div style='text-align: center;'><br/> If three or more jewels of the same color are in a row, "  +
                " <br/>they disappear, and new jewels take their place. Every three-in-a-row is 1 point, four-in-a-row 2 points, " + 
                " and five-in-a-row 3 points. Win the game by obtaining 20 points, are you ready?");
        restinfo.setFont (new Font ("Calibri", Font.BOLD, 16));         
        restinfo.setForeground (Color.white);
        restinfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        middle.add(restinfo);

        JPanel side = new JPanel (new GridBagLayout ());
        GridBagConstraints c = new GridBagConstraints();
        side.setBackground (new Color(139,0,139));

        //The following is a quit button that exits the program, and stops the music.       
        JButton quit = new JButton (createImageIcon ("quit.png"));
        quit.setActionCommand("7");
        quit.addActionListener (this);
        quit.setBackground (Color.black);        
        c.anchor = GridBagConstraints.NORTHEAST;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1; 
        c.weightx = 0.5;
        c.weighty = 1;
        side.add (quit, c);

        JButton menu = new JButton("Main Menu");
        menu.setFont (new Font ("Calibri", Font.BOLD, 30));
        menu.setBackground (Color.white);
        menu.setForeground (Color.black);
        menu.setActionCommand("1");
        menu.addActionListener (this);

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 2; 
        c.weightx = 0.5;
        c.weighty = 1;
        side.add(menu, c);
        
        // This button can be clicked to turn on the audio.
        JButton audio = new JButton("Audio On");
        audio.setFont (new Font ("Calibri", Font.BOLD, 25));
        audio.setBackground (Color.white);
        audio.setForeground (Color.black);
        audio.setActionCommand("5");
        audio.addActionListener (this);

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 1; 
        c.weightx = 0.5;
        c.weighty = 1;
        side.add(audio, c);  

        // This button can be clicked to turn off the audio.
        JButton noaudio = new JButton("Audio Off");
        noaudio.setFont (new Font ("Calibri", Font.BOLD, 25));
        noaudio.setBackground (Color.white);
        noaudio.setForeground (Color.black);
        noaudio.setActionCommand("6");
        noaudio.addActionListener (this);

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1; 
        c.weightx = 0.5;
        c.weighty = 1;
        side.add(noaudio, c);

        JLabel space = new JLabel ("");
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 2;
        c.gridheight = 3; 
        c.weightx = 0.5;
        c.weighty = 1;
        side.add(space, c);       

        //The following component is an image of a jewel for visual appeal.
        JLabel logo = new JLabel (createImageIcon ("Gem.png"));
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 15;
        c.gridwidth = 1;
        c.gridheight = 2;  
        c.weightx = 0.5;
        c.weighty = 1;
        side.add(logo, c);

        add ("2", page2);
        page2.add(middle, BorderLayout.CENTER);
        page2.add(side, BorderLayout.EAST);

    }

    public void screen3 ()
    {
        // The screen3 is the third screen of my game, and it is the actual gamescreen which allows navigation to the main menu.        
        JPanel page3 = new JPanel (new BorderLayout ());
        JPanel game = new JPanel (new GridLayout(8,8));

        // This is an integer array that is used to enable checking for matches and also switching jewels.
        jewelicons = new int[8][8];

        // This is a JButton array that is used for the user to click, which enables both the integer elements to switch, as well as the images to switch..        
        jewelbuttons = new JButton[8][8]; 

        // This is an image array that is used to physically view the jewels switching.                
        img = new ImageIcon[6];
        img[0] = createImageIcon("blue.png"); 
        img[1] = createImageIcon("purple.png");
        img[2] = createImageIcon("green.png"); 
        img[3] = createImageIcon("red.png");
        img[4] = createImageIcon("orange.png");
        img[5] = createImageIcon("yellow.png");

        // A nested for loop is used to fill the rows and colums with the buttons, and set an image from the iconindex array to each button.
        // An integer element is assigned to each element in the array to act as an index.
        for(int r = 0; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                int iconindex = (int)(Math.random() * 6);

                jewelicons[r][c] = iconindex;
                jewelbuttons[r][c] = new JButton("");
                jewelbuttons[r][c].setOpaque(true);
                jewelbuttons[r][c].setBorderPainted(false);  
                jewelbuttons[r][c].setIcon(img[iconindex]);
                jewelbuttons[r][c].setBackground ((new Color(139,0,139)));
                // The ActionCommand is set, and in the ActionPerformed, it will perform if there is a comma in the command.
                jewelbuttons[r][c].setActionCommand (""+r+","+c);
                jewelbuttons[r][c].addActionListener (this);

                // The buttons which contain images too, are added to the gameboard.
                game.add(jewelbuttons[r][c]);
            }
        }

        // A for loop that runs through each of the rows and compares the index values, to check for matches.
        for(int currentrow = 0; currentrow < 8; currentrow++) 
        {
            CheckRowForIdenticalJewels(currentrow, false);  
        }

        // A for loop that runs through each of the colums and compares the index values, to check for matches.        
        for(int currentcol = 0; currentcol < 8; currentcol++) 
        {
            CheckColumnForIdenticalJewels(currentcol, false);  
        }           

        JPanel right = new JPanel (new GridBagLayout ());
        //GridBagConstraints are the different constraints set to place each component (widget) in it's desired position.
        GridBagConstraints c = new GridBagConstraints();
        right.setBackground (Color.BLACK);
        JLabel space = new JLabel (" ");
        c.gridx = 0;
        c.weightx = 0.5;
        c.weighty = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        right.add (space, c);

        //The following is a quit button that exits the program, and stops the music.        
        JButton quit = new JButton (createImageIcon ("quit.png"));
        quit.setActionCommand("7");
        quit.addActionListener (this);
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1; 
        right.add(quit, c);

        // This button allows navigation back to the main menu.
        JButton menu = new JButton("Back to Main Menu");
        menu.setFont (new Font ("Calibri", Font.BOLD, 25));
        menu.setBackground (Color.white);
        menu.setForeground (new Color(139,0,139));
        menu.setActionCommand("1");
        menu.addActionListener (this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1; 
        c.ipadx = 0;
        c.ipady = 0;
        right.add(menu, c);

        // The next two JLabels display the amount of points and moves.
        score = new JLabel("Points: "+ totalscore);
        score.setFont (new Font ("Calibri", Font.BOLD, 25));
        score.setForeground (new Color(139,0,139));
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1; 
        c.ipadx = 0;
        c.ipady = 0;
        right.add(score, c);

        moves = new JLabel("Moves: "+ totalmoves);
        moves.setFont (new Font ("Calibri", Font.BOLD, 25));
        moves.setForeground (new Color(139,0,139));
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 2;
        c.gridheight = 1; 
        c.ipadx = 0;
        c.ipady = 0;
        right.add(moves, c);

        //The following component is an image of a jewel for visual appeal.
        JLabel logo = new JLabel (createImageIcon ("Gem.png"));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;     
        right.add(logo, c);

        //The following is a restart button that randomizes the jewels, and resets the points and moves tallies.        
        JButton restart = new JButton("Restart Game");
        restart.setFont (new Font ("Calibri", Font.BOLD, 25));
        restart.setBackground (Color.white);
        restart.setForeground (new Color(139,0,139));
        restart.setActionCommand("8");
        restart.addActionListener (this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 8;
        c.gridwidth = 2;
        c.gridheight = 1; 
        c.ipadx = 0;
        c.ipady = 0;
        right.add(restart, c);

        add ("3", page3);
        //The two JPanels (game and right) are added inside the page3 JPanel.
        page3.add(game, BorderLayout.CENTER);
        page3.add(right, BorderLayout.EAST);

    }

    public void screen4 ()
    {
        // The screen4 is the fourth screen of my game, and it appears when 20 points are reached, it allows navigation to the main menu, and game.                
        JPanel page4 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        page4.setBackground (Color.black);

        JLabel space = new JLabel ("");
        page4.add(space);       
        
        //This is a png header that is the title of my final screen that states "Game Completed".
        JLabel header = new JLabel(createImageIcon ("final.png"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 6;
        c.gridheight = 2; 
        c.weightx = 0.5;
        c.weighty = 1;
        page4.add(header, c);

        //This is a JLabel that displays the amount of moves taken to complete the game.
        moves2 = new JLabel("                    IN " + totalmoves + " MOVES");
        moves2.setFont (new Font ("Phosphate", Font.BOLD, 40));
        moves2.setForeground (Color.white);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 6;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        page4.add(moves2, c); 

        //This button allows navigation the the play screen, and restarts the game (refresh jewels, reset point and move tallies)
        JButton play = new JButton("Play Again");
        play.setFont (new Font ("Calibri", Font.BOLD, 40));
        play.setBackground (Color.white);
        play.setForeground (new Color(139,0,139));
        play.setActionCommand("8");
        play.addActionListener (this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 6;
        c.gridy = 6;
        c.gridwidth = 2;
        c.gridheight = 1;
        page4.add(play, c); 

        //This button allows navigation the the main menu
        JButton mainmenu = new JButton("Back to Main Menu");
        mainmenu.setFont (new Font ("Calibri", Font.BOLD, 40));
        mainmenu.setBackground (Color.white);
        mainmenu.setForeground (new Color(139,0,139));
        mainmenu.setActionCommand("1");
        mainmenu.addActionListener (this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 6;
        c.gridy = 9;
        c.gridwidth = 2;
        c.gridheight = 1;
        page4.add(mainmenu, c);
        
        //The following components are images of jewels for visual appeal.
        JLabel logo1 = new JLabel (createImageIcon ("Gem.png"));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.gridheight = 3;  
        c.weightx = 0.5;
        c.weighty = 1;
        page4.add(logo1, c);  

        JLabel logo2 = new JLabel (createImageIcon ("Gem.png"));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 8;
        c.gridy = 6;
        c.gridwidth = 2;
        c.gridheight = 3;  
        c.weightx = 0.5;
        c.weighty = 1;
        page4.add(logo2, c);
        
        //The following is a quit button that exits the program, and stops the music.        
        JButton quit = new JButton (createImageIcon ("quit.png"));
        quit.setBackground (Color.black);        
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.gridx = 8;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1; 
        c.weightx = 0.5;
        c.weighty = 1;
        quit.setActionCommand("7");
        quit.addActionListener (this);
        page4.add(quit, c);

        JLabel space1 = new JLabel ("");
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 2;
        c.gridheight = 1; 
        page4.add(space1, c);   

        add ("4", page4); 

    }

    public void actionPerformed (ActionEvent e)
    {
        // The actionPerformed indicates what occurs when each action is performed (buttons pressed).
        if (e.getActionCommand().equals ("1")) 
        {
            cdLayout.show (this, "1");
            
            // The resetgame method is called, so the gameboard, points, and moves will reset every time the user navigates to the playing screen.
            resetgame();            
        } 
        else if (e.getActionCommand().equals ("2")) 
        {
            // This uses CardLayout to navigate to the information/settings screen.
            cdLayout.show (this, "2");
        } 
        else if (e.getActionCommand().equals ("3")) 
        {
            // This uses CardLayout to navigate to the game screen.
            cdLayout.show (this, "3");
        } 
        else if (e.getActionCommand().equals ("4")) 
        {
            // This uses CardLayout to navigate to the game completion screen.
            cdLayout.show (this, "4");
        }
        else if (e.getActionCommand().equals("5"))
        {
            // This stops the background music, then starts it again.
            audiostop();
            audio("theme.wav", true);
            audioplay();
        }
        else if (e.getActionCommand().equals("6"))
        {
            // This stops the background music.
            audiostop();
        }
        else if (e.getActionCommand().equals("7"))
        {
            // This stops the background music and quits the program.
            audiostop();
            System.exit(0);
        }
        else if (e.getActionCommand().equals("8"))
        {
            // This uses CardLayout to navigate to the game screen.
            cdLayout.show (this, "3");

            // The resetgame method is called, so the gameboard, points, and moves will reset.
            resetgame();
        }
        else if (e.getActionCommand().contains(","))
        {
            //If the ActionCommand is a button in the array (has a comma, because row, columns), this is what happens.
            // A plethora of actions are performed on the jewel buttons.
            JewelButtonAction(e.getActionCommand());
        }

    }

    public void JewelButtonAction(String selectedjewelposition)
    {
        // This method is the action that occurs when two jewels are selected, the comparing method is called.
        if (firstjewelselected == true && secondjewelselected == true)
        {
            return;
        }
        if (firstjewelselected == false)
        {
            firstjewelselected = true;
            firstjewelposition = selectedjewelposition;
        }
        else
        {
            secondjewelselected = true;
            secondjewelposition = selectedjewelposition;     
            CompareSelectedJewels();
        }
    }

    public void CompareSelectedJewels()
    {
        //This method will compare the two selected jewels to see if they're adjacent. If they are adjacent, the gameboard is checked for matches.
        boolean adjacentjewelsselected = false;
        int firstjewelrow = -1, firstjewelcolumn = -1;
        int secondjewelrow = -1, secondjewelcolumn = -1;

        // The following two if statements split the positions x and y coordinates, so we get a row and column number.
        // This splits the first jewel's position, and sets a variable for the row and column numbers.
        String[] splitted1 = firstjewelposition.split(",");
        if (splitted1.length == 2)
        {
            firstjewelrow = Integer.valueOf(splitted1[0]);
            firstjewelcolumn = Integer.valueOf(splitted1[1]);
        }

        // This splits the second jewel's position, and sets a variable for the row and column numbers.
        String[] splitted2 = secondjewelposition.split(",");
        if (splitted2.length == 2)
        {
            secondjewelrow = Integer.valueOf(splitted2[0]);
            secondjewelcolumn = Integer.valueOf(splitted2[1]);
        }

        if (firstjewelrow == secondjewelrow && (firstjewelcolumn == secondjewelcolumn+1 || firstjewelcolumn == secondjewelcolumn -1))
        // If the jewels are on the same row, and the their column's are beside each other, adjacence is confirmed.
        {
            adjacentjewelsselected = true;
        }
        else if (firstjewelcolumn == secondjewelcolumn && (firstjewelrow == secondjewelrow+1 || firstjewelrow == secondjewelrow-1))
        // If the jewels are in the same column, and the their row's are beside each other, adjacence is confirmed.        
        {
            adjacentjewelsselected = true;
        }

        if (adjacentjewelsselected == true)
        {
            SwitchJewels(firstjewelrow, firstjewelcolumn, secondjewelrow, secondjewelcolumn);
            //Now that we know  that the jewels were adjacent, the SwitchJewels method is called and the jewels are switched.

            //The rows and colums that both jewels are located in are checked.
            CheckRowForIdenticalJewels(firstjewelrow, true);
            CheckColumnForIdenticalJewels(firstjewelcolumn, true);  
            CheckRowForIdenticalJewels(secondjewelrow, true);
            CheckColumnForIdenticalJewels(secondjewelcolumn, true); 

            //These for loops check each row an column for matches.
            for(int currentrow = 0; currentrow < 8; currentrow++) 
            {
                CheckRowForIdenticalJewels(currentrow, false);  
            }

            for(int currentcol = 0; currentcol < 8; currentcol++) 
            {
                CheckColumnForIdenticalJewels(currentcol, false);  
            }   

        }

        //Here, I am resetting the selections.
        firstjewelselected = false;
        secondjewelselected = false;
        firstjewelposition = "";
        secondjewelposition = "";    

    }

    public void SwitchJewels(int firstjewelrow, int firstjewelcolumn,int secondjewelrow, int secondjewelcolumn)
    {
        int firstIconIndex;
        int secondIconIndex;
        //This stores the buttons and icon indexes at the selected first and second positions to the local variables temporarily.
        firstIconIndex = jewelicons[firstjewelrow][firstjewelcolumn];
        secondIconIndex = jewelicons[secondjewelrow][secondjewelcolumn];

        jewelbuttons[firstjewelrow][firstjewelcolumn].setIcon(img[secondIconIndex]);
        jewelbuttons[secondjewelrow][secondjewelcolumn].setIcon(img[firstIconIndex]);

        jewelicons[firstjewelrow][firstjewelcolumn] = secondIconIndex; 
        jewelicons[secondjewelrow][secondjewelcolumn]= firstIconIndex;

        // Since a move is made, the moves tally increases by 1.
        totalmoves = totalmoves + 1 ;

        // The moves JLabel's text is also set to the new moves tally.
        if (moves != null)
        {
            moves.setText("Moves: "+ totalmoves);
        }

    }

    public void CheckRowForIdenticalJewels(int selectedrow, boolean addscore)
    {
        int previconindex = -1, currenticonindex = -1;
        int matchediconscount = 0; 
        String matchedcolumnslist = "";
        boolean randomizeicons = false;
        boolean checkforRandomizing = false;
        //The following for loop goes through every colums in the selected row to check for 3-in-a-row (or more) adjacent matched jewels.
        for(int col = 0; col < 8; col++)
        {            
            currenticonindex =  jewelicons[selectedrow][col];            

            if (currenticonindex == previconindex )
            {
                if (matchediconscount == 0)
                {
                    //It's the very first match - Add 1 count for the previous column icon and 1 count for the current column icon.
                    matchediconscount = matchediconscount + 2;  

                    //It's the very first match - Add the previous column and the current column to the matched columns list.
                    matchedcolumnslist = ""+ (col-1) + "," + col;
                }
                else 
                {
                    //Not the very first match - Consecutive matches - So, add just 1 count for the current match .
                    matchediconscount = matchediconscount +1;

                    //Not the very first match - Consecutive matches - So, add just the current column to the matched columns list.
                    matchedcolumnslist = matchedcolumnslist + "," + col;  
                }      

                if (col == 7) 
                //last column and match happened
                {
                    checkforRandomizing = true;
                }
            }
            else 
            {
                checkforRandomizing = true;
            }

            if (checkforRandomizing == true )
            {
                checkforRandomizing = false;

                if (matchediconscount > 2)
                {
                    //Switch case is a Java control structure.
                    switch (matchediconscount) 
                    {
                        case 3: 
                        if (addscore == true)
                        {
                            //Match of 3 - Add 1 point.
                            totalscore = totalscore + 1;                            
                        }
                        randomizeicons = true;
                        break;

                        case 4: 
                        if (addscore == true)
                        {
                            //Match of 4 - Add 2 points.
                            totalscore = totalscore + 2;
                        }
                        randomizeicons = true;
                        break;     

                        case 5:  
                        if (addscore == true)
                        {
                            //Match of 5 - Add 3 points.
                            totalscore = totalscore + 3;
                        }
                        randomizeicons = true;
                        break;

                        case 6:
                        if (addscore == true)
                        {
                            //Match of 6 - Add 4 points.
                            totalscore = totalscore + 4;
                        }
                        randomizeicons = true;
                        break;

                        case 7:
                        if (addscore == true)
                        {
                            //Match of 7 - Add 5 points.
                            totalscore = totalscore + 5;
                        }
                        randomizeicons = true;
                        break;

                        case 8:
                        if (addscore == true)
                        {
                            //Match of 8 - Add 6 points.
                            totalscore = totalscore + 6;
                        }
                        randomizeicons = true;
                        break;
                    }
                    if (randomizeicons == true)
                    {
                        randomizeicons = false;                        

                        String[] matchedcolumns = matchedcolumnslist.split(",");

                        if (addscore == true)
                        {
                            for (int i= 0; i < matchedcolumns.length; i++)  
                            { 
                                int matchedcolumn = Integer.valueOf(matchedcolumns[i]);                                                                 
                                jewelicons[selectedrow][matchedcolumn] = -1;                                
                                jewelbuttons[selectedrow][matchedcolumn].setIcon(null);
                            }

                            // The JOptionPane makes the user interface active.
                            UIManager um = new UIManager();
                            um.put("OptionPane.messageForeground", Color.white);
                            UIManager.put("OptionPane.background", new Color(139,0,139));
                            UIManager.put("Panel.background", Color.black);
                            JOptionPane.showMessageDialog(window,"Match Made!" );
                            //Thread.sleep is used as a way to animate, and keep the matched spots purple for 500 milliseconds.
                            try
                            {   
                                Thread.sleep (500);
                            }
                            catch (InterruptedException m)
                            {
                                ;
                            }
                        }                        
                        // The following for loop randomized new jewels into the spots of the ones that were matched. 
                        for (int i= 0; i < matchedcolumns.length; i++) 
                        { 

                            int matchedcolumn = Integer.valueOf(matchedcolumns[i]);
                            int newiconindex = (int)(Math.random() * 6);                                                                              

                            jewelicons[selectedrow][matchedcolumn] = newiconindex;                            
                            jewelbuttons[selectedrow][matchedcolumn].setIcon(img[newiconindex]);                                                     

                        }                    
                    }                    
                }
                matchediconscount = 0;
                matchedcolumnslist = "";
            }
            previconindex = currenticonindex;
        }
        // This if statement adds to the points tally.
        if (score != null && addscore && totalscore > 0)
        {
            score.setText("Points: "+ totalscore);
        }
        // This checks if the score has reached 20, if it has, navigation happens to the game completion screen.
        if (totalscore > 19)
        {
            if (moves2 != null)
            {
                moves2.setText("IN " + totalmoves + " MOVES");
            }
            cdLayout.show (this, "4");            
        }
    }

    public void CheckColumnForIdenticalJewels(int selectedcolumn, boolean addscore)
    {
        int previconindex = -1, currenticonindex = -1;
        int matchediconscount = 0; 
        String matchedrowslist = "";
        boolean randomizeicons = false;
        boolean checkforRandomizing = false;

        //The following for loop goes through every row in the selected column to check for 3-in-a-row or more adjacent matched jewels.
        for(int row = 0; row < 8; row++)
        {            
            currenticonindex =  jewelicons[row][selectedcolumn];            

            // The iconindex is checked to see if the other elements above are the same, if they are the same, the next above icon is checked.
            if (currenticonindex == previconindex )
            {
                if (matchediconscount == 0)
                {
                    //It's the very first match - Add 1 count for the previous row icon and 1 count for the current row icon.
                    matchediconscount = matchediconscount + 2;  

                    //It's the very first match - Add the previous row and the current row to the matched rows list.
                    matchedrowslist = ""+ (row-1) + "," + row;
                }
                else 
                {
                    //Not the very first match - Consecutive matches - So, add just 1 count for the current match. 
                    matchediconscount = matchediconscount +1;

                    //Not the very first match - Consecutive matches - So, add just the current row to the matched rows list.
                    matchedrowslist = matchedrowslist + "," + row;  
                }      

                if (row == 7) 
                //last row and match happened
                {
                    checkforRandomizing = true;
                }
            }
            else 
            {
                checkforRandomizing = true;
            }

            if (checkforRandomizing == true )
            {
                checkforRandomizing = false;

                if (matchediconscount > 2)
                {
                    switch (matchediconscount) 
                    {
                        case 3: 
                        if (addscore == true)
                        {
                            //Match of 3 - Add 1 point
                            totalscore = totalscore + 1;                            
                        }
                        randomizeicons = true;
                        break;

                        case 4: 
                        if (addscore == true)
                        {
                            //Match of 4 - Add 2 points
                            totalscore = totalscore + 2;
                        }
                        randomizeicons = true;
                        break;     

                        case 5:  
                        if (addscore == true)
                        {
                            //Match of 5 - Add 3 points
                            totalscore = totalscore + 3;
                        }
                        randomizeicons = true;
                        break;

                        case 6:
                        if (addscore == true)
                        {
                            //Match of 6 - Add 4 points
                            totalscore = totalscore + 4;
                        }
                        randomizeicons = true;
                        break;

                        case 7:
                        if (addscore == true)
                        {
                            //Match of 7 - Add 5 point
                            totalscore = totalscore + 5;
                        }
                        randomizeicons = true;
                        break;

                        case 8:
                        if (addscore == true)
                        {
                            //Match of 8 - Add 6 point
                            totalscore = totalscore + 6;
                        }
                        randomizeicons = true;
                        break;
                    }
                    if (randomizeicons == true)
                    {
                        randomizeicons = false;                        

                        String[] matchedrows = matchedrowslist.split(",");

                        if (addscore == true)
                        {
                            for (int i= 0; i < matchedrows.length; i++)  
                            { 
                                int matchedrow = Integer.valueOf(matchedrows[i]);                                                                 
                                jewelicons[matchedrow][selectedcolumn] = -1;                                
                                jewelbuttons[matchedrow][selectedcolumn].setIcon(null);
                            }
                            // The JOptionPane makes the user interface active.
                            UIManager um = new UIManager();
                            um.put("OptionPane.messageForeground", Color.white);
                            UIManager.put("OptionPane.background", new Color(139,0,139));
                            UIManager.put("Panel.background", Color.black);
                            JOptionPane.showMessageDialog(window,"Match Made!" );
                            //Thread.sleep is used as a way to animate, and keep the matched spots purple for 500 milliseconds.
                            try
                            {   
                                Thread.sleep (500);
                            }
                            catch (InterruptedException m)
                            {
                                ;
                            }
                        }
                        // The following for loop randomized new jewels into the spots of the ones that were matched. 
                        for (int i= 0; i < matchedrows.length; i++) 
                        { 
                            int matchedrow = Integer.valueOf(matchedrows[i]);
                            int newiconindex = (int)(Math.random() * 6);

                            jewelicons[matchedrow][selectedcolumn] = newiconindex;                            
                            jewelbuttons[matchedrow][selectedcolumn].setIcon(img[newiconindex]);                                   
                        }                    
                    }                    
                }
                matchediconscount = 0;
                matchedrowslist = "";
            }
            previconindex = currenticonindex;
        }

        // The following if statement add to the points.
        if (score != null && addscore && totalscore > 0)
        {
            score.setText("Points: "+ totalscore);
        }

        // This checks if the score has reached 20, if it has, navigation happens to the game completion screen.
        if (totalscore > 19)
        {
            cdLayout.show (this, "4");            
        }
    }

    public void randomizealljewels()
    {
        // Nested for loops are utilized to randomize the rows and columns of the jewels.
        for(int r = 0; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                int iconindex = (int)(Math.random() * 6);
                jewelicons[r][c] = iconindex;
                jewelbuttons[r][c].setIcon(img[iconindex]);

            }
        }
    }

    public void resetgame()
    {
        //This method resets the points and move tallies to zero and randomizes all the jewels on the gameboard once again.
        totalscore = 0;
        totalmoves = 0;
        firstjewelselected = false;
        secondjewelselected = false;     
        firstjewelposition = "";
        secondjewelposition = "";        

        //The following sets the moves and points display to 0 again.
        if (moves != null)
        {
            moves.setText("Moves: "+ totalmoves);
        }
        if (score != null)
        {
            score.setText("Points: "+ totalscore);
        }
        //The jewels are randomized
        randomizealljewels();

        //They are checked for matches again.
        for(int currentrow = 0; currentrow < 8; currentrow++) 
        {
            CheckRowForIdenticalJewels(currentrow, false);  
        }

        for(int currentcol = 0; currentcol < 8; currentcol++) 
        {
            CheckColumnForIdenticalJewels(currentcol, false);  
        }   
    }

    protected static ImageIcon createImageIcon (String path)
    {
        //The createImageIcon method returns an image if it is found, and returns an error message if the file isn't found.
        java.net.URL imgURL = Bejeweled.class.getResource( path);
        if (imgURL != null)
        {
            return new ImageIcon (imgURL);
        } 
        else 
        {
            System.err.println( "Couldn't find file: " + path);
            return null;
        }
    }
}