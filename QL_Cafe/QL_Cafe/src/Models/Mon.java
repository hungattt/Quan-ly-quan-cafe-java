/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author ThangIKCU
 */
public class Mon {
    private String  MaMon,TenMon, DVT, MaDanhMuc;
    private int DonGia;
    
    public Mon(){
        this.MaMon="";
        this.TenMon="";
        this.MaDanhMuc="";
        this.DVT="";
        this.DonGia=0;
    }
    public Mon(String mamon, String ten, String maloai, int gia, String dvt){
        this.MaMon=mamon;
        this.TenMon=ten;
        this.MaDanhMuc=maloai;
        this.DVT=dvt;
        this.DonGia=gia;       
    }
    
    public void SetMaMon(String mamon){
        this.MaMon=mamon;
    }
    public String GetMaMon(){
        return this.MaMon;
    }
    public void SetTenMon(String ten){
        this.TenMon=ten;
    }
    public String GetTenMon(){
        return this.TenMon;
    }
    public void SetMaDanhMuc(String maloai){
        this.MaDanhMuc=maloai;
    }
    public String GetMaDanhMuc(){
        return this.MaDanhMuc;
    }
    public void SetDonGia(int gia){
        this.DonGia=gia;
    }
    public int GetDonGia(){
        return this.DonGia;
    }
    public void SetDVT(String dvt){
        this.DVT=dvt;
    }
    public String GetDVT(){
        return this.DVT;
    }
    
    
}
