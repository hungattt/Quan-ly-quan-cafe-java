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
public class DanhMuc {
    private String  TenDanhMuc, MauSac,MaDanhMuc;
  
    public DanhMuc(){
        this.MaDanhMuc = "";
        this.TenDanhMuc = "";
        this.MauSac = "";
    }
    
    public DanhMuc(String ma, String ten, String mau){
        this.MaDanhMuc = ma;
        this.TenDanhMuc = ten;
        this.MauSac = mau;
    }
    public void SetMauSac(String mau){
        this.MauSac = mau;
    }
    public String GetMauSac(){
        return this.MauSac;
    }    
    public void SetMaDanhMuc(String ma){
        this.MaDanhMuc = ma;
    }
    public String GetMaDanhMuc(){
        return this.MaDanhMuc;
    }
    
    public void SetTenDanhMuc(String ten){
        this.TenDanhMuc = ten;
    }
    public String GetTenDanhMuc(){
        return this.TenDanhMuc;
    }
    
    @Override
    public String toString(){
        return this.TenDanhMuc;
    }

}
