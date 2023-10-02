/** Class that prints the Collatz sequence starting from a given number.
 * 
 * 
 *  @author YOUR NAME HERE
 */

/** Our commenting system for Java is described on https://www.geeksforgeeks.org/comments-in-java/. */



/** In general for Java we declare the data type or class in front of all newly created objects. */





/** Java is a high-level, class-based, mainly (not totally) object-oriented programming language (note classes are abstractions, not objects, in Java, as opposed to Python).
 * We place everything we code in classes basically.
 * The way classes and methods function in Java is described on https://www.geeksforgeeks.org/classes-objects-java/.
 * The way classes and methods function in Java is similar to Python (which we covered in cs61a in the 'Object orientated programming notes'),
 * except for modifiers described below.
 * You should also know the static keyword (described below for specifically classes and methods). Watch this video:
 * https://www.youtube.com/watch?v=yn9cDLVr_wk . */






/** For classes.
 * A public class, denoted for instance as 'public class', is available for anyone to use. No matter what package the using class is in (i.e. a package is usually for us a file directory),
 * the used class can be imported. 
 * 
 * A package private class, denoted by no explicit modifiers, denoted for instance as 'class', can be "seen" only by other classes in the same package .
 * 
 * An inner class can also be static, denoted for instance as 'public static class' or 'static class', which means that you can access it without creating an object of the outer class. Read this stackoverflow enquiry:
 * https://stackoverflow.com/questions/70324/java-inner-class-and-static-nested-class  . */

 



/** For methods.
 * Public methods, denoted for instance as 'public void method()', mean that the method is visible and can be called from other objects of other types. 
 * 
 * Private methods, denoted for instance as 'private void method()',  are only applicable to constructors, methods, and fields inside the classes. 
 * If a variable or methods or constructor is declared as private as we can access them only from within the class i.e from outside the class we can’t access them.
 * 
 * Static methods, denoted for instance as 'public static void method()' or 'private static void class()', mean that the method is associated with the class, not a specific instance (object) of that class.
 * This means that you can call a static method without creating an object of the class. Watch this video:
 * https://www.youtube.com/watch?v=xKZ3sMimQYM .
 * 
 * Void means that the method has no return value. If the method returned an int you would write int instead of void. Void is simply a data type or class.
 * Remember we declare the data type or class in front of all newly created objects. */





 public class Collatz {
    /** Returning the next number of the Collatz method */
    public static int nextNumber(int n) {                    /**  The main difference between a class and a datatype is that it is not */
                                                             /** possible to have two instances of a datatype with the */
                                                             /** same values (these instance would be one unique instance).*/
                                                             /** Otherwise treat them the same, so count 'int' similarly as a 'Collatz' type for objects. */




        if (n == 1) {
            return 1;
        } else if (n % 2 == 0) {
            return  n / 2;
        } else {
            return 3 * n + 1;
        }
    }




    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

