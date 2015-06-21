package com.github.kbayram;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class SqliteMain {

	public static void main(String[] arg) {
		Random rnd = new Random();
		try {
			// oluşturduğumuz class'ı kullanarak sqlite nesnemizi tanımladık
			SqliteDatabase db = new SqliteDatabase("E:\\PhoneBook.sqlite");

			System.out.println("Yeni Kayıt Ekleniyor");

			/// veritabanıne veri giriyoruz...
			db.execute("insert into people(firstname,lastname,phone) values(?,?,?)", "Person" + rnd.nextInt(100), "Lastname" + rnd.nextInt(100),
					"0 (123) " + rnd.nextInt(10000000));
			
			System.out.println("Yeni Kayıt Eklendi! Bilgiler Listeleniyor.");

			
			/// veritabanından olan verileri çekiyoruz.
			ResultSet rs = db.query("select * from people");
			while (rs.next()) {
				System.out.printf("%d \t %-15.15s \t %-10.10s \t %-10.10s %n", 
						rs.getInt("ID"), 
						rs.getString("Firstname"), 
						rs.getString("Lastname"),
						rs.getString("Phone"));
			}
			
			System.out.println("\nBilgiler Listelendi\n\nKayıtlar Düzenleniyor");
			
			// ilk veriyi güncelliyoruz.
			db.execute("update people set firstname=? where id = (select id from people limit 1)", "Updated Person" + rnd.nextInt(100));

			System.out.println("Kayıtlar Düzenlendi! Tekrar Bilgiler Listeleniyor\n");

			
			/// tekrar listeletiyoruz.
			ResultSet rsYeni = db.query("select * from people");
			while (rsYeni.next()) {
				System.out.printf("%d \t %-15.15s \t %-10.10s \t %-10.10s %n", rsYeni.getInt("ID"), rsYeni.getString("Firstname"),
						rsYeni.getString("Lastname"), rsYeni.getString("Phone"));

			}

			System.out.println("\nKayıtlar Siliniyor..");
			///verileri siliyoruz. 
			db.execute("delete from people where id = (select id from people limit 1)");
			System.out.println("Silindi!");

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
