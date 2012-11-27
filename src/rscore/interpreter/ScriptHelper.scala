package rscore.interpreter

/**
 * �X�N���v�g�̒����C�ό`�Ɋւ���w���p
 */
object ScriptHelper{
	// equals to project name
	private val ToplevelNamespace = "rscore"
	/**
	 * Packages to be imported
	 */
	private val DefaultImportPackages = Array[String](
			"dsl.entity.RSWorkspace",
			"dsl.entity.collection.By",
			"dsl.entity.collection.With",
			"dsl.entity.collection.WithOr",
			"dsl.entity.collection.WithAnd"
			)
			
	/**
	 * Generate 'java_import' statements
	 */
	def buildImportDeclaration(importPackages: Array[String] = DefaultImportPackages): String = {
		return importPackages.map(e => "java_import '" + ToplevelNamespace + "." + e + "'").mkString("\n")
	}
	
	def buildScript(script: String): String = {
		val newScript = "require 'java'\n" + buildImportDeclaration() + "\n" + script
		return newScript
	}
}