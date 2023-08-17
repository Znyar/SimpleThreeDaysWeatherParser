package com.znyar;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Form extends JFrame {

    public Form() throws IOException {

        super("Погода");
        super.setBounds(400, 100, 720, 480);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();

        container.setLayout(new GridLayout(1,2,0,0));

        Element data = Parser.getData();
        Elements dates = Parser.getDates(data);
        Elements periods = Parser.getPeriods(data);
        Elements temperatures = Parser.getTemperatures(data);

        int count = 0;

        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Element date : dates) {

            listModel.addElement(date.text());
            //container.add(new JLabel(date.text()));

            for (int i = count; i < periods.size(); i++) {

                //container.add(new JLabel(periods.get(i).text()));
                //container.add(new JLabel(temperatures.get(i).select("div").attr("title")));
                //container.add(new JLabel(temperatures.get(i).text()));

                listModel.addElement(periods.get(i).text() + " " + temperatures.get(i).select("div").attr("title") + " " + temperatures.get(i).text());

                /*System.out.println(periods.get(i).text() + " " + temperatures.get(i).select("div").attr("title")
                        + " " + temperatures.get(i).text());*/
                if (periods.get(i).text().equals("23:00")) {
                    count = i + 1;
                    break;
                }
            }
        }

        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        container.add(scrollPane);

    }

}
