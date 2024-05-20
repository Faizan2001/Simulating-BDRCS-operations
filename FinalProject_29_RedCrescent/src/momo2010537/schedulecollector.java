/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package momo2010537;

/**
 *
 * @author User
 */
public class schedulecollector {
    private String donorname;
    private String donormail;
    private String bloodblood;
    private String radiofemale;
    private String radiomale;
    private String donorcontact;
    private String donoraddress;
    private String textarea;
    private String selectarea;
    private String datepick;

    public schedulecollector() {
    }

    public schedulecollector(String donorname, String donormail, String bloodblood, String radiofemale, String radiomale, String donorcontact, String donoraddress, String textarea, String selectarea, String datepick) {
        this.donorname = donorname;
        this.donormail = donormail;
        this.bloodblood = bloodblood;
        this.radiofemale = radiofemale;
        this.radiomale = radiomale;
        this.donorcontact = donorcontact;
        this.donoraddress = donoraddress;
        this.textarea = textarea;
        this.selectarea = selectarea;
        this.datepick = datepick;
    }

    public String getDonorname() {
        return donorname;
    }

    public void setDonorname(String donorname) {
        this.donorname = donorname;
    }

    public String getDonormail() {
        return donormail;
    }

    public void setDonormail(String donormail) {
        this.donormail = donormail;
    }

    public String getBloodblood() {
        return bloodblood;
    }

    public void setBloodblood(String bloodblood) {
        this.bloodblood = bloodblood;
    }

    public String getRadiofemale() {
        return radiofemale;
    }

    public void setRadiofemale(String radiofemale) {
        this.radiofemale = radiofemale;
    }

    public String getRadiomale() {
        return radiomale;
    }

    public void setRadiomale(String radiomale) {
        this.radiomale = radiomale;
    }

    public String getDonorcontact() {
        return donorcontact;
    }

    public void setDonorcontact(String donorcontact) {
        this.donorcontact = donorcontact;
    }

    public String getDonoraddress() {
        return donoraddress;
    }

    public void setDonoraddress(String donoraddress) {
        this.donoraddress = donoraddress;
    }

    public String getTextarea() {
        return textarea;
    }

    public void setTextarea(String textarea) {
        this.textarea = textarea;
    }

    public String getSelectarea() {
        return selectarea;
    }

    public void setSelectarea(String selectarea) {
        this.selectarea = selectarea;
    }

    public String getDatepick() {
        return datepick;
    }

    public void setDatepick(String datepick) {
        this.datepick = datepick;
    }

    @Override
    public String toString() {
        return "schedulecollector{" + "donorname=" + donorname + ", donormail=" + donormail + ", bloodblood=" + bloodblood + ", radiofemale=" + radiofemale + ", radiomale=" + radiomale + ", donorcontact=" + donorcontact + ", donoraddress=" + donoraddress + ", textarea=" + textarea + ", selectarea=" + selectarea + ", datepick=" + datepick + '}';
    }
    
    

    
    
}
