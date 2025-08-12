import org.junit.Test;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import java.lang.*;

public class CalculateTest {

	@Test
    public void testCalclate1() {
    	// 演算子混合の四則演算
        Calculate cal = new Calculate();
        String formula = "12+11-10*9/8";
        assertEquals(Double.parseDouble("11.75"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate2() {
    	// 演算子混合+カッコの四則演算
        Calculate cal = new Calculate();
        String formula = "2*(12+11-10*9/8)-(12+11-10*9/8)";
        assertEquals(Double.parseDouble("11.75"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate3() {
    	// カッコの前の*演算子を省略した式：対応していないためNGとなるはず
        Calculate cal = new Calculate();
        String formula = "2(12+11-10*9/8)";
        assertEquals(Double.parseDouble("23.5"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate4() {
    	// 小数の四則演算：思わぬNG！doubleでなくBigDecimalにしておけばよかった！
        Calculate cal = new Calculate();
        String formula = "0.12+0.11-0.1*0.9/0.8";
        assertEquals(Double.parseDouble("0.1175"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate5() {
    	// 指数の四則演算
        Calculate cal = new Calculate();
        String formula = "6E3+5E3-4E3*3E3/2E2";
        assertEquals(Double.parseDouble("-49000"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate6() {
    	// 多重カッコのある四則演算
        Calculate cal = new Calculate();
        String formula = "((10+20)/(9-3)+10)-((10+5*(10-4))*2)";
        assertEquals(Double.parseDouble("-65"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate7() {
    	// マイナス値の四則演算：考慮していないためNGとなるはず→ならない！
        Calculate cal = new Calculate();
        String formula = "-1+-2";
        assertEquals(Double.parseDouble("-3"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate8() {
    	// マイナス値の四則演算：カッコに入っていれば計算可能なのでOKとなるはず
        Calculate cal = new Calculate();
        String formula = "(-1)+(-2)";
        assertEquals(Double.parseDouble("-3"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate9() {
    	// sqrt関数を使った四則演算1
        Calculate cal = new Calculate();
        String formula = "sqrt(sqrt(6E3+(13*40)+41))";
        assertEquals(Double.parseDouble("9"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate10() {
    	// sqrt関数を使った四則演算2
        Calculate cal = new Calculate();
        String formula = "100+sqrt(sqrt(256)*4)-sqrt(8*8)";
        assertEquals(Double.parseDouble("100"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate11() {
    	// 答えが循環小数となる四則演算：丸める処理を入れていないのでOKとなるはず（doubleは小数点以下約15桁まで保持）
        Calculate cal = new Calculate();
        String formula = "1/7";
        assertEquals(Double.parseDouble("0.142857142857142857142857"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate12() {
    	// マイナス値の四則演算：カッコ前のマイナス記号は考慮していないのでNGとなるはず
        Calculate cal = new Calculate();
        String formula = "10-(-3)";
        assertEquals(Double.parseDouble("13"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate13() {
    	// 指数表記が混同している四則演算
        Calculate cal = new Calculate();
        String formula = "5E2*(1E-4+1e-3)*(3E+2+2e+4)";
        assertEquals(Double.parseDouble("11165"), cal.calculate(formula), 0.0);
    }
    
	@Test
    public void testCalclate14() {
    	// 閉じカッコがない場合：上のCalculatorでエラーをキャッチ
        Calculate cal = new Calculate();
        String formula = "(1+2)*3+(2-5";
        assertThrows(NumberFormatException.class, () -> cal.calculate(formula));
    }
    
	@Test
    public void testCalclate15() {
    	// スペースが入っている場合：parseDoubleはスペースを無視するのでOKとなるはず
        Calculate cal = new Calculate();
        String formula = "(1 + 2) * 3 / 3";
        assertEquals(Double.parseDouble("3"), cal.calculate(formula), 0.0);
    }

	@Test
    public void testCalclateDevideZero() {
    	// ゼロ除差はNG：考慮していないのでNGとなるはず
        Calculate cal = new Calculate();
        String formula = "3/0";
        assertThrows(ArithmeticException.class, () -> cal.calculate(formula));
    }
    
    
}