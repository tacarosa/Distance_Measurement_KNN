/*
class ini digunakan untuk membentuk objek label dari data uji (label prediksi)
 */
package entity;


public class Prediksi {
    int[] Label_1;
    
    public void setLabel(int[] input_1){
        this.Label_1 = input_1;
    }
    public int[] getLabel_1(){
        return this.Label_1;
    }
}
