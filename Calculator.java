import java.util.*;

public class Calculator {
	
	public static void main(String args[]) {

		// アスタリスクをコマンドライン引数に指定するとファイル名が展開されてしまうためScannerを使用
		Scanner scanner = new Scanner(System.in);
		System.out.println("四則演算に対応した計算機です。式を入力してください。");
		String formula = scanner.nextLine();
		scanner.close();
		
		Calculate cal = new Calculate();
		
		try {
			// 計算を行う
			double result = cal.calculate(formula);
			// 結果を出力
			System.out.println(formula + " の答えは " + String.valueOf(result) + " です。");
			
		} catch (NumberFormatException e) {
			// 数値変換に失敗した場合
			System.out.println("不正な数値が入力されています。：" + e.getMessage());
		}
		
	}
	
}