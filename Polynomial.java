import java.lang.Math;

public class Polynomial{
  public double coefficients[];

  public Polynomial(){
    this.coefficients = new double[]{0};
  }
  
  public Polynomial(double coefficients[]){
    this.coefficients = coefficients;
  }

  public Polynomial add(Polynomial poly2){
    // create a new polynomial 
    // find the maximum length polynomial
    int max = poly2.coefficients.length;
    if (this.coefficients.length > poly2.coefficients.length){
      max = this.coefficients.length;
    }
    Polynomial returnpoly = new Polynomial(new double[max]);
    // sum new polynomial
    for(int i = 0; i < max; i++){
      if (i < this.coefficients.length){
        returnpoly.coefficients[i] += this.coefficients[i];
      }
      if (i < poly2.coefficients.length){
        returnpoly.coefficients[i] += poly2.coefficients[i];
      }
    }
    // return new polynomial 
    return returnpoly;
  }
  public double evaluate(double inputnum){
    double returnsum = 0;
    for(int i = 0; i < this.coefficients.length; i++){
      returnsum += this.coefficients[i] * Math.pow(inputnum,i);
    }
    return returnsum;
  }
  public boolean hasRoot(double inputnum){
    return evaluate(inputnum) == 0;
  }
}
