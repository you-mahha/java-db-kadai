package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		Scanner scanner = null; // オブジェクト化
		Connection con = null;
		Statement statement = null;
		
		try {
			System.out.println("0(昇順)か1(降順)を入力してください：");
			scanner = new Scanner(System.in); // 入力待ち
			
			// 入力内容に応じて並べ替え方向を決定
						String order = switch( scanner.nextInt() ) {
						case 0 -> "ASC"; // 昇順
						case 1 -> "DESC"; // 降順
						default -> "ASC"; // デフォルトは昇順扱い
						};
						
						// データベースに接続
						con = DriverManager.getConnection(
								"jdbc:mysql://localhost/challenge_java",
								"root",
								"" // パスワード未設定
								);
						
						System.out.println("データベース接続成功" + con);
						
						// SQLクエリを準備(データベースの編集内容を入力)
						statement = con.createStatement();
						String updateSql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5";
						 int rowCnt = statement.executeUpdate(updateSql);
						 	System.out.println("レコード更新を実行します");
				            System.out.println( rowCnt + "件のレコードが更新されました");
						
						String selectSql = "SELECT * FROM scores ORDER BY score_math DESC, score_english " + order;
						
						// SQLクエリを実行（DBMSに送信）
						ResultSet result = statement.executeQuery(selectSql);
			            System.out.println("数学・英語の点数が高い順に並べ替えました");
			            
			            // 結果
			            while (result.next()) {
			            	int id= result.getInt("id");
			            	String name = result.getString("name");
			            	int score_math = result.getInt("score_math");
			            	int score_english = result.getInt("score_english");
			            	System.out.println(result.getRow() + "件目：生徒ID=" + id + "／氏名=" + name + "／数学=" + score_math + "／英語=" + score_english);
			            }
			        } catch(SQLException e) {
			            System.out.println("エラー発生：" + e.getMessage());
			        } finally {
			        
			            // 使用したオブジェクトを解放
			        	try {
			            if( statement != null ) statement.close();			            
			            if( con != null)con.close();
			            if (scanner != null) scanner.close();
			            } catch(SQLException ignore) {}
	}
}}