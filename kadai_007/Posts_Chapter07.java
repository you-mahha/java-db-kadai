package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

    public static void main(String[] args) {

        Connection con = null;
        Statement statement = null;

        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "" // パスワード設定なし
            );
            System.out.println("データベース接続成功" + con);

            // Statement作成
            statement = con.createStatement();
            System.out.println("レコード追加を実行します");

            // データの追加
            String insertSql =
                "INSERT INTO posts (post_id, user_id, posted_at, post_content, likes) VALUES " +
                "(1, 1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13), " +
                "(2, 1002, '2023-02-08', 'お疲れ様です！', 12), " +
                "(3, 1003, '2023-02-09', '今日も頑張ります！', 18), " +
                "(4, 1001, '2023-02-09', '無理は禁物ですよ！', 17), " +
                "(5, 1002, '2023-02-10', '明日から連休ですね！', 20)";

            int rowCnt = statement.executeUpdate(insertSql);
            System.out.println(rowCnt + "件のレコードが追加されました");

            // データ取得
            ResultSet result =
                statement.executeQuery("SELECT * FROM posts WHERE user_id = 1002");
            System.out.println("ユーザーIDが1002のレコードを検索しました");

            while (result.next()) {
            	String post_content = result.getString("post_content");
            	String posted_at = result.getString("posted_at");
            	int likes = result.getInt("likes");
                System.out.println(result.getRow() + "件目：投稿日時=" + posted_at +  "／投稿内容=" + post_content + "／いいね数=" + likes);
                  
            }

        } catch (SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException ignore) {
            }
        }
    }
}