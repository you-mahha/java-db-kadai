package text.section_03;

import java.sql.Connection; // データベースとの接続を管理 
import java.sql.DriverManager; // JDBCを取り扱うためのクラス
import java.sql.SQLException; // データベースの取り扱いで発生した例外を処理するクラス

public class DbConnect_1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		try {
			// データベースに接続
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/java_db",
					"root",
					""
					);
			
			System.out.println("データベース接続成功");
			System.out.println(con);
			
			// データベース接続を解除
			con.close();
			
		} catch(SQLException e) {
			System.out.println("データベース接続失敗：" + e.getMessage());
		}
	}

}
