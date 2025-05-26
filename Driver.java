public class Driver {
  public static void main(String [] args) {
    Polynomial p = new Polynomial();
    System.out.println(p.evaluate(3));
    double [] cof1 = {6,2,5};
    double [] pow1 = {0,1,3};
    Polynomial p1 = new Polynomial(cof1,pow1);
    double [] cof2 = {0,-2,1,7,-9};
    double [] pow2 = {0,2,3,5,9};
    Polynomial p2 = new Polynomial(cof2, pow2);

    // add
    Polynomial s = p1.add(p2);
    for (int i = 0; i < s.powers.length; i++){
      System.out.print(s.coefficients[i] + "x^" + s.powers[i] + " + ");
    }
    System.out.println();
  
    // mltiply
    Polynomial mul = p1.multiply(p2);
    for (int i = 0; i < mul.powers.length; i++){
      System.out.print(mul.coefficients[i] + "x^" + mul.powers[i] + " + ");
    }
    System.out.println();
    
    // check root
    System.out.println("s(0.1) = " + s.evaluate(0.1));
    if(s.hasRoot(1))
      System.out.println("1 is a root of s");
    else
      System.out.println("1 is not a root of s");
  }
}
