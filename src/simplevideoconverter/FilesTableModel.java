package simplevideoconverter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanki23
 */
public class FilesTableModel extends DefaultTableModel {

    private int columntoblock[], rowtoblock[];
    private int countcelltoblock = 0, countinsert, countcelltorelease = 0;
    private int[] cellinrow, cellincolumn;
    private Object[] data = null;
    private String[] columnNames = {"Tên File", "Kích Thước", "Video Rate", "Audio Rate", "Frame Rate", "Volumen Normalize", "Đường Dẫn", "Xuất Ra", "Trạng Thái"};

    public FilesTableModel() {
        for (int c = 0; c < columnNames.length; c++) {
            this.addColumn(columnNames[c]);
        }
    }

    public void AddFiles(File[] files, String outputFormat) {

        for (int c = 0; c < files.length; c++) {
            double getmb = (double) (files[c].length() / 1024) / 1024;
            getmb = roundTwoDecimals(getmb);
            data = new Object[]{files[c].getName(), getmb + " mb", 2000, "192", "NTSC - 29.97", true, files[c].getPath(), outputFormat, "Đang chờ"};
            this.addRow(data);
        }
        this.cellinrow = new int[this.getRowCount()];
        this.cellincolumn = new int[this.getColumnCount()];
        this.countinsert = this.getRowCount();
    }

    public void addRow() {
        this.countinsert++;
        this.insertRow(this.getRowCount(), new Object[]{this.countinsert});
    }

    public void SetColumnToBlock(int column[]) {
        columntoblock = column;
    }

    public void SetRowToBlock(int row[]) {
        rowtoblock = row;
    }

    public void SetCellToBlock(int cellinrow, int cellincolumn) {
        this.cellinrow[countcelltoblock] = cellinrow;
        this.cellincolumn[countcelltoblock] = cellincolumn;
        countcelltoblock++;
    }

    public boolean isCellEditable(int row, int column) {
        for (int c = 0; c < countcelltoblock; c++) {
            if (row == cellinrow[c] && column == cellincolumn[c] && row != -1 && column != -1) {
                return false;
            }
        }
        /*      if(this.getValueAt(row, 0).toString().endsWith(".wmv")){
          if(column==3 || column==0 || column==1){
              return false;
          }else{
              return true;
          }
      }*/
        boolean checkcolumnvalidation = false;
        try {
            for (int c = 0; c < columntoblock.length; c++) {
                if (columntoblock[c] == column) {
                    checkcolumnvalidation = true;
                }
            }
            if (checkcolumnvalidation == true) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    public Class getColumnClass(int columnIndex) {
        // Devuelve la clase que hay en cada columna.
        switch (columnIndex) {
            case 0:
                // La columna cero contiene el nombre de la persona, que es
                // un String

                return String.class;

            case 1:
                // La columna uno contiene el apellido de la persona, que es
                // un String
                return String.class;
            case 2:
                // La columna dos contine la edad de la persona, que es un
                // Integer (no vale int, debe ser una clase)
                return Integer.class;
            case 5:
                return Boolean.class;
            default:
                // Devuelve una clase Object por defecto.
                return String.class;
        }
    }

    private double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
}
