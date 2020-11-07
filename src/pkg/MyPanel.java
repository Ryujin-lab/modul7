package pkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MyPanel extends JPanel {
   JLabel beliLabel, literLabel, BayarLabel;
   JTextField beli, liter, bayar;
   JButton isi, stop, reset, load;

   int beliBensin = 0;
   float literBensin = 0;
   int BayarBensin = 0;

   class MyThread extends Thread {
      @Override
      public void run() {
         try {
            if (beli.getText().equals("0")) {
               while (true) {
                  Thread.sleep(50);
                  literBensin += 0.01;
                  BayarBensin += 100;
                  liter.setText("" + String.format("%.02f", literBensin));
                  bayar.setText("" + BayarBensin);
               }
            }
            else {
               int cek = Integer.parseInt(beli.getText());
               while (BayarBensin < cek) {
                  Thread.sleep(50);
                  literBensin += 0.01;
                  BayarBensin += 100;
                  liter.setText("" + String.format("%.02f", literBensin));
                  bayar.setText("" + BayarBensin);
               }

            }
         } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   MyPanel() {
      setBounds(0, 0, 400, 600);
      setLayout(null);

      beliLabel = new JLabel("Beli (Rp)");
      literLabel = new JLabel("Liter");
      BayarLabel = new JLabel("Bayar (Rp)");

      beliLabel.setBounds(10, 10, 200, 30);
      literLabel.setBounds(10, 60, 200, 30);
      BayarLabel.setBounds(10, 100, 200, 30);

      beli = new JTextField("" + beliBensin);
      liter = new JTextField("" + literBensin);
      bayar = new JTextField("" + BayarBensin);

      MyThread thread = new MyThread();

      beli.setBounds(120, 10, 200, 30);
      liter.setBounds(120, 60, 200, 30);
      bayar.setBounds(120, 100, 200, 30);

      isi = new JButton("Isi");
      isi.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            if (arg0.getSource() == isi) {
               isi.setEnabled(false);
               stop.setEnabled(true);
               if (!thread.isAlive()) {
                  thread.start();
               }
               else{
                  thread.resume();
               }

            }

         }

      });

      stop = new JButton("Stop");
      stop.setEnabled(false);
      stop.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            isi.setEnabled(true);
            stop.setEnabled(false);
            thread.suspend();
         }
         
      });

      reset = new JButton("Reset");
      reset.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            beliBensin = 0;
            literBensin = 0;
            BayarBensin = 0;
            
            beli.setText ("" + beliBensin);
            liter.setText("" + literBensin);
            bayar.setText("" + BayarBensin);
         }
         
      });

      load = new JButton("Load");
      load.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            MySQL.executeUpdateQuerry("insert into jurnal (bayar, liter) values ('"+BayarBensin+"','"+literBensin+"') ");
         }
         
      });

      isi.setBounds(10,150,80,30);
      stop.setBounds(100,150,80,30);
      reset.setBounds(190,150,80,30);
      load.setBounds(280,150,80,30);

      String[] colHeadings = {"Bayar","Liter"};
      int numRows = 0 ;
      DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length) ;
      model.setColumnIdentifiers(colHeadings);
      JTable table = new JTable(model);
   

      JScrollPane sc = new JScrollPane(table);
      sc.setBounds(10,200, 380, 250);

      
      add(sc);
      add(beliLabel);
      add(literLabel);
      add(BayarLabel);
      add(isi);
      add(stop);
      add(reset);
      add(load);
      add(beli);
      add(liter);
      add(bayar);

   }
}
