import java.sql.*;
import java.util.Scanner;

public class BarangManager {

    public void insertBarang() {
        try (Connection conn = DBConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Masukkan kode barang: ");
            String kode = scanner.nextLine();
            System.out.print("Masukkan nama barang: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan harga barang: ");
            int harga = scanner.nextInt();
            System.out.print("Masukkan stok barang: ");
            int stok = scanner.nextInt();

            CallableStatement stmt = conn.prepareCall("{CALL insert_barang(?, ?, ?, ?)}");
            stmt.setString(1, kode);
            stmt.setString(2, nama);
            stmt.setInt(3, harga);
            stmt.setInt(4, stok);

            stmt.execute();
            System.out.println("Data berhasil diinsert.");

        } catch (SQLException e) {
            System.err.println("Gagal insert: " + e.getMessage());
        }
    }

    public void tampilkanBarang() {
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM view_barang");

            while (rs.next()) {
                System.out.printf("Kode: %s, Nama: %s, Harga: %d, Stok: %d, Total Nilai: %d\n",
                        rs.getString("kode"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        rs.getInt("stok"),
                        rs.getInt("total_nilai"));
            }
        } catch (SQLException e) {
            System.err.println("Gagal tampilkan data: " + e.getMessage());
        }
    }
}