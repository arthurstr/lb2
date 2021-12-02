package com.arthur;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main extends JFrame{
    private static final int WIDTH = 400;
    private static final int Height = 320;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textForPerem;

    private Double result;

    private Double Perem1=0.0;
    private Double Perem2=0.0;
    private Double Perem3=0.0;

    private JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();

    private ButtonGroup radioButtons2 = new ButtonGroup();
    private Box peremType = Box.createHorizontalBox();
    private JTextField textFieldPerem;

    private int formulaId = 1;
    private int PeremId=1;

    public Double calculate1(Double x, Double y, Double z){
        return (Math.pow(Math.exp(Math.pow(1+z,2))+Math.cos(Math.PI*Math.pow(y,3)),1/4))/(Math.pow(Math.cos(Math.pow(Math.E,x))+Math.sqrt(1/x)+Math.pow(Math.E,Math.pow(x,2)),Math.sin(x)));
    }
    public Double calculate2(Double x, Double y, Double z){

        return (Math.pow(Math.sin(Math.pow(z,y)),2)/(Math.sqrt(1+Math.pow(x,3))));
    }
    public void addRadioButton (String buttonName, final int formulaId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ev) {
                Main.this.formulaId=formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    public void addRadioButton2 (String buttonName, final int PeremId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ev) {
                Main.this.PeremId=PeremId;
                if (PeremId == 1) {
                    textFieldPerem.setText(Perem1.toString());
                }
                else {
                    if (PeremId ==2){
                        textFieldPerem.setText(Perem2.toString());
                    }
                    else {
                        if (PeremId ==3){
                            textFieldPerem.setText(Perem3.toString());
                        }
                    }
                }
            }
        });
        radioButtons2.add(button);
        peremType.add(button);
    }
    public Main() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Форм. 1", 1);
        addRadioButton("Форм. 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        //hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
       // hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        //hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        //hboxVariables.add(Box.createHorizontalGlue());
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 50);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    if (formulaId==1)
                        result = calculate1(x, y,z);
                    else
                        result = calculate2(x, y,z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PeremId == 1) {
                    Perem1+=result;
                    textFieldPerem.setText(Perem1.toString());
                }
                else {
                    if (PeremId ==2){
                        Perem2+=result;
                        textFieldPerem.setText(Perem2.toString());
                    }
                    else {
                        if (PeremId ==3){
                            Perem3+=result;
                            textFieldPerem.setText(Perem3.toString());
                        }
                    }
                }
            }
        });
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PeremId == 1) {
                    Perem1=0.0;
                    textFieldPerem.setText(Perem1.toString());
                }
                else {
                    if (PeremId ==2){
                        Perem2=0.0;
                        textFieldPerem.setText(Perem2.toString());
                    }
                    else {
                        if (PeremId ==3){
                            Perem3=0.0;
                            textFieldPerem.setText(Perem3.toString());
                        }
                    }
                }
            }
        });
        Box Mbox = Box.createHorizontalBox();
        Mbox.add(Box.createHorizontalGlue());
        Mbox.add(buttonM);
        Mbox.add(Box.createHorizontalStrut(10));
        Mbox.add(buttonMC);
        Mbox.add(Box.createHorizontalGlue());
        Mbox.setBorder(
                BorderFactory.createLineBorder(Color.PINK));
        JLabel labelPerem= new JLabel("Значение переменной:");
        textFieldPerem = new JTextField("0",15);
        textFieldPerem.setMaximumSize(textFieldPerem.getPreferredSize());
        Box Perem = Box.createHorizontalBox();
        Perem.add(Box.createHorizontalGlue());
        Perem.add(labelPerem);
        Perem.add(Box.createHorizontalStrut(10));
        Perem.add(textFieldPerem);
        Perem.add(Box.createHorizontalGlue());
        Perem.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        peremType.add(Box.createHorizontalGlue());
        addRadioButton2("Пер. 1:",1);
        addRadioButton2("Пер. 2:",2);
        addRadioButton2("Пер. 3:",3);
        radioButtons2.setSelected(radioButtons2.getElements().nextElement().getModel(), true);
        peremType.add(Box.createHorizontalGlue());
        peremType.setBorder(BorderFactory.createLineBorder(Color.GREEN));


        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Perem);
        contentBox.add(peremType);
        contentBox.add(Mbox);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);

    }
    // Главный метод класса
    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500,500);
    }
}