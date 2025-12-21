package text.section_08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUpdate_1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Connection con = null; //オブジェクト作成
		Statement statement = null; //オブジェクト作成

		try {
			// データベースに接続
			con = DriverManager.getConnection("jdbc:mysql://localhost/java_db",
					"root",
					"");

			System.out.println("データベース接続成功");

			// SQLクエリを準備
			statement = con.createStatement();
			String sql = "UPDate users SET name = '武士山花子' WHERE id = 2;";

			// SQLクエリを実行 (DBMSに送信)
			System.out.println("レコード更新:" + statement.toString());
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが更新されました");
		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			// 使用したオブジェクトを解放
			if (statement != null) { // 空白でなかったら
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}

	}
}