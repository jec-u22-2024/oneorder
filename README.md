# OneOrder
一応先に書いておくこととして Thymeleaf 使ってます。

### バックエンドといったな、あれはちょっと嘘だ。
というのも、フロント画面をビルドしたやつも入っているのです。

### どう起動するのさ

VSCodeにSpring Boot Extension Pack入れておいてね  
<img width="1440" alt="スクリーンショット 2024-08-13 10 29 11" src="https://github.com/user-attachments/assets/0e7f91aa-3278-4fa1-a445-b75353d7f650">

VSCode で oneorder_backをクローンしてプルとかして持ってくる

多分VSCode再読み込みしたら左に下の画像見たいなアイコン出てくると思う

<img width="38" alt="スクリーンショット 2024-08-13 10 35 52" src="https://github.com/user-attachments/assets/02258024-4ea2-42a0-aab2-459c981b59c8">

そこ開くとこんな感じになってると思うから、一番上のAPPSにカーソル合わせたら起動できそうなマーク出てくるからそこクリックすれば動きます。多分。

<img width="216" alt="スクリーンショット 2024-08-13 10 37 15" src="https://github.com/user-attachments/assets/6c89b47c-91b4-4f89-be54-8279258d2e19">

あとはなんか諸々試してほしい

### 基本のパス

/order (user)

/master (regi)

/kitchen (kitchen)

から始まります。

あと多分mysql入れてないとfetchとかできないと思うので要注意

mysqlのデータベース作ったりユーザー作ったりテーブル作ったりするところはあとでsqlファイルでも作っておきます。

気が向いたらテストデータもそこに突っ込むと思う。
