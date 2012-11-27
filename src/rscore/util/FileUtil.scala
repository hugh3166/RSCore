package rscore.util
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.FileInputStream

object FileUtil {
	/**
	 * �t�@�C���p�X���L�[�ɁC���̓��e���擾����
	 * @param eliminateBlankLines ��s���폜���邩
	 */
	def getFileContents(filepath: String,
		eliminateBlankLines: Boolean = true,
		eliminateCommentLines: Boolean = true): String = {
		
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
	 * �����s������i\n ��؂蕶����j�����s����菜��
	 * @param eliminateHeadBlanks �����āC�擪�̋󔒂����ׂč폜����
	 */
	def eliminateBlankLines(string: String, eliminate: Boolean = false): String = {
		val blankLine = """^\s*$"""
		val lines = string.split("\n").filterNot(line => line.matches(blankLine) || line == "")
			.map(e => if (eliminate) eliminateHeadBlanks(e) else e)
		return lines.mkString("\n")
	}

	/**
	 * ������̐擪�̋󔒂���菜��
	 */
	def eliminateHeadBlanks(string: String): String = {
		val headBlank = """^\s+"""
		val r = string.replaceAll(headBlank, "")
		return r
	}

	/**
	 * �R�����g�s (// ...) ����菜��
	 */
	def eliminateCommentLines(string: String): String = {
		val commentLine = """^\s*//.*$"""
		val lines = string.split("\n").filterNot(line => line.matches(commentLine))
		return lines.mkString("\n")
	}

}