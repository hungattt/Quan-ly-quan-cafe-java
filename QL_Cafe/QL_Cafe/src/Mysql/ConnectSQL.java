package Mysql;

import Models.Ban;
import Models.ChiTietHD;
import Models.DsOrder;
import Models.HoaDon;
import Models.DanhMuc;
import Models.TaiKhoan;
import Models.Mon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;




/**
 *
 * @author ThangIKCU
 */
public class ConnectSQL {
     Connection conn=null;
    Statement statement=null;
     
    public ConnectSQL(){
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionUrl = "jdbc:sqlserver://DESKTOP-JRFV3IL\\SQLEXPRESS:1433;databaseName=QLCAFEJAVA;integratedSecurity=true  ";
            conn= DriverManager.getConnection(connectionUrl);
            statement=conn.createStatement(
                  ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

        }
        catch (SQLException e)
        {
            System.out.println("SQL Exception : "+e.toString());
        }
        catch (ClassNotFoundException cE)
        {
            System.out.println("Class Not Found Exception : "+cE.toString());
        }     
    }
    //------------------------Danh Mụch --------------------------
    public ArrayList<DanhMuc> GetDanhMuc(){
        ArrayList<DanhMuc> arrDanhMuc = null;
        String sql = "Select * From DANHMUC";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDanhMuc = new ArrayList<DanhMuc>();
            while(rs.next()){
                DanhMuc sp = new DanhMuc(rs.getString(1), rs.getString(2), rs.getString(3));
                arrDanhMuc.add(sp);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi !");
        }
        return arrDanhMuc; 
    }
    
     public Vector GetNhomMon(){
        Vector arrDanhMuc = null;
        String sql = "Select * From DANHMUC";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDanhMuc = new Vector();
            DanhMuc all = new DanhMuc(null, "Tất cả các món","");
            arrDanhMuc.add(all);            
            while(rs.next()){
                DanhMuc sp = new DanhMuc(rs.getString(1), rs.getString(2), rs.getString(3));
                arrDanhMuc.add(sp);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi !");
        }
        return arrDanhMuc; 
    }
    
     
      public ArrayList<DanhMuc> SearchDanhMuc(String ten){
        ArrayList<DanhMuc> arrtd = null;
        String sql;
            sql = "SELECT * FROM DANHMUC WHERE TenDM LIKE '%"+ten+"%'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrtd = new ArrayList<DanhMuc>();
            while(rs.next()){
                DanhMuc td = new DanhMuc(rs.getString(1), rs.getString(2), rs.getString(3));
                arrtd.add(td);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi !");
        }
        return arrtd;
    } 
    
    public String GetMaDanhMuc(String TenDanhMuc){
        String maloai = null;
        String sql = "Select MaDM From DANHMUC Where TenDM = '"+TenDanhMuc+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
           
            while(rs.next()){
                maloai = rs.getString(1);
            }
 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được mã loại !");
        }
        return maloai; 
    }
     

    
     public int InsertDanhMuc(DanhMuc b){
        int insert = 0;
        String sql = "Insert into DANHMUC (MaDM,TenDM, MauSac) values ('"+b.GetMaDanhMuc()+"','"+b.GetTenDanhMuc()+"', '"+b.GetMauSac()+"')";
        try{
            Statement st = conn.createStatement();
            insert = st.executeUpdate(sql);
        }catch(SQLException ex){
        }
        return insert;
    }    
     
     public boolean DeleteNhom(ArrayList<String> lismanhom){
        boolean check = false;
        try{
            String sql;
            for(String ma : lismanhom){
                sql = "Delete From DANHMUC Where MaDM = '"+ma+"'";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
            } 
            check = true;
        }catch(SQLException ex){
            
        }
        return check;
    } 
    public DanhMuc GetDanhMucByMa(String manhom){
        DanhMuc loai = null;
        String sql = "Select * From DANHMUC Where MaDM = '"+manhom+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                loai = new DanhMuc(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi !");
        }
        return loai; 
    } 
   public int UpdateDanhMuc(DanhMuc b){
        int update = 0;
        String sql = "UPDATE DANHMUC SET TenDM = '"+b.GetTenDanhMuc()+"', MauSac = '"+b.GetMauSac()+"' WHERE MaDM = '"+b.GetMaDanhMuc()+"'";
        try{
            Statement st = conn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Update Loại không thành công !");
        }
        return update;
    }
    //---------------------------------End Danh Mục -------------------------------------------
   
     //-------------------------------Bàn--------------------------------------------------------
    public ArrayList<Ban> GetBan(int maban){
        ArrayList<Ban> arrBan = null;
        String sql;
        if(maban == 0)
            sql = "Select * From BAN";
        else
            sql = "Select * From BAN Where MaBan = '"+maban+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrBan = new ArrayList<Ban>();
            while(rs.next()){
                Ban sp = new Ban(rs.getInt(1), rs.getString(2), rs.getString(3));
                arrBan.add(sp);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi !");
        }
        return arrBan; 
    } 
    public int UpdateBan(Ban b){
        int update = 0;
        String sql = "UPDATE BAN SET TenBan = '"+b.GetTenBan()+"', TrangThai = '"+b.GetTrangThai()+"' WHERE MaBan = '"+b.GetMaBan()+"'";
        try{
            Statement st = conn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Update bàn không thành công !");
        }
        return update;
    }    
    
     public int UpDateTrangThaiBan(Ban b){
         int update = 0;
        String sql = "UPDATE BAN SET TrangThai = '"+b.GetTrangThai()+"' WHERE MaBan = '"+b.GetMaBan()+"'";
        try{
            Statement st = conn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Update trạng thái bàn không thành công !");
        }
        return update;
     }
     
     public boolean DeleteBan(ArrayList<Integer> listMaBan){
        boolean check = false;
        try{
            String sql;
            for(int ma : listMaBan){
                sql = "Delete From BAN Where MaBan = '"+ma+"'";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
            } 
            check = true;
        }catch(SQLException ex){
            
        }
        return check;
    }    
     
     public int InsertBan(Ban b){
        int insert = 0;
        String sql = "Insert into BAN (MaBan,TenBan, TrangThai) values ('"+b.GetMaBan()+"','"+b.GetTenBan()+"', '"+b.GetTrangThai()+"')";
        try{
            Statement st = conn.createStatement();
            insert = st.executeUpdate(sql);
        }catch(SQLException ex){
        }
        return insert;
    }
     
      public ArrayList<Ban> SearchBan(String ten){
        ArrayList<Ban> arrtd = null;
        String sql;
            sql = "SELECT * FROM BAN WHERE TenBan LIKE '%"+ten+"%'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrtd = new ArrayList<Ban>();
            while(rs.next()){
                Ban td = new Ban(rs.getInt(1), rs.getString(2), rs.getString(3));
                arrtd.add(td);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi !");
        }
        return arrtd;
    } 
   //----------------------------------End Bàn -----------------------------------------------------
      
      
    //---------------------------------Món-------------------------------------------------------
    public ArrayList<Mon> GetMonByMa(String ma){
        ArrayList<Mon> arrMon = null;
        String sql;

            sql = "Select * From MON Where MaMon = '"+ma+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrMon = new ArrayList<Mon>();
            while(rs.next()){
                Mon td = new Mon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                arrMon.add(td);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách thực đơn !");
        }
        return arrMon;        
    }
    
    public ArrayList<Mon> GetMon(String ma){
        ArrayList<Mon> arrMon = null;
        String sql;
        if(ma == null){
            sql = "Select * From MON";
        }else{
            sql = "Select * From MON where MaDM="+ma+"";
        }
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrMon = new ArrayList<Mon>();
            while(rs.next()){
                Mon td = new Mon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                arrMon.add(td);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách thực đơn !");
        }
        return arrMon;        
    }
    
    public int DeleteMon(String mamon, int mahd, int maban){
        int check = 0;
        try{
            String sql;
            sql = "Delete From CHITIETHD Where MaMon = '"+mamon+"' AND MaHoaDon = '"+mahd+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            check = 1;
            if(CheckDsMon(mahd, maban) == 0){
                check = 2;
            }
        }catch(SQLException ex){
            
        }
        return check;
    }
     public int CheckDsMon(int mahd, int maban){
        String sql;
        int dem = 0;
            sql = "Select * From HOADON AS hd INNER JOIN CHITIETHD AS ct ON ct.MaHoaDon = hd.MaHoaDon Where MaBan = '"+maban+"' AND ct.MaHoaDon = '"+mahd+"' AND TrangThai = 0";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                dem++;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách hóa đơn !");
        }
        return dem;        
    }        
     
     public ArrayList<Mon> GetDsMonBan(){
        ArrayList<Mon> arrDs = null;
        String sql;
            sql = "Select TenMon, MaMon, DVT From MON WHERE MaMon in (Select MaMon From CHITIETHD) AND HOADON.TrangThai = 1";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDs = new ArrayList<Mon>();
            while(rs.next()){
                Mon order = new Mon();
                order.SetTenMon(rs.getString(1));
                order.SetMaMon(rs.getString(2));
                order.SetDVT(rs.getString(3));
                arrDs.add(order);
            }
        }catch(SQLException ex){
        }
        return arrDs;        
    }  
     public int InsertMon(Mon td){
        int insert = 0;
        String sql = "Insert into MON (MaMon,TenMon, MaDM, DonGia, DVT) values ('"+td.GetMaMon()+"',N'"+td.GetTenMon()+"', '"+td.GetMaDanhMuc()+"', '"+td.GetDonGia()+"', '"+td.GetDVT()+"')";
        try{
            Statement st = conn.createStatement();
            insert = st.executeUpdate(sql);
        }catch(SQLException ex){
        }
        return insert;
    }
     
     public boolean DeleteMon(String listMamon){
        boolean check = false;
        try{
            String sql1;
            String sql;
            //for(String ma : listMamon){
                 sql = "Delete From CHITIETHD Where MaMon ="+listMamon+" ";
                sql1 = "Delete From MON Where MaMon ="+listMamon+" ";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                st.executeUpdate(sql1);
            //} 
            check = true;
        }catch(SQLException ex){
            
        }
        return check;
    }
     
     public int UpdateMon(Mon td){
        int update = 0;
        String sql = "UPDATE MON SET TenMon = N'"+td.GetTenMon()+"', MaDM = '"+td.GetMaDanhMuc()+"', DonGia = '"+td.GetDonGia()+"', DVT = '"+td.GetDVT()+"' WHERE MaMon = '"+td.GetMaMon()+"'";
        try{
            Statement st = conn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Update món không thành công !");
        }
        return update;
    }   
     
      public ArrayList<Mon> SearchMon(String ten){
        ArrayList<Mon> arrtd = null;
        String sql;
            sql = "SELECT * FROM MON WHERE TenMon LIKE '%"+ten+"%'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrtd = new ArrayList<Mon>();
            while(rs.next()){
                Mon td = new Mon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                arrtd.add(td);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi !");
        }
        return arrtd;
    }
      
       public ArrayList<Mon> GetChiTietMonByMa(){
        ArrayList<Mon> arrDs = null;
        String sql;
            sql = "SELECT TenMon, MaMon, DVT FROM MON where MaMon in (Select MaMon From CHITIETHD)";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDs = new ArrayList<Mon>();
            while(rs.next()){
                Mon order = new Mon();
                order.SetTenMon(rs.getString(1));
                order.SetMaMon(rs.getString(2));
                order.SetDVT(rs.getString(3));
                arrDs.add(order);
            }
        }catch(SQLException ex){
        }
        return arrDs;        
    }  
  //---------------------------------------------End Món---------------------------------------------  
    
   //---------------------------------------------Order---------------------------------------------- 
     public ArrayList<DsOrder> GetDsOrder(int ma){
        ArrayList<DsOrder> arrDs = null;
        String sql;
            sql = "Select ct.MaMon, TenMon, DVT, SoLuong, Gia, MaHoaDon From CHITIETHD AS ct INNER JOIN MON AS td ON ct.MaMon = td.MaMon Where ct.MaHoaDon = '"+ma+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDs = new ArrayList<DsOrder>();
            while(rs.next()){
                DsOrder order = new DsOrder(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                arrDs.add(order);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách Order !");
        }
        return arrDs;        
    } 
     
      public ArrayList<DsOrder> GetGiaSoLuong(String ma){
        ArrayList<DsOrder> arrDs = null;
        String sql;
            sql = "Select Gia, SoLuong, TenMon, DVT From CHITIETHD AS ct INNER JOIN HOADON AS hd ON ct.MaHoaDon = hd.MaHoaDon INNER JOIN MON AS td ON td.MaMon = ct.MaMon Where hd.TrangThai = 1 AND ct.MaMon = '"+ma+"'";
        try{
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDs = new ArrayList<DsOrder>();
            while(rs.next()){
                
                DsOrder order = new DsOrder();
                order.SetGia(rs.getInt(1));
                order.SetSoLuong(rs.getInt(2));
                order.SetTenMon(rs.getString(3));
                order.SetDVT(rs.getString(4));
                arrDs.add(order);
            }
        }catch(SQLException ex){
        }
        return arrDs;        
    }
     public ArrayList<DsOrder> GetHdByDate(String d1,String d2, String m){
        ArrayList<DsOrder> arrDs = null;
        String sql;
        if(d1.equals(d2)){
            sql = "Select Gia, SoLuong, TenMon, DVT From CHITIETHD AS ct INNER JOIN HOADON AS hd ON ct.MaHoaDon = hd.MaHoaDon INNER JOIN MON AS td ON td.MaMon = ct.MaMon Where hd.TrangThai = 1 AND hd.GioDen >= '"+d1+"' AND ct.MaMon ='"+m+"'";
        }else
            sql = "Select Gia, SoLuong, TenMon, DVT From CHITIETHD AS ct INNER JOIN HOADON AS hd ON ct.MaHoaDon = hd.MaHoaDon INNER JOIN MON AS td ON td.MaMon = ct.MaMon Where hd.TrangThai = 1 AND hd.GioDen BETWEEN '"+d1+"' AND '"+d2+"' AND ct.MaMon ='"+m+"'";
        try{
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDs = new ArrayList<DsOrder>();
            while(rs.next()){
                DsOrder order = new DsOrder();
                order.SetGia(rs.getInt(1));
                order.SetSoLuong(rs.getInt(2));
                order.SetTenMon(rs.getString(3));
                order.SetDVT(rs.getString(4));
                arrDs.add(order);
            }
        }catch(SQLException ex){
        }
        return arrDs;        
    } 
     public ArrayList<DsOrder> GetCtHDByDate(int ma, String d1, String d2){
        ArrayList<DsOrder> arrDs = null;
        String sql;
        if(d1.equals(d2))
            sql = "Select ct.MaMon, TenMon, DVT, SoLuong, Gia, ct.MaHoaDon From CHITIETHD AS ct INNER JOIN MON AS td ON ct.MaMon = td.MaMon INNER JOIN HOADON AS hd ON hd.MaHoaDon = ct.MaHoaDon Where ct.MaHoaDon = '"+ma+"' AND hd.GioDen >= '"+d1+"'";
            else
            sql = "Select ct.MaMon, TenMon, DVT, SoLuong, Gia, ct.MaHoaDon From CHITIETHD AS ct INNER JOIN MON AS td ON ct.MaMon = td.MaMon INNER JOIN HOADON AS hd ON hd.MaHoaDon = ct.MaHoaDon Where ct.MaHoaDon = '"+ma+"' AND hd.GioDen BETWEEN '"+d1+"' AND '"+d2+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDs = new ArrayList<DsOrder>();
            while(rs.next()){
                DsOrder order = new DsOrder(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                arrDs.add(order);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách chi tiết hoa đơn !"+ex.toString());
        }
        return arrDs;        
    }     

     
     //--------------------------------------------End Order-----------------------------------
     //------------------------------------------Chi Tiết Hóa Đơn---------------------------------------------
    public ChiTietHD GetDsChiTiet(String ma, int maban){
        ChiTietHD arrDs = null;
        String sql;

            sql = "Select SoLuong, Gia, MaChiTietHD From CHITIETHD AS ct INNER JOIN HOADON AS hd ON ct.MaHoaDon = hd.MaHoaDon Where MaMon = '"+ma+"' AND MaBan = '"+maban+"' AND hd.TrangThai = 0";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                arrDs = new ChiTietHD();
                arrDs.SetSoLuong(rs.getInt(1));
                arrDs.SetGia(rs.getInt(2));
                arrDs.SetMaChiTietHD(rs.getInt(3));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách Order !");
        }
        return arrDs;        
    } 
    public int UpdateChiTiet(ChiTietHD ct){
        int update = 0;
        String sql = "UPDATE CHITIETHD SET SoLuong = '"+ct.GetSoLuong()+"', Gia = '"+ct.GetGia()+"' WHERE MaChiTietHD = '"+ct.GetMaChiTietHD()+"'";
        try{
            Statement st = conn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Update chi tiết không thành công !");
        }
        return update;        
    }
    
    public int InsertChiTietHD(ChiTietHD cthd){
        int insert = 0;
        String sql = "Insert into CHITIETHD (MaChiTietHD,MaHoaDon,MaMon,SoLuong,Gia) values ('"+cthd.GetMaChiTietHD()+"','"+cthd.GetMaHD()+"', '"+cthd.GetMaMon()+"', '"+cthd.GetSoLuong()+"', '"+cthd.GetGia()+"')";
        try{
            Statement st = conn.createStatement();
            insert = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Thêm chi tiết hóa đơn không thành công , mã đã tồn tại  !");
        }
        return insert;
    }
    //----------------------------------------End Chi tiết Hóa Đơn-----------------------------
    //--------------Hóa Đơn----------------------------------------------------------------Hóa Đơn ----------------------------------
    public int HuyHD(HoaDon hd){
        int update = 0;
        String sql = "Delete From HOADON WHERE MaHoaDon = '"+hd.GetMaHD()+"'";
        try{
            Statement st = conn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Thanh toán không thành công !");
        }
        return update;        
    }    
    public int ThanhToan(HoaDon hd){
        int update = 0;
        String sql = "UPDATE HOADON SET TongTien = '"+hd.GetTongTien()+"', TrangThai = 1 WHERE MaHoaDon = '"+hd.GetMaHD()+"'";
        try{
            Statement st = conn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Thanh toán không thành công !");
        }
        return update;        
    }
    
    
    public ArrayList<HoaDon> GetDSHD(){
        ArrayList<HoaDon> arrDs = null;
        String sql;
            sql = "Select * From HOADON Where TrangThai = 1";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDs = new ArrayList<HoaDon>();
            while(rs.next()){
                HoaDon order = new HoaDon(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getInt(5), rs.getInt(6));
                arrDs.add(order);
            }
        }catch(SQLException ex){
        }
        return arrDs;        
    }
    
    public int InsertHoaDon(HoaDon hd, String gio){
        int insert = 0;
        String sql = "Insert into HOADON (MaHoaDon,MaBan, GioDen, TrangThai) values ('"+hd.GetMaHD()+"','"+hd.GetMaBan()+"', '"+gio+"', '"+hd.GetTrangThai()+"')";
        try{
            Statement st = conn.createStatement();
            insert = st.executeUpdate(sql);
        }catch(SQLException ex){
        }
        return insert;
    }
    
     public HoaDon GetHDbyMaBan(int ma){
        String sql;
        HoaDon arrhd = null;
            sql = "Select * From HOADON Where MaBan = '"+ma+"' AND TrangThai = 0";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                arrhd = new HoaDon(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getInt(5), rs.getInt(6));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách hóa đơn !");
        }
        return arrhd;        
    }    
      public int GetMaHD(int ma){
        String sql;
        int mahd = 0;
            sql = "Select MaHoaDon From HOADON , BAN Where HOADON.MaBan = BAN.MaBan  and  HOADON.MaBan = '"+ma+"' AND HOADON.TrangThai =0";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                mahd = rs.getInt(1);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách thực đơn !");
        }
        return mahd;        
    } 
      
      public int UpdateHD(HoaDon hd){
        int update = 0;
        String sql = "UPDATE HOADON SET GiamGia = '"+hd.GetGiamGia()+"' WHERE MaHoaDon = '"+hd.GetMaHD()+"'";
        try{
            Statement st = conn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Giảm giá không thành công !");
        }
        return update;
    }  
    //-------------------------------------End Hóa Đơn -----------------------------------------------------------------------------------------------------------------
           
 
    //----------------------------Tài Khoản -----------------------------------------
    public boolean DeleteTaiKhoan(ArrayList<Integer> listMaBan){
        boolean check = false;
        try{
            String sql;
            for(int ma : listMaBan){
                sql = "Delete From TAIKHOAN Where Id = '"+ma+"'";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
            } 
            check = true;
        }catch(SQLException ex){
            
        }
        return check;
    } 
    public int InserTK(TaiKhoan b){
        int insert = 0;
        String sql = "Insert into TAIKHOAN (Id,Username, Password, lv) values ('"+b.GetID()+"','"+b.GetUsername()+"', '"+b.GetPassword()+"', '"+b.GetLv()+"')";
        try{
            Statement st = conn.createStatement();
            insert = st.executeUpdate(sql);
            
        }catch(SQLException ex){
        }
        return insert;
    } 
    public TaiKhoan GetTaiKhoan(int id){
        TaiKhoan td = null;
        String sql;
            sql = "SELECT * FROM TAIKHOAN WHERE Id = '"+id+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
               td  = new TaiKhoan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi !");
        }
        return td;
    }
   
    public TaiKhoan GetTaiKhoan(String name, String pass){
        TaiKhoan td = null;
        String sql;
            sql = "SELECT * FROM TAIKHOAN Where Username = '"+name+"' AND Password='"+pass+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
               td  = new TaiKhoan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi !");
        }
        return td;
    }
    public int UpdateTK(TaiKhoan b){
        int update = 0;
        String sql = "UPDATE TAIKHOAN SET Username = '"+b.GetUsername()+"', Password = '"+b.GetPassword()+"', lv = '"+b.GetLv()+"' WHERE Id = '"+b.GetID()+"'";
        try{
            Statement st = conn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi !");
        }
        return update;
    }
    public ArrayList<TaiKhoan> GetTaiKhoan(){
        ArrayList<TaiKhoan> arrtd = null;
        String sql;
            sql = "SELECT * FROM TAIKHOAN WHERE lv != 1";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrtd = new ArrayList<TaiKhoan>();
            while(rs.next()){
                TaiKhoan td = new TaiKhoan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                arrtd.add(td);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi !");
        }
        return arrtd;
    }
    
    public boolean CheckLogin(TaiKhoan tk)
    {
        boolean check = false;
        String sql;
            sql = "Select * From TAIKHOAN Where Username = '"+tk.GetUsername()+"' AND Password='"+tk.GetPassword()+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                check = true;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi đăng nhập !");
        }
        return check; 
    }
     public int LVTK(TaiKhoan tk)
    {
        int lvtk =0;
        String sql;
            sql = "Select lv From TAIKHOAN Where Username = '"+tk.GetUsername()+"' AND Password='"+tk.GetPassword()+"'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                lvtk = rs.getInt(1);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi !");
        }
        return lvtk; 
    }   
}
    //-----------------------------End Tài Khoản ---------------------------------------

     
    
    
