/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class UnzipFile {
    private String Destionation;
    private String Source;

    public UnzipFile(String Destionation, String Source) {
        this.Destionation = Destionation;
        this.Source = Source;
    }

    public String getDestionation() {
        return Destionation;
    }

    public void setDestionation(String Destionation) {
        this.Destionation = Destionation;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }
    
    
}
