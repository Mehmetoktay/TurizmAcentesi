package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    HotelDao hotelDao = new HotelDao();

    public Hotel getById(int id){
        return this.hotelDao.getByID(id);
    }
    public ArrayList<Hotel> findAll() {return this.hotelDao.findAll();}


    //tablo oluştururken içerisine verdiğimiz arraylistin her bir elemanının değerlerini bir objeye atarak o objeden oluşan bir liste dönen metod.
    public ArrayList<Object[]> getForTable(int size,ArrayList<Hotel> hotels){
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for(Hotel obj : hotels){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getAddress();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getPhone();
            rowObject[i++] = obj.getStar();
            rowObject[i++] = obj.isCar_park();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isFitness();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isRoom_service();
            hotelList.add(rowObject);
        }
        return hotelList;
    }

    public boolean save(Hotel hotel){
        if(hotel.getId()!=0){
            Helper.showMsg("error");
        }
        return this.hotelDao.save(hotel);
    }
    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " ID kayıtlı model bulunamadı");
            return false;
        }
        return this.hotelDao.delete(id);
    }
    public boolean update(Hotel hotel){
        if (this.getById(hotel.getId()) == null){
            Helper.showMsg(hotel.getId() + "ID kayıtlı model bulunamadı");
            return false;
        }
        return this.hotelDao.update(hotel);
    }
}
