package dao;

import core.Database;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection connection;

    public RoomDao() {
        this.connection = Database.getInstance();
    }

    public Room getByID(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setId(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_id(rs.getInt("pension_id"));
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setType(rs.getString("type"));
        obj.setStock(rs.getInt("stock"));
        obj.setAdult_price(rs.getDouble("adult_price"));
        obj.setChild_price(rs.getDouble("child_price"));
        obj.setBed_capacity(rs.getInt("bed_capacity"));
        obj.setSquare_meter(rs.getInt("square_meter"));
        obj.setTelevision(rs.getBoolean("television"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setGame_console(rs.getBoolean("game_console"));
        obj.setProjection(rs.getBoolean("projection"));
        return obj;
    }

    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {

                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id," +
                "pension_id," +
                "season_id," +
                "type," +
                "stock," +
                "adult_price," +
                "child_price," +
                "bed_capacity," +
                "square_meter," +
                "television," +
                "minibar," +
                "game_console," +
                "cash_box," +
                "projection" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdult_price());
            pr.setDouble(7, room.getChild_price());
            pr.setInt(8, room.getBed_capacity());
            pr.setInt(9, room.getSquare_meter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGame_console());
            pr.setBoolean(13, room.isCash_box());
            pr.setBoolean(14, room.isProjection());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public boolean updateStock(Room room){
        String query = "UPDATE public.room SET stock = ? WHERE id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, room.getStock());
            pr.setInt(2,room.getId());

            pr.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hotel_id) {
        try {
            String query = "DELETE FROM public.room WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public boolean update(Room room) {
        try {
            String query = "UPDATE public.room SET " +
                    "hotel_id = ?," +
                    "pension_id = ?," +
                    "season_id= ?," +
                    "type= ?," +
                    "stock= ?," +
                    "adult_price = ?," +
                    "child_price = ?," +
                    "bed_capacity = ?," +
                    "square_meter = ?," +
                    "television = ?," +
                    "minibar = ?," +
                    "game_console = ?," +
                    "cash_box = ?," +
                    "projection = ?" +
                    "WHERE id = ?";

            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdult_price());
            pr.setDouble(7, room.getChild_price());
            pr.setInt(8, room.getBed_capacity());
            pr.setInt(9, room.getSquare_meter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGame_console());
            pr.setBoolean(13, room.isCash_box());
            pr.setBoolean(14, room.isProjection());
            pr.setInt(15, room.getId());
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }



}

