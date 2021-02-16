package AlgLin;

import java.io.*;
import java.util.*;

public class IrregularSysLinException extends Exception{
	public String toString() {
		String res = "Le système est irrégulier";
		return res;
	}
}