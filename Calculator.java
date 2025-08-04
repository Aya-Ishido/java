import java.util.*;
import java.lang.Math;

public class Calculator {
	
	public static void main(String args[]) {

		// アスタリスクをコマンドライン引数に指定するとファイル名が展開されてしまうためScannerを使用
		Scanner scanner = new Scanner(System.in);
		System.out.println("四則演算に対応した計算機です。式を入力してください。");
		String inputStr = scanner.nextLine();
		scanner.close();
		
		String num = "";
		List<String> list = new ArrayList<>();
		Boolean exponentFlg = false;	// 指数フラグ
		String exponent = "";			// 指数
		
		try {
			// Indexofを使うためListに格納
			for(int i = 0; i < inputStr.length(); i++) {
				char c = inputStr.charAt(i);
				if (c == '+' || c == '-' || c == '*' || c == '/') {
					// 直前までの数値をリストに格納
					if (!num.isEmpty()) {
						list.add(num);
						num = "";
					}
					// 演算子をリストに格納
					list.add(String.valueOf(c));
				} else {
					// 数値の終端まで繋げる
					num += String.valueOf(c);
				}
			}
			list.add(num);
		
			// 単純四則計算
			double num1 = 0;
			double num2 = 0;
			String strNum1 = "";
			String strNum2 = "";
			double result = 0;
			// 積・商の演算子を前から一つずつ処理
			while (list.contains("*") || list.contains("/")) {
				int indexOfOpe = 0;
				// リストの最前にある積または商の計算
				if (list.contains("/")) {
					indexOfOpe = list.indexOf("/");
					strNum1 = list.get(indexOfOpe - 1);
					strNum2 = list.get(indexOfOpe + 1);
					num1 = exponent(strNum1);
					num2 = exponent(strNum2);
					result = num1 / num2;
				} else if (list.contains("*")) {
					indexOfOpe = list.indexOf("*");
					strNum1 = list.get(indexOfOpe - 1);
					strNum2 = list.get(indexOfOpe + 1);
					num1 = exponent(strNum1);
					num2 = exponent(strNum2);
					result = num1 * num2;
				}
				// tmpListに上の計算結果とそれ以外を格納していく
				ArrayList<String> tmpList = new ArrayList<>();
				for(int i = 0; i < list.size(); i++) {
					if (i == indexOfOpe) {
						tmpList.add(String.valueOf(result));
					} else if (i != (indexOfOpe - 1) && i != (indexOfOpe + 1)) {
						tmpList.add(list.get(i));
					}
				}
				// 積・商の演算子が残っているか判定するためtmpListでlistを上書き
				list = new ArrayList<>(tmpList);
			}
			
			// 和・差の計算
			num1 = 0;
			num2 = 0;
			result = 0;
			for(int i = 0; i < list.size(); i++) {
				String str = list.get(i);
				if (str != "+" && str != "-") {
					if (i == 0) {
						result = Double.parseDouble(str);
					} else if (list.get(i - 1).equals("+")) {
						result = result + Double.parseDouble(str);
					} else if (list.get(i - 1).equals("-")) {
						result = result - Double.parseDouble(str);
					}
				}
			}
			
			// 結果を出力
			System.out.println(inputStr + " の答えは " + String.valueOf(result) + " です。");
			
		} catch (NumberFormatException e) {
			// 数値変換に失敗した場合
			System.out.println("不正な数値が入力されています。：" + e.getMessage());
		}
		
	}
	
	// 文字列をdoubleに変換して返す（指数表記対応済み）
	public static double exponent(String num) {
		double result = 0;
		// 指数表記の有無で分岐
		if (num.contains("E")) {
			double num1 = Double.parseDouble(num.substring(0, num.indexOf("E")));
			double num2 = Double.parseDouble(num.substring(num.indexOf("E") + 1));
			// TODO:-10対応も必要？
			result = num1 * Math.pow(10, num2);
		} else {
			result = Double.parseDouble(num);
		}
		return result;
	}

}