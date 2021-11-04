package FastHash;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private String name,surname1,surname2;
    private String dni;
    private int postalCode,phoneNumber;

    public Node(String name,String surname1,String surname2) {
        this.name=name;this.surname1=surname1;this.surname2=surname2;
    }

    public String getName() {return this.name;}
    public String getSurName1() {return this.surname1;}
    public String getSurName2() {return this.surname2;}
    public String getDni() {return this.dni;}
    public int    getPostalCode() {return this.postalCode;}
    public int    getPhoneNumber() {return this.phoneNumber;}

    public Node   setName(String name) {this.name=name; return this;}
    public Node   setSurname1(String surname1) {this.surname1=surname1; return this;}
    public Node   setSurname2(String surname2) {this.surname2=surname2; return this;}
    public Node   setDni(String dni) {this.dni=dni; return this;}
    public Node   setPostalCode(int postalCode) {this.postalCode=postalCode; return this;}
    public Node   setPhoneNumber(int phoneNumber) {this.phoneNumber=phoneNumber; return this;}

    public ArrayList<String> getArrayString() {
        return new ArrayList<>(Arrays.asList(getName(),getSurName1(),getSurName2()));
    }

    public String getNodeToString(){
        return "Nombre: "+getName() + " "+getSurName1()+" "+getSurName2()+"\n"+
                "DNI: "+getDni();
    }

}
