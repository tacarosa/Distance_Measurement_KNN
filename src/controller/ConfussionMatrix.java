/*
class untuk proses mendapatkan nilai confussion matrix
 */
package controller;
import entity.Dataset;
import entity.Prediksi;

public class ConfussionMatrix {
    Dataset Data;
    Prediksi LabelPrediksi;
    KNN Knn;
    
    //fungsi untuk mengecek label prediksi dengan label sebenarnya pada dataset
    public int[][] cekDataRill(int[] LabelPred, String[] LabelRill){
        int[][] ConfMatrix = new int[2][2];
        int TP=0, FP=0, FN=0, TN=0;
        for(int i = 0; i<LabelPred.length; i++){
            if(LabelRill[i].equals("1")){
                if(LabelPred[i] == 0){
                    TP++;
                } else if (LabelPred[i] == 0){
                    FN++;
                }
            } 
            else if(LabelRill[i].equals("0")){
                if(LabelPred[i] == 1){
                   FP++;
                } else if (LabelPred[i] == 0){
                   TN++;
                }
            }
        }
        ConfMatrix[0][0] = TP;
        ConfMatrix[0][1] = FP;
        ConfMatrix[1][0] = FN;
        ConfMatrix[1][1] = TN;
        return ConfMatrix;
    }
    public double Akurasi(int[][] ConfMatrix){
        int TP = ConfMatrix[0][0];
        int FP = ConfMatrix[0][1];
        int FN = ConfMatrix[1][0];
        int TN = ConfMatrix[1][1];
        double sum = TP+TN;
        double sum2 = TP+FP+FN+TN;
        return (sum/sum2)*100;
    }
    public double Sensitivity(int[][] ConfMatrix){
        int TP = ConfMatrix[0][0];
        int FP = ConfMatrix[0][1];
        int FN = ConfMatrix[1][0];
        int TN = ConfMatrix[1][1];
        double sum = TP+FN;
        return (TP/sum)*100;
    }
    public double Spesificity(int[][] ConfMatrix){
        int TP = ConfMatrix[0][0];
        int FP = ConfMatrix[0][1];
        int FN = ConfMatrix[1][0];
        int TN = ConfMatrix[1][1];
        double sum =TN+FP;
        return (TN/sum)*100;
    }
}
