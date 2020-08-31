/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Dataset {
    String[][] Data;
    String[][] DataUji;
    String[][] DataLatih;
    String[] LabelDataUji;
    String[] LabelDataLatih;
    int[] LabelPrediksi;
    //fungsi setter
    public void setData(String[][] input){
        this.Data = input;
    }
    public void setDataUji(String[][] input){
        this.DataUji = input;
    }
    public void setDataLatih(String[][] input){
        this.DataLatih = input;
    }
    public void setLabelDataUji(String[] input){
        this.LabelDataUji = input;
    }
    public void setLabelDataLatih(String[] input){
        this.LabelDataLatih = input;
    }
    public void setLabelPrediksi(int[] input){
        this.LabelPrediksi = input;
    }
    //fungsi getter
    public String[][] getData(){
        return this.Data;
    }
    public String[][] getDataUji(){
        return this.DataUji;
    }
    public String[][] getDataLatih(){
        return this.DataLatih;
    }
    public String[] getLabelDataUji(){
        return this.LabelDataUji;
    }
    public String[] getLabelDataLatih(){
        return this.LabelDataLatih;
    }
    public int[] getLabelPrediksi(){
        return this.LabelPrediksi;
    }
}
