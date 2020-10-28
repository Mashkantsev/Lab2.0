package bsu.rfe.Mashkantsev.java.group6.Lab2.b10;

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
import static java.lang.Math.*;

@SuppressWarnings("serial")

public class MainFrame extends JFrame {
    private static final int Width = 800;
    private static final int Height = 400;
    private  JTextField textFieldResult;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private double memory1,memory2,memory3;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId= 1;
    private JTextField memoryTextField;
    private int memoryId= 1;
    double result = 0;
    private ButtonGroup radioMemoryButtons = new ButtonGroup();
    private Box hboxMemoryType = Box.createHorizontalBox();

    public double calculate1 (double x, double y,double z ){
        if(sin(PI*z*z)+sin(x)+log(z*z)+x*x+exp(cos(z*x)) == 0){
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Нельзя делить на 0", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if(y == 0 || z == 0){
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Нельзя брать логарифм от 0", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        return (sin(PI*y*y) + log(y*y))/(sin(PI*z*z)+sin(x)+log(z*z)+x*x+exp(cos(z*x)));
    }
    public double calculate2 (double x, double y,double z ){
        if(x == -1){
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Нельзя делить на 0)", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if(x <= -1){
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Выражение под корнем должно быть положительным", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        return pow(sin(pow(z,y)),2)/pow(1+pow(x,3),1/3);
    }

    private void addRadioButton(String buttonName, final int formulaId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener (new ActionListener(){
            public void actionPerformed (ActionEvent ev){
                MainFrame.this.formulaId= formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    private void addMemoryRadioButton (String buttonName, int memoryId)	{
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)	{
                MainFrame.this.memoryId = memoryId;
                if (memoryId == 1)	{
                    memoryTextField.setText(toString().format("%-10.10f%n", (memory1)));
                }
                if (memoryId == 2){
                    memoryTextField.setText(toString().format("%-10.10f%n", (memory2)));
                }
                if (memoryId == 3){
                    memoryTextField.setText(toString().format("%-10.10f%n", (memory3)));
                }
            }
        });
        radioMemoryButtons.add(button);
        hboxMemoryType.add(button);
    }

    public MainFrame(){
        super("Вычисление формулы");
        setSize(Width, Height);
        Toolkit kit= Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width-Width)/2, (kit.getScreenSize().height-Height)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JLabel labelForX = new JLabel("x :"); // подпись
        textFieldX= new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("y :");
        textFieldY= new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("z :");
        textFieldZ= new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables= Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
        JLabel labelForResult= new JLabel("Результат:");
        textFieldResult= new JTextField("0",30);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult= Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        Box actions=Box.createHorizontalBox();

        JButton buttonCalculation= new JButton("Вычислить");
        buttonCalculation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try{
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    if(formulaId==1){
                        result = calculate1(x,y,z);
                    }
                    else
                        result= calculate2(x,y,z);
                    textFieldResult.setText(Double.toString(result));
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset= new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        actions.add(Box.createHorizontalGlue());
        actions.add(buttonCalculation);
        actions.add(Box.createHorizontalStrut(20));
        actions.add(buttonReset);
        actions.add(Box.createHorizontalGlue());
        hboxMemoryType.add(Box.createHorizontalGlue());
        
        addMemoryRadioButton("Переменная 1",1);
        addMemoryRadioButton("Переменная 2",2);
        addMemoryRadioButton("Переменная 3",3);
        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());
        Box memory_result=Box.createHorizontalBox();
        memory_result.add(Box.createHorizontalGlue());
        JButton MC=new JButton("MC");
        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (memoryId == 1){
                    memory1 = 0;
                }
                if (memoryId == 2)	{
                    memory2 = 0;
                }
                if (memoryId == 3)	{
                    memory3 = 0;
                }
                memoryTextField.setText("0");
            }
        });
        JButton M_plus=new JButton("M+");
        M_plus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev) {
                try{
                    Double result = Double.parseDouble(textFieldResult.getText());

                    if (memoryId == 1) 	{
                        memory1 += result;
                        memoryTextField.setText(toString().format("%-10.10f%n", (memory1)));
                    }
                    if (memoryId == 2)	{
                        memory2 += result;
                        memoryTextField.setText(toString().format("%-10.10f%n", (memory2)));
                    }
                    if (memoryId == 3)	{
                        memory3 += result;
                        memoryTextField.setText(toString().format("%-10.10f%n", (memory3)));
                    }

                }catch (NumberFormatException ex)
                { JOptionPane.showMessageDialog(MainFrame.this,
                        "Ошибка в формате записи числа с плавающей точкой", "" +
                                "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        memoryTextField = new JTextField("0.0000000000",10);
        memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());
        memory_result.add(MC);
        memory_result.add(Box.createHorizontalStrut(20));
        memory_result.add(memoryTextField);
        memory_result.add(Box.createHorizontalStrut(20));
        memory_result.add(M_plus);
        memory_result.add(Box.createHorizontalGlue());

        Box contentBox= Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxResult);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(actions);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxMemoryType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(memory_result);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}
