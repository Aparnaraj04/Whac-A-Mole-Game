import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class WhacaMole {
    int boardwidth=600;
    int boardheight=650;
    JFrame frame=new JFrame("Mario: Whac a Mole");
    JLabel textLabel=new JLabel();
    JPanel textJPanel=new JPanel();
    JPanel boardPanel=new JPanel();
    JButton[] board=new JButton[9]; 
    ImageIcon jerryIcon;
    ImageIcon tomIcon;
    JButton currJerryTile;
    JButton currTomTile;
    Random random=new Random();
    Timer setJerrTimer;
    Timer setTomTimer;
    int Score=0;

    WhacaMole(){
       // frame.setVisible(true);
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        textLabel.setFont(new Font("Arial",Font.PLAIN,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Score: 0");
        textLabel.setOpaque(true);

        textJPanel.setLayout(new BorderLayout());
        textJPanel.add(textLabel);
        frame.add(textJPanel,BorderLayout.NORTH);
        boardPanel.setLayout(new GridLayout(3,3));
        //boardPanel.setBackground(Color,black);
        frame.add(boardPanel);
        Image tomImage= new ImageIcon(getClass().getResource("/tom.jpg")).getImage();
        tomIcon=new ImageIcon(tomImage.getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH));
        Image jerryImage= new ImageIcon(getClass().getResource("/jerry.jpg")).getImage();
        jerryIcon=new ImageIcon(jerryImage.getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH));
        Score=0;
        for(int i=0;i<9;i++){
            JButton tile=new JButton();
            board[i]=tile;
            boardPanel.add(tile);
            tile.setFocusable(false);
            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton tile=(JButton)e.getSource();
                    if(tile==currJerryTile){
                        Score+=10;
                        textLabel.setText("Score:"+Integer.toString(Score));
                    }
                    else if(tile==currTomTile){
                        textLabel.setText("Game Over:"+Integer.toString(Score));
                        setJerrTimer.stop();
                        setTomTimer.stop();
                        for(int i=0;i<9;i++){
                            board[i].setEnabled(false);
                        }
                    }
                }
            });
        }
        setJerrTimer=new Timer(1800,new ActionListener() {
           public void actionPerformed(ActionEvent e){
            if(currJerryTile!=null){
                currJerryTile.setIcon(null);
                currJerryTile=null;
            }
            //if (currTomTile == tile) return;
            int num=random.nextInt(9);
            JButton tile=board[num];
            if (currTomTile == tile) return;
            currJerryTile=tile;
            currJerryTile.setIcon(jerryIcon);
           } 
        });
        setTomTimer=new Timer(1500,new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(currTomTile!=null){
                    currTomTile.setIcon(null);
                    currTomTile=null;
                }
                //if(currJerryTile==tile)return;
                int num=random.nextInt(9);
                JButton tile=board[num];
                if(currJerryTile==tile)return;
                currTomTile=tile;
                currTomTile.setIcon(tomIcon);
            }
        });
        setJerrTimer.start();
        setTomTimer.start();
        frame.setVisible(true);
    }
}

