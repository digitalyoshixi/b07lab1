import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Polynomial{
  public double coefficients[];
  public int powers[];

  public Polynomial(){
    this.coefficients = new double[]{0};
    this.powers = new int[]{0};
  }
  
  public Polynomial(double coefficients[], int powers[]){
    this.coefficients = coefficients;
    this.powers = powers;
  }
  
  public Polynomial remove_redundant(Polynomial poly){
    int polylen = poly.powers.length;
    int realpolylen = 0;
    double tempcoeff[];
    int temppowers[];

    for (int i = 0; i < polylen; i++){
      if (poly.coefficients[i] != 0){
        realpolylen+=1; 
      }
    }
    Polynomial returnpoly = new Polynomial(new double[realpolylen], new int[realpolylen]);
    int currindex = 0;
    for (int i = 0; i < polylen; i++){
      if (poly.coefficients[i] != 0){
        returnpoly.coefficients[currindex] = poly.coefficients[i];
        returnpoly.powers[currindex] = poly.powers[i];
        currindex+=1;
      }
    }
    return returnpoly;
  }
  
  public Polynomial(File inputfile){
    try {
      Scanner scanner = new Scanner(inputfile);
      if (scanner.hasNext()){
        String currentline = scanner.nextLine();
        // isolate each 
        String numbergroups[] = currentline.split("[+-]");

        this.coefficients = new double[numbergroups.length];
        this.powers = new int[numbergroups.length];
        for (int i = 0; i < numbergroups.length; i++){
          if (numbergroups[i].length() == 1){
            coefficients[i] = Double.parseDouble(numbergroups[i].substring(0,1));
            powers[i] = 0;
          }
          else{
            coefficients[i] = Double.parseDouble(numbergroups[i].substring(0,1));
            powers[i] = Integer.parseInt(numbergroups[i].substring(2,3));
          }
        }
      }
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public Polynomial add(Polynomial poly2){
    // create a new polynomial 
    // find the maximum length polynomial
    int maxpow1 = this.powers[this.powers.length -1];
    int maxpow2 = poly2.powers[poly2.powers.length -1];
    int maxlen = Math.max(maxpow1, maxpow2)+1;
    
    Polynomial returnpoly = new Polynomial(new double[maxlen], new int[maxlen]);
    for (int i = 0; i < maxlen; i++){
      returnpoly.powers[i] = i;
      returnpoly.coefficients[i] = 0;
    }
    for (int i = 0; i < this.powers.length; i++){
      returnpoly.coefficients[(int)this.powers[i]] += this.coefficients[i];
    }
    for (int i = 0; i < poly2.powers.length; i++){
      returnpoly.coefficients[(int)poly2.powers[i]] += poly2.coefficients[i];
    }
    // return new polynomial 
    return remove_redundant(returnpoly);
  }
  public double evaluate(double inputnum){
    double returnsum = 0;
    for(int i = 0; i < this.coefficients.length; i++){
      returnsum += this.coefficients[i] * this.powers[i];
    }
    return returnsum;
  }
  public boolean hasRoot(double inputnum){
    return evaluate(inputnum) == 0;
  }
  

  public Polynomial multiply(Polynomial poly2){
    int maxpow1 = this.powers[this.powers.length -1];
    int maxpow2 = poly2.powers[poly2.powers.length -1];
    int maxlen = Math.max(maxpow1, maxpow2)+1;
    
    Polynomial returnpoly = new Polynomial(new double[maxlen * 2], new int[maxlen * 2]);
    // set powers
    for (int i = 0; i < maxlen*2; i++){
      returnpoly.powers[i] = i;
      returnpoly.coefficients[i] = 0;
    }
    // multiply with 2 loops
    for (int i = 0; i < this.powers.length; i++){
      for (int j = 0; j < poly2.powers.length; j++){
        returnpoly.coefficients[(int)this.powers[i] + (int)poly2.powers[j]] += this.coefficients[i] * poly2.coefficients[j];
      }
    }
    
    return remove_redundant(returnpoly);
  }
  public String createString(){
    String retstr = "";
    for (int i = 0; i < this.powers.length; i++){
      retstr += (int)this.coefficients[i] + "x" + this.powers[i];
      if (i != this.powers.length-1){
        retstr += "+";
      }
    }
    return retstr;
  }
  public void printPolynomial(){
    System.out.println(createString());
  }
  public void saveToFile(String filename){
    try {
      FileWriter writer = new FileWriter(filename);
      writer.write(createString()); 
      writer.close();
    } catch (IOException e){
      System.err.println(e);
    }
  }

}
