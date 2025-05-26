import java.lang.Math;

public class Polynomial{
  public double coefficients[];
  public double powers[];

  public Polynomial(){
    this.coefficients = new double[]{0};
    this.powers = new double[]{0};
  }
  
  public Polynomial(double coefficients[], double powers[]){
    this.coefficients = coefficients;
    this.powers = powers;
  }

  public Polynomial add(Polynomial poly2){
    // create a new polynomial 
    // find the maximum length polynomial
    double maxpow1 = this.powers[this.powers.length -1];
    double maxpow2 = poly2.powers[poly2.powers.length -1];
    double maxlen = Math.max(maxpow1, maxpow2)+1;
    
    Polynomial returnpoly = new Polynomial(new double[(int)maxlen], new double[(int)maxlen]);
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
    return returnpoly;
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
    double maxpow1 = this.powers[this.powers.length -1];
    double maxpow2 = poly2.powers[poly2.powers.length -1];
    double maxlen = Math.max(maxpow1, maxpow2)+1;
    
    Polynomial returnpoly = new Polynomial(new double[(int)maxlen * 2], new double[(int)maxlen * 2]);
    // multiple with 2 loops
    for (int i = 0; i < this.powers.length; i++){
      for (int j = 0; j < poly2.powers.length; j++){
        returnpoly[this.powers[i] + poly2.powers[j]] += this.coefficients[i] * poly.coefficients[j];
      }
    }
    return returnpoly;
  }
}
