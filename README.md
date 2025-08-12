【概要】

コマンドライン引数に数式を与えて実行すると標準出力に答えを出力する電卓プログラムです。

プログラムを実行すると「式を入力してください。」と出るので、任意の式を入れてください。

例）「1+10/2-3-7*6」と入力後実行キーを押すと答えが表示される



【テストの実施方法】

1.以下のページよりjunit.jarとhamcrest-core.jarをダウンロードし、javaファイルと同じフォルダに格納する

　https://github.com/junit-team/junit4/wiki/Download-and-Install

2.以下のコマンドを実行しパスを指定する

　javac -cp junit-4.13.2.jar Calculate.java CalculateTest.java

3.以下のコマンドを実行しテストを実施する

　java -cp junit-4.13.2.jar:hamcrest-core-1.3.jar:.  org.junit.runner.JUnitCore  CalculateTest



【更新履歴】

8/1 level1:数の数値の四則演算を実装

8/4 level2:'E'表記の対応を実装

8/6 level2:質問の内容を踏まえて回筆修正

8/7 level3:カッコの処理を実装

8/10 level4:sqrt関数を実装

8/12 ユニットテストを実装

