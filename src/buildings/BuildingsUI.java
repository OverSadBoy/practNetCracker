package buildings;

import buildings.sup.Buildings;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuildingsUI extends JFrame {
    private static final int DWELLING = 0;
    private static final int OFFICE = 1;
    private JPanel pane;
    private JTextArea buildText;
    private JTextArea floorText;
    private JTextArea spaceText;
    private JPanel buildS;

    public BuildingsUI() {
        super("Building");
        initView();
    }

    private void initView() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu(this)[0]);
        menuBar.add(createFileMenu(this)[1]);
        setSize(800, 600);
        setLocationRelativeTo(null);
        buildS.setLayout(new BoxLayout(buildS, BoxLayout.Y_AXIS));
        setJMenuBar(menuBar);
        add(pane);
        setVisible(true);
    }

    private JMenu[] createFileMenu(Component component) {
        JMenu[] file = new JMenu[2];
        JMenu file1 = new JMenu("Файл");
        JMenu file2 = new JMenu("Look&Feel");
        JMenuItem openDwel = new JMenuItem("Открыть здание");
        JMenuItem openOffice = new JMenuItem("Открыть офис");
        JMenuItem metal = new JMenuItem("metal");
        JMenuItem temno = new JMenuItem("temno");
        JMenuItem belo = new JMenuItem("belo");
        openDwel.addActionListener(arg0 -> openBuilding(component, DWELLING));
        openOffice.addActionListener(arg0 -> openBuilding(component, OFFICE));
        metal.addActionListener(arg0 -> changStyle(component, 0));
        temno.addActionListener(arg0 -> changStyle(component, 1));
        belo.addActionListener(arg0 -> changStyle(component, 2));
        file1.add(openDwel);
        file1.add(openOffice);
        file2.add(metal);
        file2.add(temno);
        file2.add(belo);
        file[0] = file1;
        file[1] = file2;
        return file;
    }

    private void openBuilding(Component component, int type) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\SadBoy\\IdeaProjects\\practNetC\\src"));
        chooser.showOpenDialog(component);
        setVisible(true);
        if (type == DWELLING) {
            try {
                inflateView(Buildings.readBuilding(new FileReader(chooser.getSelectedFile())), DWELLING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                inflateView(Buildings.readBuilding(new FileReader(chooser.getSelectedFile())), OFFICE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void inflateView(Building building, int type) {
        System.out.println(building.getSpacesNum());
        if (type == DWELLING) {
            buildText.setText("Dwelling:\n" + building.getFloorsNum() + " floors\n" + building.getSpacesArea() + " all area");
        } else
            buildText.setText("Office:\n" + building.getFloorsNum() + " floors\n" + building.getSpacesArea() + " all area");
        for (int i = 0, k = 0; i < building.getFloorsNum(); i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.setSize(300, 60);
            panel.setVisible(true);
            for (int j = 0; j < building.getFloor(i).getSpaceNum(); j++, k++) {
                JButton button = new JButton(String.valueOf(k));
                int finalI = i;
                int finalK = k;
                button.addActionListener(actionEvent -> {
                    floorText.setText(finalI + " № floor\n" + building.getFloor(finalI).getSpaceNum() + " space on floor\n" + building.getFloor(finalI).getAreas() + " all area");
                    spaceText.setText(finalK + " № in building\n" + building.getSpace(finalK).getRoom() + " room num\n" + building.getSpace(finalK).getArea() + " area");
                });
                panel.add(button);
            }
            buildS.add(panel);
        }
    }

    private void changStyle(Component component, int value) {
        String s;
        switch (value) {
            case 0:
                s = "javax.swing.plaf.metal.MetalLookAndFeel";
                break;
            case 1:
                s = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                break;
            case 2:
                s = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                break;
            default:
                s = UIManager.getSystemLookAndFeelClassName();
        }
        try {
            UIManager.setLookAndFeel(s);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            JOptionPane.showConfirmDialog(component,"trouble which scheme");
        }
        SwingUtilities.updateComponentTreeUI(getContentPane());
    }
}
