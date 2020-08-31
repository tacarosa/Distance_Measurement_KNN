/*
class berisi fungsi atau method untuk praproses data sebelum masuk tahap klasifikasi
berupa input data, cleaning data(replace missing value)
dan split data
 */
package controller;
import boundary.MainFrame;
import entity.Dataset;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Taca 
*/
public class Praproses {
    Dataset Data;
    FileReader file;
    
    public Dataset hasilPraproses(File fileInput){
        inputData(fileInput);
        String[][] dataInput = Data.getData();
        String[][] dataBersih = replaceMissingValue(dataInput);
        splitData(dataBersih);
        return Data;
    }
    public void inputData(File fileInput){
        Data = new Dataset();
        String[][] nilai = null;
        try {
            file = new FileReader(fileInput.getPath());
            BufferedReader br = new BufferedReader(file);
            Object tableLine[] = br.lines().toArray();
            int baris = tableLine.length;
            int kolom = tableLine[0].toString().trim().split(",").length;
            int index = 0;
            nilai = new String[baris-1][kolom];
            for(int i=1; i<baris; i++){
                String line = tableLine[i].toString().trim();
                String dataRow[] = line.split(",");
                for(int j=0; j<kolom; j++){
                    nilai[index][j] = dataRow[j];
                }index++;
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Praproses.class.getName()).log(Level.SEVERE, null, ex);
        }
        Data.setData(nilai);
    }
    
    public String[][] replaceMissingValue(String[][] data){
        int baris = data.length;
        int kolom = data[0].length;
        double value=0;
        //cek data yg hilang
        for (int j=0; j<kolom; j++){
            //replace missing data
            //kondisi 1 untuk data numerik selanjutnya untuk data nominal
            if(j==1 || j==2 || j==3 || j==5 || j==6 || j==8 || j==10 || j==12 || j==26 || j==27){
                double sum = 0, count = 0;
                for (int i=0; i<baris; i++){
                    if (data[i][j].charAt(0) == '?'){
                        count++;
                    }else{
                        sum = sum + Double.parseDouble(data[i][j]);
                    }
                }
                value = sum/(baris-count);
            }
            else{
                int count1=0, count0=0;
                value = 1.0;
                for (int i=0; i<baris; i++){
                    if (data[i][j]== "1.0"){
                        count1++;
                    }else{
                        count0++;
                    }
                }
                if(count1>count0){
                    value = 1.0;
                }
            }
            //mengisi data
            for (int i=0; i<baris; i++){
                if(data[i][j].charAt(0) == '?'){
                    data[i][j] = String.valueOf(value);
                } 
            }
        }
        return data;
    }
    
    public void splitData(String[][] data){
        Data = new Dataset();
        int baris = data.length;
        int kolom = data[0].length;
        int indexBarisDL = 0;
        //hitung presetase data uji(20%) sisa = data latih
        int dataUji = (20*baris)/100;
        String[][] nilaiDataUji = new String[dataUji][kolom-1];
        String[][] nilaiDataLatih = new String[baris-dataUji][kolom-1];
        String[] nilaiLabelDataUji = new String[dataUji];
        String[] nilaiLabelDataLatih = new String[baris-dataUji];
        
        //Mengisi data uji
        for(int i=0; i<dataUji; i++){
            for(int j=0; j<kolom; j++){
                //kolom terakhir di set untuk label data
                if(j < kolom-1){
                    nilaiDataUji[i][j] = data[i][j];
                }
                else {
                    nilaiLabelDataUji[i] = data[i][j];
                }
            }
        } 
        Data.setDataUji(nilaiDataUji);
        Data.setLabelDataUji(nilaiLabelDataUji);
        //Mengisi data Latih
        int indexLabelDL = 0;
        for(int i=dataUji; i<baris; i++){
            for(int j=0; j<kolom; j++){
                //kolom 32-35 di set untuk label data
                if(j < kolom-1){
                    nilaiDataLatih[indexBarisDL][j] = data[i][j];
                }
                else {
                    nilaiLabelDataLatih[indexLabelDL] = data[i][j];
                    //System.out.println(nilaiLabelDataLatih[indexLabelDL]+"       "+i);
                    indexLabelDL++;
                }
            }
            indexBarisDL++;
        } 
        Data.setDataLatih(nilaiDataLatih);
        Data.setLabelDataLatih(nilaiLabelDataLatih);
    }
}


