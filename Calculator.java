import java.util.*;
import java.lang.Math;

public class Calculator {
	
	public static void main(String args[]) {

		// アスタリスクをコマンドライン引数に指定するとファイル名が展開されてしまうためScannerを使用
		Scanner scanner = new Scanner(System.in);
		System.out.println("四則演算に対応した計算機です。式を入力してください。");
		String formula = scanner.nextLine();
		scanner.close();
		
		try {
			double result = calculate(formula);
						
			// 結果を出力
			System.out.println(formula + " の答えは " + String.valueOf(result) + " です。");
			
		} catch (NumberFormatException e) {
			// 数値変換に失敗した場合
			System.out.println("不正な数値が入力されています。：" + e.getMessage());
		}
		
	}
	
	// 入力された式の計算を行う
	public static double calculate(String formula) {
		
		double result = 0;
		String num = "";
		List<String> list = new ArrayList<>();
		Boolean exponentFlg = false; // 指数フラグ
		
		// Indexofを使うためListに格納
		for(int i = 0; i < formula.length(); i++) {
			char c = formula.charAt(i);
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				if (exponentFlg && (c == '+' || c == '-')) {
					num += String.valueOf(c);
					exponentFlg = false;
				} else {
					if (!num.isEmpty()) {
						// 直前までの数値をリストに格納
						list.add(num);
						num = "";
					}
					// 演算子をリストに格納
					list.add(String.valueOf(c));
				}
			} else {
				// 指数（次の式に対応）：1E1, 1e1, 1E+1, 1E-1, 1e+1, 1e-1
				if (c == 'E' || c == 'e') {
				 	exponentFlg = true;
				 }
				// 数値の終端まで繋げる
				num += String.valueOf(c);
			}
		}
		list.add(num);
		
		// 積・商の演算子を前から一つずつ処理
		double num1 = 0;
		double num2 = 0;
		double tmpResult = 0;
		String strNum1 = "";
		String strNum2 = "";
		while (list.contains("*") || list.contains("/")) {
			int indexOfOpe = 0;
			// リストの最前にある積または商の計算
			if (list.contains("/")) {
				indexOfOpe = list.indexOf("/");
				strNum1 = list.get(indexOfOpe - 1);
				strNum2 = list.get(indexOfOpe + 1);
				num1 = exponent(strNum1);
				num2 = exponent(strNum2);
				tmpResult = num1 / num2;
			} else if (list.contains("*")) {
				indexOfOpe = list.indexOf("*");
				strNum1 = list.get(indexOfOpe - 1);
				strNum2 = list.get(indexOfOpe + 1);
				num1 = exponent(strNum1);
				num2 = exponent(strNum2);
				tmpResult = num1 * num2;
			}
			// tmpListに上の計算結果とそれ以外を格納していく
			ArrayList<String> tmpList = new ArrayList<>();
			for(int i = 0; i < list.size(); i++) {
				if (i == indexOfOpe) {
					tmpList.add(String.valueOf(tmpResult));
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
		return result;
		
	}
	
	// 文字列をdoubleに変換して返す（指数表記対応済み）
	public static double exponent(String num) {
		
		double result = 0;
		// 指数表記の有無で分岐
		if (num.contains("E") || num.contains("e")) {
			double num1 = 0;
			double num2 = 0;
			char exp;
			if (num.contains("E")) {
				exp = 'E';
			} else {
				exp = 'e';
			}
			num1 = Double.parseDouble(num.substring(0, num.indexOf(exp)));
			if (num.contains("+")) {
				// 1E+1, 1e+1
				num2 = Double.parseDouble(num.substring(num.indexOf(exp) + 2));
				result = num1 * Math.pow(10, num2);
			} else if (num.contains("-")) {
				// 1E-1, 1e-1
				num2 = Double.parseDouble(num.substring(num.indexOf(exp) + 2));
				result = num1 * Math.pow(10, (-1) * num2);
			} else {
				// 1E1
				num2 = Double.parseDouble(num.substring(num.indexOf(exp) + 1));
				result = num1 * Math.pow(10, num2);
			}
		} else {
			result = Double.parseDouble(num);
		}
		return result;
		
	}

}