/*
perhitungan klasifikasi k-Nearest Neighbor
 */
package controller;
import entity.Dataset;
import entity.Prediksi;
/**
 *
 * @author Taca
 */
public class KNN {
    Praproses Pra;
    
    public KNN(){};
    public void hitungKNN(Dataset data, String metode, int k){
        //inisiasi matrix untuk menyimpan hasil proses
        double[][] matrixJarak = null;
        int[][] matrixSorting = null;
        int[] Prediksi;
        //--menginisiasikan data dan label data
        String[][] dataUji = data.getDataUji();
        String[][] dataLatih = data.getDataLatih();
        String[] LabelDL = data.getLabelDataLatih();
        
        //proses perhitungan jarak
        if (metode == "Euclidean"){
            matrixJarak = metodeEuclidean(dataUji, dataLatih);
            matrixSorting = Sorting(matrixJarak, k,LabelDL);
        }else if (metode == "Manhattan"){
            matrixJarak = metodeManhattan(dataUji, dataLatih);
            matrixSorting = Sorting(matrixJarak, k,LabelDL);
        }else if (metode == "Tchebychev"){
            matrixJarak = metodeTchebychev(dataUji, dataLatih);
            matrixSorting = Sorting(matrixJarak, k,LabelDL);
        }else if(metode == "Cosine"){
            matrixJarak = metodeCosine(dataUji, dataLatih);
            matrixSorting = Sorting(matrixJarak, k,LabelDL);
        }
        //menemukan kelas mayoritas utuk mendapatkan hasil label prediksi
        Prediksi = kelasMayoritas2(matrixSorting);
        data.setLabelPrediksi(Prediksi);
    }
    //fungsi pengurutan data dari yeng terkecil-kebesar
    //hasil dimasukkan dalam matrix yang berisi index hasil sorting
    public int[][] Sorting(double[][] data, int nilaiK, String[] LabelDL){
        int baris = data.length;
        int kolom = data[0].length;
        int[][] matrixLabel = new int[baris][nilaiK];
        double temp;
        String temp2;
        
        for(int loop = 0; loop < baris; loop++){//looping per baris data hasil perhitungan jarak
            for(int i = 0; i < nilaiK; i++){//outer loop (limit value)
                int minIndex = i;
                for(int j = i; j<kolom; j++){//inner loop
                    if(data[loop][j] < data[loop][minIndex]){
                        minIndex = j;
                    }
                }
                if(data[loop][minIndex] < data[loop][i]){
                    matrixLabel[loop][i] = Integer.parseInt(LabelDL[minIndex]);
                    //swipe data
                    temp = data[loop][i];
                    data[loop][i] = data[loop][minIndex];
                    data[loop][minIndex] = temp;
                    //swipe label
                    temp2 = LabelDL[i];
                    LabelDL[i] = LabelDL[minIndex];
                    LabelDL[minIndex] = temp2;
                }
                System.out.println(matrixLabel[loop][i]+"      "+loop+"     "+i);
            }
        }
        return matrixLabel;
    }
    public int[] kelasMayoritas2(int[][] matrixSorting){
        int[] kelas = new int[matrixSorting.length];
        for(int i = 0; i<matrixSorting.length; i++){
            int kelas_0 = 0, kelas_1 = 0;
            for(int j = 0; j<matrixSorting[0].length;j++){
                if (matrixSorting[i][j] == 0){
                    kelas_0++;
                }
                else if (matrixSorting[i][j] == 1){
                    kelas_1++;
                }
            }
            if (kelas_0 > kelas_1){
                kelas[i] = 0;
            } else {
                kelas[i] = 1;
            }
        } 
        return kelas;
    }
    //
    public int findLabelMatrix2(String[] LabelDL, int index){
        int value = 0;
        for(int i = 0; i<LabelDL.length; i++){
            if(index == i){
                value = Integer.parseInt(LabelDL[i]);
            }
        }
        return value;
    }
    //fungsi pencarian label data dari matrix index hasil sorting
    public int[][] findLabelMatrix(int[][] matrixIndex, String[] LabelDL){
        int baris = matrixIndex.length;
        int kolom = matrixIndex[0].length;
        int[][] matrixLabel = new int[baris][kolom];
        int index;
        for(int i = 0; i<baris; i++){
            for(int j = 0; j<kolom; j++){
                index = matrixIndex[i][j];
                matrixLabel[i][j] = Integer.parseInt(LabelDL[index]);
                //System.out.println(matrixLabel[i][j]+"    "+i+"    "+j);
            }
        }
        return matrixLabel;
    }
    //fungsi pencarian kelas mayoritas untuk menentukan kelas label data uji
    public int[] kelasMayoritas(int[][] matrixLabel){
        int[] kelas = new int[matrixLabel.length];
        for(int i = 0; i<matrixLabel.length; i++){
            int kelas_0 = 0, kelas_1 = 0;
            for(int j = 0; j<matrixLabel[0].length;j++){
                if (matrixLabel[i][j] == 0){
                    kelas_0++;
                }
                else if (matrixLabel[i][j] == 1){
                    kelas_1++;
                }
            }
            if (kelas_0 > kelas_1){
                kelas[i] = 0;
            } else {
                kelas[i] = 1;
            }
            System.out.println(kelas[i]);
        } 
        return kelas;
    }
    //perhitungan jarak metode euclidean
    public double[][] metodeEuclidean(String[][] dataUji, String[][] dataLatih){ 
        double sum = 0.0;
        int barisDU = dataUji.length;
        int barisDL = dataLatih.length;
        int kolom = dataUji[0].length;
        //matrix baru diinisiasi
        //terdiri dari baris = [jumlah baris data uji] dan kolom = [jumlah baris data latih]
        double hasil[][] = new double[barisDU][barisDL]; 
        //iterasi sebanyak baris Data uji x baris Data Latih
        for(int i=0; i < barisDU; i++){
            for(int j = 0; j< barisDL; j++){
                for(int x = 0; x < kolom; x++){ //iterasi kolom
                    sum = sum + Math.pow(Double.parseDouble(dataUji[i][x])-Double.parseDouble(dataLatih[j][x]),2);
                }
                sum = Math.sqrt(sum);
                hasil[i][j] = sum;
                //System.out.println(hasil[i][j]+"     "+i+"      "+j);
            }
        }
        return hasil;    
    }
    //perhitungan jarak metode manhattan
    public double[][] metodeManhattan(String[][] dataUji, String[][] dataLatih){ 
        double sum = 0.0;
        int barisDU = dataUji.length;
        int barisDL = dataLatih.length;
        int kolom = dataUji[0].length;
        //matrix baru diinisiasi
        //terdiri dari baris = [jumlah baris data uji] dan kolom = [jumlah baris data latih]
        double hasil[][] = new double[barisDU][barisDL]; 
        //iterasi sebanyak baris Data uji x baris Data Latih
        for(int i=0; i < barisDU; i++){
            for(int j = 0; j< barisDL; j++){
                for(int x = 0; x < kolom; x++){ //iterasi kolom
                    sum = sum + Math.abs(Double.parseDouble(dataUji[i][x])-Double.parseDouble(dataLatih[j][x]));
                }
                sum = Math.sqrt(sum);
                hasil[i][j] = sum;
            }
        }
        return hasil;    
    }
    //perhitungan jarak metode tchebychev
    public double[][] metodeTchebychev(String[][] dataUji, String[][] dataLatih){ 
        double sum , jumlah=0.0;
        int barisDU = dataUji.length;
        int barisDL = dataLatih.length;
        int kolom = dataUji[0].length;
        double hasil[][] = new double[barisDU][barisDL]; 
        //iterasi sebanyak baris Data uji x baris Data Latih
        for(int i=0; i < barisDU; i++){
            for(int j = 0; j< barisDL; j++){
                for(int x = 0; x < kolom; x++){ //iterasi kolom
                    sum = Math.abs(Double.parseDouble(dataUji[i][x])-Double.parseDouble(dataLatih[j][x]));
                    if(sum > jumlah){
                        jumlah = sum;
                    }
                }
                hasil[i][j] = jumlah;
                jumlah = 0.0;
            }
        }
        return hasil;    
    }
    //perhitungan jarak metode cosine
    public double[][] metodeCosine(String[][] dataUji, String[][] dataLatih){ 
        double sum = 0.0, sum2, jumlah1 = 0.0, jumlah2 = 0.0;
        int barisDU = dataUji.length;
        int barisDL = dataLatih.length;
        int kolom = dataUji[0].length;
        double hasil[][] = new double[barisDU][barisDL];
        //iterasi sebanyak baris Data uji x baris Data Latih
        for(int i=0; i < barisDU; i++){
            for(int j = 0; j< barisDL; j++){
                for(int x = 0; x < kolom; x++){ //iterasi kolom
                    sum = sum + (Double.parseDouble(dataUji[i][x])*Double.parseDouble(dataLatih[j][x]));
                    jumlah1 = jumlah1 + Double.parseDouble(dataUji[i][x]);
                    jumlah2 = jumlah2 + Double.parseDouble(dataLatih[j][x]);
                }
                sum2 = sum/Math.sqrt(Math.pow(jumlah1, 2)*Math.pow(jumlah2, 2));
                hasil[i][j] = sum2;
            }
        }
        return hasil;    
    }
}
