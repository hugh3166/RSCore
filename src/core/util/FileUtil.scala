package core.util
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.FileInputStream

object FileUtil {
	/**
	 * �t�@�C���p�X���L�[�ɁC���̓��e���擾����
	 * @param eliminateBlankLines ��s���폜���邩
	 */
	def getFileContents(filepath: String, eliminateBlankLines: Boolean = true): String = {
		val blankLine = """^\s*$"""

		if (eliminateBlankLines) {
			return scala.io.Source.fromFile(filepath).getLines.toArray
				.filterNot(line => line.matches(blankLine) || line == "")
				.mkString("\n")
		} else {
			return scala.io.Source.fromFile(filepath).getLines.toArray
				.mkString("\n")
		}
	}
	
	/**
	 * �����񂩂��s����菜��
	 */
	def eliminateBlankLines(string: String): String = {
		val blankLine = """^\s*$"""
		val lines = string.split("\n").filterNot(line => line.matches(blankLine) || line == "")
		return lines.mkString("\n")
	}

}