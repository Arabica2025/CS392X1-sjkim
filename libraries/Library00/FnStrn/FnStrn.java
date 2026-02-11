package Library00.FnStrn;

public class FnStrn {

    // read-only string 
    // OOP(encapsulation)
    // String root;
    char root[];

    // default constructor
    public FnStrn(){
        root = null;
    }

    // const: argument: char[]
    public FnStrn(char[] charr) {
        //root = str;
        int length = charr.length;
        root = new char[length];
        for (int i = 0; i < length; i+= 1){
            root[i] = charr[i];
        }

        // root = charr -> not goot design
    }

    

    // length function
    public int length(){
        //return root.length();
        return root.length;
    }

    // equivalent to charAt(index)
    public char getAt(int index){
        //return root.charAt(index);
        return root[index];
    }


}
