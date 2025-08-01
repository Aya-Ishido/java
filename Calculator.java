import java.util.*;

public class Calculator {
	
	public static void main(String args[]) {
		
		Scanner scanner = new Scanner(System.in);
        
        // アスタリスクをコマンドライン引数に指定するとファイル名が展開されてしまうためScannerを使用
        System.out.println("四則演算に対応した計算機です。式を入力してください。");
        String inputStr = scanner.nextLine();
        scanner.close();
		
		// Indexofを使うためListに格納
		String num = "";
		List<String> list = new ArrayList<>();
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
		try {
			double num1 = 0;
			double num2 = 0;
			double result = 0;
			// 積・商の演算子を前から一つずつ処理
			while (list.contains("*") || list.contains("/")) {
				int indexOfOpe = 0;
				// リストの最前にある積または商の計算
				if (list.contains("*")) {
					indexOfOpe = list.indexOf("*");
					num1 = Double.parseDouble(list.get(indexOfOpe - 1));
					num2 = Double.parseDouble(list.get(indexOfOpe + 1));
					result = num1 * num2;
				} else if (list.contains("/")) {
					indexOfOpe = list.indexOf("/");
					num1 = Double.parseDouble(list.get(indexOfOpe - 1));
					num2 = Double.parseDouble(list.get(indexOfOpe + 1));
					result = num1 / num2;
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

}