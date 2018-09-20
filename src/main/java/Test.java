package main.java;

public class Test {
    public static void main(String[] args) {
        IntegerCollection collection =new IntegerCollection();

            for (int i = 0; i < 5; i++) {
                try {
                    collection.add(i);
                    System.out.println(collection);
                }
                catch (ValueNotNull ex){
                    ex.printStackTrace();
                }
            }

        try {
            collection.add(null);  // try to add null value
        }
        catch (ValueNotNull ex ){
            ex.printStackTrace();
        }

        try{
                collection.searchByValue(88);
        }
        catch (ElementNotFound ex){
                ex.printStackTrace();
        }
    }
}

